package mycompanyname.foodtracker;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nicholas on 2/21/2018.
 */

public class DatabaseHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_NAME = "fridgeManager";
    private static final String TABLE_NAME = "fridge";
    private static final String KEY_NAME = "name";
    private static final String KEY_GOESBADONMIN = "badondatemin";
    private static final String KEY_GOESBADONMAX = "badondatemax";
    private static final int KEY_ISFROZEN = 0;
    private static final String KEY_INDEX = "ROWID";
    private static final String KEY_PANTRYMIN = "minPantry";
    private static final String KEY_PANTRYMAX = "maxPantry";
    private static final String KEY_FRIDGEMIN = "minFridge";
    private static final String KEY_FRIDGEMAX = "maxFridge";
    private static final String KEY_FREEZEMIN = "minFreeze";
    private static final String KEY_FREEZEMAX = "maxFreeze";
    private static final String KEY_POSITION = "position";


    public DatabaseHandler(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        Log.e(getClass().getSimpleName(), "creating TABLE");
        String CREATE_FRIDGE_TABLE = "CREATE TABLE IF NOT EXISTS  " + TABLE_NAME + "(" +
                KEY_INDEX + " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_NAME + " TEXT, " + KEY_GOESBADONMIN + " TEXT, " +
                KEY_GOESBADONMAX + " TEXT," + KEY_PANTRYMIN + " TEXT, " +
                KEY_PANTRYMAX + " TEXT, " + KEY_FRIDGEMIN + " TEXT, " +
                KEY_FRIDGEMAX + " TEXT, " + KEY_FREEZEMIN + " TEXT, " +
                KEY_FREEZEMAX + " TEXT, " + KEY_POSITION + " INTEGER)";
        db.execSQL(CREATE_FRIDGE_TABLE);
        Log.e(getClass().getSimpleName(), "end creating TABLE");
    }

    @Override
    //used when user wants to remove the whole table and start new
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);

        onCreate(db);
    }

    public void addPerishable(Perishable temp)
    {
        Log.e(getClass().getSimpleName(), "BEGIN ADDING ITEM TO TABLE");
        /**
         * Need to look at this, need to first open the table and get the current amount of indeces in the table so that the auto increment
         * goes up automatically. currently it does not.
         *
         * So, setting the count as the index works unless something is deleted, then it fucks up and won't add anything to the table
         * I need to figure out a way to increase the index and continue to increase it so the values do not continue to fuck up and
         * not get added to the table.  Need to figure out a way to increase the index, or reassign the indeces when something is deleted
         * Right now when I delete something it doesn't add the next item to the table because the ROWID is equal to the item deleted previously
          */
        /**
         * Need to figure out how to get the above SQL statement to give me the index of the item I'm looking for if the item all ready exists
         * in the table
         */
        List<Perishable> templist = getAllPerishables();
        //int position = templist.size() + 1;

        SQLiteDatabase db = this.getWritableDatabase();
        /*if(DATABASE_VERSION != 1)
        {onUpgrade(db, 1, DATABASE_VERSION);}
        */
        String strquery = "SELECT " + KEY_INDEX + " FROM " + TABLE_NAME + " WHERE " + KEY_NAME + " = '" + temp.getpName() + "'";
        SQLiteStatement z = db.compileStatement(strquery);

        Cursor resource = db.rawQuery(strquery, null);
        long colIndex = resource.getColumnIndexOrThrow(KEY_INDEX);
        SQLiteStatement s = db.compileStatement("SELECT Count(*) FROM " + TABLE_NAME);
        long count = s.simpleQueryForLong();

        //int temp1 = db.compileStatement("SELECT Count(*) FROM " + TABLE_NAME);
        Log.i(getClass().getSimpleName(), "--------------------------");
        Log.e(getClass().getSimpleName(), "size of table " + count);
        count += 1;
        Log.e(getClass().getSimpleName(), "size of table " + count);
        Log.i(getClass().getSimpleName(), "--------------------------");
        //db.openDatabase(path, factory, flags)
        Log.e(getClass().getSimpleName(), "1 colIndex: " + colIndex);
        db.beginTransaction();
        Log.e(getClass().getSimpleName(), "2");
        ContentValues values = new ContentValues();
        Log.e(getClass().getSimpleName(), "3");
        //values.put(KEY_INDEX, position);

        values.put(KEY_NAME, count + temp.getpName());
        Log.e(getClass().getSimpleName(), "4 Count: " + count);

       /* if(colIndex != -1) {
           // Log.i(getClass().getSimpleName(), z.simpleQueryForString());
            values.put(KEY_INDEX, count);
        }
        else
        {
            Log.i(getClass().getSimpleName(), "THERE IS NO ITEM IN THE TABLE");
        }*/
        s = db.compileStatement("SELECT * FROM " +  TABLE_NAME + " ORDER BY " +
                KEY_INDEX +
                " DESC LIMIT 1;");
        //values.put(KEY_INDEX, s.simpleQueryForLong());
        values.put(KEY_GOESBADONMIN, temp.getBadOnMin());
        values.put(KEY_GOESBADONMAX, temp.getBadOnMax());
        values.put(KEY_PANTRYMIN, temp.getMinPantry());
        values.put(KEY_PANTRYMAX, temp.getMaxPantry());
        values.put(KEY_FRIDGEMIN, temp.getMinFridge());
        values.put(KEY_FRIDGEMAX, temp.getMaxFridge());
        values.put(KEY_FREEZEMIN, temp.getMinFreeze());
        values.put(KEY_FREEZEMAX, temp.getMaxFreeze());
        values.put(KEY_POSITION, temp.getPosition());
        Log.e(getClass().getSimpleName(), "5");
        Log.e(getClass().getSimpleName(), "values: " + values);

        //Log.v(getClass().getSimpleName(), "***HERE IS THE LAST INDEX: " + s.simpleQueryForLong());
        db.insertOrThrow(TABLE_NAME, null, values);
        Log.e(getClass().getSimpleName(), "6");
        db.setTransactionSuccessful();
        Log.e(getClass().getSimpleName(), "7");
        db.endTransaction();
        Log.e(getClass().getSimpleName(), "8");
        db.close();
        Log.e(getClass().getSimpleName(), "FINISH ADDING ITEM TO TABLE");
    }

    public Perishable getPerishable(String name)
    {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_NAME, new String[] {KEY_INDEX, KEY_NAME, KEY_GOESBADONMIN, KEY_GOESBADONMAX, KEY_PANTRYMIN, KEY_PANTRYMAX, KEY_FRIDGEMIN,
                        KEY_FRIDGEMAX, KEY_FREEZEMIN, KEY_FREEZEMAX, KEY_POSITION},
                KEY_NAME + "=?", new String[] {name}, null, null, null, null);
        if(cursor != null)
        {
            cursor.moveToFirst();
        }

        Perishable perish = new Perishable(cursor.getInt(0), cursor.getString(1), cursor.getString(2),
                cursor.getString(3), cursor.getString(4), cursor.getString(5),
                cursor.getString(6), cursor.getString(7), cursor.getString(8), cursor.getString(9), cursor.getInt(10));

        return perish;
    }

    public List<Perishable> getAllPerishables()
    {
        List<Perishable> pList = new ArrayList<Perishable>();

        String selectQuery = "SELECT * FROM  " + TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        if(c.moveToFirst())
        {
            do{
                Perishable p = new Perishable();
                p.setIndex(c.getInt(0));
                p.setpName(c.getString(1));
                p.setBadOnMin(c.getString(2));
                p.setBadOnMax(c.getString(3));
                p.setMinPantry(c.getString(4));
                p.setMaxPantry(c.getString(5));
                p.setMinFridge(c.getString(6));
                p.setMaxFridge(c.getString(7));
                p.setMinFreeze(c.getString(8));
                p.setMaxFreeze(c.getString(9));
                p.setPosition(c.getInt(10));
                pList.add(p);
            }while(c.moveToNext());
        }

        return pList;
    }

    public int updatePerishable(Perishable pTemp)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        //Calendar thisDay = Calendar.getInstance();
        values.put(KEY_GOESBADONMIN, pTemp.getBadOnMin());
        values.put(KEY_GOESBADONMAX, pTemp.getBadOnMax());
        values.put(KEY_POSITION, pTemp.getPosition());
        //Date today = thisDay.getTime();
		/*if(pTemp.getInPantry() == 1)
		{

		}
		if(pTemp.getIsFrozen() == 1)
		{
			values.put(KEY_GOESBADONMIN, pTemp.getMinFreeze());
			values.put(KEY_GOESBADONMAX, pTemp.getMaxFreeze());
			//thisDay.add(Calendar.DATE, pTemp.getMaxFreeze());
		}
		if(pTemp.getInFridge() == 1)
		{
			values.put(KEY_GOESBADONMIN, pTemp.getMinFridge());
			values.put(KEY_GOESBADONMAX, pTemp.getMaxFridge());
		}
		*/
        //values.put(KEY_GOESBADON, String.valueOf(thisDay.getTimeInMillis()));

        return db.update(TABLE_NAME, values, KEY_INDEX + "='" + pTemp.getIndex() + "'", null);
    }

    public void deletePerishable(Perishable pTemp)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABLE_NAME, KEY_INDEX + "='"+ pTemp.getIndex() + "'", null);
        db.close();
    }
}
