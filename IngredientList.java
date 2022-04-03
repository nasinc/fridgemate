package mycompanyname.foodtracker;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.File;

public class IngredientList extends SQLiteOpenHelper {

    public SQLiteDatabase ingredsListDb;
    public String dbPath;
    public static String DBName = "ingreds.db";
    public static final int version = '1';
    public static Context currentContext;
    public static String tableName = "ingredsList";

    public IngredientList(Context context) {
        super(context, DBName, null, version);
        currentContext = context;
        dbPath = currentContext.getApplicationInfo().dataDir + "/databases/";

        String myPath = dbPath + DBName;
        File dbFile = new File(myPath);

        Log.e(getClass().getSimpleName(), "before checking if ingredientslist table exists");

        boolean dbExists = checkDbExists();
        if (dbExists) {
            //Toast.makeText(context, "database exists", Toast.LENGTH_LONG).show();
            Log.e(getClass().getSimpleName(), "do nothing, database exists: " + dbExists + ", dbfile: " + dbFile.length());
            //ingredsListDb.close();
            /*if(dbFile.length() != 12288)
            {
                dbFile.delete();
                createDatabase();
            }*/
            //
            //Log.e(getClass().getSimpleName(), "do nothing, database exists");
        } else {
            //Toast.makeText(context, "this SGDMF doesn't exist", Toast.LENGTH_LONG).show()
            Log.e(getClass().getSimpleName(), "this SGDMF doesn't exist");
            //
            createDatabase();
            Log.e(getClass().getSimpleName(), "this SGDMF doesn't exist");
        }
    }
    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    private boolean checkDbExists()
    {
        //SQLiteDatabase checkDB = null;

        try
        {
            String myPath;//currentContext.getFilesDir().getAbsolutePath().replace("files", "databases") + File.separator + DBName;
            if(android.os.Build.VERSION.SDK_INT >= 4.2)
            {
                // currentContext.getFilesDir().getPath()+ "/"
                myPath =  dbPath + DBName;//currentContext.getApplicationContext().getPackageName() +"/databases/"+ DBName;
            }
            else
            {
                myPath = currentContext.getFilesDir().getPath() + DBName + "/databases/" + DBName;
            }
          //  checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
        }
        catch (SQLiteException e)
        {
            Log.e(getClass().getSimpleName(), "WTF is going on!");
        }

        /*if(checkDB != null)
        {
            checkDB.close();


            return checkDB != null ? true : false;
        }
*/

        File dbfile = new File(dbPath + DBName);
        return dbfile.exists();
    }

    private void createDatabase() {
        ingredsListDb = currentContext.openOrCreateDatabase(DBName, 0, null);
        ingredsListDb.execSQL("CREATE TABLE IF NOT EXISTS " +
                tableName +
                "(RecipeName VARCHAR, " +
                "Ingredients TEXT);");

    }

    public void add(String recipe, String ingreds) {
        Log.e(getClass().getSimpleName(), "recipe: " + recipe + "\ningreds: " + ingreds);

        ingredsListDb.execSQL("INSERT INTO " + tableName +
        "(RecipeName, Ingredients) VALUES ('" + recipe + "', '" + ingreds + "');");
    }
}
