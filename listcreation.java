package mycompanyname.foodtracker;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class listcreation extends FragmentActivity {
    Button cancelBtn;
    Button acceptBtn;
    ImageButton addItemBtn;
    EditText itemList;
    TextView listTitle;
    private SQLiteDatabase newDB2;
    IngredientList list1;
    private ArrayList<String> results = new ArrayList<String>();
    private ArrayList<String> newresults = new ArrayList<String>();
    private String tableName = FoodList.tableName;
    private SQLiteDatabase newDB;
    private ListView thislist;
    int counter = 0;
    FoodList universalF1;
    private AutoCompleteTextView addIngredient;

    public static String tableName2 = IngredientList.tableName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listcreation);

        cancelBtn = findViewById(R.id.cancelFromListbtn);
        acceptBtn = findViewById(R.id.createListBtn);
        itemList = findViewById(R.id.ingredientList);
        listTitle = findViewById(R.id.ingredientTitle);
        addItemBtn =  findViewById(R.id.addToListBtn);

        universalF1 = new FoodList(this);
        list1 = new IngredientList(this);
        newDB2 = list1.getWritableDatabase();

        addIngredient = findViewById(R.id.addingredienttxt);



        addIngredient.setThreshold(3);
        addIngredient.addTextChangedListener(new TextWatcher() {
             @Override
             public void beforeTextChanged(CharSequence s, int start, int count, int after) {

             }

             @Override
             public void onTextChanged(CharSequence s, int start, int before, int count) {
                 if (!s.equals("")) {
                     Log.i(getClass().getName(), "if triggered");
                     searchForText(s);
                     ArrayAdapter<String> adapter = new ArrayAdapter<String>(listcreation.this, android.R.layout.select_dialog_item, newresults);
                     addIngredient.setAdapter(adapter);
                 }
             }

             @Override
             public void afterTextChanged(Editable s) {

             }
         }
        );

        /**Need to do several things to finish this part of the app.
         * 1) Put an add button into the autocomplete text box so that once something is selected it can be added to the list being made
         *      - Make sure the item entered is added to the list
         * 2) Make the textbox where the list of items to be added is uneditable.  this way I am forcing the list to be added in one specific way
         *      and it can't be altered
         * 3) Make sure when the add button is clicked that the list is taken to the previous screen and added properly
         * 4) Possibly rename the add button so that it isn't too confusing for the user.  Don't need them pressing it before anything is added
         *      to their list.
         *
         */

        final View.OnClickListener buttonClick4 = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e(getClass().getSimpleName(), "making it here: 1067"); //works need to add new function now
                String temp = addIngredient.getText().toString();
                Log.e(getClass().getSimpleName(), "can I read the input: " + temp);
                addingIngredient(temp);
            }
        };

        addItemBtn.setOnClickListener(buttonClick4);

        //listener for add button
        final View.OnClickListener addToList = new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String tempList;
                String tempTitle;
                Log.i(getClass().getSimpleName(), "need to add to this function");
                tempTitle = checkListTitle(listTitle);
                tempList = convertList(itemList);
                Log.i(getClass().getSimpleName(), "title: " + tempTitle + "\nitemList: " + tempList);
                addIngredientsToList(tempTitle, tempList);
                addItems();
            }
        };

        acceptBtn.setOnClickListener(addToList);
        //need to fix the layout of the listcreation.xml file so that the buttons appear and there is a border around the edittext
        //fields so it looks modern


        //listener for cancel button
        View.OnClickListener discardList = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeItem();
            }
        };
        //assign listener to cancel button
        cancelBtn.setOnClickListener(discardList);
    }

    private void addingIngredient(String temp) {
        String temp2 = itemList.getText().toString();
        temp2 = temp2  + temp + "\n";
        itemList.setText(temp2);
        addIngredient.setText("");

    }

    private void searchForText(CharSequence s)
    {
        newresults.clear();//search bar now works HURRAY
        try
        {
            //FoodList fl = new FoodList(getActivity());
            //newDB = fl.getWritableDatabase();
            newDB = universalF1.getWritableDatabase();
            Cursor c = newDB.rawQuery("SELECT * FROM " + tableName + " WHERE PerishableName LIKE '" + s + "%' ORDER BY PerishableName COLLATE NOCASE;", null);
            Log.i(getClass().getName(), "**********");
            if(c != null)
            {
                Log.i(getClass().getName(), "c is not null");
                if(c.moveToFirst())
                {
                    Log.i(getClass().getName(), "move to first success");
                    do
                    {
                        Log.i(getClass().getName(), "do success");
                        String itemName = c.getString(c.getColumnIndex("PerishableName"));
                        Log.e(getClass().getName(), "category: " + c.getString((c.getColumnIndex("category"))));

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


    private String checkListTitle(TextView listTitle) {
        String title = listTitle.getEditableText().toString();
        if(title.equals(""))
        {
            title = "Shopping list";
        }

        return title;
    }

    private String convertList(EditText editableText) {
        String temp = editableText.getEditableText().toString();
        Log.i(getClass().getSimpleName(), "inside convertList function: " + temp);
        temp = temp.replace(System.lineSeparator(), ", ");
        Log.i(getClass().getSimpleName(), "after replace: " + temp);
        return temp;
    }

    //function for adding ingredients to list
    private void addIngredientsToList(String recipe, String ingreds) {
        Log.e(getClass().getSimpleName(), "##ingredients grabbed: " + ingreds);
        String r = recipe;
        String i = ingreds;
        newDB2.execSQL("INSERT INTO " + tableName2 +
                " (RecipeName, Ingredients) VALUES ('" + r + "', '" + i + "');");
        //list1.add(recipe, ingreds);
        //MainActivity.ingredients = ingreds;

    }
    //function for cancelling the screen
    protected void removeItem() {
        // TODO Auto-generated method stub
        //will include the remove sql statement for the databasehandler item, for now it just closes the view
        //and returns it to the mainactivity
        finish();
        //newDB2.notify();
        Toast.makeText(getBaseContext(), "List not saved", Toast.LENGTH_LONG).show();
    }

    protected void addItems() {
        // TODO Auto-generated method stub
        //will include the remove sql statement for the databasehandler item, for now it just closes the view
        //and returns it to the mainactivity
        finish();
        Toast.makeText(getBaseContext(), "List saved", Toast.LENGTH_LONG).show();
    }
}
