package mycompanyname.foodtracker;

import android.app.IntentService;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Nicholas on 8/11/2018.
 */

public class MyIntentService extends IntentService {
    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    private static final int NOTIFICATION_ID = 3;
    private static ArrayList<Perishable> checkingDates;
    private static final String TABLE_NAME = "fridge";
    static DatabaseHandler newDBH;
    private SQLiteDatabase newDB;
    private boolean isBad;
    public String[] badItems;
    ArrayList<String> names = new ArrayList<String>();


    public MyIntentService() {
        super("MyIntentService");
    }

    public MyIntentService(boolean isBad) {
        super("MyIntentService");
        this.isBad = isBad;
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        Calendar calendar = Calendar.getInstance();
        Log.i(getClass().getSimpleName(), "calendar value: " + calendar.getTime());
        Log.e(getClass().getSimpleName(), "******\n" + isBad + "\n******");

        isBad = false;
        Log.e(getClass().getSimpleName(), "current time: " + System.currentTimeMillis());
        //long endTime = System.currentTimeMillis() + 86400*1000;//86400*1000;
        //Log.e(getClass().getSimpleName(), "endTime: " + endTime);
        //SystemClock.sleep(64800000);//64800000
        newDBH = new DatabaseHandler(getApplicationContext());
        checkingDates = new ArrayList<Perishable>();

        openAndQueryBadDates();
        badItems = new String[checkingDates.size()];
        checkForBadItems();
        Log.i(getClass().getSimpleName(), "Here is badItems: " + Arrays.toString(badItems));
        String changedArray = Arrays.toString(badItems);
        changedArray = alterString(changedArray);

        Log.i(getClass().getSimpleName(), "New badItems list: " + changedArray);
        if(!isBad) {
            //do nothing
            Log.e(getClass().getSimpleName(), "******\ndoes this fire\n******");
            if(changedArray.contains(System.lineSeparator()))
            {
                Log.e(getClass().getSimpleName(), "******\n1\n******");
                NotificationCompat.Builder nBuilder = new NotificationCompat.Builder(getApplicationContext(), "some_channel")
                        .setSmallIcon(R.drawable.redpepper)//need to place setsound() in notification and put unique sound in project
                        .setContentTitle("Good news")
                        .setContentText("None of your food is going to spoil soon")
                        .setStyle(new NotificationCompat.BigTextStyle().bigText("None of your food is going to spoil soon"));
                //PendingIntent resultPending = nBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
                Intent notifyIntent = new Intent(this, MainActivity.class);
                PendingIntent pendingIntent = PendingIntent.getActivity(this, 2, notifyIntent, PendingIntent.FLAG_UPDATE_CURRENT);
                //to be able to launch your activity from the notification
                nBuilder.setContentIntent(pendingIntent);
                Notification notificationCompat = nBuilder.build();
                NotificationManagerCompat managerCompat = NotificationManagerCompat.from(this);
                managerCompat.notify(NOTIFICATION_ID, notificationCompat);
                nBuilder.build();
            }
        }
        else if(changedArray.equals(""))
            {
                //do nothing
                //never fires off notification
                /*NotificationCompat.Builder nBuilder = new NotificationCompat.Builder(getApplicationContext(), "some_channel")
                        .setSmallIcon(R.drawable.redpepper)
                        .setContentTitle("Good news 2")
                        //.setContentText("The following items may be getting close to spoiling:\n" + Arrays.toString(badItems))
                        .setStyle(new NotificationCompat.BigTextStyle().bigText("None of your food is going to spoil soon"));

                //PendingIntent resultPending = nBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
                Intent notifyIntent = new Intent(this, MainActivity.class);
                PendingIntent pendingIntent = PendingIntent.getActivity(this, 2, notifyIntent, PendingIntent.FLAG_UPDATE_CURRENT);
                //to be able to launch your activity from the notification
                nBuilder.setContentIntent(pendingIntent);
                Notification notificationCompat = nBuilder.build();
                NotificationManagerCompat managerCompat = NotificationManagerCompat.from(this);
                managerCompat.notify(NOTIFICATION_ID, notificationCompat);
                nBuilder.build();*/
            }
        else {
            NotificationCompat.Builder nBuilder = new NotificationCompat.Builder(getApplicationContext(), "some_channel")
                    .setSmallIcon(R.drawable.redpepper)
                    .setContentTitle("Attention")
                    //.setContentText("The following items may be getting close to spoiling:\n" + Arrays.toString(badItems))
                    .setStyle(new NotificationCompat.BigTextStyle().bigText("The following items may be getting close to spoiling:\n" + changedArray));
            //PendingIntent resultPending = nBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
            Intent notifyIntent = new Intent(this, MainActivity.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(this, 2, notifyIntent, PendingIntent.FLAG_UPDATE_CURRENT);
            //to be able to launch your activity from the notification
            nBuilder.setContentIntent(pendingIntent);
            Notification notificationCompat = nBuilder.build();
            NotificationManagerCompat managerCompat = NotificationManagerCompat.from(this);
            managerCompat.notify(NOTIFICATION_ID, notificationCompat);
            nBuilder.build();
        }
    }

    private String alterString(String changedArray) {
        String mod = changedArray;
        mod = mod.replace("[", "");
        mod = mod.replace("null", "");
        mod = mod.replace(", null", "");
        mod = mod.replace(", ", System.lineSeparator());
        mod = mod.replace("]", "");
        return mod;
    }

    private void checkForBadItems() {
        //get the current date in mm/dd/yyyy format
        //check it with each item's badonmax date
        //set isBad to true if so, put item name in new list to be used in notification
        //return the list
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yy");
        Calendar thisDay = Calendar.getInstance();
        Date today = thisDay.getTime();
        Log.e(getClass().getSimpleName(), "today's date: " + thisDay.getTime());
        Date nextDate = new Date();
        int j = 0;//size counter for badItems list
        Log.i(getClass().getSimpleName(), "isBad: " + isBad);

        for(int i = 0; i < checkingDates.size(); i++)
        {
            try {
                Log.e(getClass().getSimpleName(), "inside try isBad: " + isBad);
                nextDate = dateFormat.parse(checkingDates.get(i).getBadOnMax());
                Log.i(getClass().getSimpleName(), "nextDate" + i + ": " + nextDate);
                if(nextDate.before(today))
                {
                    Log.i(getClass().getSimpleName(), "inside ifcheckdate isBad: " + isBad);
                    String temp = checkingDates.get(i).getpName();
                    temp = removeInts(temp);
                    badItems[j] = temp + "\n";
                    j++;
                }

            } catch (ParseException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }


            if(j >= 1)
            {
                Log.i(getClass().getSimpleName(), "inside if j>0 isBad: " + isBad);
                isBad = true;

                Log.e(getClass().getSimpleName(), "inside if j>0 isBad: " + isBad);
            }
            Log.e(getClass().getSimpleName(), "is expired date before today " + today.compareTo(nextDate));
            Log.v(getClass().getSimpleName(), "dateFormat: " + nextDate);
            Log.v(getClass().getSimpleName(), "i= " + i + ", " + checkingDates.get(i).getBadOnMax());
        }
    }

    private void openAndQueryBadDates()
    {
        try
        {
            //DatabaseHandler dbh = new DatabaseHandler(actContext);
            newDB = newDBH.getWritableDatabase();
            Cursor c = newDB.rawQuery("SELECT * FROM " + TABLE_NAME + ";", null);

            if(c != null)
            {
                if(c.moveToFirst())
                {
                    do
                    {
                        Perishable temp = new Perishable();
                        String itemName = c.getString(c.getColumnIndex("name"));
                        String badon = c.getString(c.getColumnIndex("badondatemax"));
                        temp.setpName(itemName);
                        temp.setBadOnMax(badon);
                        //names.add(itemName);
                        checkingDates.add(temp);
                    } while (c.moveToNext());
                }
            }
        }
        catch(SQLiteException se)
        {
            Log.e(getClass().getSimpleName(), "22 Could not create or open the database (IntentService)");
        }
        finally
        {
            if(newDB != null)
            {
                newDB.close();
            }
        }
    }

    private String removeInts(String orig) {
        String mod = orig;
        mod = mod.replace("0", "");
        mod = mod.replace("1", "");
        mod = mod.replace("2", "");
        mod = mod.replace("3", "");
        mod = mod.replace("4", "");
        mod = mod.replace("5", "");
        mod = mod.replace("6", "");
        mod = mod.replace("7", "");
        mod = mod.replace("8", "");
        mod = mod.replace("9", "");
        return mod;
    }
}
