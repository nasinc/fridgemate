package mycompanyname.foodtracker;


import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;
    private int NOTIFICATION_REMINDER_NIGHT = 1;
    static String ingredients;
    public SQLiteDatabase ingredsListDb;
    public String dbPath;
    public static String DBName = "ingreds.db";
    public static final int version = '1';
    public static Context currentContext;
    public static String tableName = "ingredsList";



    /*private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selectedFragment = null;
            switch (item.getItemId()) {
                case R.id.navigation_home:
                  //  mTextMessage.setText(R.string.title_home);
                    selectedFragment = ItemOneFragment.newInstance();
                    break;
                case R.id.navigation_category:
                    selectedFragment = ItemFourFragment.newInstance();
                    break;
                case R.id.navigation_dashboard:
                  // mTextMessage.setText(R.string.title_dashboard);
                    selectedFragment = ItemTwoFragment.newInstance();
                    break;
                case R.id.navigation_notifications:
                   // mTextMessage.setText(R.string.title_notifications);
                    selectedFragment = ItemThreeFragment.newInstance();
                    break;
                case R.id.navigation_list:
                    selectedFragment = VoiceInputDemo.newInstance();
                    break;
                case R.id.navigation_recipes:
                    selectedFragment = ItemFiveFragment.newInstance();
                    break;
            }

            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.frame_layout, selectedFragment);
            transaction.commit();
            return true;
        }
    };*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayShowHomeEnabled(false);
        getSupportActionBar().hide();
        //ingredients = "";
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        //final ActionBar actionBar = getActionBar();
        //actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        SimpleFragmentPagerAdapter adapter = new SimpleFragmentPagerAdapter(this, getSupportFragmentManager());

        // Set the adapter onto the view pager
        viewPager.setAdapter(adapter);

        final int[] Icons = new int[] {
          R.drawable.ic_home_black_24dp,
                R.drawable.ic_categories_24dp,
                R.drawable.ic_pantry,
                R.drawable.ic_recipes_24dp,
                R.drawable.ic_settings_black_24dps,
                R.drawable.ic_check_box_black_24dp
        };

        // Give the TabLayout the ViewPager
        TabLayout tabLayout = (TabLayout) findViewById(R.id.sliding_tabs);


        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setIcon(Icons[0]);
        tabLayout.getTabAt(1).setIcon(Icons[1]);
        tabLayout.getTabAt(2).setIcon(Icons[5]);
        tabLayout.getTabAt(3).setIcon(Icons[2]);
        tabLayout.getTabAt(4).setIcon(Icons[3]);
        tabLayout.getTabAt(5).setIcon(Icons[4]);

        // Specify that tabs should be displayed in the action bar.
        //

        // Create a tab listener that is called when the user changes tabs.
        /*ActionBar.TabListener tabListener = new ActionBar.TabListener() {
            public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
                // show the given tab
                Fragment selectedFragment = null;
                switch (tab.getPosition()) {
                    case R.id.navigation_home:
                        //  mTextMessage.setText(R.string.title_home);
                        selectedFragment = ItemOneFragment.newInstance();
                        break;
                    case R.id.navigation_category:
                        selectedFragment = ItemFourFragment.newInstance();
                        break;
                    case R.id.navigation_dashboard:
                        // mTextMessage.setText(R.string.title_dashboard);
                        selectedFragment = ItemTwoFragment.newInstance();
                        break;
                    case R.id.navigation_notifications:
                        // mTextMessage.setText(R.string.title_notifications);
                        selectedFragment = ItemThreeFragment.newInstance();
                        break;
                    case R.id.navigation_list:
                        selectedFragment = VoiceInputDemo.newInstance();
                        break;
                    case R.id.navigation_recipes:
                        selectedFragment = ItemFiveFragment.newInstance();
                        break;
                }
            }

            public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {
                // hide the given tab
            }

            public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {
                // probably ignore this event
            }
        };
        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
*/
        Intent notifyIntent = new Intent(this, MyReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), NOTIFICATION_REMINDER_NIGHT, notifyIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager aManager = (AlarmManager) getApplicationContext().getSystemService(Context.ALARM_SERVICE);

        Log.i(getClass().getSimpleName(), "IS THIS FIRING INSIDE MYRECEIVER");

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 8);
        calendar.set(Calendar.MINUTE, 45);
        calendar.set(Calendar.SECOND, 00);
        Log.i(getClass().getSimpleName(), "calendar value: " + calendar.getTime());

        aManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), 1000 * 60 * 60 * 24, pendingIntent);

        //alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), 24*60*60*1000 , pendingIntent);

        // Intent result = new Intent(this, ItemOneFragment.class);

        /*GregorianCalendar eightam = new GregorianCalendar();
        eightam.set(GregorianCalendar.HOUR_OF_DAY, 8);
        eightam.set(GregorianCalendar.MINUTE, 0);
        eightam.set(GregorianCalendar.SECOND, 0);
        eightam.set(GregorianCalendar.MILLISECOND, 0);



        NotificationCompat.Builder nBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.redpepper)
                .setContentTitle("Alert")
                .setContentText("The following items may be getting close to spoiling:\n" );//+ names);


        TaskStackBuilder sBuilder = TaskStackBuilder.create(this);
        //sBuilder.addParentStack(ItemOneFragment.class);
        sBuilder.addNextIntent(result);

        PendingIntent resultPending = sBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);

        nBuilder.setContentIntent(resultPending);
        nBuilder.setAutoCancel(true);

        int nNotificationId = 001;
        NotificationManager nNotifyMgr = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        nNotifyMgr.notify(nNotificationId, nBuilder.build());

        if(eightam.before(new GregorianCalendar())){
            eightam.add(GregorianCalendar.DAY_OF_MONTH, 1);
        }*/
        //nNotifyMgr.setRepeating(type, eightam.getTimeInMillis(), 1000*60*60*24, result);
        /*FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_layout, ItemOneFragment.newInstance());
        transaction.commit();*/
    }

}
