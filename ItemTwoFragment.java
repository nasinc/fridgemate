package mycompanyname.foodtracker;

//import android.app.Fragment;
import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Nicholas on 1/22/2018.
 */

public class ItemTwoFragment extends ListFragment {

    private static final String TABLE_NAME = "fridge";
    static Context actContext;
    static DatabaseHandler newDBH;
    static MultiAdapter dataset;
    static SimpleListFragment list;
    private static ArrayList<Perishable> results;
    private SQLiteDatabase newDB;

    public static ItemTwoFragment newInstance() {
        ItemTwoFragment fragment = new ItemTwoFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        actContext = this.getContext();
        Log.e(getClass().getSimpleName(), "ItemTwoFragment.onCreate() " + this.getContext());


        results = new ArrayList<Perishable>();
        newDBH = new DatabaseHandler(this.getContext());
        list = new SimpleListFragment();

        /*android.app.FragmentManager fm = getFragmentManager();
        if(fm.findFragmentById(android.R.id.content) == null)
        {

            fm.beginTransaction().add(android.R.id.content, list).commit();
        }*/

        //android.app.ActionBar myaction = getActionBar();

        //myaction.setDisplayShowTitleEnabled(false);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        Log.v(getClass().getName(), "First onCreateView, does it make it here?");

        list.openAndQueryDatabase();
        //ArrayAdapter<String> adapter = new ArrayAdapter<String>(inflater.getContext(), R.layout.row, R.id.textname, results);
        //ArrayAdapter<String> bad = new ArrayAdapter<String>(inflater.getContext(), R.layout.row, R.id.expiredDate, baddates);
        dataset = new MultiAdapter(inflater.getContext(), R.layout.row, results);
        setListAdapter(dataset);
        dataset.notifyDataSetChanged();

        return inflater.inflate(R.layout.fragment_item_two, container, false);

    }


    public static class SimpleListFragment extends ListFragment
    {
        String[] testproducts = new String[] {"carrots", "peas", "corn", "beans", "white bread"};
        private SQLiteDatabase newDB;


        //private ArrayList<String> baddates = new ArrayList<String>();

        @Override
        public void onListItemClick(ListView l, View v, int position, long id)
        {
            //gPos = position;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
        {
            //View view = inflater.inflate(R.layout.row, container, false);
            //dataset.clear();
            Log.e(getClass().getName(), "I2F, does it make it here?");

            //openAndQueryDatabase();
            //ArrayAdapter<String> adapter = new ArrayAdapter<String>(inflater.getContext(), R.layout.row, R.id.textname, results);
            //ArrayAdapter<String> bad = new ArrayAdapter<String>(inflater.getContext(), R.layout.row, R.id.expiredDate, baddates);
            //dataset = new MultiAdapter(inflater.getContext(), R.layout.row, results);
            //setListAdapter(dataset);
            //dataset.notifyDataSetChanged();

            return super.onCreateView(inflater, container, savedInstanceState);
        }

        public void openAndQueryDatabase()
        {
            try
            {
                //DatabaseHandler dbh = new DatabaseHandler(actContext);
                newDB = newDBH.getWritableDatabase();
                Cursor c = newDB.rawQuery("SELECT * FROM " + TABLE_NAME + ";", null);
                Log.e(getClass().getName(), "new results " + c);

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
                            //results.add(itemName);
                            results.add(temp);
                        } while (c.moveToNext());
                    }
                }
            }
            catch(SQLiteException se)
            {
                Log.e(getClass().getSimpleName(), "667 Could not create or open the database");
            }
            finally
            {
                if(newDB != null)
                {
                    newDB.close();
                }
            }
        }


    }

    public static class MultiAdapter extends ArrayAdapter<Perishable>
    {
        private final Context context;
        private ArrayList<Perishable> data;
        private final int resource;
        //DatabaseHandler dbh = new DatabaseHandler(actContext);

        public MultiAdapter(Context context, int resource, ArrayList<Perishable> objects) {
            super(context, resource, objects);
            // TODO Auto-generated constructor stub
            this.context = context;
            this.data = objects;
            this.resource = resource;
        }

		/*public void refresh(ArrayList<Perishable> temp)
		{
			this.data = temp;
			notifyDataSetChanged();
		}
		*/

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View row = convertView;
            ViewHolder holder = null;
            //ViewGroup pParent;
            final int passed = position;

                if (row == null) {
                    LayoutInflater inflater = ((Activity) context).getLayoutInflater();
                    row = inflater.inflate(resource, parent, false);

                    holder = new ViewHolder();
                    holder.name = (TextView) row.findViewById(R.id.textname);
                    holder.date = (TextView) row.findViewById(R.id.expiredDate);
                    holder.location = (TextView) row.findViewById(R.id.locationtxt);
                    holder.chillbtn = (Button) row.findViewById(R.id.chillbutton);
                    holder.freezebtn = (Button) row.findViewById(R.id.freezebutton);
                    holder.deletebtn = (Button) row.findViewById(R.id.itembutton2);
                    row.setTag(holder);
                } else {
                holder = (ViewHolder) row.getTag();
            }


                final Perishable temp = data.get(position);

                Perishable temp2 = newDBH.getPerishable(temp.getpName());

                View.OnClickListener chillClick = new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        // TODO Auto-generated method stub
                        Perishable t1;
                        t1 = newDBH.getPerishable(temp.getpName());

                        t1.setBadOnMin(t1.getMinFridge());
                        t1.setBadOnMax(t1.getMaxFridge());
                        t1.setPosition(2);

                        newDBH.updatePerishable(t1);
                        notifyDataSetChanged();
                        dataset.clear();
                        list = new SimpleListFragment();
                        list.openAndQueryDatabase();
                        dataset = new MultiAdapter(actContext, R.layout.row, results);
                        list.setListAdapter(dataset);
                    }
                };
                holder.chillbtn.setOnClickListener(chillClick);

                //Log.v(getClass().getSimpleName(), "This is temp2 perishable.getPosition: " + temp2.getPosition());
                View.OnClickListener b1Click = new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        // TODO Auto-generated method stub
                        //Log.e(getClass().getSimpleName(), "position= " + passed + ", button clicked");
                        Perishable t1;
                        //String selected = list.getListView().getItemAtPosition(passed).toString();
                        t1 = newDBH.getPerishable(temp.getpName());
                        Log.e(getClass().getSimpleName(), "this is the position int " + t1.getPosition());
                        Log.e(getClass().getSimpleName(), "selected = " + t1.getpName() + ", bad date = " + t1.getBadOnMax() + ", " + t1.getMaxFreeze() +
                                ", fgmax " + t1.getMaxFridge() + ", pmax " + t1.getMaxPantry());
                        if (t1.getBadOnMax() != t1.getMaxFreeze()) {
                            t1.setBadOnMax(t1.getMaxFreeze());
                            t1.setBadOnMin(t1.getMinFreeze());
                            t1.setPosition(3);
                        }
                        newDBH.updatePerishable(t1);
                        notifyDataSetChanged();
                        dataset.clear();
                        list = new SimpleListFragment();
                        list.openAndQueryDatabase();
                        dataset = new MultiAdapter(actContext, R.layout.row, results);
                        list.setListAdapter(dataset);
                    }
                };

                Log.e(getClass().getName(), "item name: " + temp2.getpName() + ", item position: " + temp2.getPosition());
                Log.v(getClass().getName(), "item name:  " + temp2.getpName() + ", index value: " + temp2.getIndex());
                if (temp2.getPosition() == 2 || temp2.getPosition() == 3) {
                    holder.chillbtn.setEnabled(false);
                    //holder.chillbtn.setBackground(Drawable.createFromPath(String.valueOf(R.layout.disabledbgc)));
                    //holder.chillbtn.setBackgroundColor(Color.GRAY);
                }
                if (temp2.getMinFridge().isEmpty() && temp2.getMaxFridge().isEmpty()) {
                    holder.chillbtn.setEnabled(false);
                    // holder.chillbtn.setBackground(Drawable.createFromPath(String.valueOf(R.layout.disabledbgc)));
                    //holder.chillbtn.setBackgroundColor(Color.GRAY);
                }

                if (temp2.getPosition() == 3 || (temp2.getMinFreeze().isEmpty() && temp2.getMaxFreeze().isEmpty())) {
                    holder.freezebtn.setEnabled(false);
                    //holder.freezebtn.setBackground(Drawable.createFromPath(String.valueOf(R.layout.disabledbgc)));
                    // holder.freezebtn.setBackgroundColor(Color.GRAY);
                }

                holder.freezebtn.setOnClickListener(b1Click);
                if (temp.getMaxFreeze() == temp.getBadOnMax()) {
                    holder.freezebtn.setEnabled(false);
                }
                //Button b2 = (Button) row.findViewById(R.id.itembutton2);
                holder.deletebtn.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        // TODO Auto-generated method stub
                        Perishable removed;

                        removed = newDBH.getPerishable(temp.getpName());
                        newDBH.deletePerishable(removed);
                        newDBH.getAllPerishables();
                        Log.v(getClass().getSimpleName(), "**IS THIS GETTING CALLED**");
                        dataset.remove(removed);
                        //dataset.remove(removed);
                        notifyDataSetChanged();
                        dataset.clear();

                        list = new SimpleListFragment();

                        list.openAndQueryDatabase();

                        dataset = new MultiAdapter(actContext, R.layout.row, results);
                        //refresh(results);
                        //.
                        //dataset = new MultiAdapter(actContext, R.layout.row, results);

                        list.setListAdapter(dataset);


                    }
                });
                //Log.v(getClass().getSimpleName(), "this is the position set for the item: " + temp.getPosition());
                //String newName = temp.getpName().substring(1);
                String newName = removeInts(temp.getpName());
                holder.name.setText(newName);  //placing name in row
                holder.date.setText(temp.getBadOnMax()); //placing the bad date in row
                int i = 0;
                try {
                    i = checkDate(temp.getBadOnMax());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                if (i == 1) {
                    holder.date.setTextColor(Color.RED);
                } else {
                    holder.date.setTextColor(Color.GREEN);
                }
                if (temp2.getPosition() == 1) {
                    holder.location.setText("Pantry");
                } else if (temp2.getPosition() == 2) {
                    holder.location.setText("Fridge");
                } else if (temp2.getPosition() == 3) {
                    holder.location.setText("Freezer");
                }

                return row;

        }

        private int checkDate(String badOnMax) throws ParseException {
            SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yy");
            Calendar thisDay = Calendar.getInstance();
            Date today = thisDay.getTime();
            Date nextDate = new Date();
            nextDate = dateFormat.parse(badOnMax);
            if(nextDate.before(today))
            {
                return 1;
            }
            else
            {
                return 0;
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

    static class ViewHolder
    {
        TextView name, date, location;
        Button chillbtn, freezebtn, deletebtn;
    }
}
