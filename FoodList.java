package mycompanyname.foodtracker;

/**
 * Created by Nicholas on 1/24/2018.
 */
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteException;
import android.util.Log;

import java.io.File;

public class FoodList extends SQLiteOpenHelper {

    public SQLiteDatabase perishablePricesDb;
    public String DBPath;
    public static String DBName = "foodDB.db";
    public static final int version = '1';
    public static Context currentContext;
    public static String tableName = "foodList";

    public FoodList(Context context) {
        super(context, DBName, null, version);
        currentContext = context;
        DBPath = currentContext.getApplicationInfo().dataDir + "/databases/";

        String myPath = DBPath + DBName;

		File dbfile = new File(myPath);
        boolean dbExists = checkDbExists();
		if(dbExists)
		{
			//Toast.makeText(context, "database exists", Toast.LENGTH_LONG).show();
            Log.e(getClass().getSimpleName(), "do nothing, database exists: " + dbExists + ", dbfile: " + dbfile.length());

            if(dbfile.length() != 12288)
            {
                //dbfile.delete();
                Log.i(getClass().getSimpleName(), "DOES THIS IF TRIGGER???");
                //createDatabase();
            }
            else
            {
                Log.i(getClass().getSimpleName(), "WHAT ABOUT THIS???");
                dbfile.delete();
                createDatabase();
            }
            //
            //Log.e(getClass().getSimpleName(), "do nothing, database exists");
		}
		else
		{
			//Toast.makeText(context, "this SGDMF doesn't exist", Toast.LENGTH_LONG).show()
                //
            createDatabase();
			Log.e(getClass().getSimpleName(), "this SGDMF doesn't exist");
		}
        Log.e(getClass().getSimpleName(), "does it make it to this?");
        //createDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
            createDatabase();
    }

    public void createDatabase()
    {
        //perishablePricesDb.execSQL("DROP TABLE IF EXISTS " + tableName);

       /* Log.e(getClass().getSimpleName(), "does Db exist: " + dbExists);
        if(dbExists)
        {
            //do nothing


        }
        else
        {*/
//Log.e(getClass().getSimpleName(), "Is this being called, it shouldn\'t be");
            Log.e(getClass().getSimpleName(), "starting to create table in DB " + DBName);
            perishablePricesDb = currentContext.openOrCreateDatabase(DBName, 0, null);
            perishablePricesDb.execSQL("CREATE TABLE IF NOT EXISTS " +
                    tableName +
                    "(PerishableName VARCHAR, " +
                    "uoPantryMin INT(4), uoPantryMax INT(4), " +
                    "uoFridgeMin INT(4), uoFridgeMax INT(4), " +
                    "uoFreezeMin INT(4), uoFreezeMax INT(4)," +
                    "states INT(1)," +
                    "oPantryMin INT(4), oPantryMax INT(4), " +
                    "oFridgeMin INT(4), oFridgeMax INT(4), " +
                    "oFreezeMin INT(4), oFreezeMax INT(4), " +
                    "category VARCHAR);"
            );

            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Alfalfa sprouts', '0', '0', '2', '3', '0', '0', '1', '0', '0', '0', '0', '0', '0', 'v');"
            );//1
            Log.e(getClass().getSimpleName(), "1 items entered into " + DBName);
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Ancho peppers', '0', '0', '0', '365', '0', '0', '1', '0', '0', '0', '0', '0', '0', 'v');"
            );//2
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Apples', '7', '14', '30', '60', '10', '12', '1', '0', '0', '0', '0', '0', '0', 'f');"
            );//3
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Apricots', '1', '3', '4', '5', '10', '12', '1', '0', '0', '0', '0', '0', '0', 'f');"
            );//4
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Artichokes', '0', '0', '0', '7', '10', '12', '1', '0', '0', '0', '0', '0', '0', 'v');"
            );//5
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Asparagus', '0', '0', '5', '7', '0', '0', '2', '0', '0', '5', '7', '12', '18', 'v');"
            );//6 fresh and cooked
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Avocados', '3', '4', '7', '10', '3', '6', '1', '0', '0', '0', '0', '0', '0', 'v');"
            );//7
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Bananas', '2', '7', '2', '9', '2', '3', '1', '0', '0', '0', '0', '0', '0', 'f');"
            );//8
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Banana peppers', '0', '0', '0', '7', '10', '12', '1', '0', '0', '0', '0', '0', '0', 'v');"
            );//9
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Canned beans', '0', '365', '0', '0', '0', '0', '2', '0', '0', '0', '5', '0', '0', 'b');"
            );//10
            Log.e(getClass().getSimpleName(), "10 items entered into " + DBName);
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Beats', '0', '0', '0', '14', '12', '18', '1', '0', '0', '0', '0', '0', '0', 'v');"
            );//11
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Bell peppers', '0', '0', '7', '14', '10', '12', '1', '0', '0', '0', '0', '0', '0', 'v');"
            );//12
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Blackberries', '0', '0', '2', '3', '10', '12', '1', '0', '0', '0', '0', '0', '0', 'f');"
            );//13
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Blueberries', '2', '7', '5', '10', '6', '6', '1', '0', '0', '0', '0', '0', '0', 'f');"
            );//24
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Broccoli', '0', '0', '7', '14', '0', '0', '2', '0', '0', '7', '9', '0', '0', 'v');"
            );//15
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Brussel sprouts', '0', '0', '3', '5', '12', '18', '1', '0', '0', '0', '0', '0', '0', 'v');"
            );//16
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Boysenberries', '0', '0', '2', '3', '10', '12', '1', '0', '0', '0', '0', '0', '0', 'f');"
            );//17
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Cabbage', '0', '0', '0', '7', '12', '18', '1', '0', '0', '0', '0', '0', '0', 'v');"
            );//18
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Canteloupe', '2', '4', '7', '10', '10', '12', '1', '0', '0', '0', '0', '0', '0', 'f');"
            );//19
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Carrots', '0', '0', '28', '35', '0', '0', '2', '0', '0', '0', '7', '0', '0', 'v');"
            );//20
            Log.e(getClass().getSimpleName(), "20 items entered into " + DBName);
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Casaba melon', '2', '4', '7', '10', '10', '12', '1', '0', '0', '0', '0', '0', '0', 'f');"
            );//21
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Cherries', '0', '0', '4', '7', '10', '12', '1', '0', '0', '0', '0', '0', '0', 'f');"
            );//22
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Clementines', '0', '7', '7', '14', '10', '12', '1', '0', '0', '0', '0', '0', '0', 'f');"
            );//23
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Cranberries', '0', '0', '21', '28', '10', '12', '1', '0', '0', '0', '0', '0', '0', 'f');"
            );//24
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Crenshaw melons', '2', '4', '7', '10', '10', '12', '1', '0', '0', '0', '0', '0', '0', 'f');"
            );//25
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Currants', '0', '0', '1', '2', '10', '12', '1', '0', '0', '0', '0', '0', '0', 'f');"
            );//26
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Dates', '30', '60', '0', '365', '12', '24', '1', '0', '0', '0', '0', '0', '0', 'f');"
            );//27
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Elderberries', '0', '0', '2', '3', '10', '12', '1', '0', '0', '0', '0', '0', '0', 'f');"
            );//28
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Feijoa', '2', '3', '1', '2', '10', '12', '1', '0', '0', '0', '0', '0', '0', 'f');"
            );//29
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Figs', '2', '5', '5', '7', '6', '8', '1', '0', '0', '0', '0', '0', '0', 'f');"
            );//30
            Log.e(getClass().getSimpleName(), "30 items entered into " + DBName);
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Gooseberries', '0', '0', '2', '3', '10', '12', '1', '0', '0', '0', '0', '0', '0', 'f');"
            );//31
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Grapefruit', '0', '7', '14', '21', '10', '12', '1', '0', '0', '0', '0', '0', '0', 'f');"
            );//32
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Grapes', '3', '5', '7', '10', '3', '5', '1', '0', '0', '0', '0', '0', '0', 'f');"
            );//33
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Guava', '2', '5', '3', '4', '10', '12', '1', '0', '0', '0', '0', '0', '0', 'f');"
            );//34
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Honeydew', '2', '4', '5', '7', '10', '12', '1', '0', '0', '0', '0', '0', '0', 'f');"
            );//35
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Huckleberries', '0', '0', '7', '14', '10', '12', '1', '0', '0', '0', '0', '0', '0', 'f');"
            );//36
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Kiwi fruit', '2', '3', '5', '7', '10', '12', '1', '0', '0', '0', '0', '0', '0', 'f');"
            );//37
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Kumquats', '3', '5', '14', '21', '10', '12', '1', '0', '0', '0', '0', '0', '0', 'f');"
            );//38
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Lemons', '14', '28', '30', '60', '0', '0', '2', '0', '0', '2', '3', '0', '0', 'f');"
            );//39
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Limes', '14', '28', '30', '60', '0', '0', '2', '0', '0', '2', '3', '0', '0', 'f');"
            );//40
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Litchis', '0', '0', '5', '7', '10', '12', '1', '0', '0', '0', '0', '0', '0', 'f');"
            );//41
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Mangos', '2', '5', '5', '7', '10', '12', '1', '0', '0', '0', '0', '0', '0', 'f');"
            );//42
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Nectarines', '2', '3', '3', '5', '10', '12', '1', '0', '0', '0', '0', '0', '0', 'f');"
            );//43
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Oranges', '14', '21', '30', '60', '0', '0', '2', '0', '0', '1', '2', '0', '0', 'f');"
            );//44
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Papayas', '2', '5', '2', '3', '10', '12', '1', '0', '0', '0', '0', '0', '0', 'f');"
            );//45
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Passion fruit', '2', '5', '5', '7', '6', '8', '1', '0', '0', '0', '0', '0', '0', 'f');"
            );//46
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Peaches', '1', '3', '3', '5', '10', '12', '1', '0', '0', '0', '0', '0', '0', 'f');"
            );//47
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Pears', '1', '4', '5', '7', '10', '12', '1', '0', '0', '0', '0', '0', '0', 'f');"
            );//48
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Persimmons', '1', '4', '2', '3', '10', '12', '1', '0', '0', '0', '0', '0', '0', 'f');"
            );//49
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Pineapple', '2', '3', '4', '5', '0', '0', '2', '0', '0', '3', '4', '3', '5', 'f');"
            );//50
            Log.e(getClass().getSimpleName(), "50 items entered into " + DBName);
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Plantains', '3', '5', '3', '5', '10', '12', '1', '0', '0', '0', '0', '0', '0', 'f');"
            );//51
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Plums', '2', '3', '3', '5', '10', '12', '1', '0', '0', '0', '0', '0', '0', 'f');"
            );//52
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Pomegranates', '7', '14', '30', '60', '10', '12', '1', '0', '0', '0', '0', '0', '0', 'f');"
            );//53
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Prickly pears', '2', '5', '1', '3', '10', '12', '1', '0', '0', '0', '0', '0', '0', 'f');"
            );//54
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Quinces', '2', '5', '14', '21', '10', '12', '1', '0', '0', '0', '0', '0', '0', 'f');"
            );//55
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Raspberries', '0', '0', '2', '3', '10', '12', '1', '0', '0', '0', '0', '0', '0', 'f');"
            );//56
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Star fruit', '2', '5', '5', '7', '10', '12', '1', '0', '0', '0', '0', '0', '0', 'f');"
            );//57
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Strawberries', '1', '2', '5', '7', '6', '8', '2', '0', '1', '1', '3', '3', '4', 'f');"
            );//58 *whole/cut not unopened/opened for states
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Tangerines', '0', '7', '14', '21', '10', '12', '1', '0', '0', '0', '0', '0', '0', 'f');"
            );//59
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Ugli fruit', '0', '7', '14', '21', '10', '12', '1', '0', '0', '0', '0', '0', '0', 'f');"
            );//60
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Watermelon', '7', '10', '0', '14', '10', '12', '1', '0', '0', '0', '0', '0', '0', 'f');"
            );//61
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Cauliflower', '0', '0', '0', '7', '12', '18', '1', '0', '0', '0', '0', '0', '0', 'v');"
            );//62
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Celery', '0', '0', '21', '28', '0', '0', '1', '0', '0', '0', '0', '0', '0', 'v');"
            );//63
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Chinese cabbage', '0', '0', '3', '5', '10', '12', '1', '0', '0', '0', '0', '0', '0', 'v');"
            );//64
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Collard greens', '0', '0', '4', '5', '10', '12', '1', '0', '0', '0', '0', '0', '0', 'v');"
            );//65
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Corn on the cob', '0', '0', '5', '7', '0', '0', '2', '0', '0', '5', '7', '0', '0', 'v');"
            );//66
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Cucumber', '0', '0', '0', '7', '0', '0', '1', '0', '0', '0', '0', '0', '0', 'v');"
            );//67
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Eggplant', '0', '0', '5', '7', '6', '8', '1', '0', '0', '0', '0', '0', '0', 'v');"
            );//68
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Garlic cloves(not chopped)', '90', '150', '0', '0', '10', '12', '1', '0', '0', '0', '0', '0', '0', 'v');"
            );//69
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Green onions', '0', '0', '7', '10', '10', '12', '1', '0', '0', '0', '0', '0', '0', 'v');"
            );//70
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Guero peppers', '0', '0', '0', '7', '10', '12', '1', '0', '0', '0', '0', '0', '0', 'v');"
            );//71
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Habanero peppers', '0', '0', '0', '7', '10', '12', '1', '0', '0', '0', '0', '0', '0', 'v');"
            );//72
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Jalepeno peppers', '0', '0', '0', '7', '10', '12', '1', '0', '0', '0', '0', '0', '0', 'v');"
            );//73
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Jicama', '0', '0', '7', '14', '0', '0', '1', '0', '0', '0', '0', '0', '0', 'v');"
            );//74
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Kale', '0', '0', '7', '14', '0', '0', '2', '0', '0', '5', '7', '0', '0', 'v');"
            );//75
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Leeks', '0', '0', '7', '14', '10', '12', '1', '0', '0', '0', '0', '0', '0', 'v');"
            );//76
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Lettuce, Bibb', '0', '0', '3', '4', '0', '0', '1', '0', '0', '0', '0', '0', '0', 'v');"
            );//77
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Lettuce, Boston', '0', '0', '3', '4', '0', '0', '1', '0', '0', '0', '0', '0', '0', 'v');"
            );//78
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Lettuce, Butterhead', '0', '0', '3', '5', '0', '0', '1', '0', '0', '0', '0', '0', '0', 'v');"
            );//79
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Lettuce, Iceberg', '0', '0', '7', '10', '0', '0', '1', '0', '0', '0', '0', '0', '0', 'v');"
            );//80
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Lettuce, leaf(green and red)', '0', '0', '5', '7', '0', '0', '1', '0', '0', '0', '0', '0', '0', 'v');"
            );//81
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Lettuce, Lollo russo', '0', '0', '2', '3', '0', '0', '1', '0', '0', '0', '0', '0', '0', 'v');"
            );//82
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Lettuce, Mache', '0', '0', '1', '2', '0', '0', '1', '0', '0', '0', '0', '0', '0', 'v');"
            );//83
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Lettuce, Romaine', '0', '0', '7', '10', '0', '0', '1', '0', '0', '0', '0', '0', '0', 'v');"
            );//84
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Lima beans', '0', '0', '2', '3', '0', '8', '1', '0', '0', '0', '0', '0', '0', 'v');"
            );//85
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Mesclun greens', '0', '0', '2', '3', '0', '0', '1', '0', '0', '0', '0', '0', '0', 'v');"
            );//86
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Mungbean sprouts', '0', '0', '3', '5', '10', '12', '1', '0', '0', '0', '0', '0', '0', 'v');"
            );//87
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Mushrooms(all kinds), fresh', '0', '0', '7', '10', '0', '0', '1', '0', '0', '0', '0', '0', '0', 'v');"
            );//88
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Nopales', '0', '0', '7', '14', '0', '0', '1', '0', '0', '0', '0', '0', '0', 'v');"
            );//89
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Okra', '0', '0', '2', '3', '10', '12', '1', '0', '0', '0', '0', '0', '0', 'v');"
            );//90
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Onions(non-sweet)', '28', '42', '30', '60', '0', '0', '2', '0', '0', '0', '7', '0', '0', 'v');"
            );//91
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Onions(sweet)', '28', '42', '30', '60', '0', '0', '2', '0', '0', '0', '7', '0', '0', 'v');"
            );//92
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Parsnips', '0', '0', '21', '28', '10', '12', '1', '0', '0', '0', '0', '0', '0', 'v');"
            );//93
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Peas, green', '0', '0', '3', '5', '12', '18', '1', '0', '0', '0', '0', '0', '0', 'v');"
            );//94
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Peas, sugar snap', '0', '0', '3', '5', '10', '12', '1', '0', '0', '0', '0', '0', '0', 'v');"
            );//95
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Pimientos', '0', '0', '7', '14', '10', '12', '1', '0', '0', '0', '0', '0', '0', 'v');"
            );//96
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Poblano peppers', '0', '0', '0', '7', '10', '12', '1', '0', '0', '0', '0', '0', '0', 'v');"
            );//97
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Potatoes(non-sweet & not yams)', '21', '35', '0', '0', '0', '0', '2', '0', '0', '5', '7', '0', '0', 'v');"
            );//98* potatoes need warning not to refrigerate
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Pumpkin', '60', '90', '90', '150', '0', '0', '2', '0', '0', '2', '3', '6', '8', 'v');"
            );//99
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Radicchio', '0', '0', '3', '5', '0', '0', '1', '0', '0', '0', '0', '0', '0', 'v');"
            );//100
            Log.e(getClass().getSimpleName(), "100 items entered into " + DBName);
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Radishes', '0', '0', '10', '14', '0', '0', '1', '0', '0', '0', '0', '0', '0', 'v');"
            );//101
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Rhubarb', '0', '0', '5', '7', '10', '12', '1', '0', '0', '0', '0', '0', '0', 'v');"
            );//102
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Rutabagas', '0', '0', '14', '21', '6', '8', '1', '0', '0', '0', '0', '0', '0', 'v');"
            );//103
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Sauerkraut(refrigerated)', '0', '0', '14', '21', '0', '0', '1', '0', '0', '0', '0', '0', '0', 'v');"
            );//104
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Sauerkraut(unrefrigerated)', '730', '1825', '0', '0', '0', '0', '2', '0', '0', '365', '730', '0', '0', 'v');"
            );//105
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Seaweed(dried)', '60', '120', '0', '0', '4', '6', '1', '0', '0', '0', '0', '0', '0', 'v');"
            );//106
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Serrano pepper', '0', '0', '0', '7', '10', '12', '1', '0', '0', '0', '0', '0', '0', 'v');"
            );//107
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Shallots', '0', '30', '0', '30', '10', '12', '1', '0', '0', '0', '0', '0', '0', 'v');"
            );//108
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Snow peas', '0', '0', '3', '5', '10', '12', '1', '0', '0', '0', '0', '0', '0', 'v');"
            );//109
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Sorrel', '0', '0', '1', '2', '10', '12', '1', '0', '0', '0', '0', '0', '0', 'v');"
            );//110
            Log.e(getClass().getSimpleName(), "110 items entered into " + DBName);
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Soybeans', '0', '0', '3', '5', '0', '8', '1', '0', '0', '0', '0', '0', '0', 'v');"
            );//111
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Spinach', '0', '0', '3', '5', '10', '12', '1', '0', '0', '0', '0', '0', '0', 'v');"
            );//112
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Squash, summer', '1', '5', '5', '7', '0', '0', '1', '0', '0', '0', '0', '0', '0', 'v');"
            );//113 *display specific names of summer squash on itemselected page
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Squash, winter', '30', '60', '0', '0', '0', '0', '1', '0', '0', '0', '0', '0', '0', 'v');"
            );//114 *dispaly specific names of winter squash in itemselected page
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Sweet potatoes', '21', '35', '60', '90', '0', '0', '1', '0', '0', '0', '0', '0', '0', 'v');"
            );//115 *display warning not to refrigerate potatoes
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Swiss chard', '0', '0', '2', '3', '10', '12', '1', '0', '0', '0', '0', '0', '0', 'v');"
            );//116
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Tamarillos', '0', '0', '7', '10', '0', '0', '1', '0', '0', '0', '0', '0', '0', 'v');"
            );//117
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Taro', '0', '0', '2', '3', '0', '0', '1', '0', '0', '0', '0', '0', '0', 'v');"
            );//118
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Tomatoes', '1', '5', '2', '3', '0', '2', '1', '0', '0', '0', '0', '0', '0', 'v');"
            );//119
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Turnip greens', '0', '0', '1', '2', '10', '12', '1', '0', '0', '0', '0', '0', '0', 'v');"
            );//120
            Log.e(getClass().getSimpleName(), "120 items entered into " + DBName);
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Turnips', '0', '0', '14', '21', '8', '10', '1', '0', '0', '0', '0', '0', '0', 'v');"
            );//121
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Yams', '21', '35', '60', '90', '0', '0', '1', '0', '0', '0', '0', '0', '0', 'v');"
            );//122 *display warning about refrigerating yams/potatoes
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Yardlong beans', '0', '0', '3', '5', '10', '12', '1', '0', '0', '0', '0', '0', '0', 'v');"
            );//123
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('American cheese slices', '0', '0', '0', '30', '0', '3', '1', '0', '0', '0', '0', '0', '0', 'c');"
            );//124 *30 days after date on package
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Appenzeller cheese(from deli)', '0', '0', '21', '28', '0', '3', '1', '0', '0', '0', '0', '0', '0', 'c');"
            );//125
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Asiago cheese', '0', '0', '60', '120', '6', '18', '2', '0', '0', '21', '42', '6', '8', 'c');"
            );//126
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Beaufort cheese', '0', '0', '21', '28', '0', '3', '1', '0', '0', '0', '0', '0', '0', 'c');"
            );//127
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Bel paese cheese(from deli)', '0', '0', '14', '21', '0', '2', '1', '0', '0', '0', '0', '0', '0', 'c');"
            );//128
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Bleu cheese', '0', '0', '0', '7', '0', '3', '1', '0', '0', '0', '0', '0', '0', 'c');"
            );//129
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Boursalt cheese', '0', '0', '0', '7', '0', '0', '1', '0', '0', '0', '0', '0', '0', 'c');"
            );//130
            Log.e(getClass().getSimpleName(), "130 items entered into " + DBName);
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Brick cheese', '0', '0', '0', '7', '0', '3', '1', '0', '0', '0', '0', '0', '0', 'c');"
            );//131
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Brie cheese', '0', '0', '0', '7', '0', '3', '1', '0', '0', '0', '0', '0', '0', 'c');"
            );//132
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Butter', '0', '0', '0', '30', '6', '9', '1', '0', '0', '0', '14', '6', '9', 'c');"
            );//133 past printed date
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Buttermilk', '0', '0', '0', '14', '0', '3', '1', '0', '0', '0', '0', '0', '0', 'c');"
            );//134
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Cheddar cheese', '0', '0', '60', '90', '6', '8', '2', '0', '0', '0', '14', '6', '8', 'c');"
            );//135
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Cheshire cheese', '0', '0', '21', '28', '0', '3', '1', '0', '0', '0', '0', '0', '0', 'c');"
            );//136
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Colby cheese', '0', '0', '7', '14', '0', '6', '1', '0', '0', '0', '0', '0', '0', 'c');"
            );//137 past printed date
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Cottage cheese', '0', '0', '7', '10', '0', '3', '2', '0', '0', '5', '7', '2', '3', 'c');"
            );//138 past printed date all cheese for that matter is past the printed date
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Cream cheese (foil wrapped)', '0', '0', '21', '28', '0', '2', '2', '0', '0', '7', '14', '0', '0', 'c');"
            );//139
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Cream cheese (plastic container)', '0', '0', '21', '28', '0', '0', '2', '0', '0', '7', '14', '0', '0', 'c');"
            );//262
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Flavored cream cheese', '0', '0', '21', '28', '0', '0', '2', '0', '0', '7', '14', '0', '0', 'c');"
            );//263
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Reduced fat cream cheese', '0', '0', '21', '28', '0', '0', '2', '0', '0', '7', '14', '0', '0', 'c');"
            );//264
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Dubliner cheese', '0', '0', '0', '7', '0', '3', '1', '0', '0', '0', '0', '0', '0', 'c');"
            );//140
            Log.e(getClass().getSimpleName(), "140 items entered into " + DBName);
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Edam cheese', '0', '0', '21', '28', '0', '3', '1', '0', '0', '0', '0', '0', '0', 'c');"
            );//141
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Eggs', '0', '0', '21', '28', '0', '12', '1', '0', '0', '0', '0', '0', '0', 'd');"
            );//142 past sell by date
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Egg substitute', '0', '0', '21', '28', '0', '12', '1', '0', '0', '0', '0', '0', '0', 'd');"
            );//265 past sell by date
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Egg whites', '0', '0', '21', '28', '0', '12', '1', '0', '0', '0', '0', '0', '0', 'd');"
            );//266 past sell by date
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Egg yolks', '0', '0', '21', '28', '0', '12', '1', '0', '0', '0', '0', '0', '0', 'd');"
            );//267 past sell by date
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Feta cheese', '0', '0', '0', '7', '0', '0', '1', '0', '0', '0', '0', '0', '0', 'c');"
            );//143
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Fontina cheese(from deli)', '0', '0', '21', '28', '0', '3', '1', '0', '0', '0', '0', '0', '0', 'c');"
            );//144
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Gloucester cheese(from deli)', '0', '0', '21', '28', '0', '6', '1', '0', '0', '0', '0', '0', '0', 'c');"
            );//145
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Goat cheese', '0', '0', '14', '21', '0', '3', '1', '0', '0', '0', '0', '0', '0', 'c');"
            );//146
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Gouda cheese', '0', '0', '0', '7', '0', '3', '1', '0', '0', '0', '0', '0', '0', 'c');"
            );//147
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Gruyere cheese(from deli)', '0', '0', '21', '28', '0', '3', '1', '0', '0', '0', '0', '0', '0', 'c');"
            );//148
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Havarti cheese', '0', '0', '7', '14', '0', '6', '1', '0', '0', '0', '0', '0', '0', 'c');"
            );//149
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Jarlsberg cheese', '0', '0', '21', '28', '0', '3', '1', '0', '0', '0', '0', '0', '0', 'c');"
            );//150
            Log.e(getClass().getSimpleName(), "150 items entered into " + DBName);
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Lancashire cheese', '0', '0', '30', '60', '0', '6', '1', '0', '0', '0', '0', '0', '0', 'c');"
            );//151
            Log.e(getClass().getSimpleName(), "1 " + DBName);
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Limburger cheese', '0', '0', '14', '21', '0', '2', '1', '0', '0', '0', '0', '0', '0', 'c');"
            );//152
            Log.e(getClass().getSimpleName(), "2 " + DBName);
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Mascarpone cheese', '0', '0', '0', '60', '0', '3', '1', '0', '0', '0', '0', '0', '0', 'c');"
            );//153
            Log.e(getClass().getSimpleName(), "3 " + DBName);
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Lactose-free Milk', '0', '0', '7', '10', '0', '0', '2', '0', '0', '5', '7', '0', '0', 'd');"
            );//271 all milk is past printed date
            Log.e(getClass().getSimpleName(), "4 " + DBName);
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Non-fat Milk', '0', '0', '7', '10', '0', '0', '2', '0', '0', '5', '7', '0', '0', 'd');"
            );//270
            Log.e(getClass().getSimpleName(), "5 " + DBName);
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Skim Milk', '0', '0', '0', '7', '0', '0', '2', '0', '0', '5', '7', '0', '0', 'd');"
            );//269
            Log.e(getClass().getSimpleName(), "6 " + DBName);
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Reduced-fat Milk', '0', '0', '0', '7', '0', '0', '2', '0', '0', '5', '7', '0', '0', 'd');"
            );//268
            Log.e(getClass().getSimpleName(), "7 " + DBName);
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Whole Milk', '0', '0', '5', '7', '0', '0', '2', '0', '0', '5', '7', '0', '0', 'd');"
            );//154
            Log.e(getClass().getSimpleName(), "8 " + DBName);
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Monterey Jack cheese', '0', '0', '7', '14', '0', '6', '1', '0', '0', '0', '0', '0', '0', 'c');"
            );//155
            Log.e(getClass().getSimpleName(), "9 " + DBName);
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Mozzarella cheese', '0', '0', '7', '14', '0', '6', '1', '0', '0', '0', '0', '0', '0', 'c');"
            );//156
            Log.e(getClass().getSimpleName(), "10 " + DBName);
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Muenster cheese', '0', '0', '7', '14', '0', '0', '1', '0', '0', '0', '0', '0', '0', 'c');"
            );//157
            Log.e(getClass().getSimpleName(), "11 " + DBName);
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Neufchatel cheese', '0', '0', '14', '21', '0', '3', '2', '0', '0', '7', '14', '0', '0', 'c');"
            );//158
            Log.e(getClass().getSimpleName(), "12 " + DBName);
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Parmesan cheese', '0', '365', '60', '120', '6', '8', '2', '0', '0', '21', '42', '6', '8', 'c');"
            );//159
            Log.e(getClass().getSimpleName(), "13 " + DBName);
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Parmigiano-reggiano cheese', '0', '0', '0', '365', '10', '12', '1', '0', '0', '0', '0', '0', '0', 'c');"
            );//160
            Log.e(getClass().getSimpleName(), "160 items entered into " + DBName);
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Port du salut cheese', '0', '0', '14', '21', '0', '2', '1', '0', '0', '0', '0', '0', '0', 'c');"
            );//161
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Provolone cheese', '0', '0', '5', '7', '0', '3', '1', '0', '0', '0', '0', '0', '0', 'c');"
            );//162
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Ricotta cheese', '0', '0', '7', '10', '0', '3', '2', '0', '0', '5', '7', '2', '3', 'c');"
            );//163
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Romano cheese', '0', '365', '0', '365', '6', '8', '2', '0', '0', '21', '42', '6', '8', 'c');"
            );//164
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Roquefort cheese', '0', '0', '21', '28', '0', '6', '1', '0', '0', '0', '0', '0', '0', 'c');"
            );//165
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Sour cream', '0', '0', '7', '14', '0', '0', '2', '0', '0', '7', '10', '0', '0', 'd');"
            );//166
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Reduced fat sour cream', '0', '0', '7', '14', '0', '0', '2', '0', '0', '7', '10', '0', '0', 'd');"
            );//272
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Sour cream dip', '0', '0', '0', '14', '0', '0', '2', '0', '0', '7', '10', '0', '0', 'd');"
            );//273
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Soy milk', '0', '0', '7', '10', '0', '3', '1', '0', '0', '0', '0', '0', '0', 'd');"
            );//167
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Stilton cheese', '0', '0', '21', '28', '0', '6', '1', '0', '0', '0', '0', '0', '0', 'c');"
            );//168
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Swiss cheese', '0', '0', '0', '7', '0', '3', '1', '0', '0', '0', '0', '0', '0', 'c');"
            );//169
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Tilsiter cheese', '0', '0', '14', '21', '0', '2', '1', '0', '0', '0', '0', '0', '0', 'c');"
            );//170
            Log.e(getClass().getSimpleName(), "170 items entered into " + DBName);
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Whipped cream(aerosol can)', '0', '0', '14', '21', '0', '0', '1', '0', '0', '0', '0', '0', '0', 'd');"
            );//171
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Cool Whip cream', '0', '0', '7', '14', '3', '4', '2', '0', '0', '7', '10', '3', '4', 'd');"
            );//274
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Yogurt', '0', '0', '14', '21', '1', '2', '2', '0', '0', '0', '7', '0', '1', 'd');"
            );//172
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Yogurt with fruit', '0', '0', '7', '10', '1', '2', '2', '0', '0', '0', '7', '0', '1', 'd');"
            );//275
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Reduced fat Yogurt', '0', '0', '7', '14', '1', '2', '2', '0', '0', '0', '7', '0', '1', 'd');"
            );//276
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Greek Yogurt', '0', '0', '7', '14', '1', '2', '2', '0', '0', '0', '7', '0', '1', 'd');"
            );//277
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Drinkable Yogurt', '0', '0', '7', '10', '1', '2', '2', '0', '0', '0', '7', '0', '1', 'd');"
            );//278
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Frozen Yogurt', '0', '0', '0', '0', '2', '3', '2', '0', '0', '0', '0', '0', '1', 'd');"
            );//279
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Abruzzese sausage', '28', '45', '0', '180', '2', '3', '1', '0', '0', '0', '0', '0', '0', 'm');"
            );//173
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Bacon', '0', '0', '7', '14', '6', '8', '2', '0', '0', '0', '7', '0', '6', 'm');"
            );//174
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Beef brisket', '0', '0', '3', '5', '6', '8', '2', '0', '0', '0', '7', '6', '8', 'm');"
            );//175
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Beef heart', '0', '0', '1', '2', '3', '4', '2', '0', '0', '0', '7', '6', '8', 'm');"
            );//176
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Beef kidneys', '0', '0', '1', '2', '3', '4', '2', '0', '0', '0', '7', '6', '8', 'm');"
            );//177
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Beef liver', '0', '0', '1', '2', '3', '4', '2', '0', '0', '0', '7', '6', '8', 'm');"
            );//178
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Beef ribs', '0', '0', '3', '5', '6', '12', '2', '0', '0', '0', '7', '6', '8', 'm');"
            );//179
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Beef roast, all cuts', '0', '0', '3', '5', '6', '12', '2', '0', '0', '0', '7', '6', '8', 'm');"
            );//180
            Log.e(getClass().getSimpleName(), "180 items entered into " + DBName);
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Beef steak, all cuts', '0', '0', '3', '5', '6', '12', '2', '0', '0', '0', '7', '6', '8', 'm');"
            );//181
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Beef tongue', '0', '0', '1', '2', '3', '4', '2', '0', '0', '0', '7', '6', '8', 'm');"
            );//182
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Ground beef', '0', '0', '1', '2', '6', '8', '2', '0', '0', '0', '7', '6', '8', 'm');"
            );//183
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Beef, stew meat', '0', '0', '1', '2', '3', '4', '2', '0', '0', '0', '7', '6', '8', 'm');"
            );//184
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Bison chops', '0', '0', '3', '5', '4', '6', '2', '0', '0', '0', '7', '6', '8', 'm');"
            );//185
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Bison roast', '0', '0', '3', '5', '4', '6', '2', '0', '0', '0', '7', '6', '8', 'm');"
            );//186
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Bison steak', '0', '0', '3', '5', '4', '6', '2', '0', '0', '0', '7', '6', '8', 'm');"
            );//187
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Ground bison', '0', '0', '1', '2', '3', '4', '2', '0', '0', '0', '7', '6', '8', 'm');"
            );//188
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Bison, stew meat', '0', '0', '1', '2', '3', '4', '2', '0', '0', '0', '7', '6', '8', 'm');"
            );//189
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Blood sausage', '0', '0', '0', '7', '1', '2', '1', '0', '0', '0', '0', '0', '0', 'm');"
            );//190
            Log.e(getClass().getSimpleName(), "190 items entered into " + DBName);
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Bologna', '0', '0', '7', '14', '2', '3', '2', '0', '0', '7', '14', '1', '2', 'm');"
            );//191
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Bratwurst sausage', '0', '0', '1', '2', '1', '2', '1', '0', '0', '0', '0', '0', '0', 'm');"
            );//192
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Braunschweiger sausage', '0', '0', '0', '7', '1', '2', '1', '0', '0', '0', '0', '0', '0', 'm');"
            );//193
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Breakfast sausage', '0', '0', '1', '2', '1', '2', '1', '0', '0', '0', '0', '0', '0', 'm');"
            );//194
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Canadian bacon', '0', '0', '3', '4', '1', '2', '1', '0', '0', '0', '0', '0', '0', 'm');"
            );//195
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Capon', '0', '0', '1', '2', '0', '12', '1', '0', '0', '0', '0', '0', '0', 'm');"
            );//196
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Caribou', '0', '0', '3', '5', '6', '9', '1', '0', '0', '0', '0', '0', '0', 'm');"
            );//197
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Chicken breast & wings', '0', '0', '1', '2', '0', '12', '2', '0', '0', '0', '7', '0', '12', 'm');"
            );//198
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Chicken cold cuts', '0', '0', '7', '10', '6', '8', '2', '0', '0', '7', '10', '0', '0', 'm');"
            );//199
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Chicken nuggets', '0', '0', '0', '5', '0', '6', '1', '0', '0', '0', '0', '0', '0', 'm');"
            );//200
            Log.e(getClass().getSimpleName(), "200 items entered into " + DBName);
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Ground chicken', '0', '0', '1', '2', '3', '4', '1', '0', '0', '0', '0', '0', '0', 'm');"
            );//201
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Chorizo sausage', '0', '0', '14', '21', '1', '2', '1', '0', '0', '0', '0', '0', '0', 'm');"
            );//202
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Deer', '0', '0', '3', '5', '6', '9', '1', '0', '0', '0', '0', '0', '0', 'm');"
            );//203
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Duck, whole', '0', '0', '1', '2', '0', '6', '1', '0', '0', '0', '0', '0', '0', 'm');"
            );//204
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Goat, ground or stew meat', '0', '0', '1', '2', '3', '4', '1', '0', '0', '0', '0', '0', '0', 'm');"
            );//205
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Goat steak, roast, and chops', '0', '0', '3', '5', '6', '9', '1', '0', '0', '0', '0', '0', '0', 'm');"
            );//206
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Goose whole', '0', '0', '1', '2', '0', '6', '1', '0', '0', '0', '0', '0', '0', 'm');"
            );//207
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Ham cold cuts', '0', '0', '7', '10', '6', '8', '2', '0', '0', '7', '10', '0', '0', 'm');"
            );//208
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Hot dogs, all types', '0', '0', '7', '14', '4', '6', '2', '0', '0', '0', '7', '4', '6', 'm');"
            );//209 opened dates work for cooked and uncooked
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Italian sausage', '0', '0', '7', '10', '4', '6', '2', '0', '0', '0', '7', '4', '6', 'm');"
            );//210
            Log.e(getClass().getSimpleName(), "210 items entered into " + DBName);
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Knockwurst', '0', '0', '0', '7', '1', '2', '1', '0', '0', '0', '0', '0', '0', 'm');"
            );//211
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Lamb chops', '0', '0', '3', '5', '6', '9', '1', '0', '0', '0', '0', '0', '0', 'm');"
            );//212
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Lamb heart', '0', '0', '1', '2', '3', '4', '1', '0', '0', '0', '0', '0', '0', 'm');"
            );//213
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Lamb kidneys', '0', '0', '1', '2', '3', '4', '1', '0', '0', '0', '0', '0', '0', 'm');"
            );//214
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Lamb liver', '0', '0', '1', '2', '3', '4', '1', '0', '0', '0', '0', '0', '0', 'm');"
            );//215
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Lamb ribs', '0', '0', '3', '5', '4', '6', '1', '0', '0', '0', '0', '0', '0', 'm');"
            );//216
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Lamb roast, all cuts', '0', '0', '3', '5', '6', '9', '1', '0', '0', '0', '0', '0', '0', 'm');"
            );//217
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Lamb steak', '0', '0', '3', '5', '6', '12', '1', '0', '0', '0', '0', '0', '0', 'm');"
            );//218
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Ground lamb', '0', '0', '1', '2', '3', '4', '1', '0', '0', '0', '0', '0', '0', 'm');"
            );//219
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Lamb tongue', '0', '0', '1', '2', '3', '4', '1', '0', '0', '0', '0', '0', '0', 'm');"
            );//220
            Log.e(getClass().getSimpleName(), "220 items entered into " + DBName);
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Liverwurst', '0', '0', '0', '7', '1', '2', '1', '0', '0', '0', '0', '0', '0', 'm');"
            );//221
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Moose', '0', '0', '3', '5', '6', '9', '1', '0', '0', '0', '0', '0', '0', 'm');"
            );//222
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Olive loaf', '0', '0', '3', '5', '1', '2', '1', '0', '0', '0', '0', '0', '0', 'm');"
            );//223
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Pastrami', '0', '0', '3', '5', '1', '2', '1', '0', '0', '0', '0', '0', '0', 'm');"
            );//224
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Polish sausage', '0', '0', '0', '7', '1', '2', '1', '0', '0', '0', '0', '0', '0', 'm');"
            );//225
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Pork chops, all cuts', '0', '0', '1', '2', '6', '8', '2', '0', '0', '0', '7', '6', '8', 'm');"
            );//226
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Pork heart', '0', '0', '1', '2', '6', '8', '2', '0', '0', '0', '7', '6', '8', 'm');"
            );//227
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Pork kidneys', '0', '0', '1', '2', '6', '8', '2', '0', '0', '0', '7', '6', '8', 'm');"
            );//228
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Pork liver', '0', '0', '1', '2', '6', '8', '2', '0', '0', '0', '7', '6', '8', 'm');"
            );//229
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Pork ribs, all cuts', '0', '0', '3', '5', '6', '8', '2', '0', '0', '0', '7', '6', '8', 'm');"
            );//230
            Log.e(getClass().getSimpleName(), "230 items entered into " + DBName);
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Pork roast, all cuts', '0', '0', '3', '5', '6', '8', '2', '0', '0', '0', '7', '6', '8', 'm');"
            );//231
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Pork steak', '0', '0', '3', '5', '6', '12', '2', '0', '0', '0', '7', '6', '8', 'm');"
            );//232
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Pork tongue', '0', '0', '1', '2', '6', '8', '2', '0', '0', '0', '7', '6', '8', 'm');"
            );//233
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Ground pork', '0', '0', '1', '2', '6', '8', '2', '0', '0', '0', '7', '6', '8', 'm');"
            );//234
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Pork, stew meat', '0', '0', '1', '2', '6', '8', '2', '0', '0', '0', '7', '6', '8', 'm');"
            );//235
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Quail', '0', '0', '1', '2', '0', '6', '2', '0', '0', '0', '7', '6', '8', 'm');"
            );//236 all meat has uncooked and cooked states not unopen and open
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Rabbit', '0', '0', '1', '2', '0', '12', '2', '0', '0', '0', '7', '6', '8', 'm');"
            );//237
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Roast beef, cold cuts', '0', '0', '3', '5', '1', '2', '2', '0', '0', '0', '7', '6', '8', 'm');"
            );//238
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Salami', '0', '0', '21', '28', '2', '3', '2', '0', '0', '14', '21', '2', '3', 'm');"
            );//239 *after use by date
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Sausages', '0', '0', '1', '2', '1', '2', '1', '0', '0', '0', '0', '0', '0', 'm');"
            );//240
            Log.e(getClass().getSimpleName(), "240 items entered into " + DBName);
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Squab, pigeon', '0', '0', '1', '2', '0', '6', '1', '0', '0', '0', '0', '0', '0', 'm');"
            );//241
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Summer sausage', '0', '0', '0', '3', '1', '2', '1', '0', '0', '0', '0', '0', '0', 'm');"
            );//242
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Thuringer-style sausage', '0', '0', '0', '7', '1', '2', '1', '0', '0', '0', '0', '0', '0', 'm');"
            );//243 *after use by date
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Turkey cold cuts', '0', '0', '7', '10', '6', '8', '2', '0', '0', '7', '10', '0', '0', 'm');"
            );//244
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Turkey meatballs', '0', '0', '0', '5', '0', '6', '1', '0', '0', '0', '0', '0', '0', 'm');"
            );//245
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Ground turkey', '0', '0', '1', '2', '6', '8', '2', '0', '0', '0', '7', '6', '8', 'm');"
            );//246
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Turkey, whole', '0', '0', '1', '2', '6', '8', '2', '0', '0', '0', '7', '6', '8', 'm');"
            );//247
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Veal chops', '0', '0', '1', '2', '6', '8', '2', '0', '0', '0', '7', '6', '8', 'm');"
            );//248
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Veal heart', '0', '0', '1', '2', '6', '8', '2', '0', '0', '0', '7', '6', '8', 'm');"
            );//249
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Veal kidneys', '0', '0', '1', '2', '6', '8', '2', '0', '0', '0', '7', '6', '8', 'm');"
            );//250
            Log.e(getClass().getSimpleName(), "250 items entered into " + DBName);
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Veal liver', '0', '0', '1', '2', '6', '8', '2', '0', '0', '0', '7', '6', '8', 'm');"
            );//251
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Veal ribs', '0', '0', '1', '2', '6', '8', '2', '0', '0', '0', '7', '6', '8', 'm');"
            );//252
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Veal roast, all cuts', '0', '0', '1', '2', '6', '8', '2', '0', '0', '0', '7', '6', '8', 'm');"
            );//253
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Veal shanks', '0', '0', '1', '2', '6', '8', '2', '0', '0', '0', '7', '6', '8', 'm');"
            );//254
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Veal steak', '0', '0', '1', '2', '6', '8', '2', '0', '0', '0', '7', '6', '8', 'm');"
            );//255
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Veal stew meat', '0', '0', '1', '2', '6', '8', '2', '0', '0', '0', '7', '6', '8', 'm');"
            );//256
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Venison', '0', '0', '1', '2', '6', '8', '2', '0', '0', '0', '7', '6', '8', 'm');"
            );//257
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Wild boar', '0', '0', '1', '2', '6', '8', '2', '0', '0', '0', '7', '6', '8', 'm');"
            );//258
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Hamburger patties', '0', '0', '1', '2', '6', '8', '2', '0', '0', '5', '6', '4', '6', 'm');"
            );//259
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Applesauce', '30', '60', '0', '0', '0', '0', '2', '0', '0', '7', '14', '0', '0', 'f');"
            );//260
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Guacamole', '0', '0', '1', '2', '0', '0', '1', '0', '0', '0', '0', '0', '0', 'v');"
            );//261
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Baby carrots', '0', '0', '21', '28', '0', '0', '1', '0', '0', '0', '0', '0', '0', 'v');"
            );//262
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Spam', '730', '1825', '0', '0', '0', '0', '2', '0', '0', '7', '10', '0', '0', 'm');"
            );//274
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Canned Chicken', '730', '1825', '0', '0', '0', '0', '2', '0', '0', '0', '7', '0', '0', 'm');"
            );//275
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Canned Tuna', '730', '1825', '0', '0', '0', '0', '2', '0', '0', '0', '7', '0', '0', 's');"
            );//276
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Canned Corn Beef', '730', '1825', '0', '0', '0', '0', '2', '0', '0', '7', '10', '0', '0', 'm');"
            );//277
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Deviled Ham', '730', '1825', '0', '0', '0', '0', '2', '0', '0', '7', '10', '0', '0', 'm');"
            );//278
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Canned Clams', '730', '1825', '0', '0', '0', '0', '2', '0', '0', '5', '7', '0', '0', 's');"
            );//279
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Hummus', '0', '0', '4', '5', '0', '0', '1', '0', '0', '0', '0', '0', '0', 'v');"
            );//280
            Log.e(getClass().getSimpleName(), "280 items entered into " + DBName);
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Fresh Coconut', '0', '7', '14', '21', '6', '8', '2', '0', '0', '0', '7', '6', '8', 'f');"
            );//281
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Packaged Coconut', '180', '365', '180', '365', '6', '8', '2', '120', '180', '180', '240', '6', '8', 'f');"
            );//282
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Coconut oil', '0', '365', '0', '0', '0', '0', '1', '0', '0', '0', '0', '0', '0', 'f');"
            );//283
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Frozen fruit', '0', '0', '0', '0', '6', '12', '2', '0', '0', '0', '0', '6', '8', 'f');"
            );//284
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Cut lemons/limes', '0', '0', '2', '3', '0', '0', '1', '0', '0', '0', '0', '0', '0', 'f');"
            );//285
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Lemon juice', '0', '0', '120', '180', '0', '0', '1', '0', '0', '0', '0', '0', '0', 'f');"
            );//286
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Fresh Lemonade', '0', '0', '0', '7', '0', '0', '1', '0', '0', '0', '0', '0', '0', 'f');"
            );//287
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Processed Lemonade', '0', '0', '0', '14', '0', '0', '2', '0', '0', '0', '7', '0', '0', 'f');"
            );//288
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Fresh Limes', '14', '28', '30', '60', '0', '0', '1', '0', '0', '0', '0', '0', '0', 'f');"
            );//289
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Lime juice', '0', '0', '0', '180', '0', '0', '2', '0', '0', '0', '180', '0', '0', 'f');"
            );//290 *once opened, lime juice goes bad 180 days later
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Processed Limeade', '0', '0', '0', '14', '0', '0', '2', '0', '0', '0', '7', '0', '0', 'f');"
            );//291 *once item is opened, limeade goes bad 7 days later
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Oranges', '14', '21', '30', '60', '0', '0', '2', '0', '0', '1', '2', '0', '0', 'f');"
            );//292 *whole/cut are the two states
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Pineapple', '2', '3', '4', '5', '0', '0', '2', '0', '0', '3', '4', '3', '5', 'f');"
            );//293 *whole/cute are two states not unopened/opened
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Figs', '2', '5', '5', '7', '6', '8', '1', '0', '0', '0', '0', '0', '0', 'f');"
            );//294
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Pumpkin(canned)', '365', '730', '365', '730', '0', '0', '2', '0', '0', '0', '7', '6', '8', 'v');"
            );//295
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Cut lemons/limes', '0', '0', '2', '3', '0', '0', '1', '0', '0', '0', '0', '0', '0', 'f');"
            );//296
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Bacon bits', '0', '0', '0', '180', '0', '6', '2', '0', '0', '0', '42', '0', '6', 'm');"
            );//297
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Green beans (fresh)', '1', '2', '7', '10', '0', '12', '1', '0', '0', '0', '0', '0', '0', 'v');"
            );//298
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Canned Vegetables (all kinds)', '365', '730', '0', '0', '0', '0', '2', '0', '0', '7', '10', '0', '0', 'v');"
            );//299
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Canned Soup (all kinds)', '365', '730', '0', '0', '0', '0', '2', '0', '0', '0', '7', '0', '0', 'v');"
            );//300
            Log.e(getClass().getSimpleName(), "300 items entered into " + DBName);
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Vanilla Pudding', '21', '28', '21', '28', '0', '0', '1', '0', '0', '0', '0', '0', '0', 'd');"
            );//301
            Log.e(getClass().getSimpleName(), "1 " + DBName);
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Chocolate Pudding', '21', '28', '21', '28', '0', '0', '1', '0', '0', '0', '0', '0', '0', 'd');"
            );//302
            Log.e(getClass().getSimpleName(), "1 " + DBName);
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Dry Pudding mix', '28', '35', '0', '0', '0', '0', '2', '0', '1', '5', '7', '0', '0', 'd');"
            );//303
            Log.e(getClass().getSimpleName(), "1 " + DBName);
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Homemade Pudding', '0', '1', '5', '7', '0', '0', '1', '0', '0', '0', '0', '0', '0', 'd');"
            );//304
            Log.e(getClass().getSimpleName(), "1 " + DBName);
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Sweetened Condensed Milk', '0', '365', '0', '0', '0', '0', '2', '0', '0', '14', '21', '0', '0', 'd');"
            );//305 *past printed date
            Log.e(getClass().getSimpleName(), "1 " + DBName);
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Powdered Milk', '730', '3650', '0', '0', '0', '0', '2', '0', '0', '4', '5', '0', '0', 'd');"
            );//306
            Log.e(getClass().getSimpleName(), "1 " + DBName);
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Margarine', '0', '0', '120', '150', '6', '8', '2', '0', '0', '30', '60', '6', '8', 'd');"
            );//307
            Log.e(getClass().getSimpleName(), "1 " + DBName);
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Ice Cream', '0', '0', '0', '0', '2', '3', '2', '0', '0', '0', '0', '1', '2', 'd');"
            );//308
            Log.e(getClass().getSimpleName(), "1 " + DBName);
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Sherbet', '0', '0', '0', '0', '3', '4', '2', '0', '0', '0', '0', '2', '3', 'f');"
            );//309
            Log.e(getClass().getSimpleName(), "1 " + DBName);
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Eggs (Hard boiled)', '0', '0', '0', '7', '0', '0', '2', '0', '0', '0', '5', '0', '0', 'd');"
            );//310 *unpeeled/peeled not unopened/opened
            Log.e(getClass().getSimpleName(), "1 " + DBName);
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Cheesecake (fresh)', '0', '0', '5', '6', '6', '8', '2', '0', '0', '5', '7', '6', '8', 'd');"
            );//312
            Log.e(getClass().getSimpleName(), "1 " + DBName);
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Cheesecake (frozen)', '0', '0', '5', '6', '6', '8', '2', '0', '0', '5', '7', '6', '8', 'd');"
            );//313
            Log.e(getClass().getSimpleName(), "1 " + DBName);
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Cheesecake (homemade)', '0', '0', '5', '6', '6', '8', '2', '0', '0', '5', '7', '6', '8', 'd');"
            );//314
            Log.e(getClass().getSimpleName(), "1 " + DBName);
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Oreos', '14', '21', '0', '0', '0', '0', '2', '3', '4', '0', '0', '0', '0', 'x');"
            );//315
            Log.e(getClass().getSimpleName(), "1 " + DBName);
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Pop Tarts', '180', '365', '0', '0', '0', '0', '2', '7', '14', '0', '0', '0', '0', 'x');"
            );//316
            Log.e(getClass().getSimpleName(), "1 " + DBName);
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Fruit pie', '0', '0', '2', '3', '6', '8', '1', '0', '0', '0', '0', '0', '0', 'x');"
            );//317
            Log.e(getClass().getSimpleName(), "1 " + DBName);
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Pie (fresh fruit)', '0', '0', '1', '2', '0', '0', '1', '0', '0', '0', '0', '0', '0', 'x');"
            );//318
            Log.e(getClass().getSimpleName(), "1 " + DBName);
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Cream pie', '0', '0', '2', '3', '0', '6', '1', '0', '0', '0', '0', '0', '0', 'x');"
            );//319
            Log.e(getClass().getSimpleName(), "1 " + DBName);
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Custard pie', '0', '0', '2', '3', '0', '0', '1', '0', '0', '0', '0', '0', '0', 'x');"
            );//320
            Log.e(getClass().getSimpleName(), "1 " + DBName);
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Pumpkin pie', '0', '0', '2', '4', '6', '8', '1', '0', '0', '0', '0', '0', '0', 'x');"
            );//321
            Log.e(getClass().getSimpleName(), "1 " + DBName);
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Quiche', '0', '0', '2', '4', '6', '8', '1', '0', '0', '0', '0', '0', '0', 'x');"
            );//322
            Log.e(getClass().getSimpleName(), "1 " + DBName);
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Pot pie', '0', '0', '2', '4', '6', '8', '1', '0', '0', '0', '0', '0', '0', 'x');"
            );//323
            Log.e(getClass().getSimpleName(), "1 " + DBName);
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Bakery cookies', '2', '3', '0', '0', '4', '5', '1', '0', '0', '0', '0', '0', '0', 'x');"
            );//324
            Log.e(getClass().getSimpleName(), "1 " + DBName);
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Packaged cookies (soft)', '30', '60', '0', '0', '4', '5', '2', '7', '10', '0', '0', '4', '5', 'x');"
            );//325
            Log.e(getClass().getSimpleName(), "1 " + DBName);
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Packaged cookies (hard)', '30', '60', '0', '0', '4', '5', '2', '14', '21', '0', '0', '4', '5', 'x');"
            );//326
            Log.e(getClass().getSimpleName(), "1 " + DBName);
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Homemade cookies (soft)', '2', '3', '0', '0', '4', '5', '1', '0', '0', '0', '0', '0', '0', 'x');"
            );//327
            Log.e(getClass().getSimpleName(), "1 " + DBName);
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Homemade cookies (hard)', '14', '21', '0', '0', '4', '5', '1', '0', '0', '0', '0', '0', '0', 'x');"
            );//328
            Log.e(getClass().getSimpleName(), "1 " + DBName);
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Bakery cupcakes', '1', '2', '2', '4', '2', '4', '1', '0', '0', '0', '0', '0', '0', 'x');"
            );//329
            Log.e(getClass().getSimpleName(), "1 " + DBName);
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Cupcakes with fresh fruit', '0', '1', '1', '2', '2', '4', '1', '0', '0', '0', '0', '0', '0', 'x');"
            );//330
            Log.e(getClass().getSimpleName(), "330 items entered into " + DBName);
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Cupcakes with whipped cream', '0', '0', '1', '2', '0', '0', '1', '0', '0', '0', '0', '0', '0', 'x');"
            );//331
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Cupcakes with cream cheese', '1', '2', '2', '3', '2', '4', '1', '0', '0', '0', '0', '0', '0', 'x');"
            );//332
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Hostess cupcakes', '7', '14', '30', '60', '0', '0', '1', '0', '0', '0', '0', '0', '0', 'x');"
            );//333
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Homemade cupcakes', '1', '2', '2', '4', '2', '4', '1', '0', '0', '0', '0', '0', '0', 'x');"
            );//334
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Bakery cake', '1', '2', '2', '4', '2', '4', '1', '0', '0', '0', '0', '0', '0', 'x');"
            );//335
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Cake with fresh fruit', '0', '0', '1', '2', '2', '4', '1', '0', '0', '0', '0', '0', '0', 'x');"
            );//336
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Cake with whipped cream', '0', '0', '1', '2', '0', '0', '1', '0', '0', '0', '0', '0', '0', 'x');"
            );//337
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Fruit cake', '0', '0', '0', '7300', '0', '0', '1', '0', '0', '0', '0', '0', '0', 'x');"
            );//338
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Flour tortillas', '0', '7', '21', '28', '6', '8', '1', '0', '0', '0', '0', '0', '0', 'r');"
            );//339
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Corn tortillas', '7', '10', '180', '240', '6', '8', '1', '0', '0', '0', '0', '0', '0', 'r');"
            );//340
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Spinach tortillas', '0', '7', '21', '28', '6', '8', '1', '0', '0', '0', '0', '0', '0', 'r');"
            );//341
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Whole wheat tortillas', '0', '7', '21', '28', '6', '8', '1', '0', '0', '0', '0', '0', '0', 'r');"
            );//342
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Homemade tortillas', '2', '3', '5', '7', '6', '8', '1', '0', '0', '0', '0', '0', '0', 'r');"
            );//343
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('White Quinoa', '730', '1095', '730', '1095', '0', '0', '1', '0', '0', '0', '0', '0', '0', 'g');"
            );//344
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Red Quinoa', '730', '1095', '730', '1095', '0', '0', '1', '0', '0', '0', '0', '0', '0', 'g');"
            );//345
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Black Quinoa', '730', '1095', '730', '1095', '0', '0', '1', '0', '0', '0', '0', '0', '0', 'g');"
            );//346
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Packaged dry stuffing', '0', '180', '0', '0', '0', '0', '2', '0', '0', '4', '6', '4', '6', 'g');"
            );//347
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Stove Top stuffing', '0', '180', '0', '0', '0', '0', '2', '0', '0', '4', '6', '4', '6', 'g');"
            );//348
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Homemade stuffing', '0', '0', '4', '6', '4', '6', '1', '0', '0', '0', '0', '0', '0', 'g');"
            );//349
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Deli lasagna', '0', '0', '5', '7', '6', '8', '1', '0', '0', '0', '0', '0', '0', 'n');"
            );//350
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Frozen lasagna', '0', '0', '5', '7', '6', '8', '1', '0', '0', '0', '0', '0', '0', 'n');"
            );//351
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Homemade lasagna', '0', '0', '5', '7', '6', '8', '1', '0', '0', '0', '0', '0', '0', 'n');"
            );//352
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Pasta', '365', '730', '0', '0', '0', '0', '2', '0', '0', '4', '5', '6', '8', 'g');"
            );//353 *dry/cooked
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Noodles', '365', '730', '0', '0', '0', '0', '2', '0', '0', '4', '5', '6', '8', 'g');"
            );//353 *dry/cooked not unopened/opened
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Fresh Pasta', '0', '0', '4', '5', '6', '8', '1', '0', '0', '0', '0', '0', '0', 'g');"
            );//354
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Restaurant pizza', '0', '1', '3', '5', '4', '6', '1', '0', '0', '0', '0', '0', '0', 'p');"
            );//355
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Frozen pizza', '0', '1', '3', '5', '4', '6', '1', '0', '0', '0', '0', '0', '0', 'n');"
            );//356
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Homemade pizza', '0', '0', '3', '5', '4', '6', '1', '0', '0', '0', '0', '0', '0', 'p');"
            );//357
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Cheerios', '180', '240', '0', '0', '0', '0', '2', '120', '180', '0', '0', '0', '0', 'g');"
            );//358
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Shredded Wheat', '180', '240', '0', '0', '0', '0', '2', '120', '180', '0', '0', '0', '0', 'g');"
            );//359
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Cereal', '180', '240', '0', '0', '0', '0', '2', '120', '180', '0', '0', '0', '0', 'g');"
            );//360
            Log.e(getClass().getSimpleName(), "360 items entered into " + DBName);
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Cooked cereal', '0', '0', '4', '5', '0', '0', '1', '0', '0', '0', '0', '0', '0', 'g');"
            );//361
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('White rice', '1460', '1825', '0', '0', '0', '0', '2', '0', '0', '5', '7', '6', '8', 'g');"
            );//362 *dry/cooked not unopened/opened
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Oxygen free white rice', '9125', '10950', '0', '0', '0', '10950', '2', '0', '0', '5', '7', '6', '8', 'g');"
            );//363
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Brown rice', '180', '240', '240', '365', '0', '0', '2', '0', '0', '4', '5', '6', '8', 'g');"
            );//364 *dry/cooked not unopened/opened
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Wild rice', '180', '240', '0', '0', '0', '0', '2', '0', '0', '5', '7', '6', '8', 'g');"
            );//365 *dry/cooked not unopened/opened
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Minute rice', '1460', '1825', '0', '0', '0', '0', '2', '0', '0', '5', '7', '6', '8', 'g');"
            );//366 *dry/cooked not unopened/opened
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Bakery bread', '2', '3', '0', '0', '0', '6', '2', '2', '3', '0', '0', '0', '6', 'g');"
            );//367
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Packaged bagels(soft)', '5', '7', '0', '0', '0', '6', '2', '5', '7', '0', '0', '0', '6', 'g');"
            );//368
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Bakery bagels', '2', '3', '0', '0', '0', '6', '2', '2', '3', '0', '0', '0', '6', 'g');"
            );//369
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Packaged bread(soft)', '5', '7', '0', '0', '0', '6', '2', '5', '7', '0', '0', '0', '6', 'g');"
            );//370
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Cooked French toast', '5', '7', '0', '0', '6', '8', '2', '5', '7', '0', '0', '6', '8', 'g');"
            );//371
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Bread crumbs', '150', '180', '0', '0', '6', '8', '2', '150', '180', '0', '0', '6', '8', 'g');"
            );//372
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Croutons', '150', '180', '0', '0', '0', '0', '2', '150', '180', '0', '0', '0', '0', 'g');"
            );//373
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Salsa', '30', '60', '30', '60', '0', '0', '2', '0', '0', '7', '14', '0', '0', 'v');"
            );//374
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Beef jerky', '365', '730', '0', '0', '0', '0', '1', '0', '0', '7', '14', '0', '0', 'm');"
            );//375
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Turkey jerky', '365', '730', '0', '0', '0', '0', '1', '0', '0', '7', '14', '0', '0', 'm');"
            );//376
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Homemade beef jerky', '60', '90', '0', '0', '0', '0', '1', '0', '0', '7', '14', '0', '0', 'm');"
            );//377
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Sunflower seeds, raw', '60', '90', '0', '365', '0', '0', '1', '0', '12', '7', '14', '0', '0', 'e');"
            );//378
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Sunflower seeds, roasted shelled', '90', '120', '0', '365', '0', '12', '1', '0', '0', '7', '14', '0', '0', 'e');"
            );//379
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Sunflower seeds, roasted in shell', '120', '150', '0', '365', '0', '0', '1', '0', '0', '7', '14', '0', '0', 'e');"
            );//380
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Sun butter', '0', '180', '0', '0', '0', '0', '2', '0', '0', '0', '180', '0', '6', 'e');"
            );//381
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Sesame seeds, raw', '180', '365', '0', '365', '0', '12', '1', '0', '0', '0', '180', '0', '6', 'e');"
            );//382
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Roasted sesame seeds', '365', '1095', '365', '1095', '12', '36', '1', '0', '0', '0', '180', '0', '6', 'e');"
            );//383
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Tahini', '120', '180', '180', '365', '6', '12', '1', '0', '0', '0', '180', '0', '6', 'e');"
            );//384
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Tahini paste', '120', '180', '180', '365', '6', '12', '1', '0', '0', '0', '180', '0', '6', 'e');"
            );//385
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Homemade turkey soup', '0', '0', '5', '6', '6', '8', '1', '0', '0', '0', '180', '0', '6', 'm');"
            );//386
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Turkey salad', '0', '0', '5', '6', '0', '0', '1', '0', '0', '0', '180', '0', '6', 'm');"
            );//387
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Roasted chicken', '0', '0', '0', '7', '6', '8', '1', '0', '0', '0', '7', '0', '12', 'm');"
            );//388
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Tofu', '0', '0', '3', '5', '3', '5', '2', '0', '0', '3', '5', '3', '5', 'm');"
            );//390 opened fridge date is from date is opened regardless
            Log.e(getClass().getSimpleName(), "390 items entered into " + DBName);
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Sushi', '0', '0', '0', '1', '0', '0', '1', '0', '0', '0', '7', '0', '12', 's');"
            );//391
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Sashimi', '0', '0', '0', '1', '0', '0', '1', '0', '0', '0', '7', '0', '12', 's');"
            );//392
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Poke', '0', '0', '0', '1', '0', '0', '1', '0', '0', '0', '7', '0', '12', 's');"
            );//393
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Fresh shrimp', '0', '0', '1', '2', '6', '8', '2', '0', '0', '5', '7', '6', '8', 's');"
            );//394
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Canned shrimp', '0', '0', '5', '7', '0', '0', '1', '0', '0', '5', '7', '6', '8', 's');"
            );//395
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Salmon', '0', '0', '1', '2', '6', '9', '2', '0', '0', '5', '6', '6', '9', 's');"
            );//396
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Cod', '0', '0', '1', '2', '6', '9', '2', '0', '0', '5', '6', '6', '9', 's');"
            );//397
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Halibut', '0', '0', '1', '2', '6', '9', '2', '0', '0', '5', '6', '6', '9', 's');"
            );//398
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Ono', '0', '0', '1', '2', '6', '9', '2', '0', '0', '5', '6', '6', '9', 's');"
            );//399
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Tilapia', '0', '0', '1', '2', '6', '9', '2', '0', '0', '5', '6', '6', '9', 's');"
            );//400
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Catfish', '0', '0', '1', '2', '6', '9', '2', '0', '0', '5', '6', '6', '9', 's');"
            );//401
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Smoked Salmon', '0', '0', '1', '2', '3', '6', '1', '0', '0', '5', '7', '6', '8', 's');"
            );//402
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Sardines', '270', '365', '5', '7', '0', '0', '1', '0', '0', '5', '7', '6', '8', 's');"
            );//403
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Smooth peanut butter', '0', '365', '0', '365', '0', '0', '2', '90', '120', '180', '240', '0', '0', 't');"
            );//404
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Crunchy peanut butter', '0', '365', '0', '365', '0', '0', '2', '90', '120', '180', '240', '0', '0', 't');"
            );//405
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Natural peanut butter', '0', '365', '0', '365', '0', '0', '2', '0', '0', '150', '180', '0', '0', 't');"
            );//406
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Homemade peanut butter', '0', '0', '90', '180', '0', '0', '1', '0', '0', '5', '7', '6', '8', 't');"
            );//407
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Almonds', '270', '365', '0', '365', '0', '24', '1', '0', '0', '5', '7', '6', '8', 't');"
            );//408
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Brazil nuts', '0', '270', '0', '365', '0', '12', '1', '0', '0', '5', '7', '6', '8', 't');"
            );//409
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Cashews', '180', '270', '0', '365', '0', '24', '1', '0', '0', '5', '7', '6', '8', 't');"
            );//410
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Hazelnuts', '120', '180', '0', '365', '0', '12', '1', '0', '0', '5', '7', '6', '8', 't');"
            );//411
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Macadamia nuts', '270', '365', '0', '365', '0', '24', '1', '0', '0', '5', '7', '6', '8', 't');"
            );//412
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Peanuts', '270', '365', '0', '365', '0', '24', '1', '0', '0', '5', '7', '6', '8', 't');"
            );//413
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Pecans', '0', '180', '0', '365', '0', '24', '1', '0', '0', '5', '7', '6', '8', 't');"
            );//414
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Pine nuts', '30', '60', '90', '120', '5', '6', '1', '0', '0', '5', '7', '6', '8', 't');"
            );//415
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Pistachios', '0', '90', '0', '0', '0', '0', '1', '0', '0', '5', '7', '6', '8', 't');"
            );//416
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Walnuts', '0', '180', '0', '365', '12', '24', '1', '0', '0', '5', '7', '6', '8', 't');"
            );//417
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Ham', '0', '0', '7', '14', '6', '8', '2', '0', '0', '7', '10', '6', '8', 't');"
            );//418
            perishablePricesDb.execSQL("INSERT INTO " + tableName +
                    " Values ('Cooked bacon', '0', '0', '7', '10', '6', '8', '1', '0', '0', '7', '10', '6', '8', 't');"
            );//419
            Log.e(getClass().getSimpleName(), "418 items entered into " + DBName);

            Log.e(getClass().getSimpleName(), "the table has been made");



     //   }
    }

    private boolean checkDbExists()
    {
        String myPath =  DBPath + DBName;

		//SQLiteDatabase checkDB; // =  SQLiteDatabase.openOrCreateDatabase(myPath, null);//SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READWRITE);//null;

		try
		{
			//String myPath;//currentContext.getFilesDir().getAbsolutePath().replace("files", "databases") + File.separator + DBName;
			if(android.os.Build.VERSION.SDK_INT >= 4.2)
			{
				// currentContext.getFilesDir().getPath()+ "/"
				myPath =  DBPath + DBName;//currentContext.getApplicationContext().getPackageName() +"/databases/"+ DBName;
			}
			else
			{
				myPath = currentContext.getFilesDir().getPath() + DBName + "/databases/" + DBName;
			}

		}
		catch (SQLiteException e)
		{
			Log.e(getClass().getSimpleName(), "WTF is going on!");
		}

		//checkDB = SQLiteDatabase.openOrCreateDatabase(myPath, null);//SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
        //SQLiteDatabase.cre
        /*if(checkDB == null)
		{
			//checkDB.close();
			Log.i(getClass().getSimpleName(), "DOES THIS FIRE");
			return checkDB != null;
        }
        else
        {
            Log.i(getClass().getSimpleName(), "CHECKDB IS NULL");
        }*/

        File dbfile = new File(myPath);
        //checkDB = SQLiteDatabase.openOrCreateDatabase(dbfile);
		Log.e(getClass().getSimpleName(), "dbfile: " + dbfile.toString());
		return dbfile.exists();
    }
}
