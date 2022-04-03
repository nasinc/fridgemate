package mycompanyname.foodtracker;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

//import android.support.v4.app.Fragment;


public class CustomExpandableListAdapter extends BaseExpandableListAdapter {
    private Context context;
    private String tableName = RecipeList.tableName;
    private SQLiteDatabase newDB;
    private SQLiteDatabase newDB2;
    RecipeList universalR1;
    private List<String> expandableListTitle;
    private HashMap<String, List<String>> expandableListDetail;
    ImageButton websitebtn;
    ImageButton ingredsbtn;
    IngredientList list1;
    public static String tableName2 = IngredientList.tableName;

    public CustomExpandableListAdapter(Context context, List<String> expandableListTitle,
                                       HashMap<String, List<String>> expandableListDetail) {
        this.context = context;
        this.expandableListTitle = expandableListTitle;
        this.expandableListDetail = expandableListDetail;
        list1 = new IngredientList(this.context);
    }

    @Override
    public Object getChild(int listPosition, int expandedListPosition) {
        return this.expandableListDetail.get(this.expandableListTitle.get(listPosition))
                .get(expandedListPosition);
    }

    @Override
    public long getChildId(int listPosition, int expandedListPosition) {
        return expandedListPosition;
    }

    @Override
    public View getChildView(final int listPosition, final int expandedListPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {
        final String expandedListText = (String) getChild(listPosition, expandedListPosition);
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.revisedlist_item, null);
        }

        universalR1 = new RecipeList(context);
        //Log.e(getClass().getSimpleName(), "Does it make it here???");
        newDB = universalR1.getWritableDatabase();

        list1 = new IngredientList(context);
        newDB2 = list1.getWritableDatabase();
        //TextView expandedListTextView = (TextView) convertView
          //      .findViewById(R.id.expandedListItem);
        //expandedListTextView.setText(expandedListText);

        websitebtn = (ImageButton) convertView.findViewById(R.id.thatwebbtn);
        websitebtn.setOnClickListener(new ImageButton.OnClickListener() {
            @Override
            public void onClick(View v) {
                String wbpage = grabWebpage(getGroup(listPosition));
                sendToSite(wbpage);
                }
        });

        ingredsbtn = (ImageButton) convertView.findViewById(R.id.addbtn);
        ingredsbtn.setOnClickListener(new ImageButton.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ingreds = grabIngredients(getGroup(listPosition));
                String recipe = getGroup(listPosition).toString();
                String newRecipe = recipe.replace("*", "'");
                Log.i(getClass().getSimpleName(), "##ingredients grabbed: " + ingreds);
                Log.i(getClass().getSimpleName(), "##ingredients grabbed: " + recipe);
                Log.i(getClass().getSimpleName(), "##ingredients grabbed: " + newRecipe);
                addIngredientsToList(newRecipe, ingreds);
                /**
                 * NEED TO FIGURE OUT WHERE THE ASTERISK IS GOING BACK AND SHOWING UP ON THE LIST FRAGMENT(VOICEINPUTDEMO FRAGMENT)
                 * Toast.makeText(
                        v.getContext(),
                        "3 -> 4: add ingredients to list", Toast.LENGTH_SHORT
                ).show();*/
            }
        });
        return convertView;
    }

    private void addIngredientsToList(String recipe, String ingreds) {
        Log.e(getClass().getSimpleName(), "##ingredients grabbed: " + ingreds);
        Log.e(getClass().getSimpleName(), "old recipe: " + recipe);
        String newRecipe = recipe.replace("'", "*");
        Log.e(getClass().getSimpleName(), "new recipe: " + newRecipe);
        newDB2.execSQL("INSERT INTO " + tableName2 +
                "(RecipeName, Ingredients) VALUES ('" + newRecipe + "', '" + ingreds + "');");
        //list1.add(recipe, ingreds);
        //MainActivity.ingredients = ingreds;

    }

    private void sendToSite(String wbpage) {
        new Intent();
        Intent website = new Intent(context, RecipeView.class);//Intent(context, RecipeView.class);
        //Log.e(getClass().getSimpleName(), "1 b: " + b);
        website.putExtra("website", wbpage);
        //startActivityForResult(encounterOccurs, ENCOUNTER);

        ((Activity)context).startActivityForResult(website, 2);
    }

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

    @Override
    public int getChildrenCount(int listPosition) {
        return this.expandableListDetail.get(this.expandableListTitle.get(listPosition))
                .size();
    }

    @Override
    public Object getGroup(int listPosition) {
        return this.expandableListTitle.get(listPosition);
    }

    @Override
    public int getGroupCount() {
        return this.expandableListTitle.size();
    }

    @Override
    public long getGroupId(int listPosition) {
        return listPosition;
    }

    @Override
    public View getGroupView(int listPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        String listTitle = (String) getGroup(listPosition);
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.context.
                    getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.list_group, null);
        }
        TextView listTitleTextView = (TextView) convertView
                .findViewById(R.id.listTitle);
        listTitleTextView.setTypeface(null, Typeface.BOLD);
        listTitleTextView.setText(listTitle);
        return convertView;
    }

    public String grabWebpage(Object recipe)
    {
        Log.e(getClass().getSimpleName(), "recipe grabbed: " + recipe);
        //Toast.makeText(context, "go to recipe website: " + recipe, Toast.LENGTH_SHORT).show();
        ArrayList<String> strings = new ArrayList<String>();
        Cursor c = newDB.rawQuery("SELECT * FROM " + tableName + " WHERE RecipeName='" + recipe + "' ORDER BY RecipeName COLLATE NOCASE;", null);
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
                    String itemName = c.getString(c.getColumnIndex("website"));
                    strings.add(itemName);
                } while (c.moveToNext());
                //Log.e(getClass().getSimpleName(), "strings: "  + strings);
            }
        }
        //Toast.makeText(context, "go to recipe website: " + strings, Toast.LENGTH_SHORT).show();
        return strings.get(0);
    }

    public String grabIngredients(Object recipe)
    {
        Log.e(getClass().getSimpleName(), "recipe grabbed: " + recipe);
        String newRecipe = recipe.toString().replace("'", "*");
        Log.e(getClass().getSimpleName(), "recipe grabbed: " + newRecipe);
        Toast.makeText(context, "Ingredients for " + recipe + " added to List tab", Toast.LENGTH_SHORT).show();
        ArrayList<String> strings = new ArrayList<String>();
        Cursor c = newDB.rawQuery("SELECT * FROM " + tableName + " WHERE RecipeName='" + newRecipe+ "' ORDER BY RecipeName COLLATE NOCASE;", null);
        //Log.e(getClass().getSimpleName(), "this is c: " + c.getColumnName(1) + ", " + c.getColumnName(2) +
          //      ", " + c.getColumnName(3) + ", " + c.getColumnName(0));
        if(c != null)
        {
            Log.i(getClass().getSimpleName(), "inside if != null condition");
            if(c.moveToFirst())
            {
                 Log.e(getClass().getSimpleName(), "inside if movetoFirst condition");
                do
                {
                    Log.i(getClass().getSimpleName(), "inside do condition");
                    String itemName = c.getString(c.getColumnIndex("ingredients"));
                    Log.e(getClass().getSimpleName(), "itemName: " + itemName);
                    strings.add(itemName);
                } while (c.moveToNext());
                //Log.i(getClass().getSimpleName(), "strings: "  + strings.get(4));
            }
        }
        return strings.get(0);
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int listPosition, int expandedListPosition) {
        return true;
    }
}