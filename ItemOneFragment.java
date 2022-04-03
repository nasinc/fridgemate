package mycompanyname.foodtracker;

import android.app.Fragment;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.content.Intent;
import android.support.v4.app.ListFragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by Nicholas on 1/22/2018.
 *
 *  HOME TAB
 */
public class ItemOneFragment extends ListFragment {
    private ArrayList<String> results = new ArrayList<String>();
    private ArrayList<String> newresults = new ArrayList<String>();
    private String tableName = FoodList.tableName;
    private SQLiteDatabase newDB;
    private ListView thislist;
    int counter = 0;
    FoodList universalF1;

    public static ItemOneFragment newInstance() {
        ItemOneFragment fragment = new ItemOneFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e(getClass().getSimpleName(), "itemOneFragment.onCreate() " + this.getContext());

    }

    private void searchForText(CharSequence s)
    {
        newresults.clear();//search bar now works HURRAY
        try
        {
            //FoodList fl = new FoodList(getActivity());
            //newDB = fl.getWritableDatabase();
            newDB = universalF1.getWritableDatabase();  //                                            \/ 3.3.?
            Cursor c = newDB.rawQuery("SELECT * FROM " + tableName + " WHERE PerishableName LIKE '%" + s + "%' ORDER BY PerishableName COLLATE NOCASE;", null);
            Log.i(getClass().getName(), "**********");
            if(c != null)
            {
                Log.i(getClass().getName(), "c is not null");
                if(c.moveToFirst())
                {
                    Log.i(getClass().getName(), "move to first success");
                    do
                    {
                        //Log.i(getClass().getName(), "do success");
                        String itemName = c.getString(c.getColumnIndex("PerishableName"));
                        //Log.e(getClass().getName(), "category: " + c.getString((c.getColumnIndex("category"))));

                        newresults.add(itemName);
                    } while (c.moveToNext());
                }
            }
            Log.i(getClass().getName(), "**********");
        }
        catch(SQLiteException se)
        {
            Log.e(getClass().getSimpleName(), "806 Could not search non-existent database");
        }
        finally
        {
            if(newDB != null)
            {
                newDB.close();
            }
        }
        Log.i(getClass().getName(), "new results " + newresults);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){


        return inflater.inflate(R.layout.fragment_item_one, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState)
    {
        openAndQueryDatabase();
        displayResultsList();

        Log.i(getClass().getSimpleName(), "able to get this: " + tableName);
        EditText searchdata = getView().findViewById(R.id.searchtxt); //getView().findViewById(R.id.searchtxt);R.id.searchtxt);
        searchdata.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Log.i(getClass().getName(), "++++++++++");
                if (!s.equals("")) {
                    Log.i(getClass().getName(), "if triggered");
                    searchForText(s);
                } else {
                    Log.i(getClass().getName(), "else triggered");
                    openAndQueryDatabase();
                    displayResultsList();
                }
                Log.i(getClass().getName(), "++++++++++");
            }

            @Override
            public void afterTextChanged(Editable s) {
                displaynewResults();
            }
        });

        //searchdata.setOnFocusChangeListener(new );

        final ListView selector = getView().findViewById(android.R.id.list);
        selector.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // TODO Auto-generated method stub
                Log.e(getClass().getSimpleName(), "onitemclick pressed" + String.valueOf(getListAdapter().getItem(position)));


                String selected = String.valueOf(getListAdapter().getItem(position));
                Log.e(getClass().getSimpleName(), "{selected}want to see what the fuck is wrong: " + selected);

                //FoodList fl = new FoodList(getActivity());
                //newDB = fl.getWritableDatabase();
                newDB = universalF1.getReadableDatabase();
                Cursor c =
                        newDB.rawQuery("SELECT * FROM " + tableName + " WHERE PerishableName='" + selected + "';", null);
                c.moveToFirst();
                Log.e(getClass().getSimpleName(), c.getString(c.getColumnIndex("PerishableName")));
                Bundle b = new Bundle();
                b.putString("name", c.getString(0));
               // b.putString("name", c.getString(c.getColumnIndex("PerishableName")));
                b.putInt("uopantrymin", c.getInt(1));
                b.putInt("uopantrymax", c.getInt(2));
                b.putInt("uofridgemin", c.getInt(3));
                b.putInt("uofridgemax", c.getInt(4));
                b.putInt("uofreezermin", c.getInt(5));
                b.putInt("uofreezermax", c.getInt(6));
                b.putInt("opantrymin", c.getInt(8));
                b.putInt("opantrymax", c.getInt(9));
                b.putInt("ofridgemin", c.getInt(10));
                b.putInt("ofridgemax", c.getInt(11));
                b.putInt("ofreezermin", c.getInt(12));
                b.putInt("ofreezermax", c.getInt(13));
               // b.putString("category", c.getString(14));
                //b.putInt("uopantrymin", c.getInt(c.getColumnIndex("uopantryMin")));
                //b.putInt("uopantrymax", c.getInt(c.getColumnIndex("uopantryMax")));
               // b.putInt("uofridgemin", c.getInt(c.getColumnIndex("uofridgeMin")));
                //b.putInt("uofridgemax", c.getInt(c.getColumnIndex("uofridgeMax")));
                //b.putInt("uofreezermin", c.getInt(c.getColumnIndex("uofreezeMin")));
                /*b.putInt("uofreezermax", c.getInt(c.getColumnIndex("uofreezeMax")));
                b.putInt("opantrymin", c.getInt(c.getColumnIndex("opantryMin")));
                b.putInt("opantrymax", c.getInt(c.getColumnIndex("opantryMax")));
                b.putInt("ofridgemin", c.getInt(c.getColumnIndex("ofridgeMin")));
                b.putInt("ofridgemax", c.getInt(c.getColumnIndex("ofridgeMax")));
                b.putInt("ofreezermin", c.getInt(c.getColumnIndex("ofreezeMin")));
                b.putInt("ofreezermax", c.getInt(c.getColumnIndex("ofreezeMax")));*/
                sendToDetails(b);
            }
        });
    }

    private void sendToDetails(Bundle b) {
        // TODO Auto-generated method stub

        Log.e(getClass().getSimpleName(), "does this crash, b: " + b);
        Intent details = new Intent(getActivity(), Itemselected.class);
        ////Log.e(getClass().getSimpleName(), "1 b: " + b);
        details.putExtras(b);
        //startActivityForResult(encounterOccurs, ENCOUNTER);
        startActivityForResult(details, 2);
    }

    private void displaynewResults()
    {
        //ArrayAdapter adapter;
        //adapter = ArrayAdapter.createFromResource(getActivity(), newresults.indexOf(), android.R.layout.simple_list_item_1);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, newresults);
        setListAdapter(adapter);
        getListView().setTextFilterEnabled(true);
    }

    private void openAndQueryDatabase()
    {

        try
        {
            counter++;
            Log.e(getClass().getSimpleName(), "this is the counter: " + counter);
            universalF1 = new FoodList(getActivity());
            Log.e(getClass().getSimpleName(), "Does it make it here???");
            newDB = universalF1.getWritableDatabase(); // <-newDB is a null object reference, need to fix this
            Log.e(getClass().getSimpleName(), "What about here???");
            Cursor c = newDB.rawQuery("SELECT * FROM " + tableName + " ORDER BY PerishableName COLLATE NOCASE;", null);
            Log.e(getClass().getSimpleName(), "this is c: " + c);

            if(c != null)
            {
                //Log.e(getClass().getSimpleName(), "inside if != null condition");
                if(c.moveToFirst())
                {
                   // Log.e(getClass().getSimpleName(), "inside if movetoFirst condition");
                    do
                    {
                        //Log.e(getClass().getSimpleName(), "inside do condition");
                        String itemName = c.getString(c.getColumnIndex("PerishableName"));
                        results.add(itemName);
                    } while (c.moveToNext());
                    //Log.e(getClass().getSimpleName(), "results: "  + results);
                }
            }
        }
        catch(SQLiteException se)
        {
            Log.e(getClass().getSimpleName(), "frag1 702 openandQuery function didn't work");
            //newDB.execSQL("DROP TABLE IF EXISTS " + tableName);
            //universalF1.createDatabase();

        }
        finally
        {
            if(newDB != null)
            {
                newDB.close();
            }
            else
            {
                Log.e(getClass().getSimpleName(), "inside if != null condition");
                //openAndQueryDatabase();
//                openAndQueryDatabase();
            }
        }
    }

    private void displayResultsList()
    {
        //Log.e(getClass().getSimpleName(), "Why the fuck isn't this running" + results);
        //ListView listOfItems = (ListView) getView().findViewById(R.id.newlist);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, results);
        //setListAdapter(adapter);
        setListAdapter(adapter);
        getListView().setTextFilterEnabled(true);
        //setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, results));
        //getListView().setTextFilterEnabled(true);
    }

    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container,
                    false);
            return rootView;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        switch(requestCode)
        {
            case 1:
            {
                break;
            }
            case 2:
            {
                break;
            }
        }
    }
}
