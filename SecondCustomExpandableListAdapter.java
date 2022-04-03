package mycompanyname.foodtracker;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**Need to copy the original code from CustomExpandableListAdapter so that the original functionality is restored
 * Need to build a different parent xml so it includes a delete button that will remove the recipe and it's ingredients
 * from the hashmap and the table that the hashmap grabs it's information from.
 * */

public class SecondCustomExpandableListAdapter extends BaseExpandableListAdapter {
    private Context context;
    private List<String> expandableListTitle;
    private HashMap<String, List<String>> expandableListDetail;
    ExpandableListView expandableListView;
    Button trashBtn;
    IngredientList list1;
    private String tableName = list1.tableName;
    HashMap<String, List<String>> hashList;
    private SQLiteDatabase newDB;

    public SecondCustomExpandableListAdapter(Context context, List<String> expandableListTitle,
                                             HashMap<String, List<String>> expandableListDetail) {
        this.context = context;
        this.expandableListTitle = expandableListTitle;
        this.expandableListDetail = expandableListDetail;
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
    public View getChildView(int listPosition, final int expandedListPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {
        final String expandedListText = (String) getChild(listPosition, expandedListPosition);

        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.list_xitem, null);
        }
        TextView expandedListTextView = (TextView) convertView
                .findViewById(R.id.expandedListItem);
        expandedListTextView.setText(expandedListText);
        return convertView;
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
    public View getGroupView(final int listPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {

        String listTitle = (String) getGroup(listPosition);
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.context.
                    getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.list_xgroup, null);
        }

        hashList = new HashMap<>();
        list1 = new IngredientList(convertView.getContext());


        if(expandableListTitle.size() > 0)
        {
            //do nothing
        }
        else {
            try {
                newDB = list1.getReadableDatabase();

                //+ "' WHERE type='table'"
                Cursor c = newDB.rawQuery("SELECT * FROM '" + tableName + "'", null);

                if (c.moveToFirst()) {
                    while (!c.isAfterLast()) {
                        String name = c.getString(c.getColumnIndex("RecipeName"));
                        String name2 = name.replace("*", "'");
                        String ingreds = c.getString(c.getColumnIndex("Ingredients"));
                        List<String> myList = new ArrayList<String>(Arrays.asList(ingreds.split(", ")));
                        if (hashList.containsKey(name)) {
                            //skip adding to hashList
                        } else {
                            hashList.put(name2, myList);
                            Log.i(getClass().getSimpleName(), "Table Name=> " + c.getString(0));
                        }
                        c.moveToNext();
                    }
                }
            } catch (SQLiteException se) {
                Log.i(getClass().getSimpleName(), "827 database couldn't be made");
                Log.i(getClass().getSimpleName(), se.toString());
            } finally {
                newDB.close();
            }
            expandableListTitle = new ArrayList<String>(hashList.keySet());

        }

        //Log.e(getClass().getSimpleName(), "making it here: 4");
        //expandableListAdapter = new SecondCustomExpandableListAdapter(context, expandableListTitle, hashList);
        //Log.e(getClass().getSimpleName(), "making it here: voiceinputdemo 5");
        //expandableListView.setAdapter(expandableListAdapter);
        Log.e(getClass().getSimpleName(), "making it here: " + expandableListTitle.size());

        /**The try statement above connects to the table with the recipes in the listview on the VoiceInputDemo fragment
         * I need to make it so that I can delete the entries in the table and update the table properly
         * */

        trashBtn = (Button) convertView.findViewById(R.id.trashbutton);

        trashBtn.setFocusable(false);

        final View finalConvertView = convertView;
        View.OnClickListener deleteIngreds = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                                                  \/ expandableListTitle.indexOf(listPosition) +
                Toast.makeText(v.getContext(), "ingredients removed"/*+ hashList.*/, Toast.LENGTH_LONG).show(); //3.1.5
                newDB = list1.getWritableDatabase();
                String tempRecipe = expandableListTitle.get(listPosition);
                tempRecipe = tempRecipe.replace("'", "*");   //3.2.5
                newDB.execSQL("DELETE FROM " + tableName + " WHERE RecipeName = '" + tempRecipe + "'");

                expandableListTitle.remove(listPosition);
                hashList.remove(listPosition);
                //list1 = new IngredientList(finalConvertView.getContext());
                Log.e(getClass().getSimpleName(), "---- does this trigger???" + newDB.getMaximumSize());

                        /*delete(tableName, "ROWID = (SELECT * FROM " + tableName + " WHERE RecipeName = ?)",
                        new String[] {expandableListTitle.get(listPosition)});*/
                /** Getting indexoutofboundsexception index: 0, size: 0 on this line
                 * */
                notifyDataSetChanged();
                newDB.close();
            }
        };

        trashBtn.setOnClickListener(deleteIngreds);

        TextView listTitleTextView = (TextView) convertView
                .findViewById(R.id.listTitle);
        listTitleTextView.setTypeface(null, Typeface.BOLD);
        listTitleTextView.setText(listTitle);
        return convertView;
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