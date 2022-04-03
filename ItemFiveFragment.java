package mycompanyname.foodtracker;


import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Nicholas on 9/7/2018.
 *
 * RECIPE TAB

 */

public class ItemFiveFragment extends Fragment {

    private ArrayList<String> strings = new ArrayList<String>();
    private ArrayList<String> newresults = new ArrayList<String>();
    private static ArrayList<Perishable> checkingDates;
    private String tableName = RecipeList.tableName;
    private static final String TABLE_NAME = "fridge";
    static DatabaseHandler newDBH;
    private SQLiteDatabase newDB2;
    private SQLiteDatabase newDB;
    private ListView thislist;
    public String[] badItems;
    public String mIngredient;
    int counter = 0;
    RecipeList universalR1;

    /**New variables*/
    public View v;
    ExpandableListView expandableListView;
    ExpandableListAdapter expandableListAdapter;
    List<String> expandableListTitle;
    HashMap<String, List<String>> expandableListDetail;


    public static ItemFiveFragment newInstance(){
        ItemFiveFragment fragment = new ItemFiveFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        v = getActivity().getLayoutInflater().inflate(R.layout.fragment_item_five, null);
        //setContentView(R.layout.fragment_item_five);
        //Log.e(getClass().getSimpleName(), "making it here: 1");
        String appDataPath = getActivity().getApplicationInfo().dataDir;

        File dbFolder = new File(appDataPath + "/databases");//Make sure the /databases folder exists
        dbFolder.mkdir();//This can be called multiple times.

        File dbFilePath = new File(appDataPath + "/databases/recipes.db");

        try {
            InputStream inputStream = getActivity().getAssets().open("recipes.db");
            OutputStream outputStream = new FileOutputStream(dbFilePath);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = inputStream.read(buffer))>0)
            {
                outputStream.write(buffer, 0, length);
            }
            outputStream.flush();
            outputStream.close();
            inputStream.close();
        } catch (IOException e){
            //handle
            Log.i(getClass().getSimpleName(), "666 database couldn't be made");
        }





        expandableListDetail = new HashMap<>();
        expandableListView = (ExpandableListView) v.findViewById(R.id.expandableListView);
        //Log.e(getClass().getSimpleName(), "making it here: 2");

        displayResultsList();

        newDBH = new DatabaseHandler(getContext());
        checkingDates = new ArrayList<Perishable>();
        //Log.e(getClass().getSimpleName(), "this is the counter: " + counter);

        openAndQueryBadDates();
        badItems = new String[checkingDates.size()];
        checkForBadItems();
        //Log.i(getClass().getSimpleName(), "Here is badItems: " + Arrays.toString(badItems));

        Spinner mainIngredients = v.findViewById(R.id.spinner);
        ArrayAdapter<String> stringArrayAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, badItems);
        stringArrayAdapter.notifyDataSetChanged();
        mainIngredients.setAdapter(stringArrayAdapter);

        final String temp = mainIngredients.getSelectedItem().toString();

        //Log.i(getClass().getSimpleName(), "this is strings: " + strings);
        EditText searchRecipes = (EditText) v.findViewById(R.id.recipeSearch);
        searchRecipes.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Log.i(getClass().getName(), "++++++++++");
                if(!s.equals("")) {
                    searchForRecipes(s);
                }
                else
                {
                    Log.i(getClass().getName(), "+++++ " + temp + " +++++");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                expandableListDetail.clear();
                displayNewResults();
            }
        });

        mainIngredients.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                String changed = "";
                Object item = parent.getItemAtPosition(position);
                System.out.println(item.toString());
                changed = changeItem(item.toString());
                mIngredient = changed;
                expandableListDetail.clear(); //3.1.2
                findRecipes(changed);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        /**
         * Create a for loop that cycles through the strings array, puts the recipe name in the first value of the hashmap and then
         * puts a placeholder in the second.
         *
         * UPDATE -- strings is empty right here!!!
         */


       /* if(badItems.length != 0 ) {
            String temp1 = badItems[0];
            temp1 = changeItem(temp1);
            findRecipes(temp1);
        }
        //Log.i(getClass().getSimpleName(), "##this is badItems[0]: " + temp1 + "\nstrings.size(): " + strings.size() );
        int newSize = strings.size() - 1;
        //Log.i(getClass().getSimpleName(), "##newSize: " + newSize );
        for(int i = 0; i <= newSize; i++)
        {
            List<String> recipe4 = new ArrayList<String>();
            recipe4.add("placeholder");

            expandableListDetail.put(strings.get(i), recipe4);
        }*/


        //expandableListDetail = ExpandableListDataPump.getData();
        /** have to figure out why it's not displaying the data that it is getting from the expandablelistdatapump class
         * it grabs the data, for some reason it is not displaying it, need to look into why it won't display*/
        Log.e(getClass().getSimpleName(), "making it here: 3");
        expandableListTitle = new ArrayList<String>(expandableListDetail.keySet());
        Log.e(getClass().getSimpleName(), "making it here: 4");
        expandableListAdapter = new CustomExpandableListAdapter(getContext(), expandableListTitle, expandableListDetail);
        Log.e(getClass().getSimpleName(), "making it here: 5");
        expandableListView.setAdapter(expandableListAdapter);
        Log.e(getClass().getSimpleName(), "making it here: 6");
        //Log.i(getClass().getSimpleName(), "###this is strings: " + strings);
        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

            @Override
            public void onGroupExpand(int groupPosition) {
                Toast.makeText(getActivity(),
                        expandableListTitle.get(groupPosition) + " List Expanded.",
                        Toast.LENGTH_SHORT).show();
            }
        });

        expandableListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {

            @Override
            public void onGroupCollapse(int groupPosition) {
                Toast.makeText(getActivity(),
                        expandableListTitle.get(groupPosition) + " List Collapsed.",
                        Toast.LENGTH_SHORT).show();

            }
        });

        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                Toast.makeText(
                        getActivity(),
                        "does this fire", Toast.LENGTH_SHORT
                ).show();


                return false;
            }
        });



        return v;
    }

    private void searchForRecipes(CharSequence s) {

        newresults.clear();
        try
        {
            /**
             * NEED TO LOOK AT THIS.  THE RESULTS ARE CLEAR BUT THE KEYBOARD DOESN'T COME UP AT ALL
             * WHEN I WANT TO SEARCH FOR RECIPES.  NEED TO WORK ON THIS NEXT*/
            //FoodList fl = new FoodList(getActivity());
            //newDB = fl.getWritableDatabase();
            universalR1 = new RecipeList(getActivity());

            newDB = universalR1.getWritableDatabase();
            Cursor c = newDB.rawQuery("SELECT * FROM " + tableName + " WHERE RecipeName LIKE '" + s + "%' AND mainIngredient IS '" + mIngredient + "' ORDER BY RecipeName COLLATE NOCASE;", null);
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
                        String itemName = c.getString(c.getColumnIndex("RecipeName"));
                        //Log.e(getClass().getName(), "category: " + c.getString((c.getColumnIndex("category"))));

                        newresults.add(itemName);
                    } while (c.moveToNext());
                }
            }
            Log.i(getClass().getName(), "^^^^^^^^^");
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
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onResume() {
        checkForBadItems();
        Log.e("DEBUG", "onResume of LoginFragment");
        super.onResume();
    }
    /*@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e(getClass().getSimpleName(), "ItemFiveFragment.onCreate() " + this.getContext());
    }



    @Override
    public void onViewCreated(View view, Bundle savedInstanceState)
    {
        displayResultsList();
        newDBH = new DatabaseHandler(getContext());
        checkingDates = new ArrayList<Perishable>();
        Log.e(getClass().getSimpleName(), "this is the counter: " + counter);



        openAndQueryBadDates();
        badItems = new String[checkingDates.size()];
        checkForBadItems();
        Log.i(getClass().getSimpleName(), "Here is badItems: " + Arrays.toString(badItems));
        //String changedArray = Arrays.toString(badItems);
        //changedArray = alterString(changedArray);

        //Log.i(getClass().getSimpleName(), "New badItems list: " + changedArray);

        final ListView recipeSelect = getView().findViewById(android.R.id.list);
        recipeSelect.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selected = String.valueOf(getListAdapter().getItem(position));
                Log.e(getClass().getSimpleName(), "{selected}want to see what the fuck is wrong: " + selected);

                newDB = universalR1.getReadableDatabase();
                Cursor c =
                        newDB.rawQuery("SELECT * FROM " + tableName + " WHERE RecipeName='" + selected + "';", null);
                c.moveToFirst();
                Log.e(getClass().getSimpleName(), c.getString(c.getColumnIndex("RecipeName")));
                Bundle b = new Bundle();
                b.putString("recipe", c.getString(0));
                b.putString("ingredient", c.getString(1));
                b.putString("website", c.getString(2));

                need to comment this out and have the click expand to show the recipe and add to list buttons for the recipe selection
                //sendToDetails(b);

            }
        });*/

        /*Spinner mainIngredients = getView().findViewById(R.id.spinner);
        ArrayAdapter<String> stringArrayAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, badItems);
        stringArrayAdapter.notifyDataSetChanged();
        mainIngredients.setAdapter(stringArrayAdapter);

        mainIngredients.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                String changed = "";
                Object item = parent.getItemAtPosition(position);
                System.out.println(item.toString());
                changed = changeItem(item.toString());
                findRecipes(changed);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        /*recipeSelect.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }


        });
    }*/

    private String changeItem(String item) {
        String sentItem = item;
        if(item.toLowerCase().contains("limes"))
        {
            sentItem = "lime";
        }
        if(item.toLowerCase().contains("chicken"))
        {
            if(item.toLowerCase().contains("ground chicken"))
            {
                sentItem = "ground chicken";
            }
            else {
                sentItem = "chicken";
            }
        }
        if(item.toLowerCase().contains("steak"))
        {
            sentItem = "steak";
        }
        if(item.toLowerCase().contains("avocado"))
        {
            sentItem = "avocado";
        }
        if(item.toLowerCase().contains("sweet potato"))
        {
            sentItem = "sweet potato";
        }
        if(item.toLowerCase().contains("pork"))
        {
            if(item.toLowerCase().contains("ground pork"))
            {
                sentItem = "ground pork";
            }
            else
            {
                sentItem = "pork";
            }
        }
        if(item.toLowerCase().contains("chorizo"))
        {
            sentItem = "chorizo";
        }
        switch(sentItem) {
            case "chicken":
                return "chicken";
            case "steak":
                return "steak";
            case "avocado":
                return "avocado";
            case "sweet potato":
                return "sweet potato";
            default:
                return sentItem.toLowerCase();
        }

    }

    private void findRecipes(String s) {
        Log.i(getClass().getSimpleName(), "this is s: " + s);
        strings.clear();
        Log.i(getClass().getSimpleName(), "strings after being cleared: " + strings);
        universalR1 = new RecipeList(getActivity());
        Log.e(getClass().getSimpleName(), "Does it make it here???");
        newDB = universalR1.getWritableDatabase(); // <-newDB is a null object reference, need to fix this
        Log.e(getClass().getSimpleName(), "What about here???");
        Cursor c = newDB.rawQuery("SELECT * FROM " + tableName + " WHERE mainIngredient='" + s + "' ORDER BY RecipeName COLLATE NOCASE;", null);
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
                    String itemName = c.getString(c.getColumnIndex("RecipeName"));
                    itemName = itemName.replace("*", "'"); //3.2.5
                    strings.add(itemName);
                    //expandableListDetail.put(itemName, new List(c.getString(c.getColumnIndex("website")), c.getString(c.getColumnIndex("ingredients")));
                } while (c.moveToNext());
                //Log.e(getClass().getSimpleName(), "strings: "  + strings);
            }
        }
        displayResultsList();
    }

    private void displayNewResults()
    {
        Log.e(getClass().getSimpleName(), "displayNewResults firing fragment5: " + newresults);

        //openAndQueryBadDates();
        //badItems = new String[checkingDates.size()];
        //checkForBadItems();

        /*if(badItems.length != 0 ) {
            String temp1 = badItems[0];
            temp1 = changeItem(temp1);
            findRecipes(temp1);
        }*/
        //Log.i(getClass().getSimpleName(), "##this is badItems[0]: " + temp1 + "\nstrings.size(): " + strings.size() );
        int newSize = newresults.size() - 1;
        //Log.i(getClass().getSimpleName(), "##newSize: " + newSize );
        for(int i = 0; i <= newSize; i++)
        {
            List<String> recipe4 = new ArrayList<String>();
            recipe4.add("placeholder");
            String mod = newresults.get(i); //3.1.3
            //itemName = itemName.replace("*", "'");
            mod = mod.replace("*", "'"); //3.3.1
            mod = removeInts(mod);
            expandableListDetail.put(mod, recipe4);
        }

        //ListView listOfItems = (ListView) getView().findViewById(R.id.newlist);                             \/ remove these
//add delete button to this listview. want delete button to become active when  when all ingredients are selected

        //expandableListDetail = ExpandableListDataPump.getData();
        expandableListTitle = new ArrayList<String>(expandableListDetail.keySet());
        expandableListAdapter = new CustomExpandableListAdapter(getActivity(), expandableListTitle, expandableListDetail);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, strings);
        //setListAdapter(adapter);
        //setListAdapter(adapter);
        //getListView().setTextFilterEnabled(true);
        expandableListView.setAdapter(expandableListAdapter); //3.1.4
        /**
        Log.e(getClass().getSimpleName(), "displayresultslist firing fragmentfive: " + newresults);

        //openAndQueryBadDates();
        //badItems = new String[checkingDates.size()];
        //checkForBadItems();

        /*if(badItems.length != 0 ) {
            String temp1 = badItems[0];
            temp1 = changeItem(temp1);
            findRecipes(temp1);
        }
        //Log.i(getClass().getSimpleName(), "##this is badItems[0]: " + temp1 + "\nstrings.size(): " + strings.size() );
        int newSize = newresults.size() - 1;
        //Log.i(getClass().getSimpleName(), "##newSize: " + newSize );
        for(int i = 0; i <= newSize; i++)
        {
            List<String> recipe4 = new ArrayList<String>();
            recipe4.add("placeholder");
            String mod = newresults.get(i); //3.1.3
            mod = removeInts(mod);
            expandableListDetail.put(mod, recipe4);
        }

        //ListView listOfItems = (ListView) getView().findViewById(R.id.newlist);                             \/ remove these
//add delete button to this listview. want delete button to become active when  when all ingredients are selected
        expandableListView.deferNotifyDataSetChanged();
        //expandableListDetail = ExpandableListDataPump.getData();
        expandableListTitle = new ArrayList<String>(expandableListDetail.keySet());
        expandableListAdapter = new CustomExpandableListAdapter(getActivity(), expandableListTitle, expandableListDetail);



 // newresults is getting the correct search results, I just can't get them to display on the screen
 //this function is where it needs to happen.  Look are this function to figure out how to put the newresults into the list on screen
 //somehow this adapter below the comment needs to be used or incorporated.
 //
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, newresults);
        //setListAdapter(adapter);
        //setListAdapter(adapter);
        //getListView().setTextFilterEnabled(true);
        expandableListView.setAdapter(expandableListAdapter); //3.1.4
        //setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, strings));
     */

        //getListView().setTextFilterEnabled(true);
    }

    /*@Override
    public int getGroupCount() {
        return 0;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return 0;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return null;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return null;
    }

    @Override
    public long getGroupId(int groupPosition) {
        return 0;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        return null;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        return null;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
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
    }*/

    //@Override
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

    /***************************************************************************
     * Display search strings functions
     */
    private void displayResultsList()
    {
        Log.e(getClass().getSimpleName(), "displayresultslist firing fragmentfive: " + strings);

        //openAndQueryBadDates();
        //badItems = new String[checkingDates.size()];
        //checkForBadItems();

        /*if(badItems.length != 0 ) {
            String temp1 = badItems[0];
            temp1 = changeItem(temp1);
            findRecipes(temp1);
        }*/
        //Log.i(getClass().getSimpleName(), "##this is badItems[0]: " + temp1 + "\nstrings.size(): " + strings.size() );
        int newSize = strings.size() - 1;
        //Log.i(getClass().getSimpleName(), "##newSize: " + newSize );
        for(int i = 0; i <= newSize; i++)
        {
            List<String> recipe4 = new ArrayList<String>();
            recipe4.add("placeholder");
            String mod = strings.get(i); //3.1.3
            mod = removeInts(mod);
            expandableListDetail.put(mod, recipe4);
        }

        //ListView listOfItems = (ListView) getView().findViewById(R.id.newlist);                             \/ remove these
//add delete button to this listview. want delete button to become active when  when all ingredients are selected

        //expandableListDetail = ExpandableListDataPump.getData();
        expandableListTitle = new ArrayList<String>(expandableListDetail.keySet());
        expandableListAdapter = new CustomExpandableListAdapter(getActivity(), expandableListTitle, expandableListDetail);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, strings);
        //setListAdapter(adapter);
        //setListAdapter(adapter);
        //getListView().setTextFilterEnabled(true);
        expandableListView.setAdapter(expandableListAdapter); //3.1.4
        //setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, strings));
        //getListView().setTextFilterEnabled(true);
    }

    /**
     * send to details function*

    private void sendToDetails(Bundle b) {
        // TODO Auto-generated method stub

        Log.e(getClass().getSimpleName(), "does this crash, b: " + b);
        Intent website = new Intent(getActivity(), RecipeView.class);
        Log.e(getClass().getSimpleName(), "1 b: " + b);
        website.putExtras(b);
        //startActivityForResult(encounterOccurs, ENCOUNTER);
        startActivityForResult(website, 2);
    } */

    /**
     * query bad dates function
     **/
    private void openAndQueryBadDates()
    {
        try
        {
            //DatabaseHandler dbh = new DatabaseHandler(actContext);
            newDB2 = newDBH.getWritableDatabase();
            Cursor c = newDB2.rawQuery("SELECT * FROM " + TABLE_NAME + ";", null);

            if(c != null)
            {
                if(c.moveToFirst())
                {
                    do
                    {
                        Perishable temp = new Perishable();
                        String itemName = c.getString(c.getColumnIndex("name"));
                        //String badon = c.getString(c.getColumnIndex("badondatemax"));
                        temp.setpName(itemName);
                        //temp.setBadOnMax(badon);
                        //names.add(itemName);
                        checkingDates.add(temp);
                        //badItems[j] += itemName;
                    } while (c.moveToNext());
                }
            }
        }
        catch(SQLiteException se)
        {
            Log.e(getClass().getSimpleName(), "213 Could not create or open the database (ItemFiveFragment)");
        }
        finally
        {
            if(newDB != null)
            {
                newDB.close();
            }
        }
    }

    private void checkForBadItems() {
        //get the current date in mm/dd/yyyy format
        //check it with each item's badonmax date
        //set isBad to true if so, put item name in new list to be used in notification
        //return the list

        /**Now that we are getting the list of items we have, all I need to do is take these values and put them in a
         * new array so they can be dynamically fed into a spinner that will then populate and search for the
         * item in the recipes table I have created.
        *
        * */
        //SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yy");
       //// Calendar thisDay = Calendar.getInstance();
        //Date today = thisDay.getTime();
        //Log.e(getClass().getSimpleName(), "today's date: " + today.toString());
        //Date nextDate = new Date();
        int j = 0;//size counter for badItems list
        //Log.i(getClass().getSimpleName(), "isBad: " + isBad);

        for(int i = 0; i < checkingDates.size(); i++)
        {

           // Log.i(getClass().getSimpleName(), "inside ifcheckdate isBad: " + isBad);
            String temp = checkingDates.get(i).getpName();
            temp = removeInts(temp);
            badItems[j] = temp;
            j++;

            Log.v(getClass().getSimpleName(), "i= " + i + ", " + checkingDates.get(i).getBadOnMax());
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
        mod = mod.replace("''", "'"); //3.1.1
        return mod;
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

}
