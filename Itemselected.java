package mycompanyname.foodtracker;

import android.app.Fragment;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by Nicholas on 2/21/2018.
 */

public class Itemselected extends FragmentActivity {
    String Pantry1 = "", Pantry2 = "", Fridge1 = "", Fridge2 = "", Freezer1 = "", Freezer2 = "";
    Bundle received;
    DatabaseHandler dbh;
    SQLiteDatabase database1;
    Perishable newItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_itemselected);

		/*if (savedInstanceState == null) {
			getFragmentManager().beginTransaction()
					.add(R.id.mainlayout, new android.app.Fragment()).commit();
		}*/
        dbh = new DatabaseHandler(getApplicationContext());
        received = getIntent().getExtras();

        Log.e(getClass().getSimpleName(), "2 received: " + received);
        String itemName = received.getString("name");
        int uopantrymin = received.getInt("uopantrymin");
        int uopantrymax = received.getInt("uopantrymax");
        int uofridgemin = received.getInt("uofridgemin");
        int uofridgemax = received.getInt("uofridgemax");
        int uofreezemin = received.getInt("uofreezermin");
        int uofreezemax = received.getInt("uofreezermax");
        int opantrymin = received.getInt("opantrymin");
        int opantrymax = received.getInt("opantrymax");
        int ofridgemin = received.getInt("ofridgemin");
        int ofridgemax = received.getInt("ofridgemax");
        int ofreezemin = received.getInt("ofreezermin");
        int ofreezemax = received.getInt("ofreezermax");

        /**
         * create a check for whether the item can have two states or not (this means put another row in
         * the table that is called states) with 2 values, 1 meaning only one state(fruits and veggies), or 2
         * meaning 2 states (things like cheese and lunch meat) which are sealed in a container and have to be
         * opened
         * the check will determine if the switch will be enabled or not
         *
         * add unopened values, they need to be saved into memory too in case
         * the person switches between the two states
         * */
        Log.e(getClass().getSimpleName(), "pantrymin: " + uopantrymin);
        Log.e(getClass().getSimpleName(), "pantrymax: " + uopantrymax);
        Log.e(getClass().getSimpleName(), "fridgemin: " + uofridgemin);
        Log.e(getClass().getSimpleName(), "fridgemax: " + uofridgemax);
        Log.e(getClass().getSimpleName(), "freezemin: " + uofreezemin);
        Log.e(getClass().getSimpleName(), "freezemax: " + uofreezemax);

        /** send all this code to the function openOrUnopened()*/
        Pantry1 = checkForZero(uopantrymin, Pantry1);
        //thisDay.setTime(today);
        //thisDay.add(Calendar.DATE, pantrymax);

        //String pantry2 = "";//sdf.format(thisDay.getTime());
        Pantry2 = checkForZero(uopantrymax, Pantry2);
        //thisDay.setTime(today);
        //Date today = thisDay.getTime();
        //thisDay.add(Calendar.DATE, fridgemin);

        //String fridge1 = "";// sdf.format(thisDay.getTime());
        Fridge1 = checkForZero(uofridgemin, Fridge1);
        //thisDay.setTime(today);
        //thisDay.add(Calendar.DATE, fridgemax);

        //String fridge2 = "";//sdf.format(thisDay.getTime());
        Fridge2 = checkForZero(uofridgemax, Fridge2);
        //thisDay.setTime(today);
        //Date today = thisDay.getTime();
        //thisDay.add(Calendar.DATE, freezemin);

        //String freezer1 = "";//sdf.format(thisDay.getTime());
        Freezer1 = checkForZeroMonth(uofreezemin, Freezer1);
        //thisDay.setTime(today);
        //thisDay.add(Calendar.DATE, freezemax);

        //String freezer2 = "";//sdf.format(thisDay.getTime());
        Freezer2 = checkForZeroMonth(uofreezemax, Freezer2);

        newItem = new Perishable(itemName, Pantry1, Pantry2, Fridge1,
                Fridge2, Freezer1, Freezer2);

        TextView name = (TextView) findViewById(R.id.perishabletxt);
        name.setText(itemName);

        EditText pMin = (EditText) findViewById(R.id.pantrymindate);
        pMin.setText(Pantry1);

        EditText pMax = (EditText) findViewById(R.id.pantrymaxdate);
        pMax.setText(Pantry2);

        EditText rfMin = (EditText) findViewById(R.id.fridgemindate);
        rfMin.setText(Fridge1);

        EditText rfMax = (EditText) findViewById(R.id.fridgemaxdate);
        rfMax.setText(Fridge2);

        EditText fMin = (EditText) findViewById(R.id.freezemindate);
        fMin.setText(Freezer1);

        EditText fMax = (EditText) findViewById(R.id.freezemaxdate);
        fMax.setText(Freezer2);

        Calendar thisDay = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yy");
        String today = sdf.format(thisDay.getTime());

        EditText todayDate = (EditText) findViewById(R.id.todaydatetxt);
        todayDate.setText(today);

        View.OnClickListener addPantryItem = new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                pantryItemAdded(newItem);
            }
        };
        Button pantryBtn = (Button) findViewById(R.id.pantrybtn);
        pantryBtn.setOnClickListener(addPantryItem);
        if(Pantry1.equals("") && Pantry2.equals(""))
        {
            pantryBtn.setEnabled(false);
            pantryBtn.setBackgroundColor(Color.GRAY);
        }

        View.OnClickListener addFridgeItem = new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                fridgeItemAdded(newItem);
            }
        };
        Button fridgeBtn = (Button) findViewById(R.id.refrigeratebtn);
        fridgeBtn.setOnClickListener(addFridgeItem);
        if(Fridge1.equals("") && Fridge2.equals(""))
        {
            fridgeBtn.setEnabled(false);
            fridgeBtn.setBackgroundColor(Color.GRAY);
        }

        View.OnClickListener addFreezerItem = new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                freezerItemAdded(newItem);
            }
        };
        Button freezerBtn = (Button) findViewById(R.id.freezebtn);
        freezerBtn.setOnClickListener(addFreezerItem);
        if(Freezer1.equals("") && Freezer2.equals(""))
        {
            freezerBtn.setEnabled(false);
            freezerBtn.setBackgroundColor(Color.GRAY);
        }

        View.OnClickListener discardItem = new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                removeItem();
            }
        };
        Button discardButton = (Button) findViewById(R.id.discardbtn);
        discardButton.setOnClickListener(discardItem);

    }

    protected void freezerItemAdded(Perishable addedItem) {
        // TODO Auto-generated method stub
        //database1 = dbh.getWritableDatabase();
        addedItem.setBadOnMin(addedItem.getMinFreeze());
        addedItem.setBadOnMax(addedItem.getMaxFreeze());
        addedItem.setPosition(3);
        //database1.
        Log.e(getClass().getSimpleName(), "freezerItem added, bomax = " + addedItem.getBadOnMax() + ", bomin =" +
                addedItem.getBadOnMin() + ", pmax = " + addedItem.getMaxPantry() + ", pmin = " + addedItem.getMinPantry() +
                ", gmin = " + addedItem.getMinFridge() + ", gmax = " + addedItem.getMaxFridge() + ", fzrmin = " + addedItem.getMinFreeze() +
                ", frzmax = " + addedItem.getMaxFreeze());
        dbh.addPerishable(addedItem);
        Toast.makeText(getBaseContext(), "item added to freezer", Toast.LENGTH_LONG).show();
        finish();
    }

    /****need to figure out why the index value does not change when two of the same item are selected.
     *once I figure it out, the hopefully the different items in the database won't chanage when the other one is changed.
     * index value needs to change when same item is selected a second time.
     * SOMEWHERE ON THIS PAGE IS WHERE I NEED TO FIX THE ISSUE OF THE SAME INDEX BEING ASSIGNED WHEN THE ITEM IS SELECTED
     * A SECOND TIME.
     * @param addedItem
     */

    protected void fridgeItemAdded(Perishable addedItem) {
        // TODO Auto-generated method stub
        //addedItem.
        addedItem.setBadOnMin(addedItem.getMinFridge());
        addedItem.setBadOnMax(addedItem.getMaxFridge());
        addedItem.setPosition(2);


        Log.e(getClass().getSimpleName(), "What is the item: " +
                addedItem.getpName() + ", " + addedItem.getBadOnMin() + ", "  +
                addedItem.getBadOnMax() + ", "  + addedItem.getMinPantry() + ", " + addedItem.getMaxPantry() + ", "
                + addedItem.getMinFridge() + ", " + addedItem.getMaxFridge() + ", " + addedItem.getMinFreeze() + ", "
                + addedItem.getMaxFreeze());

        dbh.addPerishable(addedItem);
        Toast.makeText(getBaseContext(), "item added to fridge", Toast.LENGTH_LONG).show();
        finish();
    }

    protected void pantryItemAdded(Perishable addedItem) {
        // TODO Auto-generated method stub
        addedItem.setBadOnMin(addedItem.getMinPantry());
        addedItem.setBadOnMax(addedItem.getMaxPantry());
        addedItem.setPosition(1);
        Log.v(getClass().getSimpleName(), "pantryItem added, bomax = " + addedItem.getBadOnMax() + ", bomin =" +
                addedItem.getBadOnMin() + ", pmax = " + addedItem.getMaxPantry() + ", pmin = " + addedItem.getMinPantry() +
                ", gmin = " + addedItem.getMinFridge() + ", gmax = " + addedItem.getMaxFridge() + ", fzrmin = " + addedItem.getMinFreeze() +
                ", frzmax = " + addedItem.getMaxFreeze() + ", " + addedItem.getIndex());
        dbh.addPerishable(addedItem);
        Toast.makeText(getBaseContext(), "item added to pantry", Toast.LENGTH_LONG).show();
        finish();
    }

    protected void removeItem() {
        // TODO Auto-generated method stub
        //will include the remove sql statement for the databasehandler item, for now it just closes the view
        //and returns it to the mainactivity
        finish();
        Toast.makeText(getBaseContext(), "item removed", Toast.LENGTH_LONG).show();
    }

    private String checkForZeroMonth(int passedInt, String stringDate) {
        // TODO Auto-generated method stub
        Calendar thisDay = Calendar.getInstance();
        //Date today = thisDay.getTime();
        thisDay.add(Calendar.MONTH, passedInt);
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yy");
        if(passedInt == 0)
            stringDate = "";
        else
        {
            stringDate = sdf.format(thisDay.getTime());
        }

        return stringDate;
    }

    private String checkForZero(int passedInt, String stringDate) {
        Calendar thisDay = Calendar.getInstance();
        //Date today = thisDay.getTime();
        thisDay.add(Calendar.DATE, passedInt);
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yy");
        if(passedInt == 0)
            stringDate = "";
        else
        {
            stringDate = sdf.format(thisDay.getTime());
        }

        return stringDate;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.itemselected, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_itemselected,
                    container, false);
            return rootView;
        }
    }

    public void openOrUnopen(View v)
    {

    }
}
