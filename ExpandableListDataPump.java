package mycompanyname.foodtracker;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ExpandableListDataPump {
    private ArrayList<String> strings = new ArrayList<String>();
    //private ArrayList<String> newresults = new ArrayList<String>();
    private static ArrayList<Perishable> checkingDates;
    private String tableName = RecipeList.tableName;
    private static final String TABLE_NAME = "fridge";
    static DatabaseHandler newDBH;
    private SQLiteDatabase newDB2;
    private SQLiteDatabase newDB;
    private ListView thislist;
    public String[] badItems;
    int counter = 0;
    RecipeList universalR1;


    public static HashMap<String, List<String>> getData() {

    HashMap<String, List<String>> expandableListDetail = new HashMap<String, List<String>>();

   /** Log.e(ExpandableListDataPump.class.getSimpleName(), "adding...");
    List<String> cricket = new ArrayList<String>();
    cricket.add("India");
    cricket.add("Pakistan");
    cricket.add("Australia");
    cricket.add("England");
    cricket.add("South Africa");

    Log.e(ExpandableListDataPump.class.getSimpleName(), "adding...");
    List<String> football = new ArrayList<String>();
    football.add("Brazil");
    football.add("Spain");
    football.add("Germany");
    football.add("Netherlands");
    football.add("Italy");

    Log.e(ExpandableListDataPump.class.getSimpleName(), "adding...");
    List<String> basketball = new ArrayList<String>();
    basketball.add("United States");
    basketball.add("Spain");
    basketball.add("Argentina");
    basketball.add("France");
    basketball.add("Russia");

    Log.e(ExpandableListDataPump.class.getSimpleName(), "adding...groups");
    expandableListDetail.put("CRICKET TEAMS", cricket);
    expandableListDetail.put("FOOTBALL TEAMS", football);
    expandableListDetail.put("BASKETBALL TEAMS", basketball);
    */

    List<String> recipe1 = new ArrayList<String>();
    recipe1.add("placeholder");
    //recipe1.add("ingredients");

    expandableListDetail.put("Kung Pao Chicken", recipe1);

    List<String> recipe2 = new ArrayList<String>();
    recipe2.add("placeholder");
    ////recipe2.add("website");
    //recipe2.add("ingredients");

    expandableListDetail.put("Country Gravy and Potatoes", recipe2);

    List<String> recipe3 = new ArrayList<String>();
    recipe3.add("placeholder");
    ////recipe3.add("website");
    //recipe3.add("ingredients");

    expandableListDetail.put("Chow Mein", recipe3);

    List<String> recipe4 = new ArrayList<String>();
    recipe4.add("placeholder");
    //recipe4.add("ingredients");

    expandableListDetail.put("Barbeque Chicken and Rice", recipe4);

    List<String> recipe5 = new ArrayList<String>();
    recipe5.add("placeholder");
    //recipe5.add("ingredients");

    expandableListDetail.put("Fancy Oatmeal", recipe5);


    return expandableListDetail;
    }


    private void findRecipes(String s) {
        Log.i(getClass().getSimpleName(), "this is s: " + s);
        strings.clear();
        Log.i(getClass().getSimpleName(), "strings after being cleared: " + strings);
        //universalR1 = new RecipeList();
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
                    strings.add(itemName);
                    //expandableListDetail.put(itemName, new List(c.getString(c.getColumnIndex("website")), c.getString(c.getColumnIndex("ingredients")));
                } while (c.moveToNext());
                //Log.e(getClass().getSimpleName(), "strings: "  + strings);
            }
        }
       // displayResultsList();
    }

}
