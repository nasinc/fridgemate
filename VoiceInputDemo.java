package mycompanyname.foodtracker;

import android.Manifest;
import android.app.Activity;
import android.app.Fragment;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ListFragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Nicholas on 9/19/2018.
 *
 *  LIST TAB
 */

public class VoiceInputDemo extends ListFragment{

    private SQLiteDatabase newDB;
    int counter = 0;
    FoodList universalF1;
    IngredientList list1;
    private String tableName = list1.tableName;
    static MultiAdapter dataset;
    HashMap<String, List<String>> hashList;
    public View view1;
    public View view2;
    public ListView newList;
    Button deleteBtn;
    public AutoCompleteTextView word;
    public Button bt_voiceinput;
    Button addNewList;
    List<String> expandableListTitle;
    ExpandableListView expandableListView;
    SecondCustomExpandableListAdapter expandableListAdapter;
    public String carryOver; //string to be used for ingredients being added to list from itemFiveFragment when add ingredients button is pressed
    private static final int REQUEST_CODE = 1234;
    Bundle savedOne;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        /*if(android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
        {
            getActivity().getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);

            //set the transition
            Transition ts = new Explode();
            ts.setDuration(5000);
            getActivity().getWindow().setEnterTransition(ts);
            getActivity().getWindow().setExitTransition(ts);
        }*/

        super.onCreate(savedInstanceState);
        //view = LayoutInflater.from(this).inflate(R.layout.voiceinput,container);
        //view = view.findViewById(R.layout.voiceinput);

        //setReference();

        //setToolbarElevation(7);

        //setToolbarSubTittle(this.getClass().getSimpleName());

        savedOne = savedInstanceState;
    }

    public static VoiceInputDemo newInstance()
    {
        VoiceInputDemo fragment = new VoiceInputDemo();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        //final ListView selector = getView().findViewById(android.R.id.list);
        view1 = inflater.inflate(R.layout.voiceinput, container,false);
        //view2 = inflater.inflate(R.layout.list_xgroup, container, false);
        newList = (ListView) view1.findViewById(android.R.id.list);
        //dataset = new MultiAdapter(view.getContext(), R.layout.listrow, null);

        addNewList = view1.findViewById(R.id.addListbtn);

        return view1;
    }
    //@Override
    /*public void setReference() {
        setContentView(R.layout.voiceinput);
        //view = LayoutInflater.from(this).inflate(R.layout.voiceinput,container);
        //view = findViewById(R.layout.voiceinput);
        bt_voiceinput = (ImageButton) view.findViewById(R.id.ib_speak);
        word = (EditText) view.findViewById(R.id.et_word);
    }*/

    @Override
    public void onViewCreated(final View view, Bundle savedInstanceState)
    {

        //uncomment following two lines when I figure out how to do the voice input for list items
        //bt_voiceinput = (Button) view.findViewById(R.id.ib_speak);
        //word = (AutoCompleteTextView) view.findViewById(R.id.et_word);
        //AutoCompleteTextView newtextview = view.findViewById(R.id.et_word);
        hashList = new HashMap<>();
        list1 = new IngredientList(getActivity());
        newDB = list1.getReadableDatabase();

        /**
         * add button from voiceinput.xml
         * create onclicklistener for button that grabs the line of text from word object
         * take string in word object and add it to the list as an uncategorized recipe for the title
         * **/

        /**Saving ingredients to a new table called IngredientsList.  Need to call the table in this fragment successfully and then
         * display the ingredients under the recipe name
         *
         * **To do need to be able to delete the ingredients from the list by simply deleting the recipe title on the list
         * I can do this but first I need to be able to access the table in this fragment and display the table
         *
         * 1) access IngredientList from this fragment
         * 2) display values in IngredientList with recipe name first then all the ingredients as children
         * 3) be able to delete recipe ingredients by deleting the recipe name from the table
         * */
        try {

            //+ "' WHERE type='table'"
            Cursor c = newDB.rawQuery("SELECT * FROM '" + tableName + "'", null);

            if (c.moveToFirst()) {
                while (!c.isAfterLast()) {
                    String name = c.getString(c.getColumnIndex("RecipeName"));
                    String ingreds = c.getString(c.getColumnIndex("Ingredients"));
                    List<String> myList = new ArrayList<String>(Arrays.asList(ingreds.split(", ")));
                    if(hashList.containsKey(name))
                    {
                        //skip adding to hashList
                    }
                    else
                    {
                        name = name.replace("*", "'"); //3.2.5
                        hashList.put(name, myList);
                        Log.i(getClass().getSimpleName(), "Table Name=> " + c.getString(0));
                    }
                    c.moveToNext();
                }
            }
        }
        catch (SQLiteException se)
        {
            Log.i(getClass().getSimpleName(), "827 database couldn't be made");
            Log.i(getClass().getSimpleName(), se.toString());
        }
        finally {
            newDB.close();
        }
        expandableListView = (ExpandableListView) view1.findViewById(R.id.expandableListView);
        expandableListTitle = new ArrayList<String>(hashList.keySet());
        Log.e(getClass().getSimpleName(), "making it here: 4");
        expandableListAdapter = new SecondCustomExpandableListAdapter(getContext(), expandableListTitle, hashList);
        Log.e(getClass().getSimpleName(), "making it here: voiceinputdemo 5");
        expandableListView.setAdapter(expandableListAdapter);
        Log.e(getClass().getSimpleName(), "making it here: 6");


        View.OnClickListener addListListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e(getClass().getSimpleName(), "making it here: 666");
                openCustomList();
            }
        };

        addNewList.setOnClickListener(addListListener);

        /*if(!MainActivity.ingredients.isEmpty())
        {
            Toast.makeText(view1.getContext(), "ingredients being carried over!!!", Toast.LENGTH_SHORT).show();
            carryOver = MainActivity.ingredients;
            Log.e(getClass().getSimpleName(), "carry over ingredients: " + carryOver);
            MainActivity.ingredients = "";
            addedIngreds = carryOver.split(", ");
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, addedIngreds);
            setListAdapter(adapter);
            getListView().setTextFilterEnabled(true);
        }*/

        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

            @Override
            public void onGroupExpand(int groupPosition) {
                Toast.makeText(getActivity(),
                        expandableListTitle.get(groupPosition) + " List Expanded.",
                        Toast.LENGTH_SHORT).show();
            }
        });


        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                Toast.makeText(
                        getActivity(),
                        expandableListTitle.get(groupPosition)
                                + " -> "
                        , Toast.LENGTH_SHORT
                ).show();
                return false;
            }
        });


        //openAndQueryDatabase();
        /**String[] temp = newresults.toArray(new String[newresults.size()]);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(view.getContext(), android.R.layout.simple_dropdown_item_1line, newlist);
        word.setThreshold(2);
        word.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        word.setAdapter(adapter);
        adapter.setNotifyOnChange(true);
        adapter.notifyDataSetChanged();
*/


/*        bt_voiceinput.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                Log.v(getClass().getSimpleName(), "voiceinput onclicklistener firing!!!");
                startVoiceRecognitionActivity();

                /*Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
                try {

                    startActivityForResult(intent, 10);
                } catch (ActivityNotFoundException a) {

                    Toast.makeText(getContext(), "Opps! Your device doesn’t support Speech to Text",Toast.LENGTH_SHORT).show();
                }
                if(intent.resolveActivity(getActivity().getPackageManager()) != null) {
                    startActivityForResult(intent, 10);
                }
                else
                {
                    Toast.makeText(getActivity(), "Your device doesn't support speech input", Toast.LENGTH_SHORT).show();
                }
            }
        });
*/
        //startVoiceRecognitionActivity();
        //word.setOnClickListener()
        /**
         code where results from database are put in a string array and then used as possible dropdown selelctions for the
         autocompletetextview

         !!!!NEED TO FIGURE OUT WHY THESE LINES OF CODE AREN'T WORKING THE WAY THEY ARE SUPPOSED TO
         **/



        ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Disable button if no recognition service is present
        PackageManager pm = getActivity().getPackageManager();
        /*List<ResolveInfo> activities = pm.queryIntentActivities(
                new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH), 0);
        if (activities.size() == 0) {
            bt_voiceinput.setEnabled(false);
        }

        bt_voiceinput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startVoiceRecognitionActivity();
            }
        });
        word.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
                Object item = s;
                System.out.println(item.toString());
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                bt_voiceinput.setEnabled(false);
            }
        });
*/
    }

    private void openCustomList() {
        Intent createList = new Intent(getActivity(), listcreation.class);
        ////Log.e(getClass().getSimpleName(), "1 b: " + b);
        //details.putExtras(b);
        //startActivityForResult(encounterOccurs, ENCOUNTER);
        startActivityForResult(createList, 2);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        return super.onOptionsItemSelected(item);
    }

    /**
     * Fire an intent to start the voice recognition activity.
     */


    private void startVoiceRecognitionActivity() {
        int requestCode = 200;
        String[] permissions = {Manifest.permission.RECORD_AUDIO};

        if (ContextCompat.checkSelfPermission(getActivity(),
                Manifest.permission.RECORD_AUDIO)
                != PackageManager.PERMISSION_GRANTED) {

            // Permission is not granted
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
                    Manifest.permission.RECORD_AUDIO)) {
                // Show an explanation to the user *asynchronously* -- don't block
                // this t0hread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
            } else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(getActivity(), permissions, requestCode);
                /**ActivityCompat.requestPermissions(getActivity(),
                 arrayOf(Manifest.permission.READ_CONTACTS),
                 MY_PERMISSIONS_REQUEST_READ_CONTACTS)
                 */
                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        } else {
            // Permission has already been granted
        }

        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Say your list");

        try {

            startActivityForResult(intent, REQUEST_CODE);
        } catch (ActivityNotFoundException a) {
                Toast.makeText(getContext(), "Opps! Your device doesn’t support Speech to Text",Toast.LENGTH_SHORT).show();
        }


    }

    @Override
    public void onResume() {
        super.onResume();
        //dataset.notifyDataSetChanged();
        expandableListAdapter.notifyDataSetChanged();
        expandableListView.deferNotifyDataSetChanged();
        onViewCreated(view1, savedOne);
    }

    @Override
    public void onPause() {
        super.onPause();
        expandableListAdapter.notifyDataSetChanged();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        //If Voice recognition is successful then it returns RESULT_OK
        super.onActivityResult(requestCode, resultCode, data);


        switch (requestCode)
        {
            case REQUEST_CODE:
                if (resultCode == getActivity().RESULT_OK && data != null) {
                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    List<String> tresult = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    //Toast.makeText(getContext(), result.get(0), Toast.LENGTH_SHORT).show();
                    Log.v(getClass().getSimpleName(), "the speech: " + result.get(0));
                    String temporary = result.toString();

                    dataset = new MultiAdapter(view1.getContext(), R.layout.listrow, result);
                    newList.setAdapter(dataset);
                    dataset.notifyDataSetChanged();

                    word.setText(result.get(0));
                    //result.set(0,"");
                    //txvResult.setText(result.get(0));
                }

                break;
            default:
                break;
        }
        //super.onActivityResult(requestCode, resultCode, data);
        /*if (requestCode == REQUEST_CODE && resultCode == 1) {
            if (resultCode == 1) {
                ArrayList<String> textMatchList = data
                        .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);

                if (!textMatchList.isEmpty()) {
                    String Query = textMatchList.get(0);
                    word.setText(Query);
                }
                //Result code for various error.
            } else if (resultCode == RecognizerIntent.RESULT_NETWORK_ERROR) {
                Toast.makeText(getActivity(), "Network Error", Toast.LENGTH_LONG).show();
                //Common.showToast(this, "Network Error");
            } else if (resultCode == RecognizerIntent.RESULT_NO_MATCH) {
                Toast.makeText(getActivity(), "No Match", Toast.LENGTH_LONG).show();
                //Common.showToast(this, "No Match");
            } else if (resultCode == RecognizerIntent.RESULT_SERVER_ERROR) {
                Toast.makeText(getActivity(), "Server Error", Toast.LENGTH_LONG).show();
                //Common.showToast(this, "Server Error");
            }

        }*/
    }

    /*public void sayList(View view) {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());

        if(intent.resolveActivity(getActivity().getPackageManager()) != null) {
            startActivityForResult(intent, 10);
        }
        else
        {
            Toast.makeText(getActivity(), "Your device doesn't support speech input", Toast.LENGTH_SHORT).show();
        }
    }*/

    /*@Override
    protected void OnActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode)
        {
            case 10:
                if (resultCode == getActivity().RESULT_OK && data != null) {
                ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                //txvResult.setText(result.get(0));
            }

                break;
        }
    }

    private void openAndQueryDatabase()
    {

        try
        {
            counter++;
            Log.v(getClass().getSimpleName(), "this is the counter: " + counter);
            universalF1 = new FoodList(getActivity());
            Log.v(getClass().getSimpleName(), "Does it make it here???");
            newDB = universalF1.getWritableDatabase(); // <-newDB is a null object reference, need to fix this
            Log.v(getClass().getSimpleName(), "What about here???");
            Cursor c = newDB.rawQuery("SELECT * FROM " + tableName + " ORDER BY PerishableName COLLATE NOCASE;", null);
            Log.v(getClass().getSimpleName(), "this is c: " + c);

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
                        newresults.add(itemName);
                    } while (c.moveToNext());
                    Log.e(getClass().getSimpleName(), "results: "  + newresults);
                }
            }
        }
        catch(SQLiteException se)
        {
            Log.e(getClass().getSimpleName(), "616 openandQuery function didn't work");
            newDB.execSQL("DROP TABLE IF EXISTS " + tableName);
            universalF1.createDatabase();

        }
        finally
        {
            if(newDB != null)
            {
                newDB.close();
            }
            else
            {
                openAndQueryDatabase();
            }
        }
    }*/

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


    /****
     * MULTIADAPTER CLASS FOR VOICEINPUTDEMO
     *
     */
    public static class MultiAdapter extends ArrayAdapter<String>
    {
        private final Context context;
        private ArrayList<String> data;
        private final int resource;

        public MultiAdapter(Context context, int resource, ArrayList<String> objects) {
            super(context, resource, objects);
            this.context = context;
            this.data = objects;
            this.resource = resource;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View row = convertView;
            ViewHolder holder = null;

            String temp = data.get(0);
            Toast.makeText(getContext(), "temp string in getView: " + temp, Toast.LENGTH_LONG).show();
            String[] temp2 = temp.split(" ");


            if(row == null)
            {
                LayoutInflater inflater = ((Activity) context).getLayoutInflater();
                row = inflater.inflate(resource, parent, false);

                holder = new VoiceInputDemo.ViewHolder();
                holder.name = (TextView) row.findViewById(R.id.itemname);
                row.setTag(holder);
            }
            else {
                holder = (ViewHolder) row.getTag();
            }

            final String dmbg = temp2[position];

            holder.name.setText(dmbg);

            return row;
        }

    }

    /***
     * VIEWHOLDER CLASS FOR MULTIADAPTER
     */

    static class ViewHolder
    {
        TextView name;

    }
}