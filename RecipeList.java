package mycompanyname.foodtracker;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.File;

/**
 * Created by Nicholas on 9/5/2018.
 */

public class RecipeList  extends SQLiteOpenHelper {

    public SQLiteDatabase recipesListDb;
    public String dbPath;
    public static String DBName = "recipes.db";
    public static final int version = '1';
    public static Context currentContext;
    public static String tableName = "RecipeTable";

    public RecipeList(Context context)
    {
        super(context, DBName, null, version);
        currentContext = context;
        dbPath = currentContext.getApplicationInfo().dataDir +  "/databases/";

        String myPath = dbPath + DBName;
        File dbFile = new File(myPath);


        boolean dbExists = checkDbExists();
        if(dbExists)
        {
            //Toast.makeText(context, "database exists", Toast.LENGTH_LONG).show();
            Log.e(getClass().getSimpleName(), "do nothing, database exists: " + dbExists + ", dbfile: " + dbFile.length());

            /*if(dbFile.length() != 12288)
            {
                dbFile.delete();
                createDatabase();
            }*/
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

       /* if(checkDB != null)
        {
            checkDB.close();


            return checkDB != null ? true : false;
        }
*/
        File dbfile = new File(dbPath + DBName);
        return dbfile.exists();
    }


    /**
     * This function does not work in it's current state.  I reached the limit of the function of 64Kb.  I need to come up with
     * a solution where the table is all ready part of the package and all I do is call to load the table into the app.
     * Look up way to include sqlite table in apk then call said table
     *
     * */
    private void createDatabase() {
        /*perishablePricesDb = currentContext.openOrCreateDatabase(DBName, 0, null);
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
        );*/
        recipesListDb = currentContext.openOrCreateDatabase(DBName, 0, null);
        recipesListDb.execSQL("CREATE TABLE IF NOT EXISTS " +
                tableName +
                "(RecipeName VARCHAR, " +
                "mainIngredient VARCHAR, " +
                "website VARCHAR, " +
                "Ingredients TEXT);");

        //3.1 migrated all current recipes to database that will be included in the APK
        //addFirstGroupRecipes();
    }

    public void addFirstGroupRecipes()
    {
        addRecipeToTable("Jerk Chicken with Pineapple Black Bean Salsa", "chicken",
                "https://www.budgetbytes.com/jerk-chicken-with-pineapple-black-bean-salsa/",
        "rice, pineapple tidbits, black beans, red onion, cilantro, lime, red pepper flakes, salt, chicken, jerk seasoning, oil");
        addRecipeToTable("Jerk Chicken with Pineapple Black Bean Salsa", "pineapple",
                "https://www.budgetbytes.com/jerk-chicken-with-pineapple-black-bean-salsa/",
                "rice, pineapple tidbits, black beans, red onion, cilantro, lime, red pepper flakes, salt, chicken, jerk seasoning, oil");
        addRecipeToTable("Jerk Chicken with Pineapple Black Bean Salsa", "black beans",
                "https://www.budgetbytes.com/jerk-chicken-with-pineapple-black-bean-salsa/",
                "rice, pineapple tidbits, black beans, red onion, cilantro, lime, red pepper flakes, salt, chicken, jerk seasoning, oil");
        addRecipeToTable("Jerk Chicken with Pineapple Black Bean Salsa", "lime",
                "https://www.budgetbytes.com/jerk-chicken-with-pineapple-black-bean-salsa/",
                "rice, pineapple tidbits, black beans, red onion, cilantro, lime, red pepper flakes, salt, chicken, jerk seasoning, oil");
        addRecipeToTable("Creamy Salsa Chicken Skillet", "chicken",
                "https://www.budgetbytes.com/creamy-salsa-chicken-skillet/",
                "chicken, chili powder, cumin, garlic powder, salt, cooking oil, salsa, black beans, corn, sour cream, queso fresco, rice");
        addRecipeToTable("Creamy Salsa Chicken Skillet", "queso fresco",
                "https://www.budgetbytes.com/creamy-salsa-chicken-skillet/",
                "chicken, chili powder, cumin, garlic powder, salt, cooking oil, salsa, black beans, corn, sour cream, queso fresco, rice");
        addRecipeToTable("Creamy Salsa Chicken Skillet", "sour cream",
                "https://www.budgetbytes.com/creamy-salsa-chicken-skillet/",
                "chicken, chili powder, cumin, garlic powder, salt, cooking oil, salsa, black beans, corn, sour cream, queso fresco, rice");
        addRecipeToTable("Chorizo Stuffed Bell Peppers", "chorizo",
                "https://www.budgetbytes.com/chorizo-stuffed-bell-peppers/",
                "chorizo, rice, diced tomatoes, black beans, chili powder, salt, cheese, green onion, bell pepper");
        addRecipeToTable("Chorizo Stuffed Bell Peppers", "cheddar cheese",
                "https://www.budgetbytes.com/chorizo-stuffed-bell-peppers/",
                "chorizo, rice, diced tomatoes, black beans, chili powder, salt, cheese, green onion, bell pepper");
        addRecipeToTable("Chorizo Stuffed Bell Peppers", "bell pepper",
                "https://www.budgetbytes.com/chorizo-stuffed-bell-peppers/",
                "chorizo, rice, diced tomatoes, black beans, chili powder, salt, cheese, green onion, bell pepper");
        addRecipeToTable("Chorizo Stuffed Bell Peppers", "black beans",
                "https://www.budgetbytes.com/chorizo-stuffed-bell-peppers/",
                "chorizo, rice, diced tomatoes, black beans, chili powder, salt, cheese, green onion, bell pepper");
        addRecipeToTable("Enchilada Bubble Up Casserole", "chicken",
                "https://www.budgetbytes.com/enchilada-bubble-casserole/",
                "oil, flour, chili powder, tomato paste, cumin, garlic powder, cayenne pepper, salt, active dry yeast, baking powder, sugar, heavy cream, chicken, black beans, cheese, green onion");
        addRecipeToTable("Enchilada Bubble Up Casserole", "black beans",
                "https://www.budgetbytes.com/enchilada-bubble-casserole/",
                "oil, flour, chili powder, tomato paste, cumin, garlic powder, cayenne pepper, salt, active dry yeast, baking powder, sugar, heavy cream, chicken, black beans, cheese, green onion");
        addRecipeToTable("Enchilada Bubble Up Casserole", "cheddar cheese",
                "https://www.budgetbytes.com/enchilada-bubble-casserole/",
                "oil, flour, chili powder, tomato paste, cumin, garlic powder, cayenne pepper, salt, active dry yeast, baking powder, sugar, heavy cream, chicken, black beans, cheese, green onion");
        addRecipeToTable("Loaded Mashed Potato Bowl", "potatoes",
                "https://www.budgetbytes.com/loaded-mashed-potato-bowls/",
                "mashed potatoes, corn, black beans, chili powder, cheddar cheese, BBQ sauce, green onion, jalapeno");
        addRecipeToTable("Loaded Mashed Potato Bowl", "potatoes",
                "https://www.budgetbytes.com/loaded-mashed-potato-bowls/",
                "mashed potatoes, corn, black beans, chili powder, cheddar cheese, BBQ sauce, green onion, jalapeno");
        addRecipeToTable("Loaded Mashed Potato Bowl", "corn",
                "https://www.budgetbytes.com/loaded-mashed-potato-bowls/",
                "mashed potatoes, corn, black beans, chili powder, cheddar cheese, BBQ sauce, green onion, jalapeno");
        addRecipeToTable("Loaded Mashed Potato Bowl", "black beans",
                "https://www.budgetbytes.com/loaded-mashed-potato-bowls/",
                "mashed potatoes, corn, black beans, chili powder, cheddar cheese, BBQ sauce, green onion, jalapeno");
        addRecipeToTable("Loaded Mashed Potato Bowl", "cheddar cheese",
                "https://www.budgetbytes.com/loaded-mashed-potato-bowls/",
                "mashed potatoes, corn, black beans, chili powder, cheddar cheese, BBQ sauce, green onion, jalapeno");
        addRecipeToTable("Loaded Mashed Potato Bowl", "jalapeno",
                "https://www.budgetbytes.com/loaded-mashed-potato-bowls/",
                "mashed potatoes, corn, black beans, chili powder, cheddar cheese, BBQ sauce, green onion, jalapeno");
        addRecipeToTable("Soy Marinated Tofu Bowls with Spicy Peanut Sauce", "tofu",
                "https://www.budgetbytes.com/soy-marinated-tofu-bowls-spicy-peanut-sauce/",
                "tofu, oil, soy sauce, garlic, brown sugar, brown rice, salt, toasted sesame oil, creamy peanut butter, sriracha sauce, ginger, sugar snap peas, green onion, sesame seeds");
        addRecipeToTable("Soy Marinated Tofu Bowls with Spicy Peanut Sauce", "sugar snap peas",
                "https://www.budgetbytes.com/soy-marinated-tofu-bowls-spicy-peanut-sauce/",
                "tofu, oil, soy sauce, garlic, brown sugar, brown rice, salt, toasted sesame oil, creamy peanut butter, sriracha sauce, ginger, sugar snap peas, green onion, sesame seeds");
        addRecipeToTable("Easy Cheesy Broccoli Rice", "broccoli",
                "https://www.budgetbytes.com/easy-cheesy-broccoli-rice/",
                "broccoli, rice, butter, salt, garlic powder, cayenne pepper, black pepper, parmesan cheese, sharp cheddar cheese");
        addRecipeToTable("Easy Cheesy Broccoli Rice", "parmesan cheese",
                "https://www.budgetbytes.com/easy-cheesy-broccoli-rice/",
                "broccoli, rice, butter, salt, garlic powder, cayenne pepper, black pepper, parmesan cheese, sharp cheddar cheese");
        addRecipeToTable("Easy Cheesy Broccoli Rice", "butter",
                "https://www.budgetbytes.com/easy-cheesy-broccoli-rice/",
                "broccoli, rice, butter, salt, garlic powder, cayenne pepper, black pepper, parmesan cheese, sharp cheddar cheese");
        addRecipeToTable("Easy Cheesy Broccoli Rice", "cheddar cheese",
                "https://www.budgetbytes.com/easy-cheesy-broccoli-rice/",
                "broccoli, rice, butter, salt, garlic powder, cayenne pepper, black pepper, parmesan cheese, sharp cheddar cheese");
/*        addRecipeToTable("", "",
                "",
                "");
        addRecipeToTable("", "",
                "",
                "");
        addRecipeToTable("", "",
                "",
                "");

        addRecipeToTable("", "",
                "",
                "");
        addRecipeToTable("", "",
                "",
                "");
        addRecipeToTable("", "",
                "",
                "");
        addRecipeToTable("", "",
                "",
                "");
        addRecipeToTable("", "",
                "",
                "");
        addRecipeToTable("", "",
                "",
                "");
        addRecipeToTable("", "",
                "",
                "");
        addRecipeToTable("", "",
                "",
                "");
        addRecipeToTable("", "",
                "",
                "");
        addRecipeToTable("", "",
                "",
                "");
        addRecipeToTable("", "",
                "",
                "");
        addRecipeToTable("", "",
                "",
                "");
        addRecipeToTable("", "",
                "",
                "");
        addRecipeToTable("", "",
                "",
                "");
        addRecipeToTable("", "",
                "",
                "");
        addRecipeToTable("", "",
                "",
                "");
        addRecipeToTable("", "",
                "",
                "");
        addRecipeToTable("", "",
                "",
                "");
        addRecipeToTable("", "",
                "",
                "");
        addRecipeToTable("", "",
                "",
                "");
        addRecipeToTable("", "",
                "",
                "");
*/
    }
    public void addRecipeToTable(String recipe, String mainIng, String website, String ingredients)
    {
        recipesListDb.execSQL("INSERT INTO " + tableName + " Values ('" + recipe + ", " + mainIng + ", " + website + ", " + ingredients +  "');");
    }


     /*
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Enchilada Bubble Up Casserole', 'chicken', 'https://www.budgetbytes.com/enchilada-bubble-casserole/', " +
                "'oil, flour, chili powder, tomato paste, cumin, garlic powder, cayenne pepper, salt, active dry yeast, baking powder, sugar, heavy cream, chicken, black beans, cheese, green onion');"
        );//4
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Enchilada Bubble Up Casserole', 'black beans', 'https://www.budgetbytes.com/enchilada-bubble-casserole/', " +
                "'oil, flour, chili powder, tomato paste, cumin, garlic powder, cayenne pepper, salt, active dry yeast, baking powder, sugar, heavy cream, chicken, black beans, cheese, green onion');"
        );//4
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Enchilada Bubble Up Casserole', 'heavy cream', 'https://www.budgetbytes.com/enchilada-bubble-casserole/', " +
                "'oil, flour, chili powder, tomato paste, cumin, garlic powder, cayenne pepper, salt, active dry yeast, baking powder, sugar, heavy cream, chicken, black beans, cheese, green onion');"
        );//4
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Enchilada Bubble Up Casserole', 'cheddar cheese', 'https://www.budgetbytes.com/enchilada-bubble-casserole/', " +
                "'oil, flour, chili powder, tomato paste, cumin, garlic powder, cayenne pepper, salt, active dry yeast, baking powder, sugar, heavy cream, chicken, black beans, cheese, green onion');"
        );//4
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Loaded Mashed Potato Bowl', 'potatoes', 'https://www.budgetbytes.com/loaded-mashed-potato-bowls/', " +
                "'mashed potatoes, corn, black beans, chili powder, cheddar cheese, BBQ sauce, green onion, jalapeno');"
        );//5
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Loaded Mashed Potato Bowl', 'jalapeno', 'https://www.budgetbytes.com/loaded-mashed-potato-bowls/', " +
                "'mashed potatoes, corn, black beans, chili powder, cheddar cheese, BBQ sauce, green onion, jalapeno');"
        );//5
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Loaded Mashed Potato Bowl', 'corn', 'https://www.budgetbytes.com/loaded-mashed-potato-bowls/', " +
                "'mashed potatoes, corn, black beans, chili powder, cheddar cheese, BBQ sauce, green onion, jalapeno');"
        );//5
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Loaded Mashed Potato Bowl', 'black beans', 'https://www.budgetbytes.com/loaded-mashed-potato-bowls/', " +
                "'mashed potatoes, corn, black beans, chili powder, cheddar cheese, BBQ sauce, green onion, jalapeno');"
        );//5
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Soy Marinated Tofu Bowls with Spicy Peanut Sauce', 'tofu', 'https://www.budgetbytes.com/soy-marinated-tofu-bowls-spicy-peanut-sauce/', " +
                "'tofu, oil, soy sauce, garlic, brown sugar, brown rice, salt, toasted sesame oil, creamy peanut butter, sriracha sauce, ginger, sugar snap peas, green onion, sesame seeds');"
        );//6
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Easy Cheesy Broccoli Rice', 'broccoli', 'https://www.budgetbytes.com/easy-cheesy-broccoli-rice/', " +
                "'broccoli, rice, butter, salt, garlic powder, cayenne pepper, black pepper, parmesan cheese, sharp cheddar cheese');"
        );//7
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Italian Sausage and White Bean Skillet', 'italian sausage', 'https://www.budgetbytes.com/italian-sausage-white-bean-skillet/', " +
                "'Italian sausage, olive oil, cannellini beans, spinach, black pepper');"
        );//8
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Roasted Sweet Potato Rainbow Salad w/ Lime Crema', 'sweet potato', 'https://www.budgetbytes.com/roasted-sweet-potato-rainbow-salad-lime-crema/', " +
                "'sour cream, lime, garlic, salt, sweet potato, oil, cumin, corn, black beans, bell pepper, red onion, cilantro');"
        );//9
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Roasted Sweet Potato Rainbow Salad w/ Lime Crema', 'sour cream', 'https://www.budgetbytes.com/roasted-sweet-potato-rainbow-salad-lime-crema/', " +
                "'sour cream, lime, garlic, salt, sweet potato, oil, cumin, corn, black beans, bell pepper, red onion, cilantro');"
        );//9
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Roasted Sweet Potato Rainbow Salad w/ Lime Crema', 'lime', 'https://www.budgetbytes.com/roasted-sweet-potato-rainbow-salad-lime-crema/', " +
                "'sour cream, lime, garlic, salt, sweet potato, oil, cumin, corn, black beans, bell pepper, red onion, cilantro');"
        );//9
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Roasted Sweet Potato Rainbow Salad w/ Lime Crema', 'bell pepper', 'https://www.budgetbytes.com/roasted-sweet-potato-rainbow-salad-lime-crema/', " +
                "'sour cream, lime, garlic, salt, sweet potato, oil, cumin, corn, black beans, bell pepper, red onion, cilantro');"
        );//9
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Roasted Sweet Potato Rainbow Salad w/ Lime Crema', 'onion', 'https://www.budgetbytes.com/roasted-sweet-potato-rainbow-salad-lime-crema/', " +
                "'sour cream, lime, garlic, salt, sweet potato, oil, cumin, corn, black beans, bell pepper, red onion, cilantro');"
        );//9
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Cheesy Chicken Vegetable and Rice Casserole', 'chicken', 'https://www.budgetbytes.com/cheesy-chicken-vegetable-rice-casserole/', " +
                "'broccoli, chicken, oil, salt, pepper, yellow onion, carrot, rice, butter, flour, chicken broth, whole milk, paprika, cheddar cheese');"
        );//10
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Cheesy Chicken Vegetable and Rice Casserole', 'broccoli', 'https://www.budgetbytes.com/cheesy-chicken-vegetable-rice-casserole/', " +
                "'broccoli, chicken, oil, salt, pepper, yellow onion, carrot, rice, butter, flour, chicken broth, whole milk, paprika, cheddar cheese');"
        );//10
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Cheesy Chicken Vegetable and Rice Casserole', 'carrot', 'https://www.budgetbytes.com/cheesy-chicken-vegetable-rice-casserole/', " +
                "'broccoli, chicken, oil, salt, pepper, yellow onion, carrot, rice, butter, flour, chicken broth, whole milk, paprika, cheddar cheese');"
        );//10
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Cheesy Chicken Vegetable and Rice Casserole', 'onion', 'https://www.budgetbytes.com/cheesy-chicken-vegetable-rice-casserole/', " +
                "'broccoli, chicken, oil, salt, pepper, yellow onion, carrot, rice, butter, flour, chicken broth, whole milk, paprika, cheddar cheese');"
        );//10
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Cheesy Chicken Vegetable and Rice Casserole', 'milk', 'https://www.budgetbytes.com/cheesy-chicken-vegetable-rice-casserole/', " +
                "'broccoli, chicken, oil, salt, pepper, yellow onion, carrot, rice, butter, flour, chicken broth, whole milk, paprika, cheddar cheese');"
        );//10
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Cheesy Chicken Vegetable and Rice Casserole', 'cheddar cheese', 'https://www.budgetbytes.com/cheesy-chicken-vegetable-rice-casserole/', " +
                "'broccoli, chicken, oil, salt, pepper, yellow onion, carrot, rice, butter, flour, chicken broth, whole milk, paprika, cheddar cheese');"
        );//10
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Beef Burrito Casserole', 'ground beef', 'https://www.budgetbytes.com/beef-burrito-casserole/', " +
                "'cooking oil, ground beef, garlic, salt, rice, black beans, salsa, green onion, chili powder, cream cheese, cheese');"
        );//11
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Beef Burrito Casserole', 'cream cheese', 'https://www.budgetbytes.com/beef-burrito-casserole/', " +
                "'cooking oil, ground beef, garlic, salt, rice, black beans, salsa, green onion, chili powder, cream cheese, cheese');"
        );//11
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Beef Burrito Casserole', 'cheddar cheese', 'https://www.budgetbytes.com/beef-burrito-casserole/', " +
                "'cooking oil, ground beef, garlic, salt, rice, black beans, salsa, green onion, chili powder, cream cheese, cheese');"
        );//11
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Chili Garlic Tofu Bowls', 'tofu', 'https://www.budgetbytes.com/chili-garlic-tofu-bowls/', " +
                "'kale, oil, garlic, soy sauce, toasted sesame oil, sesame seeds, tofu, chili garlic sauce, brown sugar, brown rice, cilantro, lime');"
        );//12
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Chili Garlic Tofu Bowls', 'lime', 'https://www.budgetbytes.com/chili-garlic-tofu-bowls/', " +
                "'kale, oil, garlic, soy sauce, toasted sesame oil, sesame seeds, tofu, chili garlic sauce, brown sugar, brown rice, cilantro, lime');"
        );//12
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Slow Cooker Jambalaya', 'polish sausage', 'https://www.budgetbytes.com/slow-cooker-jambalaya/', " +
                "'celery, onion, bell pepper, garlic, smoked sausage, chicken thighs, oregano, thyme, smoked paprika, cayenne, cracked pepper, diced tomatoes, chicken broth, white rice, green onion');"
        );//13
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Slow Cooker Jambalaya', 'celery', 'https://www.budgetbytes.com/slow-cooker-jambalaya/', " +
                "'celery, onion, bell pepper, garlic, smoked sausage, chicken thighs, oregano, thyme, smoked paprika, cayenne, cracked pepper, diced tomatoes, chicken broth, white rice, green onion');"
        );//13
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Slow Cooker Jambalaya', 'onion', 'https://www.budgetbytes.com/slow-cooker-jambalaya/', " +
                "'celery, onion, bell pepper, garlic, smoked sausage, chicken thighs, oregano, thyme, smoked paprika, cayenne, cracked pepper, diced tomatoes, chicken broth, white rice, green onion');"
        );//13
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Slow Cooker Jambalaya', 'bell pepper', 'https://www.budgetbytes.com/slow-cooker-jambalaya/', " +
                "'celery, onion, bell pepper, garlic, smoked sausage, chicken thighs, oregano, thyme, smoked paprika, cayenne, cracked pepper, diced tomatoes, chicken broth, white rice, green onion');"
        );//13
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Slow Cooker Jambalaya', 'chicken', 'https://www.budgetbytes.com/slow-cooker-jambalaya/', " +
                "'celery, onion, bell pepper, garlic, smoked sausage, chicken thighs, oregano, thyme, smoked paprika, cayenne, cracked pepper, diced tomatoes, chicken broth, white rice, green onion');"
        );//13
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Shrimp Fried Rice w/ Pineapple and Toasted Coconut', 'shrimp', 'https://www.budgetbytes.com/shrimp-fried-rice-pineapple-toasted-coconut/', " +
                "'soy sauce, sriracha, brown sugar, pineapple chunks, green onion, eggs, unsweetened coconut shreds, cooking oil, deveined shrimp, rice, cilantro');"
        );//14
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Ultimate Southwest Scrambled Eggs', 'eggs', 'https://www.budgetbytes.com/ultimate-southwest-scrambled-eggs/', " +
                "'eggs, milk, salt, pepper, butter, black beans, diced green chiles, taco sauce, cheese, onion, tomato');"
        );//15
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Ultimate Southwest Scrambled Eggs', 'milk', 'https://www.budgetbytes.com/ultimate-southwest-scrambled-eggs/', " +
                "'eggs, milk, salt, pepper, butter, black beans, diced green chiles, taco sauce, cheese, onion, tomato');"
        );//15
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Ultimate Southwest Scrambled Eggs', 'tomatoes', 'https://www.budgetbytes.com/ultimate-southwest-scrambled-eggs/', " +
                "'eggs, milk, salt, pepper, butter, black beans, diced green chiles, taco sauce, cheese, onion, tomato');"
        );//15
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Ultimate Southwest Scrambled Eggs', 'cheddar cheese', 'https://www.budgetbytes.com/ultimate-southwest-scrambled-eggs/', " +
                "'eggs, milk, salt, pepper, butter, black beans, diced green chiles, taco sauce, cheese, onion, tomato');"
        );//15
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Baked Beef and Black Bean Tacos', 'ground beef', 'https://www.budgetbytes.com/easy-cheesy-broccoli-rice/', " +
                "'chili powder, paprika, cumin, oregano, cayenne, salt, pepper, oil, onion, garlic, ground beef, black beans, taco shells, tomato, cheese, jalapeno, cilantro');"
        );//16
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Baked Beef and Black Bean Tacos', 'onion', 'https://www.budgetbytes.com/easy-cheesy-broccoli-rice/', " +
                "'chili powder, paprika, cumin, oregano, cayenne, salt, pepper, oil, onion, garlic, ground beef, black beans, taco shells, tomato, cheese, jalapeno, cilantro');"
        );//16
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Baked Beef and Black Bean Tacos', 'cheddar cheese', 'https://www.budgetbytes.com/easy-cheesy-broccoli-rice/', " +
                "'chili powder, paprika, cumin, oregano, cayenne, salt, pepper, oil, onion, garlic, ground beef, black beans, taco shells, tomato, cheese, jalapeno, cilantro');"
        );//16
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Baked Beef and Black Bean Tacos', 'tomato', 'https://www.budgetbytes.com/easy-cheesy-broccoli-rice/', " +
                "'chili powder, paprika, cumin, oregano, cayenne, salt, pepper, oil, onion, garlic, ground beef, black beans, taco shells, tomato, cheese, jalapeno, cilantro');"
        );//16
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Baked Beef and Black Bean Tacos', 'jalapeno', 'https://www.budgetbytes.com/easy-cheesy-broccoli-rice/', " +
                "'chili powder, paprika, cumin, oregano, cayenne, salt, pepper, oil, onion, garlic, ground beef, black beans, taco shells, tomato, cheese, jalapeno, cilantro');"
        );//16
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Pressure Cooked Chicken and Rice', 'chicken', 'https://www.budgetbytes.com/pressure-cooker-chicken-rice/', " +
                "'whole split chicken, seasoning blend, white rice, parsley');"
        );//17
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('BBQ Chicken Burrito Bowls', 'chicken', 'https://www.budgetbytes.com/bbq-chicken-burrito-bowls/', " +
                "'whole split chicken, seasoning blend, white rice, parsley');"
        );//18
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('BBQ Chicken Burrito Bowls', 'zucchini', 'https://www.budgetbytes.com/bbq-chicken-burrito-bowls/', " +
                "'whole split chicken, seasoning blend, white rice, parsley');"
        );//18
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('BBQ Chicken Burrito Bowls', 'avocado', 'https://www.budgetbytes.com/bbq-chicken-burrito-bowls/', " +
                "'whole split chicken, seasoning blend, white rice, parsley');"
        );//18
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('BBQ Chicken Burrito Bowls', 'pepper jack cheese', 'https://www.budgetbytes.com/bbq-chicken-burrito-bowls/', " +
                "'whole split chicken, seasoning blend, white rice, parsley');"
        );//18
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Hoisin Stir Fry Bowls with Spicy Peanut Sauce', 'chicken', 'https://www.budgetbytes.com/hoisin-stir-fry-bowls-spicy-peanut-sauce/', " +
                "'peanut butter, sriracha, soy sauce, ginger, brown sugar, oil, garlic, chicken, button mushrooms, carrot, bell pepper, hoisin sauce, sesame oil, rice, chopped peanuts, green onions');"
        );//19
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Hoisin Stir Fry Bowls with Spicy Peanut Sauce', 'pork', 'https://www.budgetbytes.com/hoisin-stir-fry-bowls-spicy-peanut-sauce/'," +
                "'peanut butter, sriracha, soy sauce, ginger, brown sugar, oil, garlic, pork, button mushrooms, carrot, bell pepper, hoisin sauce, sesame oil, rice, chopped peanuts, green onions');"
        );//20
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Hoisin Stir Fry Bowls with Spicy Peanut Sauce', 'turkey', 'https://www.budgetbytes.com/hoisin-stir-fry-bowls-spicy-peanut-sauce/'," +
                "'peanut butter, sriracha, soy sauce, ginger, brown sugar, oil, garlic, turkey, button mushrooms, carrot, bell pepper, hoisin sauce, sesame oil, rice, chopped peanuts, green onions');"
        );//21
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Sweet Chili Tofu Bowl', 'tofu', 'https://www.budgetbytes.com/sweet-chili-tofu-bowls/', " +
                "'firm tofu, salt, cornstarch, cooking oil, avocado, bell pepper, green onion, cilantro, sweet chili sauce, sesame seeds, rice');"
        );//22
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Sweet Chili Tofu Bowl', 'avocado', 'https://www.budgetbytes.com/sweet-chili-tofu-bowls/', " +
                "'firm tofu, salt, cornstarch, cooking oil, avocado, bell pepper, green onion, cilantro, sweet chili sauce, sesame seeds, rice');"
        );//22
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Sweet Chili Tofu Bowl', 'bell pepper', 'https://www.budgetbytes.com/sweet-chili-tofu-bowls/', " +
                "'firm tofu, salt, cornstarch, cooking oil, avocado, bell pepper, green onion, cilantro, sweet chili sauce, sesame seeds, rice');"
        );//22
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Pan Fried Sesame Tofu with Broccoli', 'broccoli', 'https://www.budgetbytes.com/pan-fried-sesame-tofu-with-broccoli/', " +
                "'soy sauce, toasted sesame oil, brown sugar, rice vinegar, ginger, garlic, sesame seeds, cornstarch, firm tofu, cooking oil, broccoli florets, green onions, rice');"
        );//23
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Pan Fried Sesame Tofu with Broccoli', 'tofu', 'https://www.budgetbytes.com/pan-fried-sesame-tofu-with-broccoli/', " +
                "'soy sauce, toasted sesame oil, brown sugar, rice vinegar, ginger, garlic, sesame seeds, cornstarch, firm tofu, cooking oil, broccoli florets, green onions, rice');"
        );//24
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Smoked Sausage Skillet with Peppers and Farro', 'polish sausage', 'https://www.budgetbytes.com/smoked-sausage-skillet-with-peppers-and-farro/', " +
                "'olive oil, smoked sausage, bell pepper, onion, diced tomatoes, oregano, pepper, farro, parsley');"
        );//25
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Smoked Sausage Skillet with Peppers and Farro', 'bell pepper', 'https://www.budgetbytes.com/smoked-sausage-skillet-with-peppers-and-farro/', " +
                "'olive oil, smoked sausage, bell pepper, onion, diced tomatoes, oregano, pepper, farro, parsley');"
        );//25
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Smoked Sausage Skillet with Peppers and Farro', 'onion', 'https://www.budgetbytes.com/smoked-sausage-skillet-with-peppers-and-farro/', " +
                "'olive oil, smoked sausage, bell pepper, onion, diced tomatoes, oregano, pepper, farro, parsley');"
        );//25
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Weeknight Enchiladas', 'avocado', 'https://www.budgetbytes.com/weeknight-enchiladas/', " +
                "'vegetable oil, chili powder, flour, tomato paste, cumin, garlic powder, cayenne pepper, salt, corn tortillas, refried beans, pepper jack cheese, avocado, cilantro');"
        );//26
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Weeknight Enchiladas', 'refried beans', 'https://www.budgetbytes.com/weeknight-enchiladas/', " +
                "'vegetable oil, chili powder, flour, tomato paste, cumin, garlic powder, cayenne pepper, salt, corn tortillas, refried beans, pepper jack cheese, avocado, cilantro');"
        );//26
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Weeknight Enchiladas', 'cheddar cheese', 'https://www.budgetbytes.com/weeknight-enchiladas/', " +
                "'vegetable oil, chili powder, flour, tomato paste, cumin, garlic powder, cayenne pepper, salt, corn tortillas, refried beans, pepper jack cheese, avocado, cilantro');"
        );//26
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Sweet Potato Tacos with Lime Crema', 'sweet potato', 'https://www.budgetbytes.com/sweet-potato-tacos-with-lime-crema/', " +
                "'sour cream, lime, salt, garlic, olive oil, sweet potatoes, black beans, cumin, pepper, green onions, cilantro, tortillas');"
        );//27
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Sweet Potato Tacos with Lime Crema', 'sour cream', 'https://www.budgetbytes.com/sweet-potato-tacos-with-lime-crema/', " +
                "'sour cream, lime, salt, garlic, olive oil, sweet potatoes, black beans, cumin, pepper, green onions, cilantro, tortillas');"
        );//27
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Sweet Potato Tacos with Lime Crema', 'lime', 'https://www.budgetbytes.com/sweet-potato-tacos-with-lime-crema/', " +
                "'sour cream, lime, salt, garlic, olive oil, sweet potatoes, black beans, cumin, pepper, green onions, cilantro, tortillas');"
        );//27
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Sweet Potato Tacos with Lime Crema', 'black beans', 'https://www.budgetbytes.com/sweet-potato-tacos-with-lime-crema/', " +
                "'sour cream, lime, salt, garlic, olive oil, sweet potatoes, black beans, cumin, pepper, green onions, cilantro, tortillas');"
        );//27
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Sweet and Spicy Chicken Bowl', 'chicken', 'https://www.budgetbytes.com/sweet-n-spicy-chicken-bowls/', " +
                "'mild chili powder, cumin, garlic powder, smoked paprika, cayenne pepper, salt, pepper, olive oil, honey, apple cider vinegar, brown rice, chicken broth, chicken breast, pineapple tidbits, black beans, avocado, green onion, cilantro');"
        );//28
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Sweet and Spicy Chicken Bowl', 'avocado', 'https://www.budgetbytes.com/sweet-n-spicy-chicken-bowls/', " +
                "'mild chili powder, cumin, garlic powder, smoked paprika, cayenne pepper, salt, pepper, olive oil, honey, apple cider vinegar, brown rice, chicken broth, chicken breast, pineapple tidbits, black beans, avocado, green onion, cilantro');"
        );//29
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Sweet and Spicy Chicken Bowl', 'honey', 'https://www.budgetbytes.com/sweet-n-spicy-chicken-bowls/', " +
                "'mild chili powder, cumin, garlic powder, smoked paprika, cayenne pepper, salt, pepper, olive oil, honey, apple cider vinegar, brown rice, chicken broth, chicken breast, pineapple tidbits, black beans, avocado, green onion, cilantro');"
        );//29
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Broccoli Cheddar Casserole', 'broccoli', 'https://www.budgetbytes.com/broccoli-cheddar-casserole/', " +
                "'broccoli, sharp cheddar, rice, yellow onion, garlic, butter, flour, milk, salt, smoked paprika, cayenne pepper, pepper');"
        );//30
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Broccoli Cheddar Casserole', 'skim milk', 'https://www.budgetbytes.com/broccoli-cheddar-casserole/', " +
                "'broccoli, sharp cheddar, rice, yellow onion, garlic, butter, flour, milk, salt, smoked paprika, cayenne pepper, pepper');"
        );//31
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Broccoli Cheddar Casserole', 'whole milk', 'https://www.budgetbytes.com/broccoli-cheddar-casserole/', " +
                "'broccoli, sharp cheddar, rice, yellow onion, garlic, butter, flour, milk, salt, smoked paprika, cayenne pepper, pepper');"
        );//32
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Broccoli Cheddar Casserole', 'sharp cheddar cheese', 'https://www.budgetbytes.com/broccoli-cheddar-casserole/', " +
                "'broccoli, sharp cheddar, rice, yellow onion, garlic, butter, flour, milk, salt, smoked paprika, cayenne pepper, pepper');"
        );//32
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Spicy Tuna Guacamole Bowl', 'avocado', 'https://www.budgetbytes.com/spicy-tuna-guacamole-bowls/'," +
                "'rice, cucumber, edamame, carrot, canned tuna, guacamole, cilantro, sriracha sauce');"
        );//33
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Spicy Tuna Guacamole Bowl', 'tuna', 'https://www.budgetbytes.com/spicy-tuna-guacamole-bowls/'," +
                "'rice, cucumber, edamame, carrot, canned tuna, guacamole, cilantro, sriracha sauce');"
        );//34
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Spicy Tuna Guacamole Bowl', 'cucumber', 'https://www.budgetbytes.com/spicy-tuna-guacamole-bowls/'," +
                "'rice, cucumber, edamame, carrot, canned tuna, guacamole, cilantro, sriracha sauce');"
        );//35
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Unstuffed Bell Peppers', 'ground beef', 'https://www.budgetbytes.com/unstuffed-bell-peppers/', " +
                "'olive oil, garlic, onion, ground beef, bell peppers, diced tomatoes, rice, basil, oregano, pepper, beef broth, tomato sauce, worcestershire sauce');"
        );//36
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Unstuffed Bell Peppers', 'bell peppers', 'https://www.budgetbytes.com/unstuffed-bell-peppers/', " +
                "'olive oil, garlic, onion, ground beef, bell peppers, diced tomatoes, rice, basil, oregano, pepper, beef broth, tomato sauce, worcestershire sauce');"
        );//36
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Southwest Chicken Skillet', 'chicken', 'https://www.budgetbytes.com/southwest-chicken-skillet/', " +
                "'white rice, salsa, shredded chicken, black beans, chili powder, chicken broth, shredded cheese, green onion');"
        );//37
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Chorizo Chicken Jambalaya', 'chicken', 'https://www.budgetbytes.com/chorizo-chicken-jambalaya/', " +
                "'oil, chorizo, chicken, onion pepper celery mix(frozen), tomato paste, rice, diced tomatoes,  chicken broth, bay leaf, cumin, smoked paprika, oregano, green onion');"
        );//38
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Chorizo Chicken Jambalaya', 'chorizo', 'https://www.budgetbytes.com/chorizo-chicken-jambalaya/', " +
                "'oil, chorizo, chicken, onion pepper celery mix(frozen), tomato paste, rice, diced tomatoes,  chicken broth, bay leaf, cumin, smoked paprika, oregano, green onion');"
        );//39
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Sausage and Kale Skillet', 'italian sausage', 'https://www.budgetbytes.com/sausage-kale-skillet/'," +
                "'italian sausage, kale, chick peas, marinara sauce, mozzarella cheese, pasta');"
        );//40
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Sausage and Kale Skillet', 'kale', 'https://www.budgetbytes.com/sausage-kale-skillet/'," +
                "'italian sausage, kale, chick peas, marinara sauce, mozzarella cheese, pasta');"
        );//40
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Sausage and Kale Skillet', 'mozzarella cheese', 'https://www.budgetbytes.com/sausage-kale-skillet/'," +
                "'italian sausage, kale, chick peas, marinara sauce, mozzarella cheese, pasta');"
        );//40
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Roasted Corn and Zucchini Tacos', 'zucchini', 'https://www.budgetbytes.com/roasted-corn-zucchini-tacos/', " +
                "'zucchini, frozen corn, taco seasoning, garlic powder, olive oil, black beans, salt, corn tortillas, avocado, monterrey jack cheese, cilantro');"
        );//41
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Roasted Corn and Zucchini Tacos', 'corn', 'https://www.budgetbytes.com/roasted-corn-zucchini-tacos/', " +
                "'zucchini, frozen corn, taco seasoning, garlic powder, olive oil, black beans, salt, corn tortillas, avocado, monterrey jack cheese, cilantro');"
        );//41
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Roasted Corn and Zucchini Tacos', 'black beans', 'https://www.budgetbytes.com/roasted-corn-zucchini-tacos/', " +
                "'zucchini, frozen corn, taco seasoning, garlic powder, olive oil, black beans, salt, corn tortillas, avocado, monterrey jack cheese, cilantro');"
        );//41
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Roasted Corn and Zucchini Tacos', 'avocado', 'https://www.budgetbytes.com/roasted-corn-zucchini-tacos/', " +
                "'zucchini, frozen corn, taco seasoning, garlic powder, olive oil, black beans, salt, corn tortillas, avocado, monterrey jack cheese, cilantro');"
        );//41
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Roasted Corn and Zucchini Tacos', 'cheddar cheese', 'https://www.budgetbytes.com/roasted-corn-zucchini-tacos/', " +
                "'zucchini, frozen corn, taco seasoning, garlic powder, olive oil, black beans, salt, corn tortillas, avocado, monterrey jack cheese, cilantro');"
        );//41
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Southwest Steak Bowls', 'steak', 'https://www.budgetbytes.com/southwest-steak-bowls/', " +
                "'steak, olive oil, garlic, cumin, salt, lime, brown rice, chicken broth, onion, tomato, cilantro, black beans, corn, sour cream');"
        );//42
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Southwest Steak Bowls', 'limr', 'https://www.budgetbytes.com/southwest-steak-bowls/', " +
                "'steak, olive oil, garlic, cumin, salt, lime, brown rice, chicken broth, onion, tomato, cilantro, black beans, corn, sour cream');"
        );//42
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Southwest Steak Bowls', 'onion', 'https://www.budgetbytes.com/southwest-steak-bowls/', " +
                "'steak, olive oil, garlic, cumin, salt, lime, brown rice, chicken broth, onion, tomato, cilantro, black beans, corn, sour cream');"
        );//42
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Southwest Steak Bowls', 'tomato', 'https://www.budgetbytes.com/southwest-steak-bowls/', " +
                "'steak, olive oil, garlic, cumin, salt, lime, brown rice, chicken broth, onion, tomato, cilantro, black beans, corn, sour cream');"
        );//42
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Southwest Steak Bowls', 'black beans', 'https://www.budgetbytes.com/southwest-steak-bowls/', " +
                "'steak, olive oil, garlic, cumin, salt, lime, brown rice, chicken broth, onion, tomato, cilantro, black beans, corn, sour cream');"
        );//42
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Southwest Steak Bowls', 'corn', 'https://www.budgetbytes.com/southwest-steak-bowls/', " +
                "'steak, olive oil, garlic, cumin, salt, lime, brown rice, chicken broth, onion, tomato, cilantro, black beans, corn, sour cream');"
        );//42
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Southwest Steak Bowls', 'sour cream', 'https://www.budgetbytes.com/southwest-steak-bowls/', " +
                "'steak, olive oil, garlic, cumin, salt, lime, brown rice, chicken broth, onion, tomato, cilantro, black beans, corn, sour cream');"
        );//42
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Sweet Chili Chicken Bowls', 'chicken', 'https://www.budgetbytes.com/sweet-chili-chicken-bowls/', " +
                "'crushed pineapple, black beans, mango, garlic, ginger, lime, salt, sweet chili sauce, chicken, jasmine rice, cilantro');"
        );//43
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Sweet Chili Chicken Bowls', 'pineapple', 'https://www.budgetbytes.com/sweet-chili-chicken-bowls/', " +
                "'crushed pineapple, black beans, mango, garlic, ginger, lime, salt, sweet chili sauce, chicken, jasmine rice, cilantro');"
        );//43
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Sweet Chili Chicken Bowls', 'black beans', 'https://www.budgetbytes.com/sweet-chili-chicken-bowls/', " +
                "'crushed pineapple, black beans, mango, garlic, ginger, lime, salt, sweet chili sauce, chicken, jasmine rice, cilantro');"
        );//43
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Sweet Chili Chicken Bowls', 'mango', 'https://www.budgetbytes.com/sweet-chili-chicken-bowls/', " +
                "'crushed pineapple, black beans, mango, garlic, ginger, lime, salt, sweet chili sauce, chicken, jasmine rice, cilantro');"
        );//43
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Sweet Chili Chicken Bowls', 'lime', 'https://www.budgetbytes.com/sweet-chili-chicken-bowls/', " +
                "'crushed pineapple, black beans, mango, garlic, ginger, lime, salt, sweet chili sauce, chicken, jasmine rice, cilantro');"
        );//43
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Southwest Breakfast Scramble', 'eggs', 'https://www.budgetbytes.com/southwest-breakfast-scramble/', " +
                "'eggs, butter, black beans, tortilla strips, salsa, cheese, cilantro, salt, pepper');"
        );//44
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Southwest Breakfast Scramble', 'black beans', 'https://www.budgetbytes.com/southwest-breakfast-scramble/', " +
                "'eggs, butter, black beans, tortilla strips, salsa, cheese, cilantro, salt, pepper');"
        );//44
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Southwest Breakfast Scramble', 'cheddar cheese', 'https://www.budgetbytes.com/southwest-breakfast-scramble/', " +
                "'eggs, butter, black beans, tortilla strips, salsa, cheese, cilantro, salt, pepper');"
        );//44
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Golden Rice Bowls', 'eggs', 'https://www.budgetbytes.com/golden-rice-bowls/', " +
                "'butter, yellow onion, garlic, jasmine rice, turmeric, cumin, cinnamon, bay leaf, chicken broth, olive oil, spinach, salt, pepper, eggs');"
        );//45
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Banh Mi Bowls', 'eggs', 'https://www.budgetbytes.com/banh-mi-bowls/'," +
                "'carrots, onion, rice vinegar, white sugar, salt, ground pork, eggs, breadcrumbs, garlic, ginger, soy sauce, green onion, jasmine rice, jalapeno, cucumber, cilantro');"
        );//46
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Banh Mi Bowls', 'ground pork', 'https://www.budgetbytes.com/banh-mi-bowls/', " +
                "'carrots, onion, rice vinegar, white sugar, salt, ground pork, eggs, breadcrumbs, garlic, ginger, soy sauce, green onion, jasmine rice, jalapeno, cucumber, cilantro');"
        );//47
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Banh Mi Bowls', 'jalapeno', 'https://www.budgetbytes.com/banh-mi-bowls/', " +
                "'carrots, onion, rice vinegar, white sugar, salt, ground pork, eggs, breadcrumbs, garlic, ginger, soy sauce, green onion, jasmine rice, jalapeno, cucumber, cilantro');"
        );//47
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Banh Mi Bowls', 'cucumber', 'https://www.budgetbytes.com/banh-mi-bowls/', " +
                "'carrots, onion, rice vinegar, white sugar, salt, ground pork, eggs, breadcrumbs, garlic, ginger, soy sauce, green onion, jasmine rice, jalapeno, cucumber, cilantro');"
        );//47
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Salsa Chicken Casserole', 'chicken', 'https://www.budgetbytes.com/salsa-chicken-casserole/', " +
                "'rice, corn, black beans, salsa, chicken broth, chili powder, oregano, chicken, cheddar cheese, green onions');"
        );//48
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Salsa Chicken Casserole', 'chicken', 'https://www.budgetbytes.com/salsa-chicken-casserole/', " +
                "'rice, corn, black beans, salsa, chicken broth, chili powder, oregano, chicken, cheddar cheese, green onions');"
        );//48
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Salsa Chicken Casserole', 'corn', 'https://www.budgetbytes.com/salsa-chicken-casserole/', " +
                "'rice, corn, black beans, salsa, chicken broth, chili powder, oregano, chicken, cheddar cheese, green onions');"
        );//48
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Salsa Chicken Casserole', 'black beans', 'https://www.budgetbytes.com/salsa-chicken-casserole/', " +
                "'rice, corn, black beans, salsa, chicken broth, chili powder, oregano, chicken, cheddar cheese, green onions');"
        );//48
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Salsa Chicken Casserole', 'cheddar cheese', 'https://www.budgetbytes.com/salsa-chicken-casserole/', " +
                "'rice, corn, black beans, salsa, chicken broth, chili powder, oregano, chicken, cheddar cheese, green onions');"
        );//48
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Sausage and Kale Cassoulet', 'italian sausage', 'https://www.budgetbytes.com/sausage-kale-cassoulet/', " +
                "'great northern beans, yellow onion, garlic, sweet italian sausage, bay leaf, thyme, oregano, rosemary, diced tomatoes, chicken broth, kale');"
        );//49
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Sausage and Kale Cassoulet', 'kale', 'https://www.budgetbytes.com/sausage-kale-cassoulet/', " +
                "'great northern beans, yellow onion, garlic, sweet italian sausage, bay leaf, thyme, oregano, rosemary, diced tomatoes, chicken broth, kale');"
        );//49
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Sausage and Kale Cassoulet', 'onion', 'https://www.budgetbytes.com/sausage-kale-cassoulet/', " +
                "'great northern beans, yellow onion, garlic, sweet italian sausage, bay leaf, thyme, oregano, rosemary, diced tomatoes, chicken broth, kale');"
        );//49
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Chipotle Chicken Chili', 'chicken', 'https://www.budgetbytes.com/chipotle-chicken-chili-or-c3/', " +
                "'olive oil, yellow onion, garlic, chicken breast, V8 juice, diced tomatoes, kidney beans, black beans, corn, chipotle peppers in adobo sauce, chili powder, cumin, oregano, salt');"
        );//50
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Chipotle Chicken Chili', 'kidney beans', 'https://www.budgetbytes.com/chipotle-chicken-chili-or-c3/', " +
                "'olive oil, yellow onion, garlic, chicken breast, V8 juice, diced tomatoes, kidney beans, black beans, corn, chipotle peppers in adobo sauce, chili powder, cumin, oregano, salt');"
        );//50
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Chipotle Chicken Chili', 'black beans', 'https://www.budgetbytes.com/chipotle-chicken-chili-or-c3/', " +
                "'olive oil, yellow onion, garlic, chicken breast, V8 juice, diced tomatoes, kidney beans, black beans, corn, chipotle peppers in adobo sauce, chili powder, cumin, oregano, salt');"
        );//50
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Chipotle Chicken Chili', 'corn', 'https://www.budgetbytes.com/chipotle-chicken-chili-or-c3/', " +
                "'olive oil, yellow onion, garlic, chicken breast, V8 juice, diced tomatoes, kidney beans, black beans, corn, chipotle peppers in adobo sauce, chili powder, cumin, oregano, salt');"
        );//50
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Chipotle Chicken Chili', 'onion', 'https://www.budgetbytes.com/chipotle-chicken-chili-or-c3/', " +
                "'olive oil, yellow onion, garlic, chicken breast, V8 juice, diced tomatoes, kidney beans, black beans, corn, chipotle peppers in adobo sauce, chili powder, cumin, oregano, salt');"
        );//50
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Taco Stuffed Shells', 'ground beef', 'https://www.budgetbytes.com/taco-stuffed-shells/', " +
                "'olive oil, garlic, yellow onion, ground beef, black beans, pinto beans, taco seasoning, salt, jumbo shells, enchilada sauce, shredded cheese, green onions');"
        );//51
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Taco Stuffed Shells', 'black beans', 'https://www.budgetbytes.com/taco-stuffed-shells/', " +
                "'olive oil, garlic, yellow onion, ground beef, black beans, pinto beans, taco seasoning, salt, jumbo shells, enchilada sauce, shredded cheese, green onions');"
        );//51
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Taco Stuffed Shells', 'pinto beans', 'https://www.budgetbytes.com/taco-stuffed-shells/', " +
                "'olive oil, garlic, yellow onion, ground beef, black beans, pinto beans, taco seasoning, salt, jumbo shells, enchilada sauce, shredded cheese, green onions');"
        );//51
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Taco Stuffed Shells', 'onion', 'https://www.budgetbytes.com/taco-stuffed-shells/', " +
                "'olive oil, garlic, yellow onion, ground beef, black beans, pinto beans, taco seasoning, salt, jumbo shells, enchilada sauce, shredded cheese, green onions');"
        );//51
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Taco Stuffed Shells', 'cheddar cheese', 'https://www.budgetbytes.com/taco-stuffed-shells/', " +
                "'olive oil, garlic, yellow onion, ground beef, black beans, pinto beans, taco seasoning, salt, jumbo shells, enchilada sauce, shredded cheese, green onions');"
        );//51
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Chili Lime Shrimp Bowls', 'shrimp', 'https://www.budgetbytes.com/chili-lime-shrimp-bowls/', " +
                "'shrimp, chili powder, vegetable oil, honey, soy sauce, garlic, lime, black beans, cumin, jalapenos, crushed pineapple, red onion, salt, cilantro, rice');"
        );//52
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Chili Lime Shrimp Bowls', 'lime', 'https://www.budgetbytes.com/chili-lime-shrimp-bowls/', " +
                "'shrimp, chili powder, vegetable oil, honey, soy sauce, garlic, lime, black beans, cumin, jalapenos, crushed pineapple, red onion, salt, cilantro, rice');"
        );//52
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Chili Lime Shrimp Bowls', 'black beans', 'https://www.budgetbytes.com/chili-lime-shrimp-bowls/', " +
                "'shrimp, chili powder, vegetable oil, honey, soy sauce, garlic, lime, black beans, cumin, jalapenos, crushed pineapple, red onion, salt, cilantro, rice');"
        );//52
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Chili Lime Shrimp Bowls', 'jalapeno', 'https://www.budgetbytes.com/chili-lime-shrimp-bowls/', " +
                "'shrimp, chili powder, vegetable oil, honey, soy sauce, garlic, lime, black beans, cumin, jalapenos, crushed pineapple, red onion, salt, cilantro, rice');"
        );//52
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Chili Lime Shrimp Bowls', 'pineapple', 'https://www.budgetbytes.com/chili-lime-shrimp-bowls/', " +
                "'shrimp, chili powder, vegetable oil, honey, soy sauce, garlic, lime, black beans, cumin, jalapenos, crushed pineapple, red onion, salt, cilantro, rice');"
        );//52
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Chili Lime Shrimp Bowls', 'red onion', 'https://www.budgetbytes.com/chili-lime-shrimp-bowls/', " +
                "'shrimp, chili powder, vegetable oil, honey, soy sauce, garlic, lime, black beans, cumin, jalapenos, crushed pineapple, red onion, salt, cilantro, rice');"
        );//52
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Teriyaki Meatball Bowls', 'ground pork', 'https://www.budgetbytes.com/teriyaki-meatball-bowls/', " +
                "'ground pork, egg, plain breadcrumbs, garlic, ginger, soy sauce, green onion, black pepper, brown sugar, toasted sesame oil, vinegar, corn starch, sesame seeds, jasmine rice');"
        );//53
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Creamy Chicken and Rice Soup', 'chicken', 'https://www.budgetbytes.com/creamy-chicken-rice-soup/', " +
                "'olive oil, yellow onion, garlic, carrots, celery, chicken breast, bay leaf, oregano, thyme, cracked pepper, salt, wild rice mix, parsley, butter, flour, milk');"
        );//54
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Creamy Chicken and Rice Soup', 'onion', 'https://www.budgetbytes.com/creamy-chicken-rice-soup/', " +
                "'olive oil, yellow onion, garlic, carrots, celery, chicken breast, bay leaf, oregano, thyme, cracked pepper, salt, wild rice mix, parsley, butter, flour, milk');"
        );//54
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Creamy Chicken and Rice Soup', 'carrot', 'https://www.budgetbytes.com/creamy-chicken-rice-soup/', " +
                "'olive oil, yellow onion, garlic, carrots, celery, chicken breast, bay leaf, oregano, thyme, cracked pepper, salt, wild rice mix, parsley, butter, flour, milk');"
        );//54
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Creamy Chicken and Rice Soup', 'celery', 'https://www.budgetbytes.com/creamy-chicken-rice-soup/', " +
                "'olive oil, yellow onion, garlic, carrots, celery, chicken breast, bay leaf, oregano, thyme, cracked pepper, salt, wild rice mix, parsley, butter, flour, milk');"
        );//54
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Sausage and Kale Soup', 'italian sausage', 'https://www.budgetbytes.com/sausage-kale-soup/', " +
                "'olive oil, sweet italian sausage, yellow onion, carrots, celery, garlic, kale, white beans, chicken broth, oregano, cumin, crushed red pepper, salt, pepper');"
        );//55
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Slow Cooker Taco Chicken Bowls', 'chicken', 'https://www.budgetbytes.com/taco-chicken-bowls/', " +
                "'chicken breast, salsa, garlic, black beans, corn, chili powder, cumin, oregano, cayenne pepper, black pepper, rice, shredded cheese, green onions');"
        );//56
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Slow Cooker Taco Chicken Bowls', 'black beans', 'https://www.budgetbytes.com/taco-chicken-bowls/', " +
                "'chicken breast, salsa, garlic, black beans, corn, chili powder, cumin, oregano, cayenne pepper, black pepper, rice, shredded cheese, green onions');"
        );//56
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Slow Cooker Taco Chicken Bowls', 'corn', 'https://www.budgetbytes.com/taco-chicken-bowls/', " +
                "'chicken breast, salsa, garlic, black beans, corn, chili powder, cumin, oregano, cayenne pepper, black pepper, rice, shredded cheese, green onions');"
        );//56
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Slow Cooker Taco Chicken Bowls', 'cheddar cheese', 'https://www.budgetbytes.com/taco-chicken-bowls/', " +
                "'chicken breast, salsa, garlic, black beans, corn, chili powder, cumin, oregano, cayenne pepper, black pepper, rice, shredded cheese, green onions');"
        );//56
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Sloppy Joes Plus', 'ground beef', 'https://www.budgetbytes.com/sloppy-joes-plus/', " +
                "'brown lentils, olive oil, garlic, sweet onion, green bell pepper, ground beef, tomato sauce, tomato paste, cider vinegar, brown sugar, dijon mustard, salt, chili pepper, hamburger buns');"
        );//57
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Huevos Rancheros', 'eggs', 'https://www.budgetbytes.com/huevos-rancheros/', " +
                "'tomato, vidalia onion, cilantro, lime, olive oil, garlic, jalapeno, black beans, cumin, salt, tortillas, eggs, cheddar cheese');"
        );//114
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Huevos Rancheros', 'onion', 'https://www.budgetbytes.com/huevos-rancheros/', " +
                "'tomato, vidalia onion, cilantro, lime, olive oil, garlic, jalapeno, black beans, cumin, salt, tortillas, eggs, cheddar cheese');"
        );//110
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Huevos Rancheros', 'tomatoes', 'https://www.budgetbytes.com/huevos-rancheros/', " +
                "'tomato, vidalia onion, cilantro, lime, olive oil, garlic, jalapeno, black beans, cumin, salt, tortillas, eggs, cheddar cheese');"
        );//111
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Huevos Rancheros', 'lime', 'https://www.budgetbytes.com/huevos-rancheros/', " +
                "'tomato, vidalia onion, cilantro, lime, olive oil, garlic, jalapeno, black beans, cumin, salt, tortillas, eggs, cheddar cheese');"
        );//112
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Huevos Rancheros', 'jalapeno', 'https://www.budgetbytes.com/huevos-rancheros/', " +
                "'tomato, vidalia onion, cilantro, lime, olive oil, garlic, jalapeno, black beans, cumin, salt, tortillas, eggs, cheddar cheese');"
        );//113
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Huevos Rancheros', 'black beans', 'https://www.budgetbytes.com/huevos-rancheros/', " +
                "'tomato, vidalia onion, cilantro, lime, olive oil, garlic, jalapeno, black beans, cumin, salt, tortillas, eggs, cheddar cheese');"
        );//115
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Huevos Rancheros', 'cheddar cheese', 'https://www.budgetbytes.com/huevos-rancheros/', " +
                "'tomato, vidalia onion, cilantro, lime, olive oil, garlic, jalapeno, black beans, cumin, salt, tortillas, eggs, cheddar cheese');"
        );//58

        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Coconut Rice Pilaf', 'pineapple', 'https://www.budgetbytes.com/coconut-rice-pilaf/', " +
                "'jasmine rice, coconut milk, garlic, olive oil, onion, frozen peas and carrots, pineapple chunks, soy sauce, sriracha sauce, salt, pepper');"
        );//59
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Kung Pao Chicken', 'chicken', 'https://www.budgetbytes.com/kung-pao-chicken/', " +
                "'chicken breast, vegetable oil, soy sauce, rice wine, corn starch, brown sugar, sambal chili sauce, garlic, sesame oil, green onion, bell pepper, carrots, roasted unsalted peanuts, jasmine rice');"
        );//60
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Lentil and Sausage Stew', 'italian sausage', 'https://www.budgetbytes.com/lentil-sausage-stew/', " +
                "'italian sausage, onion, carrots, celery, garlic, brown lentils, chicken broth, cayenne, smoked paprika, cumin, oregano, frozen spinach');"
        );//61
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Greek Chicken Stew', 'chicken', 'https://www.budgetbytes.com/greek-chicken-stew/', " +
                "'chicken thighs, onion, garlic, olive oil, salt, pepper, diced tomatoes, tomato sauce, bay leaf, oregano, thyme, parsley, chickpeas, feta cheese, rice, bullion');"
        );//62
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Greek Chicken Stew', 'onion', 'https://www.budgetbytes.com/greek-chicken-stew/', " +
                "'chicken thighs, onion, garlic, olive oil, salt, pepper, diced tomatoes, tomato sauce, bay leaf, oregano, thyme, parsley, chickpeas, feta cheese, rice, bullion');"
        );//62
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Greek Chicken Stew', 'feta cheese', 'https://www.budgetbytes.com/greek-chicken-stew/', " +
                "'chicken thighs, onion, garlic, olive oil, salt, pepper, diced tomatoes, tomato sauce, bay leaf, oregano, thyme, parsley, chickpeas, feta cheese, rice, bullion');"
        );//62
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Louisiana Red Beans and Rice', 'italian sausage', 'https://www.budgetbytes.com/louisiana-red-beans-rice/', " +
                "'kidney beans, yellow onion, celery, green pepper, hot sausage, vegetable oil, garlic, bay leaves, thyme, oregano chicken bouillon, cajun seasoning, green onions, rice');"
        );//63
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Ratatouille Frittata', 'eggs', 'https://www.budgetbytes.com/ratatouille-frittata/', " +
                "'eggplant, zucchini, onion, grape tomatoes, oregano, crushed red pepper, salt, pepper, olive oil, garlic, eggs, milk, parmesan cheese');"
        );//64
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Ratatouille Frittata', 'eggplant', 'https://www.budgetbytes.com/ratatouille-frittata/', " +
                "'eggplant, zucchini, onion, grape tomatoes, oregano, crushed red pepper, salt, pepper, olive oil, garlic, eggs, milk, parmesan cheese');"
        );//65
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Ratatouille Frittata', 'zucchini', 'https://www.budgetbytes.com/ratatouille-frittata/', " +
                "'eggplant, zucchini, onion, grape tomatoes, oregano, crushed red pepper, salt, pepper, olive oil, garlic, eggs, milk, parmesan cheese');"
        );//66
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Bacon Broccoli Cheddar Quiche', 'broccoli', 'https://www.budgetbytes.com/bacon-broccoli-cheddar-crustless-quiche/', " +
                "'broccoli florets, bacon, eggs, milk, grated parmesan, seasoning salt, medium cheddar cheese');"
        );//67
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Bacon Broccoli Cheddar Quiche', 'eggs', 'https://www.budgetbytes.com/bacon-broccoli-cheddar-crustless-quiche/', " +
                "'broccoli florets, bacon, eggs, milk, grated parmesan, seasoning salt, medium cheddar cheese');"
        );//68
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Bacon Broccoli Cheddar Quiche', 'bacon', 'https://www.budgetbytes.com/bacon-broccoli-cheddar-crustless-quiche/', " +
                "'broccoli florets, bacon, eggs, milk, grated parmesan, seasoning salt, medium cheddar cheese');"
        );//69
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Bacon Broccoli Cheddar Quiche', 'milk', 'https://www.budgetbytes.com/bacon-broccoli-cheddar-crustless-quiche/', " +
                "'broccoli florets, bacon, eggs, milk, grated parmesan, seasoning salt, medium cheddar cheese');"
        );//70
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Chorizo Breakfast Hash', 'chorizo', 'https://www.budgetbytes.com/chorizo-breakfast-hash/', " +
                "'baby red potatoes, oil, chorizo, onion, bell pepper, salt, pepper, chili powder, green onion, eggs, sour cream, salsa');"
        );//71
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Chorizo Breakfast Hash', 'eggs', 'https://www.budgetbytes.com/chorizo-breakfast-hash/', " +
                "'baby red potatoes, oil, chorizo, onion, bell pepper, salt, pepper, chili powder, green onion, eggs, sour cream, salsa');"
        );//72
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Chorizo Breakfast Hash', 'potatoes', 'https://www.budgetbytes.com/chorizo-breakfast-hash/', " +
                "'baby red potatoes, oil, chorizo, onion, bell pepper, salt, pepper, chili powder, green onion, eggs, sour cream, salsa');"
        );//73
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Bibimbap', 'eggs', 'https://www.budgetbytes.com/bibimbap-ultimate-bowl-meal/', " +
                "'jasmine rice, cooking oil, spinach, toasted sesame oil, salt, ground beef, chili garlic sauce, soy sauce, brown sugar, carrot, cucumber, green onion, eggs, kimchi, sesame seeds');"
        );//74
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Bibimbap', 'cucumber', 'https://www.budgetbytes.com/bibimbap-ultimate-bowl-meal/', " +
                "'jasmine rice, cooking oil, spinach, toasted sesame oil, salt, ground beef, chili garlic sauce, soy sauce, brown sugar, carrot, cucumber, green onion, eggs, kimchi, sesame seeds');"
        );//75
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Bibimbap', 'carrot', 'https://www.budgetbytes.com/bibimbap-ultimate-bowl-meal/', " +
                "'jasmine rice, cooking oil, spinach, toasted sesame oil, salt, ground beef, chili garlic sauce, soy sauce, brown sugar, carrot, cucumber, green onion, eggs, kimchi, sesame seeds');"
        );//76
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Bibimbap', 'ground beef', 'https://www.budgetbytes.com/bibimbap-ultimate-bowl-meal/', " +
                "'jasmine rice, cooking oil, spinach, toasted sesame oil, salt, ground beef, chili garlic sauce, soy sauce, brown sugar, carrot, cucumber, green onion, eggs, kimchi, sesame seeds');"
        );//77
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Pineapple Sriracha Breakfast Bowl', 'eggs', 'https://www.budgetbytes.com/pineapple-sriracha-breakfast-bowls/', " +
                "'rice, soy sauce, sriracha, sesame oil, chopped pineapple, green onion, egg, salt, pepper');"
        );//78
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Pineapple Sriracha Breakfast Bowl', 'pineapple', 'https://www.budgetbytes.com/pineapple-sriracha-breakfast-bowls/', " +
                "'rice, soy sauce, sriracha, sesame oil, chopped pineapple, green onion, egg, salt, pepper');"
        );//79
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Roasted Red Pepper and Feta Frittata', 'eggs', 'https://www.budgetbytes.com/roasted-red-pepper-and-feta-frittata/', " +
                "'vegetable oil, garlic, baby spinach, roasted red peppers, feta, salt, pepper, crushed red pepper, eggs, milk');"
        );//80
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Roasted Red Pepper and Feta Frittata', 'spinach', 'https://www.budgetbytes.com/roasted-red-pepper-and-feta-frittata/', " +
                "'vegetable oil, garlic, baby spinach, roasted red peppers, feta, salt, pepper, crushed red pepper, eggs, milk');"
        );//81
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Roasted Red Pepper and Feta Frittata', 'feta cheese', 'https://www.budgetbytes.com/roasted-red-pepper-and-feta-frittata/', " +
                "'vegetable oil, garlic, baby spinach, roasted red peppers, feta, salt, pepper, crushed red pepper, eggs, milk');"
        );//82
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Roasted Red Pepper and Feta Frittata', 'milk', 'https://www.budgetbytes.com/roasted-red-pepper-and-feta-frittata/', " +
                "'vegetable oil, garlic, baby spinach, roasted red peppers, feta, salt, pepper, crushed red pepper, eggs, milk');"
        );//83
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Ham and Potato Frittata', 'eggs', 'https://www.budgetbytes.com/ham-potato-frittata/', " +
                "'olive oil, potatoes, green onion, deli ham, salt, pepper, eggs, milk, parmesan cheese');"
        );//84
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Ham and Potato Frittata', 'potatoes', 'https://www.budgetbytes.com/ham-potato-frittata/', " +
                "'olive oil, potatoes, green onion, deli ham, salt, pepper, eggs, milk, parmesan cheese');"
        );//85
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Ham and Potato Frittata', 'ham', 'https://www.budgetbytes.com/ham-potato-frittata/', " +
                "'olive oil, potatoes, green onion, deli ham, salt, pepper, eggs, milk, parmesan cheese');"
        );//86
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Spinach Mushroom and Feta Quiche', 'eggs', 'https://www.budgetbytes.com/spinach-mushroom-feta-crustless-quiche/', " +
                "'mushrooms, garlic, spinach, eggs, milk, feta cheese, Parmesan cheese, mozzarella cheese, salt, pepper');"
        );//87
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Spinach Mushroom and Feta Quiche', 'mushrooms', 'https://www.budgetbytes.com/spinach-mushroom-feta-crustless-quiche/', " +
                "'mushrooms, garlic, spinach, eggs, milk, feta cheese, Parmesan cheese, mozzarella cheese, salt, pepper');"
        );//88
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Spinach Mushroom and Feta Quiche', 'spinach', 'https://www.budgetbytes.com/spinach-mushroom-feta-crustless-quiche/', " +
                "'mushrooms, garlic, spinach, eggs, milk, feta cheese, Parmesan cheese, mozzarella cheese, salt, pepper');"
        );//89
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Spinach Mushroom and Feta Quiche', 'feta cheese', 'https://www.budgetbytes.com/spinach-mushroom-feta-crustless-quiche/', " +
                "'mushrooms, garlic, spinach, eggs, milk, feta cheese, Parmesan cheese, mozzarella cheese, salt, pepper');"
        );//90
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Spinach Mushroom and Feta Quiche', 'parmesan cheese', 'https://www.budgetbytes.com/spinach-mushroom-feta-crustless-quiche/', " +
                "'mushrooms, garlic, spinach, eggs, milk, feta cheese, Parmesan cheese, mozzarella cheese, salt, pepper');"
        );//91
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Spinach Mushroom and Feta Quiche', 'mozzarella cheese', 'https://www.budgetbytes.com/spinach-mushroom-feta-crustless-quiche/', " +
                "'mushrooms, garlic, spinach, eggs, milk, feta cheese, Parmesan cheese, mozzarella cheese, salt, pepper');"
        );//92
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Salsa Poached Eggs and Grits', 'ham', 'https://www.budgetbytes.com/salsa-poached-eggs-grits/', " +
                "'olive oil, vidalia onion, garlic, fire roasted tomatoes, diced green chiles, tomato paste, salt, cumin, pepper, eggs, green onions, grits, butter ');"
        );//93
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Salsa Poached Eggs and Grits', 'onion', 'https://www.budgetbytes.com/salsa-poached-eggs-grits/', " +
                "'olive oil, vidalia onion, garlic, fire roasted tomatoes, diced green chiles, tomato paste, salt, cumin, pepper, eggs, green onions, grits, butter ');"
        );//93
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Salsa Poached Eggs and Grits', 'eggs', 'https://www.budgetbytes.com/salsa-poached-eggs-grits/', " +
                "'olive oil, vidalia onion, garlic, fire roasted tomatoes, diced green chiles, tomato paste, salt, cumin, pepper, eggs, green onions, grits, butter ');"
        );//94
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Quick Migas', 'eggs', 'https://www.budgetbytes.com/quick-migas/', " +
                "'eggs, milk, salsa, shredded cheddar cheese, tortilla strips, cilantro, butter, green onions, salt, pepper');"
        );//95
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Quick Migas', 'milk', 'https://www.budgetbytes.com/quick-migas/', " +
                "'eggs, milk, salsa, shredded cheddar cheese, tortilla strips, cilantro, butter, green onions, salt, pepper');"
        );//96
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Quick Migas', 'cheddar cheese', 'https://www.budgetbytes.com/quick-migas/', " +
                "'eggs, milk, salsa, shredded cheddar cheese, tortilla strips, cilantro, butter, green onions, salt, pepper');"
        );//96
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Rice Cooker Teriyaki Shrimp and Rice', 'shrimp', 'https://www.budgetbytes.com/rice-cooker-teriyaki-shrimp-and-rice/', " +
                "'raw medium shrimp, onion, frozen peas, jasmine rice, garlic, ginger, soy sauce, brown sugar');"
        );//97
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Rice Cooker Teriyaki Shrimp and Rice', 'onion', 'https://www.budgetbytes.com/rice-cooker-teriyaki-shrimp-and-rice/', " +
                "'raw medium shrimp, onion, frozen peas, jasmine rice, garlic, ginger, soy sauce, brown sugar');"
        );//97
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Hoisin Tofu Lettuce Cups', 'tofu', 'https://www.budgetbytes.com/hoisin-tofu-lettuce-cups/', " +
                "'extra firm tofu, salt, cornstarch, cooking oil, hoisin sauce, green onions, carrot, peanuts, rice, head of lettuce');"
        );//98
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Hoisin Tofu Lettuce Cups', 'carrots', 'https://www.budgetbytes.com/hoisin-tofu-lettuce-cups/', " +
                "'extra firm tofu, salt, cornstarch, cooking oil, hoisin sauce, green onions, carrot, peanuts, rice, head of lettuce');"
        );//98
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Hoisin Tofu Lettuce Cups', 'rice', 'https://www.budgetbytes.com/hoisin-tofu-lettuce-cups/', " +
                "'extra firm tofu, salt, cornstarch, cooking oil, hoisin sauce, green onions, carrot, peanuts, rice, head of lettuce');"
        );//98
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Hoisin Tofu Lettuce Cups', 'lettuce', 'https://www.budgetbytes.com/hoisin-tofu-lettuce-cups/', " +
                "'extra firm tofu, salt, cornstarch, cooking oil, hoisin sauce, green onions, carrot, peanuts, rice, head of lettuce');"
        );//98
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Easy Sesame Chicken', 'chicken', 'https://www.budgetbytes.com/easy-sesame-chicken/', " +
                "'egg, cornstarch, salt, pepper, chicken thighs, cooking oil, soy sauce, sesame oil, brown sugar, rice vinegar, ginger, garlic, sesame seeds, jasmine rice, green onions');"
        );//99
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Slow Cooker Pineapple Teriyaki Chicken', 'chicken', 'https://www.budgetbytes.com/slow-cooker-pineapple-teriyaki-chicken/', " +
                "'pineapple chunks, soy sauce, brown sugar, garlic, ginger, chicken thighs, cornstarch, green onions, rice');"
        );//100
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Slow Cooker Pineapple Teriyaki Chicken', 'pineapple', 'https://www.budgetbytes.com/slow-cooker-pineapple-teriyaki-chicken/', " +
                "'pineapple chunks, soy sauce, brown sugar, garlic, ginger, chicken thighs, cornstarch, green onions, rice');"
        );//101
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Honey Hoisin Garlic Chicken', 'chicken', 'https://www.budgetbytes.com/jessica-gavins-honey-hoisin-garlic-chicken/', " +
                "'chicken thighs, salt, pepper, vegetable oil, hoisin sauce, soy sauce, honey, ginger, garlic, sesame seeds, cilantro');"
        );//102
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Pork and Peanut Dragon Noodles', 'ground pork', 'https://www.budgetbytes.com/pork-peanut-dragon-noodles/', " +
                "'chili garlic sauce, soy sauce, brown sugar, ground pork, ramen noodles, green onions, unsalted peanuts');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Slow Cooker Sesame Beef', 'beef', 'https://www.budgetbytes.com/slow-cooker-sesame-beef/', " +
                "'soy sauce, brown sugar, garlic, ginger, sesame oil, beef roast, green onions, cornstarch, sesame seeds');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Coconut Crusted Tofu w/ Sweet Chili Dipping Sauce', 'tofu', 'https://www.budgetbytes.com/coconut-crusted-tofu/', " +
                "'tofu, eggs, cornstarch, water, salt, pepper, unsweetened coconut flakes, coconut oil, sweet chili sauce');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Coconut Crusted Tofu w/ Sweet Chili Dipping Sauce', 'eggs', 'https://www.budgetbytes.com/coconut-crusted-tofu/', " +
                "'tofu, eggs, cornstarch, water, salt, pepper, unsweetened coconut flakes, coconut oil, sweet chili sauce');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Easy Orange Chicken', 'chicken', 'https://www.budgetbytes.com/easy-orange-chicken/', " +
                "'orange, soy sauce, brown sugar, rice vinegar, ginger, garlic, pepper flakes, cornstarch, chicken thighs, egg, salt, pepper, cooking oil, rice, green onions');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Sheet Pan Sweet and Sour Chicken', 'chicken', 'https://www.budgetbytes.com/sheet-pan-sweet-sour-chicken/', " +
                "'onion, green bell pepper, red bell pepper, pineapple chunks, chicken breast, oil, garlic powder, salt, pepper, ketchup, brown sugar, apple cider vinegar, soy sauce, corn starch, green onions, rice');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Sheet Pan Sweet and Sour Chicken', 'bell pepper', 'https://www.budgetbytes.com/sheet-pan-sweet-sour-chicken/', " +
                "'onion, green bell pepper, red bell pepper, pineapple chunks, chicken breast, oil, garlic powder, salt, pepper, ketchup, brown sugar, apple cider vinegar, soy sauce, corn starch, green onions, rice');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Sheet Pan Sweet and Sour Chicken', 'onion', 'https://www.budgetbytes.com/sheet-pan-sweet-sour-chicken/', " +
                "'onion, green bell pepper, red bell pepper, pineapple chunks, chicken breast, oil, garlic powder, salt, pepper, ketchup, brown sugar, apple cider vinegar, soy sauce, corn starch, green onions, rice');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Sheet Pan Sweet and Sour Chicken', 'pineapple', 'https://www.budgetbytes.com/sheet-pan-sweet-sour-chicken/', " +
                "'onion, green bell pepper, red bell pepper, pineapple chunks, chicken breast, oil, garlic powder, salt, pepper, ketchup, brown sugar, apple cider vinegar, soy sauce, corn starch, green onions, rice');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Singapore Noodles With Crispy Tofu', 'tofu', 'https://www.budgetbytes.com/singapore-noodles-crispy-tofu/', " +
                "'tofu, salt, cornstarch, oil, soy sauce, toasted sesame oil, sriracha, rice vinegar, curry powder, rice, garlic, ginger, onions, shredded cabbage and carrots');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Singapore Noodles With Crispy Tofu', 'onion', 'https://www.budgetbytes.com/singapore-noodles-crispy-tofu/', " +
                "'tofu, salt, cornstarch, oil, soy sauce, toasted sesame oil, sriracha, rice vinegar, curry powder, rice, garlic, ginger, onions, shredded cabbage and carrots');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Singapore Noodles With Crispy Tofu', 'cabbage', 'https://www.budgetbytes.com/singapore-noodles-crispy-tofu/', " +
                "'tofu, salt, cornstarch, oil, soy sauce, toasted sesame oil, sriracha, rice vinegar, curry powder, rice, garlic, ginger, onions, shredded cabbage and carrots');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Singapore Noodles With Crispy Tofu', 'carrots', 'https://www.budgetbytes.com/singapore-noodles-crispy-tofu/', " +
                "'tofu, salt, cornstarch, oil, soy sauce, toasted sesame oil, sriracha, rice vinegar, curry powder, rice, garlic, ginger, onions, shredded cabbage and carrots');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Slow Cooker 5 Spice Pulled Pork', 'pork', 'https://www.budgetbytes.com/slow-cooker-5-spice-pulled-pork/', " +
                "'brown sugar, five spice blend, garlic, rice vinegar, soy sauce, pork loin, cornstarch');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Savory Cabbage Pancakes', 'cabbage', 'https://www.budgetbytes.com/savory-cabbage-pancakes-okonomiyaki/', " +
                "'eggs, soy sauce, toasted sesame oil, all-purpose flour, shredded green cabbage, carrot, onion, oil, mayonnaise, sriracha, sesame seeds, green onions');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Savory Cabbage Pancakes', 'eggs', 'https://www.budgetbytes.com/savory-cabbage-pancakes-okonomiyaki/', " +
                "'eggs, soy sauce, toasted sesame oil, all-purpose flour, shredded green cabbage, carrot, onion, oil, mayonnaise, sriracha, sesame seeds, green onions');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Savory Cabbage Pancakes', 'carrot', 'https://www.budgetbytes.com/savory-cabbage-pancakes-okonomiyaki/', " +
                "'eggs, soy sauce, toasted sesame oil, all-purpose flour, shredded green cabbage, carrot, onion, oil, mayonnaise, sriracha, sesame seeds, green onions');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Savory Cabbage Pancakes', 'onion', 'https://www.budgetbytes.com/savory-cabbage-pancakes-okonomiyaki/', " +
                "'eggs, soy sauce, toasted sesame oil, all-purpose flour, shredded green cabbage, carrot, onion, oil, mayonnaise, sriracha, sesame seeds, green onions');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Beef and Cabbage Stir Fry', 'beef', 'https://www.budgetbytes.com/beef-cabbage-stir-fry/', " +
                "'soy sauce, sesame oil, sriracha, brown sugar, green cabbage, carrots, green onion, cooking oil, ground beef, garlic, grated ginger, salt, pepper, sesame seeds');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Beef and Cabbage Stir Fry', 'cabbage', 'https://www.budgetbytes.com/beef-cabbage-stir-fry/', " +
                "'soy sauce, sesame oil, sriracha, brown sugar, green cabbage, carrots, green onion, cooking oil, ground beef, garlic, grated ginger, salt, pepper, sesame seeds');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Sticky Ginger Soy Glazes Chicken', 'chicken', 'https://www.budgetbytes.com/sticky-ginger-soy-glazed-chicken/', " +
                "'brown sugar, soy sauce, garlic, ginger, cracked pepper, boneless skinless chicken, cooking oil, green onion, sesame seeds');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Lime Shrimp Dragon Noodles', 'shrimp', 'https://www.budgetbytes.com/lime-shrimp-dragon-noodles/', " +
                "'frozen shrimp, ramen noodles, sriracha, soy sauce, brown sugar, lime, butter, garlic, green onion, cilantro');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Lime Shrimp Dragon Noodles', 'lime', 'https://www.budgetbytes.com/lime-shrimp-dragon-noodles/', " +
                "'frozen shrimp, ramen noodles, sriracha, soy sauce, brown sugar, lime, butter, garlic, green onion, cilantro');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Thai Coconut Curry Braised Chicken Thighs', 'chicken', 'https://www.budgetbytes.com/thai-coconut-curry-braised-chicken-thighs/', " +
                "'cooking oil, chicken thighs, Thai curry paste, garlic, ginger, coconut milk, chicken broth, fish sauce, rice, cilantro, green onion, lime');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Thai Coconut Curry Braised Chicken Thighs', 'lime', 'https://www.budgetbytes.com/thai-coconut-curry-braised-chicken-thighs/', " +
                "'cooking oil, chicken thighs, Thai curry paste, garlic, ginger, coconut milk, chicken broth, fish sauce, rice, cilantro, green onion, lime');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Instant Pot Congee', 'chicken', 'https://www.budgetbytes.com/instant-pot-congee-jook/', " +
                "'rice, garlic, ginger, shittake mushrooms, chicken, salt, green onion, cilantro, peanuts, soy sauce, toasted sesame oil');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Instant Pot Congee', 'mushrooms', 'https://www.budgetbytes.com/instant-pot-congee-jook/', " +
                "'rice, garlic, ginger, shittake mushrooms, chicken, salt, green onion, cilantro, peanuts, soy sauce, toasted sesame oil');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Stir Fry Beef Noodles', 'ground beef', 'https://www.budgetbytes.com/stir-fry-beef-noodles/'," +
                "'soy sauce, brown sugar, chili garlic sauce, sesame oil, rice noodles, garlic, ginger, vegetable oil, ground beef, carrot, green onion, cilantro');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Stir Fry Beef Noodles', 'carrots', 'https://www.budgetbytes.com/stir-fry-beef-noodles/'," +
                "'soy sauce, brown sugar, chili garlic sauce, sesame oil, rice noodles, garlic, ginger, vegetable oil, ground beef, carrot, green onion, cilantro');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Nam Sod', 'ground pork', 'https://www.budgetbytes.com/nam-sod-thai-pork-salad/', " +
                "'lime juice, fish sauce, ginger, chili garlic sauce, ground pork, garlic, canola oil, red onion, carrot, cilantro, peanuts, rice');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Nam Sod', 'ground turkey', 'https://www.budgetbytes.com/nam-sod-thai-pork-salad/', " +
                "'lime juice, fish sauce, ginger, chili garlic sauce, ground turkey, garlic, canola oil, red onion, carrot, cilantro, peanuts, rice');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Nam Sod', 'onion', 'https://www.budgetbytes.com/nam-sod-thai-pork-salad/', " +
                "'lime juice, fish sauce, ginger, chili garlic sauce, ground turkey, garlic, canola oil, red onion, carrot, cilantro, peanuts, rice');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Nam Sod', 'carrot', 'https://www.budgetbytes.com/nam-sod-thai-pork-salad/', " +
                "'lime juice, fish sauce, ginger, chili garlic sauce, ground turkey, garlic, canola oil, red onion, carrot, cilantro, peanuts, rice');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Sesame Glazed Salmon and Green Beans', 'salmon', 'https://www.budgetbytes.com/sesame-glazed-salmon-and-green-beans/', " +
                "'salmon fillet, green beans, soy sauce, rice wine, brown sugar, toasted sesame oil, garlic, ginger, sesame seeds, cornstarch');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Sesame Glazed Salmon and Green Beans', 'green beans', 'https://www.budgetbytes.com/sesame-glazed-salmon-and-green-beans/', " +
                "'salmon fillet, green beans, soy sauce, rice wine, brown sugar, toasted sesame oil, garlic, ginger, sesame seeds, cornstarch');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Crunchy Chinese Chicken Salad', 'chicken', 'https://www.budgetbytes.com/crunchy-chinese-chicken-salad/', " +
                "'canola oil, rice vinegar, honey, soy sauce, ground ginger, garlic powder, toasted sesame oil, cracked pepper, cabbage, carrots, green onions, sliced almonds, ramen noodles, rotisserie chicken');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Crunchy Chinese Chicken Salad', 'cabbage', 'https://www.budgetbytes.com/crunchy-chinese-chicken-salad/', " +
                "'canola oil, rice vinegar, honey, soy sauce, ground ginger, garlic powder, toasted sesame oil, cracked pepper, cabbage, carrots, green onions, sliced almonds, ramen noodles, rotisserie chicken');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Thai Peanut Chicken', 'chicken', 'https://www.budgetbytes.com/thai-peanut-chicken/', " +
                "'garlic, ginger, lime, garlic chili sauce, soy sauce, honey, toasted sesame oil, vegetable oil, peanut butter, cilantro, chicken pieces');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Thai Peanut Chicken', 'lime', 'https://www.budgetbytes.com/thai-peanut-chicken/', " +
                "'garlic, ginger, lime, garlic chili sauce, soy sauce, honey, toasted sesame oil, vegetable oil, peanut butter, cilantro, chicken pieces');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Thai Peanut Chicken', 'honey', 'https://www.budgetbytes.com/thai-peanut-chicken/', " +
                "'garlic, ginger, lime, garlic chili sauce, soy sauce, honey, toasted sesame oil, vegetable oil, peanut butter, cilantro, chicken pieces');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Spicy Pork Pad Thai', 'ground pork', 'https://www.budgetbytes.com/spicy-pork-pad-thai/', " +
                "'flat rice noodles, lime, sriracha, vegetable oil, garlic, ground pork, eggs, onion, bell pepper, cilantro');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Spicy Pork Pad Thai', 'lime', 'https://www.budgetbytes.com/spicy-pork-pad-thai/', " +
                "'flat rice noodles, lime, sriracha, vegetable oil, garlic, ground pork, eggs, onion, bell pepper, cilantro');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Spicy Pork Pad Thai', 'eggs', 'https://www.budgetbytes.com/spicy-pork-pad-thai/', " +
                "'flat rice noodles, lime, sriracha, vegetable oil, garlic, ground pork, eggs, onion, bell pepper, cilantro');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Spicy Pork Pad Thai', 'onion', 'https://www.budgetbytes.com/spicy-pork-pad-thai/', " +
                "'flat rice noodles, lime, sriracha, vegetable oil, garlic, ground pork, eggs, onion, bell pepper, cilantro');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Spicy Pork Pad Thai', 'bell pepper', 'https://www.budgetbytes.com/spicy-pork-pad-thai/', " +
                "'flat rice noodles, lime, sriracha, vegetable oil, garlic, ground pork, eggs, onion, bell pepper, cilantro');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Baked Pineapple Teriyaki Chicken Thighs', 'chicken', 'https://www.budgetbytes.com/baked-pineapple-teriyaki-chicken-thighs/', " +
                "'pineapple chunks, ginger, garlic, soy sauce, rice vinegar, brown sugar, cornstarch, boneless skinless chicken thighs, green onions, rice');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Baked Pineapple Teriyaki Chicken Thighs', 'pineapple', 'https://www.budgetbytes.com/baked-pineapple-teriyaki-chicken-thighs/', " +
                "'pineapple chunks, ginger, garlic, soy sauce, rice vinegar, brown sugar, cornstarch, boneless skinless chicken thighs, green onions, rice');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Thai Chicken Pizza', 'chicken', 'https://www.budgetbytes.com/thai-chicken-pizza/', " +
                "'naan, hoisin sauce, peanut butter, ginger, sriracha, chicken breast, mozzarella cheese, red pepper, red onion, cilantro, salt, pepper');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Thai Chicken Pizza', 'mozzarella cheese', 'https://www.budgetbytes.com/thai-chicken-pizza/', " +
                "'naan, hoisin sauce, peanut butter, ginger, sriracha, chicken breast, mozzarella cheese, red pepper, red onion, cilantro, salt, pepper');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Thai Chicken Pizza', 'bell pepper', 'https://www.budgetbytes.com/thai-chicken-pizza/', " +
                "'naan, hoisin sauce, peanut butter, ginger, sriracha, chicken breast, mozzarella cheese, red pepper, red onion, cilantro, salt, pepper');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Thai Chicken Pizza', 'onion', 'https://www.budgetbytes.com/thai-chicken-pizza/', " +
                "'naan, hoisin sauce, peanut butter, ginger, sriracha, chicken breast, mozzarella cheese, red pepper, red onion, cilantro, salt, pepper');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Turkey Sriracha Meatballs', 'ground turkey', 'https://www.budgetbytes.com/turkey-sriracha-meatballs/', " +
                "'ground turkey, egg, breadcrumbs, ginger, soy sauce, green onion, black pepper, rice vinegar, brown sugar, sriracha, cornstarch');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Pineapple Teriyaki Chicken Thighs', 'chicken', 'https://www.budgetbytes.com/pineapple-teriyaki-chicken-thighs/', " +
                "'garlic, ginger, vegetable oil, soy sauce, pineapple jam, rice vinegar, cornstarch, bone-in chicken thighs');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Pineapple Teriyaki Chicken Thighs', 'pineapple', 'https://www.budgetbytes.com/pineapple-teriyaki-chicken-thighs/', " +
                "'garlic, ginger, vegetable oil, soy sauce, pineapple jam, rice vinegar, cornstarch, bone-in chicken thighs');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Teriyaki Salmon with Sriracha Mayo', 'salmon', 'https://www.budgetbytes.com/teriyaki-salmon-w-sriracha-mayo/', " +
                "'salmon fillet, soy sauce, toasted sesame oil, ginger, garlic, brown sugar, rice wine, cornstarch, mayonnaise, sriracha');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Chicken Adobo', 'chicken', 'https://www.budgetbytes.com/chicken-adobo/', " +
                "'skin on bone-in chicken, soy sauce, apple cider vinegar, vegetable oil, honey, bay leaves, garlic, black peppercorns');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Ginger Salmon', 'salmon', 'https://www.budgetbytes.com/ginger-salmon/', " +
                "'salmon, ginger, garlic, brown sugar, soy sauce, toasted sesame oil');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Coconut Chicken Soup', 'chicken', 'https://www.budgetbytes.com/coconut-chicken-soup/', " +
                "'lemongrass, ginger, lime, crushed red pepper, coconut milk, chicken bouillon, chicken breast, fish sauce, cilantro');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Coconut Chicken Soup', 'lime', 'https://www.budgetbytes.com/coconut-chicken-soup/', " +
                "'lemongrass, ginger, lime, crushed red pepper, coconut milk, chicken bouillon, chicken breast, fish sauce, cilantro');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Asian Sticky Wings', 'chicken', 'https://www.budgetbytes.com/asian-sticky-wings/', " +
                "'chicken wings, honey, soy sauce, chili garlic sauce, sesame oil, teriyaki sauce, rice vinegar, garlic, ginger, green onion');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Teriyaki Pork Sandwiches', 'pork', 'https://www.budgetbytes.com/teriyaki-pork-sandwiches/', " +
                "'pork butt roast, sweet onion, teriyaki sauce, pineapple slices, ginger, all-purpose flour, hamburger buns');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Teriyaki Pork Sandwiches', 'onion', 'https://www.budgetbytes.com/teriyaki-pork-sandwiches/', " +
                "'pork butt roast, sweet onion, teriyaki sauce, pineapple slices, ginger, all-purpose flour, hamburger buns');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Lobster and Cream Cheese Wontons', 'lobster', 'https://www.budgetbytes.com/lobster-cream-cheese-wontons/', " +
                "'cream cheese, imitation lobster, egg, green onion, wanton wrappers, sriracha');"
        );// <-- RIGHT HERE
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Lobster and Cream Cheese Wontons', 'cream cheese', 'https://www.budgetbytes.com/lobster-cream-cheese-wontons/', " +
                "'cream cheese, imitation lobster, egg, green onion, wanton wrappers, sriracha');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Lobster and Cream Cheese Wontons', 'egg', 'https://www.budgetbytes.com/lobster-cream-cheese-wontons/', " +
                "'cream cheese, imitation lobster, egg, green onion, wanton wrappers, sriracha');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Crab and Cream Cheese Wontons', 'crab', 'https://www.budgetbytes.com/lobster-cream-cheese-wontons/', " +
                "'cream cheese, imitation crab, egg, green onion, wanton wrappers, sriracha');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Crab and Cream Cheese Wontons', 'cream cheese', 'https://www.budgetbytes.com/lobster-cream-cheese-wontons/', " +
                "'cream cheese, imitation crab, egg, green onion, wanton wrappers, sriracha');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Crab and Cream Cheese Wontons', 'egg', 'https://www.budgetbytes.com/lobster-cream-cheese-wontons/', " +
                "'cream cheese, imitation crab, egg, green onion, wanton wrappers, sriracha');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Spring Rolls & Quick Peanut Sauce', 'crab', 'https://www.budgetbytes.com/spring-rolls-quick-peanut-sauce/', " +
                "'rice paper wrappers, red leaf lettuce, bean threads, mango, krab, peanut butter, hoisin sauce, ginger, sriracha');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Spring Rolls & Quick Peanut Sauce', 'lettuce', 'https://www.budgetbytes.com/spring-rolls-quick-peanut-sauce/', " +
                "'rice paper wrappers, red leaf lettuce, bean threads, mango, krab, peanut butter, hoisin sauce, ginger, sriracha');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Spring Rolls & Quick Peanut Sauce', 'mango', 'https://www.budgetbytes.com/spring-rolls-quick-peanut-sauce/', " +
                "'rice paper wrappers, red leaf lettuce, bean threads, mango, krab, peanut butter, hoisin sauce, ginger, sriracha');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Sriracha Chicken Strips', 'chicken', 'https://www.budgetbytes.com/sriracha-chicken-strips/', " +
                "'chicken breast, sriracha, rice vinegar, ginger, garlic, egg, all-purpose flour, panko breadcrumbs, salt, pepper');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Pork and Ginger Pot Stickers', 'ground pork', 'https://www.budgetbytes.com/pork-ginger-pot-stickers/'," +
                "'ground pork, green cabbage, salt, vegetable oil, ginger, garlic, soy sauce, sesame oil, green onion, carrot, wonton wrappers, bouillon cubes');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Pork and Ginger Pot Stickers', 'cabbage', 'https://www.budgetbytes.com/pork-ginger-pot-stickers/'," +
                "'ground pork, green cabbage, salt, vegetable oil, ginger, garlic, soy sauce, sesame oil, green onion, carrot, wonton wrappers, bouillon cubes');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Coconut Chicken With Sweet Chili Dipping Sauce', 'chicken', 'https://www.budgetbytes.com/coconut-chicken-w-sweet-chili-dipping-sauce/', " +
                "'chicken breast, eggs, coconut milk, all-purpose flour, panko breadcrumbs, shredded coconut, salt, vegetable oil, sweet chili sauce');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Coconut Chicken With Sweet Chili Dipping Sauce', 'egg', 'https://www.budgetbytes.com/coconut-chicken-w-sweet-chili-dipping-sauce/', " +
                "'chicken breast, eggs, coconut milk, all-purpose flour, panko breadcrumbs, shredded coconut, salt, vegetable oil, sweet chili sauce');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Slow Cooker Chicken Tikka Masala', 'chicken', 'https://www.budgetbytes.com/slow-cooker-chicken-tikka-masala/', " +
                "'garam masala, cumin, turmeric, smoked paprika, salt, cayenne, cracked pepper, chicken breast, cooking oil, yellow onion, garlic, ginger, tomato sauce, heavy cream, rice, cilantro');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Slow Cooker Chicken Tikka Masala', 'onion', 'https://www.budgetbytes.com/slow-cooker-chicken-tikka-masala/', " +
                "'garam masala, cumin, turmeric, smoked paprika, salt, cayenne, cracked pepper, chicken breast, cooking oil, yellow onion, garlic, ginger, tomato sauce, heavy cream, rice, cilantro');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Chickpea and Potato Masala', 'potato', 'https://www.budgetbytes.com/chana-aloo-masala-chickpea-and-potato-masala/', " +
                "'russet potato, olive oil, yellow onion, garlic, ginger, garam masala, crushed tomatoes, tomato paste, salt, chickpeas, cilantro, yogurt, rice');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Chickpea and Potato Masala', 'chickpea', 'https://www.budgetbytes.com/chana-aloo-masala-chickpea-and-potato-masala/', " +
                "'russet potato, olive oil, yellow onion, garlic, ginger, garam masala, crushed tomatoes, tomato paste, salt, chickpeas, cilantro, yogurt, rice');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Chickpea and Potato Masala', 'yogurt', 'https://www.budgetbytes.com/chana-aloo-masala-chickpea-and-potato-masala/', " +
                "'russet potato, olive oil, yellow onion, garlic, ginger, garam masala, crushed tomatoes, tomato paste, salt, chickpeas, cilantro, yogurt, rice');"
        );// <--8-->
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Curried Potatoes With Poached Eggs', 'potatoes', 'https://www.budgetbytes.com/curried-potatoes-with-poached-eggs/', " +
                "'russet potato, ginger, garlic, olive oil, curry powder, tomato sauce, eggs, cilantro');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Curried Potatoes With Poached Eggs', 'eggs', 'https://www.budgetbytes.com/curried-potatoes-with-poached-eggs/', " +
                "'russet potato, ginger, garlic, olive oil, curry powder, tomato sauce, eggs, cilantro');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Curry Beef With Peas', 'ground beef', 'https://www.budgetbytes.com/curry-beef-with-peas/', " +
                "'vegetable oil, garlic, ginger, curry powder, turmeric, red pepper flakes, ground beef, potato, beef broth, flour, frozen peas, salt');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Curry Beef With Peas', 'peas', 'https://www.budgetbytes.com/curry-beef-with-peas/', " +
                "'vegetable oil, garlic, ginger, curry powder, turmeric, red pepper flakes, ground beef, potato, beef broth, flour, frozen peas, salt');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Curry Beef With Peas', 'potatoes', 'https://www.budgetbytes.com/curry-beef-with-peas/', " +
                "'vegetable oil, garlic, ginger, curry powder, turmeric, red pepper flakes, ground beef, potato, beef broth, flour, frozen peas, salt');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Curry Chicken Salad', 'chicken', 'https://www.budgetbytes.com/curry-chicken-salad/', " +
                "'mayonnaise, plain yogurt, lemon juice, honey, curry powder, salt, pepper, chicken, celery, green onions, sliced almonds, raisins');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Curry Chicken Salad', 'yogurt', 'https://www.budgetbytes.com/curry-chicken-salad/', " +
                "'mayonnaise, plain yogurt, lemon juice, honey, curry powder, salt, pepper, chicken, celery, green onions, sliced almonds, raisins');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Curry Chicken Salad', 'honey', 'https://www.budgetbytes.com/curry-chicken-salad/', " +
                "'mayonnaise, plain yogurt, lemon juice, honey, curry powder, salt, pepper, chicken, celery, green onions, sliced almonds, raisins');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Curry Chicken Salad', 'celery', 'https://www.budgetbytes.com/curry-chicken-salad/', " +
                "'mayonnaise, plain yogurt, lemon juice, honey, curry powder, salt, pepper, chicken, celery, green onions, sliced almonds, raisins');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Tandoori Chicken Bites', 'chicken', 'https://www.budgetbytes.com/tandoori-chicken-bites/', " +
                "'chicken breast, plain yogurt, lemon juice, garlic, ginger, salt, coriander, cumin, turmeric, cayenne pepper, garam masala, paprika');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Tandoori Chicken Bites', 'yogurt', 'https://www.budgetbytes.com/tandoori-chicken-bites/', " +
                "'chicken breast, plain yogurt, lemon juice, garlic, ginger, salt, coriander, cumin, turmeric, cayenne pepper, garam masala, paprika');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Turmeric Chicken', 'chicken', 'https://www.budgetbytes.com/turmeric-chicken/', " +
                "'olive oil, red onion, ginger, garlic, turmeric, cumin, cinnamon, crushed red pepper, bay leaf, chicken breast, diced tomatoes, coconut milk, salt, pepper, cilantro, rice');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Turmeric Chicken', 'red onion', 'https://www.budgetbytes.com/turmeric-chicken/', " +
                "'olive oil, red onion, ginger, garlic, turmeric, cumin, cinnamon, crushed red pepper, bay leaf, chicken breast, diced tomatoes, coconut milk, salt, pepper, cilantro, rice');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Tandoori Chicken Kebabs', 'chicken', 'https://www.budgetbytes.com/tandoori-chicken-kebabs/', " +
                "'chicken breast, plain yogurt, lemon juice, garlic, ginger, yellow onion, salt, turmeric, cayenne pepper, garam masala, paprika');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Tandoori Chicken Kebabs', 'onion', 'https://www.budgetbytes.com/tandoori-chicken-kebabs/', " +
                "'chicken breast, plain yogurt, lemon juice, garlic, ginger, yellow onion, salt, turmeric, cayenne pepper, garam masala, paprika');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Tandoori Chicken Kebabs', 'yogurt', 'https://www.budgetbytes.com/tandoori-chicken-kebabs/', " +
                "'chicken breast, plain yogurt, lemon juice, garlic, ginger, yellow onion, salt, turmeric, cayenne pepper, garam masala, paprika');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Tandoori Chicken Kebabs', 'lemon', 'https://www.budgetbytes.com/tandoori-chicken-kebabs/', " +
                "'chicken breast, plain yogurt, lemon juice, garlic, ginger, yellow onion, salt, turmeric, cayenne pepper, garam masala, paprika');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Not Butter Chicken', 'chicken', 'https://www.budgetbytes.com/not-butter-chicken/', " +
                "'chicken breast, butter, garlic, onion, ginger, garam masala, cayenne pepper, tomato sauce, plain yogurt, rice');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Not Butter Chicken', 'yogurt', 'https://www.budgetbytes.com/not-butter-chicken/', " +
                "'chicken breast, butter, garlic, onion, ginger, garam masala, cayenne pepper, tomato sauce, plain yogurt, rice');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Not Butter Chicken', 'onion', 'https://www.budgetbytes.com/not-butter-chicken/', " +
                "'chicken breast, butter, garlic, onion, ginger, garam masala, cayenne pepper, tomato sauce, plain yogurt, rice');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Roasted Eggplant With Meat Sauce', 'eggplant', 'https://www.budgetbytes.com/roasted-eggplant-meat-sauce/', " +
                "'olive oil, garlic, Italian seasoning blend, salt, eggplant, ground beef, onion, butter, crushed tomatoes, pepper, salt, Parmesan cheese');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Roasted Eggplant With Meat Sauce', 'ground beef', 'https://www.budgetbytes.com/roasted-eggplant-meat-sauce/', " +
                "'olive oil, garlic, Italian seasoning blend, salt, eggplant, ground beef, onion, butter, crushed tomatoes, pepper, salt, Parmesan cheese');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Roasted Eggplant With Meat Sauce', 'mozzarella cheese', 'https://www.budgetbytes.com/roasted-eggplant-meat-sauce/', " +
                "'olive oil, garlic, Italian seasoning blend, salt, eggplant, ground beef, onion, butter, crushed tomatoes, pepper, salt, Parmesan cheese');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Roasted Eggplant With Meat Sauce', 'parmesan cheese', 'https://www.budgetbytes.com/roasted-eggplant-meat-sauce/', " +
                "'olive oil, garlic, Italian seasoning blend, salt, eggplant, ground beef, onion, butter, crushed tomatoes, pepper, salt, Parmesan cheese');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Italian Sausage Stuffed Zucchini', 'italian sausage', 'https://www.budgetbytes.com/italian-sausage-stuffed-zucchini/', " +
                "'medium zucchini, olive oil, Italian sausage, garlic, breadcrumbs, shredded Italian cheese blend, pasta sauce, salt, pepper');"
        );
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Italian Sausage Stuffed Zucchini', 'zucchini', 'https://www.budgetbytes.com/italian-sausage-stuffed-zucchini/', " +
                "'medium zucchini, olive oil, Italian sausage, garlic, breadcrumbs, shredded Italian cheese blend, pasta sauce, salt, pepper');"
        );
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Italian Sausage Stuffed Zucchini', 'mozzarella cheese', 'https://www.budgetbytes.com/italian-sausage-stuffed-zucchini/', " +
                "'medium zucchini, olive oil, Italian sausage, garlic, breadcrumbs, shredded Italian cheese blend, pasta sauce, salt, pepper');"
        ); // <--8
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Eggplant Parmesan Pasta', 'eggplant', 'https://www.budgetbytes.com/eggplant-parmesan-pasta/', " +
                "'eggplant, salt, flour, egg, milk, breadcrumbs, Parmesan cheese, garlic powder, rigatoni, pasta sauce, shredded mozzarella cheese');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Eggplant Parmesan Pasta', 'egg', 'https://www.budgetbytes.com/eggplant-parmesan-pasta/', " +
                "'eggplant, salt, flour, egg, milk, breadcrumbs, Parmesan cheese, garlic powder, rigatoni, pasta sauce, shredded mozzarella cheese');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Eggplant Parmesan Pasta', 'milk', 'https://www.budgetbytes.com/eggplant-parmesan-pasta/', " +
                "'eggplant, salt, flour, egg, milk, breadcrumbs, Parmesan cheese, garlic powder, rigatoni, pasta sauce, shredded mozzarella cheese');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Eggplant Parmesan Pasta', 'parmesan cheese', 'https://www.budgetbytes.com/eggplant-parmesan-pasta/', " +
                "'eggplant, salt, flour, egg, milk, breadcrumbs, Parmesan cheese, garlic powder, rigatoni, pasta sauce, shredded mozzarella cheese');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Eggplant Parmesan Pasta', 'mozzarella cheese', 'https://www.budgetbytes.com/eggplant-parmesan-pasta/', " +
                "'eggplant, salt, flour, egg, milk, breadcrumbs, Parmesan cheese, garlic powder, rigatoni, pasta sauce, shredded mozzarella cheese');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Slow Cooker Italian Chicken and Peppers', 'chicken', 'https://www.budgetbytes.com/slow-cooker-italian-chicken-and-peppers/', " +
                "'bell peppers (any color), onion, chicken breast, basil, oregano, salt, pepper, marinara sauce');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Slow Cooker Italian Chicken and Peppers', 'bell pepper', 'https://www.budgetbytes.com/slow-cooker-italian-chicken-and-peppers/', " +
                "'bell peppers (any color), onion, chicken breast, basil, oregano, salt, pepper, marinara sauce');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Slow Cooker Italian Chicken and Peppers', 'onion', 'https://www.budgetbytes.com/slow-cooker-italian-chicken-and-peppers/', " +
                "'bell peppers (any color), onion, chicken breast, basil, oregano, salt, pepper, marinara sauce');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Loaded French Bread Pizza', 'italian sausage', 'https://www.budgetbytes.com/loaded-french-bread-pizza/', " +
                "'French bread loaf, Italian sausage, spinach, roma tomatoes, mushrooms, black olives, mozzarella cheese, tomato sauce, tomato paste, oregano, basil, garlic powder');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Loaded French Bread Pizza', 'spinach', 'https://www.budgetbytes.com/loaded-french-bread-pizza/', " +
                "'French bread loaf, Italian sausage, spinach, roma tomatoes, mushrooms, black olives, mozzarella cheese, tomato sauce, tomato paste, oregano, basil, garlic powder');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Loaded French Bread Pizza', 'tomatoes', 'https://www.budgetbytes.com/loaded-french-bread-pizza/', " +
                "'French bread loaf, Italian sausage, spinach, roma tomatoes, mushrooms, black olives, mozzarella cheese, tomato sauce, tomato paste, oregano, basil, garlic powder');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Loaded French Bread Pizza', 'mushrooms', 'https://www.budgetbytes.com/loaded-french-bread-pizza/', " +
                "'French bread loaf, Italian sausage, spinach, roma tomatoes, mushrooms, black olives, mozzarella cheese, tomato sauce, tomato paste, oregano, basil, garlic powder');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Loaded French Bread Pizza', 'mozzarella cheese', 'https://www.budgetbytes.com/loaded-french-bread-pizza/', " +
                "'French bread loaf, Italian sausage, spinach, roma tomatoes, mushrooms, black olives, mozzarella cheese, tomato sauce, tomato paste, oregano, basil, garlic powder');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Skillet Meatballs and Marinara', 'italian sausage', 'https://www.budgetbytes.com/skillet-meatballs-and-marinara/', " +
                "'Italian sausage, breadcrumbs, egg, onion, olive oil, garlic, crushed tomatoes, oregano, basil, salt, pepper, brown sugar, tomato paste, pasta');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Skillet Meatballs and Marinara', 'eggs', 'https://www.budgetbytes.com/skillet-meatballs-and-marinara/', " +
                "'Italian sausage, breadcrumbs, egg, onion, olive oil, garlic, crushed tomatoes, oregano, basil, salt, pepper, brown sugar, tomato paste, pasta');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Zuppa Toscana', 'italian sausage', 'https://www.budgetbytes.com/zuppa-toscana/', " +
                "'Italian sausage, onion, garlic, Great Northern beans, smoked paprika, chicken broth, half and half, red potatoes, kale, red pepper flakes, pepper');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Zuppa Toscana', 'kale', 'https://www.budgetbytes.com/zuppa-toscana/', " +
                "'Italian sausage, onion, garlic, Great Northern beans, smoked paprika, chicken broth, half and half, red potatoes, kale, red pepper flakes, pepper');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Zuppa Toscana', 'potatoes', 'https://www.budgetbytes.com/zuppa-toscana/', " +
                "'Italian sausage, onion, garlic, Great Northern beans, smoked paprika, chicken broth, half and half, red potatoes, kale, red pepper flakes, pepper');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Zuppa Toscana', 'onion', 'https://www.budgetbytes.com/zuppa-toscana/', " +
                "'Italian sausage, onion, garlic, Great Northern beans, smoked paprika, chicken broth, half and half, red potatoes, kale, red pepper flakes, pepper');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Spicy Shrimp Tomato Pasta', 'shrimp', 'https://www.budgetbytes.com/spicy-shrimp-tomato-pasta/', " +
                "'fettuccine, olive oil, butter, garlic, shrimp, diced tomatoes, red pepper flakes, salt, pepper, parsley');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Tuna and Red Pepper Pasta', 'tuna', 'https://www.budgetbytes.com/tuna-red-pepper-pasta/', " +
                "'pasta, olive oil, garlic, crushed red pepper, parsley, tuna, salt, pepper');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Skillet Lasagna', 'ricotta cheese', 'https://www.budgetbytes.com/skillet-lasagna/', " +
                "'olive oil, garlic, Italian sausage, red wine, tomato paste, diced tomatoes, Italian Seasoning, pasta, ricotta cheese, parmesan cheese, mozzarella cheese, spinach');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Skillet Lasagna', 'parmesan cheese', 'https://www.budgetbytes.com/skillet-lasagna/', " +
                "'olive oil, garlic, Italian sausage, red wine, tomato paste, diced tomatoes, Italian Seasoning, pasta, ricotta cheese, parmesan cheese, mozzarella cheese, spinach');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Skillet Lasagna', 'mozzarella cheese', 'https://www.budgetbytes.com/skillet-lasagna/', " +
                "'olive oil, garlic, Italian sausage, red wine, tomato paste, diced tomatoes, Italian Seasoning, pasta, ricotta cheese, parmesan cheese, mozzarella cheese, spinach');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Skillet Lasagna', 'spinach', 'https://www.budgetbytes.com/skillet-lasagna/', " +
                "'olive oil, garlic, Italian sausage, red wine, tomato paste, diced tomatoes, Italian Seasoning, pasta, ricotta cheese, parmesan cheese, mozzarella cheese, spinach');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Eggplant Parmesan', 'eggplant', 'https://www.budgetbytes.com/eggplant-parmesan/', " +
                "'eggplant, salt, flour, eggs, breadcrumbs, garlic powder, parsley, grated parmesan, marinara sauce, mozzarella cheese');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Eggplant Parmesan', 'eggs', 'https://www.budgetbytes.com/eggplant-parmesan/', " +
                "'eggplant, salt, flour, eggs, breadcrumbs, garlic powder, parsley, grated parmesan, marinara sauce, mozzarella cheese');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Eggplant Parmesan', 'parmesan cheese', 'https://www.budgetbytes.com/eggplant-parmesan/', " +
                "'eggplant, salt, flour, eggs, breadcrumbs, garlic powder, parsley, grated parmesan, marinara sauce, mozzarella cheese');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Eggplant Parmesan', 'mozzarella cheese', 'https://www.budgetbytes.com/eggplant-parmesan/', " +
                "'eggplant, salt, flour, eggs, breadcrumbs, garlic powder, parsley, grated parmesan, marinara sauce, mozzarella cheese');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Italian Meatballs', 'parmesan cheese', 'https://www.budgetbytes.com/italian-meatballs/', " +
                "'ground beef, Italian sausage, onion, garlic, olive oil, breadcrumbs, parmesan cheese, parsley, eggs, flour');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Italian Meatballs', 'ground beef', 'https://www.budgetbytes.com/italian-meatballs/', " +
                "'ground beef, Italian sausage, onion, garlic, olive oil, breadcrumbs, parmesan cheese, parsley, eggs, flour');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Italian Meatballs', 'italian sausage', 'https://www.budgetbytes.com/italian-meatballs/', " +
                "'ground beef, Italian sausage, onion, garlic, olive oil, breadcrumbs, parmesan cheese, parsley, eggs, flour');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Italian Meatballs', 'onion', 'https://www.budgetbytes.com/italian-meatballs/', " +
                "'ground beef, Italian sausage, onion, garlic, olive oil, breadcrumbs, parmesan cheese, parsley, eggs, flour');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Greek Marinated Chicken', 'chicken', 'https://www.budgetbytes.com/greek-marinated-chicken/', " +
                "'plain yogurt, olive oil, garlic, oregano, lemon, salt, pepper, parsley, chicken');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Greek Marinated Chicken', 'yogurt', 'https://www.budgetbytes.com/greek-marinated-chicken/', " +
                "'plain yogurt, olive oil, garlic, oregano, lemon, salt, pepper, parsley, chicken');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Greek Marinated Chicken', 'lemon', 'https://www.budgetbytes.com/greek-marinated-chicken/', " +
                "'plain yogurt, olive oil, garlic, oregano, lemon, salt, pepper, parsley, chicken');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Chicken Piccata', 'chicken', 'https://www.budgetbytes.com/chicken-piccata/', " +
                "'lemon, parsley, garlic, chicken breast, salt, pepper, flour, olive oil, butter, white wine (or chicken broth), capers');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Chicken Piccata', 'lemon', 'https://www.budgetbytes.com/chicken-piccata/', " +
                "'lemon, parsley, garlic, chicken breast, salt, pepper, flour, olive oil, butter, white wine (or chicken broth), capers');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Mediterranean Tuna Pasta', 'tuna', 'https://www.budgetbytes.com/mediterranean-tuna-pasta/', " +
                "'olive oil, onion, garlic, anchovy filets, diced tomatoes, kalamata olives, basil, red pepper flakes, salt, pepper, squash, tuna steaks, parsley, pasta');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Mediterranean Tuna Pasta', 'olives', 'https://www.budgetbytes.com/mediterranean-tuna-pasta/', " +
                "'olive oil, onion, garlic, anchovy filets, diced tomatoes, kalamata olives, basil, red pepper flakes, salt, pepper, squash, tuna steaks, parsley, pasta');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Mediterranean Tuna Pasta', 'zucchini', 'https://www.budgetbytes.com/mediterranean-tuna-pasta/', " +
                "'olive oil, onion, garlic, anchovy filets, diced tomatoes, kalamata olives, basil, red pepper flakes, salt, pepper, squash, tuna steaks, parsley, pasta');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Chicken Shawarma', 'chicken', 'https://www.budgetbytes.com/chicken-shawarma/', " +
                "'chicken breast, plain yogurt, garlic, lemon juice, cinnamon, dried oregano, salt, nutmeg, cloves, plain greek yogurt, dill, pita or flatbread, romaine lettuce, cucumber, roma tomatoes');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Chicken Shawarma', 'greek yogurt', 'https://www.budgetbytes.com/chicken-shawarma/', " +
                "'chicken breast, plain yogurt, garlic, lemon juice, cinnamon, dried oregano, salt, nutmeg, cloves, plain greek yogurt, dill, pita or flatbread, romaine lettuce, cucumber, roma tomatoes');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Chicken Shawarma', 'yogurt', 'https://www.budgetbytes.com/chicken-shawarma/', " +
                "'chicken breast, plain yogurt, garlic, lemon juice, cinnamon, dried oregano, salt, nutmeg, cloves, plain greek yogurt, dill, pita or flatbread, romaine lettuce, cucumber, roma tomatoes');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Chicken Shawarma', 'lemon', 'https://www.budgetbytes.com/chicken-shawarma/', " +
                "'chicken breast, plain yogurt, garlic, lemon juice, cinnamon, dried oregano, salt, nutmeg, cloves, plain greek yogurt, dill, pita or flatbread, romaine lettuce, cucumber, roma tomatoes');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Chicken Shawarma', 'cucumber', 'https://www.budgetbytes.com/chicken-shawarma/', " +
                "'chicken breast, plain yogurt, garlic, lemon juice, cinnamon, dried oregano, salt, nutmeg, cloves, plain greek yogurt, dill, pita or flatbread, romaine lettuce, cucumber, roma tomatoes');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Chicken Shawarma', 'tomato', 'https://www.budgetbytes.com/chicken-shawarma/', " +
                "'chicken breast, plain yogurt, garlic, lemon juice, cinnamon, dried oregano, salt, nutmeg, cloves, plain greek yogurt, dill, pita or flatbread, romaine lettuce, cucumber, roma tomatoes');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Chicken Shawarma', 'lettuce', 'https://www.budgetbytes.com/chicken-shawarma/', " +
                "'chicken breast, plain yogurt, garlic, lemon juice, cinnamon, dried oregano, salt, nutmeg, cloves, plain greek yogurt, dill, pita or flatbread, romaine lettuce, cucumber, roma tomatoes');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Greek Turkey Burger', 'ground turkey', 'https://www.budgetbytes.com/greek-turkey-burgers/', " +
                "'greek yogurt, lemon, garlic, dill, ground turkey, sun dried tomatoes, red onion, spinach, feta cheese, oregano, breadcrumbs, egg, cucumber, hamburger buns, salt, pepper');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Greek Turkey Burger', 'lemon', 'https://www.budgetbytes.com/greek-turkey-burgers/', " +
                "'greek yogurt, lemon, garlic, dill, ground turkey, sun dried tomatoes, red onion, spinach, feta cheese, oregano, breadcrumbs, egg, cucumber, hamburger buns, salt, pepper');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Greek Turkey Burger', 'tomato', 'https://www.budgetbytes.com/greek-turkey-burgers/', " +
                "'greek yogurt, lemon, garlic, dill, ground turkey, sun dried tomatoes, red onion, spinach, feta cheese, oregano, breadcrumbs, egg, cucumber, hamburger buns, salt, pepper');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Greek Turkey Burger', 'onion', 'https://www.budgetbytes.com/greek-turkey-burgers/', " +
                "'greek yogurt, lemon, garlic, dill, ground turkey, sun dried tomatoes, red onion, spinach, feta cheese, oregano, breadcrumbs, egg, cucumber, hamburger buns, salt, pepper');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Greek Turkey Burger', 'spinach', 'https://www.budgetbytes.com/greek-turkey-burgers/', " +
                "'greek yogurt, lemon, garlic, dill, ground turkey, sun dried tomatoes, red onion, spinach, feta cheese, oregano, breadcrumbs, egg, cucumber, hamburger buns, salt, pepper');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Greek Turkey Burger', 'feta cheese', 'https://www.budgetbytes.com/greek-turkey-burgers/', " +
                "'greek yogurt, lemon, garlic, dill, ground turkey, sun dried tomatoes, red onion, spinach, feta cheese, oregano, breadcrumbs, egg, cucumber, hamburger buns, salt, pepper');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Greek Turkey Burger', 'egg', 'https://www.budgetbytes.com/greek-turkey-burgers/', " +
                "'greek yogurt, lemon, garlic, dill, ground turkey, sun dried tomatoes, red onion, spinach, feta cheese, oregano, breadcrumbs, egg, cucumber, hamburger buns, salt, pepper');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Greek Turkey Burger', 'cucumber', 'https://www.budgetbytes.com/greek-turkey-burgers/', " +
                "'greek yogurt, lemon, garlic, dill, ground turkey, sun dried tomatoes, red onion, spinach, feta cheese, oregano, breadcrumbs, egg, cucumber, hamburger buns, salt, pepper');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Pesto Shrimp Pasta', 'shrimp', 'https://www.budgetbytes.com/pesto-shrimp-pasta/', " +
                "'olive oil, garlic, shrimp, cherry tomatoes, pasta, pesto, fresh parsley, salt, pepper');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Pesto Shrimp Pasta', 'tomato', 'https://www.budgetbytes.com/pesto-shrimp-pasta/', " +
                "'olive oil, garlic, shrimp, cherry tomatoes, pasta, pesto, fresh parsley, salt, pepper');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Spiced Chickpea Tostada/Taco', 'chickpea', 'https://www.budgetbytes.com/spiced-chickpea-tostadas/', " +
                "'sour cream, lime, garlic, salt, chickpeas, oil, chili powder, smoked paprika, cumin, cayenne, oregano, garlic powder, pepper, tostadas, guacamole, radishes, cilantro');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Spiced Chickpea Tostada/Taco', 'lime', 'https://www.budgetbytes.com/spiced-chickpea-tostadas/', " +
                "'sour cream, lime, garlic, salt, chickpeas, oil, chili powder, smoked paprika, cumin, cayenne, oregano, garlic powder, pepper, tostadas, guacamole, radishes, cilantro');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Spiced Chickpea Tostada/Taco', 'avocado', 'https://www.budgetbytes.com/spiced-chickpea-tostadas/', " +
                "'sour cream, lime, garlic, salt, chickpeas, oil, chili powder, smoked paprika, cumin, cayenne, oregano, garlic powder, pepper, tostadas, guacamole, radishes, cilantro');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Spiced Chickpea Tostada/Taco', 'radish', 'https://www.budgetbytes.com/spiced-chickpea-tostadas/', " +
                "'sour cream, lime, garlic, salt, chickpeas, oil, chili powder, smoked paprika, cumin, cayenne, oregano, garlic powder, pepper, tostadas, guacamole, radishes, cilantro');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Spiced Chickpea Tostada/Taco', 'sour cream', 'https://www.budgetbytes.com/spiced-chickpea-tostadas/', " +
                "'sour cream, lime, garlic, salt, chickpeas, oil, chili powder, smoked paprika, cumin, cayenne, oregano, garlic powder, pepper, tostadas, guacamole, radishes, cilantro');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Cowboy Caviar', 'lime', 'https://www.budgetbytes.com/cowboy-caviar/', " +
                "'black beans, black eyed peas, bell pepper, roma tomatoes, jalapeno, red onion, cilantro, olive oil, lime, balsamic vinegar, chili powder, cumin, salt, sugar');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Cowboy Caviar', 'black beans', 'https://www.budgetbytes.com/cowboy-caviar/', " +
                "'black beans, black eyed peas, bell pepper, roma tomatoes, jalapeno, red onion, cilantro, olive oil, lime, balsamic vinegar, chili powder, cumin, salt, sugar');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Cowboy Caviar', 'bell pepper', 'https://www.budgetbytes.com/cowboy-caviar/', " +
                "'black beans, black eyed peas, bell pepper, roma tomatoes, jalapeno, red onion, cilantro, olive oil, lime, balsamic vinegar, chili powder, cumin, salt, sugar');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Cowboy Caviar', 'tomato', 'https://www.budgetbytes.com/cowboy-caviar/', " +
                "'black beans, black eyed peas, bell pepper, roma tomatoes, jalapeno, red onion, cilantro, olive oil, lime, balsamic vinegar, chili powder, cumin, salt, sugar');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Cowboy Caviar', 'jalapeno', 'https://www.budgetbytes.com/cowboy-caviar/', " +
                "'black beans, black eyed peas, bell pepper, roma tomatoes, jalapeno, red onion, cilantro, olive oil, lime, balsamic vinegar, chili powder, cumin, salt, sugar');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Cowboy Caviar', 'onion', 'https://www.budgetbytes.com/cowboy-caviar/', " +
                "'black beans, black eyed peas, bell pepper, roma tomatoes, jalapeno, red onion, cilantro, olive oil, lime, balsamic vinegar, chili powder, cumin, salt, sugar');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Slow Cooker Chicken Tortilla Soup', 'chicken', 'https://www.budgetbytes.com/slow-cooker-chicken-tortilla-soup/', " +
                "'onion, garlic, chicken breast, chili powder, cumin, smoked paprika, cayenne, black pepper, fire roasted tomatoes, diced tomatoes, black beans, corn, chicken broth, corn tortillas, avocado, cilantro, sour cream, lime');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Slow Cooker Chicken Tortilla Soup', 'onion', 'https://www.budgetbytes.com/slow-cooker-chicken-tortilla-soup/', " +
                "'onion, garlic, chicken breast, chili powder, cumin, smoked paprika, cayenne, black pepper, fire roasted tomatoes, diced tomatoes, black beans, corn, chicken broth, corn tortillas, avocado, cilantro, sour cream, lime');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Slow Cooker Chicken Tortilla Soup', 'black beans', 'https://www.budgetbytes.com/slow-cooker-chicken-tortilla-soup/', " +
                "'onion, garlic, chicken breast, chili powder, cumin, smoked paprika, cayenne, black pepper, fire roasted tomatoes, diced tomatoes, black beans, corn, chicken broth, corn tortillas, avocado, cilantro, sour cream, lime');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Slow Cooker Chicken Tortilla Soup', 'lime', 'https://www.budgetbytes.com/slow-cooker-chicken-tortilla-soup/', " +
                "'onion, garlic, chicken breast, chili powder, cumin, smoked paprika, cayenne, black pepper, fire roasted tomatoes, diced tomatoes, black beans, corn, chicken broth, corn tortillas, avocado, cilantro, sour cream, lime');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Slow Cooker Chicken Tortilla Soup', 'sour cream', 'https://www.budgetbytes.com/slow-cooker-chicken-tortilla-soup/', " +
                "'onion, garlic, chicken breast, chili powder, cumin, smoked paprika, cayenne, black pepper, fire roasted tomatoes, diced tomatoes, black beans, corn, chicken broth, corn tortillas, avocado, cilantro, sour cream, lime');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Slow Cooker Chicken Tortilla Soup', 'avocado', 'https://www.budgetbytes.com/slow-cooker-chicken-tortilla-soup/', " +
                "'onion, garlic, chicken breast, chili powder, cumin, smoked paprika, cayenne, black pepper, fire roasted tomatoes, diced tomatoes, black beans, corn, chicken broth, corn tortillas, avocado, cilantro, sour cream, lime');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Slow Cooker Chicken Tortilla Soup', 'corn', 'https://www.budgetbytes.com/slow-cooker-chicken-tortilla-soup/', " +
                "'onion, garlic, chicken breast, chili powder, cumin, smoked paprika, cayenne, black pepper, fire roasted tomatoes, diced tomatoes, black beans, corn, chicken broth, corn tortillas, avocado, cilantro, sour cream, lime');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Easiest Burrito Bowl Meal', 'ground beef', 'https://www.budgetbytes.com/easiest-burrito-bowl-meal-prep/', " +
                "'rice, ground beef, taco seasoning, corn, black beans, cheese, salsa, green onion');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Easiest Burrito Bowl Meal', 'corn', 'https://www.budgetbytes.com/easiest-burrito-bowl-meal-prep/', " +
                "'rice, ground beef, taco seasoning, corn, black beans, cheese, salsa, green onion');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Easiest Burrito Bowl Meal', 'black beans', 'https://www.budgetbytes.com/easiest-burrito-bowl-meal-prep/', " +
                "'rice, ground beef, taco seasoning, corn, black beans, cheese, salsa, green onion');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Easiest Burrito Bowl Meal', 'cheddar cheese', 'https://www.budgetbytes.com/easiest-burrito-bowl-meal-prep/', " +
                "'rice, ground beef, taco seasoning, corn, black beans, cheese, salsa, green onion');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Green Chile Migas', 'eggs', 'https://www.budgetbytes.com/green-chile-migas/', " +
                "'corn tortillas, cooking oil, butter, eggs, green chiles, sour cream, queso fresco, green onion');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Green Chile Migas', 'sour cream', 'https://www.budgetbytes.com/green-chile-migas/', " +
                "'corn tortillas, cooking oil, butter, eggs, green chiles, sour cream, queso fresco, green onion');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Green Chile Migas', 'queso fresco', 'https://www.budgetbytes.com/green-chile-migas/', " +
                "'corn tortillas, cooking oil, butter, eggs, green chiles, sour cream, queso fresco, green onion');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Slow Cooker Carnitas', 'pork', 'https://www.budgetbytes.com/slow-cooker-carnitas/', " +
                "'yellow onion, garlic, cinnamon stick, oregano, cumin, salt, pepper, chipotle powder, pork butt roast, oranges');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Slow Cooker Carnitas', 'oranges', 'https://www.budgetbytes.com/slow-cooker-carnitas/', " +
                "'yellow onion, garlic, cinnamon stick, oregano, cumin, salt, pepper, chipotle powder, pork butt roast, oranges');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Slow Cooker Carnitas', 'onion', 'https://www.budgetbytes.com/slow-cooker-carnitas/', " +
                "'yellow onion, garlic, cinnamon stick, oregano, cumin, salt, pepper, chipotle powder, pork butt roast, oranges');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Southwest Beef and Cabbage Stir Fry', 'ground beef', 'https://www.budgetbytes.com/southwest-beef-cabbage-stir-fry/', " +
                "'green cabbage, cooking oil, ground beef, garlic, chili powder, cumin, salt, diced tomatoes, corn, green onion');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Southwest Beef and Cabbage Stir Fry', 'cabbage', 'https://www.budgetbytes.com/southwest-beef-cabbage-stir-fry/', " +
                "'green cabbage, cooking oil, ground beef, garlic, chili powder, cumin, salt, diced tomatoes, corn, green onion');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Southwest Beef and Cabbage Stir Fry', 'corn', 'https://www.budgetbytes.com/southwest-beef-cabbage-stir-fry/', " +
                "'green cabbage, cooking oil, ground beef, garlic, chili powder, cumin, salt, diced tomatoes, corn, green onion');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Cumin Lime Roasted Sweet Potatoes', 'sweet potatoes', 'https://www.budgetbytes.com/cumin-lime-roasted-sweet-potatoes/', " +
                "'sweet potatoes, olive oil, cumin, salt, cilantro, lime');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Cumin Lime Roasted Sweet Potatoes', 'lime', 'https://www.budgetbytes.com/cumin-lime-roasted-sweet-potatoes/', " +
                "'sweet potatoes, olive oil, cumin, salt, cilantro, lime');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Southwest Spaghetti Squash Bowl', 'spaghetti squash', 'https://www.budgetbytes.com/southwest-spaghetti-squash-bowls/', " +
                "'spaghetti squash, chili powder, salt, black beans, salsa, cheese, green onion');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Southwest Spaghetti Squash Bowl', 'black beans', 'https://www.budgetbytes.com/southwest-spaghetti-squash-bowls/', " +
                "'spaghetti squash, chili powder, salt, black beans, salsa, cheese, green onion');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Southwest Spaghetti Squash Bowl', 'cheddar cheese', 'https://www.budgetbytes.com/southwest-spaghetti-squash-bowls/', " +
                "'spaghetti squash, chili powder, salt, black beans, salsa, cheese, green onion');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Slow Cooker Salsa Verde Chicken Bowl', 'chicken', 'https://www.budgetbytes.com/slow-cooker-salsa-verde-chicken/', " +
                "'yellow onion, bell pepper, chicken, cumin, salt, garlic powder, pepper, salsa verde, rice, black beans, jalapeno, queso fresco, lime, cilantro');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Slow Cooker Salsa Verde Chicken Bowl', 'lime', 'https://www.budgetbytes.com/slow-cooker-salsa-verde-chicken/', " +
                "'yellow onion, bell pepper, chicken, cumin, salt, garlic powder, pepper, salsa verde, rice, black beans, jalapeno, queso fresco, lime, cilantro');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Slow Cooker Salsa Verde Chicken Bowl', 'queso fresco', 'https://www.budgetbytes.com/slow-cooker-salsa-verde-chicken/', " +
                "'yellow onion, bell pepper, chicken, cumin, salt, garlic powder, pepper, salsa verde, rice, black beans, jalapeno, queso fresco, lime, cilantro');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Slow Cooker Salsa Verde Chicken Bowl', 'jalapeno', 'https://www.budgetbytes.com/slow-cooker-salsa-verde-chicken/', " +
                "'yellow onion, bell pepper, chicken, cumin, salt, garlic powder, pepper, salsa verde, rice, black beans, jalapeno, queso fresco, lime, cilantro');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Slow Cooker Salsa Verde Chicken Bowl', 'black beans', 'https://www.budgetbytes.com/slow-cooker-salsa-verde-chicken/', " +
                "'yellow onion, bell pepper, chicken, cumin, salt, garlic powder, pepper, salsa verde, rice, black beans, jalapeno, queso fresco, lime, cilantro');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Slow Cooker Salsa Verde Chicken Bowl', 'bell peppers', 'https://www.budgetbytes.com/slow-cooker-salsa-verde-chicken/', " +
                "'yellow onion, bell pepper, chicken, cumin, salt, garlic powder, pepper, salsa verde, rice, black beans, jalapeno, queso fresco, lime, cilantro');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Slow Cooker Salsa Verde Chicken Bowl', 'onion', 'https://www.budgetbytes.com/slow-cooker-salsa-verde-chicken/', " +
                "'yellow onion, bell pepper, chicken, cumin, salt, garlic powder, pepper, salsa verde, rice, black beans, jalapeno, queso fresco, lime, cilantro');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Hatch Chile Sweet Potato Cornbread', 'milk', 'https://www.budgetbytes.com/hatch-chile-sweet-potato-cornbread/', " +
                "'hatch chile, sweet potato, yellow cornmeal, flour, sugar, baking powder, salt, eggs, sour cream, milk, cooking oil, green onion');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Hatch Chile Sweet Potato Cornbread', 'sweet potato', 'https://www.budgetbytes.com/hatch-chile-sweet-potato-cornbread/', " +
                "'hatch chile, sweet potato, yellow cornmeal, flour, sugar, baking powder, salt, eggs, sour cream, milk, cooking oil, green onion');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Hatch Chile Sweet Potato Cornbread', 'eggs', 'https://www.budgetbytes.com/hatch-chile-sweet-potato-cornbread/', " +
                "'hatch chile, sweet potato, yellow cornmeal, flour, sugar, baking powder, salt, eggs, sour cream, milk, cooking oil, green onion');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Hatch Chile Sweet Potato Cornbread', 'sour cream', 'https://www.budgetbytes.com/hatch-chile-sweet-potato-cornbread/', " +
                "'hatch chile, sweet potato, yellow cornmeal, flour, sugar, baking powder, salt, eggs, sour cream, milk, cooking oil, green onion');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Creamy Chicken Fajita Pasta', 'chicken', 'https://www.budgetbytes.com/creamy-chicken-fajita-pasta/', " +
                "'chili powder, paprika, onion powder, garlic powder, cumin, cayenne, sugar, salt, oil, chicken breast, bell pepper, yellow onion, fire roasted tomatoes, cream cheese, pasta, green onion');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Creamy Chicken Fajita Pasta', 'bell pepper', 'https://www.budgetbytes.com/creamy-chicken-fajita-pasta/', " +
                "'chili powder, paprika, onion powder, garlic powder, cumin, cayenne, sugar, salt, oil, chicken breast, bell pepper, yellow onion, fire roasted tomatoes, cream cheese, pasta, green onion');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Creamy Chicken Fajita Pasta', 'onion', 'https://www.budgetbytes.com/creamy-chicken-fajita-pasta/', " +
                "'chili powder, paprika, onion powder, garlic powder, cumin, cayenne, sugar, salt, oil, chicken breast, bell pepper, yellow onion, fire roasted tomatoes, cream cheese, pasta, green onion');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Creamy Chicken Fajita Pasta', 'cream cheese', 'https://www.budgetbytes.com/creamy-chicken-fajita-pasta/', " +
                "'chili powder, paprika, onion powder, garlic powder, cumin, cayenne, sugar, salt, oil, chicken breast, bell pepper, yellow onion, fire roasted tomatoes, cream cheese, pasta, green onion');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Chipotle Portobello Oven Fajitas', 'portobello mushrooms', 'https://www.budgetbytes.com/chipotle-portobello-oven-fajitas/', " +
                "'chili powder, chipotle powder, onion powder, cumin, garlic powder, sugar, salt, portobello mushrooms, bell pepper, yellow onion, olive oil, flour tortilla, avocado, cilantro, lime');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Chipotle Portobello Oven Fajitas', 'avocado', 'https://www.budgetbytes.com/chipotle-portobello-oven-fajitas/', " +
                "'chili powder, chipotle powder, onion powder, cumin, garlic powder, sugar, salt, portobello mushrooms, bell pepper, yellow onion, olive oil, flour tortilla, avocado, cilantro, lime');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Chipotle Portobello Oven Fajitas', 'lime', 'https://www.budgetbytes.com/chipotle-portobello-oven-fajitas/', " +
                "'chili powder, chipotle powder, onion powder, cumin, garlic powder, sugar, salt, portobello mushrooms, bell pepper, yellow onion, olive oil, flour tortilla, avocado, cilantro, lime');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Chipotle Portobello Oven Fajitas', 'onion', 'https://www.budgetbytes.com/chipotle-portobello-oven-fajitas/', " +
                "'chili powder, chipotle powder, onion powder, cumin, garlic powder, sugar, salt, portobello mushrooms, bell pepper, yellow onion, olive oil, flour tortilla, avocado, cilantro, lime');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Chipotle Portobello Oven Fajitas', 'bell pepper', 'https://www.budgetbytes.com/chipotle-portobello-oven-fajitas/', " +
                "'chili powder, chipotle powder, onion powder, cumin, garlic powder, sugar, salt, portobello mushrooms, bell pepper, yellow onion, olive oil, flour tortilla, avocado, cilantro, lime');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Cheese Scallion Stuffed Jalapenos', 'jalapenos', 'https://www.budgetbytes.com/cheesy-scallion-stuffed-jalapenos/', " +
                "'cream cheese, monterrey jack cheese, sour cream, green onions, garlic, cilantro, chili powder, salt, jalapenos, oil');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Cheese Scallion Stuffed Jalapenos', 'cream cheese', 'https://www.budgetbytes.com/cheesy-scallion-stuffed-jalapenos/', " +
                "'cream cheese, monterrey jack cheese, sour cream, green onions, garlic, cilantro, chili powder, salt, jalapenos, oil');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Cheese Scallion Stuffed Jalapenos', 'monterrey jack cheese', 'https://www.budgetbytes.com/cheesy-scallion-stuffed-jalapenos/', " +
                "'cream cheese, monterrey jack cheese, sour cream, green onions, garlic, cilantro, chili powder, salt, jalapenos, oil');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Cheese Scallion Stuffed Jalapenos', 'sour cream', 'https://www.budgetbytes.com/cheesy-scallion-stuffed-jalapenos/', " +
                "'cream cheese, monterrey jack cheese, sour cream, green onions, garlic, cilantro, chili powder, salt, jalapenos, oil');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Hot and Sour Vegetable Soup With Tofu', 'tofu', 'https://www.budgetbytes.com/hot-sour-vegetable-soup-with-tofu/', " +
                "'canola oil, ginger, green onion, red cabbage, carrots, button mushrooms, vegetable broth, soy sauce, rice vinegar, chili garlic sauce, tofu');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Hot and Sour Vegetable Soup With Tofu', 'cabbage', 'https://www.budgetbytes.com/hot-sour-vegetable-soup-with-tofu/', " +
                "'canola oil, ginger, green onion, red cabbage, carrots, button mushrooms, vegetable broth, soy sauce, rice vinegar, chili garlic sauce, tofu');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Hot and Sour Vegetable Soup With Tofu', 'carrots', 'https://www.budgetbytes.com/hot-sour-vegetable-soup-with-tofu/', " +
                "'canola oil, ginger, green onion, red cabbage, carrots, button mushrooms, vegetable broth, soy sauce, rice vinegar, chili garlic sauce, tofu');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Hot and Sour Vegetable Soup With Tofu', 'mushrooms', 'https://www.budgetbytes.com/hot-sour-vegetable-soup-with-tofu/', " +
                "'canola oil, ginger, green onion, red cabbage, carrots, button mushrooms, vegetable broth, soy sauce, rice vinegar, chili garlic sauce, tofu');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Spicy Cashew Crunch Stir Fry', 'brocolli', 'https://www.budgetbytes.com/spicy-cashew-crunch-stir-fry/', " +
                "'soy sauce, garlic chili sauce, brown sugar, ginger, toasted sesame oil, cornstarch, vegetable oil, broccoli, carrot, bell pepper, yellow onion, cashews, green onion, rice');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Spicy Cashew Crunch Stir Fry', 'carrot', 'https://www.budgetbytes.com/spicy-cashew-crunch-stir-fry/', " +
                "'soy sauce, garlic chili sauce, brown sugar, ginger, toasted sesame oil, cornstarch, vegetable oil, broccoli, carrot, bell pepper, yellow onion, cashews, green onion, rice');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Spicy Cashew Crunch Stir Fry', 'bell pepper', 'https://www.budgetbytes.com/spicy-cashew-crunch-stir-fry/', " +
                "'soy sauce, garlic chili sauce, brown sugar, ginger, toasted sesame oil, cornstarch, vegetable oil, broccoli, carrot, bell pepper, yellow onion, cashews, green onion, rice');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Spicy Cashew Crunch Stir Fry', 'onion', 'https://www.budgetbytes.com/spicy-cashew-crunch-stir-fry/', " +
                "'soy sauce, garlic chili sauce, brown sugar, ginger, toasted sesame oil, cornstarch, vegetable oil, broccoli, carrot, bell pepper, yellow onion, cashews, green onion, rice');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Slow Cooker 5 Spice Chicken', 'chicken', 'https://www.budgetbytes.com/slow-cooker-5-spice-chicken/ ', " +
                "'garlic, ginger, vegetable oil, soy sauce, brown sugar, 5 spice powder, chicken pieces, onion, rice wine');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Vegetable Stir Fry With Noodles', 'cabbage', 'https://www.budgetbytes.com/snap-challenge-vegetable-stir-fry-noodles/', " +
                "'vegetable oil, purple cabbage, bell pepper, carrot, yellow onion, ramen noodles, soy sauce, brown sugar, sriracha, cornstarch, green onion, cilantro');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Kung Pao Chicken (And Vegetables)', 'chicken', 'https://www.budgetbytes.com/kung-pao-chicken-vegetables/', " +
                "'oyster sauce, balsamin vinegar, soy sauce, brown sugar, toasted sesame oil, crushed red pepper flakes, ginger, garlic, cornstarch, chicken breast, egg, black pepper, vegetable oil, broccoli, red bell pepper, green onion, peanuts, rice');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Kung Pao Chicken (And Vegetables)', 'brocolli', 'https://www.budgetbytes.com/kung-pao-chicken-vegetables/', " +
                "'oyster sauce, balsamin vinegar, soy sauce, brown sugar, toasted sesame oil, crushed red pepper flakes, ginger, garlic, cornstarch, chicken breast, egg, black pepper, vegetable oil, broccoli, red bell pepper, green onion, peanuts, rice');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Kung Pao Chicken (And Vegetables)', 'bell pepper', 'https://www.budgetbytes.com/kung-pao-chicken-vegetables/', " +
                "'oyster sauce, balsamin vinegar, soy sauce, brown sugar, toasted sesame oil, crushed red pepper flakes, ginger, garlic, cornstarch, chicken breast, egg, black pepper, vegetable oil, broccoli, red bell pepper, green onion, peanuts, rice');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Honey Sriracha Chicken Thighs', 'chicken', 'https://www.budgetbytes.com/honey-sriracha-chicken-thighs/', " +
                "'chicken thighs, garlic, ginger, sriracha, soy sauce, rice vinegar, honey, brown sugar, vegetable oil, cornstarch, green onion, cilantro');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Hoisin Beef Tacos With Sweet and Sour Slaw', 'beef', 'https://www.budgetbytes.com/hoisin-beef-tacos-with-sweet-sour-slaw/', " +
                "'rice vinegar, sugar, salt, toasted sesame oil, crushed red pepper, shredded cabbage, carrot, multi-purpose shredded beef, hoisin sauce, tortillas, cilantro');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Chicken in Peanut Sauce', 'chicken', 'https://www.budgetbytes.com/chicken-in-peanut-sauce/', " +
                "'garlic, ginger, vegetable oil, chicken breast, coconut milk, peanut butter, soy sauce, brown sugar, sriracha, lime, cilantro, jasmine rice');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Thai Turkey Meatloaf', 'ground turkey', 'https://www.budgetbytes.com/thai-turkey-meatloaf/', " +
                "'ground turkey, garlic, green onion, ginger, soy sauce, toasted sesame oil, eggs, jasmine rice, brown sugar, ketchup, sriracha, rice vinegar');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Homemade Chicken Yakisoba', 'chicken', 'https://www.budgetbytes.com/chicken-yakisoba/', " +
                "'green cabbage, yellow onion, carrots, broccoli, ginger, chicken breast, vegetable oil, ramen noodles, sesame oil, soy sauce, worcestershire sauce, ketchup, sriracha, sugar');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Sweet Chili Chicken Stir Fry Bowls', 'chicken', 'https://www.budgetbytes.com/sweet-chili-chicken-stir-fry-bowls/', " +
                "'jasmine rice, garlic, salt, coconut milk, chicken breast, cooking oil, sweet chili sauce, pineapple tidbits, broccoli');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Mushroom Broccoli Stir Fry Noodles', 'mushrooms', 'https://www.budgetbytes.com/simple-mushroom-broccoli-stir-fry-noodles/', " +
                "'soy sauce, toasted sesame oil, chili garlic sauce, brown sugar, cornstarch, rice noodles, cooking oil, white mushrooms, garlic, broccoli, green onion');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Mushroom Broccoli Stir Fry Noodles', 'brocolli', 'https://www.budgetbytes.com/simple-mushroom-broccoli-stir-fry-noodles/', " +
                "'soy sauce, toasted sesame oil, chili garlic sauce, brown sugar, cornstarch, rice noodles, cooking oil, white mushrooms, garlic, broccoli, green onion');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Peanut Lime Cauliflower Salad', 'cauliflower', 'https://www.budgetbytes.com/peanut-lime-cauliflower-salad/', " +
                "'coconut oil, cauliflower, salt ginger, lime, soy sauce, chili garlic sauce, bell pepper, red onion, peanuts, cilantro');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Peanut Lime Cauliflower Salad', 'bell pepper', 'https://www.budgetbytes.com/peanut-lime-cauliflower-salad/', " +
                "'coconut oil, cauliflower, salt ginger, lime, soy sauce, chili garlic sauce, bell pepper, red onion, peanuts, cilantro');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Sushi Bowls with Sriracha Mayo', 'crab', 'https://www.budgetbytes.com/sushi-bowls-sriracha-mayo/', " +
                "'white rice, rice vinegar, sugar, salt, crab stick, carrot, cucumber, avocado, nori snack pack, sesame seeds, mayonnaise, sriracha');"
        );
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Sloppy Joes Plus', 'ground beef', 'https://www.budgetbytes.com/sloppy-joes-plus/', " +
                "'brown lentils, olive oil, garlic, sweet onion, green bell pepper, ground beef, tomato sauce, tomato paste, cider vinegar, brown sugar, dijon mustard, salt, chili powder, hamburger buns');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Chili Roasted Sweet Potatoes', 'sweet potatoes', 'https://www.budgetbytes.com/chili-roasted-sweet-potatoes/', " +
                "'sweet potatoes, chili powder, olive oil, salt');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Cilantro Lime Chicken Drumsticks', 'chicken', 'https://www.budgetbytes.com/cilantro-lime-chicken-drumsticks/', " +
                "'olive oil, garlic, cumin, salt, pepper, lime, cilantro, chicken drumsticks');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Cilantro Lime Chicken Drumsticks', 'lime', 'https://www.budgetbytes.com/cilantro-lime-chicken-drumsticks/', " +
                "'olive oil, garlic, cumin, salt, pepper, lime, cilantro, chicken drumsticks');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Posole', 'shredded chicken', 'https://www.budgetbytes.com/30-minute-posole/', " +
                "'yellow onion, oil, flour, chili powder, tomato paste, cumin, garlic powder, cayenne pepper, salt, chicken broth, green chiles, hominy, shredded chicken, lime, cilantro');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Posole', 'onion', 'https://www.budgetbytes.com/30-minute-posole/', " +
                "'yellow onion, oil, flour, chili powder, tomato paste, cumin, garlic powder, cayenne pepper, salt, chicken broth, green chiles, hominy, shredded chicken, lime, cilantro');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Posole', 'lime', 'https://www.budgetbytes.com/30-minute-posole/', " +
                "'yellow onion, oil, flour, chili powder, tomato paste, cumin, garlic powder, cayenne pepper, salt, chicken broth, green chiles, hominy, shredded chicken, lime, cilantro');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Posole', 'shredded beef', 'https://www.budgetbytes.com/30-minute-posole/', " +
                "'yellow onion, oil, flour, chili powder, tomato paste, cumin, garlic powder, cayenne pepper, salt, chicken broth, green chiles, hominy, shredded beef, lime, cilantro');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Posole', 'shredded pork', 'https://www.budgetbytes.com/30-minute-posole/', " +
                "'yellow onion, oil, flour, chili powder, tomato paste, cumin, garlic powder, cayenne pepper, salt, chicken broth, green chiles, hominy, shredded pork, lime, cilantro');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Vegetable Enchilada Casserole', 'italian sausage', 'https://www.budgetbytes.com/vegetable-enchilada-casserole/', " +
                "'corn tortillas, taco blend cheese, zucchini, black beans, corn, green chiles, green onion, cilantro, salt, oil, chili powder, tomato paste, cumin, garlic powder, cayenne pepper');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Vegetable Enchilada Casserole', 'black beans', 'https://www.budgetbytes.com/vegetable-enchilada-casserole/', " +
                "'corn tortillas, taco blend cheese, zucchini, black beans, corn, green chiles, green onion, cilantro, salt, oil, chili powder, tomato paste, cumin, garlic powder, cayenne pepper');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Vegetable Enchilada Casserole', 'zucchini', 'https://www.budgetbytes.com/vegetable-enchilada-casserole/', " +
                "'corn tortillas, taco blend cheese, zucchini, black beans, corn, green chiles, green onion, cilantro, salt, oil, chili powder, tomato paste, cumin, garlic powder, cayenne pepper');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Poor Man''s Burrito Bowls', 'cheddar cheese', 'https://www.budgetbytes.com/poor-mans-burrito-bowls/', " +
                "'rice, black beans, cumin, garlic powder, salt, salsa, shredded cheese, green onion');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Poor Man''s Burrito Bowls', 'black beans', 'https://www.budgetbytes.com/poor-mans-burrito-bowls/', " +
                "'rice, black beans, cumin, garlic powder, salt, salsa, shredded cheese, green onion');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Taco Salad Skillet', 'ground beef', 'https://www.budgetbytes.com/taco-salad-skillet/', " +
                "'oil, ground beef, chili powder, cumin, oregano, garlic powder, cayenne pepper, salt, black beans, cheese, tortilla chips, iceberg lettuce, tomato, onion, sour cream');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Taco Salad Skillet', 'sour cream', 'https://www.budgetbytes.com/taco-salad-skillet/', " +
                "'oil, ground beef, chili powder, cumin, oregano, garlic powder, cayenne pepper, salt, black beans, cheese, tortilla chips, iceberg lettuce, tomato, onion, sour cream');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Taco Salad Skillet', 'onion', 'https://www.budgetbytes.com/taco-salad-skillet/', " +
                "'oil, ground beef, chili powder, cumin, oregano, garlic powder, cayenne pepper, salt, black beans, cheese, tortilla chips, iceberg lettuce, tomato, onion, sour cream');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Taco Salad Skillet', 'tomato', 'https://www.budgetbytes.com/taco-salad-skillet/', " +
                "'oil, ground beef, chili powder, cumin, oregano, garlic powder, cayenne pepper, salt, black beans, cheese, tortilla chips, iceberg lettuce, tomato, onion, sour cream');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Taco Salad Skillet', 'lettuce', 'https://www.budgetbytes.com/taco-salad-skillet/', " +
                "'oil, ground beef, chili powder, cumin, oregano, garlic powder, cayenne pepper, salt, black beans, cheese, tortilla chips, iceberg lettuce, tomato, onion, sour cream');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Taco Salad Skillet', 'cheddar cheese', 'https://www.budgetbytes.com/taco-salad-skillet/', " +
                "'oil, ground beef, chili powder, cumin, oregano, garlic powder, cayenne pepper, salt, black beans, cheese, tortilla chips, iceberg lettuce, tomato, onion, sour cream');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Taco Salad Skillet', 'black beans', 'https://www.budgetbytes.com/taco-salad-skillet/', " +
                "'oil, ground beef, chili powder, cumin, oregano, garlic powder, cayenne pepper, salt, black beans, cheese, tortilla chips, iceberg lettuce, tomato, onion, sour cream');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Taco Salad Skillet', 'corn', 'https://www.budgetbytes.com/taco-salad-skillet/', " +
                "'oil, ground beef, chili powder, cumin, oregano, garlic powder, cayenne pepper, salt, black beans, cheese, tortilla chips, iceberg lettuce, tomato, onion, sour cream');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Slow Cooker Chicken Ropa Vieja', 'chicken', 'https://www.budgetbytes.com/slow-cooker-chicken-ropa-vieja/', " +
                "'chicken breast, garlic, oregano, cumin, red pepper flakes, onion, bell pepper, diced tomatoes, tomato paste, salt');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Slow Cooker Chicken Ropa Vieja', 'tomato', 'https://www.budgetbytes.com/slow-cooker-chicken-ropa-vieja/', " +
                "'chicken breast, garlic, oregano, cumin, red pepper flakes, onion, bell pepper, diced tomatoes, tomato paste, salt');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Slow Cooker Chicken Ropa Vieja', 'bell pepper', 'https://www.budgetbytes.com/slow-cooker-chicken-ropa-vieja/', " +
                "'chicken breast, garlic, oregano, cumin, red pepper flakes, onion, bell pepper, diced tomatoes, tomato paste, salt');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Slow Cooker Chicken Ropa Vieja', 'onion', 'https://www.budgetbytes.com/slow-cooker-chicken-ropa-vieja/', " +
                "'chicken breast, garlic, oregano, cumin, red pepper flakes, onion, bell pepper, diced tomatoes, tomato paste, salt');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Nacho Taters', 'potatoes', 'https://www.budgetbytes.com/nacho-taters/', " +
                "'potatoes, oil, corn, cheddar cheese, red enchilada sauce, black beans, green onion, jalapeno');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Nacho Taters', 'corn', 'https://www.budgetbytes.com/nacho-taters/', " +
                "'potatoes, oil, corn, cheddar cheese, red enchilada sauce, black beans, green onion, jalapeno');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Nacho Taters', 'cheddar cheese', 'https://www.budgetbytes.com/nacho-taters/', " +
                "'potatoes, oil, corn, cheddar cheese, red enchilada sauce, black beans, green onion, jalapeno');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Nacho Taters', 'black beans', 'https://www.budgetbytes.com/nacho-taters/', " +
                "'potatoes, oil, corn, cheddar cheese, red enchilada sauce, black beans, green onion, jalapeno');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Nacho Taters', 'jalapeno', 'https://www.budgetbytes.com/nacho-taters/', " +
                "'potatoes, oil, corn, cheddar cheese, red enchilada sauce, black beans, green onion, jalapeno');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Slow Cooker White Chicken Chili', 'chicken', 'https://www.budgetbytes.com/slow-cooker-white-chicken-chili/', " +
                "'onion, garlic, jalapeno, chicken, salsa verde, cannellini beans, pinto beans, cumin, oregano, cayenne pepper, black pepper, chicken broth, monterrey jack cheese');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Slow Cooker White Chicken Chili', 'onion', 'https://www.budgetbytes.com/slow-cooker-white-chicken-chili/', " +
                "'onion, garlic, jalapeno, chicken, salsa verde, cannellini beans, pinto beans, cumin, oregano, cayenne pepper, black pepper, chicken broth, monterrey jack cheese');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Slow Cooker White Chicken Chili', 'jalapeno', 'https://www.budgetbytes.com/slow-cooker-white-chicken-chili/', " +
                "'onion, garlic, jalapeno, chicken, salsa verde, cannellini beans, pinto beans, cumin, oregano, cayenne pepper, black pepper, chicken broth, monterrey jack cheese');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Slow Cooker White Chicken Chili', 'pinto beans', 'https://www.budgetbytes.com/slow-cooker-white-chicken-chili/', " +
                "'onion, garlic, jalapeno, chicken, salsa verde, cannellini beans, pinto beans, cumin, oregano, cayenne pepper, black pepper, chicken broth, monterrey jack cheese');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Slow Cooker White Chicken Chili', 'cannellini beans', 'https://www.budgetbytes.com/slow-cooker-white-chicken-chili/', " +
                "'onion, garlic, jalapeno, chicken, salsa verde, cannellini beans, pinto beans, cumin, oregano, cayenne pepper, black pepper, chicken broth, monterrey jack cheese');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Slow Cooker White Chicken Chili', 'monterrey jack cheese', 'https://www.budgetbytes.com/slow-cooker-white-chicken-chili/', " +
                "'onion, garlic, jalapeno, chicken, salsa verde, cannellini beans, pinto beans, cumin, oregano, cayenne pepper, black pepper, chicken broth, monterrey jack cheese');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Sweet Potato Tortilla Soup', 'sweet potato', 'https://www.budgetbytes.com/sweet-potato-tortilla-soup/', " +
                "'oil, onion, garlic, chili powder, cumin, cayenne pepper, corn meal, vegetable broth, tomato paste, diced tomato, black beans, sweet potato, corn, cilantro, avocado, cheddar cheese, sour cream, tortilla strips, lime');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Sweet Potato Tortilla Soup', 'sweet potato', 'https://www.budgetbytes.com/sweet-potato-tortilla-soup/', " +
                "'oil, onion, garlic, chili powder, cumin, cayenne pepper, corn meal, vegetable broth, tomato paste, diced tomato, black beans, sweet potato, corn, cilantro, avocado, cheddar cheese, sour cream, tortilla strips, lime');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Sweet Potato Tortilla Soup', 'lime', 'https://www.budgetbytes.com/sweet-potato-tortilla-soup/', " +
                "'oil, onion, garlic, chili powder, cumin, cayenne pepper, corn meal, vegetable broth, tomato paste, diced tomato, black beans, sweet potato, corn, cilantro, avocado, cheddar cheese, sour cream, tortilla strips, lime');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Sweet Potato Tortilla Soup', 'sour cream', 'https://www.budgetbytes.com/sweet-potato-tortilla-soup/', " +
                "'oil, onion, garlic, chili powder, cumin, cayenne pepper, corn meal, vegetable broth, tomato paste, diced tomato, black beans, sweet potato, corn, cilantro, avocado, cheddar cheese, sour cream, tortilla strips, lime');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Sweet Potato Tortilla Soup', 'cheddar cheese', 'https://www.budgetbytes.com/sweet-potato-tortilla-soup/', " +
                "'oil, onion, garlic, chili powder, cumin, cayenne pepper, corn meal, vegetable broth, tomato paste, diced tomato, black beans, sweet potato, corn, cilantro, avocado, cheddar cheese, sour cream, tortilla strips, lime');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Sweet Potato Tortilla Soup', 'black beans', 'https://www.budgetbytes.com/sweet-potato-tortilla-soup/', " +
                "'oil, onion, garlic, chili powder, cumin, cayenne pepper, corn meal, vegetable broth, tomato paste, diced tomato, black beans, sweet potato, corn, cilantro, avocado, cheddar cheese, sour cream, tortilla strips, lime');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Sweet Potato Tortilla Soup', 'avocado', 'https://www.budgetbytes.com/sweet-potato-tortilla-soup/', " +
                "'oil, onion, garlic, chili powder, cumin, cayenne pepper, corn meal, vegetable broth, tomato paste, diced tomato, black beans, sweet potato, corn, cilantro, avocado, cheddar cheese, sour cream, tortilla strips, lime');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Sweet Potato Tortilla Soup', 'corn', 'https://www.budgetbytes.com/sweet-potato-tortilla-soup/', " +
                "'oil, onion, garlic, chili powder, cumin, cayenne pepper, corn meal, vegetable broth, tomato paste, diced tomato, black beans, sweet potato, corn, cilantro, avocado, cheddar cheese, sour cream, tortilla strips, lime');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Beef and Pineapple Enchiladas', 'pineapple', 'https://www.budgetbytes.com/beef-pineapple-enchiladas/', " +
                "'oil, garlic, jalapeno, ground beef, black beans, chili powder, salt, pineapple chunks, onion, cilantro, corn tortillas, flour, tomato paste, cumin, garlic powder, cayenne pepper');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Beef and Pineapple Enchiladas', 'ground beef', 'https://www.budgetbytes.com/beef-pineapple-enchiladas/', " +
                "'oil, garlic, jalapeno, ground beef, black beans, chili powder, salt, pineapple chunks, onion, cilantro, corn tortillas, flour, tomato paste, cumin, garlic powder, cayenne pepper');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Beef and Pineapple Enchiladas', 'black beans', 'https://www.budgetbytes.com/beef-pineapple-enchiladas/', " +
                "'oil, garlic, jalapeno, ground beef, black beans, chili powder, salt, pineapple chunks, onion, cilantro, corn tortillas, flour, tomato paste, cumin, garlic powder, cayenne pepper');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Beef and Pineapple Enchiladas', 'onion', 'https://www.budgetbytes.com/beef-pineapple-enchiladas/', " +
                "'oil, garlic, jalapeno, ground beef, black beans, chili powder, salt, pineapple chunks, onion, cilantro, corn tortillas, flour, tomato paste, cumin, garlic powder, cayenne pepper');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Beef and Pineapple Enchiladas', 'jalapeno', 'https://www.budgetbytes.com/beef-pineapple-enchiladas/', " +
                "'oil, garlic, jalapeno, ground beef, black beans, chili powder, salt, pineapple chunks, onion, cilantro, corn tortillas, flour, tomato paste, cumin, garlic powder, cayenne pepper');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Creamy Vegetarian Enchilada Pasta', 'corn', 'https://www.budgetbytes.com/creamy-vegetarian-enchilada-pasta/', " +
                "'oil, garlic, green chiles, corn, black beans, cumin, red pepper flakes, egg noodles, vegetable broth, sour cream, green onions, cilantro, pepper jack cheese');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Creamy Vegetarian Enchilada Pasta', 'black beans', 'https://www.budgetbytes.com/creamy-vegetarian-enchilada-pasta/', " +
                "'oil, garlic, green chiles, corn, black beans, cumin, red pepper flakes, egg noodles, vegetable broth, sour cream, green onions, cilantro, pepper jack cheese');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Creamy Vegetarian Enchilada Pasta', 'sour cream', 'https://www.budgetbytes.com/creamy-vegetarian-enchilada-pasta/', " +
                "'oil, garlic, green chiles, corn, black beans, cumin, red pepper flakes, egg noodles, vegetable broth, sour cream, green onions, cilantro, pepper jack cheese');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Creamy Vegetarian Enchilada Pasta', 'pepper jack cheese', 'https://www.budgetbytes.com/creamy-vegetarian-enchilada-pasta/', " +
                "'oil, garlic, green chiles, corn, black beans, cumin, red pepper flakes, egg noodles, vegetable broth, sour cream, green onions, cilantro, pepper jack cheese');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Slow Cooker Black Bean Soup', 'onion', 'https://www.budgetbytes.com/slow-cooker-black-bean-soup/', " +
                "'garlic, onion, celery, carrots, black beans, salsa, chili powder, cumin, oregano, vegetable broth');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Slow Cooker Black Bean Soup', 'celery', 'https://www.budgetbytes.com/slow-cooker-black-bean-soup/', " +
                "'garlic, onion, celery, carrots, black beans, salsa, chili powder, cumin, oregano, vegetable broth');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Slow Cooker Black Bean Soup', 'carrots', 'https://www.budgetbytes.com/slow-cooker-black-bean-soup/', " +
                "'garlic, onion, celery, carrots, black beans, salsa, chili powder, cumin, oregano, vegetable broth');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Slow Cooker Black Bean Soup', 'black beans', 'https://www.budgetbytes.com/slow-cooker-black-bean-soup/', " +
                "'garlic, onion, celery, carrots, black beans, salsa, chili powder, cumin, oregano, vegetable broth');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Chorizo Sweet Potato Skillet', 'sweet potato', 'https://www.budgetbytes.com/chorizo-sweet-potato-skillet/', " +
                "'olive oil, sweet potato, chorizo, black beans, rice, salsa, chicken broth, cheese, green onion');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Chorizo Sweet Potato Skillet', 'chorizo', 'https://www.budgetbytes.com/chorizo-sweet-potato-skillet/', " +
                "'olive oil, sweet potato, chorizo, black beans, rice, salsa, chicken broth, cheese, green onion');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Chorizo Sweet Potato Skillet', 'black beans', 'https://www.budgetbytes.com/chorizo-sweet-potato-skillet/', " +
                "'olive oil, sweet potato, chorizo, black beans, rice, salsa, chicken broth, cheese, green onion');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Chorizo Sweet Potato Skillet', 'cheddar cheese', 'https://www.budgetbytes.com/chorizo-sweet-potato-skillet/', " +
                "'olive oil, sweet potato, chorizo, black beans, rice, salsa, chicken broth, cheese, green onion');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Southwest Chicken Skillet', 'chicken', 'https://www.budgetbytes.com/southwest-chicken-skillet/', " +
                "'white rice, salsa, shredded chicken, black beans, chili powder, chicken broth, shredded cheese, green onion');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Southwest Chicken Skillet', 'black beans', 'https://www.budgetbytes.com/southwest-chicken-skillet/', " +
                "'white rice, salsa, shredded chicken, black beans, chili powder, chicken broth, shredded cheese, green onion');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Southwest Chicken Skillet', 'cheddar cheese', 'https://www.budgetbytes.com/southwest-chicken-skillet/', " +
                "'white rice, salsa, shredded chicken, black beans, chili powder, chicken broth, shredded cheese, green onion');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Chicken Green Chile Enchiladas', 'chicken', 'https://www.budgetbytes.com/chicken-turkey-green-chile-enchiladas/', " +
                "'chicken, diced tomatoes with chiles, diced green chiles, Monterrey jack cheese, cilantro, tortillas, enchilada sauce');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Chicken Green Chile Enchiladas', 'monterrey jack cheese', 'https://www.budgetbytes.com/chicken-turkey-green-chile-enchiladas/', " +
                "'chicken, diced tomatoes with chiles, diced green chiles, Monterrey jack cheese, cilantro, tortillas, enchilada sauce');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Turkey Green Chile Enchiladas', 'turkey', 'https://www.budgetbytes.com/chicken-turkey-green-chile-enchiladas/', " +
                "'turkey, diced tomatoes with chiles, diced green chiles, Monterrey jack cheese, cilantro, tortillas, enchilada sauce');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Turkey Green Chile Enchiladas', 'cheeese', 'https://www.budgetbytes.com/chicken-turkey-green-chile-enchiladas/', " +
                "'turkey, diced tomatoes with chiles, diced green chiles, Monterrey jack cheese, cilantro, tortillas, enchilada sauce');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Chorizo Sweet Potato Chili', 'chorizo', 'https://www.budgetbytes.com/chorizo-sweet-potato-chili/', " +
                "'chorizo, onion, garlic, sweet potato, diced tomatoes, kidney beans, black beans, tomato paste, chili powder, cumin, oregano, green onion');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Chorizo Sweet Potato Chili', 'onion', 'https://www.budgetbytes.com/chorizo-sweet-potato-chili/', " +
                "'chorizo, onion, garlic, sweet potato, diced tomatoes, kidney beans, black beans, tomato paste, chili powder, cumin, oregano, green onion');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Chorizo Sweet Potato Chili', 'sweet potato', 'https://www.budgetbytes.com/chorizo-sweet-potato-chili/', " +
                "'chorizo, onion, garlic, sweet potato, diced tomatoes, kidney beans, black beans, tomato paste, chili powder, cumin, oregano, green onion');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Chorizo Sweet Potato Chili', 'kidney beans', 'https://www.budgetbytes.com/chorizo-sweet-potato-chili/', " +
                "'chorizo, onion, garlic, sweet potato, diced tomatoes, kidney beans, black beans, tomato paste, chili powder, cumin, oregano, green onion');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Chorizo Sweet Potato Chili', 'black beans', 'https://www.budgetbytes.com/chorizo-sweet-potato-chili/', " +
                "'chorizo, onion, garlic, sweet potato, diced tomatoes, kidney beans, black beans, tomato paste, chili powder, cumin, oregano, green onion');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Chili Cornbread Skillet', 'jalapeno', 'https://www.budgetbytes.com/chili-cornbread-skillet/', " +
                "'olive oil, garlic, onion, jalapeno, tomato paste, fire roasted tomatoes, pinto beans, chili powder, cumin, oregano, salt, cheddar cheese, cornmeal, flour, sugar, baking powder, milk, egg, vegetable oil');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Chili Cornbread Skillet', 'pinto beans', 'https://www.budgetbytes.com/chili-cornbread-skillet/', " +
                "'olive oil, garlic, onion, jalapeno, tomato paste, fire roasted tomatoes, pinto beans, chili powder, cumin, oregano, salt, cheddar cheese, cornmeal, flour, sugar, baking powder, milk, egg, vegetable oil');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Chili Cornbread Skillet', 'cheddar cheese', 'https://www.budgetbytes.com/chili-cornbread-skillet/', " +
                "'olive oil, garlic, onion, jalapeno, tomato paste, fire roasted tomatoes, pinto beans, chili powder, cumin, oregano, salt, cheddar cheese, cornmeal, flour, sugar, baking powder, milk, egg, vegetable oil');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Chili Cornbread Skillet', 'milk', 'https://www.budgetbytes.com/chili-cornbread-skillet/', " +
                "'olive oil, garlic, onion, jalapeno, tomato paste, fire roasted tomatoes, pinto beans, chili powder, cumin, oregano, salt, cheddar cheese, cornmeal, flour, sugar, baking powder, milk, egg, vegetable oil');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Chili Cornbread Skillet', 'egg', 'https://www.budgetbytes.com/chili-cornbread-skillet/', " +
                "'olive oil, garlic, onion, jalapeno, tomato paste, fire roasted tomatoes, pinto beans, chili powder, cumin, oregano, salt, cheddar cheese, cornmeal, flour, sugar, baking powder, milk, egg, vegetable oil');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Chili Cornbread Skillet', 'onion', 'https://www.budgetbytes.com/chili-cornbread-skillet/', " +
                "'olive oil, garlic, onion, jalapeno, tomato paste, fire roasted tomatoes, pinto beans, chili powder, cumin, oregano, salt, cheddar cheese, cornmeal, flour, sugar, baking powder, milk, egg, vegetable oil');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Beef Taco Pasta', 'ground beef', 'https://www.budgetbytes.com/beef-taco-pasta/', " +
                "'olive oil, onion, garlic, ground beef, chili powder, cumin, oregano, cayenne pepper, diced tomatoes, diced chiles, beef broth, egg noodles, black olives, cheddar cheese, green onion');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Beef Taco Pasta', 'onion', 'https://www.budgetbytes.com/beef-taco-pasta/', " +
                "'olive oil, onion, garlic, ground beef, chili powder, cumin, oregano, cayenne pepper, diced tomatoes, diced chiles, beef broth, egg noodles, black olives, cheddar cheese, green onion');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Beef Taco Pasta', 'cheddar cheese', 'https://www.budgetbytes.com/beef-taco-pasta/', " +
                "'olive oil, onion, garlic, ground beef, chili powder, cumin, oregano, cayenne pepper, diced tomatoes, diced chiles, beef broth, egg noodles, black olives, cheddar cheese, green onion');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Black Bean and Avocado Enchiladas', 'corn', 'https://www.budgetbytes.com/black-bean-avocado-enchiladas/', " +
                "'vegetable oil, flour, chile powder, tomato paste, cumin, garlic powder, cayenne pepper, unsweetened cocoa powder, black beans, avocado, tomato, onion, corn, cilantro, salt, tortillas');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Black Bean and Avocado Enchiladas', 'onion', 'https://www.budgetbytes.com/black-bean-avocado-enchiladas/', " +
                "'vegetable oil, flour, chile powder, tomato paste, cumin, garlic powder, cayenne pepper, unsweetened cocoa powder, black beans, avocado, tomato, onion, corn, cilantro, salt, tortillas');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Black Bean and Avocado Enchiladas', 'tomato', 'https://www.budgetbytes.com/black-bean-avocado-enchiladas/', " +
                "'vegetable oil, flour, chile powder, tomato paste, cumin, garlic powder, cayenne pepper, unsweetened cocoa powder, black beans, avocado, tomato, onion, corn, cilantro, salt, tortillas');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Black Bean and Avocado Enchiladas', 'avocado', 'https://www.budgetbytes.com/black-bean-avocado-enchiladas/', " +
                "'vegetable oil, flour, chile powder, tomato paste, cumin, garlic powder, cayenne pepper, unsweetened cocoa powder, black beans, avocado, tomato, onion, corn, cilantro, salt, tortillas');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Black Bean and Avocado Enchiladas', 'black beans', 'https://www.budgetbytes.com/black-bean-avocado-enchiladas/', " +
                "'vegetable oil, flour, chile powder, tomato paste, cumin, garlic powder, cayenne pepper, unsweetened cocoa powder, black beans, avocado, tomato, onion, corn, cilantro, salt, tortillas');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Easy Oven Fajitas', 'chicken', 'https://www.budgetbytes.com/oven-fajitas/', " +
                "'chili powder, paprika, onion powder, garlic powder, cumin, cayenne, sugar, salt, onion, bell peppers, chicken breast, vegetable oil, lime, tortillas, sour cream, cilantro');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Easy Oven Fajitas', 'onion', 'https://www.budgetbytes.com/oven-fajitas/', " +
                "'chili powder, paprika, onion powder, garlic powder, cumin, cayenne, sugar, salt, onion, bell peppers, chicken breast, vegetable oil, lime, tortillas, sour cream, cilantro');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Easy Oven Fajitas', 'bell pepper', 'https://www.budgetbytes.com/oven-fajitas/', " +
                "'chili powder, paprika, onion powder, garlic powder, cumin, cayenne, sugar, salt, onion, bell peppers, chicken breast, vegetable oil, lime, tortillas, sour cream, cilantro');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Easy Oven Fajitas', 'sour cream', 'https://www.budgetbytes.com/oven-fajitas/', " +
                "'chili powder, paprika, onion powder, garlic powder, cumin, cayenne, sugar, salt, onion, bell peppers, chicken breast, vegetable oil, lime, tortillas, sour cream, cilantro');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Easy Oven Fajitas', 'lime', 'https://www.budgetbytes.com/oven-fajitas/', " +
                "'chili powder, paprika, onion powder, garlic powder, cumin, cayenne, sugar, salt, onion, bell peppers, chicken breast, vegetable oil, lime, tortillas, sour cream, cilantro');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Spicy Coconut Vegetable Stir Fry', 'coconut milk', 'https://www.budgetbytes.com/spicy-coconut-vegetable-stir-fry/', " +
                "'coconut milk, peanut butter, sriracha sauce, brown sugar, soy sauce, lime juice, garlic, ginger, mixed vegetables, cooking oil, rice, peanuts, cilantro, lime');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Spicy Coconut Vegetable Stir Fry', 'lime', 'https://www.budgetbytes.com/spicy-coconut-vegetable-stir-fry/', " +
                "'coconut milk, peanut butter, sriracha sauce, brown sugar, soy sauce, lime juice, garlic, ginger, mixed vegetables, cooking oil, rice, peanuts, cilantro, lime');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Lobster and Cream Cheese Wontons', 'lobster', 'https://www.budgetbytes.com/lobster-cream-cheese-wontons/', " +
                "'cream cheese, imitation lobster, egg, green onion, wanton wrappers, sriracha');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Lobster and Cream Cheese Wontons', 'cream cheese', 'https://www.budgetbytes.com/lobster-cream-cheese-wontons/', " +
                "'cream cheese, imitation lobster, egg, green onion, wanton wrappers, sriracha');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Lobster and Cream Cheese Wontons', 'egg', 'https://www.budgetbytes.com/lobster-cream-cheese-wontons/', " +
                "'cream cheese, imitation lobster, egg, green onion, wanton wrappers, sriracha');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Crab and Cream Cheese Wontons', 'crab', 'https://www.budgetbytes.com/lobster-cream-cheese-wontons/', " +
                "'cream cheese, imitation crab, egg, green onion, wanton wrappers, sriracha');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Crab and Cream Cheese Wontons', 'cream cheese', 'https://www.budgetbytes.com/lobster-cream-cheese-wontons/', " +
                "'cream cheese, imitation crab, egg, green onion, wanton wrappers, sriracha');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Crab and Cream Cheese Wontons', 'egg', 'https://www.budgetbytes.com/lobster-cream-cheese-wontons/', " +
                "'cream cheese, imitation crab, egg, green onion, wanton wrappers, sriracha');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Spring Rolls & Quick Peanut Sauce', 'crab', 'https://www.budgetbytes.com/spring-rolls-quick-peanut-sauce/', " +
                "'rice paper wrappers, red leaf lettuce, bean threads, mango, crab, peanut butter, hoisin sauce, ginger, sriracha');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Spring Rolls & Quick Peanut Sauce', 'lettuce', 'https://www.budgetbytes.com/spring-rolls-quick-peanut-sauce/', " +
                "'rice paper wrappers, red leaf lettuce, bean threads, mango, crab, peanut butter, hoisin sauce, ginger, sriracha');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Spring Rolls & Quick Peanut Sauce', 'mango', 'https://www.budgetbytes.com/spring-rolls-quick-peanut-sauce/', " +
                "'rice paper wrappers, red leaf lettuce, bean threads, mango, crab, peanut butter, hoisin sauce, ginger, sriracha');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Hot and Sour Vegetable Soup With Tofu', 'tofu', 'https://www.budgetbytes.com/hot-sour-vegetable-soup-with-tofu/', " +
                "'canola oil, ginger, green onion, red cabbage, carrots, button mushrooms, vegetable broth, soy sauce, rice vinegar, chili garlic sauce, tofu');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Hot and Sour Vegetable Soup With Tofu', 'cabbage', 'https://www.budgetbytes.com/hot-sour-vegetable-soup-with-tofu/', " +
                "'canola oil, ginger, green onion, red cabbage, carrots, button mushrooms, vegetable broth, soy sauce, rice vinegar, chili garlic sauce, tofu');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Hot and Sour Vegetable Soup With Tofu', 'carrot', 'https://www.budgetbytes.com/hot-sour-vegetable-soup-with-tofu/', " +
                "'canola oil, ginger, green onion, red cabbage, carrots, button mushrooms, vegetable broth, soy sauce, rice vinegar, chili garlic sauce, tofu');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Hot and Sour Vegetable Soup With Tofu', 'mushroom', 'https://www.budgetbytes.com/hot-sour-vegetable-soup-with-tofu/', " +
                "'canola oil, ginger, green onion, red cabbage, carrots, button mushrooms, vegetable broth, soy sauce, rice vinegar, chili garlic sauce, tofu');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Spicy Cashew Crunch Stir Fry', 'broccoli', 'https://www.budgetbytes.com/spicy-cashew-crunch-stir-fry/', " +
                "'soy sauce, garlic chili sauce, brown sugar, ginger, toasted sesame oil, cornstarch, vegetable oil, broccoli, carrot, bell pepper, yellow onion, cashews, green onion, rice');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Spicy Cashew Crunch Stir Fry', 'carrot', 'https://www.budgetbytes.com/spicy-cashew-crunch-stir-fry/', " +
                "'soy sauce, garlic chili sauce, brown sugar, ginger, toasted sesame oil, cornstarch, vegetable oil, broccoli, carrot, bell pepper, yellow onion, cashews, green onion, rice');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Spicy Cashew Crunch Stir Fry', 'bell pepper', 'https://www.budgetbytes.com/spicy-cashew-crunch-stir-fry/', " +
                "'soy sauce, garlic chili sauce, brown sugar, ginger, toasted sesame oil, cornstarch, vegetable oil, broccoli, carrot, bell pepper, yellow onion, cashews, green onion, rice');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Spicy Cashew Crunch Stir Fry', 'onion', 'https://www.budgetbytes.com/spicy-cashew-crunch-stir-fry/', " +
                "'soy sauce, garlic chili sauce, brown sugar, ginger, toasted sesame oil, cornstarch, vegetable oil, broccoli, carrot, bell pepper, yellow onion, cashews, green onion, rice');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Slow Cooker 5 Spice Chicken', 'chicken', 'https://www.budgetbytes.com/slow-cooker-5-spice-chicken/', " +
                "'garlic, ginger, vegetable oil, soy sauce, brown sugar, 5 spice powder, chicken pieces, onion, rice wine');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Slow Cooker 5 Spice Chicken', 'onion', 'https://www.budgetbytes.com/slow-cooker-5-spice-chicken/', " +
                "'garlic, ginger, vegetable oil, soy sauce, brown sugar, 5 spice powder, chicken pieces, onion, rice wine');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Vegetable Stir Fry With Noodles', 'cabbage', 'https://www.budgetbytes.com/snap-challenge-vegetable-stir-fry-noodles/', " +
                "'vegetable oil, purple cabbage, bell pepper, carrot, yellow onion, ramen noodles, soy sauce, brown sugar, sriracha, cornstarch, green onion, cilantro');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Vegetable Stir Fry With Noodles', 'bell pepper', 'https://www.budgetbytes.com/snap-challenge-vegetable-stir-fry-noodles/', " +
                "'vegetable oil, purple cabbage, bell pepper, carrot, yellow onion, ramen noodles, soy sauce, brown sugar, sriracha, cornstarch, green onion, cilantro');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Vegetable Stir Fry With Noodles', 'carrot', 'https://www.budgetbytes.com/snap-challenge-vegetable-stir-fry-noodles/', " +
                "'vegetable oil, purple cabbage, bell pepper, carrot, yellow onion, ramen noodles, soy sauce, brown sugar, sriracha, cornstarch, green onion, cilantro');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Vegetable Stir Fry With Noodles', 'onion', 'https://www.budgetbytes.com/snap-challenge-vegetable-stir-fry-noodles/', " +
                "'vegetable oil, purple cabbage, bell pepper, carrot, yellow onion, ramen noodles, soy sauce, brown sugar, sriracha, cornstarch, green onion, cilantro');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Kung Pao Chicken (And Vegetables)', 'chicken', 'https://www.budgetbytes.com/kung-pao-chicken-vegetables/', " +
                "'oyster sauce, balsamin vinegar, soy sauce, brown sugar, toasted sesame oil, crushed red pepper flakes, ginger, garlic, cornstarch, chicken breast, egg, black pepper, vegetable oil, broccoli, red bell pepper, green onion, peanuts, rice');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Kung Pao Chicken (And Vegetables)', 'eggs', 'https://www.budgetbytes.com/kung-pao-chicken-vegetables/', " +
                "'oyster sauce, balsamin vinegar, soy sauce, brown sugar, toasted sesame oil, crushed red pepper flakes, ginger, garlic, cornstarch, chicken breast, egg, black pepper, vegetable oil, broccoli, red bell pepper, green onion, peanuts, rice');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Kung Pao Chicken (And Vegetables)', 'broccoli', 'https://www.budgetbytes.com/kung-pao-chicken-vegetables/', " +
                "'oyster sauce, balsamin vinegar, soy sauce, brown sugar, toasted sesame oil, crushed red pepper flakes, ginger, garlic, cornstarch, chicken breast, egg, black pepper, vegetable oil, broccoli, red bell pepper, green onion, peanuts, rice');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Kung Pao Chicken (And Vegetables)', 'bell pepper', 'https://www.budgetbytes.com/kung-pao-chicken-vegetables/', " +
                "'oyster sauce, balsamin vinegar, soy sauce, brown sugar, toasted sesame oil, crushed red pepper flakes, ginger, garlic, cornstarch, chicken breast, egg, black pepper, vegetable oil, broccoli, red bell pepper, green onion, peanuts, rice');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Kung Pao Chicken (And Vegetables)', 'onion', 'https://www.budgetbytes.com/kung-pao-chicken-vegetables/', " +
                "'oyster sauce, balsamin vinegar, soy sauce, brown sugar, toasted sesame oil, crushed red pepper flakes, ginger, garlic, cornstarch, chicken breast, egg, black pepper, vegetable oil, broccoli, red bell pepper, green onion, peanuts, rice');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Hoisin Beef Tacos With Sweet and Sour Slaw', 'shredded beef', 'https://www.budgetbytes.com/hoisin-beef-tacos-with-sweet-sour-slaw/', " +
                "'rice vinegar, sugar, salt, toasted sesame oil, crushed red pepper, shredded cabbage, carrot, multi-purpose shredded beef, hoisin sauce, tortillas, cilantro');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Hoisin Beef Tacos With Sweet and Sour Slaw', 'cabbage', 'https://www.budgetbytes.com/hoisin-beef-tacos-with-sweet-sour-slaw/', " +
                "'rice vinegar, sugar, salt, toasted sesame oil, crushed red pepper, shredded cabbage, carrot, multi-purpose shredded beef, hoisin sauce, tortillas, cilantro');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Hoisin Beef Tacos With Sweet and Sour Slaw', 'carrot', 'https://www.budgetbytes.com/hoisin-beef-tacos-with-sweet-sour-slaw/', " +
                "'rice vinegar, sugar, salt, toasted sesame oil, crushed red pepper, shredded cabbage, carrot, multi-purpose shredded beef, hoisin sauce, tortillas, cilantro');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Chicken in Peanut Sauce', 'chicken', 'https://www.budgetbytes.com/chicken-in-peanut-sauce/', " +
                "'garlic, ginger, vegetable oil, chicken breast, coconut milk, peanut butter, soy sauce, brown sugar, sriracha, lime, cilantro, jasmine rice');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Chicken in Peanut Sauce', 'lime', 'https://www.budgetbytes.com/chicken-in-peanut-sauce/', " +
                "'garlic, ginger, vegetable oil, chicken breast, coconut milk, peanut butter, soy sauce, brown sugar, sriracha, lime, cilantro, jasmine rice');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Thai Turkey Meatloaf', 'ground turkey', 'https://www.budgetbytes.com/thai-turkey-meatloaf/', " +
                "'ground turkey, garlic, green onion, ginger, soy sauce, toasted sesame oil, eggs, jasmine rice, brown sugar, ketchup, sriracha, rice vinegar');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Thai Turkey Meatloaf', 'egg', 'https://www.budgetbytes.com/thai-turkey-meatloaf/', " +
                "'ground turkey, garlic, green onion, ginger, soy sauce, toasted sesame oil, eggs, jasmine rice, brown sugar, ketchup, sriracha, rice vinegar');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Homemade Chicken Yakisoba', 'broccoli', 'https://www.budgetbytes.com/chicken-yakisoba/', " +
                "'green cabbage, yellow onion, carrots, broccoli, ginger, chicken breast, vegetable oil, ramen noodles, sesame oil, soy sauce, worcestershire sauce, ketchup, sriracha, sugar');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Homemade Chicken Yakisoba', 'chicken', 'https://www.budgetbytes.com/chicken-yakisoba/', " +
                "'green cabbage, yellow onion, carrots, broccoli, ginger, chicken breast, vegetable oil, ramen noodles, sesame oil, soy sauce, worcestershire sauce, ketchup, sriracha, sugar');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Homemade Chicken Yakisoba', 'carrots', 'https://www.budgetbytes.com/chicken-yakisoba/', " +
                "'green cabbage, yellow onion, carrots, broccoli, ginger, chicken breast, vegetable oil, ramen noodles, sesame oil, soy sauce, worcestershire sauce, ketchup, sriracha, sugar');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Homemade Chicken Yakisoba', 'onion', 'https://www.budgetbytes.com/chicken-yakisoba/', " +
                "'green cabbage, yellow onion, carrots, broccoli, ginger, chicken breast, vegetable oil, ramen noodles, sesame oil, soy sauce, worcestershire sauce, ketchup, sriracha, sugar');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Homemade Chicken Yakisoba', 'cabbage', 'https://www.budgetbytes.com/chicken-yakisoba/', " +
                "'green cabbage, yellow onion, carrots, broccoli, ginger, chicken breast, vegetable oil, ramen noodles, sesame oil, soy sauce, worcestershire sauce, ketchup, sriracha, sugar');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Sweet Chili Chicken Stir Fry Bowls', 'chicken', 'https://www.budgetbytes.com/sweet-chili-chicken-stir-fry-bowls/', " +
                "'jasmine rice, garlic, salt, coconut milk, chicken breast, cooking oil, sweet chili sauce, pineapple tidbits, broccoli');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Sweet Chili Chicken Stir Fry Bowls', 'broccoli', 'https://www.budgetbytes.com/sweet-chili-chicken-stir-fry-bowls/', " +
                "'jasmine rice, garlic, salt, coconut milk, chicken breast, cooking oil, sweet chili sauce, pineapple tidbits, broccoli');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Sweet Chili Chicken Stir Fry Bowls', 'pineapple', 'https://www.budgetbytes.com/sweet-chili-chicken-stir-fry-bowls/', " +
                "'jasmine rice, garlic, salt, coconut milk, chicken breast, cooking oil, sweet chili sauce, pineapple tidbits, broccoli');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Mushroom Broccoli Stir Fry Noodles', 'broccoli', 'https://www.budgetbytes.com/simple-mushroom-broccoli-stir-fry-noodles/', " +
                "'soy sauce, toasted sesame oil, chili garlic sauce, brown sugar, cornstarch, rice noodles, cooking oil, white mushrooms, garlic, broccoli, green onion');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Mushroom Broccoli Stir Fry Noodles', 'mushrooms', 'https://www.budgetbytes.com/simple-mushroom-broccoli-stir-fry-noodles/', " +
                "'soy sauce, toasted sesame oil, chili garlic sauce, brown sugar, cornstarch, rice noodles, cooking oil, white mushrooms, garlic, broccoli, green onion');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Peanut Lime Cauliflower Salad', 'cauliflower', 'https://www.budgetbytes.com/peanut-lime-cauliflower-salad/', " +
                "'coconut oil, cauliflower, salt ginger, lime, soy sauce, chili garlic sauce, bell pepper, red onion, peanuts, cilantro');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Peanut Lime Cauliflower Salad', 'lime', 'https://www.budgetbytes.com/peanut-lime-cauliflower-salad/', " +
                "'coconut oil, cauliflower, salt ginger, lime, soy sauce, chili garlic sauce, bell pepper, red onion, peanuts, cilantro');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Peanut Lime Cauliflower Salad', 'bell pepper', 'https://www.budgetbytes.com/peanut-lime-cauliflower-salad/', " +
                "'coconut oil, cauliflower, salt ginger, lime, soy sauce, chili garlic sauce, bell pepper, red onion, peanuts, cilantro');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Peanut Lime Cauliflower Salad', 'onion', 'https://www.budgetbytes.com/peanut-lime-cauliflower-salad/', " +
                "'coconut oil, cauliflower, salt ginger, lime, soy sauce, chili garlic sauce, bell pepper, red onion, peanuts, cilantro');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Sushi Bowls with Sriracha Mayo', 'crab', 'https://www.budgetbytes.com/sushi-bowls-sriracha-mayo/', " +
                "'white rice, rice vinegar, sugar, salt, crab stick, carrot, cucumber, avocado, nori snack pack, sesame seeds, mayonnaise, sriracha');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Sushi Bowls with Sriracha Mayo', 'carrot', 'https://www.budgetbytes.com/sushi-bowls-sriracha-mayo/', " +
                "'white rice, rice vinegar, sugar, salt, crab stick, carrot, cucumber, avocado, nori snack pack, sesame seeds, mayonnaise, sriracha');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Sushi Bowls with Sriracha Mayo', 'cucumber', 'https://www.budgetbytes.com/sushi-bowls-sriracha-mayo/', " +
                "'white rice, rice vinegar, sugar, salt, crab stick, carrot, cucumber, avocado, nori snack pack, sesame seeds, mayonnaise, sriracha');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Sushi Bowls with Sriracha Mayo', 'avocado', 'https://www.budgetbytes.com/sushi-bowls-sriracha-mayo/', " +
                "'white rice, rice vinegar, sugar, salt, crab stick, carrot, cucumber, avocado, nori snack pack, sesame seeds, mayonnaise, sriracha');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Cauliflower and Chickpea Masala', 'cauliflower', 'https://www.budgetbytes.com/easy-cauliflower-and-chickpea-masala/', " +
                "'garam masala, cumin, turmeric, smoked paprika, salt, cayenne, cracked pepper, onion, garlic, ginger, olive oil, cauliflower, chickpeas, tomato sauce, heavy cream, salt');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Cauliflower and Chickpea Masala', 'onion', 'https://www.budgetbytes.com/easy-cauliflower-and-chickpea-masala/', " +
                "'garam masala, cumin, turmeric, smoked paprika, salt, cayenne, cracked pepper, onion, garlic, ginger, olive oil, cauliflower, chickpeas, tomato sauce, heavy cream, salt');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Slow Cooker Coconut Curry Lentils', 'sweet potato', 'https://www.budgetbytes.com/slow-cooker-coconut-curry-lentils/', " +
                "'yellow onion, garlic, brown lentils, sweet potato, carrots, curry powder, ground cloves, petite diced tomatoes, tomato sauce, vegetable broth, coconut milk, rice, red onion, cilantro');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Slow Cooker Coconut Curry Lentils', 'carrot', 'https://www.budgetbytes.com/slow-cooker-coconut-curry-lentils/', " +
                "'yellow onion, garlic, brown lentils, sweet potato, carrots, curry powder, ground cloves, petite diced tomatoes, tomato sauce, vegetable broth, coconut milk, rice, red onion, cilantro');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Slow Cooker Coconut Curry Lentils', 'onion', 'https://www.budgetbytes.com/slow-cooker-coconut-curry-lentils/', " +
                "'yellow onion, garlic, brown lentils, sweet potato, carrots, curry powder, ground cloves, petite diced tomatoes, tomato sauce, vegetable broth, coconut milk, rice, red onion, cilantro');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Coconut Vegetable Curry', 'cauliflower', 'https://www.budgetbytes.com/coconut-vegetable-curry/', " +
                "'olive oil, garlic, ginger, curry powder, carrots, onion, tomato paste, diced tomatoes, broccoli, cauliflower, coconut milk, salt, sugar, cilantro');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Coconut Vegetable Curry', 'broccoli', 'https://www.budgetbytes.com/coconut-vegetable-curry/', " +
                "'olive oil, garlic, ginger, curry powder, carrots, onion, tomato paste, diced tomatoes, broccoli, cauliflower, coconut milk, salt, sugar, cilantro');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Coconut Vegetable Curry', 'carrot', 'https://www.budgetbytes.com/coconut-vegetable-curry/', " +
                "'olive oil, garlic, ginger, curry powder, carrots, onion, tomato paste, diced tomatoes, broccoli, cauliflower, coconut milk, salt, sugar, cilantro');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Coconut Vegetable Curry', 'onion', 'https://www.budgetbytes.com/coconut-vegetable-curry/', " +
                "'olive oil, garlic, ginger, curry powder, carrots, onion, tomato paste, diced tomatoes, broccoli, cauliflower, coconut milk, salt, sugar, cilantro');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Chana Saag', 'spinach', 'https://www.budgetbytes.com/chana-saag/', " +
                "'olive oil, onion, garlic, ginger, curry powder, cumin, salt, tomato, spinach, chickpeas, evaporated milk');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Chana Saag', 'chickpeas', 'https://www.budgetbytes.com/chana-saag/', " +
                "'olive oil, onion, garlic, ginger, curry powder, cumin, salt, tomato, spinach, chickpeas, evaporated milk, \n');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Spinach Artichoke Lasagna', 'spinach', 'https://www.budgetbytes.com/spinach-artichoke-lasagna/', " +
                "'spinach, artichoke hearts, ricotta cheese, mozzarella cheese, parmesan cheese, eggs, garlic, pepper, salt, marinara sauce, no-boil noodles');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Spinach Artichoke Lasagna', 'ricotta cheese', 'https://www.budgetbytes.com/spinach-artichoke-lasagna/', " +
                "'spinach, artichoke hearts, ricotta cheese, mozzarella cheese, parmesan cheese, eggs, garlic, pepper, salt, marinara sauce, no-boil noodles');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Spinach Artichoke Lasagna', 'mozzarella cheese', 'https://www.budgetbytes.com/spinach-artichoke-lasagna/', " +
                "'spinach, artichoke hearts, ricotta cheese, mozzarella cheese, parmesan cheese, eggs, garlic, pepper, salt, marinara sauce, no-boil noodles');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Spinach Artichoke Lasagna', 'parmesan cheese', 'https://www.budgetbytes.com/spinach-artichoke-lasagna/', " +
                "'spinach, artichoke hearts, ricotta cheese, mozzarella cheese, parmesan cheese, eggs, garlic, pepper, salt, marinara sauce, no-boil noodles');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Spinach Artichoke Lasagna', 'eggs', 'https://www.budgetbytes.com/spinach-artichoke-lasagna/', " +
                "'spinach, artichoke hearts, ricotta cheese, mozzarella cheese, parmesan cheese, eggs, garlic, pepper, salt, marinara sauce, no-boil noodles');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Spinach Lasagna Roll Ups', 'ricotta cheese', 'https://www.budgetbytes.com/spinach-lasagna-roll-ups/', " +
                "'lasagna noodles, ricotta, parmesan cheese, mozzarella cheese, eggs, spinach, marinara sauce, salt, pepper');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Spinach Lasagna Roll Ups', 'parmesan cheese', 'https://www.budgetbytes.com/spinach-lasagna-roll-ups/', " +
                "'lasagna noodles, ricotta, parmesan cheese, mozzarella cheese, eggs, spinach, marinara sauce, salt, pepper');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Spinach Lasagna Roll Ups', 'mozzarella cheese', 'https://www.budgetbytes.com/spinach-lasagna-roll-ups/', " +
                "'lasagna noodles, ricotta, parmesan cheese, mozzarella cheese, eggs, spinach, marinara sauce, salt, pepper');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Spinach Lasagna Roll Ups', 'eggs', 'https://www.budgetbytes.com/spinach-lasagna-roll-ups/', " +
                "'lasagna noodles, ricotta, parmesan cheese, mozzarella cheese, eggs, spinach, marinara sauce, salt, pepper');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Spinach Lasagna Roll Ups', 'spinach', 'https://www.budgetbytes.com/spinach-lasagna-roll-ups/', " +
                "'lasagna noodles, ricotta, parmesan cheese, mozzarella cheese, eggs, spinach, marinara sauce, salt, pepper');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Easy Ricotta Gnocchi', 'ricotta cheese', 'https://www.budgetbytes.com/easy-ricotta-gnocchi/', " +
                "'ricotta cheese, egg, olive oil, all-purpose flour, salt, pepper');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Easy Ricotta Gnocchi', 'eggs', 'https://www.budgetbytes.com/easy-ricotta-gnocchi/', " +
                "'ricotta cheese, egg, olive oil, all-purpose flour, salt, pepper');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Beef Kofta Meatballs With Roasted Vegetables', 'zucchini', 'https://www.budgetbytes.com/beef-kofta-meatballs-with-roasted-vegetables/', " +
                "'zucchini, yellow squash, grape tomatoes, red onion, olive oil, garlic powder, dried oregano, salt, pepper, ground beef, garlic, parsley, cumin, cinnamon, cloves, rice');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Beef Kofta Meatballs With Roasted Vegetables', 'yellow squash', 'https://www.budgetbytes.com/beef-kofta-meatballs-with-roasted-vegetables/', " +
                "'zucchini, yellow squash, grape tomatoes, red onion, olive oil, garlic powder, dried oregano, salt, pepper, ground beef, garlic, parsley, cumin, cinnamon, cloves, rice');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Beef Kofta Meatballs With Roasted Vegetables', 'tomatoes', 'https://www.budgetbytes.com/beef-kofta-meatballs-with-roasted-vegetables/', " +
                "'zucchini, yellow squash, grape tomatoes, red onion, olive oil, garlic powder, dried oregano, salt, pepper, ground beef, garlic, parsley, cumin, cinnamon, cloves, rice');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Beef Kofta Meatballs With Roasted Vegetables', 'onion', 'https://www.budgetbytes.com/beef-kofta-meatballs-with-roasted-vegetables/', " +
                "'zucchini, yellow squash, grape tomatoes, red onion, olive oil, garlic powder, dried oregano, salt, pepper, ground beef, garlic, parsley, cumin, cinnamon, cloves, rice');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Beef Kofta Meatballs With Roasted Vegetables', 'ground beef', 'https://www.budgetbytes.com/beef-kofta-meatballs-with-roasted-vegetables/', " +
                "'zucchini, yellow squash, grape tomatoes, red onion, olive oil, garlic powder, dried oregano, salt, pepper, ground beef, garlic, parsley, cumin, cinnamon, cloves, rice');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Greek Chicken Wraps', 'lemon', 'https://www.budgetbytes.com/greek-chicken-wraps/', " +
                "'olive oil, lemon juice, garlic, oregano, salt, pepper, chicken thighs, greek yogurt, dried dill, grape tomatoes, cucumber, red onion, flatbread, hummus');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Greek Chicken Wraps', 'chicken', 'https://www.budgetbytes.com/greek-chicken-wraps/', " +
                "'olive oil, lemon juice, garlic, oregano, salt, pepper, chicken thighs, greek yogurt, dried dill, grape tomatoes, cucumber, red onion, flatbread, hummus');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Greek Chicken Wraps', 'greek yogurt', 'https://www.budgetbytes.com/greek-chicken-wraps/', " +
                "'olive oil, lemon juice, garlic, oregano, salt, pepper, chicken thighs, greek yogurt, dried dill, grape tomatoes, cucumber, red onion, flatbread, hummus');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Greek Chicken Wraps', 'tomatoes', 'https://www.budgetbytes.com/greek-chicken-wraps/', " +
                "'olive oil, lemon juice, garlic, oregano, salt, pepper, chicken thighs, greek yogurt, dried dill, grape tomatoes, cucumber, red onion, flatbread, hummus');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Greek Chicken Wraps', 'cucumber', 'https://www.budgetbytes.com/greek-chicken-wraps/', " +
                "'olive oil, lemon juice, garlic, oregano, salt, pepper, chicken thighs, greek yogurt, dried dill, grape tomatoes, cucumber, red onion, flatbread, hummus');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Greek Chicken Wraps', 'onion', 'https://www.budgetbytes.com/greek-chicken-wraps/', " +
                "'olive oil, lemon juice, garlic, oregano, salt, pepper, chicken thighs, greek yogurt, dried dill, grape tomatoes, cucumber, red onion, flatbread, hummus');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Greek Chicken Wraps', 'hummus', 'https://www.budgetbytes.com/greek-chicken-wraps/', " +
                "'olive oil, lemon juice, garlic, oregano, salt, pepper, chicken thighs, greek yogurt, dried dill, grape tomatoes, cucumber, red onion, flatbread, hummus');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Mediterranean Hummus Bowls', 'ground beef', 'https://www.budgetbytes.com/mediterranean-hummus-bowls/', " +
                "'ground beef, oregano, cumin, garlic powder, salt, rice, hummus, grape tomatoes, cucumber, red onion, kalamata olives, feta cheese, parsley');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Mediterranean Hummus Bowls', 'hummus', 'https://www.budgetbytes.com/mediterranean-hummus-bowls/', " +
                "'ground beef, oregano, cumin, garlic powder, salt, rice, hummus, grape tomatoes, cucumber, red onion, kalamata olives, feta cheese, parsley');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Mediterranean Hummus Bowls', 'tomatoes', 'https://www.budgetbytes.com/mediterranean-hummus-bowls/', " +
                "'ground beef, oregano, cumin, garlic powder, salt, rice, hummus, grape tomatoes, cucumber, red onion, kalamata olives, feta cheese, parsley');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Mediterranean Hummus Bowls', 'cucumber', 'https://www.budgetbytes.com/mediterranean-hummus-bowls/', " +
                "'ground beef, oregano, cumin, garlic powder, salt, rice, hummus, grape tomatoes, cucumber, red onion, kalamata olives, feta cheese, parsley');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Mediterranean Hummus Bowls', 'onion', 'https://www.budgetbytes.com/mediterranean-hummus-bowls/', " +
                "'ground beef, oregano, cumin, garlic powder, salt, rice, hummus, grape tomatoes, cucumber, red onion, kalamata olives, feta cheese, parsley');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Mediterranean Hummus Bowls', 'olives', 'https://www.budgetbytes.com/mediterranean-hummus-bowls/', " +
                "'ground beef, oregano, cumin, garlic powder, salt, rice, hummus, grape tomatoes, cucumber, red onion, kalamata olives, feta cheese, parsley');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Mediterranean Hummus Bowls', 'feta cheese', 'https://www.budgetbytes.com/mediterranean-hummus-bowls/', " +
                "'ground beef, oregano, cumin, garlic powder, salt, rice, hummus, grape tomatoes, cucumber, red onion, kalamata olives, feta cheese, parsley');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Sheet Pan Greek Chicken and Vegetables', 'lemon', 'https://www.budgetbytes.com/sheet-pan-greek-chicken-and-vegetables/', " +
                "'olive oil, lemon, garlic, oregano, salt, pepper, grape tomatoes, bell peppers, red onion, chicken breast, kalamata olives, feta cheese, parsley, rice');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Sheet Pan Greek Chicken and Vegetables', 'tomatoes', 'https://www.budgetbytes.com/sheet-pan-greek-chicken-and-vegetables/', " +
                "'olive oil, lemon, garlic, oregano, salt, pepper, grape tomatoes, bell peppers, red onion, chicken breast, kalamata olives, feta cheese, parsley, rice');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Sheet Pan Greek Chicken and Vegetables', 'bell pepper', 'https://www.budgetbytes.com/sheet-pan-greek-chicken-and-vegetables/', " +
                "'olive oil, lemon, garlic, oregano, salt, pepper, grape tomatoes, bell peppers, red onion, chicken breast, kalamata olives, feta cheese, parsley, rice');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Sheet Pan Greek Chicken and Vegetables', 'onion', 'https://www.budgetbytes.com/sheet-pan-greek-chicken-and-vegetables/', " +
                "'olive oil, lemon, garlic, oregano, salt, pepper, grape tomatoes, bell peppers, red onion, chicken breast, kalamata olives, feta cheese, parsley, rice');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Sheet Pan Greek Chicken and Vegetables', 'chicken', 'https://www.budgetbytes.com/sheet-pan-greek-chicken-and-vegetables/', " +
                "'olive oil, lemon, garlic, oregano, salt, pepper, grape tomatoes, bell peppers, red onion, chicken breast, kalamata olives, feta cheese, parsley, rice');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Sheet Pan Greek Chicken and Vegetables', 'olives', 'https://www.budgetbytes.com/sheet-pan-greek-chicken-and-vegetables/', " +
                "'olive oil, lemon, garlic, oregano, salt, pepper, grape tomatoes, bell peppers, red onion, chicken breast, kalamata olives, feta cheese, parsley, rice');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Sheet Pan Greek Chicken and Vegetables', 'feta cheese', 'https://www.budgetbytes.com/sheet-pan-greek-chicken-and-vegetables/', " +
                "'olive oil, lemon, garlic, oregano, salt, pepper, grape tomatoes, bell peppers, red onion, chicken breast, kalamata olives, feta cheese, parsley, rice');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Italian Orzo Salad', 'lemon', 'https://www.budgetbytes.com/italian-orzo-salad/', " +
                "'olive oil, red wine vinegar, lemon juice, Italian seasoning, garlic powder, salt, dijon mustard, sugar, orzo, chickpeas, spinach, roasted red peppers, kalamata olives, artichoke hearts, grape tomatoes');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Italian Orzo Salad', 'chickpeas', 'https://www.budgetbytes.com/italian-orzo-salad/', " +
                "'olive oil, red wine vinegar, lemon juice, Italian seasoning, garlic powder, salt, dijon mustard, sugar, orzo, chickpeas, spinach, roasted red peppers, kalamata olives, artichoke hearts, grape tomatoes');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Italian Orzo Salad', 'spinach', 'https://www.budgetbytes.com/italian-orzo-salad/', " +
                "'olive oil, red wine vinegar, lemon juice, Italian seasoning, garlic powder, salt, dijon mustard, sugar, orzo, chickpeas, spinach, roasted red peppers, kalamata olives, artichoke hearts, grape tomatoes');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Italian Orzo Salad', 'olives', 'https://www.budgetbytes.com/italian-orzo-salad/', " +
                "'olive oil, red wine vinegar, lemon juice, Italian seasoning, garlic powder, salt, dijon mustard, sugar, orzo, chickpeas, spinach, roasted red peppers, kalamata olives, artichoke hearts, grape tomatoes');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Italian Orzo Salad', 'tomatoes', 'https://www.budgetbytes.com/italian-orzo-salad/', " +
                "'olive oil, red wine vinegar, lemon juice, Italian seasoning, garlic powder, salt, dijon mustard, sugar, orzo, chickpeas, spinach, roasted red peppers, kalamata olives, artichoke hearts, grape tomatoes');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Moroccan Spiced Sweet Potatoes', 'sweet potatoes', 'https://www.budgetbytes.com/moroccan-spiced-sweet-potatoes/', " +
                "'cumin, turmeric, cinnamon, cayenne pepper, paprika, salt, olive oil, sweet potatoes, cilantro');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Greek Turkey Rice Skillet', 'ground turkey', 'https://www.budgetbytes.com/greek-turkey-rice-skillet/', " +
                "'olive oil, garlic, ground turkey, oregano, salt, pepper, white rice, spinach, sun dried tomatoes, kalamata olives, chicken broth, parsley, lemon, feta cheese');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Greek Turkey Rice Skillet', 'spinach', 'https://www.budgetbytes.com/greek-turkey-rice-skillet/', " +
                "'olive oil, garlic, ground turkey, oregano, salt, pepper, white rice, spinach, sun dried tomatoes, kalamata olives, chicken broth, parsley, lemon, feta cheese');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Greek Turkey Rice Skillet', 'tomatoes', 'https://www.budgetbytes.com/greek-turkey-rice-skillet/', " +
                "'olive oil, garlic, ground turkey, oregano, salt, pepper, white rice, spinach, sun dried tomatoes, kalamata olives, chicken broth, parsley, lemon, feta cheese');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Greek Turkey Rice Skillet', 'olives', 'https://www.budgetbytes.com/greek-turkey-rice-skillet/', " +
                "'olive oil, garlic, ground turkey, oregano, salt, pepper, white rice, spinach, sun dried tomatoes, kalamata olives, chicken broth, parsley, lemon, feta cheese');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Greek Turkey Rice Skillet', 'lemon', 'https://www.budgetbytes.com/greek-turkey-rice-skillet/', " +
                "'olive oil, garlic, ground turkey, oregano, salt, pepper, white rice, spinach, sun dried tomatoes, kalamata olives, chicken broth, parsley, lemon, feta cheese');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Greek Turkey Rice Skillet', 'feta cheese', 'https://www.budgetbytes.com/greek-turkey-rice-skillet/', " +
                "'olive oil, garlic, ground turkey, oregano, salt, pepper, white rice, spinach, sun dried tomatoes, kalamata olives, chicken broth, parsley, lemon, feta cheese');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Super Fresh Cucumber Salad', 'lemon', 'https://www.budgetbytes.com/super-fresh-cucmber-salad/', " +
                "'olive oil, red wine vinegar, lemon juice, oregano, salt, pepper, cucumber, roma tomatoes, red onion, green bell pepper, parsley, feta cheese');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Super Fresh Cucumber Salad', 'cucumber', 'https://www.budgetbytes.com/super-fresh-cucmber-salad/', " +
                "'olive oil, red wine vinegar, lemon juice, oregano, salt, pepper, cucumber, roma tomatoes, red onion, green bell pepper, parsley, feta cheese');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Super Fresh Cucumber Salad', 'tomato', 'https://www.budgetbytes.com/super-fresh-cucmber-salad/', " +
                "'olive oil, red wine vinegar, lemon juice, oregano, salt, pepper, cucumber, roma tomatoes, red onion, green bell pepper, parsley, feta cheese');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Super Fresh Cucumber Salad', 'onion', 'https://www.budgetbytes.com/super-fresh-cucmber-salad/', " +
                "'olive oil, red wine vinegar, lemon juice, oregano, salt, pepper, cucumber, roma tomatoes, red onion, green bell pepper, parsley, feta cheese');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Super Fresh Cucumber Salad', 'bell pepper', 'https://www.budgetbytes.com/super-fresh-cucmber-salad/', " +
                "'olive oil, red wine vinegar, lemon juice, oregano, salt, pepper, cucumber, roma tomatoes, red onion, green bell pepper, parsley, feta cheese');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Super Fresh Cucumber Salad', 'feta cheese', 'https://www.budgetbytes.com/super-fresh-cucmber-salad/', " +
                "'olive oil, red wine vinegar, lemon juice, oregano, salt, pepper, cucumber, roma tomatoes, red onion, green bell pepper, parsley, feta cheese');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Creamy Lemon Dill Greek Pasta Salad', 'greek yogurt', 'https://www.budgetbytes.com/creamy-lemon-dill-greek-pasta-salad/', " +
                "'plain Greek yogurt, mayonnaise, garlic, lemon, salt, dried dill, pepper, penne, grape tomatoes, cucumber, artichoke hearts, red onion, feta cheese');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Creamy Lemon Dill Greek Pasta Salad', 'lemon', 'https://www.budgetbytes.com/creamy-lemon-dill-greek-pasta-salad/', " +
                "'plain Greek yogurt, mayonnaise, garlic, lemon, salt, dried dill, pepper, penne, grape tomatoes, cucumber, artichoke hearts, red onion, feta cheese');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Creamy Lemon Dill Greek Pasta Salad', 'tomatoes', 'https://www.budgetbytes.com/creamy-lemon-dill-greek-pasta-salad/', " +
                "'plain Greek yogurt, mayonnaise, garlic, lemon, salt, dried dill, pepper, penne, grape tomatoes, cucumber, artichoke hearts, red onion, feta cheese');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Creamy Lemon Dill Greek Pasta Salad', 'cucumber', 'https://www.budgetbytes.com/creamy-lemon-dill-greek-pasta-salad/', " +
                "'plain Greek yogurt, mayonnaise, garlic, lemon, salt, dried dill, pepper, penne, grape tomatoes, cucumber, artichoke hearts, red onion, feta cheese');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Creamy Lemon Dill Greek Pasta Salad', 'onion', 'https://www.budgetbytes.com/creamy-lemon-dill-greek-pasta-salad/', " +
                "'plain Greek yogurt, mayonnaise, garlic, lemon, salt, dried dill, pepper, penne, grape tomatoes, cucumber, artichoke hearts, red onion, feta cheese');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Creamy Lemon Dill Greek Pasta Salad', 'feta cheese', 'https://www.budgetbytes.com/creamy-lemon-dill-greek-pasta-salad/', " +
                "'plain Greek yogurt, mayonnaise, garlic, lemon, salt, dried dill, pepper, penne, grape tomatoes, cucumber, artichoke hearts, red onion, feta cheese');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Spaghetti Carbonara', 'bacon', 'https://www.budgetbytes.com/spaghetti-carbonara/', " +
                "'bacon, garlic, spaghetti, eggs, Parmesan cheese, pepper, salt, parsley');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Spaghetti Carbonara', 'eggs', 'https://www.budgetbytes.com/spaghetti-carbonara/', " +
                "'bacon, garlic, spaghetti, eggs, Parmesan cheese, pepper, salt, parsley');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Spaghetti Carbonara', 'parmesan cheese', 'https://www.budgetbytes.com/spaghetti-carbonara/', " +
                "'bacon, garlic, spaghetti, eggs, Parmesan cheese, pepper, salt, parsley');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Greek Chicken Pasta Salad', 'lemon', 'https://www.budgetbytes.com/greek-chicken-pasta-salad/', " +
                "'lemon, olive oil, garlic, oregano, salt, pepper, chicken thighs, pasta, bell pepper, grape tomatoes, cucumber, parsley, feta cheese');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Greek Chicken Pasta Salad', 'chicken', 'https://www.budgetbytes.com/greek-chicken-pasta-salad/', " +
                "'lemon, olive oil, garlic, oregano, salt, pepper, chicken thighs, pasta, bell pepper, grape tomatoes, cucumber, parsley, feta cheese');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Greek Chicken Pasta Salad', 'bell pepper', 'https://www.budgetbytes.com/greek-chicken-pasta-salad/', " +
                "'lemon, olive oil, garlic, oregano, salt, pepper, chicken thighs, pasta, bell pepper, grape tomatoes, cucumber, parsley, feta cheese');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Greek Chicken Pasta Salad', 'tomatoes', 'https://www.budgetbytes.com/greek-chicken-pasta-salad/', " +
                "'lemon, olive oil, garlic, oregano, salt, pepper, chicken thighs, pasta, bell pepper, grape tomatoes, cucumber, parsley, feta cheese');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Greek Chicken Pasta Salad', 'cucumber', 'https://www.budgetbytes.com/greek-chicken-pasta-salad/', " +
                "'lemon, olive oil, garlic, oregano, salt, pepper, chicken thighs, pasta, bell pepper, grape tomatoes, cucumber, parsley, feta cheese');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Greek Chicken Pasta Salad', 'feta cheese', 'https://www.budgetbytes.com/greek-chicken-pasta-salad/', " +
                "'lemon, olive oil, garlic, oregano, salt, pepper, chicken thighs, pasta, bell pepper, grape tomatoes, cucumber, parsley, feta cheese');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Smoky White Bean Shakshuka', 'onion', 'https://www.budgetbytes.com/smoky-white-bean-shakshuka/', " +
                "'olive oil, garlic, yellow onion, whole peeled tomatoes, smoked paprika, cumin, oregano, red pepper flakes, pepper, salt, cannellini beans, eggs, parsley, feta cheese');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Smoky White Bean Shakshuka', 'eggs', 'https://www.budgetbytes.com/smoky-white-bean-shakshuka/', " +
                "'olive oil, garlic, yellow onion, whole peeled tomatoes, smoked paprika, cumin, oregano, red pepper flakes, pepper, salt, cannellini beans, eggs, parsley, feta cheese');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Smoky White Bean Shakshuka', 'feta cheese', 'https://www.budgetbytes.com/smoky-white-bean-shakshuka/', " +
                "'olive oil, garlic, yellow onion, whole peeled tomatoes, smoked paprika, cumin, oregano, red pepper flakes, pepper, salt, cannellini beans, eggs, parsley, feta cheese');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Oven Roasted Greek Stuffed Pitas', 'onion', 'https://www.budgetbytes.com/greek-stuffed-pitas/', " +
                "'red onion, eggplant, tomatoes, green bell peppers, chicken breast, garlic, olive oil, lemon juice, oregano, salt, pepper, feta cheese, parsley, whole pita breads');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Oven Roasted Greek Stuffed Pitas', 'eggplant', 'https://www.budgetbytes.com/greek-stuffed-pitas/', " +
                "'red onion, eggplant, tomatoes, green bell peppers, chicken breast, garlic, olive oil, lemon juice, oregano, salt, pepper, feta cheese, parsley, whole pita breads');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Oven Roasted Greek Stuffed Pitas', 'tomatoes', 'https://www.budgetbytes.com/greek-stuffed-pitas/', " +
                "'red onion, eggplant, tomatoes, green bell peppers, chicken breast, garlic, olive oil, lemon juice, oregano, salt, pepper, feta cheese, parsley, whole pita breads');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Oven Roasted Greek Stuffed Pitas', 'bell peppers', 'https://www.budgetbytes.com/greek-stuffed-pitas/', " +
                "'red onion, eggplant, tomatoes, green bell peppers, chicken breast, garlic, olive oil, lemon juice, oregano, salt, pepper, feta cheese, parsley, whole pita breads');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Oven Roasted Greek Stuffed Pitas', 'chicken', 'https://www.budgetbytes.com/greek-stuffed-pitas/', " +
                "'red onion, eggplant, tomatoes, green bell peppers, chicken breast, garlic, olive oil, lemon juice, oregano, salt, pepper, feta cheese, parsley, whole pita breads');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Oven Roasted Greek Stuffed Pitas', 'lemon', 'https://www.budgetbytes.com/greek-stuffed-pitas/', " +
                "'red onion, eggplant, tomatoes, green bell peppers, chicken breast, garlic, olive oil, lemon juice, oregano, salt, pepper, feta cheese, parsley, whole pita breads');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Oven Roasted Greek Stuffed Pitas', 'feta cheese', 'https://www.budgetbytes.com/greek-stuffed-pitas/', " +
                "'red onion, eggplant, tomatoes, green bell peppers, chicken breast, garlic, olive oil, lemon juice, oregano, salt, pepper, feta cheese, parsley, whole pita breads');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Spinach Pie', 'onion', 'https://www.budgetbytes.com/spinach-pie/', " +
                "'olive oil, yellow onion, garlic, cottage cheese, Parmesan cheese, nutmeg, salt, pepper, eggs, spinach, puff pastry, flour');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Spinach Pie', 'cottage cheese', 'https://www.budgetbytes.com/spinach-pie/', " +
                "'olive oil, yellow onion, garlic, cottage cheese, Parmesan cheese, nutmeg, salt, pepper, eggs, spinach, puff pastry, flour');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Spinach Pie', 'parmesan cheese', 'https://www.budgetbytes.com/spinach-pie/', " +
                "'olive oil, yellow onion, garlic, cottage cheese, Parmesan cheese, nutmeg, salt, pepper, eggs, spinach, puff pastry, flour');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Spinach Pie', 'eggs', 'https://www.budgetbytes.com/spinach-pie/', " +
                "'olive oil, yellow onion, garlic, cottage cheese, Parmesan cheese, nutmeg, salt, pepper, eggs, spinach, puff pastry, flour');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Spinach Pie', 'spinach', 'https://www.budgetbytes.com/spinach-pie/', " +
                "'olive oil, yellow onion, garlic, cottage cheese, Parmesan cheese, nutmeg, salt, pepper, eggs, spinach, puff pastry, flour');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Mediterranean Quesadilla', 'spinach', 'https://www.budgetbytes.com/mediterranean-quesadillas/', " +
                "'flour tortillas, spinach, red onion, roasted red peppers, kalamata olives, parsley, feta cheese, mozzarella cheese, oregano');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Mediterranean Quesadilla', 'onion', 'https://www.budgetbytes.com/mediterranean-quesadillas/', " +
                "'flour tortillas, spinach, red onion, roasted red peppers, kalamata olives, parsley, feta cheese, mozzarella cheese, oregano');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Mediterranean Quesadilla', 'bell pepper', 'https://www.budgetbytes.com/mediterranean-quesadillas/', " +
                "'flour tortillas, spinach, red onion, roasted red peppers, kalamata olives, parsley, feta cheese, mozzarella cheese, oregano');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Mediterranean Quesadilla', 'olives', 'https://www.budgetbytes.com/mediterranean-quesadillas/', " +
                "'flour tortillas, spinach, red onion, roasted red peppers, kalamata olives, parsley, feta cheese, mozzarella cheese, oregano');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Mediterranean Quesadilla', 'feta cheese', 'https://www.budgetbytes.com/mediterranean-quesadillas/', " +
                "'flour tortillas, spinach, red onion, roasted red peppers, kalamata olives, parsley, feta cheese, mozzarella cheese, oregano');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Mediterranean Quesadilla', 'mozzarella cheese', 'https://www.budgetbytes.com/mediterranean-quesadillas/', " +
                "'flour tortillas, spinach, red onion, roasted red peppers, kalamata olives, parsley, feta cheese, mozzarella cheese, oregano');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Quinoa Tabbouleh', 'lemon', 'https://www.budgetbytes.com/quinoa-tabbouleh/', " +
                "'quinoa, lemon, garlic, olive oil, salt, tomato, cucumber, parsley');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Quinoa Tabbouleh', 'tomato', 'https://www.budgetbytes.com/quinoa-tabbouleh/', " +
                "'quinoa, lemon, garlic, olive oil, salt, tomato, cucumber, parsley');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Quinoa Tabbouleh', 'cucumber', 'https://www.budgetbytes.com/quinoa-tabbouleh/', " +
                "'quinoa, lemon, garlic, olive oil, salt, tomato, cucumber, parsley');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Easy Homemade Falafel', 'chickpeas', 'https://www.budgetbytes.com/falafel/', " +
                "'chickpeas, red onion, parsley, cilantro, garlic, salt, cayenne, cumin, baking powder, flour, cooking oil');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Easy Homemade Falafel', 'onion', 'https://www.budgetbytes.com/falafel/', " +
                "'chickpeas, red onion, parsley, cilantro, garlic, salt, cayenne, cumin, baking powder, flour, cooking oil');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Pasta Puttanesca', 'anchovies', 'https://www.budgetbytes.com/pasta-puttanesca/', " +
                "'pasta, olive oil, yellow onion, garlic, anchovy filets, red pepper flakes, diced tomatoes, kalamata olives, basil, capers, brown sugar');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Pasta Puttanesca', 'onion', 'https://www.budgetbytes.com/pasta-puttanesca/', " +
                "'pasta, olive oil, yellow onion, garlic, anchovy filets, red pepper flakes, diced tomatoes, kalamata olives, basil, capers, brown sugar');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Pasta Puttanesca', 'olives', 'https://www.budgetbytes.com/pasta-puttanesca/', " +
                "'pasta, olive oil, yellow onion, garlic, anchovy filets, red pepper flakes, diced tomatoes, kalamata olives, basil, capers, brown sugar');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Pasta Puttanesca', 'capers', 'https://www.budgetbytes.com/pasta-puttanesca/', " +
                "'pasta, olive oil, yellow onion, garlic, anchovy filets, red pepper flakes, diced tomatoes, kalamata olives, basil, capers, brown sugar');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Tuscan White Bean Pasta', 'tomatoes', 'https://www.budgetbytes.com/tuscan-white-bean-pasta/', " +
                "'fettuccine or linguine, olive oil, butter, cherry tomatoes, pepper, salt, basil, cannellini beans, spinach, parmesan cheese');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Tuscan White Bean Pasta', 'spinach', 'https://www.budgetbytes.com/tuscan-white-bean-pasta/', " +
                "'fettuccine or linguine, olive oil, butter, cherry tomatoes, pepper, salt, basil, cannellini beans, spinach, parmesan cheese');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Tuscan White Bean Pasta', 'parmesan cheese', 'https://www.budgetbytes.com/tuscan-white-bean-pasta/', " +
                "'fettuccine or linguine, olive oil, butter, cherry tomatoes, pepper, salt, basil, cannellini beans, spinach, parmesan cheese');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Tuscan White Bean Pasta', 'cannellini beans', 'https://www.budgetbytes.com/tuscan-white-bean-pasta/', " +
                "'fettuccine or linguine, olive oil, butter, cherry tomatoes, pepper, salt, basil, cannellini beans, spinach, parmesan cheese');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Mediterranean White Bean Salad', 'white beans', 'https://www.budgetbytes.com/mediterranean-white-bean-salad/', " +
                "'white beans, parsley, grape tomatoes, garlic, olive oil, lemon juice, pepper, salt, feta cheese');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Mediterranean White Bean Salad', 'tomatoes', 'https://www.budgetbytes.com/mediterranean-white-bean-salad/', " +
                "'white beans, parsley, grape tomatoes, garlic, olive oil, lemon juice, pepper, salt, feta cheese');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Mediterranean White Bean Salad', 'lemon', 'https://www.budgetbytes.com/mediterranean-white-bean-salad/', " +
                "'white beans, parsley, grape tomatoes, garlic, olive oil, lemon juice, pepper, salt, feta cheese');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Mediterranean White Bean Salad', 'feta cheese', 'https://www.budgetbytes.com/mediterranean-white-bean-salad/', " +
                "'white beans, parsley, grape tomatoes, garlic, olive oil, lemon juice, pepper, salt, feta cheese');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Easy Cilantro Lime Chicken', 'chicken', 'https://www.budgetbytes.com/easy-cilantro-lime-chicken/', " +
                "'olive oil, garlic, cumin, salt, pepper, lime, cilantro, chicken thighs');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Easy Cilantro Lime Chicken', 'lime', 'https://www.budgetbytes.com/easy-cilantro-lime-chicken/', " +
                "'olive oil, garlic, cumin, salt, pepper, lime, cilantro, chicken thighs');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Tempeh Burrito Bowls', 'sweet potato', 'https://www.budgetbytes.com/tempeh-burrito-bowls/', " +
                "'sweet potatoes, cooking oil, tempeh, tomato paste, chili powder, smoked paprika, cumin, cayenne, oregano, garlic powder, salt, pepper, rice, salsa, corn kernels, jalapeno, avocado, sour cream');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Tempeh Burrito Bowls', 'tempeh', 'https://www.budgetbytes.com/tempeh-burrito-bowls/', " +
                "'sweet potatoes, cooking oil, tempeh, tomato paste, chili powder, smoked paprika, cumin, cayenne, oregano, garlic powder, salt, pepper, rice, salsa, corn kernels, jalapeno, avocado, sour cream');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Tempeh Burrito Bowls', 'avocado', 'https://www.budgetbytes.com/tempeh-burrito-bowls/', " +
                "'sweet potatoes, cooking oil, tempeh, tomato paste, chili powder, smoked paprika, cumin, cayenne, oregano, garlic powder, salt, pepper, rice, salsa, corn kernels, jalapeno, avocado, sour cream');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Tempeh Burrito Bowls', 'sour cream', 'https://www.budgetbytes.com/tempeh-burrito-bowls/', " +
                "'sweet potatoes, cooking oil, tempeh, tomato paste, chili powder, smoked paprika, cumin, cayenne, oregano, garlic powder, salt, pepper, rice, salsa, corn kernels, jalapeno, avocado, sour cream');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Tempeh Burrito Bowls', 'jalapeno', 'https://www.budgetbytes.com/tempeh-burrito-bowls/', " +
                "'sweet potatoes, cooking oil, tempeh, tomato paste, chili powder, smoked paprika, cumin, cayenne, oregano, garlic powder, salt, pepper, rice, salsa, corn kernels, jalapeno, avocado, sour cream');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Hummus Breakfast Tacos', 'hummus', 'https://www.budgetbytes.com/hummus-breakfast-tacos/', " +
                "'tortillas, butter, eggs, salt, pepper, hummus, cilantro, pickled red onions, feta cheese');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Hummus Breakfast Tacos', 'feta cheese', 'https://www.budgetbytes.com/hummus-breakfast-tacos/', " +
                "'tortillas, butter, eggs, salt, pepper, hummus, cilantro, pickled red onions, feta cheese');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Hummus Breakfast Tacos', 'eggs', 'https://www.budgetbytes.com/hummus-breakfast-tacos/', " +
                "'tortillas, butter, eggs, salt, pepper, hummus, cilantro, pickled red onions, feta cheese');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Hummus Breakfast Tacos', 'butter', 'https://www.budgetbytes.com/hummus-breakfast-tacos/', " +
                "'tortillas, butter, eggs, salt, pepper, hummus, cilantro, pickled red onions, feta cheese');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Hummus Breakfast Tacos', 'red onion', 'https://www.budgetbytes.com/hummus-breakfast-tacos/', " +
                "'tortillas, butter, eggs, salt, pepper, hummus, cilantro, pickled red onions, feta cheese');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Salsa Chicken Meal Prep Bowls', 'chicken', 'https://www.budgetbytes.com/salsa-chicken-meal-prep-bowls/', " +
                "'brown rice, salt, chicken breast, salsa, chicken broth, chili powder, bell peppers, oil, salt, green onions, sour cream');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Salsa Chicken Meal Prep Bowls', 'bell peppers', 'https://www.budgetbytes.com/salsa-chicken-meal-prep-bowls/', " +
                "'brown rice, salt, chicken breast, salsa, chicken broth, chili powder, bell peppers, oil, salt, green onions, sour cream');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Salsa Chicken Meal Prep Bowls', 'sour cream', 'https://www.budgetbytes.com/salsa-chicken-meal-prep-bowls/', " +
                "'brown rice, salt, chicken breast, salsa, chicken broth, chili powder, bell peppers, oil, salt, green onions, sour cream');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Creamy White Bean and Spinach Quesadillas', 'cannellini beans', 'https://www.budgetbytes.com/creamy-white-bean-and-spinach-quesadillas/', " +
                "'cannellini beans, chili powder, cumin, garlic powder, salt, spinach, pepper jack cheese, sour cream, flour tortillas');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Creamy White Bean and Spinach Quesadillas', 'spinach', 'https://www.budgetbytes.com/creamy-white-bean-and-spinach-quesadillas/', " +
                "'cannellini beans, chili powder, cumin, garlic powder, salt, spinach, pepper jack cheese, sour cream, flour tortillas');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Creamy White Bean and Spinach Quesadillas', 'pepper jack cheese', 'https://www.budgetbytes.com/creamy-white-bean-and-spinach-quesadillas/', " +
                "'cannellini beans, chili powder, cumin, garlic powder, salt, spinach, pepper jack cheese, sour cream, flour tortillas');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Creamy White Bean and Spinach Quesadillas', 'sour cream', 'https://www.budgetbytes.com/creamy-white-bean-and-spinach-quesadillas/', " +
                "'cannellini beans, chili powder, cumin, garlic powder, salt, spinach, pepper jack cheese, sour cream, flour tortillas');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Creamy Chicken and Spinach Quesadillas', 'chicken', 'https://www.budgetbytes.com/creamy-chicken-and-spinach-quesadillas/', " +
                "'chicken breast, chili powder, cumin, garlic powder, salt, spinach, pepper jack cheese, sour cream, flour tortillas');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Creamy Chicken and Spinach Quesadillas', 'spinach', 'https://www.budgetbytes.com/creamy-chicken-and-spinach-quesadillas/', " +
                "'chicken breast, chili powder, cumin, garlic powder, salt, spinach, pepper jack cheese, sour cream, flour tortillas');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Creamy Chicken and Spinach Quesadillas', 'pepper jack cheese', 'https://www.budgetbytes.com/creamy-chicken-and-spinach-quesadillas/', " +
                "'chicken breast, chili powder, cumin, garlic powder, salt, spinach, pepper jack cheese, sour cream, flour tortillas');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Creamy Chicken and Spinach Quesadillas', 'sour cream', 'https://www.budgetbytes.com/creamy-chicken-and-spinach-quesadillas/', " +
                "'chicken breast, chili powder, cumin, garlic powder, salt, spinach, pepper jack cheese, sour cream, flour tortillas');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Basic Chili', 'black beans', 'https://www.budgetbytes.com/basic-chili/', " +
                "'olive oil, yellow onion, garlic, ground beef, kidney beans, black beans, diced tomatoes, tomato paste, chili powder, cumin, cayenne powder, garlic powder, onion powder, brown sugar, salt, pepper');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Basic Chili', 'kidney beans', 'https://www.budgetbytes.com/basic-chili/', " +
                "'olive oil, yellow onion, garlic, ground beef, kidney beans, black beans, diced tomatoes, tomato paste, chili powder, cumin, cayenne powder, garlic powder, onion powder, brown sugar, salt, pepper');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Basic Chili', 'jalapeno', 'https://www.budgetbytes.com/basic-chili/', " +
                "'olive oil, yellow onion, garlic, ground beef, kidney beans, black beans, diced tomatoes, tomato paste, chili powder, cumin, cayenne powder, garlic powder, onion powder, brown sugar, salt, pepper');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Basic Chili', 'onion', 'https://www.budgetbytes.com/basic-chili/', " +
                "'olive oil, yellow onion, garlic, ground beef, kidney beans, black beans, diced tomatoes, tomato paste, chili powder, cumin, cayenne powder, garlic powder, onion powder, brown sugar, salt, pepper');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Basic Chili', 'ground beef', 'https://www.budgetbytes.com/basic-chili/', " +
                "'olive oil, yellow onion, garlic, ground beef, kidney beans, black beans, diced tomatoes, tomato paste, chili powder, cumin, cayenne powder, garlic powder, onion powder, brown sugar, salt, pepper');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Basic Chili', 'ground chicken', 'https://www.budgetbytes.com/basic-chili/', " +
                "'olive oil, yellow onion, garlic, ground chicken, kidney beans, black beans, diced tomatoes, tomato paste, chili powder, cumin, cayenne powder, garlic powder, onion powder, brown sugar, salt, pepper');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Basic Chili', 'ground pork', 'https://www.budgetbytes.com/basic-chili/', " +
                "'olive oil, yellow onion, garlic, ground pork, kidney beans, black beans, diced tomatoes, tomato paste, chili powder, cumin, cayenne powder, garlic powder, onion powder, brown sugar, salt, pepper');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Spinach White Bean Enchiladas With Pepper Jack Sauce', 'cream cheese', 'https://www.budgetbytes.com/spinach-white-bean-enchiladas-pepper-jack-sauce/', " +
                "'corn tortillas, pepper jack cheese, spinach, white beans, garlic powder, cumin, salt, pepper, cilantro, butter, garlic, cream cheese, whole milk');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Spinach White Bean Enchiladas With Pepper Jack Sauce', 'pepper jack cheese', 'https://www.budgetbytes.com/spinach-white-bean-enchiladas-pepper-jack-sauce/', " +
                "'corn tortillas, pepper jack cheese, spinach, white beans, garlic powder, cumin, salt, pepper, cilantro, butter, garlic, cream cheese, whole milk');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Spinach White Bean Enchiladas With Pepper Jack Sauce', 'spinach', 'https://www.budgetbytes.com/spinach-white-bean-enchiladas-pepper-jack-sauce/', " +
                "'corn tortillas, pepper jack cheese, spinach, white beans, garlic powder, cumin, salt, pepper, cilantro, butter, garlic, cream cheese, whole milk');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Spinach White Bean Enchiladas With Pepper Jack Sauce', 'white beans', 'https://www.budgetbytes.com/spinach-white-bean-enchiladas-pepper-jack-sauce/', " +
                "'corn tortillas, pepper jack cheese, spinach, white beans, garlic powder, cumin, salt, pepper, cilantro, butter, garlic, cream cheese, whole milk');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Spinach White Bean Enchiladas With Pepper Jack Sauce', 'butter', 'https://www.budgetbytes.com/spinach-white-bean-enchiladas-pepper-jack-sauce/', " +
                "'corn tortillas, pepper jack cheese, spinach, white beans, garlic powder, cumin, salt, pepper, cilantro, butter, garlic, cream cheese, whole milk');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Spinach White Bean Enchiladas With Pepper Jack Sauce', 'milk', 'https://www.budgetbytes.com/spinach-white-bean-enchiladas-pepper-jack-sauce/', " +
                "'corn tortillas, pepper jack cheese, spinach, white beans, garlic powder, cumin, salt, pepper, cilantro, butter, garlic, cream cheese, whole milk');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Chorizo and Sweet Potato Enchiladas', 'sweet potato', 'https://www.budgetbytes.com/chorizo-sweet-potato-enchiladas/', " +
                "'sweet potatoes, poblano pepper, garlic, vegetable oil, chorizo, tortillas, enchilada sauce, shredded cheese, green onion');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Chorizo and Sweet Potato Enchiladas', 'poblano pepper', 'https://www.budgetbytes.com/chorizo-sweet-potato-enchiladas/', " +
                "'sweet potatoes, poblano pepper, garlic, vegetable oil, chorizo, tortillas, enchilada sauce, shredded cheese, green onion');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Chorizo and Sweet Potato Enchiladas', 'chorizo', 'https://www.budgetbytes.com/chorizo-sweet-potato-enchiladas/', " +
                "'sweet potatoes, poblano pepper, garlic, vegetable oil, chorizo, tortillas, enchilada sauce, shredded cheese, green onion');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Chorizo and Sweet Potato Enchiladas', 'cheddar cheese', 'https://www.budgetbytes.com/chorizo-sweet-potato-enchiladas/', " +
                "'sweet potatoes, poblano pepper, garlic, vegetable oil, chorizo, tortillas, enchilada sauce, shredded cheese, green onion');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Slow Cooker Salsa Chicken Verde Bowls', 'onion', 'https://www.budgetbytes.com/slow-cooker-salsa-verde-chicken/', " +
                "'yellow onion, bell pepper, chicken pieces, cumin, salt, garlic powder, pepper, salsa verde, rice, black beans, jalapeno, queso fresco, lime, cilantro');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Slow Cooker Salsa Chicken Verde Bowls', 'bell pepper', 'https://www.budgetbytes.com/slow-cooker-salsa-verde-chicken/', " +
                "'yellow onion, bell pepper, chicken pieces, cumin, salt, garlic powder, pepper, salsa verde, rice, black beans, jalapeno, queso fresco, lime, cilantro');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Slow Cooker Salsa Chicken Verde Bowls', 'chicken', 'https://www.budgetbytes.com/slow-cooker-salsa-verde-chicken/', " +
                "'yellow onion, bell pepper, chicken pieces, cumin, salt, garlic powder, pepper, salsa verde, rice, black beans, jalapeno, queso fresco, lime, cilantro');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Slow Cooker Salsa Chicken Verde Bowls', 'black beans', 'https://www.budgetbytes.com/slow-cooker-salsa-verde-chicken/', " +
                "'yellow onion, bell pepper, chicken pieces, cumin, salt, garlic powder, pepper, salsa verde, rice, black beans, jalapeno, queso fresco, lime, cilantro');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Slow Cooker Salsa Chicken Verde Bowls', 'jalapeno', 'https://www.budgetbytes.com/slow-cooker-salsa-verde-chicken/', " +
                "'yellow onion, bell pepper, chicken pieces, cumin, salt, garlic powder, pepper, salsa verde, rice, black beans, jalapeno, queso fresco, lime, cilantro');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Slow Cooker Salsa Chicken Verde Bowls', 'queso fresco', 'https://www.budgetbytes.com/slow-cooker-salsa-verde-chicken/', " +
                "'yellow onion, bell pepper, chicken pieces, cumin, salt, garlic powder, pepper, salsa verde, rice, black beans, jalapeno, queso fresco, lime, cilantro');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Slow Cooker Salsa Chicken Verde Bowls', 'lime', 'https://www.budgetbytes.com/slow-cooker-salsa-verde-chicken/', " +
                "'yellow onion, bell pepper, chicken pieces, cumin, salt, garlic powder, pepper, salsa verde, rice, black beans, jalapeno, queso fresco, lime, cilantro');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Tomato and Corn Relish', 'roma tomato', 'https://www.budgetbytes.com/tomato-and-corn-relish/', " +
                "'corn kernals, roma tomatoes, yellow onion, cilantro, lime, cumin, salt');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Tomato and Corn Relish', 'onion', 'https://www.budgetbytes.com/tomato-and-corn-relish/', " +
                "'corn kernals, roma tomatoes, yellow onion, cilantro, lime, cumin, salt');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Tomato and Corn Relish', 'lime', 'https://www.budgetbytes.com/tomato-and-corn-relish/', " +
                "'corn kernals, roma tomatoes, yellow onion, cilantro, lime, cumin, salt');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Tomato and Corn Relish', 'corn', 'https://www.budgetbytes.com/tomato-and-corn-relish/', " +
                "'corn kernals, roma tomatoes, yellow onion, cilantro, lime, cumin, salt');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Fish Tacos with Cumin Lime Slaw', 'corn', 'https://www.budgetbytes.com/fish-tacos-with-cumin-lime-slaw/', " +
                "'corn tortillas, white fish filets, mild chili powder blend, salt, vegetable oil, corn kernels, Cumin Lime Slaw, cilantro');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Fish Tacos with Cumin Lime Slaw', 'fish fillet', 'https://www.budgetbytes.com/fish-tacos-with-cumin-lime-slaw/', " +
                "'corn tortillas, white fish filets, mild chili powder blend, salt, vegetable oil, corn kernels, Cumin Lime Slaw, cilantro');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Cumin Lime Slaw', 'mayonnaise', 'https://www.budgetbytes.com/cumin-lime-coleslaw/', " +
                "'mayonnaise, lime juice, cumin, salt, cayenne pepper, sugar, green cabbage, carrots, green onions');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Cumin Lime Slaw', 'lime', 'https://www.budgetbytes.com/cumin-lime-coleslaw/', " +
                "'mayonnaise, lime juice, cumin, salt, cayenne pepper, sugar, green cabbage, carrots, green onions');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Cumin Lime Slaw', 'green cabbage', 'https://www.budgetbytes.com/cumin-lime-coleslaw/', " +
                "'mayonnaise, lime juice, cumin, salt, cayenne pepper, sugar, green cabbage, carrots, green onions');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Cumin Lime Slaw', 'carrots', 'https://www.budgetbytes.com/cumin-lime-coleslaw/', " +
                "'mayonnaise, lime juice, cumin, salt, cayenne pepper, sugar, green cabbage, carrots, green onions');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Green Chile Breakfast Quesadilla', 'monterrey jack cheese', 'https://www.budgetbytes.com/green-chile-breakfast-quesadillas/', " +
                "'Monterrey or Pepper Jack cheese, diced mild green chiles, cilantro, eggs, tortillas');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Green Chile Breakfast Quesadilla', 'pepper jack cheese', 'https://www.budgetbytes.com/green-chile-breakfast-quesadillas/', " +
                "'Monterrey or Pepper Jack cheese, diced mild green chiles, cilantro, eggs, tortillas');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Green Chile Breakfast Quesadilla', 'eggs', 'https://www.budgetbytes.com/green-chile-breakfast-quesadillas/', " +
                "'Monterrey or Pepper Jack cheese, diced mild green chiles, cilantro, eggs, tortillas');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Saucy Southwest Shredded Beef', 'beef', 'https://www.budgetbytes.com/saucy-southwest-shredded-beef/', " +
                "'tomato paste, garlic, chili powder, cumin, oregano, cayenne pepper, brown sugar, cornstarch, beef broth, boneless beef rump, salt');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Chili Roasted Potatoes', 'broccoli', 'https://www.budgetbytes.com/chili-roasted-potatoes/', " +
                "'potatoes, olive oil, chili powder, cumin, cayenne pepper, garlic powder, onion powder, salt, pepper');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Roasted Corn and Zucchini Tacos', 'zucchini', 'https://www.budgetbytes.com/roasted-corn-zucchini-tacos/', " +
                "'zucchini, frozen corn, taco seasoning, garlic powder, olive oil, black beans, salt, corn tortillas, avocado, monterrey jack cheese, cilantro');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Roasted Corn and Zucchini Tacos', 'black beans', 'https://www.budgetbytes.com/roasted-corn-zucchini-tacos/', " +
                "'zucchini, frozen corn, taco seasoning, garlic powder, olive oil, black beans, salt, corn tortillas, avocado, monterrey jack cheese, cilantro');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Roasted Corn and Zucchini Tacos', 'avocado', 'https://www.budgetbytes.com/roasted-corn-zucchini-tacos/', " +
                "'zucchini, frozen corn, taco seasoning, garlic powder, olive oil, black beans, salt, corn tortillas, avocado, monterrey jack cheese, cilantro');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Roasted Corn and Zucchini Tacos', 'monterrey jack cheese', 'https://www.budgetbytes.com/roasted-corn-zucchini-tacos/', " +
                "'zucchini, frozen corn, taco seasoning, garlic powder, olive oil, black beans, salt, corn tortillas, avocado, monterrey jack cheese, cilantro');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Roasted Corn and Zucchini Tacos', 'corn', 'https://www.budgetbytes.com/roasted-corn-zucchini-tacos/', " +
                "'zucchini, frozen corn, taco seasoning, garlic powder, olive oil, black beans, salt, corn tortillas, avocado, monterrey jack cheese, cilantro');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Chicken and Pineapple Quesadilla', 'pepper jack cheese', 'https://www.budgetbytes.com/chicken-pineapple-quesadillas/', " +
                "'chicken breast, pineapple chunks, black beans, green onion, cilantro, cumin, red pepper flakes, salt, mozzarella or monterrey jack cheese, soft taco sized flour tortillas');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Chicken and Pineapple Quesadilla', 'mozzarella cheese', 'https://www.budgetbytes.com/chicken-pineapple-quesadillas/', " +
                "'chicken breast, pineapple chunks, black beans, green onion, cilantro, cumin, red pepper flakes, salt, mozzarella or monterrey jack cheese, soft taco sized flour tortillas');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Chicken and Pineapple Quesadilla', 'black beans', 'https://www.budgetbytes.com/chicken-pineapple-quesadillas/', " +
                "'chicken breast, pineapple chunks, black beans, green onion, cilantro, cumin, red pepper flakes, salt, mozzarella or monterrey jack cheese, soft taco sized flour tortillas');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Chicken and Pineapple Quesadilla', 'pineapple', 'https://www.budgetbytes.com/chicken-pineapple-quesadillas/', " +
                "'chicken breast, pineapple chunks, black beans, green onion, cilantro, cumin, red pepper flakes, salt, mozzarella or monterrey jack cheese, soft taco sized flour tortillas');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Chicken and Pineapple Quesadilla', 'chicken', 'https://www.budgetbytes.com/chicken-pineapple-quesadillas/', " +
                "'chicken breast, pineapple chunks, black beans, green onion, cilantro, cumin, red pepper flakes, salt, mozzarella or monterrey jack cheese, soft taco sized flour tortillas');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Chicken and Pumpkin Soup', 'yellow onion', 'https://www.budgetbytes.com/chicken-pumpkin-soup/', " +
                "'olive oil, yellow onion, garlic, chicken broth, chicken breast, pumpkin puree, black beans, corn kernels, chipotle peppers in adobo sauce, cumin, salt, cilantro');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Chicken and Pumpkin Soup', 'chicken', 'https://www.budgetbytes.com/chicken-pumpkin-soup/', " +
                "'olive oil, yellow onion, garlic, chicken broth, chicken breast, pumpkin puree, black beans, corn kernels, chipotle peppers in adobo sauce, cumin, salt, cilantro');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Chicken and Pumpkin Soup', 'black beans', 'https://www.budgetbytes.com/chicken-pumpkin-soup/', " +
                "'olive oil, yellow onion, garlic, chicken broth, chicken breast, pumpkin puree, black beans, corn kernels, chipotle peppers in adobo sauce, cumin, salt, cilantro');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Chicken and Pumpkin Soup', 'corn', 'https://www.budgetbytes.com/chicken-pumpkin-soup/', " +
                "'olive oil, yellow onion, garlic, chicken broth, chicken breast, pumpkin puree, black beans, corn kernels, chipotle peppers in adobo sauce, cumin, salt, cilantro');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Roasted Poblano Grits and Chorizo', 'poblano peppers', 'https://www.budgetbytes.com/roasted-poblano-grits-with-chorizo/', " +
                "'poblano peppers, olive oil, milk, water, garlic, salt, quick cooking grits, butter, Mexican chorizo');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Roasted Poblano Grits and Chorizo', 'milk', 'https://www.budgetbytes.com/roasted-poblano-grits-with-chorizo/', " +
                "'poblano peppers, olive oil, milk, water, garlic, salt, quick cooking grits, butter, Mexican chorizo');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Roasted Poblano Grits and Chorizo', 'butter', 'https://www.budgetbytes.com/roasted-poblano-grits-with-chorizo/', " +
                "'poblano peppers, olive oil, milk, water, garlic, salt, quick cooking grits, butter, Mexican chorizo');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Roasted Poblano Grits and Chorizo', 'chorizo', 'https://www.budgetbytes.com/roasted-poblano-grits-with-chorizo/', " +
                "'poblano peppers, olive oil, milk, water, garlic, salt, quick cooking grits, butter, Mexican chorizo');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Quinoa Black Bean Tacos', 'pico de gallo', 'https://www.budgetbytes.com/quinoa-black-bean-tacos/', " +
                "'quinoa, garlic, salt, chili powder, cumin, oregano, smoked paprika, black beans, pico de gallo, avocado, corn tortillas, cilantro');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Quinoa Black Bean Tacos', 'black beans', 'https://www.budgetbytes.com/quinoa-black-bean-tacos/', " +
                "'quinoa, garlic, salt, chili powder, cumin, oregano, smoked paprika, black beans, pico de gallo, avocado, corn tortillas, cilantro');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Quinoa Black Bean Tacos', 'avocado', 'https://www.budgetbytes.com/quinoa-black-bean-tacos/', " +
                "'quinoa, garlic, salt, chili powder, cumin, oregano, smoked paprika, black beans, pico de gallo, avocado, corn tortillas, cilantro');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Chicken Enchilada Pasta', 'yellow onion', 'https://www.budgetbytes.com/chicken-enchilada-pasta/', " +
                "'vegetable oil, yellow onion, garlic, chicken breast, enchilada sauce, sour cream, pasta, monterrey jack cheese, green onion');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Chicken Enchilada Pasta', 'chicken', 'https://www.budgetbytes.com/chicken-enchilada-pasta/', " +
                "'vegetable oil, yellow onion, garlic, chicken breast, enchilada sauce, sour cream, pasta, monterrey jack cheese, green onion');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Chicken Enchilada Pasta', 'sour cream', 'https://www.budgetbytes.com/chicken-enchilada-pasta/', " +
                "'vegetable oil, yellow onion, garlic, chicken breast, enchilada sauce, sour cream, pasta, monterrey jack cheese, green onion');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Chicken Enchilada Pasta', 'monterrey jack cheese', 'https://www.budgetbytes.com/chicken-enchilada-pasta/', " +
                "'vegetable oil, yellow onion, garlic, chicken breast, enchilada sauce, sour cream, pasta, monterrey jack cheese, green onion');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Chicken and Lime Soup', 'yellow onion', 'https://www.budgetbytes.com/chicken-lime-soup/', " +
                "'olive oil, medium yellow onion, celery, jalapeno, garlic, chicken breast, chicken broth, diced tomatoes with chiles, oregano, cumin, lime, cilantro, avocado');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Chicken and Lime Soup', 'celery', 'https://www.budgetbytes.com/chicken-lime-soup/', " +
                "'olive oil, medium yellow onion, celery, jalapeno, garlic, chicken breast, chicken broth, diced tomatoes with chiles, oregano, cumin, lime, cilantro, avocado');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Chicken and Lime Soup', 'jalapeno', 'https://www.budgetbytes.com/chicken-lime-soup/', " +
                "'olive oil, medium yellow onion, celery, jalapeno, garlic, chicken breast, chicken broth, diced tomatoes with chiles, oregano, cumin, lime, cilantro, avocado');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Chicken and Lime Soup', 'chicken', 'https://www.budgetbytes.com/chicken-lime-soup/', " +
                "'olive oil, medium yellow onion, celery, jalapeno, garlic, chicken breast, chicken broth, diced tomatoes with chiles, oregano, cumin, lime, cilantro, avocado');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Chicken and Lime Soup', 'lime', 'https://www.budgetbytes.com/chicken-lime-soup/', " +
                "'olive oil, medium yellow onion, celery, jalapeno, garlic, chicken breast, chicken broth, diced tomatoes with chiles, oregano, cumin, lime, cilantro, avocado');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Chicken and Lime Soup', 'avocado', 'https://www.budgetbytes.com/chicken-lime-soup/', " +
                "'olive oil, medium yellow onion, celery, jalapeno, garlic, chicken breast, chicken broth, diced tomatoes with chiles, oregano, cumin, lime, cilantro, avocado');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Roasted Vegetable Burrito', 'zucchini', 'https://www.budgetbytes.com/roasted-vegetable-burritos/', " +
                "'white rice, salt, chili powder, zucchini, red onion, red bell pepper, button mushrooms, salt, cumin, oregano, olive oil, flour tortillas, black beans, shredded cheese, cilantro');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Roasted Vegetable Burrito', 'red onion', 'https://www.budgetbytes.com/roasted-vegetable-burritos/', " +
                "'white rice, salt, chili powder, zucchini, red onion, red bell pepper, button mushrooms, salt, cumin, oregano, olive oil, flour tortillas, black beans, shredded cheese, cilantro');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Roasted Vegetable Burrito', 'bell pepper', 'https://www.budgetbytes.com/roasted-vegetable-burritos/', " +
                "'white rice, salt, chili powder, zucchini, red onion, red bell pepper, button mushrooms, salt, cumin, oregano, olive oil, flour tortillas, black beans, shredded cheese, cilantro');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Roasted Vegetable Burrito', 'mushrooms', 'https://www.budgetbytes.com/roasted-vegetable-burritos/', " +
                "'white rice, salt, chili powder, zucchini, red onion, red bell pepper, button mushrooms, salt, cumin, oregano, olive oil, flour tortillas, black beans, shredded cheese, cilantro');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Roasted Vegetable Burrito', 'black beans', 'https://www.budgetbytes.com/roasted-vegetable-burritos/', " +
                "'white rice, salt, chili powder, zucchini, red onion, red bell pepper, button mushrooms, salt, cumin, oregano, olive oil, flour tortillas, black beans, shredded cheese, cilantro');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Roasted Vegetable Burrito', 'cheddar cheese', 'https://www.budgetbytes.com/roasted-vegetable-burritos/', " +
                "'white rice, salt, chili powder, zucchini, red onion, red bell pepper, button mushrooms, salt, cumin, oregano, olive oil, flour tortillas, black beans, shredded cheese, cilantro');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Vegetable Tamale Pie', 'onion', 'https://www.budgetbytes.com/vegetable-tamale-pie/', " +
                "'olive oil, onion, garlic, sweet potatoes, black beans, green onion, poblano pepper, jalapeno pepper, red enchilada sauce, chili powder, cumin, salt, yellow cornmeal, all purpose flour, sugar, baking powder, milk, egg, vegetable oil, shredded cheddar cheese, cilantro');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Vegetable Tamale Pie', 'sweet potato', 'https://www.budgetbytes.com/vegetable-tamale-pie/', " +
                "'olive oil, onion, garlic, sweet potatoes, black beans, green onion, poblano pepper, jalapeno pepper, red enchilada sauce, chili powder, cumin, salt, yellow cornmeal, all purpose flour, sugar, baking powder, milk, egg, vegetable oil, shredded cheddar cheese, cilantro');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Vegetable Tamale Pie', 'black beans', 'https://www.budgetbytes.com/vegetable-tamale-pie/', " +
                "'olive oil, onion, garlic, sweet potatoes, black beans, green onion, poblano pepper, jalapeno pepper, red enchilada sauce, chili powder, cumin, salt, yellow cornmeal, all purpose flour, sugar, baking powder, milk, egg, vegetable oil, shredded cheddar cheese, cilantro');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Vegetable Tamale Pie', 'poblano pepper', 'https://www.budgetbytes.com/vegetable-tamale-pie/', " +
                "'olive oil, onion, garlic, sweet potatoes, black beans, green onion, poblano pepper, jalapeno pepper, red enchilada sauce, chili powder, cumin, salt, yellow cornmeal, all purpose flour, sugar, baking powder, milk, egg, vegetable oil, shredded cheddar cheese, cilantro');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Vegetable Tamale Pie', 'jalapeno', 'https://www.budgetbytes.com/vegetable-tamale-pie/', " +
                "'olive oil, onion, garlic, sweet potatoes, black beans, green onion, poblano pepper, jalapeno pepper, red enchilada sauce, chili powder, cumin, salt, yellow cornmeal, all purpose flour, sugar, baking powder, milk, egg, vegetable oil, shredded cheddar cheese, cilantro');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Vegetable Tamale Pie', 'milk', 'https://www.budgetbytes.com/vegetable-tamale-pie/', " +
                "'olive oil, onion, garlic, sweet potatoes, black beans, green onion, poblano pepper, jalapeno pepper, red enchilada sauce, chili powder, cumin, salt, yellow cornmeal, all purpose flour, sugar, baking powder, milk, egg, vegetable oil, shredded cheddar cheese, cilantro');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Vegetable Tamale Pie', 'eggs', 'https://www.budgetbytes.com/vegetable-tamale-pie/', " +
                "'olive oil, onion, garlic, sweet potatoes, black beans, green onion, poblano pepper, jalapeno pepper, red enchilada sauce, chili powder, cumin, salt, yellow cornmeal, all purpose flour, sugar, baking powder, milk, egg, vegetable oil, shredded cheddar cheese, cilantro');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Vegetable Tamale Pie', 'cheddar cheese', 'https://www.budgetbytes.com/vegetable-tamale-pie/', " +
                "'olive oil, onion, garlic, sweet potatoes, black beans, green onion, poblano pepper, jalapeno pepper, red enchilada sauce, chili powder, cumin, salt, yellow cornmeal, all purpose flour, sugar, baking powder, milk, egg, vegetable oil, shredded cheddar cheese, cilantro');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Southwest Salad with Taco Ranch Dressing', 'cheddar cheese', 'https://www.budgetbytes.com/southwest-salad-with-taco-ranch-dressing/', " +
                "'plain yogurt, buttermilk, mayonnaise, salt, garlic powder, chili powder, cumin, cayenne pepper, corn tortillas, non-stick spray, iceberg lettuce, black beans, frozen corn kernels, tomato, green onion, shredded cheese');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Southwest Salad with Taco Ranch Dressing', 'tomato', 'https://www.budgetbytes.com/southwest-salad-with-taco-ranch-dressing/', " +
                "'plain yogurt, buttermilk, mayonnaise, salt, garlic powder, chili powder, cumin, cayenne pepper, corn tortillas, non-stick spray, iceberg lettuce, black beans, frozen corn kernels, tomato, green onion, shredded cheese');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Southwest Salad with Taco Ranch Dressing', 'corn', 'https://www.budgetbytes.com/southwest-salad-with-taco-ranch-dressing/', " +
                "'plain yogurt, buttermilk, mayonnaise, salt, garlic powder, chili powder, cumin, cayenne pepper, corn tortillas, non-stick spray, iceberg lettuce, black beans, frozen corn kernels, tomato, green onion, shredded cheese');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Southwest Salad with Taco Ranch Dressing', 'black beans', 'https://www.budgetbytes.com/southwest-salad-with-taco-ranch-dressing/', " +
                "'plain yogurt, buttermilk, mayonnaise, salt, garlic powder, chili powder, cumin, cayenne pepper, corn tortillas, non-stick spray, iceberg lettuce, black beans, frozen corn kernels, tomato, green onion, shredded cheese');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Southwest Salad with Taco Ranch Dressing', 'iceberg lettuce', 'https://www.budgetbytes.com/southwest-salad-with-taco-ranch-dressing/', " +
                "'plain yogurt, buttermilk, mayonnaise, salt, garlic powder, chili powder, cumin, cayenne pepper, corn tortillas, non-stick spray, iceberg lettuce, black beans, frozen corn kernels, tomato, green onion, shredded cheese');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Southwest Salad with Taco Ranch Dressing', 'mayonnaise', 'https://www.budgetbytes.com/southwest-salad-with-taco-ranch-dressing/', " +
                "'plain yogurt, buttermilk, mayonnaise, salt, garlic powder, chili powder, cumin, cayenne pepper, corn tortillas, non-stick spray, iceberg lettuce, black beans, frozen corn kernels, tomato, green onion, shredded cheese');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Southwest Salad with Taco Ranch Dressing', 'buttermilk', 'https://www.budgetbytes.com/southwest-salad-with-taco-ranch-dressing/', " +
                "'plain yogurt, buttermilk, mayonnaise, salt, garlic powder, chili powder, cumin, cayenne pepper, corn tortillas, non-stick spray, iceberg lettuce, black beans, frozen corn kernels, tomato, green onion, shredded cheese');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Southwest Salad with Taco Ranch Dressing', 'plain yogurt', 'https://www.budgetbytes.com/southwest-salad-with-taco-ranch-dressing/', " +
                "'plain yogurt, buttermilk, mayonnaise, salt, garlic powder, chili powder, cumin, cayenne pepper, corn tortillas, non-stick spray, iceberg lettuce, black beans, frozen corn kernels, tomato, green onion, shredded cheese');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Lentil Tacos', 'yellow onion', 'https://www.budgetbytes.com/lentil-tacos/', " +
                "'brown lentils, yellow onion, garlic, olive oil, taco seasoning, salt, corn tortillas, pico de gallo, sour cream, avocados');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Lentil Tacos', 'pico de gallo', 'https://www.budgetbytes.com/lentil-tacos/', " +
                "'brown lentils, yellow onion, garlic, olive oil, taco seasoning, salt, corn tortillas, pico de gallo, sour cream, avocados');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Lentil Tacos', 'sour cream', 'https://www.budgetbytes.com/lentil-tacos/', " +
                "'brown lentils, yellow onion, garlic, olive oil, taco seasoning, salt, corn tortillas, pico de gallo, sour cream, avocados');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Lentil Tacos', 'avocado', 'https://www.budgetbytes.com/lentil-tacos/', " +
                "'brown lentils, yellow onion, garlic, olive oil, taco seasoning, salt, corn tortillas, pico de gallo, sour cream, avocados');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Freezer Breakfast Burritos', 'eggs', 'https://www.budgetbytes.com/freezer-breakfast-burritos/', " +
                "'large tortillas, eggs, butter, red pepper flakes, salt, pepper, black beans, salsa, cheddar cheese');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Freezer Breakfast Burritos', 'butter', 'https://www.budgetbytes.com/freezer-breakfast-burritos/', " +
                "'large tortillas, eggs, butter, red pepper flakes, salt, pepper, black beans, salsa, cheddar cheese');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Freezer Breakfast Burritos', 'black beans', 'https://www.budgetbytes.com/freezer-breakfast-burritos/', " +
                "'large tortillas, eggs, butter, red pepper flakes, salt, pepper, black beans, salsa, cheddar cheese');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Freezer Breakfast Burritos', 'cheddar cheese', 'https://www.budgetbytes.com/freezer-breakfast-burritos/', " +
                "'large tortillas, eggs, butter, red pepper flakes, salt, pepper, black beans, salsa, cheddar cheese');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Caldo Xochitl', 'yellow onion', 'https://www.budgetbytes.com/caldo-xochitl-mexican-hot-flower-soup/', " +
                "'olive oil, garlic, yellow onion, celery, bone-in chicken breast, diced tomatoes with chiles, lime, cilantro, avocado, chicken bullion');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Caldo Xochitl', 'celery', 'https://www.budgetbytes.com/caldo-xochitl-mexican-hot-flower-soup/', " +
                "'olive oil, garlic, yellow onion, celery, bone-in chicken breast, diced tomatoes with chiles, lime, cilantro, avocado, chicken bullion');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Caldo Xochitl', 'chicken', 'https://www.budgetbytes.com/caldo-xochitl-mexican-hot-flower-soup/', " +
                "'olive oil, garlic, yellow onion, celery, bone-in chicken breast, diced tomatoes with chiles, lime, cilantro, avocado, chicken bullion');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Caldo Xochitl', 'lime', 'https://www.budgetbytes.com/caldo-xochitl-mexican-hot-flower-soup/', " +
                "'olive oil, garlic, yellow onion, celery, bone-in chicken breast, diced tomatoes with chiles, lime, cilantro, avocado, chicken bullion');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Caldo Xochitl', 'avocado', 'https://www.budgetbytes.com/caldo-xochitl-mexican-hot-flower-soup/', " +
                "'olive oil, garlic, yellow onion, celery, bone-in chicken breast, diced tomatoes with chiles, lime, cilantro, avocado, chicken bullion');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Chicken Enchiladas', 'tomato', 'https://www.budgetbytes.com/chicken-enchiladas/', " +
                "'chicken breast, garlic powder, cayenne pepper, cumin, salt, pepper, sour cream, shredded mozzarella cheese, green chiles, tortillas, green enchilada sauce, tomato, green onion');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Chicken Enchiladas', 'mozzarella cheese', 'https://www.budgetbytes.com/chicken-enchiladas/', " +
                "'chicken breast, garlic powder, cayenne pepper, cumin, salt, pepper, sour cream, shredded mozzarella cheese, green chiles, tortillas, green enchilada sauce, tomato, green onion');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Chicken Enchiladas', 'sour cream', 'https://www.budgetbytes.com/chicken-enchiladas/', " +
                "'chicken breast, garlic powder, cayenne pepper, cumin, salt, pepper, sour cream, shredded mozzarella cheese, green chiles, tortillas, green enchilada sauce, tomato, green onion');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Chicken Enchiladas', 'chicken', 'https://www.budgetbytes.com/chicken-enchiladas/', " +
                "'chicken breast, garlic powder, cayenne pepper, cumin, salt, pepper, sour cream, shredded mozzarella cheese, green chiles, tortillas, green enchilada sauce, tomato, green onion');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Chili Cheese Cornbread', 'eggs', 'https://www.budgetbytes.com/chili-cheese-cornbread/', " +
                "'cornbread mix, eggs, milk, chili powder, frozen corn kernels, shredded cheddar cheese');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Chili Cheese Cornbread', 'milk', 'https://www.budgetbytes.com/chili-cheese-cornbread/', " +
                "'cornbread mix, eggs, milk, chili powder, frozen corn kernels, shredded cheddar cheese');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Chili Cheese Cornbread', 'corn', 'https://www.budgetbytes.com/chili-cheese-cornbread/', " +
                "'cornbread mix, eggs, milk, chili powder, frozen corn kernels, shredded cheddar cheese');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Chili Cheese Cornbread', 'cheddar cheese', 'https://www.budgetbytes.com/chili-cheese-cornbread/', " +
                "'cornbread mix, eggs, milk, chili powder, frozen corn kernels, shredded cheddar cheese');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Stuffed Poblano Peppers', 'pablano pepper', 'https://www.budgetbytes.com/stuffed-poblano-peppers/', " +
                "'long grain rice, vegetable or chicken bullion, chili powder, cilantro, poblano peppers, cheese, salsa, black beans, non-stick spray');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Stuffed Poblano Peppers', 'cheddar cheese', 'https://www.budgetbytes.com/stuffed-poblano-peppers/', " +
                "'long grain rice, vegetable or chicken bullion, chili powder, cilantro, poblano peppers, cheese, salsa, black beans, non-stick spray');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Stuffed Poblano Peppers', 'black beans', 'https://www.budgetbytes.com/stuffed-poblano-peppers/', " +
                "'long grain rice, vegetable or chicken bullion, chili powder, cilantro, poblano peppers, cheese, salsa, black beans, non-stick spray');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Sweet Potato Enchiladas', 'cheddar cheese', 'https://www.budgetbytes.com/sweet-potato-enchiladas/', " +
                "'Roasted Poblano and Sweet Potato salad, flour tortillas, red enchilada sauce, shredded cheddar cheese');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Sweet Potato Enchiladas', 'sweet potatoes', 'https://www.budgetbytes.com/sweet-potato-enchiladas/', " +
                "'Roasted Poblano and Sweet Potato salad, flour tortillas, red enchilada sauce, shredded cheddar cheese');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Sweet Potato Enchiladas', 'pablano peppers', 'https://www.budgetbytes.com/sweet-potato-enchiladas/', " +
                "'Roasted Poblano and Sweet Potato salad, flour tortillas, red enchilada sauce, shredded cheddar cheese');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Roasted Poblano and Sweet Potato Salad', 'sweet potatoes', 'https://www.budgetbytes.com/roasted-poblano-and-sweet-potato-salad/', " +
                "'large sweet potatoes, poblano peppers, black beans, corn kernels, red onion, lime, olive oil, cilantro, cumin, salt');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Roasted Poblano and Sweet Potato Salad', 'pablano peppers', 'https://www.budgetbytes.com/roasted-poblano-and-sweet-potato-salad/', " +
                "'large sweet potatoes, poblano peppers, black beans, corn kernels, red onion, lime, olive oil, cilantro, cumin, salt');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Roasted Poblano and Sweet Potato Salad', 'black beans', 'https://www.budgetbytes.com/roasted-poblano-and-sweet-potato-salad/', " +
                "'large sweet potatoes, poblano peppers, black beans, corn kernels, red onion, lime, olive oil, cilantro, cumin, salt');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Roasted Poblano and Sweet Potato Salad', 'corn', 'https://www.budgetbytes.com/roasted-poblano-and-sweet-potato-salad/', " +
                "'large sweet potatoes, poblano peppers, black beans, corn kernels, red onion, lime, olive oil, cilantro, cumin, salt');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Roasted Poblano and Sweet Potato Salad', 'red onion', 'https://www.budgetbytes.com/roasted-poblano-and-sweet-potato-salad/', " +
                "'large sweet potatoes, poblano peppers, black beans, corn kernels, red onion, lime, olive oil, cilantro, cumin, salt');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Roasted Poblano and Sweet Potato Salad', 'lime', 'https://www.budgetbytes.com/roasted-poblano-and-sweet-potato-salad/', " +
                "'large sweet potatoes, poblano peppers, black beans, corn kernels, red onion, lime, olive oil, cilantro, cumin, salt');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Spicy Orecchiette with Chicken Sausage and Kale', 'chicken sausage', 'https://www.budgetbytes.com/spicy-orecchiette-with-chicken-sausage-and-kale/', " +
                "'orecchiette, olive oil, chicken sausage, butter, garlic, kale, grated Parmesan, crushed red pepper, salt, pepper');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Spicy Orecchiette with Chicken Sausage and Kale', 'butter', 'https://www.budgetbytes.com/spicy-orecchiette-with-chicken-sausage-and-kale/', " +
                "'orecchiette, olive oil, chicken sausage, butter, garlic, kale, grated Parmesan, crushed red pepper, salt, pepper');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Spicy Orecchiette with Chicken Sausage and Kale', 'paremsan cheese', 'https://www.budgetbytes.com/spicy-orecchiette-with-chicken-sausage-and-kale/', " +
                "'orecchiette, olive oil, chicken sausage, butter, garlic, kale, grated Parmesan, crushed red pepper, salt, pepper');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Spicy Orecchiette with Chicken Sausage and Kale', 'kale', 'https://www.budgetbytes.com/spicy-orecchiette-with-chicken-sausage-and-kale/', " +
                "'orecchiette, olive oil, chicken sausage, butter, garlic, kale, grated Parmesan, crushed red pepper, salt, pepper');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('One Pot Teriyaki Chicken and Rice', 'chicken', 'https://www.budgetbytes.com/one-pot-teriyaki-chicken-and-rice/', " +
                "'chicken breast, cooking oil, garlic, ginger, jasmine rice, frozen stir fry vegetables, soy sauce, brown sugar, toasted sesame oil, green onions');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Creamy Spinach Artichoke Chicken', 'parmesan cheese', 'https://www.budgetbytes.com/creamy-spinach-artichoke-chicken/', " +
                "'chicken breast, salt, pepper, cooking oil, spinach, quartered artichoke hearts in water, garlic, butter, chicken broth, sour cream, cream cheese, milk, grated Parmesan, crushed red pepper');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Creamy Spinach Artichoke Chicken', 'cream cheese', 'https://www.budgetbytes.com/creamy-spinach-artichoke-chicken/', " +
                "'chicken breast, salt, pepper, cooking oil, spinach, quartered artichoke hearts in water, garlic, butter, chicken broth, sour cream, cream cheese, milk, grated Parmesan, crushed red pepper');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Creamy Spinach Artichoke Chicken', 'sour cream', 'https://www.budgetbytes.com/creamy-spinach-artichoke-chicken/', " +
                "'chicken breast, salt, pepper, cooking oil, spinach, quartered artichoke hearts in water, garlic, butter, chicken broth, sour cream, cream cheese, milk, grated Parmesan, crushed red pepper');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Creamy Spinach Artichoke Chicken', 'butter', 'https://www.budgetbytes.com/creamy-spinach-artichoke-chicken/', " +
                "'chicken breast, salt, pepper, cooking oil, spinach, quartered artichoke hearts in water, garlic, butter, chicken broth, sour cream, cream cheese, milk, grated Parmesan, crushed red pepper');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Creamy Spinach Artichoke Chicken', 'spinach', 'https://www.budgetbytes.com/creamy-spinach-artichoke-chicken/', " +
                "'chicken breast, salt, pepper, cooking oil, spinach, quartered artichoke hearts in water, garlic, butter, chicken broth, sour cream, cream cheese, milk, grated Parmesan, crushed red pepper');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Creamy Spinach Artichoke Chicken', 'milk', 'https://www.budgetbytes.com/creamy-spinach-artichoke-chicken/', " +
                "'chicken breast, salt, pepper, cooking oil, spinach, quartered artichoke hearts in water, garlic, butter, chicken broth, sour cream, cream cheese, milk, grated Parmesan, crushed red pepper');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Creamy Spinach Artichoke Chicken', 'chicken', 'https://www.budgetbytes.com/creamy-spinach-artichoke-chicken/', " +
                "'chicken breast, salt, pepper, cooking oil, spinach, quartered artichoke hearts in water, garlic, butter, chicken broth, sour cream, cream cheese, milk, grated Parmesan, crushed red pepper');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Coconut Turmeric Chicken Soup', 'lime', 'https://www.budgetbytes.com/coconut-turmeric-chicken-soup/', " +
                "'yellow onion, garlic, ginger, olive oil, turmeric, cumin, crushed red pepper, carrots, celery, chicken breast, chicken broth, coconut milk, salt, jasmine rice, cilantro, lime');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Coconut Turmeric Chicken Soup', 'coconut milk', 'https://www.budgetbytes.com/coconut-turmeric-chicken-soup/', " +
                "'yellow onion, garlic, ginger, olive oil, turmeric, cumin, crushed red pepper, carrots, celery, chicken breast, chicken broth, coconut milk, salt, jasmine rice, cilantro, lime');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Coconut Turmeric Chicken Soup', 'chicken', 'https://www.budgetbytes.com/coconut-turmeric-chicken-soup/', " +
                "'yellow onion, garlic, ginger, olive oil, turmeric, cumin, crushed red pepper, carrots, celery, chicken breast, chicken broth, coconut milk, salt, jasmine rice, cilantro, lime');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Coconut Turmeric Chicken Soup', 'celery', 'https://www.budgetbytes.com/coconut-turmeric-chicken-soup/', " +
                "'yellow onion, garlic, ginger, olive oil, turmeric, cumin, crushed red pepper, carrots, celery, chicken breast, chicken broth, coconut milk, salt, jasmine rice, cilantro, lime');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Coconut Turmeric Chicken Soup', 'carrots', 'https://www.budgetbytes.com/coconut-turmeric-chicken-soup/', " +
                "'yellow onion, garlic, ginger, olive oil, turmeric, cumin, crushed red pepper, carrots, celery, chicken breast, chicken broth, coconut milk, salt, jasmine rice, cilantro, lime');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Coconut Turmeric Chicken Soup', 'yellow onion', 'https://www.budgetbytes.com/coconut-turmeric-chicken-soup/', " +
                "'yellow onion, garlic, ginger, olive oil, turmeric, cumin, crushed red pepper, carrots, celery, chicken breast, chicken broth, coconut milk, salt, jasmine rice, cilantro, lime');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('One Pot Creamy Cajun Chicken Pasta', 'cream cheese', 'https://www.budgetbytes.com/one-pot-creamy-cajun-chicken-pasta/', " +
                "'smoked paprika, oregano, thyme, garlic powder, onion powder, cayenne pepper, black pepper, salt, olive oil, butter, chicken breast, yellow onion, penne pasta, fire roasted diced tomatoes, chicken broth, cream cheese, green onion');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('One Pot Creamy Cajun Chicken Pasta', 'yellow onion', 'https://www.budgetbytes.com/one-pot-creamy-cajun-chicken-pasta/', " +
                "'smoked paprika, oregano, thyme, garlic powder, onion powder, cayenne pepper, black pepper, salt, olive oil, butter, chicken breast, yellow onion, penne pasta, fire roasted diced tomatoes, chicken broth, cream cheese, green onion');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('One Pot Creamy Cajun Chicken Pasta', 'chicken', 'https://www.budgetbytes.com/one-pot-creamy-cajun-chicken-pasta/', " +
                "'smoked paprika, oregano, thyme, garlic powder, onion powder, cayenne pepper, black pepper, salt, olive oil, butter, chicken breast, yellow onion, penne pasta, fire roasted diced tomatoes, chicken broth, cream cheese, green onion');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('One Pot Creamy Cajun Chicken Pasta', 'butter', 'https://www.budgetbytes.com/one-pot-creamy-cajun-chicken-pasta/', " +
                "'smoked paprika, oregano, thyme, garlic powder, onion powder, cayenne pepper, black pepper, salt, olive oil, butter, chicken breast, yellow onion, penne pasta, fire roasted diced tomatoes, chicken broth, cream cheese, green onion');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Garlic Herb Baked Chicken Breast', 'chicken', 'https://www.budgetbytes.com/garlic-herb-baked-chicken-breast/', " +
                "'butter, parsley, oregano, basil, garlic powder, onion powder, salt, pepper, chicken breast');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Garlic Herb Baked Chicken Breast', 'butter', 'https://www.budgetbytes.com/garlic-herb-baked-chicken-breast/', " +
                "'butter, parsley, oregano, basil, garlic powder, onion powder, salt, pepper, chicken breast');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Sheet Pan Pesto Chicken Dinner', 'potatoes', 'https://www.budgetbytes.com/sheet-pan-pesto-chicken-dinner/', " +
                "'baby potatoes, olive oil, oregano, salt, pepper, grape tomatoes, chicken breast, pesto, mozzarella cheese, spring salad mix, Caesar dressing');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Sheet Pan Pesto Chicken Dinner', 'grape tomatoes', 'https://www.budgetbytes.com/sheet-pan-pesto-chicken-dinner/', " +
                "'baby potatoes, olive oil, oregano, salt, pepper, grape tomatoes, chicken breast, pesto, mozzarella cheese, spring salad mix, Caesar dressing');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Sheet Pan Pesto Chicken Dinner', 'chicken', 'https://www.budgetbytes.com/sheet-pan-pesto-chicken-dinner/', " +
                "'baby potatoes, olive oil, oregano, salt, pepper, grape tomatoes, chicken breast, pesto, mozzarella cheese, spring salad mix, Caesar dressing');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Sheet Pan Pesto Chicken Dinner', 'mozzarella cheese', 'https://www.budgetbytes.com/sheet-pan-pesto-chicken-dinner/', " +
                "'baby potatoes, olive oil, oregano, salt, pepper, grape tomatoes, chicken breast, pesto, mozzarella cheese, spring salad mix, Caesar dressing');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Sheet Pan Pesto Chicken Dinner', 'salad greens', 'https://www.budgetbytes.com/sheet-pan-pesto-chicken-dinner/', " +
                "'baby potatoes, olive oil, oregano, salt, pepper, grape tomatoes, chicken breast, pesto, mozzarella cheese, spring salad mix, Caesar dressing');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Sweet and Spicy Glazed Chicken Thighs', 'chicken', 'https://www.budgetbytes.com/sweet-and-spicy-glazed-chicken-thighs/', " +
                "'brown sugar, cayenne pepper, garlic powder, paprika, salt, pepper, olive oil, boneless skinless chicken thighs');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('20 Minute Honey Mustard Chicken', 'chicken', 'https://www.budgetbytes.com/20-minute-honey-mustard-chicken/', " +
                "'boneless skinless chicken thighs, smoked paprika, garlic powder, salt, cooking oil, chicken broth, ground mustard, honey, butter');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('20 Minute Honey Mustard Chicken', 'butter', 'https://www.budgetbytes.com/20-minute-honey-mustard-chicken/', " +
                "'boneless skinless chicken thighs, smoked paprika, garlic powder, salt, cooking oil, chicken broth, ground mustard, honey, butter');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('20 Minute Honey Mustard Chicken', 'honey', 'https://www.budgetbytes.com/20-minute-honey-mustard-chicken/', " +
                "'boneless skinless chicken thighs, smoked paprika, garlic powder, salt, cooking oil, chicken broth, ground mustard, honey, butter');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('White Cheddar Mac and Cheese with Chicken and Broccoli', 'broccoli', 'https://www.budgetbytes.com/white-cheddar-mac-and-cheese/', " +
                "'pasta, frozen broccoli florets, chicken breast, salt, pepper, cooking oil, butter, evaporated milk, garlic powder, smoked paprika, extra sharp white cheddar cheese');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('White Cheddar Mac and Cheese with Chicken and Broccoli', 'chicken', 'https://www.budgetbytes.com/white-cheddar-mac-and-cheese/', " +
                "'pasta, frozen broccoli florets, chicken breast, salt, pepper, cooking oil, butter, evaporated milk, garlic powder, smoked paprika, extra sharp white cheddar cheese');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('White Cheddar Mac and Cheese with Chicken and Broccoli', 'cheddar cheese', 'https://www.budgetbytes.com/white-cheddar-mac-and-cheese/', " +
                "'pasta, frozen broccoli florets, chicken breast, salt, pepper, cooking oil, butter, evaporated milk, garlic powder, smoked paprika, extra sharp white cheddar cheese');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Monterey Chicken Skillet', 'monterrey jack cheese', 'https://www.budgetbytes.com/monterey-chicken-skillet/', " +
                "'chicken, rotel, fusili pasta, chicken broth, barbeque sauce, Monterey jack cheese, bacon, green onions');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Monterey Chicken Skillet', 'chicken', 'https://www.budgetbytes.com/monterey-chicken-skillet/', " +
                "'chicken, rotel, fusili pasta, chicken broth, barbeque sauce, Monterey jack cheese, bacon, green onions');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Monterey Chicken Skillet', 'bacon', 'https://www.budgetbytes.com/monterey-chicken-skillet/', " +
                "'chicken, rotel, fusili pasta, chicken broth, barbeque sauce, Monterey jack cheese, bacon, green onions');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Chicken and Dumplings With Vegetables', 'yellow onion', 'https://www.budgetbytes.com/chicken-dumplings-vegetables/', " +
                "'yellow onion, carrots, celery, olive oil, chicken breast, chicken broth, salt, pepper, whole milk, all-purpose flower, frozen peas, parsley, baking powder, butter');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Chicken and Dumplings With Vegetables', 'carrots', 'https://www.budgetbytes.com/chicken-dumplings-vegetables/', " +
                "'yellow onion, carrots, celery, olive oil, chicken breast, chicken broth, salt, pepper, whole milk, all-purpose flower, frozen peas, parsley, baking powder, butter');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Chicken and Dumplings With Vegetables', 'celery', 'https://www.budgetbytes.com/chicken-dumplings-vegetables/', " +
                "'yellow onion, carrots, celery, olive oil, chicken breast, chicken broth, salt, pepper, whole milk, all-purpose flower, frozen peas, parsley, baking powder, butter');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Chicken and Dumplings With Vegetables', 'chicken', 'https://www.budgetbytes.com/chicken-dumplings-vegetables/', " +
                "'yellow onion, carrots, celery, olive oil, chicken breast, chicken broth, salt, pepper, whole milk, all-purpose flower, frozen peas, parsley, baking powder, butter');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Chicken and Dumplings With Vegetables', 'milk', 'https://www.budgetbytes.com/chicken-dumplings-vegetables/', " +
                "'yellow onion, carrots, celery, olive oil, chicken breast, chicken broth, salt, pepper, whole milk, all-purpose flower, frozen peas, parsley, baking powder, butter');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Chicken and Dumplings With Vegetables', 'peas', 'https://www.budgetbytes.com/chicken-dumplings-vegetables/', " +
                "'yellow onion, carrots, celery, olive oil, chicken breast, chicken broth, salt, pepper, whole milk, all-purpose flower, frozen peas, parsley, baking powder, butter');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Chicken and Dumplings With Vegetables', 'butter', 'https://www.budgetbytes.com/chicken-dumplings-vegetables/', " +
                "'yellow onion, carrots, celery, olive oil, chicken breast, chicken broth, salt, pepper, whole milk, all-purpose flower, frozen peas, parsley, baking powder, butter');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Herb Butter Chicken Thighs', 'chicken', 'https://www.budgetbytes.com/herb-butter-chicken-thighs/', " +
                "'chicken thighs, salt, pepper, olive oil, garlic, Italian seasoning herb blend, chicken broth, butter, parsley');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Herb Butter Chicken Thighs', 'butter', 'https://www.budgetbytes.com/herb-butter-chicken-thighs/', " +
                "'chicken thighs, salt, pepper, olive oil, garlic, Italian seasoning herb blend, chicken broth, butter, parsley');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Spinach Artichoke Quesadilla', 'chicken', 'https://www.budgetbytes.com/spinach-artichoke-quesadillas/', " +
                "'double spinach artichoke dip, flour tortillas, shredded rotisserie chicken, hot sauce');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Double Spinach Artichoke Dip', 'mayonnaise', 'https://www.budgetbytes.com/double-spinach-artichoke-dip/', " +
                "'spinach, artichoke hearts, mozzarella cheese, Parmesan cheese, red pepper flakes, butter, garlic, cream cheese, sour cream, mayonnaise, hot sauce');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Double Spinach Artichoke Dip', 'sour cream', 'https://www.budgetbytes.com/double-spinach-artichoke-dip/', " +
                "'spinach, artichoke hearts, mozzarella cheese, Parmesan cheese, red pepper flakes, butter, garlic, cream cheese, sour cream, mayonnaise, hot sauce');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Double Spinach Artichoke Dip', 'cream cheese', 'https://www.budgetbytes.com/double-spinach-artichoke-dip/', " +
                "'spinach, artichoke hearts, mozzarella cheese, Parmesan cheese, red pepper flakes, butter, garlic, cream cheese, sour cream, mayonnaise, hot sauce');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Double Spinach Artichoke Dip', 'butter', 'https://www.budgetbytes.com/double-spinach-artichoke-dip/', " +
                "'spinach, artichoke hearts, mozzarella cheese, Parmesan cheese, red pepper flakes, butter, garlic, cream cheese, sour cream, mayonnaise, hot sauce');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Double Spinach Artichoke Dip', 'parmesan cheese', 'https://www.budgetbytes.com/double-spinach-artichoke-dip/', " +
                "'spinach, artichoke hearts, mozzarella cheese, Parmesan cheese, red pepper flakes, butter, garlic, cream cheese, sour cream, mayonnaise, hot sauce');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Double Spinach Artichoke Dip', 'mozzarella cheese', 'https://www.budgetbytes.com/double-spinach-artichoke-dip/', " +
                "'spinach, artichoke hearts, mozzarella cheese, Parmesan cheese, red pepper flakes, butter, garlic, cream cheese, sour cream, mayonnaise, hot sauce');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Double Spinach Artichoke Dip', 'spinach', 'https://www.budgetbytes.com/double-spinach-artichoke-dip/', " +
                "'spinach, artichoke hearts, mozzarella cheese, Parmesan cheese, red pepper flakes, butter, garlic, cream cheese, sour cream, mayonnaise, hot sauce');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Garlic Parmesan Kale Pasta', 'kale', 'https://www.budgetbytes.com/garlic-parmesan-kale-pasta/', " +
                "'kale, angel hair pasta, olive oil, butter, garlic, grated parmesan cheese, salt, pepper, red pepper flakes');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Garlic Parmesan Kale Pasta', 'butter', 'https://www.budgetbytes.com/garlic-parmesan-kale-pasta/', " +
                "'kale, angel hair pasta, olive oil, butter, garlic, grated parmesan cheese, salt, pepper, red pepper flakes');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Garlic Parmesan Kale Pasta', 'parmesan cheese', 'https://www.budgetbytes.com/garlic-parmesan-kale-pasta/', " +
                "'kale, angel hair pasta, olive oil, butter, garlic, grated parmesan cheese, salt, pepper, red pepper flakes');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Garlic Marinated Chicken', 'chicken', 'https://www.budgetbytes.com/garlic-parmesan-kale-pasta-meal-prep/', " +
                "'olive oil, lemon juice, garlic, oregano, salt, pepper, chicken breast');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Garlic Marinated Chicken', 'lemon', 'https://www.budgetbytes.com/garlic-parmesan-kale-pasta-meal-prep/', " +
                "'olive oil, lemon juice, garlic, oregano, salt, pepper, chicken breast');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Skillet Pineapple BBQ Chicken', 'chicken', 'https://www.budgetbytes.com/skillet-pineapple-bbq-chicken/', " +
                "'cooking oil, chicken breast, salt, pepper, pineapple slices in juice, barbeque sauce, jalapeno, green onion');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Skillet Pineapple BBQ Chicken', 'pineapple', 'https://www.budgetbytes.com/skillet-pineapple-bbq-chicken/', " +
                "'cooking oil, chicken breast, salt, pepper, pineapple slices in juice, barbeque sauce, jalapeno, green onion');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Skillet Pineapple BBQ Chicken', 'jalapeno', 'https://www.budgetbytes.com/skillet-pineapple-bbq-chicken/', " +
                "'cooking oil, chicken breast, salt, pepper, pineapple slices in juice, barbeque sauce, jalapeno, green onion');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Chimichurri Chicken and Rice', 'chicken', 'https://www.budgetbytes.com/chimichurri-chicken-rice/', " +
                "'olive oil, red wine vinegar, oregano, cumin, red pepper flakes, salt, garlic, parsley, cilantro, chicken breast, pepper, yellow onion, roma tomatoes, frozen peas, white rice');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Chimichurri Chicken and Rice', 'yellow onion', 'https://www.budgetbytes.com/chimichurri-chicken-rice/', " +
                "'olive oil, red wine vinegar, oregano, cumin, red pepper flakes, salt, garlic, parsley, cilantro, chicken breast, pepper, yellow onion, roma tomatoes, frozen peas, white rice');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Chimichurri Chicken and Rice', 'roma tomatoes', 'https://www.budgetbytes.com/chimichurri-chicken-rice/', " +
                "'olive oil, red wine vinegar, oregano, cumin, red pepper flakes, salt, garlic, parsley, cilantro, chicken breast, pepper, yellow onion, roma tomatoes, frozen peas, white rice');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Chimichurri Chicken and Rice', 'peas', 'https://www.budgetbytes.com/chimichurri-chicken-rice/', " +
                "'olive oil, red wine vinegar, oregano, cumin, red pepper flakes, salt, garlic, parsley, cilantro, chicken breast, pepper, yellow onion, roma tomatoes, frozen peas, white rice');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Ultimate BBQ Chicken Quesadilla', 'chicken', 'https://www.budgetbytes.com/ultimate-bbq-chicken-quesadillas/', " +
                "'chopped cooked chicken, black beans, red onion, jalapeno, cilantro, cheddar cheese, chili powder, smoked paprika, salt, barbeque sauce, flour tortillas');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Ultimate BBQ Chicken Quesadilla', 'black beans', 'https://www.budgetbytes.com/ultimate-bbq-chicken-quesadillas/', " +
                "'chopped cooked chicken, black beans, red onion, jalapeno, cilantro, cheddar cheese, chili powder, smoked paprika, salt, barbeque sauce, flour tortillas');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Ultimate BBQ Chicken Quesadilla', 'red onion', 'https://www.budgetbytes.com/ultimate-bbq-chicken-quesadillas/', " +
                "'chopped cooked chicken, black beans, red onion, jalapeno, cilantro, cheddar cheese, chili powder, smoked paprika, salt, barbeque sauce, flour tortillas');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Ultimate BBQ Chicken Quesadilla', 'jalapeno', 'https://www.budgetbytes.com/ultimate-bbq-chicken-quesadillas/', " +
                "'chopped cooked chicken, black beans, red onion, jalapeno, cilantro, cheddar cheese, chili powder, smoked paprika, salt, barbeque sauce, flour tortillas');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Ultimate BBQ Chicken Quesadilla', 'cheddar cheese', 'https://www.budgetbytes.com/ultimate-bbq-chicken-quesadillas/', " +
                "'chopped cooked chicken, black beans, red onion, jalapeno, cilantro, cheddar cheese, chili powder, smoked paprika, salt, barbeque sauce, flour tortillas');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Lemon Garlic Roasted Chicken', 'chicken', 'https://www.budgetbytes.com/lemon-garlic-roasted-chicken/', " +
                "'large lemon, olive oil, garlic cloves, salt, pepper, split chicken breasts');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Lemon Garlic Roasted Chicken', 'lemon', 'https://www.budgetbytes.com/lemon-garlic-roasted-chicken/', " +
                "'large lemon, olive oil, garlic cloves, salt, pepper, split chicken breasts');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Homemade Chicken Noodle Soup', 'yellow onion', 'https://www.budgetbytes.com/chicken-noodle-soup/', " +
                "'olive oil, yellow onion, garlic, carrots, celery, chicken breast (bone-in), basil, parsley, thyme, bay leaf, pepper, salt, egg noodles');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Homemade Chicken Noodle Soup', 'carrots', 'https://www.budgetbytes.com/chicken-noodle-soup/', " +
                "'olive oil, yellow onion, garlic, carrots, celery, chicken breast (bone-in), basil, parsley, thyme, bay leaf, pepper, salt, egg noodles');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Homemade Chicken Noodle Soup', 'celery', 'https://www.budgetbytes.com/chicken-noodle-soup/', " +
                "'olive oil, yellow onion, garlic, carrots, celery, chicken breast (bone-in), basil, parsley, thyme, bay leaf, pepper, salt, egg noodles');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Homemade Chicken Noodle Soup', 'chicken', 'https://www.budgetbytes.com/chicken-noodle-soup/', " +
                "'olive oil, yellow onion, garlic, carrots, celery, chicken breast (bone-in), basil, parsley, thyme, bay leaf, pepper, salt, egg noodles');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Herb Roasted Chicken Breast', 'butter', 'https://www.budgetbytes.com/herb-roasted-chicken-breasts/', " +
                "'butter, garlic, basil, thyme, rosemary, salt, pepper, chicken breast');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Herb Roasted Chicken Breast', 'chicken', 'https://www.budgetbytes.com/herb-roasted-chicken-breasts/', " +
                "'butter, garlic, basil, thyme, rosemary, salt, pepper, chicken breast');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Baked Chicken with Artichokes and Tomatoes', 'tomatoes', 'https://www.budgetbytes.com/baked-chicken-artichokes-tomatoes/', " +
                "'diced tomatoes, olive oil, garlic, oregano, salt, pepper, lemon juice, chicken breast halves, artichoke hearts, mozzarella cheese');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Baked Chicken with Artichokes and Tomatoes', 'lemon', 'https://www.budgetbytes.com/baked-chicken-artichokes-tomatoes/', " +
                "'diced tomatoes, olive oil, garlic, oregano, salt, pepper, lemon juice, chicken breast halves, artichoke hearts, mozzarella cheese');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Baked Chicken with Artichokes and Tomatoes', 'chicken', 'https://www.budgetbytes.com/baked-chicken-artichokes-tomatoes/', " +
                "'diced tomatoes, olive oil, garlic, oregano, salt, pepper, lemon juice, chicken breast halves, artichoke hearts, mozzarella cheese');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Baked Chicken with Artichokes and Tomatoes', 'mozzarella cheese', 'https://www.budgetbytes.com/baked-chicken-artichokes-tomatoes/', " +
                "'diced tomatoes, olive oil, garlic, oregano, salt, pepper, lemon juice, chicken breast halves, artichoke hearts, mozzarella cheese');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Balsamic Chicken Skillet', 'chicken', 'https://www.budgetbytes.com/balsamic-chicken-skillet/', " +
                "'olive oil, brown sugar, balsamic vinegar, soy sauce, garlic, pepper, chicken thighs, mozzarella cheese, roma tomatoes, parsley');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Balsamic Chicken Skillet', 'mozzarella cheese', 'https://www.budgetbytes.com/balsamic-chicken-skillet/', " +
                "'olive oil, brown sugar, balsamic vinegar, soy sauce, garlic, pepper, chicken thighs, mozzarella cheese, roma tomatoes, parsley');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Balsamic Chicken Skillet', 'roma tomatoes', 'https://www.budgetbytes.com/balsamic-chicken-skillet/', " +
                "'olive oil, brown sugar, balsamic vinegar, soy sauce, garlic, pepper, chicken thighs, mozzarella cheese, roma tomatoes, parsley');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Pressure Cooker Chicken and Rice', 'chicken', 'https://www.budgetbytes.com/pressure-cooker-chicken-rice/', " +
                "'whole split chicken, seasoning blend, white rice, parsley');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Quickie Faux Pho', 'chicken', 'https://www.budgetbytes.com/quickie-faux-pho/', " +
                "'chicken stock, five spice blend, chicken, wide rice noodles, jalapeno, lime, green onion, cilantro, sriracha sauce, hoisin sauce');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Quickie Faux Pho', 'jalapeno', 'https://www.budgetbytes.com/quickie-faux-pho/', " +
                "'chicken stock, five spice blend, chicken, wide rice noodles, jalapeno, lime, green onion, cilantro, sriracha sauce, hoisin sauce');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Quickie Faux Pho', 'lime', 'https://www.budgetbytes.com/quickie-faux-pho/', " +
                "'chicken stock, five spice blend, chicken, wide rice noodles, jalapeno, lime, green onion, cilantro, sriracha sauce, hoisin sauce');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Skillet Chicken With Orzo and Olives', 'chicken', 'https://www.budgetbytes.com/skillet-chicken-with-orzo-and-olives/', " +
                "'chicken thighs, salt, pepper, olive oil, garlic, diced tomatoes, kalamata olives, oregano, chicken broth, orzo, parsley');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Skillet Chicken With Orzo and Olives', 'kalamata olives', 'https://www.budgetbytes.com/skillet-chicken-with-orzo-and-olives/', " +
                "'chicken thighs, salt, pepper, olive oil, garlic, diced tomatoes, kalamata olives, oregano, chicken broth, orzo, parsley');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Skillet Chicken With Orzo and Olives', 'tomatoes', 'https://www.budgetbytes.com/skillet-chicken-with-orzo-and-olives/', " +
                "'chicken thighs, salt, pepper, olive oil, garlic, diced tomatoes, kalamata olives, oregano, chicken broth, orzo, parsley');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Kale Chicken and Gnocchi Soup', 'yellow onion', 'https://www.budgetbytes.com/kale-chicken-and-gnocchi-soup/', " +
                "'olive oil, garlic, yellow onion, carrots, kale, chicken breast, chicken broth, nutmeg, basil, pepper, red pepper flakes, gnocchi, half and half');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Kale Chicken and Gnocchi Soup', 'carrots', 'https://www.budgetbytes.com/kale-chicken-and-gnocchi-soup/', " +
                "'olive oil, garlic, yellow onion, carrots, kale, chicken breast, chicken broth, nutmeg, basil, pepper, red pepper flakes, gnocchi, half and half');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Kale Chicken and Gnocchi Soup', 'kale', 'https://www.budgetbytes.com/kale-chicken-and-gnocchi-soup/', " +
                "'olive oil, garlic, yellow onion, carrots, kale, chicken breast, chicken broth, nutmeg, basil, pepper, red pepper flakes, gnocchi, half and half');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Kale Chicken and Gnocchi Soup', 'chicken', 'https://www.budgetbytes.com/kale-chicken-and-gnocchi-soup/', " +
                "'olive oil, garlic, yellow onion, carrots, kale, chicken breast, chicken broth, nutmeg, basil, pepper, red pepper flakes, gnocchi, half and half');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Matzo Ball Soup', 'yellow onion', 'https://www.budgetbytes.com/matzo-ball-soup/', " +
                "'vegetable oil, garlic, onion, carrots, celery, chicken breasts, chicken broth, water, cracked pepper, fresh dill, eggs, matzo meal, salt, baking powder');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Matzo Ball Soup', 'carrots', 'https://www.budgetbytes.com/matzo-ball-soup/', " +
                "'vegetable oil, garlic, onion, carrots, celery, chicken breasts, chicken broth, water, cracked pepper, fresh dill, eggs, matzo meal, salt, baking powder');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Matzo Ball Soup', 'celery', 'https://www.budgetbytes.com/matzo-ball-soup/', " +
                "'vegetable oil, garlic, onion, carrots, celery, chicken breasts, chicken broth, water, cracked pepper, fresh dill, eggs, matzo meal, salt, baking powder');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Matzo Ball Soup', 'chicken', 'https://www.budgetbytes.com/matzo-ball-soup/', " +
                "'vegetable oil, garlic, onion, carrots, celery, chicken breasts, chicken broth, water, cracked pepper, fresh dill, eggs, matzo meal, salt, baking powder');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Matzo Ball Soup', 'eggs', 'https://www.budgetbytes.com/matzo-ball-soup/', " +
                "'vegetable oil, garlic, onion, carrots, celery, chicken breasts, chicken broth, water, cracked pepper, fresh dill, eggs, matzo meal, salt, baking powder');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Yellow Rice Chicken Skillet', 'chicken', 'https://www.budgetbytes.com/yellow-rice-chicken-skillet/', " +
                "'vegetable oil, chicken thighs, salt, pepper, garlic, turmeric, cumin, cinnamon, jasmine rice, frozen peas, chicken broth, cilantro');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Yellow Rice Chicken Skillet', 'peas', 'https://www.budgetbytes.com/yellow-rice-chicken-skillet/', " +
                "'vegetable oil, chicken thighs, salt, pepper, garlic, turmeric, cumin, cinnamon, jasmine rice, frozen peas, chicken broth, cilantro');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Creole White Beans With Chicken', 'navy beans', 'https://www.budgetbytes.com/creole-white-beans-with-chicken/', " +
                "'navy beans, olive oil, garlic, yellow onion, celery, green bell peppers, red bell peppers, parsley, chicken thighs, creole seasoning blend, oregano, smoked paprika, pepper');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Creole White Beans With Chicken', 'yellow onion', 'https://www.budgetbytes.com/creole-white-beans-with-chicken/', " +
                "'navy beans, olive oil, garlic, yellow onion, celery, green bell peppers, red bell peppers, parsley, chicken thighs, creole seasoning blend, oregano, smoked paprika, pepper');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Creole White Beans With Chicken', 'celery', 'https://www.budgetbytes.com/creole-white-beans-with-chicken/', " +
                "'navy beans, olive oil, garlic, yellow onion, celery, green bell peppers, red bell peppers, parsley, chicken thighs, creole seasoning blend, oregano, smoked paprika, pepper');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Creole White Beans With Chicken', 'bell peppers', 'https://www.budgetbytes.com/creole-white-beans-with-chicken/', " +
                "'navy beans, olive oil, garlic, yellow onion, celery, green bell peppers, red bell peppers, parsley, chicken thighs, creole seasoning blend, oregano, smoked paprika, pepper');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Creole White Beans With Chicken', 'chicken', 'https://www.budgetbytes.com/creole-white-beans-with-chicken/', " +
                "'navy beans, olive oil, garlic, yellow onion, celery, green bell peppers, red bell peppers, parsley, chicken thighs, creole seasoning blend, oregano, smoked paprika, pepper');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Lemon Pepper Chicken With Orzo', 'chicken', 'https://www.budgetbytes.com/lemon-pepper-chicken-with-orzo/', " +
                "'chicken thighs, lemon pepper seasoning, canola oil, garlic, chicken broth, parsley, orzo, feta cheese');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Lemon Pepper Chicken With Orzo', 'feta cheese', 'https://www.budgetbytes.com/lemon-pepper-chicken-with-orzo/', " +
                "'chicken thighs, lemon pepper seasoning, canola oil, garlic, chicken broth, parsley, orzo, feta cheese');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Chicken Kale Caesar Wraps', 'kale', 'https://www.budgetbytes.com/chicken-kale-caesar-wraps/', " +
                "'kale, chickpeas, carrots, rotisserie chicken, Parmesan cheese, pepper, Caesar dressing, tortillas');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Chicken Kale Caesar Wraps', 'chickpeas', 'https://www.budgetbytes.com/chicken-kale-caesar-wraps/', " +
                "'kale, chickpeas, carrots, rotisserie chicken, Parmesan cheese, pepper, Caesar dressing, tortillas');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Chicken Kale Caesar Wraps', 'carrots', 'https://www.budgetbytes.com/chicken-kale-caesar-wraps/', " +
                "'kale, chickpeas, carrots, rotisserie chicken, Parmesan cheese, pepper, Caesar dressing, tortillas');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Chicken Kale Caesar Wraps', 'chicken', 'https://www.budgetbytes.com/chicken-kale-caesar-wraps/', " +
                "'kale, chickpeas, carrots, rotisserie chicken, Parmesan cheese, pepper, Caesar dressing, tortillas');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Chicken Kale Caesar Wraps', 'parmesan cheese', 'https://www.budgetbytes.com/chicken-kale-caesar-wraps/', " +
                "'kale, chickpeas, carrots, rotisserie chicken, Parmesan cheese, pepper, Caesar dressing, tortillas');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Sweet and Spicy Chicken Bowls', 'chicken', 'https://www.budgetbytes.com/sweet-n-spicy-chicken-bowls/', " +
                "'mild chili powder, cumin, garlic powder, smoked paprika, cayenne pepper, salt, pepper, olive oil, honey, apple cider vinegar, brown rice, chicken broth, chicken breast, pineapple tidbits, black beans, avocado, green onion, cilantro');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Sweet and Spicy Chicken Bowls', 'honey', 'https://www.budgetbytes.com/sweet-n-spicy-chicken-bowls/', " +
                "'mild chili powder, cumin, garlic powder, smoked paprika, cayenne pepper, salt, pepper, olive oil, honey, apple cider vinegar, brown rice, chicken broth, chicken breast, pineapple tidbits, black beans, avocado, green onion, cilantro');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Sweet and Spicy Chicken Bowls', 'pineapple', 'https://www.budgetbytes.com/sweet-n-spicy-chicken-bowls/', " +
                "'mild chili powder, cumin, garlic powder, smoked paprika, cayenne pepper, salt, pepper, olive oil, honey, apple cider vinegar, brown rice, chicken broth, chicken breast, pineapple tidbits, black beans, avocado, green onion, cilantro');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Sweet and Spicy Chicken Bowls', 'black beans', 'https://www.budgetbytes.com/sweet-n-spicy-chicken-bowls/', " +
                "'mild chili powder, cumin, garlic powder, smoked paprika, cayenne pepper, salt, pepper, olive oil, honey, apple cider vinegar, brown rice, chicken broth, chicken breast, pineapple tidbits, black beans, avocado, green onion, cilantro');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Sweet and Spicy Chicken Bowls', 'avocado', 'https://www.budgetbytes.com/sweet-n-spicy-chicken-bowls/', " +
                "'mild chili powder, cumin, garlic powder, smoked paprika, cayenne pepper, salt, pepper, olive oil, honey, apple cider vinegar, brown rice, chicken broth, chicken breast, pineapple tidbits, black beans, avocado, green onion, cilantro');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Balsamic Chicken Thighs', 'chicken', 'https://www.budgetbytes.com/balsamic-chicken-thighs/', " +
                "'olive oil, brown sugar, balsamic vinegar, soy sauce, pepper, chicken thighs, parsley');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Creamy Chicken and Black Bean Enchiladas', 'cream cheese', 'https://www.budgetbytes.com/snap-challenge-creamy-chicken-black-bean-enchiladas/', " +
                "'vegetable oil, garlic, diced tomatoes with green chiles, cream cheese, shredded chicken, black beans, corn, cumin, tortillas, red enchilada sauce');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Creamy Chicken and Black Bean Enchiladas', 'chicken', 'https://www.budgetbytes.com/snap-challenge-creamy-chicken-black-bean-enchiladas/', " +
                "'vegetable oil, garlic, diced tomatoes with green chiles, cream cheese, shredded chicken, black beans, corn, cumin, tortillas, red enchilada sauce');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Creamy Chicken and Black Bean Enchiladas', 'black beans', 'https://www.budgetbytes.com/snap-challenge-creamy-chicken-black-bean-enchiladas/', " +
                "'vegetable oil, garlic, diced tomatoes with green chiles, cream cheese, shredded chicken, black beans, corn, cumin, tortillas, red enchilada sauce');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Creamy Chicken and Black Bean Enchiladas', 'corn', 'https://www.budgetbytes.com/snap-challenge-creamy-chicken-black-bean-enchiladas/', " +
                "'vegetable oil, garlic, diced tomatoes with green chiles, cream cheese, shredded chicken, black beans, corn, cumin, tortillas, red enchilada sauce');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Soy Dijon Chicken Thighs With Sweet Potatoes', 'chicken', 'https://www.budgetbytes.com/snap-challenge-soy-dijon-chicken-thighs-sweet-potatoes/', " +
                "'Dijon mustard, soy sauce, vegetable oil, brown sugar, garlic, pepper, chicken thighs, sweet potato');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Soy Dijon Chicken Thighs With Sweet Potatoes', 'sweet potatoes', 'https://www.budgetbytes.com/snap-challenge-soy-dijon-chicken-thighs-sweet-potatoes/', " +
                "'Dijon mustard, soy sauce, vegetable oil, brown sugar, garlic, pepper, chicken thighs, sweet potato');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Chicken Arrabbiata', 'chicken', 'https://www.budgetbytes.com/chicken-arrabbiata/', " +
                "'olive oil, chicken thighs, salt, pepper, flour, garlic, diced tomatoes, crushed tomatoes, basil, crushed red peppers, chicken broth, parsley, pasta');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Artichoke Chicken Skillet', 'chicken', 'https://www.budgetbytes.com/artichoke-chicken-skillet/', " +
                "'chicken breast, salt, pepper, olive oil, garlic, petite diced tomatoes, quartered artichoke hearts, basil, lemon zest, parsley, pasta');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Artichoke Chicken Skillet', 'lemon', 'https://www.budgetbytes.com/artichoke-chicken-skillet/', " +
                "'chicken breast, salt, pepper, olive oil, garlic, petite diced tomatoes, quartered artichoke hearts, basil, lemon zest, parsley, pasta');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Creamy Pesto Pasta With Chicken and Broccoli', 'broccoli', 'https://www.budgetbytes.com/creamy-pesto-pasta-chicken-broccoli/', " +
                "'bow tie pasta, frozen broccoli florets, olive oil, chicken breast, pesto, chicken broth, cream cheese');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Creamy Pesto Pasta With Chicken and Broccoli', 'chicken', 'https://www.budgetbytes.com/creamy-pesto-pasta-chicken-broccoli/', " +
                "'bow tie pasta, frozen broccoli florets, olive oil, chicken breast, pesto, chicken broth, cream cheese');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Creamy Pesto Pasta With Chicken and Broccoli', 'cream cheese', 'https://www.budgetbytes.com/creamy-pesto-pasta-chicken-broccoli/', " +
                "'bow tie pasta, frozen broccoli florets, olive oil, chicken breast, pesto, chicken broth, cream cheese');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Slow Cooker Chicken and Dumplings', 'yellow onion', 'https://www.budgetbytes.com/slow-cooker-chicken-dumplings/', " +
                "'garlic, yellow onion, celery, carrots, chicken breasts, bay leaf, basil, thyme, pepper, salt, all-purpose flour, baking powder, parsley, butter, sugar, milk');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Slow Cooker Chicken and Dumplings', 'celery', 'https://www.budgetbytes.com/slow-cooker-chicken-dumplings/', " +
                "'garlic, yellow onion, celery, carrots, chicken breasts, bay leaf, basil, thyme, pepper, salt, all-purpose flour, baking powder, parsley, butter, sugar, milk');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Slow Cooker Chicken and Dumplings', 'carrots', 'https://www.budgetbytes.com/slow-cooker-chicken-dumplings/', " +
                "'garlic, yellow onion, celery, carrots, chicken breasts, bay leaf, basil, thyme, pepper, salt, all-purpose flour, baking powder, parsley, butter, sugar, milk');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Slow Cooker Chicken and Dumplings', 'chickdn', 'https://www.budgetbytes.com/slow-cooker-chicken-dumplings/', " +
                "'garlic, yellow onion, celery, carrots, chicken breasts, bay leaf, basil, thyme, pepper, salt, all-purpose flour, baking powder, parsley, butter, sugar, milk');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Slow Cooker Chicken and Dumplings', 'butter', 'https://www.budgetbytes.com/slow-cooker-chicken-dumplings/', " +
                "'garlic, yellow onion, celery, carrots, chicken breasts, bay leaf, basil, thyme, pepper, salt, all-purpose flour, baking powder, parsley, butter, sugar, milk');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Slow Cooker Chicken and Dumplings', 'milk', 'https://www.budgetbytes.com/slow-cooker-chicken-dumplings/', " +
                "'garlic, yellow onion, celery, carrots, chicken breasts, bay leaf, basil, thyme, pepper, salt, all-purpose flour, baking powder, parsley, butter, sugar, milk');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Cajun Chicken Pasta', 'cream cheese', 'https://www.budgetbytes.com/cajun-chicken-pasta/', " +
                "'paprika (smoked or sweet), garlic powder, cayenne pepper, oregano, thyme, salt, pepper, chicken breast, butter, green bell pepper, red bell pepper, yellow onion, fire roasted tomatoes, cream cheese, fettuccine, green onions');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Cajun Chicken Pasta', 'yellow onion', 'https://www.budgetbytes.com/cajun-chicken-pasta/', " +
                "'paprika (smoked or sweet), garlic powder, cayenne pepper, oregano, thyme, salt, pepper, chicken breast, butter, green bell pepper, red bell pepper, yellow onion, fire roasted tomatoes, cream cheese, fettuccine, green onions');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Cajun Chicken Pasta', 'bell pepper', 'https://www.budgetbytes.com/cajun-chicken-pasta/', " +
                "'paprika (smoked or sweet), garlic powder, cayenne pepper, oregano, thyme, salt, pepper, chicken breast, butter, green bell pepper, red bell pepper, yellow onion, fire roasted tomatoes, cream cheese, fettuccine, green onions');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Cajun Chicken Pasta', 'butter', 'https://www.budgetbytes.com/cajun-chicken-pasta/', " +
                "'paprika (smoked or sweet), garlic powder, cayenne pepper, oregano, thyme, salt, pepper, chicken breast, butter, green bell pepper, red bell pepper, yellow onion, fire roasted tomatoes, cream cheese, fettuccine, green onions');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Cajun Chicken Pasta', 'chicken', 'https://www.budgetbytes.com/cajun-chicken-pasta/', " +
                "'paprika (smoked or sweet), garlic powder, cayenne pepper, oregano, thyme, salt, pepper, chicken breast, butter, green bell pepper, red bell pepper, yellow onion, fire roasted tomatoes, cream cheese, fettuccine, green onions');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Maple Dijon Chicken Thighs', 'chicken', 'https://www.budgetbytes.com/maple-dijon-chicken/', " +
                "'Dijon mustard, maple syrup, olive oil, soy sauce, garlic, rosemary, chicken thighs');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Slow Cooker Jerk Chicken', 'chicken', 'https://www.budgetbytes.com/slow-cooker-jerk-chicken/', " +
                "'chicken pieces, molasses, vegetable oil, soy sauce, garlic, ginger, cinnamon, allspice, thyme, nutmeg, medium onion, medium orange, serrano or jalapeno pepper');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Slow Cooker Jerk Chicken', 'yellow onion', 'https://www.budgetbytes.com/slow-cooker-jerk-chicken/', " +
                "'chicken pieces, molasses, vegetable oil, soy sauce, garlic, ginger, cinnamon, allspice, thyme, nutmeg, medium onion, medium orange, serrano or jalapeno pepper');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Slow Cooker Jerk Chicken', 'jalapeno pepper', 'https://www.budgetbytes.com/slow-cooker-jerk-chicken/', " +
                "'chicken pieces, molasses, vegetable oil, soy sauce, garlic, ginger, cinnamon, allspice, thyme, nutmeg, medium onion, medium orange, serrano or jalapeno pepper');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Slow Cooker Jerk Chicken', 'serrano pepper', 'https://www.budgetbytes.com/slow-cooker-jerk-chicken/', " +
                "'chicken pieces, molasses, vegetable oil, soy sauce, garlic, ginger, cinnamon, allspice, thyme, nutmeg, medium onion, medium orange, serrano or jalapeno pepper');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Honey Mustard Chicken Salad', 'chicken', 'https://www.budgetbytes.com/honey-mustard-chicken-salad/', " +
                "'chicken breast, celery, dried cranberries, mayonnaise, honey, dijon mustard, apple cider vinegar, salt, garlic powder, paprika');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Honey Mustard Chicken Salad', 'celery', 'https://www.budgetbytes.com/honey-mustard-chicken-salad/', " +
                "'chicken breast, celery, dried cranberries, mayonnaise, honey, dijon mustard, apple cider vinegar, salt, garlic powder, paprika');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Honey Mustard Chicken Salad', 'mayonnaise', 'https://www.budgetbytes.com/honey-mustard-chicken-salad/', " +
                "'chicken breast, celery, dried cranberries, mayonnaise, honey, dijon mustard, apple cider vinegar, salt, garlic powder, paprika');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Honey Mustard Chicken Salad', 'honey', 'https://www.budgetbytes.com/honey-mustard-chicken-salad/', " +
                "'chicken breast, celery, dried cranberries, mayonnaise, honey, dijon mustard, apple cider vinegar, salt, garlic powder, paprika');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Honey Balsamic Chicken Tenders', 'chicken', 'https://www.budgetbytes.com/honey-balsamic-chicken-tenders/', " +
                "'chicken breast, balsamic vinegar, garlic, olive oil, salt, pepper, butter, honey');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Honey Balsamic Chicken Tenders', 'butter', 'https://www.budgetbytes.com/honey-balsamic-chicken-tenders/', " +
                "'chicken breast, balsamic vinegar, garlic, olive oil, salt, pepper, butter, honey');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Honey Spice Chicken Thighs', 'chicken', 'https://www.budgetbytes.com/honey-spice-chicken-thighs/', " +
                "'chicken thighs, honey, cider vinegar, chili powder, garlic powder, smoked paprika, cumin, cayenne pepper, salt');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Honey Spice Chicken Thighs', 'honey', 'https://www.budgetbytes.com/honey-spice-chicken-thighs/', " +
                "'chicken thighs, honey, cider vinegar, chili powder, garlic powder, smoked paprika, cumin, cayenne pepper, salt');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Chicken and Apple Salad', 'chicken', 'https://www.budgetbytes.com/chicken-n-apple-salad/', " +
                "'chicken breast halves, apples, red onion, dried cranberries, mayonnaise, plain yogurt, dijon mustard, red wine vinegar, salt, pepper');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Chicken and Apple Salad', 'apples', 'https://www.budgetbytes.com/chicken-n-apple-salad/', " +
                "'chicken breast halves, apples, red onion, dried cranberries, mayonnaise, plain yogurt, dijon mustard, red wine vinegar, salt, pepper');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Chicken and Apple Salad', 'red onion', 'https://www.budgetbytes.com/chicken-n-apple-salad/', " +
                "'chicken breast halves, apples, red onion, dried cranberries, mayonnaise, plain yogurt, dijon mustard, red wine vinegar, salt, pepper');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Chicken and Apple Salad', 'mayonnaise', 'https://www.budgetbytes.com/chicken-n-apple-salad/', " +
                "'chicken breast halves, apples, red onion, dried cranberries, mayonnaise, plain yogurt, dijon mustard, red wine vinegar, salt, pepper');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Chicken and Apple Salad', 'plain yogurt', 'https://www.budgetbytes.com/chicken-n-apple-salad/', " +
                "'chicken breast halves, apples, red onion, dried cranberries, mayonnaise, plain yogurt, dijon mustard, red wine vinegar, salt, pepper');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Balsamic Bruschetta Chicken', 'chicken', 'https://www.budgetbytes.com/balsamic-bruschetta-chicken/', " +
                "'chicken breast, balsamic vinegar, olive oil, minced garlic, salt, pepper, roma tomatoes, sweet vidalia onion, shredded mozzarella');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Balsamic Bruschetta Chicken', 'roma tomatoes', 'https://www.budgetbytes.com/balsamic-bruschetta-chicken/', " +
                "'chicken breast, balsamic vinegar, olive oil, minced garlic, salt, pepper, roma tomatoes, sweet vidalia onion, shredded mozzarella');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Balsamic Bruschetta Chicken', 'sweet vidalia onion', 'https://www.budgetbytes.com/balsamic-bruschetta-chicken/', " +
                "'chicken breast, balsamic vinegar, olive oil, minced garlic, salt, pepper, roma tomatoes, sweet vidalia onion, shredded mozzarella');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Balsamic Bruschetta Chicken', 'mozzarella cheese', 'https://www.budgetbytes.com/balsamic-bruschetta-chicken/', " +
                "'chicken breast, balsamic vinegar, olive oil, minced garlic, salt, pepper, roma tomatoes, sweet vidalia onion, shredded mozzarella');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Creamy Mushroom Pasta With Chicken', 'yellow onion', 'https://www.budgetbytes.com/creamy-mushroom-pasta-w-chicken/', " +
                "'olive oil, onion, garlic, mushrooms, all purpose flour, chicken base, water, white wine, half and half, dried thyme, salt, pepper, parsley, Parmesan cheese, pasta, chicken breast');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Creamy Mushroom Pasta With Chicken', 'mushrooms', 'https://www.budgetbytes.com/creamy-mushroom-pasta-w-chicken/', " +
                "'olive oil, onion, garlic, mushrooms, all purpose flour, chicken base, water, white wine, half and half, dried thyme, salt, pepper, parsley, Parmesan cheese, pasta, chicken breast');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Creamy Mushroom Pasta With Chicken', 'half and half', 'https://www.budgetbytes.com/creamy-mushroom-pasta-w-chicken/', " +
                "'olive oil, onion, garlic, mushrooms, all purpose flour, chicken base, water, white wine, half and half, dried thyme, salt, pepper, parsley, Parmesan cheese, pasta, chicken breast');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Creamy Mushroom Pasta With Chicken', 'parmesan cheese', 'https://www.budgetbytes.com/creamy-mushroom-pasta-w-chicken/', " +
                "'olive oil, onion, garlic, mushrooms, all purpose flour, chicken base, water, white wine, half and half, dried thyme, salt, pepper, parsley, Parmesan cheese, pasta, chicken breast');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Creamy Mushroom Pasta With Chicken', 'chicken', 'https://www.budgetbytes.com/creamy-mushroom-pasta-w-chicken/', " +
                "'olive oil, onion, garlic, mushrooms, all purpose flour, chicken base, water, white wine, half and half, dried thyme, salt, pepper, parsley, Parmesan cheese, pasta, chicken breast');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Raspberry Chipotle BBQ Chicken', 'yellow onion', 'https://www.budgetbytes.com/raspberry-chipotle-bbq-chicken/', " +
                "'vegetable oil, yellow onion, garlic, tomato sauce, raspberry jam, cider vinegar, chipotle pepper in adobo sauce, chicken thighs');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Raspberry Chipotle BBQ Chicken', 'chicken', 'https://www.budgetbytes.com/raspberry-chipotle-bbq-chicken/', " +
                "'vegetable oil, yellow onion, garlic, tomato sauce, raspberry jam, cider vinegar, chipotle pepper in adobo sauce, chicken thighs');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Jambalaya', 'smoked sausage', 'https://www.budgetbytes.com/jambalaya/', " +
                "'chicken, smoked sausage, garlic, seasoning mix, long grain rice, cayenne pepper, tomato paste, water, diced tomatoes, bay leaves, thyme, parsley, salt, pepper');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Jambalaya', 'chicken', 'https://www.budgetbytes.com/jambalaya/', " +
                "'chicken, smoked sausage, garlic, seasoning mix, long grain rice, cayenne pepper, tomato paste, water, diced tomatoes, bay leaves, thyme, parsley, salt, pepper');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Chicken Chili Verde', 'yellow onion', 'https://www.budgetbytes.com/chicken-chili-verde/', " +
                "'olive oil, yellow onion, garlic, chicken breast halves, white beans, bouillon, cumin, oregano, cayenne powder, red pepper flakes, diced green chiles, salt, pepper, monterrey jack cheese, cilantro');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Chicken Chili Verde', 'chicken', 'https://www.budgetbytes.com/chicken-chili-verde/', " +
                "'olive oil, yellow onion, garlic, chicken breast halves, white beans, bouillon, cumin, oregano, cayenne powder, red pepper flakes, diced green chiles, salt, pepper, monterrey jack cheese, cilantro');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Chicken Chili Verde', 'white beans', 'https://www.budgetbytes.com/chicken-chili-verde/', " +
                "'olive oil, yellow onion, garlic, chicken breast halves, white beans, bouillon, cumin, oregano, cayenne powder, red pepper flakes, diced green chiles, salt, pepper, monterrey jack cheese, cilantro');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Chicken Chili Verde', 'monterrey jack cheese', 'https://www.budgetbytes.com/chicken-chili-verde/', " +
                "'olive oil, yellow onion, garlic, chicken breast halves, white beans, bouillon, cumin, oregano, cayenne powder, red pepper flakes, diced green chiles, salt, pepper, monterrey jack cheese, cilantro');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Pepperoni Stuffed Chicken', 'chicken', 'https://www.budgetbytes.com/pepperoni-stuffed-chicken/', " +
                "'chicken breast, mozzarella cheese, pepperoni, egg, all-purpose flour, breadcrumbs, vegetable oil, salt, pepper');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Pepperoni Stuffed Chicken', 'mozzarella cheese', 'https://www.budgetbytes.com/pepperoni-stuffed-chicken/', " +
                "'chicken breast, mozzarella cheese, pepperoni, egg, all-purpose flour, breadcrumbs, vegetable oil, salt, pepper');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Pepperoni Stuffed Chicken', 'pepperoni', 'https://www.budgetbytes.com/pepperoni-stuffed-chicken/', " +
                "'chicken breast, mozzarella cheese, pepperoni, egg, all-purpose flour, breadcrumbs, vegetable oil, salt, pepper');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Pepperoni Stuffed Chicken', 'eggs', 'https://www.budgetbytes.com/pepperoni-stuffed-chicken/', " +
                "'chicken breast, mozzarella cheese, pepperoni, egg, all-purpose flour, breadcrumbs, vegetable oil, salt, pepper');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Grilled Pesto Chicken', 'chicken', 'https://www.budgetbytes.com/grilled-pesto-chicken/', " +
                "'chicken breast, pesto, roma tomatoes, mozzarella cheese, salt, pepper');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Grilled Pesto Chicken', 'roma tomatoes', 'https://www.budgetbytes.com/grilled-pesto-chicken/', " +
                "'chicken breast, pesto, roma tomatoes, mozzarella cheese, salt, pepper');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Grilled Pesto Chicken', 'mozzarella cheese', 'https://www.budgetbytes.com/grilled-pesto-chicken/', " +
                "'chicken breast, pesto, roma tomatoes, mozzarella cheese, salt, pepper');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Cheesy Cottage Pie', 'russet potatoes', 'https://www.budgetbytes.com/cheesy-cottage-pie/', " +
                "'russet potatoes, salt, butter, milk, shredded cheddar, olive oil, yellow onion, garlic, ground beef, all-purpose flour, beef broth, Worcestershire sauce, rosemary, thyme, frozen peas and carrots, corn');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Cheesy Cottage Pie', 'butter', 'https://www.budgetbytes.com/cheesy-cottage-pie/', " +
                "'russet potatoes, salt, butter, milk, shredded cheddar, olive oil, yellow onion, garlic, ground beef, all-purpose flour, beef broth, Worcestershire sauce, rosemary, thyme, frozen peas and carrots, corn');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Cheesy Cottage Pie', 'milk', 'https://www.budgetbytes.com/cheesy-cottage-pie/', " +
                "'russet potatoes, salt, butter, milk, shredded cheddar, olive oil, yellow onion, garlic, ground beef, all-purpose flour, beef broth, Worcestershire sauce, rosemary, thyme, frozen peas and carrots, corn');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Cheesy Cottage Pie', 'yellow onion', 'https://www.budgetbytes.com/cheesy-cottage-pie/', " +
                "'russet potatoes, salt, butter, milk, shredded cheddar, olive oil, yellow onion, garlic, ground beef, all-purpose flour, beef broth, Worcestershire sauce, rosemary, thyme, frozen peas and carrots, corn');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Cheesy Cottage Pie', 'cheddar cheese', 'https://www.budgetbytes.com/cheesy-cottage-pie/', " +
                "'russet potatoes, salt, butter, milk, shredded cheddar, olive oil, yellow onion, garlic, ground beef, all-purpose flour, beef broth, Worcestershire sauce, rosemary, thyme, frozen peas and carrots, corn');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Cheesy Cottage Pie', 'ground beef', 'https://www.budgetbytes.com/cheesy-cottage-pie/', " +
                "'russet potatoes, salt, butter, milk, shredded cheddar, olive oil, yellow onion, garlic, ground beef, all-purpose flour, beef broth, Worcestershire sauce, rosemary, thyme, frozen peas and carrots, corn');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Cheesy Cottage Pie', 'carrots', 'https://www.budgetbytes.com/cheesy-cottage-pie/', " +
                "'russet potatoes, salt, butter, milk, shredded cheddar, olive oil, yellow onion, garlic, ground beef, all-purpose flour, beef broth, Worcestershire sauce, rosemary, thyme, frozen peas and carrots, corn');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Cheesy Cottage Pie', 'peas', 'https://www.budgetbytes.com/cheesy-cottage-pie/', " +
                "'russet potatoes, salt, butter, milk, shredded cheddar, olive oil, yellow onion, garlic, ground beef, all-purpose flour, beef broth, Worcestershire sauce, rosemary, thyme, frozen peas and carrots, corn');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Cheesy Cottage Pie', 'corn', 'https://www.budgetbytes.com/cheesy-cottage-pie/', " +
                "'russet potatoes, salt, butter, milk, shredded cheddar, olive oil, yellow onion, garlic, ground beef, all-purpose flour, beef broth, Worcestershire sauce, rosemary, thyme, frozen peas and carrots, corn');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('BBQ Beef and Cabbage', 'ground beef', 'https://www.budgetbytes.com/bbq-beef-and-cabbage/', " +
                "'olive oil, ground beef, onion, smoked paprika, garlic powder, salt, shredded cabbage, tomato sauce, apple cider vinegar, brown sugar, Worcestershire sauce, dijon mustard, shredded cheddar, green onion');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('BBQ Beef and Cabbage', 'yellow onion', 'https://www.budgetbytes.com/bbq-beef-and-cabbage/', " +
                "'olive oil, ground beef, onion, smoked paprika, garlic powder, salt, shredded cabbage, tomato sauce, apple cider vinegar, brown sugar, Worcestershire sauce, dijon mustard, shredded cheddar, green onion');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('BBQ Beef and Cabbage', 'cabbage', 'https://www.budgetbytes.com/bbq-beef-and-cabbage/', " +
                "'olive oil, ground beef, onion, smoked paprika, garlic powder, salt, shredded cabbage, tomato sauce, apple cider vinegar, brown sugar, Worcestershire sauce, dijon mustard, shredded cheddar, green onion');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('BBQ Beef and Cabbage', 'cheddar cheese', 'https://www.budgetbytes.com/bbq-beef-and-cabbage/', " +
                "'olive oil, ground beef, onion, smoked paprika, garlic powder, salt, shredded cabbage, tomato sauce, apple cider vinegar, brown sugar, Worcestershire sauce, dijon mustard, shredded cheddar, green onion');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('One Pot American Goulash', 'yellow onion', 'https://www.budgetbytes.com/goulash/', " +
                "'olive oil, medium onion, garlic, bell pepper, ground beef, red wine, diced tomatoes, tomato sauce, soy sauce, bay leaves, oregano, basil, red pepper flakes, water, elbow macaroni, salt');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('One Pot American Goulash', 'ground beef', 'https://www.budgetbytes.com/goulash/', " +
                "'olive oil, medium onion, garlic, bell pepper, ground beef, red wine, diced tomatoes, tomato sauce, soy sauce, bay leaves, oregano, basil, red pepper flakes, water, elbow macaroni, salt');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('One Pot American Goulash', 'bell pepper', 'https://www.budgetbytes.com/goulash/', " +
                "'olive oil, medium onion, garlic, bell pepper, ground beef, red wine, diced tomatoes, tomato sauce, soy sauce, bay leaves, oregano, basil, red pepper flakes, water, elbow macaroni, salt');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Cheddar Cheeseburger Meatloaf', 'eggs', 'https://www.budgetbytes.com/cheddar-cheeseburger-meatloaf/', " +
                "'plain breadcrumbs, smoked paprika, garlic powder, salt, egg, minced onion, sharp cheddar, ground beef, ketchup, brown sugar, yellow mustard, Worcestershire sauce');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Cheddar Cheeseburger Meatloaf', 'ground beef', 'https://www.budgetbytes.com/cheddar-cheeseburger-meatloaf/', " +
                "'plain breadcrumbs, smoked paprika, garlic powder, salt, egg, minced onion, sharp cheddar, ground beef, ketchup, brown sugar, yellow mustard, Worcestershire sauce');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Cheddar Cheeseburger Meatloaf', 'yellow onion', 'https://www.budgetbytes.com/cheddar-cheeseburger-meatloaf/', " +
                "'plain breadcrumbs, smoked paprika, garlic powder, salt, egg, minced onion, sharp cheddar, ground beef, ketchup, brown sugar, yellow mustard, Worcestershire sauce');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Cheddar Cheeseburger Meatloaf', 'sharp cheddar cheese', 'https://www.budgetbytes.com/cheddar-cheeseburger-meatloaf/', " +
                "'plain breadcrumbs, smoked paprika, garlic powder, salt, egg, minced onion, sharp cheddar, ground beef, ketchup, brown sugar, yellow mustard, Worcestershire sauce');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Beef and Cabbage Soup', 'yellow onion', 'https://www.budgetbytes.com/beef-and-cabbage-soup/', " +
                "'yellow onion, garlic, olive oil, ground beef, shredded cabbage, stewed tomatoes, oregano, basil, pepper, beef broth, Worcestershire sauce, lemon, parsley');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Beef and Cabbage Soup', 'ground beef', 'https://www.budgetbytes.com/beef-and-cabbage-soup/', " +
                "'yellow onion, garlic, olive oil, ground beef, shredded cabbage, stewed tomatoes, oregano, basil, pepper, beef broth, Worcestershire sauce, lemon, parsley');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Beef and Cabbage Soup', 'cabbage', 'https://www.budgetbytes.com/beef-and-cabbage-soup/', " +
                "'yellow onion, garlic, olive oil, ground beef, shredded cabbage, stewed tomatoes, oregano, basil, pepper, beef broth, Worcestershire sauce, lemon, parsley');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Beef and Cabbage Soup', 'lemon', 'https://www.budgetbytes.com/beef-and-cabbage-soup/', " +
                "'yellow onion, garlic, olive oil, ground beef, shredded cabbage, stewed tomatoes, oregano, basil, pepper, beef broth, Worcestershire sauce, lemon, parsley');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Sheet Pan BBQ Meatloaf Dinner', 'broccoli', 'https://www.budgetbytes.com/sheet-pan-bbq-meatloaf-dinner/', " +
                "'sweet potatoes, broccoli florets, olive oil, seasoning salt, salt, pepper, ground beef, large egg, bread crumbs, BBQ sauce, smoked paprika, garlic powder');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Sheet Pan BBQ Meatloaf Dinner', 'sweet potatoes', 'https://www.budgetbytes.com/sheet-pan-bbq-meatloaf-dinner/', " +
                "'sweet potatoes, broccoli florets, olive oil, seasoning salt, salt, pepper, ground beef, large egg, bread crumbs, BBQ sauce, smoked paprika, garlic powder');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Sheet Pan BBQ Meatloaf Dinner', 'ground beef', 'https://www.budgetbytes.com/sheet-pan-bbq-meatloaf-dinner/', " +
                "'sweet potatoes, broccoli florets, olive oil, seasoning salt, salt, pepper, ground beef, large egg, bread crumbs, BBQ sauce, smoked paprika, garlic powder');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Sheet Pan BBQ Meatloaf Dinner', 'egg', 'https://www.budgetbytes.com/sheet-pan-bbq-meatloaf-dinner/', " +
                "'sweet potatoes, broccoli florets, olive oil, seasoning salt, salt, pepper, ground beef, large egg, bread crumbs, BBQ sauce, smoked paprika, garlic powder');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Cheeseburger Salad With Animal Sauce', 'mayonnaise', 'https://www.budgetbytes.com/cheeseburger-salad-animal-sauce/', " +
                "'mayonnaise, ketchup, olive oil, relish, apple cider vinegar, sugar, salt, pepper, ground beef, seasoning salt, cooking oil, romaine lettuce, red onion, tomato, cheddar cheese');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Cheeseburger Salad With Animal Sauce', 'ground beef', 'https://www.budgetbytes.com/cheeseburger-salad-animal-sauce/', " +
                "'mayonnaise, ketchup, olive oil, relish, apple cider vinegar, sugar, salt, pepper, ground beef, seasoning salt, cooking oil, romaine lettuce, red onion, tomato, cheddar cheese');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Cheeseburger Salad With Animal Sauce', 'romaine lettuce', 'https://www.budgetbytes.com/cheeseburger-salad-animal-sauce/', " +
                "'mayonnaise, ketchup, olive oil, relish, apple cider vinegar, sugar, salt, pepper, ground beef, seasoning salt, cooking oil, romaine lettuce, red onion, tomato, cheddar cheese');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Cheeseburger Salad With Animal Sauce', 'red onion', 'https://www.budgetbytes.com/cheeseburger-salad-animal-sauce/', " +
                "'mayonnaise, ketchup, olive oil, relish, apple cider vinegar, sugar, salt, pepper, ground beef, seasoning salt, cooking oil, romaine lettuce, red onion, tomato, cheddar cheese');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Cheeseburger Salad With Animal Sauce', 'tomato', 'https://www.budgetbytes.com/cheeseburger-salad-animal-sauce/', " +
                "'mayonnaise, ketchup, olive oil, relish, apple cider vinegar, sugar, salt, pepper, ground beef, seasoning salt, cooking oil, romaine lettuce, red onion, tomato, cheddar cheese');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Cheeseburger Salad With Animal Sauce', 'cheddar cheese', 'https://www.budgetbytes.com/cheeseburger-salad-animal-sauce/', " +
                "'mayonnaise, ketchup, olive oil, relish, apple cider vinegar, sugar, salt, pepper, ground beef, seasoning salt, cooking oil, romaine lettuce, red onion, tomato, cheddar cheese');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Instant Pot Beef Stew', 'beef', 'https://www.budgetbytes.com/instant-pot-beef-stew/', " +
                "'beef stew meat, salt, pepper, flour, butter, beef broth, Worcestershire sauce, soy sauce, brown sugar, garlic, tomato paste, rosemary, thyme, frozen pearl onions, mushrooms, carrots, potatoes');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Instant Pot Beef Stew', 'butter', 'https://www.budgetbytes.com/instant-pot-beef-stew/', " +
                "'beef stew meat, salt, pepper, flour, butter, beef broth, Worcestershire sauce, soy sauce, brown sugar, garlic, tomato paste, rosemary, thyme, frozen pearl onions, mushrooms, carrots, potatoes');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Instant Pot Beef Stew', 'mushrooms', 'https://www.budgetbytes.com/instant-pot-beef-stew/', " +
                "'beef stew meat, salt, pepper, flour, butter, beef broth, Worcestershire sauce, soy sauce, brown sugar, garlic, tomato paste, rosemary, thyme, frozen pearl onions, mushrooms, carrots, potatoes');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Instant Pot Beef Stew', 'carrots', 'https://www.budgetbytes.com/instant-pot-beef-stew/', " +
                "'beef stew meat, salt, pepper, flour, butter, beef broth, Worcestershire sauce, soy sauce, brown sugar, garlic, tomato paste, rosemary, thyme, frozen pearl onions, mushrooms, carrots, potatoes');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Instant Pot Beef Stew', 'potatoes', 'https://www.budgetbytes.com/instant-pot-beef-stew/', " +
                "'beef stew meat, salt, pepper, flour, butter, beef broth, Worcestershire sauce, soy sauce, brown sugar, garlic, tomato paste, rosemary, thyme, frozen pearl onions, mushrooms, carrots, potatoes');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Beef and Mushroom Country Casserole', 'cheddar cheese', 'https://www.budgetbytes.com/beef-mushroom-country-casserole/', " +
                "'cooking oil, ground beef, mushrooms, onion, garlic powder, salt, pepper, Worcestershire sauce, extra wide egg noodles, tomato sauce, fire roasted diced tomatoes, sour cream, cheddar cheese, green onions');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Beef and Mushroom Country Casserole', 'sour cream', 'https://www.budgetbytes.com/beef-mushroom-country-casserole/', " +
                "'cooking oil, ground beef, mushrooms, onion, garlic powder, salt, pepper, Worcestershire sauce, extra wide egg noodles, tomato sauce, fire roasted diced tomatoes, sour cream, cheddar cheese, green onions');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Beef and Mushroom Country Casserole', 'yellow onion', 'https://www.budgetbytes.com/beef-mushroom-country-casserole/', " +
                "'cooking oil, ground beef, mushrooms, onion, garlic powder, salt, pepper, Worcestershire sauce, extra wide egg noodles, tomato sauce, fire roasted diced tomatoes, sour cream, cheddar cheese, green onions');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Beef and Mushroom Country Casserole', 'mushrooms', 'https://www.budgetbytes.com/beef-mushroom-country-casserole/', " +
                "'cooking oil, ground beef, mushrooms, onion, garlic powder, salt, pepper, Worcestershire sauce, extra wide egg noodles, tomato sauce, fire roasted diced tomatoes, sour cream, cheddar cheese, green onions');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Beef and Mushroom Country Casserole', 'ground beef', 'https://www.budgetbytes.com/beef-mushroom-country-casserole/', " +
                "'cooking oil, ground beef, mushrooms, onion, garlic powder, salt, pepper, Worcestershire sauce, extra wide egg noodles, tomato sauce, fire roasted diced tomatoes, sour cream, cheddar cheese, green onions');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Skillet Cheeseburger Pasta', 'yellow onion', 'https://www.budgetbytes.com/skillet-cheeseburger-pasta/', " +
                "'yellow onion, olive oil, ground beef, all-purpose flour, tomato sauce, beef broth, pasta shells, sharp cheddar cheese, hot dog relish, green onion');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Skillet Cheeseburger Pasta', 'ground beef', 'https://www.budgetbytes.com/skillet-cheeseburger-pasta/', " +
                "'yellow onion, olive oil, ground beef, all-purpose flour, tomato sauce, beef broth, pasta shells, sharp cheddar cheese, hot dog relish, green onion');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Skillet Cheeseburger Pasta', 'sharp cheddar cheese', 'https://www.budgetbytes.com/skillet-cheeseburger-pasta/', " +
                "'yellow onion, olive oil, ground beef, all-purpose flour, tomato sauce, beef broth, pasta shells, sharp cheddar cheese, hot dog relish, green onion');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Spaghetti with Vegetable and Meat Sauce', 'ground beef', 'https://www.budgetbytes.com/spaghetti-with-vegetable-meat-sauce/', " +
                "'olive oil, ground beef, yellow onion, garlic, zucchini, carrots, basil, oregano, salt, pepper, red pepper flakes, pasta sauce, spaghetti');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Spaghetti with Vegetable and Meat Sauce', 'yellow onion', 'https://www.budgetbytes.com/spaghetti-with-vegetable-meat-sauce/', " +
                "'olive oil, ground beef, yellow onion, garlic, zucchini, carrots, basil, oregano, salt, pepper, red pepper flakes, pasta sauce, spaghetti');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Spaghetti with Vegetable and Meat Sauce', 'zucchini', 'https://www.budgetbytes.com/spaghetti-with-vegetable-meat-sauce/', " +
                "'olive oil, ground beef, yellow onion, garlic, zucchini, carrots, basil, oregano, salt, pepper, red pepper flakes, pasta sauce, spaghetti');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Spaghetti with Vegetable and Meat Sauce', 'carrots', 'https://www.budgetbytes.com/spaghetti-with-vegetable-meat-sauce/', " +
                "'olive oil, ground beef, yellow onion, garlic, zucchini, carrots, basil, oregano, salt, pepper, red pepper flakes, pasta sauce, spaghetti');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Slow Cooker Rosemary Garlic Beef Stew', 'carrots', 'https://www.budgetbytes.com/slow-cooker-rosemary-garlic-beef-stew-slow-cooker/', " +
                "'carrots, celery, onion, red potatoes, olive oil, garlic, beef stew meat, salt, pepper, all-purpose flour, beef broth, dijon mustard, Worcestershire sauce, soy sauce, brown sugar, rosemary, thyme');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Slow Cooker Rosemary Garlic Beef Stew', 'celery', 'https://www.budgetbytes.com/slow-cooker-rosemary-garlic-beef-stew-slow-cooker/', " +
                "'carrots, celery, onion, red potatoes, olive oil, garlic, beef stew meat, salt, pepper, all-purpose flour, beef broth, dijon mustard, Worcestershire sauce, soy sauce, brown sugar, rosemary, thyme');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Slow Cooker Rosemary Garlic Beef Stew', 'yellow onion', 'https://www.budgetbytes.com/slow-cooker-rosemary-garlic-beef-stew-slow-cooker/', " +
                "'carrots, celery, onion, red potatoes, olive oil, garlic, beef stew meat, salt, pepper, all-purpose flour, beef broth, dijon mustard, Worcestershire sauce, soy sauce, brown sugar, rosemary, thyme');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Slow Cooker Rosemary Garlic Beef Stew', 'red potatoes', 'https://www.budgetbytes.com/slow-cooker-rosemary-garlic-beef-stew-slow-cooker/', " +
                "'carrots, celery, onion, red potatoes, olive oil, garlic, beef stew meat, salt, pepper, all-purpose flour, beef broth, dijon mustard, Worcestershire sauce, soy sauce, brown sugar, rosemary, thyme');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Slow Cooker Rosemary Garlic Beef Stew', 'beef', 'https://www.budgetbytes.com/slow-cooker-rosemary-garlic-beef-stew-slow-cooker/', " +
                "'carrots, celery, onion, red potatoes, olive oil, garlic, beef stew meat, salt, pepper, all-purpose flour, beef broth, dijon mustard, Worcestershire sauce, soy sauce, brown sugar, rosemary, thyme');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('One Pot Beef and Mushroom Stroganoff', 'butter', 'https://www.budgetbytes.com/one-pot-beef-mushroom-stroganoff/', " +
                "'butter, garlic, ground beef, button mushrooms, cracked pepper, all-purpose flour, beef broth, wide egg noodles, sour cream, parsley');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('One Pot Beef and Mushroom Stroganoff', 'ground beef', 'https://www.budgetbytes.com/one-pot-beef-mushroom-stroganoff/', " +
                "'butter, garlic, ground beef, button mushrooms, cracked pepper, all-purpose flour, beef broth, wide egg noodles, sour cream, parsley');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('One Pot Beef and Mushroom Stroganoff', 'button mushrooms', 'https://www.budgetbytes.com/one-pot-beef-mushroom-stroganoff/', " +
                "'butter, garlic, ground beef, button mushrooms, cracked pepper, all-purpose flour, beef broth, wide egg noodles, sour cream, parsley');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('One Pot Beef and Mushroom Stroganoff', 'sour cream', 'https://www.budgetbytes.com/one-pot-beef-mushroom-stroganoff/', " +
                "'butter, garlic, ground beef, button mushrooms, cracked pepper, all-purpose flour, beef broth, wide egg noodles, sour cream, parsley');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('(Not) Philly Cheesesteak Sandwiches', 'shredded beef', 'https://www.budgetbytes.com/not-philly-cheesesteak-sandwiches/', " +
                "'Multi-purpose shredded beef, onion, bell pepper, vegetable oil, salt, pepper, large loaf soft French bread, provolone cheese');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('(Not) Philly Cheesesteak Sandwiches', 'yellow onion', 'https://www.budgetbytes.com/not-philly-cheesesteak-sandwiches/', " +
                "'Multi-purpose shredded beef, onion, bell pepper, vegetable oil, salt, pepper, large loaf soft French bread, provolone cheese');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('(Not) Philly Cheesesteak Sandwiches', 'bell pepper', 'https://www.budgetbytes.com/not-philly-cheesesteak-sandwiches/', " +
                "'Multi-purpose shredded beef, onion, bell pepper, vegetable oil, salt, pepper, large loaf soft French bread, provolone cheese');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('(Not) Philly Cheesesteak Sandwiches', 'provolone cheese', 'https://www.budgetbytes.com/not-philly-cheesesteak-sandwiches/', " +
                "'Multi-purpose shredded beef, onion, bell pepper, vegetable oil, salt, pepper, large loaf soft French bread, provolone cheese');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Beef Ragout with Mashed Cauliflower', 'cauliflower', 'https://www.budgetbytes.com/beef-ragout-with-mashed-cauliflower/', " +
                "'cauliflower, butter, chicken bouillon, garlic, salt, pepper, olive oil, onion, tomato paste, red wine or beef broth, diced tomatoes, basil, oregano, bay leaf, soy sauce, brown sugar, shredded beef');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Beef Ragout with Mashed Cauliflower', 'butter', 'https://www.budgetbytes.com/beef-ragout-with-mashed-cauliflower/', " +
                "'cauliflower, butter, chicken bouillon, garlic, salt, pepper, olive oil, onion, tomato paste, red wine or beef broth, diced tomatoes, basil, oregano, bay leaf, soy sauce, brown sugar, shredded beef');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Beef Ragout with Mashed Cauliflower', 'yellow onion', 'https://www.budgetbytes.com/beef-ragout-with-mashed-cauliflower/', " +
                "'cauliflower, butter, chicken bouillon, garlic, salt, pepper, olive oil, onion, tomato paste, red wine or beef broth, diced tomatoes, basil, oregano, bay leaf, soy sauce, brown sugar, shredded beef');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Beef Ragout with Mashed Cauliflower', 'shredded beef', 'https://www.budgetbytes.com/beef-ragout-with-mashed-cauliflower/', " +
                "'cauliflower, butter, chicken bouillon, garlic, salt, pepper, olive oil, onion, tomato paste, red wine or beef broth, diced tomatoes, basil, oregano, bay leaf, soy sauce, brown sugar, shredded beef');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('BBQ Beef Stuffed Potatoes', 'russet potatoes', 'https://www.budgetbytes.com/bbq-beef-stuffed-potatoes/', " +
                "'russet potatoes, vegetable oil, salt, multi-purpose shredded beef, bbq sauce, cheddar cheese, green onion, sour cream');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('BBQ Beef Stuffed Potatoes', 'shredded beef', 'https://www.budgetbytes.com/bbq-beef-stuffed-potatoes/', " +
                "'russet potatoes, vegetable oil, salt, multi-purpose shredded beef, bbq sauce, cheddar cheese, green onion, sour cream');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('BBQ Beef Stuffed Potatoes', 'cheddar cheese', 'https://www.budgetbytes.com/bbq-beef-stuffed-potatoes/', " +
                "'russet potatoes, vegetable oil, salt, multi-purpose shredded beef, bbq sauce, cheddar cheese, green onion, sour cream');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('BBQ Beef Stuffed Potatoes', 'sour cream', 'https://www.budgetbytes.com/bbq-beef-stuffed-potatoes/', " +
                "'russet potatoes, vegetable oil, salt, multi-purpose shredded beef, bbq sauce, cheddar cheese, green onion, sour cream');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Chunky Beef Stew', 'beef', 'https://www.budgetbytes.com/chunky-beef-stew/', " +
                "'boneless stew meat, all-purpose flour, olive oil, onion, garlic, tomato paste, beef broth, bay leaf, thyme, rosemary, pepper, carrots, potatoes, frozen peas, Worcestershire sauce, parsley');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Chunky Beef Stew', 'carrots', 'https://www.budgetbytes.com/chunky-beef-stew/', " +
                "'boneless stew meat, all-purpose flour, olive oil, onion, garlic, tomato paste, beef broth, bay leaf, thyme, rosemary, pepper, carrots, potatoes, frozen peas, Worcestershire sauce, parsley');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Chunky Beef Stew', 'potatoes', 'https://www.budgetbytes.com/chunky-beef-stew/', " +
                "'boneless stew meat, all-purpose flour, olive oil, onion, garlic, tomato paste, beef broth, bay leaf, thyme, rosemary, pepper, carrots, potatoes, frozen peas, Worcestershire sauce, parsley');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Chunky Beef Stew', 'peas', 'https://www.budgetbytes.com/chunky-beef-stew/', " +
                "'boneless stew meat, all-purpose flour, olive oil, onion, garlic, tomato paste, beef broth, bay leaf, thyme, rosemary, pepper, carrots, potatoes, frozen peas, Worcestershire sauce, parsley');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Joe''s Special', 'parmesan cheese', 'https://www.budgetbytes.com/joes-special-my-way/', " +
                "'olive oil, yellow onion, garlic, ground beef, oregano, salt, Worcestershire sauce, spinach, eggs, feta cheese, parmesan cheese');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Joe''s Special', 'feta cheese', 'https://www.budgetbytes.com/joes-special-my-way/', " +
                "'olive oil, yellow onion, garlic, ground beef, oregano, salt, Worcestershire sauce, spinach, eggs, feta cheese, parmesan cheese');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Joe''s Special', 'eggs', 'https://www.budgetbytes.com/joes-special-my-way/', " +
                "'olive oil, yellow onion, garlic, ground beef, oregano, salt, Worcestershire sauce, spinach, eggs, feta cheese, parmesan cheese');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Joe''s Special', 'spinach', 'https://www.budgetbytes.com/joes-special-my-way/', " +
                "'olive oil, yellow onion, garlic, ground beef, oregano, salt, Worcestershire sauce, spinach, eggs, feta cheese, parmesan cheese');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Joe''s Special', 'ground beef', 'https://www.budgetbytes.com/joes-special-my-way/', " +
                "'olive oil, yellow onion, garlic, ground beef, oregano, salt, Worcestershire sauce, spinach, eggs, feta cheese, parmesan cheese');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Joe''s Special', 'yellow onion', 'https://www.budgetbytes.com/joes-special-my-way/', " +
                "'olive oil, yellow onion, garlic, ground beef, oregano, salt, Worcestershire sauce, spinach, eggs, feta cheese, parmesan cheese');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Chili Cheese Beef and Mac', 'ground beef', 'https://www.budgetbytes.com/chili-cheese-beef-n-mac/', " +
                "'olive oil, ground beef, garlic, onion, all-purpose flour, chili powder, smoked paprika, oregano, tomato sauce, beef broth, macaroni, shredded sharp cheddar');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Chili Cheese Beef and Mac', 'sharp cheddar cheese', 'https://www.budgetbytes.com/chili-cheese-beef-n-mac/', " +
                "'olive oil, ground beef, garlic, onion, all-purpose flour, chili powder, smoked paprika, oregano, tomato sauce, beef broth, macaroni, shredded sharp cheddar');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Chili Cheese Beef and Mac', 'onion', 'https://www.budgetbytes.com/chili-cheese-beef-n-mac/', " +
                "'olive oil, ground beef, garlic, onion, all-purpose flour, chili powder, smoked paprika, oregano, tomato sauce, beef broth, macaroni, shredded sharp cheddar');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Moroccan Beef Stew', 'beef', 'https://www.budgetbytes.com/moroccan-beef-stew/', " +
                "'olive oil, beef stew meat, yellow onion, ginger, garlic, allspice, cinnamon, red wine, tomato paste, honey, crushed red pepper, salt, dried apricots, raisins, jasmine rice');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Moroccan Beef Stew', 'yellow onion', 'https://www.budgetbytes.com/moroccan-beef-stew/', " +
                "'olive oil, beef stew meat, yellow onion, ginger, garlic, allspice, cinnamon, red wine, tomato paste, honey, crushed red pepper, salt, dried apricots, raisins, jasmine rice');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Moroccan Beef Stew', 'honey', 'https://www.budgetbytes.com/moroccan-beef-stew/', " +
                "'olive oil, beef stew meat, yellow onion, ginger, garlic, allspice, cinnamon, red wine, tomato paste, honey, crushed red pepper, salt, dried apricots, raisins, jasmine rice');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Beef and Barley Soup', 'red potatoes', 'https://www.budgetbytes.com/beef-barley-soup/', " +
                "'beef stew meat, vegetable oil, onion, garlic, carrots, celery, mushrooms, tomato paste, red wine, beef bouillon, barley, bay leaf, thyme, red potatoes, salt, pepper');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Beef and Barley Soup', 'mushrooms', 'https://www.budgetbytes.com/beef-barley-soup/', " +
                "'beef stew meat, vegetable oil, onion, garlic, carrots, celery, mushrooms, tomato paste, red wine, beef bouillon, barley, bay leaf, thyme, red potatoes, salt, pepper');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Beef and Barley Soup', 'celery', 'https://www.budgetbytes.com/beef-barley-soup/', " +
                "'beef stew meat, vegetable oil, onion, garlic, carrots, celery, mushrooms, tomato paste, red wine, beef bouillon, barley, bay leaf, thyme, red potatoes, salt, pepper');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Beef and Barley Soup', 'carrots', 'https://www.budgetbytes.com/beef-barley-soup/', " +
                "'beef stew meat, vegetable oil, onion, garlic, carrots, celery, mushrooms, tomato paste, red wine, beef bouillon, barley, bay leaf, thyme, red potatoes, salt, pepper');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Beef and Barley Soup', 'yellow onion', 'https://www.budgetbytes.com/beef-barley-soup/', " +
                "'beef stew meat, vegetable oil, onion, garlic, carrots, celery, mushrooms, tomato paste, red wine, beef bouillon, barley, bay leaf, thyme, red potatoes, salt, pepper');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Beef and Barley Soup', 'beef', 'https://www.budgetbytes.com/beef-barley-soup/', " +
                "'beef stew meat, vegetable oil, onion, garlic, carrots, celery, mushrooms, tomato paste, red wine, beef bouillon, barley, bay leaf, thyme, red potatoes, salt, pepper');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Ground Turkey Stir Fry', 'ground turkey', 'https://www.budgetbytes.com/ground-turkey-stir-fry/', " +
                "'soy sauce, toasted sesame oil, brown sugar, red pepper flakes, bell peppers, green onion, spinach, cooking oil, garlic, ground turkey, peanuts, brown rice');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Ground Turkey Stir Fry', 'bell peppers', 'https://www.budgetbytes.com/ground-turkey-stir-fry/', " +
                "'soy sauce, toasted sesame oil, brown sugar, red pepper flakes, bell peppers, green onion, spinach, cooking oil, garlic, ground turkey, peanuts, brown rice');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Ground Turkey Stir Fry', 'spinach', 'https://www.budgetbytes.com/ground-turkey-stir-fry/', " +
                "'soy sauce, toasted sesame oil, brown sugar, red pepper flakes, bell peppers, green onion, spinach, cooking oil, garlic, ground turkey, peanuts, brown rice');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Cidar Roasted Turkey Breast', 'turkey', 'https://www.budgetbytes.com/cider-roasted-turkey-breast/', " +
                "'split turkey breast, butter, dried sage, dried rosemary, dried thyme, salt, large apple, yellow onion, apple cidar');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Cidar Roasted Turkey Breast', 'butter', 'https://www.budgetbytes.com/cider-roasted-turkey-breast/', " +
                "'split turkey breast, butter, dried sage, dried rosemary, dried thyme, salt, large apple, yellow onion, apple cidar');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Cidar Roasted Turkey Breast', 'yellow onion', 'https://www.budgetbytes.com/cider-roasted-turkey-breast/', " +
                "'split turkey breast, butter, dried sage, dried rosemary, dried thyme, salt, large apple, yellow onion, apple cidar');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Cidar Roasted Turkey Breast', 'apple', 'https://www.budgetbytes.com/cider-roasted-turkey-breast/', " +
                "'split turkey breast, butter, dried sage, dried rosemary, dried thyme, salt, large apple, yellow onion, apple cidar');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Turkey And Stuffing Meatballs', 'turkey', 'https://www.budgetbytes.com/turkey-and-stuffing-meatballs/', " +
                "'box stuffing mix, hot water, ground turkey, egg, butter');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Turkey And Stuffing Meatballs', 'eggs', 'https://www.budgetbytes.com/turkey-and-stuffing-meatballs/', " +
                "'box stuffing mix, hot water, ground turkey, egg, butter');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Turkey And Stuffing Meatballs', 'butter', 'https://www.budgetbytes.com/turkey-and-stuffing-meatballs/', " +
                "'box stuffing mix, hot water, ground turkey, egg, butter');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Spinach and Feta Turkey Meatballs', 'ground turkey', 'https://www.budgetbytes.com/spinach-and-feta-turkey-meatballs/', " +
                "'spinach, olive oil, ground turkey, bread crumbs, garlic powder, pepper, salt, egg, feta cheese');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Spinach and Feta Turkey Meatballs', 'spinach', 'https://www.budgetbytes.com/spinach-and-feta-turkey-meatballs/', " +
                "'spinach, olive oil, ground turkey, bread crumbs, garlic powder, pepper, salt, egg, feta cheese');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Spinach and Feta Turkey Meatballs', 'eggs', 'https://www.budgetbytes.com/spinach-and-feta-turkey-meatballs/', " +
                "'spinach, olive oil, ground turkey, bread crumbs, garlic powder, pepper, salt, egg, feta cheese');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Spinach and Feta Turkey Meatballs', 'feta cheese', 'https://www.budgetbytes.com/spinach-and-feta-turkey-meatballs/', " +
                "'spinach, olive oil, ground turkey, bread crumbs, garlic powder, pepper, salt, egg, feta cheese');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Turkey Chili Smothered Sweet Potatoes', 'ground turkey', 'https://www.budgetbytes.com/turkey-chili-smothered-sweet-potatoes/', " +
                "'olive oil, ground turkey, yellow onion, garlic, tomato sauce, tomato paste, kidney or black beans, chili powder, oregano, cumin, water, salt, sweet potatoes, sharp cheddar cheese, cilantro, green onion');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Turkey Chili Smothered Sweet Potatoes', 'onion', 'https://www.budgetbytes.com/turkey-chili-smothered-sweet-potatoes/', " +
                "'olive oil, ground turkey, yellow onion, garlic, tomato sauce, tomato paste, kidney or black beans, chili powder, oregano, cumin, water, salt, sweet potatoes, sharp cheddar cheese, cilantro, green onion');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Turkey Chili Smothered Sweet Potatoes', 'sweet potatoes', 'https://www.budgetbytes.com/turkey-chili-smothered-sweet-potatoes/', " +
                "'olive oil, ground turkey, yellow onion, garlic, tomato sauce, tomato paste, kidney or black beans, chili powder, oregano, cumin, water, salt, sweet potatoes, sharp cheddar cheese, cilantro, green onion');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Turkey Chili Smothered Sweet Potatoes', 'cheddar cheese', 'https://www.budgetbytes.com/turkey-chili-smothered-sweet-potatoes/', " +
                "'olive oil, ground turkey, yellow onion, garlic, tomato sauce, tomato paste, kidney or black beans, chili powder, oregano, cumin, water, salt, sweet potatoes, sharp cheddar cheese, cilantro, green onion');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Mini Black Bean Turkey Burgers', 'ground turkey', 'https://www.budgetbytes.com/mini-black-bean-turkey-burgers/', " +
                "'black beans, green onions, chili powder, cumin, garlic powder, cayenne pepper, salt, ground turkey, avocado, lime, sour cream, sugar, pita halves, roma tomatoes');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Mini Black Bean Turkey Burgers', 'black beans', 'https://www.budgetbytes.com/mini-black-bean-turkey-burgers/', " +
                "'black beans, green onions, chili powder, cumin, garlic powder, cayenne pepper, salt, ground turkey, avocado, lime, sour cream, sugar, pita halves, roma tomatoes');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Mini Black Bean Turkey Burgers', 'sour cream', 'https://www.budgetbytes.com/mini-black-bean-turkey-burgers/', " +
                "'black beans, green onions, chili powder, cumin, garlic powder, cayenne pepper, salt, ground turkey, avocado, lime, sour cream, sugar, pita halves, roma tomatoes');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Mini Black Bean Turkey Burgers', 'avocado', 'https://www.budgetbytes.com/mini-black-bean-turkey-burgers/', " +
                "'black beans, green onions, chili powder, cumin, garlic powder, cayenne pepper, salt, ground turkey, avocado, lime, sour cream, sugar, pita halves, roma tomatoes');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Mini Black Bean Turkey Burgers', 'lime', 'https://www.budgetbytes.com/mini-black-bean-turkey-burgers/', " +
                "'black beans, green onions, chili powder, cumin, garlic powder, cayenne pepper, salt, ground turkey, avocado, lime, sour cream, sugar, pita halves, roma tomatoes');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Mini Black Bean Turkey Burgers', 'roma tomato', 'https://www.budgetbytes.com/mini-black-bean-turkey-burgers/', " +
                "'black beans, green onions, chili powder, cumin, garlic powder, cayenne pepper, salt, ground turkey, avocado, lime, sour cream, sugar, pita halves, roma tomatoes');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Mini Garden Turkey Loaves', 'ground turkey', 'https://www.budgetbytes.com/mini-garden-turkey-loaves/', " +
                "'olive oil, small onion, garlic, carrots, zucchini, button mushrooms, salt, pepper, worcestershire sauce, ketchup, egg, breadcrumbs, ground turkey, brown sugar, apple cider vinegar');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Mini Garden Turkey Loaves', 'carrot', 'https://www.budgetbytes.com/mini-garden-turkey-loaves/', " +
                "'olive oil, small onion, garlic, carrots, zucchini, button mushrooms, salt, pepper, worcestershire sauce, ketchup, egg, breadcrumbs, ground turkey, brown sugar, apple cider vinegar');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Mini Garden Turkey Loaves', 'zucchini', 'https://www.budgetbytes.com/mini-garden-turkey-loaves/', " +
                "'olive oil, small onion, garlic, carrots, zucchini, button mushrooms, salt, pepper, worcestershire sauce, ketchup, egg, breadcrumbs, ground turkey, brown sugar, apple cider vinegar');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Mini Garden Turkey Loaves', 'button mushroom', 'https://www.budgetbytes.com/mini-garden-turkey-loaves/', " +
                "'olive oil, small onion, garlic, carrots, zucchini, button mushrooms, salt, pepper, worcestershire sauce, ketchup, egg, breadcrumbs, ground turkey, brown sugar, apple cider vinegar');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Mini Garden Turkey Loaves', 'eggs', 'https://www.budgetbytes.com/mini-garden-turkey-loaves/', " +
                "'olive oil, small onion, garlic, carrots, zucchini, button mushrooms, salt, pepper, worcestershire sauce, ketchup, egg, breadcrumbs, ground turkey, brown sugar, apple cider vinegar');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Mini Garden Turkey Loaves', 'yellow onion', 'https://www.budgetbytes.com/mini-garden-turkey-loaves/', " +
                "'olive oil, small onion, garlic, carrots, zucchini, button mushrooms, salt, pepper, worcestershire sauce, ketchup, egg, breadcrumbs, ground turkey, brown sugar, apple cider vinegar');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Weeknight Black Bean Chili', 'ground turkey', 'https://www.budgetbytes.com/weeknight-black-bean-chili/', " +
                "'olive oil, yellow onion, garlic, ground turkey, black beans, diced tomatoes with green chiles, tomato paste, chili powder, cumin, smoked paprika, oregano, salt');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Weeknight Black Bean Chili', 'yellow onion', 'https://www.budgetbytes.com/weeknight-black-bean-chili/', " +
                "'olive oil, yellow onion, garlic, ground turkey, black beans, diced tomatoes with green chiles, tomato paste, chili powder, cumin, smoked paprika, oregano, salt');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Weeknight Black Bean Chili', 'black beans', 'https://www.budgetbytes.com/weeknight-black-bean-chili/', " +
                "'olive oil, yellow onion, garlic, ground turkey, black beans, diced tomatoes with green chiles, tomato paste, chili powder, cumin, smoked paprika, oregano, salt');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Leftover Stuffin Muffins', 'turkey', 'https://www.budgetbytes.com/leftover-stuffin-muffins/', " +
                "'precooked stuffing or dressing, turkey or ham, frozen spinach, eggs, milk or cream, salt, pepper');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Leftover Stuffin Muffins', 'ham', 'https://www.budgetbytes.com/leftover-stuffin-muffins/', " +
                "'precooked stuffing or dressing, turkey or ham, frozen spinach, eggs, milk or cream, salt, pepper');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Leftover Stuffin Muffins', 'spinach', 'https://www.budgetbytes.com/leftover-stuffin-muffins/', " +
                "'precooked stuffing or dressing, turkey or ham, frozen spinach, eggs, milk or cream, salt, pepper');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Leftover Stuffin Muffins', 'eggs', 'https://www.budgetbytes.com/leftover-stuffin-muffins/', " +
                "'precooked stuffing or dressing, turkey or ham, frozen spinach, eggs, milk or cream, salt, pepper');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Leftover Stuffin Muffins', 'milk', 'https://www.budgetbytes.com/leftover-stuffin-muffins/', " +
                "'precooked stuffing or dressing, turkey or ham, frozen spinach, eggs, milk or cream, salt, pepper');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Green Chile Turkey Burgers', 'avocado', 'https://www.budgetbytes.com/green-chile-turkey-burgers/', " +
                "'ground turkey, diced green chiles, ground cumin, garlic powder, salt, green onions, pepper jack cheese, hamburger buns, tomato, avocado');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Green Chile Turkey Burgers', 'ground turkey', 'https://www.budgetbytes.com/green-chile-turkey-burgers/', " +
                "'ground turkey, diced green chiles, ground cumin, garlic powder, salt, green onions, pepper jack cheese, hamburger buns, tomato, avocado');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Green Chile Turkey Burgers', 'pepper jack cheese', 'https://www.budgetbytes.com/green-chile-turkey-burgers/', " +
                "'ground turkey, diced green chiles, ground cumin, garlic powder, salt, green onions, pepper jack cheese, hamburger buns, tomato, avocado');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Green Chile Turkey Burgers', 'tomatoes', 'https://www.budgetbytes.com/green-chile-turkey-burgers/', " +
                "'ground turkey, diced green chiles, ground cumin, garlic powder, salt, green onions, pepper jack cheese, hamburger buns, tomato, avocado');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Oven Roasted Autumn Medley', 'turkey sausage', 'https://www.budgetbytes.com/oven-roasted-autumn-medley/', " +
                "'red onion, sweet potato, apples, olive oil, basil, sage, rosemary, salt, pepper, turkey sausage, parsley');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Oven Roasted Autumn Medley', 'red onion', 'https://www.budgetbytes.com/oven-roasted-autumn-medley/', " +
                "'red onion, sweet potato, apples, olive oil, basil, sage, rosemary, salt, pepper, turkey sausage, parsley');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Oven Roasted Autumn Medley', 'sweet potato', 'https://www.budgetbytes.com/oven-roasted-autumn-medley/', " +
                "'red onion, sweet potato, apples, olive oil, basil, sage, rosemary, salt, pepper, turkey sausage, parsley');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Oven Roasted Autumn Medley', 'apple', 'https://www.budgetbytes.com/oven-roasted-autumn-medley/', " +
                "'red onion, sweet potato, apples, olive oil, basil, sage, rosemary, salt, pepper, turkey sausage, parsley');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Turkey And Stuffing Casserole', 'bacon', 'https://www.budgetbytes.com/turkey-stuffing-casserole/', " +
                "'bacon, celery, apples, yellow onion, garlic, sage, thyme, salt, pepper, loaf french bread, chicken base, parsley, olive oil, turkey pieces');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Turkey And Stuffing Casserole', 'celery', 'https://www.budgetbytes.com/turkey-stuffing-casserole/', " +
                "'bacon, celery, apples, yellow onion, garlic, sage, thyme, salt, pepper, loaf french bread, chicken base, parsley, olive oil, turkey pieces');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Turkey And Stuffing Casserole', 'apples', 'https://www.budgetbytes.com/turkey-stuffing-casserole/', " +
                "'bacon, celery, apples, yellow onion, garlic, sage, thyme, salt, pepper, loaf french bread, chicken base, parsley, olive oil, turkey pieces');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Turkey And Stuffing Casserole', 'yellow onion', 'https://www.budgetbytes.com/turkey-stuffing-casserole/', " +
                "'bacon, celery, apples, yellow onion, garlic, sage, thyme, salt, pepper, loaf french bread, chicken base, parsley, olive oil, turkey pieces');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Turkey And Stuffing Casserole', 'turkey', 'https://www.budgetbytes.com/turkey-stuffing-casserole/', " +
                "'bacon, celery, apples, yellow onion, garlic, sage, thyme, salt, pepper, loaf french bread, chicken base, parsley, olive oil, turkey pieces');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Hearty Meatball Soup', 'carrots', 'https://www.budgetbytes.com/hearty-meatball-soup/', " +
                "'olive oil, medium onion, carrots, celery, garlic, diced tomatoes, beef bouillon, water, dry pasta, turkey meatballs, parsley, parmesan cheese');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Hearty Meatball Soup', 'yellow onion', 'https://www.budgetbytes.com/hearty-meatball-soup/', " +
                "'olive oil, medium onion, carrots, celery, garlic, diced tomatoes, beef bouillon, water, dry pasta, turkey meatballs, parsley, parmesan cheese');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Hearty Meatball Soup', 'celery', 'https://www.budgetbytes.com/hearty-meatball-soup/', " +
                "'olive oil, medium onion, carrots, celery, garlic, diced tomatoes, beef bouillon, water, dry pasta, turkey meatballs, parsley, parmesan cheese');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Hearty Meatball Soup', 'turkey', 'https://www.budgetbytes.com/hearty-meatball-soup/', " +
                "'olive oil, medium onion, carrots, celery, garlic, diced tomatoes, beef bouillon, water, dry pasta, turkey meatballs, parsley, parmesan cheese');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Hearty Meatball Soup', 'parmesan cheese', 'https://www.budgetbytes.com/hearty-meatball-soup/', " +
                "'olive oil, medium onion, carrots, celery, garlic, diced tomatoes, beef bouillon, water, dry pasta, turkey meatballs, parsley, parmesan cheese');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Pineapple Pork Hawaiian Burgers', 'ground pork', 'https://www.budgetbytes.com/pineapple-pork-hawaiian-burgers/', " +
                "'pineapple slices, green onion, monterey jack cheese, ground pork, teriyaki sauce, red onion, cooking oil, onion rolls, mayonnaise, sriracha sauce');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Pineapple Pork Hawaiian Burgers', 'monterrey jack cheese', 'https://www.budgetbytes.com/pineapple-pork-hawaiian-burgers/', " +
                "'pineapple slices, green onion, monterey jack cheese, ground pork, teriyaki sauce, red onion, cooking oil, onion rolls, mayonnaise, sriracha sauce');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Pineapple Pork Hawaiian Burgers', 'pineapple', 'https://www.budgetbytes.com/pineapple-pork-hawaiian-burgers/', " +
                "'pineapple slices, green onion, monterey jack cheese, ground pork, teriyaki sauce, red onion, cooking oil, onion rolls, mayonnaise, sriracha sauce');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Pineapple Pork Hawaiian Burgers', 'red onion', 'https://www.budgetbytes.com/pineapple-pork-hawaiian-burgers/', " +
                "'pineapple slices, green onion, monterey jack cheese, ground pork, teriyaki sauce, red onion, cooking oil, onion rolls, mayonnaise, sriracha sauce');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Pineapple Pork Hawaiian Burgers', 'mayonnaise', 'https://www.budgetbytes.com/pineapple-pork-hawaiian-burgers/', " +
                "'pineapple slices, green onion, monterey jack cheese, ground pork, teriyaki sauce, red onion, cooking oil, onion rolls, mayonnaise, sriracha sauce');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Roasted Bratwurst With Peppers And Onions', 'bratwurst', 'https://www.budgetbytes.com/roasted-bratwurst-peppers-onions/', " +
                "'olive oil, red wine vinegar, mustard, garlic powder, salt, pepper, bell peppers, yellow onion, bratwurst links, buns, cheddar cheese');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Roasted Bratwurst With Peppers And Onions', 'cheddar cheese', 'https://www.budgetbytes.com/roasted-bratwurst-peppers-onions/', " +
                "'olive oil, red wine vinegar, mustard, garlic powder, salt, pepper, bell peppers, yellow onion, bratwurst links, buns, cheddar cheese');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Roasted Bratwurst With Peppers And Onions', 'bell pepper', 'https://www.budgetbytes.com/roasted-bratwurst-peppers-onions/', " +
                "'olive oil, red wine vinegar, mustard, garlic powder, salt, pepper, bell peppers, yellow onion, bratwurst links, buns, cheddar cheese');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Roasted Bratwurst With Peppers And Onions', 'yellow onion', 'https://www.budgetbytes.com/roasted-bratwurst-peppers-onions/', " +
                "'olive oil, red wine vinegar, mustard, garlic powder, salt, pepper, bell peppers, yellow onion, bratwurst links, buns, cheddar cheese');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Sausage And Tortelloni Soup', 'italian sausage', 'https://www.budgetbytes.com/sausage-tortellini-soup/', " +
                "'olive oil, italian sausage, yellow onion, garlic, carrots, stewed tomatoes, basil, oregano, pepper, vegetable broth, water, cheese tortelloni, baby spinach');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Sausage And Tortelloni Soup', 'yellow onion', 'https://www.budgetbytes.com/sausage-tortellini-soup/', " +
                "'olive oil, italian sausage, yellow onion, garlic, carrots, stewed tomatoes, basil, oregano, pepper, vegetable broth, water, cheese tortelloni, baby spinach');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Sausage And Tortelloni Soup', 'carrots', 'https://www.budgetbytes.com/sausage-tortellini-soup/', " +
                "'olive oil, italian sausage, yellow onion, garlic, carrots, stewed tomatoes, basil, oregano, pepper, vegetable broth, water, cheese tortelloni, baby spinach');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Sausage And Tortelloni Soup', 'spinach', 'https://www.budgetbytes.com/sausage-tortellini-soup/', " +
                "'olive oil, italian sausage, yellow onion, garlic, carrots, stewed tomatoes, basil, oregano, pepper, vegetable broth, water, cheese tortelloni, baby spinach');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Kielbasa And Cabbage Skillet', 'kielbasa', 'https://www.budgetbytes.com/kielbasa-cabbage-skillet/', " +
                "'olive oil, red wine vinegar, whole grain mustard, garlic powder, salt, pepper, kielbasa, yellow onion, cabbage');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Kielbasa And Cabbage Skillet', 'yellow onion', 'https://www.budgetbytes.com/kielbasa-cabbage-skillet/', " +
                "'olive oil, red wine vinegar, whole grain mustard, garlic powder, salt, pepper, kielbasa, yellow onion, cabbage');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Kielbasa And Cabbage Skillet', 'cabbage', 'https://www.budgetbytes.com/kielbasa-cabbage-skillet/', " +
                "'olive oil, red wine vinegar, whole grain mustard, garlic powder, salt, pepper, kielbasa, yellow onion, cabbage');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('One Pot Bacon Broccoli Mac and Cheese', 'broccoli', 'https://www.budgetbytes.com/one-pot-bacon-broccoli-mac-cheese/', " +
                "'frozen broccoli florets, bacon, medium cheddar cheese, pasta, evaporated milk, smoked paprika, hot sauce, dijon mustard, salt');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('One Pot Bacon Broccoli Mac and Cheese', 'cheddar cheese', 'https://www.budgetbytes.com/one-pot-bacon-broccoli-mac-cheese/', " +
                "'frozen broccoli florets, bacon, medium cheddar cheese, pasta, evaporated milk, smoked paprika, hot sauce, dijon mustard, salt');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('One Pot Bacon Broccoli Mac and Cheese', 'bacon', 'https://www.budgetbytes.com/one-pot-bacon-broccoli-mac-cheese/', " +
                "'frozen broccoli florets, bacon, medium cheddar cheese, pasta, evaporated milk, smoked paprika, hot sauce, dijon mustard, salt');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Pressure Cooker Red Beans', 'sausage', 'https://www.budgetbytes.com/pressure-cooker-red-beans/', " +
                "'smoked sausage, olive oil, yellow onion, bell pepper, celery, garlic, uncooked red beans, thyme, oregano, smoked paprika, cayenne, black pepper, chicken broth, water, green onion, rice');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Pressure Cooker Red Beans', 'celery', 'https://www.budgetbytes.com/pressure-cooker-red-beans/', " +
                "'smoked sausage, olive oil, yellow onion, bell pepper, celery, garlic, uncooked red beans, thyme, oregano, smoked paprika, cayenne, black pepper, chicken broth, water, green onion, rice');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Pressure Cooker Red Beans', 'bell pepper', 'https://www.budgetbytes.com/pressure-cooker-red-beans/', " +
                "'smoked sausage, olive oil, yellow onion, bell pepper, celery, garlic, uncooked red beans, thyme, oregano, smoked paprika, cayenne, black pepper, chicken broth, water, green onion, rice');"
        );//olive oil, red wine vinegar, whole grain mustard, garlic, salt, pepper, kielbasa, baby red potatoes, cabbage, parsley

        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Pressure Cooker Red Beans', 'yellow onion', 'https://www.budgetbytes.com/pressure-cooker-red-beans/', " +
                "'smoked sausage, olive oil, yellow onion, bell pepper, celery, garlic, uncooked red beans, thyme, oregano, smoked paprika, cayenne, black pepper, chicken broth, water, green onion, rice');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Pressure Cooker Red Beans', 'red beans', 'https://www.budgetbytes.com/pressure-cooker-red-beans/', " +
                "'smoked sausage, olive oil, yellow onion, bell pepper, celery, garlic, uncooked red beans, thyme, oregano, smoked paprika, cayenne, black pepper, chicken broth, water, green onion, rice');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('One Pot Kielbasa and Cabbage Dinner', 'kielbasa', 'https://www.budgetbytes.com/one-pan-roasted-kielbasa-cabbage-dinner/', " +
                "'olive oil, red wine vinegar, whole grain mustard, garlic, salt, pepper, kielbasa, baby red potatoes, cabbage, parsley');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('One Pot Kielbasa and Cabbage Dinner', 'red potatoes', 'https://www.budgetbytes.com/one-pan-roasted-kielbasa-cabbage-dinner/', " +
                "'olive oil, red wine vinegar, whole grain mustard, garlic, salt, pepper, kielbasa, baby red potatoes, cabbage, parsley');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('One Pot Kielbasa and Cabbage Dinner', 'cabbage', 'https://www.budgetbytes.com/one-pan-roasted-kielbasa-cabbage-dinner/', " +
                "'olive oil, red wine vinegar, whole grain mustard, garlic, salt, pepper, kielbasa, baby red potatoes, cabbage, parsley');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('One Pot Sausage and Sun Dried Tomato Pasta', 'broccoli', 'https://www.budgetbytes.com/one-pot-sausage-sun-dried-tomato-pasta/', " +
                "'sweet italian sausage, olive oil, garlic, frozen broccoli florets, chicken broth, sun dried tomatoes, pasta, red pepper flakes, parmesan cheese');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('One Pot Sausage and Sun Dried Tomato Pasta', 'italian sausage', 'https://www.budgetbytes.com/one-pot-sausage-sun-dried-tomato-pasta/', " +
                "'sweet italian sausage, olive oil, garlic, frozen broccoli florets, chicken broth, sun dried tomatoes, pasta, red pepper flakes, parmesan cheese');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('One Pot Sausage and Sun Dried Tomato Pasta', 'parmesan cheese', 'https://www.budgetbytes.com/one-pot-sausage-sun-dried-tomato-pasta/', " +
                "'sweet italian sausage, olive oil, garlic, frozen broccoli florets, chicken broth, sun dried tomatoes, pasta, red pepper flakes, parmesan cheese');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Brown Sugar Bacon Breakfast Sandwiches With Chipotle Mayo', 'bacon', 'https://www.budgetbytes.com/brown-sugar-bacon-breakfast-sandwiches-chipotle-mayo/', " +
                "'bacon, brown sugar, mayonnaise, chipotle chili powder, garlic powder, brioche buns, eggs, tomatoes, lettuce');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Brown Sugar Bacon Breakfast Sandwiches With Chipotle Mayo', 'mayonnaise', 'https://www.budgetbytes.com/brown-sugar-bacon-breakfast-sandwiches-chipotle-mayo/', " +
                "'bacon, brown sugar, mayonnaise, chipotle chili powder, garlic powder, brioche buns, eggs, tomatoes, lettuce');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Brown Sugar Bacon Breakfast Sandwiches With Chipotle Mayo', 'eggs', 'https://www.budgetbytes.com/brown-sugar-bacon-breakfast-sandwiches-chipotle-mayo/', " +
                "'bacon, brown sugar, mayonnaise, chipotle chili powder, garlic powder, brioche buns, eggs, tomatoes, lettuce');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Brown Sugar Bacon Breakfast Sandwiches With Chipotle Mayo', 'tomato', 'https://www.budgetbytes.com/brown-sugar-bacon-breakfast-sandwiches-chipotle-mayo/', " +
                "'bacon, brown sugar, mayonnaise, chipotle chili powder, garlic powder, brioche buns, eggs, tomatoes, lettuce');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Brown Sugar Bacon Breakfast Sandwiches With Chipotle Mayo', 'lettuce', 'https://www.budgetbytes.com/brown-sugar-bacon-breakfast-sandwiches-chipotle-mayo/', " +
                "'bacon, brown sugar, mayonnaise, chipotle chili powder, garlic powder, brioche buns, eggs, tomatoes, lettuce');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('German Potato Salad', 'red potato', 'https://www.budgetbytes.com/german-potato-salad/', " +
                "'small red potatoes, apple cider vinegar, whole grain mustard, white sugar, pepper, salt, bacon, yellow onion, parsley');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('German Potato Salad', 'bacon', 'https://www.budgetbytes.com/german-potato-salad/', " +
                "'small red potatoes, apple cider vinegar, whole grain mustard, white sugar, pepper, salt, bacon, yellow onion, parsley');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('German Potato Salad', 'yellow onion', 'https://www.budgetbytes.com/german-potato-salad/', " +
                "'small red potatoes, apple cider vinegar, whole grain mustard, white sugar, pepper, salt, bacon, yellow onion, parsley');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Bacon and Carmelized Pineapple Pizza', 'bacon', 'https://www.budgetbytes.com/bacon-caramelized-pineapple-pizza/', " +
                "'bacon, pineapple chunks, brown sugar, pizza dough, pizza sauce, monterrey jack cheese, green onion');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Bacon and Carmelized Pineapple Pizza', 'pineapple', 'https://www.budgetbytes.com/bacon-caramelized-pineapple-pizza/', " +
                "'bacon, pineapple chunks, brown sugar, pizza dough, pizza sauce, monterrey jack cheese, green onion');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Bacon and Carmelized Pineapple Pizza', 'monterrey jack cheese', 'https://www.budgetbytes.com/bacon-caramelized-pineapple-pizza/', " +
                "'bacon, pineapple chunks, brown sugar, pizza dough, pizza sauce, monterey jack cheese, green onion');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Blackberry Sage Pork Chops', 'pork chops', 'https://www.budgetbytes.com/blackberry-sage-pork-chops/', " +
                "'pork chops, salt, pepper, olive oil, blackberry jam, butter, balsamic vinegar, water, sage');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Blackberry Sage Pork Chops', 'butter', 'https://www.budgetbytes.com/blackberry-sage-pork-chops/', " +
                "'pork chops, salt, pepper, olive oil, blackberry jam, butter, balsamic vinegar, water, sage');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Pastalaya', 'sausage', 'https://www.budgetbytes.com/pastalaya/', " +
                "'vegetable oil, smoked sausage, garlic, yellow onion, bell pepper, celery, diced tomatoes, creole seasoning, oregano, paprika, thyme, pepper, chicken broth, water, penne pasta, half and half, parsley, green onion');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Pastalaya', 'yellow onion', 'https://www.budgetbytes.com/pastalaya/', " +
                "'vegetable oil, smoked sausage, garlic, yellow onion, bell pepper, celery, diced tomatoes, creole seasoning, oregano, paprika, thyme, pepper, chicken broth, water, penne pasta, half and half, parsley, green onion');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Pastalaya', 'bell pepper', 'https://www.budgetbytes.com/pastalaya/', " +
                "'vegetable oil, smoked sausage, garlic, yellow onion, bell pepper, celery, diced tomatoes, creole seasoning, oregano, paprika, thyme, pepper, chicken broth, water, penne pasta, half and half, parsley, green onion');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Pastalaya', 'celery', 'https://www.budgetbytes.com/pastalaya/', " +
                "'vegetable oil, smoked sausage, garlic, yellow onion, bell pepper, celery, diced tomatoes, creole seasoning, oregano, paprika, thyme, pepper, chicken broth, water, penne pasta, half and half, parsley, green onion');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Pastalaya', 'half and half', 'https://www.budgetbytes.com/pastalaya/', " +
                "'vegetable oil, smoked sausage, garlic, yellow onion, bell pepper, celery, diced tomatoes, creole seasoning, oregano, paprika, thyme, pepper, chicken broth, water, penne pasta, half and half, parsley, green onion');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Pasta Salad With Sausage and Arugula', 'italian sausage', 'https://www.budgetbytes.com/pasta-salad-with-sausage-and-arugula/', " +
                "'canola oil, olive oil, red wine vinegar, garlic, dijon mustard, oregano, salt, pepper, pasta, italian sausage, mozzarella cheese, red bell pepper, orange or yellow bell pepper, arugula');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Pasta Salad With Sausage and Arugula', 'mozzarella cheese', 'https://www.budgetbytes.com/pasta-salad-with-sausage-and-arugula/', " +
                "'canola oil, olive oil, red wine vinegar, garlic, dijon mustard, oregano, salt, pepper, pasta, italian sausage, mozzarella cheese, red bell pepper, orange or yellow bell pepper, arugula');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Pasta Salad With Sausage and Arugula', 'bell pepper', 'https://www.budgetbytes.com/pasta-salad-with-sausage-and-arugula/', " +
                "'canola oil, olive oil, red wine vinegar, garlic, dijon mustard, oregano, salt, pepper, pasta, italian sausage, mozzarella cheese, red bell pepper, orange or yellow bell pepper, arugula');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Pasta Salad With Sausage and Arugula', 'orange', 'https://www.budgetbytes.com/pasta-salad-with-sausage-and-arugula/', " +
                "'canola oil, olive oil, red wine vinegar, garlic, dijon mustard, oregano, salt, pepper, pasta, italian sausage, mozzarella cheese, red bell pepper, orange or yellow bell pepper, arugula');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Pasta Salad With Sausage and Arugula', 'arugula', 'https://www.budgetbytes.com/pasta-salad-with-sausage-and-arugula/', " +
                "'canola oil, olive oil, red wine vinegar, garlic, dijon mustard, oregano, salt, pepper, pasta, italian sausage, mozzarella cheese, red bell pepper, orange or yellow bell pepper, arugula');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('White Beans With Tomato and Sausage', 'italian sausage', 'https://www.budgetbytes.com/white-beans-tomato-sausage/', " +
                "'olive oil, italian sausage, yellow onion, garlic, crushed tomatoes, basil, oregano, red pepper flakes, pepper, great northern beans, spinach');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('White Beans With Tomato and Sausage', 'yellow onion', 'https://www.budgetbytes.com/white-beans-tomato-sausage/', " +
                "'olive oil, italian sausage, yellow onion, garlic, crushed tomatoes, basil, oregano, red pepper flakes, pepper, great northern beans, spinach');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('White Beans With Tomato and Sausage', 'great northern beans', 'https://www.budgetbytes.com/white-beans-tomato-sausage/', " +
                "'olive oil, italian sausage, yellow onion, garlic, crushed tomatoes, basil, oregano, red pepper flakes, pepper, great northern beans, spinach');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('White Beans With Tomato and Sausage', 'spinach', 'https://www.budgetbytes.com/white-beans-tomato-sausage/', " +
                "'olive oil, italian sausage, yellow onion, garlic, crushed tomatoes, basil, oregano, red pepper flakes, pepper, great northern beans, spinach');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Honey Mustard Pork Chops', 'pork chops', 'https://www.budgetbytes.com/honey-mustard-pork-chops/', " +
                "'mayonnaise, dijon mustard, honey, garlic powder, smoked paprika, salt, pepper, vegetable oil, pork chops');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Honey Mustard Pork Chops', 'mayonnaise', 'https://www.budgetbytes.com/honey-mustard-pork-chops/', " +
                "'mayonnaise, dijon mustard, honey, garlic powder, smoked paprika, salt, pepper, vegetable oil, pork chops');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Bacon and Spinach Pasta With Parmesan', 'bacon', 'https://www.budgetbytes.com/bacon-spinach-pasta-parmesan/', " +
                "'bacon, small onion, chicken broth, pasta, fresh spinach, parmesan cheese');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Bacon and Spinach Pasta With Parmesan', 'spinach', 'https://www.budgetbytes.com/bacon-spinach-pasta-parmesan/', " +
                "'bacon, small onion, chicken broth, pasta, fresh spinach, parmesan cheese');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Bacon and Spinach Pasta With Parmesan', 'yellow onion', 'https://www.budgetbytes.com/bacon-spinach-pasta-parmesan/', " +
                "'bacon, small onion, chicken broth, pasta, fresh spinach, parmesan cheese');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Bacon and Spinach Pasta With Parmesan', 'parmesan cheese', 'https://www.budgetbytes.com/bacon-spinach-pasta-parmesan/', " +
                "'bacon, small onion, chicken broth, pasta, fresh spinach, parmesan cheese');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Penne Pasta With Sausage and Greens', 'italian sausage', 'https://www.budgetbytes.com/snap-challenge-penne-pasta-sausage-greens/', " +
                "'penne pasta, olive oil, italian sausage, garlic, yellow onion, crushed tomatoes, tomato paste, basil, salt, pepper, brown sugar, broccoli, spinach, feta');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Penne Pasta With Sausage and Greens', 'yellow onion', 'https://www.budgetbytes.com/snap-challenge-penne-pasta-sausage-greens/', " +
                "'penne pasta, olive oil, italian sausage, garlic, yellow onion, crushed tomatoes, tomato paste, basil, salt, pepper, brown sugar, broccoli, spinach, feta');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Penne Pasta With Sausage and Greens', 'broccoli', 'https://www.budgetbytes.com/snap-challenge-penne-pasta-sausage-greens/', " +
                "'penne pasta, olive oil, italian sausage, garlic, yellow onion, crushed tomatoes, tomato paste, basil, salt, pepper, brown sugar, broccoli, spinach, feta');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Penne Pasta With Sausage and Greens', 'spinach', 'https://www.budgetbytes.com/snap-challenge-penne-pasta-sausage-greens/', " +
                "'penne pasta, olive oil, italian sausage, garlic, yellow onion, crushed tomatoes, tomato paste, basil, salt, pepper, brown sugar, broccoli, spinach, feta');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Penne Pasta With Sausage and Greens', 'feta cheese', 'https://www.budgetbytes.com/snap-challenge-penne-pasta-sausage-greens/', " +
                "'penne pasta, olive oil, italian sausage, garlic, yellow onion, crushed tomatoes, tomato paste, basil, salt, pepper, brown sugar, broccoli, spinach, feta');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('One Pot Sausage and Mushroom Pasta', 'italian sausage', 'https://www.budgetbytes.com/one-pot-sausage-mushroom-pasta/', " +
                "'olive oil, italian sausage, garlic, yellow onion, button mushrooms, crushed tomatoes, basil, oregano, vegetable broth, rigatoni, parmesan cheese, parsley');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('One Pot Sausage and Mushroom Pasta', 'button mushrooms', 'https://www.budgetbytes.com/one-pot-sausage-mushroom-pasta/', " +
                "'olive oil, italian sausage, garlic, yellow onion, button mushrooms, crushed tomatoes, basil, oregano, vegetable broth, rigatoni, parmesan cheese, parsley');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('One Pot Sausage and Mushroom Pasta', 'parmesan cheese', 'https://www.budgetbytes.com/one-pot-sausage-mushroom-pasta/', " +
                "'olive oil, italian sausage, garlic, yellow onion, button mushrooms, crushed tomatoes, basil, oregano, vegetable broth, rigatoni, parmesan cheese, parsley');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('One Pot Sausage and Mushroom Pasta', 'yellow onion', 'https://www.budgetbytes.com/one-pot-sausage-mushroom-pasta/', " +
                "'olive oil, italian sausage, garlic, yellow onion, button mushrooms, crushed tomatoes, basil, oregano, vegetable broth, rigatoni, parmesan cheese, parsley');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Split Pea, Bacon and Potato Soup', 'bacon', 'https://www.budgetbytes.com/split-pea-bacon-potato-soup/', " +
                "'bacon, yellow onion, garlic, split peas, chicken broth, bay leaf, pepper, salt, potatoes');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Split Pea, Bacon and Potato Soup', 'yellow onion', 'https://www.budgetbytes.com/split-pea-bacon-potato-soup/', " +
                "'bacon, yellow onion, garlic, split peas, chicken broth, bay leaf, pepper, salt, potatoes');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Split Pea, Bacon and Potato Soup', 'potatoes', 'https://www.budgetbytes.com/split-pea-bacon-potato-soup/', " +
                "'bacon, yellow onion, garlic, split peas, chicken broth, bay leaf, pepper, salt, potatoes');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Navy Bean Soup With Sausage and Spinach', 'sausage', 'https://www.budgetbytes.com/navy-bean-soup-with-sausage-spinach/', " +
                "'olive oil, smoked sausage, medium onion, garlic, carrots, celery, navy beans, bay leaf, thyme, rosemary, pepper, water, baby spinach, salt, apple cider vinegar');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Navy Bean Soup With Sausage and Spinach', 'yellow onion', 'https://www.budgetbytes.com/navy-bean-soup-with-sausage-spinach/', " +
                "'olive oil, smoked sausage, medium onion, garlic, carrots, celery, navy beans, bay leaf, thyme, rosemary, pepper, water, baby spinach, salt, apple cider vinegar');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Navy Bean Soup With Sausage and Spinach', 'carrots', 'https://www.budgetbytes.com/navy-bean-soup-with-sausage-spinach/', " +
                "'olive oil, smoked sausage, medium onion, garlic, carrots, celery, navy beans, bay leaf, thyme, rosemary, pepper, water, baby spinach, salt, apple cider vinegar');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Navy Bean Soup With Sausage and Spinach', 'celery', 'https://www.budgetbytes.com/navy-bean-soup-with-sausage-spinach/', " +
                "'olive oil, smoked sausage, medium onion, garlic, carrots, celery, navy beans, bay leaf, thyme, rosemary, pepper, water, baby spinach, salt, apple cider vinegar');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Navy Bean Soup With Sausage and Spinach', 'navy beans', 'https://www.budgetbytes.com/navy-bean-soup-with-sausage-spinach/', " +
                "'olive oil, smoked sausage, medium onion, garlic, carrots, celery, navy beans, bay leaf, thyme, rosemary, pepper, water, baby spinach, salt, apple cider vinegar');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Navy Bean Soup With Sausage and Spinach', 'spinach', 'https://www.budgetbytes.com/navy-bean-soup-with-sausage-spinach/', " +
                "'olive oil, smoked sausage, medium onion, garlic, carrots, celery, navy beans, bay leaf, thyme, rosemary, pepper, water, baby spinach, salt, apple cider vinegar');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Creamy Spinach and Sausage Pasta', 'sausage', 'https://www.budgetbytes.com/creamy-spinach-sausage-pasta/', " +
                "'smoked sausage, olive oil, medium onion, diced tomatoes with chiles, chicken broth, pasta, spinach, monterey jack cheese, green onion');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Creamy Spinach and Sausage Pasta', 'yellow onion', 'https://www.budgetbytes.com/creamy-spinach-sausage-pasta/', " +
                "'smoked sausage, olive oil, medium onion, diced tomatoes with chiles, chicken broth, pasta, spinach, monterey jack cheese, green onion');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Creamy Spinach and Sausage Pasta', 'spinach', 'https://www.budgetbytes.com/creamy-spinach-sausage-pasta/', " +
                "'smoked sausage, olive oil, medium onion, diced tomatoes with chiles, chicken broth, pasta, spinach, monterey jack cheese, green onion');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Creamy Spinach and Sausage Pasta', 'monterrey jack cheese', 'https://www.budgetbytes.com/creamy-spinach-sausage-pasta/', " +
                "'smoked sausage, olive oil, medium onion, diced tomatoes with chiles, chicken broth, pasta, spinach, monterey jack cheese, green onion');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Stromboli', 'salami', 'https://www.budgetbytes.com/stromboli/', " +
                "'active dry yeast, sugar, warm water, salt, olive oil, all purpose flour, salami, provolone cheese, banana pepper slices, italian seasoning blend');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Stromboli', 'provolone cheese', 'https://www.budgetbytes.com/stromboli/', " +
                "'active dry yeast, sugar, warm water, salt, olive oil, all purpose flour, salami, provolone cheese, banana pepper slices, italian seasoning blend');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Soy Dijon Pork Tenderloin', 'pork chops', 'https://www.budgetbytes.com/soy-dijon-pork-tenderloin/', " +
                "'pork tenderloin, dijon mustard, soy sauce, olive oil, brown sugar, garlic, pepper, butter, vegetable broth');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Soy Dijon Pork Tenderloin', 'butter', 'https://www.budgetbytes.com/soy-dijon-pork-tenderloin/', " +
                "'pork tenderloin, dijon mustard, soy sauce, olive oil, brown sugar, garlic, pepper, butter, vegetable broth');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Ranch Chops', 'pork chops', 'https://www.budgetbytes.com/ranch-chops/', " +
                "'boneless pork chops, buttermilk, panko bread crumbs, salt, parsley, garlic powder, dill, pepper, vegetable oil');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Ranch Chops', 'buttermilk', 'https://www.budgetbytes.com/ranch-chops/', " +
                "'boneless pork chops, buttermilk, panko bread crumbs, salt, parsley, garlic powder, dill, pepper, vegetable oil');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Baked Spaghetti and Sausage', 'italian sausage', 'https://www.budgetbytes.com/baked-spaghetti-with-sausage/', " +
                "'olive oil, yellow onion, garlic, italian sausage, tomato paste, diced or crushed tomatoes, salt, sugar, spaghetti, feta cheese, parmesan cheese, mozzarella cheese');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Baked Spaghetti and Sausage', 'yellow onion', 'https://www.budgetbytes.com/baked-spaghetti-with-sausage/', " +
                "'olive oil, yellow onion, garlic, italian sausage, tomato paste, diced or crushed tomatoes, salt, sugar, spaghetti, feta cheese, parmesan cheese, mozzarella cheese');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Baked Spaghetti and Sausage', 'feta cheese', 'https://www.budgetbytes.com/baked-spaghetti-with-sausage/', " +
                "'olive oil, yellow onion, garlic, italian sausage, tomato paste, diced or crushed tomatoes, salt, sugar, spaghetti, feta cheese, parmesan cheese, mozzarella cheese');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Baked Spaghetti and Sausage', 'mozzarella cheese', 'https://www.budgetbytes.com/baked-spaghetti-with-sausage/', " +
                "'olive oil, yellow onion, garlic, italian sausage, tomato paste, diced or crushed tomatoes, salt, sugar, spaghetti, feta cheese, parmesan cheese, mozzarella cheese');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Baked Spaghetti and Sausage', 'parmesan cheese', 'https://www.budgetbytes.com/baked-spaghetti-with-sausage/', " +
                "'olive oil, yellow onion, garlic, italian sausage, tomato paste, diced or crushed tomatoes, salt, sugar, spaghetti, feta cheese, parmesan cheese, mozzarella cheese');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Hawaiian Ham Quesadillas', 'cream cheese', 'https://www.budgetbytes.com/hawaiian-ham-quesadillas/', " +
                "'cream cheese, mozzarella cheese, green onions, red pepper flakes, pineapple chunks in juice, smoked ham, flour tortillas');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Hawaiian Ham Quesadillas', 'mozzarella cheese', 'https://www.budgetbytes.com/hawaiian-ham-quesadillas/', " +
                "'cream cheese, mozzarella cheese, green onions, red pepper flakes, pineapple chunks in juice, smoked ham, flour tortillas');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Hawaiian Ham Quesadillas', 'pineapple', 'https://www.budgetbytes.com/hawaiian-ham-quesadillas/', " +
                "'cream cheese, mozzarella cheese, green onions, red pepper flakes, pineapple chunks in juice, smoked ham, flour tortillas');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Hawaiian Ham Quesadillas', 'ham', 'https://www.budgetbytes.com/hawaiian-ham-quesadillas/', " +
                "'cream cheese, mozzarella cheese, green onions, red pepper flakes, pineapple chunks in juice, smoked ham, flour tortillas');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Orange Molasses Pork Chops', 'pork chops', 'https://www.budgetbytes.com/orange-molasses-pork-chops/', " +
                "'pork chops, molasses, orange juice concentrate, apple cider vinegar, dijon mustard, garlic powder, salt, vegetable oil');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Kale and Chorizo Frittata', 'chorizo', 'https://www.budgetbytes.com/kale-chorizo-frittata/', " +
                "'mexican style chorizo, kale, eggs, milk, parmesan cheese, mozzarella cheese');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Kale and Chorizo Frittata', 'kale', 'https://www.budgetbytes.com/kale-chorizo-frittata/', " +
                "'mexican style chorizo, kale, eggs, milk, parmesan cheese, mozzarella cheese');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Kale and Chorizo Frittata', 'eggs', 'https://www.budgetbytes.com/kale-chorizo-frittata/', " +
                "'mexican style chorizo, kale, eggs, milk, parmesan cheese, mozzarella cheese');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Kale and Chorizo Frittata', 'milk', 'https://www.budgetbytes.com/kale-chorizo-frittata/', " +
                "'mexican style chorizo, kale, eggs, milk, parmesan cheese, mozzarella cheese');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Kale and Chorizo Frittata', 'parmesan cheese', 'https://www.budgetbytes.com/kale-chorizo-frittata/', " +
                "'mexican style chorizo, kale, eggs, milk, parmesan cheese, mozzarella cheese');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Kale and Chorizo Frittata', 'mozzarella cheese', 'https://www.budgetbytes.com/kale-chorizo-frittata/', " +
                "'mexican style chorizo, kale, eggs, milk, parmesan cheese, mozzarella cheese');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Herb Roasted Pork Loin', 'pork loin', 'https://www.budgetbytes.com/herb-roasted-pork-loin/', " +
                "'pork loin, garlic, basil, thyme, rosemary, pepper, salt, olive oil');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Spicy Sausage and Broccoli Pasta', 'italian sausage', 'https://www.budgetbytes.com/spicy-sausage-broccoli-pasta/', " +
                "'olive oil, garlic, crushed red pepper, italian sausage, broccoli, bow tie pasta, smoked gouda, italian cheese blend, salt, pepper');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Spicy Sausage and Broccoli Pasta', 'broccoli', 'https://www.budgetbytes.com/spicy-sausage-broccoli-pasta/', " +
                "'olive oil, garlic, crushed red pepper, italian sausage, broccoli, bow tie pasta, smoked gouda, italian cheese blend, salt, pepper');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Spicy Sausage and Broccoli Pasta', 'smoked gouda', 'https://www.budgetbytes.com/spicy-sausage-broccoli-pasta/', " +
                "'olive oil, garlic, crushed red pepper, italian sausage, broccoli, bow tie pasta, smoked gouda, italian cheese blend, salt, pepper');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Spicy Sausage and Broccoli Pasta', 'italian cheese blend', 'https://www.budgetbytes.com/spicy-sausage-broccoli-pasta/', " +
                "'olive oil, garlic, crushed red pepper, italian sausage, broccoli, bow tie pasta, smoked gouda, italian cheese blend, salt, pepper');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Chili Lime Pork Loin', 'pork loin', 'https://www.budgetbytes.com/chili-lime-pork-loin/', " +
                "'pork loin filet, chili powder, lime, vegetable oil, garlic, soy sauce');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Chili Lime Pork Loin', 'lime', 'https://www.budgetbytes.com/chili-lime-pork-loin/', " +
                "'pork loin filet, chili powder, lime, vegetable oil, garlic, soy sauce');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Coffee Rubbed Pork Roast', 'broccoli', 'https://www.budgetbytes.com/coffee-rubbed-pork-roast/', " +
                "'Boston butt or pork shoulder, coffee beans, brown sugar, cayenne pepper, salt, pepper, garlic, smoked paprika');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Cajun Pasta Salad', 'sausage', 'https://www.budgetbytes.com/cajun-pasta-salad/', " +
                "'pasta, smoked sausage, green bell pepper, red bell pepper, red onion, celery, vegetable oil, red wine vinegar, dijon mustard, garlic, salt, cayenne pepper, paprika, pepper, parsley');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Cajun Pasta Salad', 'bell pepper', 'https://www.budgetbytes.com/cajun-pasta-salad/', " +
                "'pasta, smoked sausage, green bell pepper, red bell pepper, red onion, celery, vegetable oil, red wine vinegar, dijon mustard, garlic, salt, cayenne pepper, paprika, pepper, parsley');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Cajun Pasta Salad', 'red onion', 'https://www.budgetbytes.com/cajun-pasta-salad/', " +
                "'pasta, smoked sausage, green bell pepper, red bell pepper, red onion, celery, vegetable oil, red wine vinegar, dijon mustard, garlic, salt, cayenne pepper, paprika, pepper, parsley');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Cajun Pasta Salad', 'celery', 'https://www.budgetbytes.com/cajun-pasta-salad/', " +
                "'pasta, smoked sausage, green bell pepper, red bell pepper, red onion, celery, vegetable oil, red wine vinegar, dijon mustard, garlic, salt, cayenne pepper, paprika, pepper, parsley');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Apricot Dijon Pork Chops', 'pork chops', 'https://www.budgetbytes.com/apricot-dijon-pork-chops/', " +
                "'pork chops, olive oil, apricot preserves, dijon mustard, apple cider vinegar, garlic powder, water, salt, pepper');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Zucchini Sausage Ravioli', 'italian sausage', 'https://www.budgetbytes.com/zucchini-sausage-ravioli/', " +
                "'cheese ravioli, olive oil, italian sausage, zucchini, pasta sauce');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Zucchini Sausage Ravioli', 'zucchini', 'https://www.budgetbytes.com/zucchini-sausage-ravioli/', " +
                "'cheese ravioli, olive oil, italian sausage, zucchini, pasta sauce');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Pineapple Pork Kebabs', 'honey', 'https://www.budgetbytes.com/pineapple-pork-kebabs-no-grill-required/', " +
                "'garlic, ginger, soy sauce, vegetable oil, honey, boneless pork chops, vidalia onion, green bell pepper, pineapple chunks, salt, pepper, cilantro, sriracha');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Pineapple Pork Kebabs', 'pork chops', 'https://www.budgetbytes.com/pineapple-pork-kebabs-no-grill-required/', " +
                "'garlic, ginger, soy sauce, vegetable oil, honey, boneless pork chops, vidalia onion, green bell pepper, pineapple chunks, salt, pepper, cilantro, sriracha');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Pineapple Pork Kebabs', 'vidalia onion', 'https://www.budgetbytes.com/pineapple-pork-kebabs-no-grill-required/', " +
                "'garlic, ginger, soy sauce, vegetable oil, honey, boneless pork chops, vidalia onion, green bell pepper, pineapple chunks, salt, pepper, cilantro, sriracha');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Pineapple Pork Kebabs', 'bell pepper', 'https://www.budgetbytes.com/pineapple-pork-kebabs-no-grill-required/', " +
                "'garlic, ginger, soy sauce, vegetable oil, honey, boneless pork chops, vidalia onion, green bell pepper, pineapple chunks, salt, pepper, cilantro, sriracha');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Pineapple Pork Kebabs', 'pineapple', 'https://www.budgetbytes.com/pineapple-pork-kebabs-no-grill-required/', " +
                "'garlic, ginger, soy sauce, vegetable oil, honey, boneless pork chops, vidalia onion, green bell pepper, pineapple chunks, salt, pepper, cilantro, sriracha');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Chunky Split Pea Soup', 'yellow onion', 'https://www.budgetbytes.com/chunky-split-pea-soup/', " +
                "'olive oil, onion, garlic, celery, carrots, ham hock, split peas, chicken bouillon, bay leaf, potato, salt, pepper');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Chunky Split Pea Soup', 'celery', 'https://www.budgetbytes.com/chunky-split-pea-soup/', " +
                "'olive oil, onion, garlic, celery, carrots, ham hock, split peas, chicken bouillon, bay leaf, potato, salt, pepper');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Chunky Split Pea Soup', 'carrots', 'https://www.budgetbytes.com/chunky-split-pea-soup/', " +
                "'olive oil, onion, garlic, celery, carrots, ham hock, split peas, chicken bouillon, bay leaf, potato, salt, pepper');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Chunky Split Pea Soup', 'potato', 'https://www.budgetbytes.com/chunky-split-pea-soup/', " +
                "'olive oil, onion, garlic, celery, carrots, ham hock, split peas, chicken bouillon, bay leaf, potato, salt, pepper');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Cranberry and Walnut Stuffed Pork Loin', 'pork loin', 'https://www.budgetbytes.com/cranberry-walnut-stuffed-pork-loin/', " +
                "'pork loin, box stuffing mix, butter, dried cranberries, chopped walnuts, olive oil');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Cranberry and Walnut Stuffed Pork Loin', 'butter', 'https://www.budgetbytes.com/cranberry-walnut-stuffed-pork-loin/', " +
                "'pork loin, box stuffing mix, butter, dried cranberries, chopped walnuts, olive oil');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Spicy Hawaiian Pizza', 'monterrey jack cheese', 'https://www.budgetbytes.com/spicy-hawaiian-pizza/', " +
                "'pizza dough, pizza sauce, shredded monterrey jack cheese, pineapple pieces, sliced ham, jalapeno');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Spicy Hawaiian Pizza', 'pineapple', 'https://www.budgetbytes.com/spicy-hawaiian-pizza/', " +
                "'pizza dough, pizza sauce, shredded monterrey jack cheese, pineapple pieces, sliced ham, jalapeno');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Spicy Hawaiian Pizza', 'ham', 'https://www.budgetbytes.com/spicy-hawaiian-pizza/', " +
                "'pizza dough, pizza sauce, shredded monterrey jack cheese, pineapple pieces, sliced ham, jalapeno');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Spicy Hawaiian Pizza', 'jalapeno', 'https://www.budgetbytes.com/spicy-hawaiian-pizza/', " +
                "'pizza dough, pizza sauce, shredded monterrey jack cheese, pineapple pieces, sliced ham, jalapeno');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Slow Cooker BBQ Ribs', 'pork spare ribs', 'https://www.budgetbytes.com/slow-cooker-bbq-ribs/', " +
                "'yellow onion, pork spare ribs, bbq sauce, apple sauce, water, salt, pepper');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Slow Cooker BBQ Ribs', 'yellow onion', 'https://www.budgetbytes.com/slow-cooker-bbq-ribs/', " +
                "'yellow onion, pork spare ribs, bbq sauce, apple sauce, water, salt, pepper');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Garlic Lime Bacon Wrapped Shrimp', 'lime', 'https://www.allrecipes.com/recipe/165039/garlic-lime-bacon-wrapped-shrimp/', " +
                "'vegetable oil, lime juice, garlic, salt, pepper, shrimp, peppered bacon, whole green chili peppers, avocado');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Garlic Lime Bacon Wrapped Shrimp', 'shrimp', 'https://www.allrecipes.com/recipe/165039/garlic-lime-bacon-wrapped-shrimp/', " +
                "'vegetable oil, lime juice, garlic, salt, pepper, shrimp, peppered bacon, whole green chili peppers, avocado');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Garlic Lime Bacon Wrapped Shrimp', 'bacon', 'https://www.allrecipes.com/recipe/165039/garlic-lime-bacon-wrapped-shrimp/', " +
                "'vegetable oil, lime juice, garlic, salt, pepper, shrimp, peppered bacon, whole green chili peppers, avocado');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Garlic Lime Bacon Wrapped Shrimp', 'avocado', 'https://www.allrecipes.com/recipe/165039/garlic-lime-bacon-wrapped-shrimp/', " +
                "'vegetable oil, lime juice, garlic, salt, pepper, shrimp, peppered bacon, whole green chili peppers, avocado');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Fried Stuffed Squash Blossoms', 'broccoli', 'https://www.allrecipes.com/recipe/234616/fried-stuffed-squash-blossoms/', " +
                "'zucchini blossoms, goat cheese, egg, gruyere cheese, pepper, cayenne pepper, self rising flour, cornstarch, water, vegetable oil, all purpose flour');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Fried Stuffed Squash Blossoms', 'zucchini', 'https://www.allrecipes.com/recipe/234616/fried-stuffed-squash-blossoms/', " +
                "'zucchini blossoms, goat cheese, egg, gruyere cheese, pepper, cayenne pepper, self rising flour, cornstarch, water, vegetable oil, all purpose flour');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Fried Stuffed Squash Blossoms', 'goat cheese', 'https://www.allrecipes.com/recipe/234616/fried-stuffed-squash-blossoms/', " +
                "'zucchini blossoms, goat cheese, egg, gruyere cheese, pepper, cayenne pepper, self rising flour, cornstarch, water, vegetable oil, all purpose flour');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Fried Stuffed Squash Blossoms', 'gruyere cheese', 'https://www.allrecipes.com/recipe/234616/fried-stuffed-squash-blossoms/', " +
                "'zucchini blossoms, goat cheese, egg, gruyere cheese, pepper, cayenne pepper, self rising flour, cornstarch, water, vegetable oil, all purpose flour');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Fried Stuffed Squash Blossoms', 'egg', 'https://www.allrecipes.com/recipe/234616/fried-stuffed-squash-blossoms/', " +
                "'zucchini blossoms, goat cheese, egg, gruyere cheese, pepper, cayenne pepper, self rising flour, cornstarch, water, vegetable oil, all purpose flour');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Stuffed Jalapeno Firecrackers', 'jalapeno', 'https://www.allrecipes.com/recipe/216909/stuffed-jalapeno-firecrackers/', " +
                "'jalapeno peppers, cream cheese, jalapeno bacon');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Stuffed Jalapeno Firecrackers', 'cream cheese', 'https://www.allrecipes.com/recipe/216909/stuffed-jalapeno-firecrackers/', " +
                "'jalapeno peppers, cream cheese, jalapeno bacon');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Stuffed Jalapeno Firecrackers', 'bacon', 'https://www.allrecipes.com/recipe/216909/stuffed-jalapeno-firecrackers/', " +
                "'jalapeno peppers, cream cheese, jalapeno bacon');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Easy Rumaki with Pineapple', 'pineapple', 'https://www.allrecipes.com/recipe/235095/easy-rumaki-with-pineapple/', " +
                "'cooking spray, pineapple, chestnut slices, sesame ginger salad dressing, green onions, bacon');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Easy Rumaki with Pineapple', 'broccoli', 'https://www.allrecipes.com/recipe/235095/easy-rumaki-with-pineapple/', " +
                "'cooking spray, pineapple, chestnut slices, sesame ginger salad dressing, green onions, bacon');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Coconut Shrimp', 'shrimp', 'https://www.allrecipes.com/recipe/17753/coconut-shrimp-i/', " +
                "'egg, flour, beer, baking powder, flaked coconut, shrimp, vegetable oil');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Coconut Shrimp', 'egg', 'https://www.allrecipes.com/recipe/17753/coconut-shrimp-i/', " +
                "'egg, flour, beer, baking powder, flaked coconut, shrimp, vegetable oil');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Tenderloin With Spicy Gorgonzola Pine Nut Herb Butter', 'butter', 'https://www.allrecipes.com/recipe/106231/tenderloin-with-spicy-gorgonzola-pine-nut-herb-butter', " +
                "'unsalted butter, crumbled gorgonzola cheese, thyme, rosemary, parsley, red pepper flakes, garlic, pine nuts, salt, pepper, beef tenderloin fillets');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Tenderloin With Spicy Gorgonzola Pine Nut Herb Butter', 'gorgonzola cheese', 'https://www.allrecipes.com/recipe/106231/tenderloin-with-spicy-gorgonzola-pine-nut-herb-butter', " +
                "'unsalted butter, crumbled gorgonzola cheese, thyme, rosemary, parsley, red pepper flakes, garlic, pine nuts, salt, pepper, beef tenderloin fillets');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Tenderloin With Spicy Gorgonzola Pine Nut Herb Butter', 'beef tenderloin', 'https://www.allrecipes.com/recipe/106231/tenderloin-with-spicy-gorgonzola-pine-nut-herb-butter', " +
                "'unsalted butter, crumbled gorgonzola cheese, thyme, rosemary, parsley, red pepper flakes, garlic, pine nuts, salt, pepper, beef tenderloin fillets');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Blue Cheese Burgers', 'ground beef', 'https://www.allrecipes.com/recipe/24685/blue-cheese-burgers/', " +
                "'lean ground beef, blue cheese, chives, hot pepper sauce, worcestershire sauce, pepper, salt, mustard, French rolls');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Blue Cheese Burgers', 'blue cheese', 'https://www.allrecipes.com/recipe/24685/blue-cheese-burgers/', " +
                "'lean ground beef, blue cheese, chives, hot pepper sauce, worcestershire sauce, pepper, salt, mustard, French rolls');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Carne Asada Tacos', 'broccoli', 'https://www.allrecipes.com/recipe/223173/carne-asada-tacos-or-al-pastor-tacos/', " +
                "'yellow onion, garlic, parsley, oregano, paprika, anise seeds, pepper, cumin, sage, thyme, bay leaves, cloves, soy sauce, cider vinegar, orange lime, flank steak, tomatoes, chipotle peppers, corn tortillas, cilantro, radishes');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Carne Asada Tacos', 'yellow onion', 'https://www.allrecipes.com/recipe/223173/carne-asada-tacos-or-al-pastor-tacos/', " +
                "'yellow onion, garlic, parsley, oregano, paprika, anise seeds, pepper, cumin, sage, thyme, bay leaves, cloves, soy sauce, cider vinegar, orange lime, flank steak, tomatoes, chipotle peppers, corn tortillas, cilantro, radishes');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Carne Asada Tacos', 'carne asada', 'https://www.allrecipes.com/recipe/223173/carne-asada-tacos-or-al-pastor-tacos/', " +
                "'yellow onion, garlic, parsley, oregano, paprika, anise seeds, pepper, cumin, sage, thyme, bay leaves, cloves, soy sauce, cider vinegar, orange lime, flank steak, tomatoes, chipotle peppers, corn tortillas, cilantro, radishes');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Carne Asada Tacos', 'orange', 'https://www.allrecipes.com/recipe/223173/carne-asada-tacos-or-al-pastor-tacos/', " +
                "'yellow onion, garlic, parsley, oregano, paprika, anise seeds, pepper, cumin, sage, thyme, bay leaves, cloves, soy sauce, cider vinegar, orange lime, flank steak, tomatoes, chipotle peppers, corn tortillas, cilantro, radishes');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Carne Asada Tacos', 'lime', 'https://www.allrecipes.com/recipe/223173/carne-asada-tacos-or-al-pastor-tacos/', " +
                "'yellow onion, garlic, parsley, oregano, paprika, anise seeds, pepper, cumin, sage, thyme, bay leaves, cloves, soy sauce, cider vinegar, orange lime, flank steak, tomatoes, chipotle peppers, corn tortillas, cilantro, radishes');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Carne Asada Tacos', 'tomato', 'https://www.allrecipes.com/recipe/223173/carne-asada-tacos-or-al-pastor-tacos/', " +
                "'yellow onion, garlic, parsley, oregano, paprika, anise seeds, pepper, cumin, sage, thyme, bay leaves, cloves, soy sauce, cider vinegar, orange lime, flank steak, tomatoes, chipotle peppers, corn tortillas, cilantro, radishes');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Carne Asada Tacos', 'radish', 'https://www.allrecipes.com/recipe/223173/carne-asada-tacos-or-al-pastor-tacos/', " +
                "'yellow onion, garlic, parsley, oregano, paprika, anise seeds, pepper, cumin, sage, thyme, bay leaves, cloves, soy sauce, cider vinegar, orange lime, flank steak, tomatoes, chipotle peppers, corn tortillas, cilantro, radishes');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Grillled Corn With Lime Cilantro Butter', 'corn', 'https://www.allrecipes.com/recipe/222862/grilled-corn-with-cilantro-lime-butter/', " +
                "'butter, cilantro, lime, cayenne pepper, corn on the cob, water');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Grillled Corn With Lime Cilantro Butter', 'lime', 'https://www.allrecipes.com/recipe/222862/grilled-corn-with-cilantro-lime-butter/', " +
                "'butter, cilantro, lime, cayenne pepper, corn on the cob, water');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Grillled Corn With Lime Cilantro Butter', 'butter', 'https://www.allrecipes.com/recipe/222862/grilled-corn-with-cilantro-lime-butter/', " +
                "'butter, cilantro, lime, cayenne pepper, corn on the cob, water');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Juiciest Hamburgers Ever', 'ground beef', 'https://www.allrecipes.com/recipe/49404/juiciest-hamburgers-ever/', " +
                "'ground beef, eggs, dry bread crumbs, evaporated milk, Worcestershire sauce, cayenne pepper, garlic');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Juiciest Hamburgers Ever', 'eggs', 'https://www.allrecipes.com/recipe/49404/juiciest-hamburgers-ever/', " +
                "'ground beef, eggs, dry bread crumbs, evaporated milk, Worcestershire sauce, cayenne pepper, garlic');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Bacon Wrapped Hamburgers', 'cheddar cheese', 'https://www.allrecipes.com/recipe/64893/bacon-wrapped-hamburgers/', " +
                "'cheddar cheese, Parmesan cheese, yellow onion, egg, ketchup, salt, pepper, ground beef, bacon, hamburger buns');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Bacon Wrapped Hamburgers', 'parmesan cheese', 'https://www.allrecipes.com/recipe/64893/bacon-wrapped-hamburgers/', " +
                "'cheddar cheese, Parmesan cheese, yellow onion, egg, ketchup, salt, pepper, ground beef, bacon, hamburger buns');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Bacon Wrapped Hamburgers', 'yellow onion', 'https://www.allrecipes.com/recipe/64893/bacon-wrapped-hamburgers/', " +
                "'cheddar cheese, Parmesan cheese, yellow onion, egg, ketchup, salt, pepper, ground beef, bacon, hamburger buns');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Bacon Wrapped Hamburgers', 'egg', 'https://www.allrecipes.com/recipe/64893/bacon-wrapped-hamburgers/', " +
                "'cheddar cheese, Parmesan cheese, yellow onion, egg, ketchup, salt, pepper, ground beef, bacon, hamburger buns');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Bacon Wrapped Hamburgers', 'ground beef', 'https://www.allrecipes.com/recipe/64893/bacon-wrapped-hamburgers/', " +
                "'cheddar cheese, Parmesan cheese, yellow onion, egg, ketchup, salt, pepper, ground beef, bacon, hamburger buns');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Bacon Wrapped Hamburgers', 'bacon', 'https://www.allrecipes.com/recipe/64893/bacon-wrapped-hamburgers/', " +
                "'cheddar cheese, Parmesan cheese, yellow onion, egg, ketchup, salt, pepper, ground beef, bacon, hamburger buns');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Grilled Shrimp Scampi', 'shrimp', 'https://www.allrecipes.com/recipe/12771/grilled-shrimp-scampi/', " +
                "'olive oil, lemon juice, parsley, garlic, pepper, red pepper flakes, medium shrimp');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Grilled Shrimp Scampi', 'lemon', 'https://www.allrecipes.com/recipe/12771/grilled-shrimp-scampi/', " +
                "'olive oil, lemon juice, parsley, garlic, pepper, red pepper flakes, medium shrimp');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Pork Chops Stuffed With Smoked Gouda and Bacon', 'gouda cheese', 'https://www.allrecipes.com/recipe/69411/pork-chops-stuffed-with-smoked-gouda-and-bacon/', " +
                "'gouda cheese, bacon, parsley, pepper, salt, pork chops, olive oil');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Pork Chops Stuffed With Smoked Gouda and Bacon', 'bacon', 'https://www.allrecipes.com/recipe/69411/pork-chops-stuffed-with-smoked-gouda-and-bacon/', " +
                "'gouda cheese, bacon, parsley, pepper, salt, pork chops, olive oil');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Pork Chops Stuffed With Smoked Gouda and Bacon', 'pork chops', 'https://www.allrecipes.com/recipe/69411/pork-chops-stuffed-with-smoked-gouda-and-bacon/', " +
                "'gouda cheese, bacon, parsley, pepper, salt, pork chops, olive oil');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Spinach and Feta Turkey Burgers', 'ground turkey', 'https://www.allrecipes.com/recipe/158968/spinach-and-feta-turkey-burgers/', " +
                "'eggs, garlic, feta cheese, spinach, ground turkey');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Spinach and Feta Turkey Burgers', 'spinach', 'https://www.allrecipes.com/recipe/158968/spinach-and-feta-turkey-burgers/', " +
                "'eggs, garlic, feta cheese, spinach, ground turkey');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Spinach and Feta Turkey Burgers', 'feta cheese', 'https://www.allrecipes.com/recipe/158968/spinach-and-feta-turkey-burgers/', " +
                "'eggs, garlic, feta cheese, spinach, ground turkey');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Spinach and Feta Turkey Burgers', 'eggs', 'https://www.allrecipes.com/recipe/158968/spinach-and-feta-turkey-burgers/', " +
                "'eggs, garlic, feta cheese, spinach, ground turkey');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Seasoned Turkey Burgers', 'ground turkey', 'https://www.allrecipes.com/recipe/20040/seasoned-turkey-burgers/', " +
                "'ground turkey, dry onion soup mix, pepper, garlic powder, soy sauce, eggs, hamburger buns');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Seasoned Turkey Burgers', 'eggs', 'https://www.allrecipes.com/recipe/20040/seasoned-turkey-burgers/', " +
                "'ground turkey, dry onion soup mix, pepper, garlic powder, soy sauce, eggs, hamburger buns');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Maple Garlic Marinated Pork Tenderloin', 'pork', 'https://www.allrecipes.com/recipe/51997/maple-garlic-marinated-pork-tenderloin/', " +
                "'dijon mustard, sesame oil, garlic, pepper, maple syrup, pork tenderloins');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Grilled Salmon', 'salmon', 'https://www.allrecipes.com/recipe/218093/heathers-grilled-salmon/', " +
                "'brown sugar, olive oil, soy sauce, lemon pepper, thyme, basil, parsley, garlic powder, salmon fillets');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Chicken Satay', 'chicken', 'https://www.allrecipes.com/recipe/17511/chicken-satay/', " +
                "'creamy peanut butter, soy sauce, lemon or lime juice, brown sugar, curry powder, garlic, hot pepper sauce, chicken breast');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Chicken Satay', 'lemon', 'https://www.allrecipes.com/recipe/17511/chicken-satay/', " +
                "'creamy peanut butter, soy sauce, lemon or lime juice, brown sugar, curry powder, garlic, hot pepper sauce, chicken breast');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Chicken Satay', 'lime', 'https://www.allrecipes.com/recipe/17511/chicken-satay/', " +
                "'creamy peanut butter, soy sauce, lemon or lime juice, brown sugar, curry powder, garlic, hot pepper sauce, chicken breast');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Sensational Sirloin Kebobs', 'steak', 'https://www.allrecipes.com/recipe/25483/sensational-sirloin-kabobs/', " +
                "'soy sauce, light brown sugar, white vinegar, garlic powder, seasoned salt, garlic pepper seasoning, lemon-lime soda, beef sirloin steak, green bell peppers, skewers, mushrooms, cherry tomatoes, pineapple');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Sensational Sirloin Kebobs', 'bell peppers', 'https://www.allrecipes.com/recipe/25483/sensational-sirloin-kabobs/', " +
                "'soy sauce, light brown sugar, white vinegar, garlic powder, seasoned salt, garlic pepper seasoning, lemon-lime soda, beef sirloin steak, green bell peppers, skewers, mushrooms, cherry tomatoes, pineapple');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Sensational Sirloin Kebobs', 'mushrooms', 'https://www.allrecipes.com/recipe/25483/sensational-sirloin-kabobs/', " +
                "'soy sauce, light brown sugar, white vinegar, garlic powder, seasoned salt, garlic pepper seasoning, lemon-lime soda, beef sirloin steak, green bell peppers, skewers, mushrooms, cherry tomatoes, pineapple');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Sensational Sirloin Kebobs', 'cherry tomato', 'https://www.allrecipes.com/recipe/25483/sensational-sirloin-kabobs/', " +
                "'soy sauce, light brown sugar, white vinegar, garlic powder, seasoned salt, garlic pepper seasoning, lemon-lime soda, beef sirloin steak, green bell peppers, skewers, mushrooms, cherry tomatoes, pineapple');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Sensational Sirloin Kebobs', 'pineapple', 'https://www.allrecipes.com/recipe/25483/sensational-sirloin-kabobs/', " +
                "'soy sauce, light brown sugar, white vinegar, garlic powder, seasoned salt, garlic pepper seasoning, lemon-lime soda, beef sirloin steak, green bell peppers, skewers, mushrooms, cherry tomatoes, pineapple');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Cheddar Bacon Hamburger', 'ground beef', 'https://www.allrecipes.com/recipe/60088/cheddar-bacon-hamburgers/', " +
                "'ground beef, cheddar cheese, horseradish, salt, pepper, garlic powder, bacon bits, hamburger buns');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Cheddar Bacon Hamburger', 'cheddar cheese', 'https://www.allrecipes.com/recipe/60088/cheddar-bacon-hamburgers/', " +
                "'ground beef, cheddar cheese, horseradish, salt, pepper, garlic powder, bacon bits, hamburger buns');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Grilled Potatoes and Onions', 'potatoes', 'https://www.allrecipes.com/recipe/14512/grilled-potatoes-and-onion/', " +
                "'potatoes, red onion, salt, pepper, butter');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Grilled Potatoes and Onions', 'red onion', 'https://www.allrecipes.com/recipe/14512/grilled-potatoes-and-onion/', " +
                "'potatoes, red onion, salt, pepper, butter');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Grilled Potatoes and Onions', 'butter', 'https://www.allrecipes.com/recipe/14512/grilled-potatoes-and-onion/', " +
                "'potatoes, red onion, salt, pepper, butter');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Barbeque Halibut Steaks', 'butter', 'https://www.allrecipes.com/recipe/12837/barbeque-halibut-steaks/', " +
                "'butter, brown sugar, garlic, lemon juice, soy sauce, pepper, halibut steaks');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Barbeque Halibut Steaks', 'halibut', 'https://www.allrecipes.com/recipe/12837/barbeque-halibut-steaks/', " +
                "'butter, brown sugar, garlic, lemon juice, soy sauce, pepper, halibut steaks');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Barbeque Halibut Steaks', 'lemon', 'https://www.allrecipes.com/recipe/12837/barbeque-halibut-steaks/', " +
                "'butter, brown sugar, garlic, lemon juice, soy sauce, pepper, halibut steaks');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Grill Master Chicken Wings', 'chicken wings', 'https://www.allrecipes.com/recipe/213068/grill-master-chicken-wings/', " +
                "'soy sauce, italian salad dressing, chicken wings, butter, soy sauce, hot pepper sauce');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Grill Master Chicken Wings', 'butter', 'https://www.allrecipes.com/recipe/213068/grill-master-chicken-wings/', " +
                "'soy sauce, italian salad dressing, chicken wings, butter, soy sauce, hot pepper sauce');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Grilled Pork Loin Chops', 'pork chops', 'https://www.allrecipes.com/recipe/218075/grilled-pork-loin-chops/', " +
                "'garlic, brown sugar, honey, soy sauce, worcestershire sauce, ketchup, ginger, onion powder, cinnamon, cayenne pepper, boneless pork chops');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Grilled Pork Loin Chops', 'honey', 'https://www.allrecipes.com/recipe/218075/grilled-pork-loin-chops/', " +
                "'garlic, brown sugar, honey, soy sauce, worcestershire sauce, ketchup, ginger, onion powder, cinnamon, cayenne pepper, boneless pork chops');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Grilled Chicken With Rosemary and Bacon', 'chicken', 'https://www.allrecipes.com/recipe/217981/grilled-chicken-with-rosemary-and-bacon/', " +
                "'garlic powder, chicken breast, salt, pepper, rosemary, bacon');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Grilled Chicken With Rosemary and Bacon', 'bacon', 'https://www.allrecipes.com/recipe/217981/grilled-chicken-with-rosemary-and-bacon/', " +
                "'garlic powder, chicken breast, salt, pepper, rosemary, bacon');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Good Frickin Paprika Chicken', 'chicken', 'https://www.allrecipes.com/recipe/221093/good-frickin-paprika-chicken/', " +
                "'plain yogurt, garlic, ground paprika, olive oil, hot chile paste, cayenne pepper, whole chicken, salt, sherry vinegar, ketchup, salt, pepper');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Good Frickin Paprika Chicken', 'yogurt', 'https://www.allrecipes.com/recipe/221093/good-frickin-paprika-chicken/', " +
                "'plain yogurt, garlic, ground paprika, olive oil, hot chile paste, cayenne pepper, whole chicken, salt, sherry vinegar, ketchup, salt, pepper');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Spicy Grilled Shrimp', 'shrimp', 'https://www.allrecipes.com/recipe/12775/spicy-grilled-shrimp/', " +
                "'garlic, salt, cayenne pepper, paprika, olive oil, lemon, large shrimp');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Spicy Grilled Shrimp', 'lemon', 'https://www.allrecipes.com/recipe/12775/spicy-grilled-shrimp/', " +
                "'garlic, salt, cayenne pepper, paprika, olive oil, lemon, large shrimp');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Amazing Whisky Grilled Baby Back Ribs', 'pork ribs', 'https://www.allrecipes.com/recipe/35753/scott-hibbs-amazing-whisky-grilled-baby-back-ribs/', " +
                "'baby back pork ribs, pepper, red chile pepper, vegetable oil, onion, water, tomato paste, brown sugar, honey, worcestershire sauce, salt, liquid smoke, whiskey, garlic powder, paprika, onion powder, dark molasses');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Amazing Whisky Grilled Baby Back Ribs', 'honey', 'https://www.allrecipes.com/recipe/35753/scott-hibbs-amazing-whisky-grilled-baby-back-ribs/', " +
                "'baby back pork ribs, pepper, red chile pepper, vegetable oil, onion, water, tomato paste, brown sugar, honey, worcestershire sauce, salt, liquid smoke, whiskey, garlic powder, paprika, onion powder, dark molasses');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Chicken Souvlaki with Tsatsiki Sauce', 'chicken', 'https://www.allrecipes.com/recipe/231644/chicken-souvlaki-with-tzatziki-sauce/', " +
                "'olive oil, lemon juice, oregano, salt, chicken breast, greek style yogurt, cucumber, white vinegar, garlic, salt, skewers');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Chicken Souvlaki with Tsatsiki Sauce', 'yogurt', 'https://www.allrecipes.com/recipe/231644/chicken-souvlaki-with-tzatziki-sauce/', " +
                "'olive oil, lemon juice, oregano, salt, chicken breast, greek style yogurt, cucumber, white vinegar, garlic, salt, skewers');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Grilled Potato Salad', 'red potato', 'https://www.allrecipes.com/recipe/244205/grilled-potato-salad/', " +
                "'red potatoes, olive oil, pepper, garlic, white sugar, bacon, apple cider vinegar, salt, green onion, parsley');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Grilled Potato Salad', 'bacon', 'https://www.allrecipes.com/recipe/244205/grilled-potato-salad/', " +
                "'red potatoes, olive oil, pepper, garlic, white sugar, bacon, apple cider vinegar, salt, green onion, parsley');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Sirloin Steak With Garlic Butter', 'steak', 'https://www.allrecipes.com/recipe/14554/sirloin-steak-with-garlic-butter/ ', " +
                "'butter, garlic powder, garlic, beef top sirloin steak, salt, pepper');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Sirloin Steak With Garlic Butter', 'butter', 'https://www.allrecipes.com/recipe/14554/sirloin-steak-with-garlic-butter/ ', " +
                "'butter, garlic powder, garlic, beef top sirloin steak, salt, pepper');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Beerbeque Beef Flank Steak', 'steak', 'https://www.allrecipes.com/recipe/233294/beerbecue-beef-flank-steak/', " +
                "'ketchup, molasses, white vinegar, white sugar, pepper, salt, cayenne pepper, cumin, allspice, cinnamon, beer, beef flank steak');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Grilled Steak Salad With Asian Dressing', 'steak', 'https://www.allrecipes.com/recipe/222718/grilled-steak-salad-with-asian-dressing/', " +
                "'rib eye steak, soy sauce, montreal steak seasoning, lemon, rice vinegar, olive oil, white sugar, sesame oil, garlic powder, red pepper flakes, romaine lettuce, cucumber, avocado, tomato, carrot, red onion, sesame seeds');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Grilled Steak Salad With Asian Dressing', 'lemon', 'https://www.allrecipes.com/recipe/222718/grilled-steak-salad-with-asian-dressing/', " +
                "'rib eye steak, soy sauce, montreal steak seasoning, lemon, rice vinegar, olive oil, white sugar, sesame oil, garlic powder, red pepper flakes, romaine lettuce, cucumber, avocado, tomato, carrot, red onion, sesame seeds');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Grilled Steak Salad With Asian Dressing', 'romaine lettuce', 'https://www.allrecipes.com/recipe/222718/grilled-steak-salad-with-asian-dressing/', " +
                "'rib eye steak, soy sauce, montreal steak seasoning, lemon, rice vinegar, olive oil, white sugar, sesame oil, garlic powder, red pepper flakes, romaine lettuce, cucumber, avocado, tomato, carrot, red onion, sesame seeds');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Grilled Steak Salad With Asian Dressing', 'cucumber', 'https://www.allrecipes.com/recipe/222718/grilled-steak-salad-with-asian-dressing/', " +
                "'rib eye steak, soy sauce, montreal steak seasoning, lemon, rice vinegar, olive oil, white sugar, sesame oil, garlic powder, red pepper flakes, romaine lettuce, cucumber, avocado, tomato, carrot, red onion, sesame seeds');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Grilled Steak Salad With Asian Dressing', 'avocado', 'https://www.allrecipes.com/recipe/222718/grilled-steak-salad-with-asian-dressing/', " +
                "'rib eye steak, soy sauce, montreal steak seasoning, lemon, rice vinegar, olive oil, white sugar, sesame oil, garlic powder, red pepper flakes, romaine lettuce, cucumber, avocado, tomato, carrot, red onion, sesame seeds');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Grilled Steak Salad With Asian Dressing', 'tomatoes', 'https://www.allrecipes.com/recipe/222718/grilled-steak-salad-with-asian-dressing/', " +
                "'rib eye steak, soy sauce, montreal steak seasoning, lemon, rice vinegar, olive oil, white sugar, sesame oil, garlic powder, red pepper flakes, romaine lettuce, cucumber, avocado, tomato, carrot, red onion, sesame seeds');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Grilled Steak Salad With Asian Dressing', 'carrot', 'https://www.allrecipes.com/recipe/222718/grilled-steak-salad-with-asian-dressing/', " +
                "'rib eye steak, soy sauce, montreal steak seasoning, lemon, rice vinegar, olive oil, white sugar, sesame oil, garlic powder, red pepper flakes, romaine lettuce, cucumber, avocado, tomato, carrot, red onion, sesame seeds');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Grilled Steak Salad With Asian Dressing', 'red onion', 'https://www.allrecipes.com/recipe/222718/grilled-steak-salad-with-asian-dressing/', " +
                "'rib eye steak, soy sauce, montreal steak seasoning, lemon, rice vinegar, olive oil, white sugar, sesame oil, garlic powder, red pepper flakes, romaine lettuce, cucumber, avocado, tomato, carrot, red onion, sesame seeds');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Korean BBQ Short Ribs', 'beef ribs', 'https://www.allrecipes.com/recipe/141716/korean-bbq-short-ribs-gal-bi/', " +
                "'soy sauce, water, white vinegar, brown sugar, white sugar, pepper, sesame oil, garlic, onion, beef short ribs');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Korean BBQ Short Ribs', 'yellow onion', 'https://www.allrecipes.com/recipe/141716/korean-bbq-short-ribs-gal-bi/', " +
                "'soy sauce, water, white vinegar, brown sugar, white sugar, pepper, sesame oil, garlic, onion, beef short ribs');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Moms Beef Shish Kabobs', 'beef', 'https://www.allrecipes.com/recipe/218486/moms-beef-shish-kabobs/', " +
                "'vegetable oil, soy sauce, lemon juice, mustard, worcestershire sauce, garlic, pepper, salt, lean beef, mushroom caps, green bell pepper, red bell pepper, yellow onion');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Moms Beef Shish Kabobs', 'lemon', 'https://www.allrecipes.com/recipe/218486/moms-beef-shish-kabobs/', " +
                "'vegetable oil, soy sauce, lemon juice, mustard, worcestershire sauce, garlic, pepper, salt, lean beef, mushroom caps, green bell pepper, red bell pepper, yellow onion');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Moms Beef Shish Kabobs', 'mushrooms', 'https://www.allrecipes.com/recipe/218486/moms-beef-shish-kabobs/', " +
                "'vegetable oil, soy sauce, lemon juice, mustard, worcestershire sauce, garlic, pepper, salt, lean beef, mushroom caps, green bell pepper, red bell pepper, yellow onion');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Moms Beef Shish Kabobs', 'bell pepper', 'https://www.allrecipes.com/recipe/218486/moms-beef-shish-kabobs/', " +
                "'vegetable oil, soy sauce, lemon juice, mustard, worcestershire sauce, garlic, pepper, salt, lean beef, mushroom caps, green bell pepper, red bell pepper, yellow onion');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Moms Beef Shish Kabobs', 'yellow onion', 'https://www.allrecipes.com/recipe/218486/moms-beef-shish-kabobs/', " +
                "'vegetable oil, soy sauce, lemon juice, mustard, worcestershire sauce, garlic, pepper, salt, lean beef, mushroom caps, green bell pepper, red bell pepper, yellow onion');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Marinated Flank Steak', 'steak', 'https://www.allrecipes.com/recipe/18074/marinated-flank-steak/', " +
                "'vegetable oil, soy sauce, red wine vinegar, lemon juice, worcestershire sauce, dijon mustard, garlic, pepper, flank steak');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Marinated Flank Steak', 'lemon', 'https://www.allrecipes.com/recipe/18074/marinated-flank-steak/', " +
                "'vegetable oil, soy sauce, red wine vinegar, lemon juice, worcestershire sauce, dijon mustard, garlic, pepper, flank steak');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Savory Garlic Marinated Steak', 'steak', 'https://www.allrecipes.com/recipe/17325/savory-garlic-marinated-steaks/', " +
                "'balsamic vinegar, soy sauce, garlic, honey, pepper, worcestershire sauce, onion powder, salt, liquid smoke, cayenne pepper, rib eye steak');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Savory Garlic Marinated Steak', 'honey', 'https://www.allrecipes.com/recipe/17325/savory-garlic-marinated-steaks/', " +
                "'balsamic vinegar, soy sauce, garlic, honey, pepper, worcestershire sauce, onion powder, salt, liquid smoke, cayenne pepper, rib eye steak');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Ranch Burger', 'ground beef', 'https://www.allrecipes.com/recipe/72715/ranch-burgers/', " +
                "'ground beef, ranch dressing mix, egg, saltine crackers, onion, hamburger buns');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Ranch Burger', 'eggs', 'https://www.allrecipes.com/recipe/72715/ranch-burgers/', " +
                "'ground beef, ranch dressing mix, egg, saltine crackers, onion, hamburger buns');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Ranch Burger', 'onion', 'https://www.allrecipes.com/recipe/72715/ranch-burgers/', " +
                "'ground beef, ranch dressing mix, egg, saltine crackers, onion, hamburger buns');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Best Burgers Ever', 'ground beef', 'https://www.allrecipes.com/recipe/72657/best-hamburger-ever/', " +
                "'lean ground beef, yellow onion, colby jack or cheddar cheese, soy sauce, worcestershire sauce, egg, dry onion soup mix, garlic, garlic powder, parsley, basil, oregano, rosemary, salt, pepper');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Best Burgers Ever', 'yellow onion', 'https://www.allrecipes.com/recipe/72657/best-hamburger-ever/', " +
                "'lean ground beef, yellow onion, colby jack or cheddar cheese, soy sauce, worcestershire sauce, egg, dry onion soup mix, garlic, garlic powder, parsley, basil, oregano, rosemary, salt, pepper');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Best Burgers Ever', 'colby jack cheese', 'https://www.allrecipes.com/recipe/72657/best-hamburger-ever/', " +
                "'lean ground beef, yellow onion, colby jack or cheddar cheese, soy sauce, worcestershire sauce, egg, dry onion soup mix, garlic, garlic powder, parsley, basil, oregano, rosemary, salt, pepper');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Best Burgers Ever', 'cheddar cheese', 'https://www.allrecipes.com/recipe/72657/best-hamburger-ever/', " +
                "'lean ground beef, yellow onion, colby jack or cheddar cheese, soy sauce, worcestershire sauce, egg, dry onion soup mix, garlic, garlic powder, parsley, basil, oregano, rosemary, salt, pepper');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Best Burgers Ever', 'tomatoes', 'https://www.allrecipes.com/recipe/72657/best-hamburger-ever/', " +
                "'lean ground beef, yellow onion, colby jack or cheddar cheese, soy sauce, worcestershire sauce, egg, dry onion soup mix, garlic, garlic powder, parsley, basil, oregano, rosemary, salt, pepper');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Best Burgers Ever', 'romaine lettuce', 'https://www.allrecipes.com/recipe/72657/best-hamburger-ever/', " +
                "'lean ground beef, yellow onion, colby jack or cheddar cheese, soy sauce, worcestershire sauce, egg, dry onion soup mix, garlic, garlic powder, parsley, basil, oregano, rosemary, salt, pepper');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Lisas Favorite Carne Asada', 'steak', 'https://www.allrecipes.com/recipe/186691/lisas-favorite-carne-asada-marinade/', " +
                "'orange juice, lemon juice, lime juice, garlic, soy sauce, canned chipotle peppers, chili powder, cumin, paprika, oregano, pepper, cilantro, olive oil, flank steak');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Lisas Favorite Carne Asada', 'orange', 'https://www.allrecipes.com/recipe/186691/lisas-favorite-carne-asada-marinade/', " +
                "'orange juice, lemon juice, lime juice, garlic, soy sauce, canned chipotle peppers, chili powder, cumin, paprika, oregano, pepper, cilantro, olive oil, flank steak');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Lisas Favorite Carne Asada', 'lemon', 'https://www.allrecipes.com/recipe/186691/lisas-favorite-carne-asada-marinade/', " +
                "'orange juice, lemon juice, lime juice, garlic, soy sauce, canned chipotle peppers, chili powder, cumin, paprika, oregano, pepper, cilantro, olive oil, flank steak');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Lisas Favorite Carne Asada', 'lime', 'https://www.allrecipes.com/recipe/186691/lisas-favorite-carne-asada-marinade/', " +
                "'orange juice, lemon juice, lime juice, garlic, soy sauce, canned chipotle peppers, chili powder, cumin, paprika, oregano, pepper, cilantro, olive oil, flank steak');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Rib Eye Steaks With a Soy and Ginger Marinade', 'steak', 'https://www.allrecipes.com/recipe/14624/rib-eye-steaks-with-a-soy-and-ginger-marinade/', " +
                "'soy sauce, maple syrup, garlic, ginger, mustard powder, sesame oil, hot pepper sauce, beer, rib eye steak');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Bulgogi', 'steak', 'https://www.allrecipes.com/recipe/63911/bulgogi-korean-barbecued-beef/', " +
                "'soy sauce, sesame oil, sesame seeds, garlic, white sugar, salt, pepper, beef top sirloin, carrot, green onion, yellow onion');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Bulgogi', 'carrots', 'https://www.allrecipes.com/recipe/63911/bulgogi-korean-barbecued-beef/', " +
                "'soy sauce, sesame oil, sesame seeds, garlic, white sugar, salt, pepper, beef top sirloin, carrot, green onion, yellow onion');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Bulgogi', 'yellow onion', 'https://www.allrecipes.com/recipe/63911/bulgogi-korean-barbecued-beef/', " +
                "'soy sauce, sesame oil, sesame seeds, garlic, white sugar, salt, pepper, beef top sirloin, carrot, green onion, yellow onion');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Sweet Teriyaki Beef Skewers', 'steak', 'https://www.allrecipes.com/recipe/231664/sweet-teriyaki-beef-skewers/', " +
                "'light brown sugar, soy sauce, pineapple juice, water, vegetable oil, garlic, boneless round steak, bamboo skewers');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Easy Bacon Onion and Cheese Stuffed Hamburgers', 'ground beef', 'https://www.allrecipes.com/recipe/141125/easy-bacon-onion-and-cheese-stuffed-burgers/', " +
                "'ground beef, salt, pepper, barbeque sauce, garlic powder, bacon, yellow onion, cheddar cheese, hamburger buns');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Easy Bacon Onion and Cheese Stuffed Hamburgers', 'yellow onion', 'https://www.allrecipes.com/recipe/141125/easy-bacon-onion-and-cheese-stuffed-burgers/', " +
                "'ground beef, salt, pepper, barbeque sauce, garlic powder, bacon, yellow onion, cheddar cheese, hamburger buns');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Easy Bacon Onion and Cheese Stuffed Hamburgers', 'bacon', 'https://www.allrecipes.com/recipe/141125/easy-bacon-onion-and-cheese-stuffed-burgers/', " +
                "'ground beef, salt, pepper, barbeque sauce, garlic powder, bacon, yellow onion, cheddar cheese, hamburger buns');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Easy Bacon Onion and Cheese Stuffed Hamburgers', 'cheddar cheese', 'https://www.allrecipes.com/recipe/141125/easy-bacon-onion-and-cheese-stuffed-burgers/', " +
                "'ground beef, salt, pepper, barbeque sauce, garlic powder, bacon, yellow onion, cheddar cheese, hamburger buns');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Best Ever Saucy Beef Kabobs', 'steak', 'https://www.allrecipes.com/recipe/19610/best-ever-saucy-beef-kabobs/', " +
                "'tomato juice, butter, ketchup, dry mustard, salt, paprika, black pepper, garlic, worcestershire sauce, hot sauce, beef sirloin');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Best Ever Saucy Beef Kabobs', 'bell pepper', 'https://www.allrecipes.com/recipe/19610/best-ever-saucy-beef-kabobs/', " +
                "'tomato juice, butter, ketchup, dry mustard, salt, paprika, black pepper, garlic, worcestershire sauce, hot sauce, beef sirloin');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Best Ever Saucy Beef Kabobs', 'onion', 'https://www.allrecipes.com/recipe/19610/best-ever-saucy-beef-kabobs/', " +
                "'tomato juice, butter, ketchup, dry mustard, salt, paprika, black pepper, garlic, worcestershire sauce, hot sauce, beef sirloin');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Best Ever Saucy Beef Kabobs', 'cherry tomato', 'https://www.allrecipes.com/recipe/19610/best-ever-saucy-beef-kabobs/', " +
                "'tomato juice, butter, ketchup, dry mustard, salt, paprika, black pepper, garlic, worcestershire sauce, hot sauce, beef sirloin');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Best Ever Saucy Beef Kabobs', 'mushrooms', 'https://www.allrecipes.com/recipe/19610/best-ever-saucy-beef-kabobs/', " +
                "'tomato juice, butter, ketchup, dry mustard, salt, paprika, black pepper, garlic, worcestershire sauce, hot sauce, beef sirloin');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Best Ever Saucy Beef Kabobs', 'butter', 'https://www.allrecipes.com/recipe/19610/best-ever-saucy-beef-kabobs/', " +
                "'tomato juice, butter, ketchup, dry mustard, salt, paprika, black pepper, garlic, worcestershire sauce, hot sauce, beef sirloin');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Squirrels Great Beef Steak', 'steak', 'https://www.allrecipes.com/recipe/21048/squirrels-great-beef-steak/', " +
                "'soy sauce, italian salad dressing, barbeque sauce, vegetable oil, garlic, steak seasoning, salt, pepper, beef sirloin steak');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Flat Iron Steak and Spinach Salad', 'steak', 'https://www.allrecipes.com/recipe/161026/flat-iron-steak-and-spinach-salad/', " +
                "'flat iron steak, salt, pepper, olive oil, red onion, italian salad dressing, red bell peppers, portobello mushrooms, red wine, baby spinach, crumbled blue cheese');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Flat Iron Steak and Spinach Salad', 'spinach', 'https://www.allrecipes.com/recipe/161026/flat-iron-steak-and-spinach-salad/', " +
                "'flat iron steak, salt, pepper, olive oil, red onion, italian salad dressing, red bell peppers, portobello mushrooms, red wine, baby spinach, crumbled blue cheese');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Flat Iron Steak and Spinach Salad', 'bell pepper', 'https://www.allrecipes.com/recipe/161026/flat-iron-steak-and-spinach-salad/', " +
                "'flat iron steak, salt, pepper, olive oil, red onion, italian salad dressing, red bell peppers, portobello mushrooms, red wine, baby spinach, crumbled blue cheese');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Flat Iron Steak and Spinach Salad', 'red onion', 'https://www.allrecipes.com/recipe/161026/flat-iron-steak-and-spinach-salad/', " +
                "'flat iron steak, salt, pepper, olive oil, red onion, italian salad dressing, red bell peppers, portobello mushrooms, red wine, baby spinach, crumbled blue cheese');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Flat Iron Steak and Spinach Salad', 'mushrooms', 'https://www.allrecipes.com/recipe/161026/flat-iron-steak-and-spinach-salad/', " +
                "'flat iron steak, salt, pepper, olive oil, red onion, italian salad dressing, red bell peppers, portobello mushrooms, red wine, baby spinach, crumbled blue cheese');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Flat Iron Steak and Spinach Salad', 'blue cheese', 'https://www.allrecipes.com/recipe/161026/flat-iron-steak-and-spinach-salad/', " +
                "'flat iron steak, salt, pepper, olive oil, red onion, italian salad dressing, red bell peppers, portobello mushrooms, red wine, baby spinach, crumbled blue cheese');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Firecracker Burgers', 'ground beef', 'https://www.allrecipes.com/recipe/17426/firecracker-burgers/', " +
                "'ground beef, can diced green chiles, beef bouillon cubes, Monterey Jack cheese');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Firecracker Burgers', 'monterey jack cheese', 'https://www.allrecipes.com/recipe/17426/firecracker-burgers/', " +
                "'ground beef, can diced green chiles, beef bouillon cubes, Monterey Jack cheese');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Blackjack Brisket', 'beef brisket', 'https://www.allrecipes.com/recipe/58493/blackjack-brisket/', " +
                "'untrimmed beef brisket, can of beer, large onion, garlic, salt, pepper, hickory smoke barbeque sauce, molasses, liquid smoke');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Blackjack Brisket', 'yellow onion', 'https://www.allrecipes.com/recipe/58493/blackjack-brisket/', " +
                "'untrimmed beef brisket, can of beer, large onion, garlic, salt, pepper, hickory smoke barbeque sauce, molasses, liquid smoke');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Bourbon New York Strip Steak', 'steak', 'https://www.allrecipes.com/recipe/30644/bourbon-street-new-york-strip-steak/', " +
                "'boneless New York strip steak, bourbon whiskey, dark brown sugar');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Margarita Beef Skewers', 'steak', 'https://www.allrecipes.com/recipe/139219/margarita-beef-skewers/', " +
                "'margarita mix, salt, white sugar, garlic, vegetable oil, sirloin steak, metal or bamboo skewers, mushrooms, yellow onion, red or green bell pepper');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Margarita Beef Skewers', 'mushrooms', 'https://www.allrecipes.com/recipe/139219/margarita-beef-skewers/', " +
                "'margarita mix, salt, white sugar, garlic, vegetable oil, sirloin steak, metal or bamboo skewers, mushrooms, yellow onion, red or green bell pepper');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Margarita Beef Skewers', 'yellow onion', 'https://www.allrecipes.com/recipe/139219/margarita-beef-skewers/', " +
                "'margarita mix, salt, white sugar, garlic, vegetable oil, sirloin steak, metal or bamboo skewers, mushrooms, yellow onion, red or green bell pepper');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Margarita Beef Skewers', 'bell pepper', 'https://www.allrecipes.com/recipe/139219/margarita-beef-skewers/', " +
                "'margarita mix, salt, white sugar, garlic, vegetable oil, sirloin steak, metal or bamboo skewers, mushrooms, yellow onion, red or green bell pepper');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Grilled Tri tip', 'beef tri tip', 'https://www.allrecipes.com/recipe/139269/grilled-tri-tip/', " +
                "'Tri-tip roast, garlic, salt, pepper, garlic salt');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Lemon grass Ground Beef Skewers', 'steak', 'https://www.allrecipes.com/recipe/188286/lemongrass-ground-beef-skewers/', " +
                "'lemon grass, yellow onion, garlic, lean ground beef, salt, pepper, write sugar, corn starch, oyster sauce, sesame oil');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Lemon grass Ground Beef Skewers', 'yellow onion', 'https://www.allrecipes.com/recipe/188286/lemongrass-ground-beef-skewers/', " +
                "'lemon grass, yellow onion, garlic, lean ground beef, salt, pepper, write sugar, corn starch, oyster sauce, sesame oil');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Persian Kabob', 'steak', 'https://www.allrecipes.com/recipe/55022/persian-kabob/', " +
                "'Low-fat yogurt, yellow onion, mint, beef top sirloin');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Persian Kabob', 'yellow onion', 'https://www.allrecipes.com/recipe/55022/persian-kabob/', " +
                "'Low-fat yogurt, yellow onion, mint, beef top sirloin');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Persian Kabob', 'yogurt', 'https://www.allrecipes.com/recipe/55022/persian-kabob/', " +
                "'Low-fat yogurt, yellow onion, mint, beef top sirloin');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('California Thai Flank Steak', 'steak', 'https://www.allrecipes.com/recipe/41636/california-thai-flank-steak/', " +
                "'soy sauce, rice vinegar, rice wine, lime juice, dark sesame oil, red onion, fresh basil, fresh mint, lemon grass, crushed peanuts, chile paste, ground coriander, garlic salt, flank steak');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('California Thai Flank Steak', 'lime', 'https://www.allrecipes.com/recipe/41636/california-thai-flank-steak/', " +
                "'soy sauce, rice vinegar, rice wine, lime juice, dark sesame oil, red onion, fresh basil, fresh mint, lemon grass, crushed peanuts, chile paste, ground coriander, garlic salt, flank steak');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('California Thai Flank Steak', 'red onion', 'https://www.allrecipes.com/recipe/41636/california-thai-flank-steak/', " +
                "'soy sauce, rice vinegar, rice wine, lime juice, dark sesame oil, red onion, fresh basil, fresh mint, lemon grass, crushed peanuts, chile paste, ground coriander, garlic salt, flank steak');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Bacon and Roquefort Stuffed Burgers', 'ground beef', 'https://www.allrecipes.com/recipe/78212/bacon-and-roquefort-stuffed-burgers/', " +
                "'ground beef, worcestershire sauce, dijon mustard, black pepper, bacon, roquefort or blue cheese, thyme leaves, hamburger buns, tomato, yellow onion, lettuce');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Bacon and Roquefort Stuffed Burgers', 'bacon', 'https://www.allrecipes.com/recipe/78212/bacon-and-roquefort-stuffed-burgers/', " +
                "'ground beef, worcestershire sauce, dijon mustard, black pepper, bacon, roquefort or blue cheese, thyme leaves, hamburger buns, tomato, yellow onion, lettuce');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Bacon and Roquefort Stuffed Burgers', 'blue cheese', 'https://www.allrecipes.com/recipe/78212/bacon-and-roquefort-stuffed-burgers/', " +
                "'ground beef, worcestershire sauce, dijon mustard, black pepper, bacon, roquefort or blue cheese, thyme leaves, hamburger buns, tomato, yellow onion, lettuce');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Bacon and Roquefort Stuffed Burgers', 'tomatoes', 'https://www.allrecipes.com/recipe/78212/bacon-and-roquefort-stuffed-burgers/', " +
                "'ground beef, worcestershire sauce, dijon mustard, black pepper, bacon, roquefort or blue cheese, thyme leaves, hamburger buns, tomato, yellow onion, lettuce');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Bacon and Roquefort Stuffed Burgers', 'red onion', 'https://www.allrecipes.com/recipe/78212/bacon-and-roquefort-stuffed-burgers/', " +
                "'ground beef, worcestershire sauce, dijon mustard, black pepper, bacon, roquefort or blue cheese, thyme leaves, hamburger buns, tomato, yellow onion, lettuce');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Bacon and Roquefort Stuffed Burgers', 'lettuce', 'https://www.allrecipes.com/recipe/78212/bacon-and-roquefort-stuffed-burgers/', " +
                "'ground beef, worcestershire sauce, dijon mustard, black pepper, bacon, roquefort or blue cheese, thyme leaves, hamburger buns, tomato, yellow onion, lettuce');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Pepper Steak Packets', 'steak', 'https://www.allrecipes.com/recipe/33551/pepper-steak-packet/', " +
                "'sirloin steak, red bell pepper, green bell pepper, yellow bell pepper, sweet onion, cherry tomatoes, zucchini, butter, steak sauce');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Pepper Steak Packets', 'bell pepper', 'https://www.allrecipes.com/recipe/33551/pepper-steak-packet/', " +
                "'sirloin steak, red bell pepper, green bell pepper, yellow bell pepper, sweet onion, cherry tomatoes, zucchini, butter, steak sauce');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Pepper Steak Packets', 'sweet onion', 'https://www.allrecipes.com/recipe/33551/pepper-steak-packet/', " +
                "'sirloin steak, red bell pepper, green bell pepper, yellow bell pepper, sweet onion, cherry tomatoes, zucchini, butter, steak sauce');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Pepper Steak Packets', 'cherry tomato', 'https://www.allrecipes.com/recipe/33551/pepper-steak-packet/', " +
                "'sirloin steak, red bell pepper, green bell pepper, yellow bell pepper, sweet onion, cherry tomatoes, zucchini, butter, steak sauce');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Pepper Steak Packets', 'zucchini', 'https://www.allrecipes.com/recipe/33551/pepper-steak-packet/', " +
                "'sirloin steak, red bell pepper, green bell pepper, yellow bell pepper, sweet onion, cherry tomatoes, zucchini, butter, steak sauce');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Pepper Steak Packets', 'butter', 'https://www.allrecipes.com/recipe/33551/pepper-steak-packet/', " +
                "'sirloin steak, red bell pepper, green bell pepper, yellow bell pepper, sweet onion, cherry tomatoes, zucchini, butter, steak sauce');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Lea''s Burgers', 'ground beef', 'https://www.allrecipes.com/recipe/70513/leas-hamburgers/', " +
                "'ground beef, small onion, egg, bread crumbs, bacon, garlic salt, hamburger buns');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Lea''s Burgers', 'egg', 'https://www.allrecipes.com/recipe/70513/leas-hamburgers/', " +
                "'ground beef, small onion, egg, bread crumbs, bacon, garlic salt, hamburger buns');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Lea''s Burgers', 'bacon', 'https://www.allrecipes.com/recipe/70513/leas-hamburgers/', " +
                "'ground beef, small onion, egg, bread crumbs, bacon, garlic salt, hamburger buns');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Lea''s Burgers', 'yellow onion', 'https://www.allrecipes.com/recipe/70513/leas-hamburgers/', " +
                "'ground beef, small onion, egg, bread crumbs, bacon, garlic salt, hamburger buns');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Chicken Tikki Masala', 'chicken', 'https://www.allrecipes.com/recipe/45736/chicken-tikka-masala/', " +
                "'yogurt, lemon juice, cumin, cinnamon, cayenne pepper, pepper, ginger, salt, chicken breast, skewers, butter, garlic, jalapeno, cumin, paprika, tomato sauce, heavy cream, cilantro');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Chicken Tikki Masala', 'lemon', 'https://www.allrecipes.com/recipe/45736/chicken-tikka-masala/', " +
                "'yogurt, lemon juice, cumin, cinnamon, cayenne pepper, pepper, ginger, salt, chicken breast, skewers, butter, garlic, jalapeno, cumin, paprika, tomato sauce, heavy cream, cilantro');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Chicken Tikki Masala', 'yogurt', 'https://www.allrecipes.com/recipe/45736/chicken-tikka-masala/', " +
                "'yogurt, lemon juice, cumin, cinnamon, cayenne pepper, pepper, ginger, salt, chicken breast, skewers, butter, garlic, jalapeno, cumin, paprika, tomato sauce, heavy cream, cilantro');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Chicken Tikki Masala', 'butter', 'https://www.allrecipes.com/recipe/45736/chicken-tikka-masala/', " +
                "'yogurt, lemon juice, cumin, cinnamon, cayenne pepper, pepper, ginger, salt, chicken breast, skewers, butter, garlic, jalapeno, cumin, paprika, tomato sauce, heavy cream, cilantro');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Chicken Tikki Masala', 'jalapeno', 'https://www.allrecipes.com/recipe/45736/chicken-tikka-masala/', " +
                "'yogurt, lemon juice, cumin, cinnamon, cayenne pepper, pepper, ginger, salt, chicken breast, skewers, butter, garlic, jalapeno, cumin, paprika, tomato sauce, heavy cream, cilantro');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Chicken Tikki Masala', 'heavy cream', 'https://www.allrecipes.com/recipe/45736/chicken-tikka-masala/', " +
                "'yogurt, lemon juice, cumin, cinnamon, cayenne pepper, pepper, ginger, salt, chicken breast, skewers, butter, garlic, jalapeno, cumin, paprika, tomato sauce, heavy cream, cilantro');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Barbequed Pineapple', 'pineapple', 'https://www.allrecipes.com/recipe/22332/barbequed-pineapple/', " +
                "'fresh pineapple, rum, brown sugar, ground cinnamon, ground nutmeg, ground ginger, ground clove');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Grilled Salmon I', 'salmon', 'https://www.allrecipes.com/recipe/12720/grilled-salmon-i/', " +
                "'salmon fillets, lemon pepper, garlic powder, soy sauce, brown sugar, water, vegetable oil');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Rosemary Ranch Chicken Kabobs', 'chicken', 'https://www.allrecipes.com/recipe/64513/rosemary-ranch-chicken-kabobs/', " +
                "'olive oil, ranch dressing, worcestershire sauce, rosemary, salt, lemon juice, white vinegar, pepper, white sugar, chicken breast');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Rosemary Ranch Chicken Kabobs', 'lemon', 'https://www.allrecipes.com/recipe/64513/rosemary-ranch-chicken-kabobs/', " +
                "'olive oil, ranch dressing, worcestershire sauce, rosemary, salt, lemon juice, white vinegar, pepper, white sugar, chicken breast');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Homemade Black Bean Veggie Burgers', 'black beans', 'https://www.allrecipes.com/recipe/85452/homemade-black-bean-veggie-burgers/', " +
                "'can of black beans, green bell pepper, yellow onion, garlic, egg, chili powder, cumin, Thai chili sauce, bread crumbs');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Homemade Black Bean Veggie Burgers', 'bell pepper', 'https://www.allrecipes.com/recipe/85452/homemade-black-bean-veggie-burgers/', " +
                "'can of black beans, green bell pepper, yellow onion, garlic, egg, chili powder, cumin, Thai chili sauce, bread crumbs');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Homemade Black Bean Veggie Burgers', 'yellow onion', 'https://www.allrecipes.com/recipe/85452/homemade-black-bean-veggie-burgers/', " +
                "'can of black beans, green bell pepper, yellow onion, garlic, egg, chili powder, cumin, Thai chili sauce, bread crumbs');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Homemade Black Bean Veggie Burgers', 'eggs', 'https://www.allrecipes.com/recipe/85452/homemade-black-bean-veggie-burgers/', " +
                "'can of black beans, green bell pepper, yellow onion, garlic, egg, chili powder, cumin, Thai chili sauce, bread crumbs');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Yummy Honey Chicken Kabobs', 'chicken', 'https://www.allrecipes.com/recipe/8626/yummy-honey-chicken-kabobs/', " +
                "'vegetable oil, honey, soy sauce, black pepper, chicken breasts, garlic, small onions, red bell peppers, skewers');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Yummy Honey Chicken Kabobs', 'yellow onion', 'https://www.allrecipes.com/recipe/8626/yummy-honey-chicken-kabobs/', " +
                "'vegetable oil, honey, soy sauce, black pepper, chicken breasts, garlic, small onions, red bell peppers, skewers');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Yummy Honey Chicken Kabobs', 'bell pepper', 'https://www.allrecipes.com/recipe/8626/yummy-honey-chicken-kabobs/', " +
                "'vegetable oil, honey, soy sauce, black pepper, chicken breasts, garlic, small onions, red bell peppers, skewers');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Yummy Honey Chicken Kabobs', 'honey', 'https://www.allrecipes.com/recipe/8626/yummy-honey-chicken-kabobs/', " +
                "'vegetable oil, honey, soy sauce, black pepper, chicken breasts, garlic, small onions, red bell peppers, skewers');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Honey Mustard Grilled Chicken', 'chicken', 'https://www.allrecipes.com/recipe/8720/honey-mustard-grilled-chicken/', " +
                "'dijon mustard, honey, mayonnaise, steak sauce, chicken breast');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Honey Mustard Grilled Chicken', 'honey', 'https://www.allrecipes.com/recipe/8720/honey-mustard-grilled-chicken/', " +
                "'dijon mustard, honey, mayonnaise, steak sauce, chicken breast');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Unbelievable Chicken', 'chicken', 'https://www.allrecipes.com/recipe/30522/unbelievable-chicken/', " +
                "'cider vinegar, prepared mustard, garlic, lime, lemon, brown sugar, salt, pepper, olive oil, chicken breast');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Unbelievable Chicken', 'lemon', 'https://www.allrecipes.com/recipe/30522/unbelievable-chicken/', " +
                "'cider vinegar, prepared mustard, garlic, lime, lemon, brown sugar, salt, pepper, olive oil, chicken breast');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Unbelievable Chicken', 'lime', 'https://www.allrecipes.com/recipe/30522/unbelievable-chicken/', " +
                "'cider vinegar, prepared mustard, garlic, lime, lemon, brown sugar, salt, pepper, olive oil, chicken breast');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Grilled Marinated Shrimp', 'shrimp', 'https://www.allrecipes.com/recipe/19484/grilled-marinated-shrimp/', " +
                "'olive oil, fresh parsley, lemon, hot pepper sauce, garlic, tomato paste, dried oregano, salt, pepper, large shrimp, skewers');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Grilled Marinated Shrimp', 'lemon', 'https://www.allrecipes.com/recipe/19484/grilled-marinated-shrimp/', " +
                "'olive oil, fresh parsley, lemon, hot pepper sauce, garlic, tomato paste, dried oregano, salt, pepper, large shrimp, skewers');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Key West Chicken', 'chicken', 'https://www.allrecipes.com/recipe/25445/key-west-chicken/', " +
                "'soy sauce, honey, vegetable oil, lime juice, garlic, chicken breast');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Key West Chicken', 'lime', 'https://www.allrecipes.com/recipe/25445/key-west-chicken/', " +
                "'soy sauce, honey, vegetable oil, lime juice, garlic, chicken breast');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Key West Chicken', 'chicken', 'https://www.allrecipes.com/recipe/25445/key-west-chicken/', " +
                "'soy sauce, honey, vegetable oil, lime juice, garlic, chicken breast');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Key West Chicken', 'lime', 'https://www.allrecipes.com/recipe/25445/key-west-chicken/', " +
                "'soy sauce, honey, vegetable oil, lime juice, garlic, chicken breast');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Key West Chicken', 'honey', 'https://www.allrecipes.com/recipe/25445/key-west-chicken/', " +
                "'soy sauce, honey, vegetable oil, lime juice, garlic, chicken breast');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Grilled Bacon Jalapeno Wraps', 'jalapeno', 'https://www.allrecipes.com/recipe/87076/grilled-bacon-jalapeno-wraps/', " +
                "'jalapeno peppers, cream cheese, bacon');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Grilled Bacon Jalapeno Wraps', 'cream cheese', 'https://www.allrecipes.com/recipe/87076/grilled-bacon-jalapeno-wraps/', " +
                "'jalapeno peppers, cream cheese, bacon');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Grilled Bacon Jalapeno Wraps', 'bacon', 'https://www.allrecipes.com/recipe/87076/grilled-bacon-jalapeno-wraps/', " +
                "'jalapeno peppers, cream cheese, bacon');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Firecracker Grilled Alaska Salmon', 'salmon', 'https://www.allrecipes.com/recipe/16699/firecracker-grilled-alaska-salmon/', " +
                "'salmon fillets, peanut oil, soy sauce, balsamic vinegar, green onions, brown sugar, garlic, ginger, red pepper flakes, sesame oil, salt');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Chipotle Crusted Pork Tenderloin', 'pork', 'https://www.allrecipes.com/recipe/54342/chipotle-crusted-pork-tenderloin/', " +
                "'onion powder, garlic powder, chipotle chile powder, salt, brown sugar, pork tenderloin');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Grilled Fish Tacos With Chipotle Lime Dressing', 'tilapia', 'https://www.allrecipes.com/recipe/142614/grilled-fish-tacos-with-chipotle-lime-dressing/', " +
                "'olive oil, white vinegar, lime, lime juice, honey, garlic, chili powder, seafood seasoning, pepper, hot pepper sauce, tilapia fillets, sour cream, adobo sauce, cumin, salt, tortillas, tomatoes, cilantro, head of cabbage');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Grilled Fish Tacos With Chipotle Lime Dressing', 'lime', 'https://www.allrecipes.com/recipe/142614/grilled-fish-tacos-with-chipotle-lime-dressing/', " +
                "'olive oil, white vinegar, lime, lime juice, honey, garlic, chili powder, seafood seasoning, pepper, hot pepper sauce, tilapia fillets, sour cream, adobo sauce, cumin, salt, tortillas, tomatoes, cilantro, head of cabbage');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Grilled Fish Tacos With Chipotle Lime Dressing', 'honey', 'https://www.allrecipes.com/recipe/142614/grilled-fish-tacos-with-chipotle-lime-dressing/', " +
                "'olive oil, white vinegar, lime, lime juice, honey, garlic, chili powder, seafood seasoning, pepper, hot pepper sauce, tilapia fillets, sour cream, adobo sauce, cumin, salt, tortillas, tomatoes, cilantro, head of cabbage');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Grilled Fish Tacos With Chipotle Lime Dressing', 'sour cream', 'https://www.allrecipes.com/recipe/142614/grilled-fish-tacos-with-chipotle-lime-dressing/', " +
                "'olive oil, white vinegar, lime, lime juice, honey, garlic, chili powder, seafood seasoning, pepper, hot pepper sauce, tilapia fillets, sour cream, adobo sauce, cumin, salt, tortillas, tomatoes, cilantro, head of cabbage');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Grilled Fish Tacos With Chipotle Lime Dressing', 'tomatoes', 'https://www.allrecipes.com/recipe/142614/grilled-fish-tacos-with-chipotle-lime-dressing/', " +
                "'olive oil, white vinegar, lime, lime juice, honey, garlic, chili powder, seafood seasoning, pepper, hot pepper sauce, tilapia fillets, sour cream, adobo sauce, cumin, salt, tortillas, tomatoes, cilantro, head of cabbage');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Grilled Fish Tacos With Chipotle Lime Dressing', 'cabbage', 'https://www.allrecipes.com/recipe/142614/grilled-fish-tacos-with-chipotle-lime-dressing/', " +
                "'olive oil, white vinegar, lime, lime juice, honey, garlic, chili powder, seafood seasoning, pepper, hot pepper sauce, tilapia fillets, sour cream, adobo sauce, cumin, salt, tortillas, tomatoes, cilantro, head of cabbage');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Hawaiian Chicken Kabobs', 'chicken', 'https://www.allrecipes.com/recipe/20415/hawaiian-chicken-kabobs/', " +
                "'soy sauce, brown sugar, sherry, sesame oil, ground ginger, garlic, chicken breast, pineapple chunks');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Hawaiian Chicken Kabobs', 'pineapple', 'https://www.allrecipes.com/recipe/20415/hawaiian-chicken-kabobs/', " +
                "'soy sauce, brown sugar, sherry, sesame oil, ground ginger, garlic, chicken breast, pineapple chunks');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Grilled Lemon Chicken', 'brochickenccoli', 'https://www.allrecipes.com/recipe/88817/grilled-lemon-chicken/', " +
                "'lemon juice, olive oil, dijon mustard, garlic, red bell pepper, salt, pepper, chicken breasts');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Grilled Lemon Chicken', 'bell pepper', 'https://www.allrecipes.com/recipe/88817/grilled-lemon-chicken/', " +
                "'lemon juice, olive oil, dijon mustard, garlic, red bell pepper, salt, pepper, chicken breasts');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Grilled Lemon Chicken', 'lemon', 'https://www.allrecipes.com/recipe/88817/grilled-lemon-chicken/', " +
                "'lemon juice, olive oil, dijon mustard, garlic, red bell pepper, salt, pepper, chicken breasts');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Grilled Delmonico Steaks', 'steak', 'https://www.allrecipes.com/recipe/68301/grilled-delmonico-steaks/', " +
                "'olive oil, worcestershire sauce, soy sauce, garlic, medium onion, salt, pepper, rosemary, steak seasoning, steak sauce, rib eye steaks');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Grilled Delmonico Steaks', 'yellow onion', 'https://www.allrecipes.com/recipe/68301/grilled-delmonico-steaks/', " +
                "'olive oil, worcestershire sauce, soy sauce, garlic, medium onion, salt, pepper, rosemary, steak seasoning, steak sauce, rib eye steaks');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Margarita Grilled Shrimp', 'shrimp', 'https://www.allrecipes.com/recipe/60111/margarita-grilled-shrimp/', " +
                "'shrimp, olive oil, cilantro, lime juice, garlic, tequila, cayenne pepper, salt, skewers');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Margarita Grilled Shrimp', 'lime', 'https://www.allrecipes.com/recipe/60111/margarita-grilled-shrimp/', " +
                "'shrimp, olive oil, cilantro, lime juice, garlic, tequila, cayenne pepper, salt, skewers');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Grilled Lamb With Brown Sugar Glaze', 'lamb', 'https://www.allrecipes.com/recipe/47822/grilled-lamb-with-brown-sugar-glaze/', " +
                "'brown sugar, ground ginger, tarragon, cinnamon, pepper, garlic powder, salt, lamb chops');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Grilled Garlic Parmesan Zucchini', 'zucchini', 'https://www.allrecipes.com/recipe/200919/grilled-garlic-parmesan-zucchini/', " +
                "'zucchini, butter, garlic, parsley, parmesan cheese');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Grilled Garlic Parmesan Zucchini', 'parmesan cheese', 'https://www.allrecipes.com/recipe/200919/grilled-garlic-parmesan-zucchini/', " +
                "'zucchini, butter, garlic, parsley, parmesan cheese');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Grilled Garlic Parmesan Zucchini', 'butter', 'https://www.allrecipes.com/recipe/200919/grilled-garlic-parmesan-zucchini/', " +
                "'zucchini, butter, garlic, parsley, parmesan cheese');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Texas Pork Ribs', 'pork ribs', 'https://www.allrecipes.com/recipe/16913/texas-pork-ribs/', " +
                "'pork spareribs, white sugar, salt, pepper, sweet paprika, cayenne pepper, garlic powder, pan drippings, chopped onion, ketchup, hot water, brown sugar, wood chips');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Texas Pork Ribs', 'yellow onion', 'https://www.allrecipes.com/recipe/16913/texas-pork-ribs/', " +
                "'pork spareribs, white sugar, salt, pepper, sweet paprika, cayenne pepper, garlic powder, pan drippings, chopped onion, ketchup, hot water, brown sugar, wood chips');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Jalapeno Chicken', 'chicken', 'https://www.allrecipes.com/recipe/34066/jalapeno-chicken-ii/', " +
                "'chicken breast halves, italian dressing, jalapeno peppers, cream cheese, bacon, toothpicks');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Jalapeno Chicken', 'jalapeno', 'https://www.allrecipes.com/recipe/34066/jalapeno-chicken-ii/', " +
                "'chicken breast halves, italian dressing, jalapeno peppers, cream cheese, bacon, toothpicks');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Jalapeno Chicken', 'bacon', 'https://www.allrecipes.com/recipe/34066/jalapeno-chicken-ii/', " +
                "'chicken breast halves, italian dressing, jalapeno peppers, cream cheese, bacon, toothpicks');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Jalapeno Chicken', 'cream cheese', 'https://www.allrecipes.com/recipe/34066/jalapeno-chicken-ii/', " +
                "'chicken breast halves, italian dressing, jalapeno peppers, cream cheese, bacon, toothpicks');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Grilled Lemon Herb Pork Chops', 'pork chops', 'https://www.allrecipes.com/recipe/50848/grilled-lemon-herb-pork-chops/', " +
                "'lemon juice, vegetable oil, garlic, salt, pepper, oregano, pork chops');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Grilled Lemon Herb Pork Chops', 'lemon', 'https://www.allrecipes.com/recipe/50848/grilled-lemon-herb-pork-chops/', " +
                "'lemon juice, vegetable oil, garlic, salt, pepper, oregano, pork chops');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Spicy Chipotle Turkey Burgers', 'ground beef', 'https://www.allrecipes.com/recipe/166583/spicy-chipotle-turkey-burgers/', " +
                "'ground turkey, chopped onion, cilantro, chipotle chile in adobo sauce, garlic powder, onion powder, seasoned salt, pepper, mozzarella cheese, hamburger buns');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Spicy Chipotle Turkey Burgers', 'yellow onion', 'https://www.allrecipes.com/recipe/166583/spicy-chipotle-turkey-burgers/', " +
                "'ground turkey, chopped onion, cilantro, chipotle chile in adobo sauce, garlic powder, onion powder, seasoned salt, pepper, mozzarella cheese, hamburger buns');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Spicy Chipotle Turkey Burgers', 'mozzarella cheese', 'https://www.allrecipes.com/recipe/166583/spicy-chipotle-turkey-burgers/', " +
                "'ground turkey, chopped onion, cilantro, chipotle chile in adobo sauce, garlic powder, onion powder, seasoned salt, pepper, mozzarella cheese, hamburger buns');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Chicken Souvlaki Gyro Style', 'chicken', 'https://www.allrecipes.com/recipe/72114/chicken-souvlaki-gyro-style/', " +
                "'balsamic vinaigrette, lemon juice, oregano, pepper, chicken, cucumber, salt, plain yogurt, sour cream, rice vinegar, olive oil, garlic, dill, greek seasoning, pita bread, romaine lettuce, red onion, tomato, kalamata olives, pepperoncini, feta cheese');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Chicken Souvlaki Gyro Style', 'lemon', 'https://www.allrecipes.com/recipe/72114/chicken-souvlaki-gyro-style/', " +
                "'balsamic vinaigrette, lemon juice, oregano, pepper, chicken, cucumber, salt, plain yogurt, sour cream, rice vinegar, olive oil, garlic, dill, greek seasoning, pita bread, romaine lettuce, red onion, tomato, kalamata olives, pepperoncini, feta cheese');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Chicken Souvlaki Gyro Style', 'romaine lettuce', 'https://www.allrecipes.com/recipe/72114/chicken-souvlaki-gyro-style/', " +
                "'balsamic vinaigrette, lemon juice, oregano, pepper, chicken, cucumber, salt, plain yogurt, sour cream, rice vinegar, olive oil, garlic, dill, greek seasoning, pita bread, romaine lettuce, red onion, tomato, kalamata olives, pepperoncini, feta cheese');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Chicken Souvlaki Gyro Style', 'cucumber', 'https://www.allrecipes.com/recipe/72114/chicken-souvlaki-gyro-style/', " +
                "'balsamic vinaigrette, lemon juice, oregano, pepper, chicken, cucumber, salt, plain yogurt, sour cream, rice vinegar, olive oil, garlic, dill, greek seasoning, pita bread, romaine lettuce, red onion, tomato, kalamata olives, pepperoncini, feta cheese');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Chicken Souvlaki Gyro Style', 'yogurt', 'https://www.allrecipes.com/recipe/72114/chicken-souvlaki-gyro-style/', " +
                "'balsamic vinaigrette, lemon juice, oregano, pepper, chicken, cucumber, salt, plain yogurt, sour cream, rice vinegar, olive oil, garlic, dill, greek seasoning, pita bread, romaine lettuce, red onion, tomato, kalamata olives, pepperoncini, feta cheese');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Chicken Souvlaki Gyro Style', 'sour cream', 'https://www.allrecipes.com/recipe/72114/chicken-souvlaki-gyro-style/', " +
                "'balsamic vinaigrette, lemon juice, oregano, pepper, chicken, cucumber, salt, plain yogurt, sour cream, rice vinegar, olive oil, garlic, dill, greek seasoning, pita bread, romaine lettuce, red onion, tomato, kalamata olives, pepperoncini, feta cheese');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Chicken Souvlaki Gyro Style', 'red onion', 'https://www.allrecipes.com/recipe/72114/chicken-souvlaki-gyro-style/', " +
                "'balsamic vinaigrette, lemon juice, oregano, pepper, chicken, cucumber, salt, plain yogurt, sour cream, rice vinegar, olive oil, garlic, dill, greek seasoning, pita bread, romaine lettuce, red onion, tomato, kalamata olives, pepperoncini, feta cheese');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Chicken Souvlaki Gyro Style', 'tomtato', 'https://www.allrecipes.com/recipe/72114/chicken-souvlaki-gyro-style/', " +
                "'balsamic vinaigrette, lemon juice, oregano, pepper, chicken, cucumber, salt, plain yogurt, sour cream, rice vinegar, olive oil, garlic, dill, greek seasoning, pita bread, romaine lettuce, red onion, tomato, kalamata olives, pepperoncini, feta cheese');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Chicken Souvlaki Gyro Style', 'feta cheese', 'https://www.allrecipes.com/recipe/72114/chicken-souvlaki-gyro-style/', " +
                "'balsamic vinaigrette, lemon juice, oregano, pepper, chicken, cucumber, salt, plain yogurt, sour cream, rice vinegar, olive oil, garlic, dill, greek seasoning, pita bread, romaine lettuce, red onion, tomato, kalamata olives, pepperoncini, feta cheese');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Chicken Souvlaki Gyro Style', 'kalamata olives', 'https://www.allrecipes.com/recipe/72114/chicken-souvlaki-gyro-style/', " +
                "'balsamic vinaigrette, lemon juice, oregano, pepper, chicken, cucumber, salt, plain yogurt, sour cream, rice vinegar, olive oil, garlic, dill, greek seasoning, pita bread, romaine lettuce, red onion, tomato, kalamata olives, pepperoncini, feta cheese');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Slammin Salmon', 'salmon', 'https://www.allrecipes.com/recipe/137414/slammin-salmon/', " +
                "'balsamic vinegar, lemon juice, soy sauce, brown sugar, ground ginger, paprika, pepper, crushed red pepper flakes, garlic, green onion, sesame oil, peanut oil, salmon fillets');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Slammin Salmon', 'lemon', 'https://www.allrecipes.com/recipe/137414/slammin-salmon/', " +
                "'balsamic vinegar, lemon juice, soy sauce, brown sugar, ground ginger, paprika, pepper, crushed red pepper flakes, garlic, green onion, sesame oil, peanut oil, salmon fillets');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Pesto Turkey Burgers', 'ground beef', 'https://www.allrecipes.com/recipe/139972/pesto-turkey-burgers/', " +
                "'lean ground turkey, basil pesto, garlic, feta cheese, seasoned salt, bread crumbs');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Pesto Turkey Burgers', 'feta cheese', 'https://www.allrecipes.com/recipe/139972/pesto-turkey-burgers/', " +
                "'lean ground turkey, basil pesto, garlic, feta cheese, seasoned salt, bread crumbs');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Spicy Turkey Burgers', 'ground beef', 'https://www.allrecipes.com/recipe/72416/spicy-turkey-burgers/', " +
                "'lean ground turkey, garlic, ginger root, green chile peppers, cilantro, salt, soy sauce, pepper, paprika, dry mustard, cumin, worcestershire sauce');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Grilled Sea Bass', 'sea bass', 'https://www.allrecipes.com/recipe/45782/grilled-sea-bass/', " +
                "'garlic powder, onion powder, paprika, lemon pepper, sea salt, sea bass, butter, garlic, parsley, olive oil');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Grilled Sea Bass', 'butter', 'https://www.allrecipes.com/recipe/45782/grilled-sea-bass/', " +
                "'garlic powder, onion powder, paprika, lemon pepper, sea salt, sea bass, butter, garlic, parsley, olive oil');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Grilled Garlic and Herb Shrimp', 'shrimp', 'https://www.allrecipes.com/recipe/143394/grilled-garlic-and-herb-shrimp/', " +
                "'paprika, garlic, italian seasoning blend, lemon juice, olive oil, black pepper, basil leaves, brown sugar, shrimp');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Grilled Garlic and Herb Shrimp', 'lemon', 'https://www.allrecipes.com/recipe/143394/grilled-garlic-and-herb-shrimp/', " +
                "'paprika, garlic, italian seasoning blend, lemon juice, olive oil, black pepper, basil leaves, brown sugar, shrimp');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Amy''s Barbeque Chicken Salad', 'chicken', 'https://www.allrecipes.com/recipe/72631/amys-barbecue-chicken-salad/', " +
                "'chicken breast, red leaf lettuce, green leaf lettuce, tomato, cilantro, corn, black beans, french fried onions ranch dressing, barbeque sauce');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Amy''s Barbeque Chicken Salad', 'red leaf lettuce', 'https://www.allrecipes.com/recipe/72631/amys-barbecue-chicken-salad/', " +
                "'chicken breast, red leaf lettuce, green leaf lettuce, tomato, cilantro, corn, black beans, french fried onions ranch dressing, barbeque sauce');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Amy''s Barbeque Chicken Salad', 'green leaf lettuce', 'https://www.allrecipes.com/recipe/72631/amys-barbecue-chicken-salad/', " +
                "'chicken breast, red leaf lettuce, green leaf lettuce, tomato, cilantro, corn, black beans, french fried onions ranch dressing, barbeque sauce');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Amy''s Barbeque Chicken Salad', 'tomatoes', 'https://www.allrecipes.com/recipe/72631/amys-barbecue-chicken-salad/', " +
                "'chicken breast, red leaf lettuce, green leaf lettuce, tomato, cilantro, corn, black beans, french fried onions ranch dressing, barbeque sauce');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Amy''s Barbeque Chicken Salad', 'corn', 'https://www.allrecipes.com/recipe/72631/amys-barbecue-chicken-salad/', " +
                "'chicken breast, red leaf lettuce, green leaf lettuce, tomato, cilantro, corn, black beans, french fried onions ranch dressing, barbeque sauce');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Amy''s Barbeque Chicken Salad', 'black beans', 'https://www.allrecipes.com/recipe/72631/amys-barbecue-chicken-salad/', " +
                "'chicken breast, red leaf lettuce, green leaf lettuce, tomato, cilantro, corn, black beans, french fried onions ranch dressing, barbeque sauce');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Thai Spiced Barbeque Shrimp', 'shrimp', 'https://www.allrecipes.com/recipe/87163/thai-spiced-barbecue-shrimp/', " +
                "'lemon juice, soy sauce, dijon mustard, garlic, brown sugar, curry paste, medium shrimp');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Thai Spiced Barbeque Shrimp', 'lemon', 'https://www.allrecipes.com/recipe/87163/thai-spiced-barbecue-shrimp/', " +
                "'lemon juice, soy sauce, dijon mustard, garlic, brown sugar, curry paste, medium shrimp');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Spicy Lime Grilled Shrimp', 'shrimp', 'https://www.allrecipes.com/recipe/186625/spicy-lime-grilled-shrimp/', " +
                "'cajun seasoning, lime, vegetable oil, shrimp');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Spicy Lime Grilled Shrimp', 'lime', 'https://www.allrecipes.com/recipe/186625/spicy-lime-grilled-shrimp/', " +
                "'cajun seasoning, lime, vegetable oil, shrimp');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Restaurant Style Tequila Lime Chicken', 'chicken', 'https://www.allrecipes.com/recipe/19127/restaurant-style-tequila-lime-chicken/', " +
                "'water, teriyaki sauce, lime juice, garlic, liquid smoke, salt, ginger, tequila, chicken breasts');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Restaurant Style Tequila Lime Chicken', 'lime', 'https://www.allrecipes.com/recipe/19127/restaurant-style-tequila-lime-chicken/', " +
                "'water, teriyaki sauce, lime juice, garlic, liquid smoke, salt, ginger, tequila, chicken breasts');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Ray''s Chicken', 'chicken', 'https://www.allrecipes.com/recipe/72804/rays-chicken/', " +
                "'white vinegar, vegetable oil, soy sauce, lemon, lime, sherry, ground mustard, honey, garlic, brown sugar, lemon pepper, oregano, rosemary, salt, chicken breast');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Ray''s Chicken', 'lemon', 'https://www.allrecipes.com/recipe/72804/rays-chicken/', " +
                "'white vinegar, vegetable oil, soy sauce, lemon, lime, sherry, ground mustard, honey, garlic, brown sugar, lemon pepper, oregano, rosemary, salt, chicken breast');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Ray''s Chicken', 'lime', 'https://www.allrecipes.com/recipe/72804/rays-chicken/', " +
                "'white vinegar, vegetable oil, soy sauce, lemon, lime, sherry, ground mustard, honey, garlic, brown sugar, lemon pepper, oregano, rosemary, salt, chicken breast');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Ray''s Chicken', 'honey', 'https://www.allrecipes.com/recipe/72804/rays-chicken/', " +
                "'white vinegar, vegetable oil, soy sauce, lemon, lime, sherry, ground mustard, honey, garlic, brown sugar, lemon pepper, oregano, rosemary, salt, chicken breast');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Shoyu Chicken', 'chicken0', 'https://www.allrecipes.com/recipe/202463/shoyu-chicken/', " +
                "'brown sugar, water, garlic, onion, ginger, pepper, oregano, red pepper flakes, cayenne pepper, paprika, chicken thighs');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Jay''s Jerk Chicken', 'chicken', 'https://www.allrecipes.com/recipe/8667/jays-jerk-chicken/', " +
                "'green onion, yellow onion, jalapeno pepper, soy sauce, white vinegar, vegetable oil, brown sugar, thyme, clove, nutmeg, allspice, chicken breast');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Jay''s Jerk Chicken', 'jalapeno', 'https://www.allrecipes.com/recipe/8667/jays-jerk-chicken/', " +
                "'green onion, yellow onion, jalapeno pepper, soy sauce, white vinegar, vegetable oil, brown sugar, thyme, clove, nutmeg, allspice, chicken breast');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Jay''s Jerk Chicken', 'yellow onion', 'https://www.allrecipes.com/recipe/8667/jays-jerk-chicken/', " +
                "'green onion, yellow onion, jalapeno pepper, soy sauce, white vinegar, vegetable oil, brown sugar, thyme, clove, nutmeg, allspice, chicken breast');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Feta Cheese Turkey Burgers', 'ground turkey', 'https://www.allrecipes.com/recipe/14572/feta-cheese-turkey-burgers/', " +
                "'ground turkey, feta cheese, kalamata olives, oregano, pepper');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Feta Cheese Turkey Burgers', 'feta cheese', 'https://www.allrecipes.com/recipe/14572/feta-cheese-turkey-burgers/', " +
                "'ground turkey, feta cheese, kalamata olives, oregano, pepper');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Feta Cheese Turkey Burgers', 'kalamata olives', 'https://www.allrecipes.com/recipe/14572/feta-cheese-turkey-burgers/', " +
                "'ground turkey, feta cheese, kalamata olives, oregano, pepper');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Marinated Greek Chicken Kabobs', 'chicken', 'https://www.allrecipes.com/recipe/70624/marinated-greek-chicken-kabobs/', " +
                "'plain yogurt, feta cheese with basil and sun dried tomatoes, lemon, oregano, salt, pepper, rosemary, chicken breast, red onion, green bell pepper');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Marinated Greek Chicken Kabobs', 'yogurt', 'https://www.allrecipes.com/recipe/70624/marinated-greek-chicken-kabobs/', " +
                "'plain yogurt, feta cheese with basil and sun dried tomatoes, lemon, oregano, salt, pepper, rosemary, chicken breast, red onion, green bell pepper');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Marinated Greek Chicken Kabobs', 'feta cheese', 'https://www.allrecipes.com/recipe/70624/marinated-greek-chicken-kabobs/', " +
                "'plain yogurt, feta cheese with basil and sun dried tomatoes, lemon, oregano, salt, pepper, rosemary, chicken breast, red onion, green bell pepper');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Marinated Greek Chicken Kabobs', 'lemon', 'https://www.allrecipes.com/recipe/70624/marinated-greek-chicken-kabobs/', " +
                "'plain yogurt, feta cheese with basil and sun dried tomatoes, lemon, oregano, salt, pepper, rosemary, chicken breast, red onion, green bell pepper');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Marinated Greek Chicken Kabobs', 'red onion', 'https://www.allrecipes.com/recipe/70624/marinated-greek-chicken-kabobs/', " +
                "'plain yogurt, feta cheese with basil and sun dried tomatoes, lemon, oregano, salt, pepper, rosemary, chicken breast, red onion, green bell pepper');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Marinated Greek Chicken Kabobs', 'bell pepper', 'https://www.allrecipes.com/recipe/70624/marinated-greek-chicken-kabobs/', " +
                "'plain yogurt, feta cheese with basil and sun dried tomatoes, lemon, oregano, salt, pepper, rosemary, chicken breast, red onion, green bell pepper');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Cilantro Burgers', 'ground beef', 'https://www.allrecipes.com/recipe/53676/cilantro-burgers/', " +
                "'ground beef, barbeque sauce, garlic powder, cilantro, monterey jack cheese, sourdough bread, lettuce, tomato');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Cilantro Burgers', 'monterey jack cheese', 'https://www.allrecipes.com/recipe/53676/cilantro-burgers/', " +
                "'ground beef, barbeque sauce, garlic powder, cilantro, monterey jack cheese, sourdough bread, lettuce, tomato');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Cilantro Burgers', 'lettuce', 'https://www.allrecipes.com/recipe/53676/cilantro-burgers/', " +
                "'ground beef, barbeque sauce, garlic powder, cilantro, monterey jack cheese, sourdough bread, lettuce, tomato');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Cilantro Burgers', 'tomato', 'https://www.allrecipes.com/recipe/53676/cilantro-burgers/', " +
                "'ground beef, barbeque sauce, garlic powder, cilantro, monterey jack cheese, sourdough bread, lettuce, tomato');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Jalapeno Blue Cheese Burgers', 'ground beef', 'https://www.allrecipes.com/recipe/85554/jalapeno-blue-cheese-burgers/', " +
                "'ground beef, jalapenos, blue cheese, onion powder, garlic powder, soy sauce, salt, swiss cheese, hamburger buns');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Jalapeno Blue Cheese Burgers', 'jalapeno', 'https://www.allrecipes.com/recipe/85554/jalapeno-blue-cheese-burgers/', " +
                "'ground beef, jalapenos, blue cheese, onion powder, garlic powder, soy sauce, salt, swiss cheese, hamburger buns');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Jalapeno Blue Cheese Burgers', 'blue cheese', 'https://www.allrecipes.com/recipe/85554/jalapeno-blue-cheese-burgers/', " +
                "'ground beef, jalapenos, blue cheese, onion powder, garlic powder, soy sauce, salt, swiss cheese, hamburger buns');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Jalapeno Blue Cheese Burgers', 'swiss cheese', 'https://www.allrecipes.com/recipe/85554/jalapeno-blue-cheese-burgers/', " +
                "'ground beef, jalapenos, blue cheese, onion powder, garlic powder, soy sauce, salt, swiss cheese, hamburger buns');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('All American Here''s The Beef Burger', 'ground beef', 'https://www.allrecipes.com/recipe/236113/all-american-heres-the-beef-burgers/', " +
                "'beef chuck, beef brisket, kaiser buns, butter, bacon, cheddar cheese, pepper, ketchup, mustard, dill pickle, garlic powder, smoked paprika, beefsteak tomato, lettuce');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('All American Here''s The Beef Burger', 'beef brisket', 'https://www.allrecipes.com/recipe/236113/all-american-heres-the-beef-burgers/', " +
                "'beef chuck, beef brisket, kaiser buns, butter, bacon, cheddar cheese, pepper, ketchup, mustard, dill pickle, garlic powder, smoked paprika, beefsteak tomato, lettuce');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('All American Here''s The Beef Burger', 'butter', 'https://www.allrecipes.com/recipe/236113/all-american-heres-the-beef-burgers/', " +
                "'beef chuck, beef brisket, kaiser buns, butter, bacon, cheddar cheese, pepper, ketchup, mustard, dill pickle, garlic powder, smoked paprika, beefsteak tomato, lettuce');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('All American Here''s The Beef Burger', 'bacon', 'https://www.allrecipes.com/recipe/236113/all-american-heres-the-beef-burgers/', " +
                "'beef chuck, beef brisket, kaiser buns, butter, bacon, cheddar cheese, pepper, ketchup, mustard, dill pickle, garlic powder, smoked paprika, beefsteak tomato, lettuce');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('All American Here''s The Beef Burger', 'cheddar cheese', 'https://www.allrecipes.com/recipe/236113/all-american-heres-the-beef-burgers/', " +
                "'beef chuck, beef brisket, kaiser buns, butter, bacon, cheddar cheese, pepper, ketchup, mustard, dill pickle, garlic powder, smoked paprika, beefsteak tomato, lettuce');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('All American Here''s The Beef Burger', 'tomato', 'https://www.allrecipes.com/recipe/236113/all-american-heres-the-beef-burgers/', " +
                "'beef chuck, beef brisket, kaiser buns, butter, bacon, cheddar cheese, pepper, ketchup, mustard, dill pickle, garlic powder, smoked paprika, beefsteak tomato, lettuce');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('All American Here''s The Beef Burger', 'lettuce', 'https://www.allrecipes.com/recipe/236113/all-american-heres-the-beef-burgers/', " +
                "'beef chuck, beef brisket, kaiser buns, butter, bacon, cheddar cheese, pepper, ketchup, mustard, dill pickle, garlic powder, smoked paprika, beefsteak tomato, lettuce');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Surprise Burgers', 'ground beef', 'https://www.allrecipes.com/recipe/61645/surprise-burgers/', " +
                "'ground beef, pineapple, ketchup, brown sugar, prepared yellow mustard');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Surprise Burgers', 'pineapple', 'https://www.allrecipes.com/recipe/61645/surprise-burgers/', " +
                "'ground beef, pineapple, ketchup, brown sugar, prepared yellow mustard');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Aussie Works Burger', 'ground beef', 'https://www.allrecipes.com/recipe/70869/aussie-works-burger/', " +
                "'ground beef, large yellow onion, eggs, canadian bacon, pineapple, cheddar cheese, beets, tomato, lettuce, ketchup, mustard, relish, mayonnaise, kaiser rolls');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Aussie Works Burger', 'yellow onion', 'https://www.allrecipes.com/recipe/70869/aussie-works-burger/', " +
                "'ground beef, large yellow onion, eggs, canadian bacon, pineapple, cheddar cheese, beets, tomato, lettuce, ketchup, mustard, relish, mayonnaise, kaiser rolls');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Aussie Works Burger', 'eggs', 'https://www.allrecipes.com/recipe/70869/aussie-works-burger/', " +
                "'ground beef, large yellow onion, eggs, canadian bacon, pineapple, cheddar cheese, beets, tomato, lettuce, ketchup, mustard, relish, mayonnaise, kaiser rolls');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Aussie Works Burger', 'canadian bacon', 'https://www.allrecipes.com/recipe/70869/aussie-works-burger/', " +
                "'ground beef, large yellow onion, eggs, canadian bacon, pineapple, cheddar cheese, beets, tomato, lettuce, ketchup, mustard, relish, mayonnaise, kaiser rolls');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Aussie Works Burger', 'pineapple', 'https://www.allrecipes.com/recipe/70869/aussie-works-burger/', " +
                "'ground beef, large yellow onion, eggs, canadian bacon, pineapple, cheddar cheese, beets, tomato, lettuce, ketchup, mustard, relish, mayonnaise, kaiser rolls');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Aussie Works Burger', 'cheddar cheese', 'https://www.allrecipes.com/recipe/70869/aussie-works-burger/', " +
                "'ground beef, large yellow onion, eggs, canadian bacon, pineapple, cheddar cheese, beets, tomato, lettuce, ketchup, mustard, relish, mayonnaise, kaiser rolls');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Aussie Works Burger', 'beets', 'https://www.allrecipes.com/recipe/70869/aussie-works-burger/', " +
                "'ground beef, large yellow onion, eggs, canadian bacon, pineapple, cheddar cheese, beets, tomato, lettuce, ketchup, mustard, relish, mayonnaise, kaiser rolls');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Aussie Works Burger', 'tomato', 'https://www.allrecipes.com/recipe/70869/aussie-works-burger/', " +
                "'ground beef, large yellow onion, eggs, canadian bacon, pineapple, cheddar cheese, beets, tomato, lettuce, ketchup, mustard, relish, mayonnaise, kaiser rolls');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Aussie Works Burger', 'lettuce', 'https://www.allrecipes.com/recipe/70869/aussie-works-burger/', " +
                "'ground beef, large yellow onion, eggs, canadian bacon, pineapple, cheddar cheese, beets, tomato, lettuce, ketchup, mustard, relish, mayonnaise, kaiser rolls');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Aussie Works Burger', 'mayonnaise', 'https://www.allrecipes.com/recipe/70869/aussie-works-burger/', " +
                "'ground beef, large yellow onion, eggs, canadian bacon, pineapple, cheddar cheese, beets, tomato, lettuce, ketchup, mustard, relish, mayonnaise, kaiser rolls');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Chili Burgers', 'ground beef', 'https://www.allrecipes.com/recipe/75906/chili-burgers/', " +
                "'ground beef, italian sausage, chili sauce, salt, pepper');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Chili Burgers', 'italian sausage', 'https://www.allrecipes.com/recipe/75906/chili-burgers/', " +
                "'ground beef, italian sausage, chili sauce, salt, pepper');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Texas Stuffed Grilled Burgers', 'ground beef', 'https://www.allrecipes.com/recipe/72951/texas-stuffed-grilled-burgers/', " +
                "'lean ground beef, worcestershire sauce, hickory seasoning, salt, pepper, chopped onion, mushrooms, cooked ham, cheddar cheese');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Texas Stuffed Grilled Burgers', 'mushrooms', 'https://www.allrecipes.com/recipe/72951/texas-stuffed-grilled-burgers/', " +
                "'lean ground beef, worcestershire sauce, hickory seasoning, salt, pepper, chopped onion, mushrooms, cooked ham, cheddar cheese');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Texas Stuffed Grilled Burgers', 'ham', 'https://www.allrecipes.com/recipe/72951/texas-stuffed-grilled-burgers/', " +
                "'lean ground beef, worcestershire sauce, hickory seasoning, salt, pepper, chopped onion, mushrooms, cooked ham, cheddar cheese');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Texas Stuffed Grilled Burgers', 'cheddar cheese', 'https://www.allrecipes.com/recipe/72951/texas-stuffed-grilled-burgers/', " +
                "'lean ground beef, worcestershire sauce, hickory seasoning, salt, pepper, chopped onion, mushrooms, cooked ham, cheddar cheese');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Texas Stuffed Grilled Burgers', 'yellow onion', 'https://www.allrecipes.com/recipe/72951/texas-stuffed-grilled-burgers/', " +
                "'lean ground beef, worcestershire sauce, hickory seasoning, salt, pepper, chopped onion, mushrooms, cooked ham, cheddar cheese');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Tex Mex Burger With Cajun Mayo', 'mayonnaise', 'https://www.allrecipes.com/recipe/75290/tex-mex-burger-with-cajun-mayo/', " +
                "'mayonnaise, cajun seasoning, ground beef sirloin, jalapeno, white onion, garlic, cajun seasoning, worcestershire sauce, pepper jack cheese, hamburger buns, lettuce, tomato');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Tex Mex Burger With Cajun Mayo', 'jalapeno', 'https://www.allrecipes.com/recipe/75290/tex-mex-burger-with-cajun-mayo/', " +
                "'mayonnaise, cajun seasoning, ground beef sirloin, jalapeno, white onion, garlic, cajun seasoning, worcestershire sauce, pepper jack cheese, hamburger buns, lettuce, tomato');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Tex Mex Burger With Cajun Mayo', 'white onion', 'https://www.allrecipes.com/recipe/75290/tex-mex-burger-with-cajun-mayo/', " +
                "'mayonnaise, cajun seasoning, ground beef sirloin, jalapeno, white onion, garlic, cajun seasoning, worcestershire sauce, pepper jack cheese, hamburger buns, lettuce, tomato');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Tex Mex Burger With Cajun Mayo', 'ground beef', 'https://www.allrecipes.com/recipe/75290/tex-mex-burger-with-cajun-mayo/', " +
                "'mayonnaise, cajun seasoning, ground beef sirloin, jalapeno, white onion, garlic, cajun seasoning, worcestershire sauce, pepper jack cheese, hamburger buns, lettuce, tomato');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Tex Mex Burger With Cajun Mayo', 'pepper jack cheese', 'https://www.allrecipes.com/recipe/75290/tex-mex-burger-with-cajun-mayo/', " +
                "'mayonnaise, cajun seasoning, ground beef sirloin, jalapeno, white onion, garlic, cajun seasoning, worcestershire sauce, pepper jack cheese, hamburger buns, lettuce, tomato');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Tex Mex Burger With Cajun Mayo', 'tomato', 'https://www.allrecipes.com/recipe/75290/tex-mex-burger-with-cajun-mayo/', " +
                "'mayonnaise, cajun seasoning, ground beef sirloin, jalapeno, white onion, garlic, cajun seasoning, worcestershire sauce, pepper jack cheese, hamburger buns, lettuce, tomato');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Tex Mex Burger With Cajun Mayo', 'lettuce', 'https://www.allrecipes.com/recipe/75290/tex-mex-burger-with-cajun-mayo/', " +
                "'mayonnaise, cajun seasoning, ground beef sirloin, jalapeno, white onion, garlic, cajun seasoning, worcestershire sauce, pepper jack cheese, hamburger buns, lettuce, tomato');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Caprese Burger', 'tomato', 'https://www.allrecipes.com/recipe/207872/caprese-burger/', " +
                "'balsamic vinegar, olive oil, salt, pepper, tomato, ground beef, tomato paste, fresh basil, parmesan cheese, garlic, mozzarella cheese, hamburger buns');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Caprese Burger', 'ground beef', 'https://www.allrecipes.com/recipe/207872/caprese-burger/', " +
                "'balsamic vinegar, olive oil, salt, pepper, tomato, ground beef, tomato paste, fresh basil, parmesan cheese, garlic, mozzarella cheese, hamburger buns');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Caprese Burger', 'parmesan cheese', 'https://www.allrecipes.com/recipe/207872/caprese-burger/', " +
                "'balsamic vinegar, olive oil, salt, pepper, tomato, ground beef, tomato paste, fresh basil, parmesan cheese, garlic, mozzarella cheese, hamburger buns');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Caprese Burger', 'mozzarella cheese', 'https://www.allrecipes.com/recipe/207872/caprese-burger/', " +
                "'balsamic vinegar, olive oil, salt, pepper, tomato, ground beef, tomato paste, fresh basil, parmesan cheese, garlic, mozzarella cheese, hamburger buns');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Summer Feta Burger With Gourmet Cheese Spread', 'cream cheese', 'https://www.allrecipes.com/recipe/141912/summer-feta-burger-with-gourmet-cheese-spread/', " +
                "'cream cheese, garlic, fresh basil, fresh dill, olive oil, red onion, ground beef, ground pork, crumbled feta, red wine, egg, salt, pepper, tomato, green leaf lettuce, kaiser rolls');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Summer Feta Burger With Gourmet Cheese Spread', 'red onion', 'https://www.allrecipes.com/recipe/141912/summer-feta-burger-with-gourmet-cheese-spread/', " +
                "'cream cheese, garlic, fresh basil, fresh dill, olive oil, red onion, ground beef, ground pork, crumbled feta, red wine, egg, salt, pepper, tomato, green leaf lettuce, kaiser rolls');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Summer Feta Burger With Gourmet Cheese Spread', 'ground beef', 'https://www.allrecipes.com/recipe/141912/summer-feta-burger-with-gourmet-cheese-spread/', " +
                "'cream cheese, garlic, fresh basil, fresh dill, olive oil, red onion, ground beef, ground pork, crumbled feta, red wine, egg, salt, pepper, tomato, green leaf lettuce, kaiser rolls');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Summer Feta Burger With Gourmet Cheese Spread', 'ground pork', 'https://www.allrecipes.com/recipe/141912/summer-feta-burger-with-gourmet-cheese-spread/', " +
                "'cream cheese, garlic, fresh basil, fresh dill, olive oil, red onion, ground beef, ground pork, crumbled feta, red wine, egg, salt, pepper, tomato, green leaf lettuce, kaiser rolls');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Summer Feta Burger With Gourmet Cheese Spread', 'feta cheese', 'https://www.allrecipes.com/recipe/141912/summer-feta-burger-with-gourmet-cheese-spread/', " +
                "'cream cheese, garlic, fresh basil, fresh dill, olive oil, red onion, ground beef, ground pork, crumbled feta, red wine, egg, salt, pepper, tomato, green leaf lettuce, kaiser rolls');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Summer Feta Burger With Gourmet Cheese Spread', 'eggs', 'https://www.allrecipes.com/recipe/141912/summer-feta-burger-with-gourmet-cheese-spread/', " +
                "'cream cheese, garlic, fresh basil, fresh dill, olive oil, red onion, ground beef, ground pork, crumbled feta, red wine, egg, salt, pepper, tomato, green leaf lettuce, kaiser rolls');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Summer Feta Burger With Gourmet Cheese Spread', 'tomato', 'https://www.allrecipes.com/recipe/141912/summer-feta-burger-with-gourmet-cheese-spread/', " +
                "'cream cheese, garlic, fresh basil, fresh dill, olive oil, red onion, ground beef, ground pork, crumbled feta, red wine, egg, salt, pepper, tomato, green leaf lettuce, kaiser rolls');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Summer Feta Burger With Gourmet Cheese Spread', 'green leaf lettuce', 'https://www.allrecipes.com/recipe/141912/summer-feta-burger-with-gourmet-cheese-spread/', " +
                "'cream cheese, garlic, fresh basil, fresh dill, olive oil, red onion, ground beef, ground pork, crumbled feta, red wine, egg, salt, pepper, tomato, green leaf lettuce, kaiser rolls');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Triple Bypass', 'ground beef', 'https://www.allrecipes.com/recipe/71494/triple-bypasses/', " +
                "'ground beef, eggs, chopped onion, salt, pepper, red pepper flakes, hot dogs, bacon, barbeque sauce, hoagie rolls, toothpicks');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Triple Bypass', 'eggs', 'https://www.allrecipes.com/recipe/71494/triple-bypasses/', " +
                "'ground beef, eggs, chopped onion, salt, pepper, red pepper flakes, hot dogs, bacon, barbeque sauce, hoagie rolls, toothpicks');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Triple Bypass', 'yellow onion', 'https://www.allrecipes.com/recipe/71494/triple-bypasses/', " +
                "'ground beef, eggs, chopped onion, salt, pepper, red pepper flakes, hot dogs, bacon, barbeque sauce, hoagie rolls, toothpicks');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Triple Bypass', 'bacon', 'https://www.allrecipes.com/recipe/71494/triple-bypasses/', " +
                "'ground beef, eggs, chopped onion, salt, pepper, red pepper flakes, hot dogs, bacon, barbeque sauce, hoagie rolls, toothpicks');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Triple Bypass', 'hot dogs', 'https://www.allrecipes.com/recipe/71494/triple-bypasses/', " +
                "'ground beef, eggs, chopped onion, salt, pepper, red pepper flakes, hot dogs, bacon, barbeque sauce, hoagie rolls, toothpicks');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Grilled Mushroom Swiss Burgers', 'ground beef', 'https://www.allrecipes.com/recipe/36058/grilled-mushroom-swiss-burgers/', " +
                "'lean ground beef, seasoned meat tenderizer, salt, pepper, butter, mushrooms, soy sauce, swiss cheese, hamburger buns');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Grilled Mushroom Swiss Burgers', 'butter', 'https://www.allrecipes.com/recipe/36058/grilled-mushroom-swiss-burgers/', " +
                "'lean ground beef, seasoned meat tenderizer, salt, pepper, butter, mushrooms, soy sauce, swiss cheese, hamburger buns');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Grilled Mushroom Swiss Burgers', 'mushrooms', 'https://www.allrecipes.com/recipe/36058/grilled-mushroom-swiss-burgers/', " +
                "'lean ground beef, seasoned meat tenderizer, salt, pepper, butter, mushrooms, soy sauce, swiss cheese, hamburger buns');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Grilled Mushroom Swiss Burgers', 'swiss cheese', 'https://www.allrecipes.com/recipe/36058/grilled-mushroom-swiss-burgers/', " +
                "'lean ground beef, seasoned meat tenderizer, salt, pepper, butter, mushrooms, soy sauce, swiss cheese, hamburger buns');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Jalapeno Burgers', 'ground beef', 'https://www.allrecipes.com/recipe/19936/jalapeno-hamburgers/', " +
                "'lean ground beef, jalapeno peppers, garlic, tomato paste, cilantro, cajun seasoning');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Jalapeno Burgers', 'jalapeno', 'https://www.allrecipes.com/recipe/19936/jalapeno-hamburgers/', " +
                "'lean ground beef, jalapeno peppers, garlic, tomato paste, cilantro, cajun seasoning');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Bill''s Beefuna Burgers', 'ground beef', 'https://www.allrecipes.com/recipe/64337/bills-beefuna-burgers/', " +
                "'ground beef, tuna, sweet onion, sweet pickle relish, salt, pepper, hamburger buns');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Bill''s Beefuna Burgers', 'sweet onion', 'https://www.allrecipes.com/recipe/64337/bills-beefuna-burgers/', " +
                "'ground beef, tuna, sweet onion, sweet pickle relish, salt, pepper, hamburger buns');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Bill''s Beefuna Burgers', 'tuna', 'https://www.allrecipes.com/recipe/64337/bills-beefuna-burgers/', " +
                "'ground beef, tuna, sweet onion, sweet pickle relish, salt, pepper, hamburger buns');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Red White And Blue Burgers', 'ground beef', 'https://www.allrecipes.com/recipe/168192/red-white-and-blue-burgers/', " +
                "'ground beef, red onion, dry ranch salad dressing, blue cheese, hamburger buns');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Red White And Blue Burgers', 'red onion', 'https://www.allrecipes.com/recipe/168192/red-white-and-blue-burgers/', " +
                "'ground beef, red onion, dry ranch salad dressing, blue cheese, hamburger buns');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Red White And Blue Burgers', 'blue cheese', 'https://www.allrecipes.com/recipe/168192/red-white-and-blue-burgers/', " +
                "'ground beef, red onion, dry ranch salad dressing, blue cheese, hamburger buns');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Mediterranean Burgers', 'ground lamb', 'https://www.allrecipes.com/recipe/215564/mediterranean-lamb-burgers/', " +
                "'ground lamb, ground beef, fresh mint, ginger root, garlic, salt, pepper, greek yogurt, lemon, sweet onion, green tomatoes, ciabatta sandwich rolls, feta cheese, arugala');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Mediterranean Burgers', 'ground beef', 'https://www.allrecipes.com/recipe/215564/mediterranean-lamb-burgers/', " +
                "'ground lamb, ground beef, fresh mint, ginger root, garlic, salt, pepper, greek yogurt, lemon, sweet onion, green tomatoes, ciabatta sandwich rolls, feta cheese, arugala');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Mediterranean Burgers', 'yogurt', 'https://www.allrecipes.com/recipe/215564/mediterranean-lamb-burgers/', " +
                "'ground lamb, ground beef, fresh mint, ginger root, garlic, salt, pepper, greek yogurt, lemon, sweet onion, green tomatoes, ciabatta sandwich rolls, feta cheese, arugala');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Mediterranean Burgers', 'lemon', 'https://www.allrecipes.com/recipe/215564/mediterranean-lamb-burgers/', " +
                "'ground lamb, ground beef, fresh mint, ginger root, garlic, salt, pepper, greek yogurt, lemon, sweet onion, green tomatoes, ciabatta sandwich rolls, feta cheese, arugala');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Mediterranean Burgers', 'sweet onion', 'https://www.allrecipes.com/recipe/215564/mediterranean-lamb-burgers/', " +
                "'ground lamb, ground beef, fresh mint, ginger root, garlic, salt, pepper, greek yogurt, lemon, sweet onion, green tomatoes, ciabatta sandwich rolls, feta cheese, arugala');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Mediterranean Burgers', 'green tomato', 'https://www.allrecipes.com/recipe/215564/mediterranean-lamb-burgers/', " +
                "'ground lamb, ground beef, fresh mint, ginger root, garlic, salt, pepper, greek yogurt, lemon, sweet onion, green tomatoes, ciabatta sandwich rolls, feta cheese, arugala');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Mediterranean Burgers', 'feta cheese', 'https://www.allrecipes.com/recipe/215564/mediterranean-lamb-burgers/', " +
                "'ground lamb, ground beef, fresh mint, ginger root, garlic, salt, pepper, greek yogurt, lemon, sweet onion, green tomatoes, ciabatta sandwich rolls, feta cheese, arugala');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Mediterranean Burgers', 'arugula', 'https://www.allrecipes.com/recipe/215564/mediterranean-lamb-burgers/', " +
                "'ground lamb, ground beef, fresh mint, ginger root, garlic, salt, pepper, greek yogurt, lemon, sweet onion, green tomatoes, ciabatta sandwich rolls, feta cheese, arugala');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Backyard Cooper Burgers', 'ground beef', 'https://www.allrecipes.com/recipe/222850/backyard-cooper-burgers/', " +
                "'ground beef, eggs, worcestershire sauce, olive oil, parmesan cheese, garlic powder, salt, pepper, onion powder');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Backyard Cooper Burgers', 'eggs', 'https://www.allrecipes.com/recipe/222850/backyard-cooper-burgers/', " +
                "'ground beef, eggs, worcestershire sauce, olive oil, parmesan cheese, garlic powder, salt, pepper, onion powder');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Backyard Cooper Burgers', 'parmesan cheese', 'https://www.allrecipes.com/recipe/222850/backyard-cooper-burgers/', " +
                "'ground beef, eggs, worcestershire sauce, olive oil, parmesan cheese, garlic powder, salt, pepper, onion powder');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Chipotle Burgers With Avocado Salsa', 'ground beef', 'https://www.allrecipes.com/recipe/222784/chipotle-burgers-with-avocado-salsa/', " +
                "'ground beef, chipotle peppers in adobo sauce, mushrooms, worcestershire sauce, avocado, tomato, jalapeno, lime, salt, pepper, onion rolls');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Chipotle Burgers With Avocado Salsa', 'mushrooms', 'https://www.allrecipes.com/recipe/222784/chipotle-burgers-with-avocado-salsa/', " +
                "'ground beef, chipotle peppers in adobo sauce, mushrooms, worcestershire sauce, avocado, tomato, jalapeno, lime, salt, pepper, onion rolls');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Chipotle Burgers With Avocado Salsa', 'avocado', 'https://www.allrecipes.com/recipe/222784/chipotle-burgers-with-avocado-salsa/', " +
                "'ground beef, chipotle peppers in adobo sauce, mushrooms, worcestershire sauce, avocado, tomato, jalapeno, lime, salt, pepper, onion rolls');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Chipotle Burgers With Avocado Salsa', 'tomato', 'https://www.allrecipes.com/recipe/222784/chipotle-burgers-with-avocado-salsa/', " +
                "'ground beef, chipotle peppers in adobo sauce, mushrooms, worcestershire sauce, avocado, tomato, jalapeno, lime, salt, pepper, onion rolls');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Chipotle Burgers With Avocado Salsa', 'jalapeno', 'https://www.allrecipes.com/recipe/222784/chipotle-burgers-with-avocado-salsa/', " +
                "'ground beef, chipotle peppers in adobo sauce, mushrooms, worcestershire sauce, avocado, tomato, jalapeno, lime, salt, pepper, onion rolls');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Chipotle Burgers With Avocado Salsa', 'lime', 'https://www.allrecipes.com/recipe/222784/chipotle-burgers-with-avocado-salsa/', " +
                "'ground beef, chipotle peppers in adobo sauce, mushrooms, worcestershire sauce, avocado, tomato, jalapeno, lime, salt, pepper, onion rolls');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Sweet Onion Burgers', 'sweet onion', 'https://www.allrecipes.com/recipe/14571/sweet-onion-burgers/', " +
                "'sweet onion, ground beef, salt, pepper, hamburger buns');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Sweet Onion Burgers', 'ground beef', 'https://www.allrecipes.com/recipe/14571/sweet-onion-burgers/', " +
                "'sweet onion, ground beef, salt, pepper, hamburger buns');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Skippy Burgers', 'ground beef', 'https://www.allrecipes.com/recipe/25484/skippy-burgers/', " +
                "'ground beef, bread crumbs, worcestershire sauce, garlic salt, pepper, bottle dark beer, yellow onion, jalapeno');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Skippy Burgers', 'yellow onion', 'https://www.allrecipes.com/recipe/25484/skippy-burgers/', " +
                "'ground beef, bread crumbs, worcestershire sauce, garlic salt, pepper, bottle dark beer, yellow onion, jalapeno');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Skippy Burgers', 'jalapeno', 'https://www.allrecipes.com/recipe/25484/skippy-burgers/', " +
                "'ground beef, bread crumbs, worcestershire sauce, garlic salt, pepper, bottle dark beer, yellow onion, jalapeno');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('The Burger Your Mama Warned You About', 'ground beef', 'https://www.allrecipes.com/recipe/139691/the-burger-your-mama-warned-you-about/', " +
                "'ground beef, red onion, green bell pepper, garlic, dry onion soup mix, barbeque sauce, bread crumbs, worcestershire sauce, salt, pepper');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('The Burger Your Mama Warned You About', 'red onion', 'https://www.allrecipes.com/recipe/139691/the-burger-your-mama-warned-you-about/', " +
                "'ground beef, red onion, green bell pepper, garlic, dry onion soup mix, barbeque sauce, bread crumbs, worcestershire sauce, salt, pepper');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('The Burger Your Mama Warned You About', 'bell pepper', 'https://www.allrecipes.com/recipe/139691/the-burger-your-mama-warned-you-about/', " +
                "'ground beef, red onion, green bell pepper, garlic, dry onion soup mix, barbeque sauce, bread crumbs, worcestershire sauce, salt, pepper');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Bacon Bourbon Burger', 'ground beef', 'https://www.allrecipes.com/recipe/222362/bacon-bourbon-burgers/', " +
                "'lean ground beef, bourbon, worcestershire sauce, brown sugar, steak seasoning, garlic salt, bacon, cheddar cheese, hamburger buns, tomatoes, lettuce');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Bacon Bourbon Burger', 'bacon', 'https://www.allrecipes.com/recipe/222362/bacon-bourbon-burgers/', " +
                "'lean ground beef, bourbon, worcestershire sauce, brown sugar, steak seasoning, garlic salt, bacon, cheddar cheese, hamburger buns, tomatoes, lettuce');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Bacon Bourbon Burger', 'cheddar cheese', 'https://www.allrecipes.com/recipe/222362/bacon-bourbon-burgers/', " +
                "'lean ground beef, bourbon, worcestershire sauce, brown sugar, steak seasoning, garlic salt, bacon, cheddar cheese, hamburger buns, tomatoes, lettuce');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Bacon Bourbon Burger', 'tomato', 'https://www.allrecipes.com/recipe/222362/bacon-bourbon-burgers/', " +
                "'lean ground beef, bourbon, worcestershire sauce, brown sugar, steak seasoning, garlic salt, bacon, cheddar cheese, hamburger buns, tomatoes, lettuce');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Bacon Bourbon Burger', 'lettuce', 'https://www.allrecipes.com/recipe/222362/bacon-bourbon-burgers/', " +
                "'lean ground beef, bourbon, worcestershire sauce, brown sugar, steak seasoning, garlic salt, bacon, cheddar cheese, hamburger buns, tomatoes, lettuce');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('The Stowe Show Sweet Burger', 'ground beef', 'https://www.allrecipes.com/recipe/75798/the-stowe-show-sweet-burger/', " +
                "'worcestershire sauce, brown sugar, garlic powder, season salt, celery seed, italian seasoning, basil, ground beef, yellow onion, salt, pepper');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('The Stowe Show Sweet Burger', 'yellow onion', 'https://www.allrecipes.com/recipe/75798/the-stowe-show-sweet-burger/', " +
                "'worcestershire sauce, brown sugar, garlic powder, season salt, celery seed, italian seasoning, basil, ground beef, yellow onion, salt, pepper');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Zesty Black And Blue Burger', 'ground beef', 'https://www.allrecipes.com/recipe/256904/zesty-black-blue-burger/', " +
                "'hamburger buns, ground beef, eggs, worcestershire sauce, bacon, green onions, garlic, blue cheese, oregano, red pepper flakes, pepper, sea salt, onion powder, brown sugar');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Zesty Black And Blue Burger', 'eggs', 'https://www.allrecipes.com/recipe/256904/zesty-black-blue-burger/', " +
                "'hamburger buns, ground beef, eggs, worcestershire sauce, bacon, green onions, garlic, blue cheese, oregano, red pepper flakes, pepper, sea salt, onion powder, brown sugar');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Zesty Black And Blue Burger', 'bacon', 'https://www.allrecipes.com/recipe/256904/zesty-black-blue-burger/', " +
                "'hamburger buns, ground beef, eggs, worcestershire sauce, bacon, green onions, garlic, blue cheese, oregano, red pepper flakes, pepper, sea salt, onion powder, brown sugar');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Zesty Black And Blue Burger', 'blue cheese', 'https://www.allrecipes.com/recipe/256904/zesty-black-blue-burger/', " +
                "'hamburger buns, ground beef, eggs, worcestershire sauce, bacon, green onions, garlic, blue cheese, oregano, red pepper flakes, pepper, sea salt, onion powder, brown sugar');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Tim''s Famous Salsa Burgers', 'ground beef', 'https://www.allrecipes.com/recipe/17763/tims-famous-salsa-burgers/', " +
                "'ground beef, hot pepper sauce, salsa, cheddar cheese, monterey jack cheese');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Tim''s Famous Salsa Burgers', 'cheddar cheese', 'https://www.allrecipes.com/recipe/17763/tims-famous-salsa-burgers/', " +
                "'ground beef, hot pepper sauce, salsa, cheddar cheese, monterey jack cheese');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Tim''s Famous Salsa Burgers', 'monterey jack cheese', 'https://www.allrecipes.com/recipe/17763/tims-famous-salsa-burgers/', " +
                "'ground beef, hot pepper sauce, salsa, cheddar cheese, monterey jack cheese');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Onion Ranch Burger', 'ground beef', 'https://www.allrecipes.com/recipe/235207/onion-ranch-burgers/', " +
                "'ground beef, water, ranch dressing mix, dry onion soup mix');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Gourmet Grilling Burgers', 'ground beef', 'https://www.allrecipes.com/recipe/172683/gourmet-grilling-burgers/', " +
                "'lean ground beef, italian sausage, red onion, aneheim chile pepper, garlic, muenster cheese, egg, worcestershire sauce, chili powder, salt, pepper');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Gourmet Grilling Burgers', 'italian sausage', 'https://www.allrecipes.com/recipe/172683/gourmet-grilling-burgers/', " +
                "'lean ground beef, italian sausage, red onion, aneheim chile pepper, garlic, muenster cheese, egg, worcestershire sauce, chili powder, salt, pepper');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Gourmet Grilling Burgers', 'red onion', 'https://www.allrecipes.com/recipe/172683/gourmet-grilling-burgers/', " +
                "'lean ground beef, italian sausage, red onion, aneheim chile pepper, garlic, muenster cheese, egg, worcestershire sauce, chili powder, salt, pepper');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Gourmet Grilling Burgers', 'muenster cheese', 'https://www.allrecipes.com/recipe/172683/gourmet-grilling-burgers/', " +
                "'lean ground beef, italian sausage, red onion, aneheim chile pepper, garlic, muenster cheese, egg, worcestershire sauce, chili powder, salt, pepper');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Gourmet Grilling Burgers', 'eggs', 'https://www.allrecipes.com/recipe/172683/gourmet-grilling-burgers/', " +
                "'lean ground beef, italian sausage, red onion, aneheim chile pepper, garlic, muenster cheese, egg, worcestershire sauce, chili powder, salt, pepper');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Cumin Corn Burgers', 'ground beef', 'https://www.allrecipes.com/recipe/14575/cumin-corn-burgers/', " +
                "'ground beef, ground cumin, cumin seeds, corn, salt, pepper');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Cumin Corn Burgers', 'corn', 'https://www.allrecipes.com/recipe/14575/cumin-corn-burgers/', " +
                "'ground beef, ground cumin, cumin seeds, corn, salt, pepper');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Talk Of The Town Superburgers', 'ground beef', 'https://www.allrecipes.com/recipe/73999/talk-of-the-town-superburgers/', " +
                "'ground beef, dry onion soup mix, warm water, ranch flavored tortilla chips, chili cheese flavored tortilla chips, yellow onion, minced garlic, pickled jalapeno peppers, banana pepper slices, salt, cajun seasoning, pepper jack cheese, hamburger buns');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Talk Of The Town Superburgers', 'yellow onion', 'https://www.allrecipes.com/recipe/73999/talk-of-the-town-superburgers/', " +
                "'ground beef, dry onion soup mix, warm water, ranch flavored tortilla chips, chili cheese flavored tortilla chips, yellow onion, minced garlic, pickled jalapeno peppers, banana pepper slices, salt, cajun seasoning, pepper jack cheese, hamburger buns');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Talk Of The Town Superburgers', 'jalapeno', 'https://www.allrecipes.com/recipe/73999/talk-of-the-town-superburgers/', " +
                "'ground beef, dry onion soup mix, warm water, ranch flavored tortilla chips, chili cheese flavored tortilla chips, yellow onion, minced garlic, pickled jalapeno peppers, banana pepper slices, salt, cajun seasoning, pepper jack cheese, hamburger buns');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Talk Of The Town Superburgers', 'banana peppers', 'https://www.allrecipes.com/recipe/73999/talk-of-the-town-superburgers/', " +
                "'ground beef, dry onion soup mix, warm water, ranch flavored tortilla chips, chili cheese flavored tortilla chips, yellow onion, minced garlic, pickled jalapeno peppers, banana pepper slices, salt, cajun seasoning, pepper jack cheese, hamburger buns');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Talk Of The Town Superburgers', 'pepper jack cheese', 'https://www.allrecipes.com/recipe/73999/talk-of-the-town-superburgers/', " +
                "'ground beef, dry onion soup mix, warm water, ranch flavored tortilla chips, chili cheese flavored tortilla chips, yellow onion, minced garlic, pickled jalapeno peppers, banana pepper slices, salt, cajun seasoning, pepper jack cheese, hamburger buns');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Barbequed Citrus Burgers', 'lemon', 'https://www.allrecipes.com/recipe/206343/barbequed-citrus-burgers/', " +
                "'lemon, lime, orange, barbeque sauce, ground beef, pepper jack cheese, hamburger buns');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Barbequed Citrus Burgers', 'lime', 'https://www.allrecipes.com/recipe/206343/barbequed-citrus-burgers/', " +
                "'lemon, lime, orange, barbeque sauce, ground beef, pepper jack cheese, hamburger buns');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Barbequed Citrus Burgers', 'orange', 'https://www.allrecipes.com/recipe/206343/barbequed-citrus-burgers/', " +
                "'lemon, lime, orange, barbeque sauce, ground beef, pepper jack cheese, hamburger buns');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Barbequed Citrus Burgers', 'ground beef', 'https://www.allrecipes.com/recipe/206343/barbequed-citrus-burgers/', " +
                "'lemon, lime, orange, barbeque sauce, ground beef, pepper jack cheese, hamburger buns');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Barbequed Citrus Burgers', 'pepper jack cheese', 'https://www.allrecipes.com/recipe/206343/barbequed-citrus-burgers/', " +
                "'lemon, lime, orange, barbeque sauce, ground beef, pepper jack cheese, hamburger buns');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Sunnyside Burger With Chipotle Aioli', 'mayonnaise', 'https://www.allrecipes.com/recipe/247237/sunnyside-burger-with-chipotle-aioli/', " +
                "'mayonnaise, garlic, chipotle chile powder, salt, ground beef, italian seasoned bread crumbs, coriander, pepper, salt, sharp cheddar cheese, cooking spray, hamburger buns, lettuce, red onion, tomato');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Sunnyside Burger With Chipotle Aioli', 'ground beef', 'https://www.allrecipes.com/recipe/247237/sunnyside-burger-with-chipotle-aioli/', " +
                "'mayonnaise, garlic, chipotle chile powder, salt, ground beef, italian seasoned bread crumbs, coriander, pepper, salt, sharp cheddar cheese, cooking spray, hamburger buns, lettuce, red onion, tomato');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Sunnyside Burger With Chipotle Aioli', 'cheddar cheese', 'https://www.allrecipes.com/recipe/247237/sunnyside-burger-with-chipotle-aioli/', " +
                "'mayonnaise, garlic, chipotle chile powder, salt, ground beef, italian seasoned bread crumbs, coriander, pepper, salt, sharp cheddar cheese, cooking spray, hamburger buns, lettuce, red onion, tomato');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Sunnyside Burger With Chipotle Aioli', 'lettuce', 'https://www.allrecipes.com/recipe/247237/sunnyside-burger-with-chipotle-aioli/', " +
                "'mayonnaise, garlic, chipotle chile powder, salt, ground beef, italian seasoned bread crumbs, coriander, pepper, salt, sharp cheddar cheese, cooking spray, hamburger buns, lettuce, red onion, tomato');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Sunnyside Burger With Chipotle Aioli', 'eggs', 'https://www.allrecipes.com/recipe/247237/sunnyside-burger-with-chipotle-aioli/', " +
                "'mayonnaise, garlic, chipotle chile powder, salt, ground beef, italian seasoned bread crumbs, coriander, pepper, salt, sharp cheddar cheese, cooking spray, hamburger buns, lettuce, red onion, tomato');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Sunnyside Burger With Chipotle Aioli', 'red onion', 'https://www.allrecipes.com/recipe/247237/sunnyside-burger-with-chipotle-aioli/', " +
                "'mayonnaise, garlic, chipotle chile powder, salt, ground beef, italian seasoned bread crumbs, coriander, pepper, salt, sharp cheddar cheese, cooking spray, hamburger buns, lettuce, red onion, tomato');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Sunnyside Burger With Chipotle Aioli', 'tomato', 'https://www.allrecipes.com/recipe/247237/sunnyside-burger-with-chipotle-aioli/', " +
                "'mayonnaise, garlic, chipotle chile powder, salt, ground beef, italian seasoned bread crumbs, coriander, pepper, salt, sharp cheddar cheese, cooking spray, hamburger buns, lettuce, red onion, tomato');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Chipotle Taco Burger', 'groudn beef', 'https://www.allrecipes.com/recipe/240958/chipotle-taco-burger/', " +
                "'ground beef, onion, chipotle peppers in adobo sauce, taco seasoning');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Chipotle Taco Burger', 'yellow onion', 'https://www.allrecipes.com/recipe/240958/chipotle-taco-burger/', " +
                "'ground beef, onion, chipotle peppers in adobo sauce, taco seasoning');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Spiceburgs', 'eggs', 'https://www.allrecipes.com/recipe/233129/spiceburgs/', " +
                "'egg, barbeque sauce, worcestershire sauce, cayenne pepper, garlic powder, pepper, ground beef, onion, bread crumbs, sour cream, hamburger buns');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Spiceburgs', 'ground beef', 'https://www.allrecipes.com/recipe/233129/spiceburgs/', " +
                "'egg, barbeque sauce, worcestershire sauce, cayenne pepper, garlic powder, pepper, ground beef, onion, bread crumbs, sour cream, hamburger buns');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Spiceburgs', 'yellow onion', 'https://www.allrecipes.com/recipe/233129/spiceburgs/', " +
                "'egg, barbeque sauce, worcestershire sauce, cayenne pepper, garlic powder, pepper, ground beef, onion, bread crumbs, sour cream, hamburger buns');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Pam''s Summertime Sweet Burgers', 'ground beef', 'https://www.allrecipes.com/recipe/218205/pams-summertime-sweet-burgers/', " +
                "'ground beef, ground pork, dry onion soup mix, barbeque sauce, onion, garlic salt');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Pam''s Summertime Sweet Burgers', 'ground pork', 'https://www.allrecipes.com/recipe/218205/pams-summertime-sweet-burgers/', " +
                "'ground beef, ground pork, dry onion soup mix, barbeque sauce, onion, garlic salt');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Pam''s Summertime Sweet Burgers', 'yellow onion', 'https://www.allrecipes.com/recipe/218205/pams-summertime-sweet-burgers/', " +
                "'ground beef, ground pork, dry onion soup mix, barbeque sauce, onion, garlic salt');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Bacon And Blue Cheese Burgers', 'ground beef', 'https://www.allrecipes.com/recipe/231166/bacon-and-blue-cheese-burgers/', " +
                "'lean ground beef, chives, steak sauce, salt, pepper, dry mustard, worcestershire sauce, hot pepper sauce, hamburger buns, bacon, blue cheese');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Bacon And Blue Cheese Burgers', 'bacon', 'https://www.allrecipes.com/recipe/231166/bacon-and-blue-cheese-burgers/', " +
                "'lean ground beef, chives, steak sauce, salt, pepper, dry mustard, worcestershire sauce, hot pepper sauce, hamburger buns, bacon, blue cheese');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Bacon And Blue Cheese Burgers', 'blue cheese', 'https://www.allrecipes.com/recipe/231166/bacon-and-blue-cheese-burgers/', " +
                "'lean ground beef, chives, steak sauce, salt, pepper, dry mustard, worcestershire sauce, hot pepper sauce, hamburger buns, bacon, blue cheese');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Beef and Brie Sliders', 'ground beef', 'https://www.allrecipes.com/recipe/237933/beef-and-brie-sliders/', " +
                "'soft bread crumbs, blueberry greek yogurt, dijon mustard, salt, pepper, lean ground beef, Brie cheese, cocktail buns, red onion');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Beef and Brie Sliders', 'yogurt', 'https://www.allrecipes.com/recipe/237933/beef-and-brie-sliders/', " +
                "'soft bread crumbs, blueberry greek yogurt, dijon mustard, salt, pepper, lean ground beef, Brie cheese, cocktail buns, red onion');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Beef and Brie Sliders', 'brie cheese', 'https://www.allrecipes.com/recipe/237933/beef-and-brie-sliders/', " +
                "'soft bread crumbs, blueberry greek yogurt, dijon mustard, salt, pepper, lean ground beef, Brie cheese, cocktail buns, red onion');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Beef and Brie Sliders', 'red onion', 'https://www.allrecipes.com/recipe/237933/beef-and-brie-sliders/', " +
                "'soft bread crumbs, blueberry greek yogurt, dijon mustard, salt, pepper, lean ground beef, Brie cheese, cocktail buns, red onion');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Blue Cheese Stuffed Burgers', 'mushrooms', 'https://www.allrecipes.com/recipe/238746/blue-cheese-stuffed-burgers/', " +
                "'olive oil, salt, pepper, mushrooms, red onion, ground beef, worcestershire sauce, blue cheese, hamburger buns, lettuce, tomato');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Blue Cheese Stuffed Burgers', 'red onion', 'https://www.allrecipes.com/recipe/238746/blue-cheese-stuffed-burgers/', " +
                "'olive oil, salt, pepper, mushrooms, red onion, ground beef, worcestershire sauce, blue cheese, hamburger buns, lettuce, tomato');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Blue Cheese Stuffed Burgers', 'ground beef', 'https://www.allrecipes.com/recipe/238746/blue-cheese-stuffed-burgers/', " +
                "'olive oil, salt, pepper, mushrooms, red onion, ground beef, worcestershire sauce, blue cheese, hamburger buns, lettuce, tomato');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Blue Cheese Stuffed Burgers', 'blue cheese', 'https://www.allrecipes.com/recipe/238746/blue-cheese-stuffed-burgers/', " +
                "'olive oil, salt, pepper, mushrooms, red onion, ground beef, worcestershire sauce, blue cheese, hamburger buns, lettuce, tomato');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Blue Cheese Stuffed Burgers', 'lettuce', 'https://www.allrecipes.com/recipe/238746/blue-cheese-stuffed-burgers/', " +
                "'olive oil, salt, pepper, mushrooms, red onion, ground beef, worcestershire sauce, blue cheese, hamburger buns, lettuce, tomato');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Blue Cheese Stuffed Burgers', 'tomato', 'https://www.allrecipes.com/recipe/238746/blue-cheese-stuffed-burgers/', " +
                "'olive oil, salt, pepper, mushrooms, red onion, ground beef, worcestershire sauce, blue cheese, hamburger buns, lettuce, tomato');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Lil Woody''s Farmers Market Burger', 'mushrooms', 'https://www.allrecipes.com/recipe/255192/lil-woodys-farmers-market-burger/', " +
                "'salt, porcini mushrooms, thyme, garlic powder, pepper, red pepper flakes, egg yolks, lemon, garlic, dill, canola oil, olive oil, red onion, red wine vinegar, white sugar, brioche buns, ground beef, goat cheese, heirloom tomatoes, arugula');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Lil Woody''s Farmers Market Burger', 'lemon', 'https://www.allrecipes.com/recipe/255192/lil-woodys-farmers-market-burger/', " +
                "'salt, porcini mushrooms, thyme, garlic powder, pepper, red pepper flakes, egg yolks, lemon, garlic, dill, canola oil, olive oil, red onion, red wine vinegar, white sugar, brioche buns, ground beef, goat cheese, heirloom tomatoes, arugula');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Lil Woody''s Farmers Market Burger', 'eggs', 'https://www.allrecipes.com/recipe/255192/lil-woodys-farmers-market-burger/', " +
                "'salt, porcini mushrooms, thyme, garlic powder, pepper, red pepper flakes, egg yolks, lemon, garlic, dill, canola oil, olive oil, red onion, red wine vinegar, white sugar, brioche buns, ground beef, goat cheese, heirloom tomatoes, arugula');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Lil Woody''s Farmers Market Burger', 'red onion', 'https://www.allrecipes.com/recipe/255192/lil-woodys-farmers-market-burger/', " +
                "'salt, porcini mushrooms, thyme, garlic powder, pepper, red pepper flakes, egg yolks, lemon, garlic, dill, canola oil, olive oil, red onion, red wine vinegar, white sugar, brioche buns, ground beef, goat cheese, heirloom tomatoes, arugula');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Lil Woody''s Farmers Market Burger', 'ground beef', 'https://www.allrecipes.com/recipe/255192/lil-woodys-farmers-market-burger/', " +
                "'salt, porcini mushrooms, thyme, garlic powder, pepper, red pepper flakes, egg yolks, lemon, garlic, dill, canola oil, olive oil, red onion, red wine vinegar, white sugar, brioche buns, ground beef, goat cheese, heirloom tomatoes, arugula');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Lil Woody''s Farmers Market Burger', 'goat cheese', 'https://www.allrecipes.com/recipe/255192/lil-woodys-farmers-market-burger/', " +
                "'salt, porcini mushrooms, thyme, garlic powder, pepper, red pepper flakes, egg yolks, lemon, garlic, dill, canola oil, olive oil, red onion, red wine vinegar, white sugar, brioche buns, ground beef, goat cheese, heirloom tomatoes, arugula');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Lil Woody''s Farmers Market Burger', 'arugula', 'https://www.allrecipes.com/recipe/255192/lil-woodys-farmers-market-burger/', " +
                "'salt, porcini mushrooms, thyme, garlic powder, pepper, red pepper flakes, egg yolks, lemon, garlic, dill, canola oil, olive oil, red onion, red wine vinegar, white sugar, brioche buns, ground beef, goat cheese, heirloom tomatoes, arugula');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Lil Woody''s Farmers Market Burger', 'tomato', 'https://www.allrecipes.com/recipe/255192/lil-woodys-farmers-market-burger/', " +
                "'salt, porcini mushrooms, thyme, garlic powder, pepper, red pepper flakes, egg yolks, lemon, garlic, dill, canola oil, olive oil, red onion, red wine vinegar, white sugar, brioche buns, ground beef, goat cheese, heirloom tomatoes, arugula');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Cherry Bourbon Burgers', 'ground venison', 'https://www.allrecipes.com/recipe/246211/cherry-bourbon-burgers/', " +
                "'ground venison, ground beef, cherry flavored Kentucky bourbon whiskey, bacon, pepper, barbeque sauce, pepper jack cheese');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Cherry Bourbon Burgers', 'ground beef', 'https://www.allrecipes.com/recipe/246211/cherry-bourbon-burgers/', " +
                "'ground venison, ground beef, cherry flavored Kentucky bourbon whiskey, bacon, pepper, barbeque sauce, pepper jack cheese');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Cherry Bourbon Burgers', 'bacon', 'https://www.allrecipes.com/recipe/246211/cherry-bourbon-burgers/', " +
                "'ground venison, ground beef, cherry flavored Kentucky bourbon whiskey, bacon, pepper, barbeque sauce, pepper jack cheese');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Cherry Bourbon Burgers', 'pepper jack cheese', 'https://www.allrecipes.com/recipe/246211/cherry-bourbon-burgers/', " +
                "'ground venison, ground beef, cherry flavored Kentucky bourbon whiskey, bacon, pepper, barbeque sauce, pepper jack cheese');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Dragon''s Breath Steak Burgers', 'ground beef', 'https://www.allrecipes.com/recipe/257767/dragons-breath-steak-burgers/', " +
                "'lean ground beef, worcestershire sauce, diced onion, garlic, gouda cheese, hamburger buns');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Dragon''s Breath Steak Burgers', 'yellow onion', 'https://www.allrecipes.com/recipe/257767/dragons-breath-steak-burgers/', " +
                "'lean ground beef, worcestershire sauce, diced onion, garlic, gouda cheese, hamburger buns');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Dragon''s Breath Steak Burgers', 'gouda cheese', 'https://www.allrecipes.com/recipe/257767/dragons-breath-steak-burgers/', " +
                "'lean ground beef, worcestershire sauce, diced onion, garlic, gouda cheese, hamburger buns');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Mo Bros Burgers', 'ground beef', 'https://www.allrecipes.com/recipe/269694/mo-bros-burgers/', " +
                "'ground beef, worcestershire sauce, italian bread crumbs, steak sauce, liquid smoke, sharp cheddar cheese, hamburger buns');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Mo Bros Burgers', 'cheddar cheese', 'https://www.allrecipes.com/recipe/269694/mo-bros-burgers/', " +
                "'ground beef, worcestershire sauce, italian bread crumbs, steak sauce, liquid smoke, sharp cheddar cheese, hamburger buns');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Gourmet Bacon, Onion, and Mushroom Burgers', 'bacon', 'https://www.allrecipes.com/recipe/245715/gourmet-bacon-onion-and-mushroom-burgers/', " +
                "'thick cut bacon, yellow onion, baby bella mushrooms, red wine, hamburger buns, ground beef, sea salt, garlic powder, pepper, pepper jack cheese');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Gourmet Bacon, Onion, and Mushroom Burgers', 'yellow onion', 'https://www.allrecipes.com/recipe/245715/gourmet-bacon-onion-and-mushroom-burgers/', " +
                "'thick cut bacon, yellow onion, baby bella mushrooms, red wine, hamburger buns, ground beef, sea salt, garlic powder, pepper, pepper jack cheese');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Gourmet Bacon, Onion, and Mushroom Burgers', 'mushrooms', 'https://www.allrecipes.com/recipe/245715/gourmet-bacon-onion-and-mushroom-burgers/', " +
                "'thick cut bacon, yellow onion, baby bella mushrooms, red wine, hamburger buns, ground beef, sea salt, garlic powder, pepper, pepper jack cheese');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Gourmet Bacon, Onion, and Mushroom Burgers', 'ground beef', 'https://www.allrecipes.com/recipe/245715/gourmet-bacon-onion-and-mushroom-burgers/', " +
                "'thick cut bacon, yellow onion, baby bella mushrooms, red wine, hamburger buns, ground beef, sea salt, garlic powder, pepper, pepper jack cheese');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Gourmet Bacon, Onion, and Mushroom Burgers', 'pepper jack cheese', 'https://www.allrecipes.com/recipe/245715/gourmet-bacon-onion-and-mushroom-burgers/', " +
                "'thick cut bacon, yellow onion, baby bella mushrooms, red wine, hamburger buns, ground beef, sea salt, garlic powder, pepper, pepper jack cheese');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Bold Honey Barbeque Burgers', 'ground beef', 'https://www.allrecipes.com/recipe/255681/bold-honey-barbecue-burger/', " +
                "'ground beef, green onions, garlic powder, cayenne pepper, steak seasoning, hamburger buns, butter, barbeque sauce, honey, bacon, onion rings, american cheese, tomatoes, lettuce');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Bold Honey Barbeque Burgers', 'butter', 'https://www.allrecipes.com/recipe/255681/bold-honey-barbecue-burger/', " +
                "'ground beef, green onions, garlic powder, cayenne pepper, steak seasoning, hamburger buns, butter, barbeque sauce, honey, bacon, onion rings, american cheese, tomatoes, lettuce');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Bold Honey Barbeque Burgers', 'honey', 'https://www.allrecipes.com/recipe/255681/bold-honey-barbecue-burger/', " +
                "'ground beef, green onions, garlic powder, cayenne pepper, steak seasoning, hamburger buns, butter, barbeque sauce, honey, bacon, onion rings, american cheese, tomatoes, lettuce');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Bold Honey Barbeque Burgers', 'bacon', 'https://www.allrecipes.com/recipe/255681/bold-honey-barbecue-burger/', " +
                "'ground beef, green onions, garlic powder, cayenne pepper, steak seasoning, hamburger buns, butter, barbeque sauce, honey, bacon, onion rings, american cheese, tomatoes, lettuce');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Bold Honey Barbeque Burgers', 'american cheese', 'https://www.allrecipes.com/recipe/255681/bold-honey-barbecue-burger/', " +
                "'ground beef, green onions, garlic powder, cayenne pepper, steak seasoning, hamburger buns, butter, barbeque sauce, honey, bacon, onion rings, american cheese, tomatoes, lettuce');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Bold Honey Barbeque Burgers', 'tomato', 'https://www.allrecipes.com/recipe/255681/bold-honey-barbecue-burger/', " +
                "'ground beef, green onions, garlic powder, cayenne pepper, steak seasoning, hamburger buns, butter, barbeque sauce, honey, bacon, onion rings, american cheese, tomatoes, lettuce');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Bold Honey Barbeque Burgers', 'lettuce', 'https://www.allrecipes.com/recipe/255681/bold-honey-barbecue-burger/', " +
                "'ground beef, green onions, garlic powder, cayenne pepper, steak seasoning, hamburger buns, butter, barbeque sauce, honey, bacon, onion rings, american cheese, tomatoes, lettuce');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Beach House Burgers', 'ground beef', 'https://www.allrecipes.com/recipe/240470/beach-house-burgers/', " +
                "'ground sirloin, ground beef, yellow onion, salt, pepper, tomatoes, fresh basil, mozzarella cheese, hamburger buns');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Beach House Burgers', 'ground sirloin', 'https://www.allrecipes.com/recipe/240470/beach-house-burgers/', " +
                "'ground sirloin, ground beef, yellow onion, salt, pepper, tomatoes, fresh basil, mozzarella cheese, hamburger buns');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Beach House Burgers', 'yellow onion', 'https://www.allrecipes.com/recipe/240470/beach-house-burgers/', " +
                "'ground sirloin, ground beef, yellow onion, salt, pepper, tomatoes, fresh basil, mozzarella cheese, hamburger buns');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Beach House Burgers', 'tomato', 'https://www.allrecipes.com/recipe/240470/beach-house-burgers/', " +
                "'ground sirloin, ground beef, yellow onion, salt, pepper, tomatoes, fresh basil, mozzarella cheese, hamburger buns');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Beach House Burgers', 'mozzarella cheese', 'https://www.allrecipes.com/recipe/240470/beach-house-burgers/', " +
                "'ground sirloin, ground beef, yellow onion, salt, pepper, tomatoes, fresh basil, mozzarella cheese, hamburger buns');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Deluxe Olive Stuffed Burgers', 'ground beef', 'https://www.allrecipes.com/recipe/258962/deluxe-olive-stuffed-burgers/', " +
                "'ground beef, garlic powder, pepper, green olives, cheddar cheese, hamburger buns, mayonnaise, tomato, onion, lettuce');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Deluxe Olive Stuffed Burgers', 'mayonnaise', 'https://www.allrecipes.com/recipe/258962/deluxe-olive-stuffed-burgers/', " +
                "'ground beef, garlic powder, pepper, green olives, cheddar cheese, hamburger buns, mayonnaise, tomato, onion, lettuce');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Deluxe Olive Stuffed Burgers', 'tomato', 'https://www.allrecipes.com/recipe/258962/deluxe-olive-stuffed-burgers/', " +
                "'ground beef, garlic powder, pepper, green olives, cheddar cheese, hamburger buns, mayonnaise, tomato, onion, lettuce');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Deluxe Olive Stuffed Burgers', 'onion', 'https://www.allrecipes.com/recipe/258962/deluxe-olive-stuffed-burgers/', " +
                "'ground beef, garlic powder, pepper, green olives, cheddar cheese, hamburger buns, mayonnaise, tomato, onion, lettuce');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Deluxe Olive Stuffed Burgers', 'lettuce', 'https://www.allrecipes.com/recipe/258962/deluxe-olive-stuffed-burgers/', " +
                "'ground beef, garlic powder, pepper, green olives, cheddar cheese, hamburger buns, mayonnaise, tomato, onion, lettuce');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Jean''s Bacon Jalapeno Cream Cheese Stuffed Burgers', 'bacon', 'https://www.allrecipes.com/recipe/258963/jeans-bacon-jalapeno-cream-cheese-stuffed-burgers/', " +
                "'bacon, cream cheese, jalapeno, salt, ground beef, rolled oats, hamburger buns');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Jean''s Bacon Jalapeno Cream Cheese Stuffed Burgers', 'cream cheese', 'https://www.allrecipes.com/recipe/258963/jeans-bacon-jalapeno-cream-cheese-stuffed-burgers/', " +
                "'bacon, cream cheese, jalapeno, salt, ground beef, rolled oats, hamburger buns');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Jean''s Bacon Jalapeno Cream Cheese Stuffed Burgers', 'jalapeno', 'https://www.allrecipes.com/recipe/258963/jeans-bacon-jalapeno-cream-cheese-stuffed-burgers/', " +
                "'bacon, cream cheese, jalapeno, salt, ground beef, rolled oats, hamburger buns');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Jean''s Bacon Jalapeno Cream Cheese Stuffed Burgers', 'ground beef', 'https://www.allrecipes.com/recipe/258963/jeans-bacon-jalapeno-cream-cheese-stuffed-burgers/', " +
                "'bacon, cream cheese, jalapeno, salt, ground beef, rolled oats, hamburger buns');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Rosemary Crusted Oxtail Burgers', 'butter', 'https://www.allrecipes.com/recipe/266472/rosemary-crusted-oxtail-burger/', " +
                "'olive oil, butter, large onion, garlic, water, oxtail, carrots, bay leaves, rosemary leaves, sea salt, pepper, coriander seeds, smoked paprika, ground beef, bread crumbs, mustard, ketchup, hot pepper sauce');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Rosemary Crusted Oxtail Burgers', 'yellow onion', 'https://www.allrecipes.com/recipe/266472/rosemary-crusted-oxtail-burger/', " +
                "'olive oil, butter, large onion, garlic, water, oxtail, carrots, bay leaves, rosemary leaves, sea salt, pepper, coriander seeds, smoked paprika, ground beef, bread crumbs, mustard, ketchup, hot pepper sauce');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Rosemary Crusted Oxtail Burgers', 'carrots', 'https://www.allrecipes.com/recipe/266472/rosemary-crusted-oxtail-burger/', " +
                "'olive oil, butter, large onion, garlic, water, oxtail, carrots, bay leaves, rosemary leaves, sea salt, pepper, coriander seeds, smoked paprika, ground beef, bread crumbs, mustard, ketchup, hot pepper sauce');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Rosemary Crusted Oxtail Burgers', 'oxrail', 'https://www.allrecipes.com/recipe/266472/rosemary-crusted-oxtail-burger/', " +
                "'olive oil, butter, large onion, garlic, water, oxtail, carrots, bay leaves, rosemary leaves, sea salt, pepper, coriander seeds, smoked paprika, ground beef, bread crumbs, mustard, ketchup, hot pepper sauce');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Rosemary Crusted Oxtail Burgers', 'ground beef', 'https://www.allrecipes.com/recipe/266472/rosemary-crusted-oxtail-burger/', " +
                "'olive oil, butter, large onion, garlic, water, oxtail, carrots, bay leaves, rosemary leaves, sea salt, pepper, coriander seeds, smoked paprika, ground beef, bread crumbs, mustard, ketchup, hot pepper sauce');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Blue Cheese Fig Burgers', 'sweet onion', 'https://www.allrecipes.com/recipe/254546/blue-cheese-fig-burgers/', " +
                "'olive oil, sweet onion, fig preserves, ground beef, salt, pepper, garlic powder, worcestershire sauce, blue cheese, hamburger buns, lettuce, tomatoes');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Blue Cheese Fig Burgers', 'ground beef', 'https://www.allrecipes.com/recipe/254546/blue-cheese-fig-burgers/', " +
                "'olive oil, sweet onion, fig preserves, ground beef, salt, pepper, garlic powder, worcestershire sauce, blue cheese, hamburger buns, lettuce, tomatoes');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Blue Cheese Fig Burgers', 'blue cheese', 'https://www.allrecipes.com/recipe/254546/blue-cheese-fig-burgers/', " +
                "'olive oil, sweet onion, fig preserves, ground beef, salt, pepper, garlic powder, worcestershire sauce, blue cheese, hamburger buns, lettuce, tomatoes');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Blue Cheese Fig Burgers', 'lettuce', 'https://www.allrecipes.com/recipe/254546/blue-cheese-fig-burgers/', " +
                "'olive oil, sweet onion, fig preserves, ground beef, salt, pepper, garlic powder, worcestershire sauce, blue cheese, hamburger buns, lettuce, tomatoes');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Blue Cheese Fig Burgers', 'tomato', 'https://www.allrecipes.com/recipe/254546/blue-cheese-fig-burgers/', " +
                "'olive oil, sweet onion, fig preserves, ground beef, salt, pepper, garlic powder, worcestershire sauce, blue cheese, hamburger buns, lettuce, tomatoes');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Carolina Barbeque Burgers', 'ground beef', 'https://www.allrecipes.com/recipe/254521/carolina-barbecue-burgers/', " +
                "'ground beef, brown sugar, beer, yellow mustard, cider vinegar, cabbage, hamburger buns');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Carolina Barbeque Burgers', 'cabbage', 'https://www.allrecipes.com/recipe/254521/carolina-barbecue-burgers/', " +
                "'ground beef, brown sugar, beer, yellow mustard, cider vinegar, cabbage, hamburger buns');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Jan''s Blueberry and Flax Burgers', 'blueberries', 'https://www.allrecipes.com/recipe/246031/jans-blueberry-and-flax-burgers-paleo/', " +
                "'blueberries, ground beef, flax seed meal, sea salt, pepper');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Jan''s Blueberry and Flax Burgers', 'ground beef', 'https://www.allrecipes.com/recipe/246031/jans-blueberry-and-flax-burgers-paleo/', " +
                "'blueberries, ground beef, flax seed meal, sea salt, pepper');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Wolfgang Puck''s Cheeseburger Sliders', 'mayonnaise', 'https://www.allrecipes.com/recipe/269896/wolfgang-pucks-cheeseburger-sliders/', " +
                "'mayonnaise, red onion, ketchup, sherry vinegar, capers, parsley, thyme, white sugar, ground beef, kosher salt, pepper, olive oil, cheddar cheese, brioche buns, iceberg lettuce, tomatoes, cornichons');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Wolfgang Puck''s Cheeseburger Sliders', 'red onion', 'https://www.allrecipes.com/recipe/269896/wolfgang-pucks-cheeseburger-sliders/', " +
                "'mayonnaise, red onion, ketchup, sherry vinegar, capers, parsley, thyme, white sugar, ground beef, kosher salt, pepper, olive oil, cheddar cheese, brioche buns, iceberg lettuce, tomatoes, cornichons');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Wolfgang Puck''s Cheeseburger Sliders', 'ground beef', 'https://www.allrecipes.com/recipe/269896/wolfgang-pucks-cheeseburger-sliders/', " +
                "'mayonnaise, red onion, ketchup, sherry vinegar, capers, parsley, thyme, white sugar, ground beef, kosher salt, pepper, olive oil, cheddar cheese, brioche buns, iceberg lettuce, tomatoes, cornichons');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Wolfgang Puck''s Cheeseburger Sliders', 'cheddar cheese', 'https://www.allrecipes.com/recipe/269896/wolfgang-pucks-cheeseburger-sliders/', " +
                "'mayonnaise, red onion, ketchup, sherry vinegar, capers, parsley, thyme, white sugar, ground beef, kosher salt, pepper, olive oil, cheddar cheese, brioche buns, iceberg lettuce, tomatoes, cornichons');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Wolfgang Puck''s Cheeseburger Sliders', 'lettuce', 'https://www.allrecipes.com/recipe/269896/wolfgang-pucks-cheeseburger-sliders/', " +
                "'mayonnaise, red onion, ketchup, sherry vinegar, capers, parsley, thyme, white sugar, ground beef, kosher salt, pepper, olive oil, cheddar cheese, brioche buns, iceberg lettuce, tomatoes, cornichons');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Wolfgang Puck''s Cheeseburger Sliders', 'tomato', 'https://www.allrecipes.com/recipe/269896/wolfgang-pucks-cheeseburger-sliders/', " +
                "'mayonnaise, red onion, ketchup, sherry vinegar, capers, parsley, thyme, white sugar, ground beef, kosher salt, pepper, olive oil, cheddar cheese, brioche buns, iceberg lettuce, tomatoes, cornichons');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Wolfgang Puck''s Cheeseburger Sliders', 'pickles', 'https://www.allrecipes.com/recipe/269896/wolfgang-pucks-cheeseburger-sliders/', " +
                "'mayonnaise, red onion, ketchup, sherry vinegar, capers, parsley, thyme, white sugar, ground beef, kosher salt, pepper, olive oil, cheddar cheese, brioche buns, iceberg lettuce, tomatoes, cornichons');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('The Real Deal Korean Beef Ribs', 'beef ribs', 'https://www.allrecipes.com/recipe/158229/the-real-deal-korean-beef-ribs/', " +
                "'korean style short ribs, soy sauce, white sugar, honey, garlic, pepper, water, sesame oil, Asian plum wine');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('The Real Deal Korean Beef Ribs', 'honey', 'https://www.allrecipes.com/recipe/158229/the-real-deal-korean-beef-ribs/', " +
                "'korean style short ribs, soy sauce, white sugar, honey, garlic, pepper, water, sesame oil, Asian plum wine');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('WV''s Finest Boneless Ribs', 'beef ribs', 'https://www.allrecipes.com/recipe/76581/wvs-finest-boneless-ribs/', " +
                "'boneless beef short ribs, chili sauce, liquid smoke, yellow onion, garlic, yellow mustard, honey mustard, worcestershire sauce, steak sauce, celery seed, salt, pepper');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('WV''s Finest Boneless Ribs', 'yellow onion', 'https://www.allrecipes.com/recipe/76581/wvs-finest-boneless-ribs/', " +
                "'boneless beef short ribs, chili sauce, liquid smoke, yellow onion, garlic, yellow mustard, honey mustard, worcestershire sauce, steak sauce, celery seed, salt, pepper');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Ribs Fantastic', 'beef ribs', 'https://www.allrecipes.com/recipe/30834/ribs-fantastic/', " +
                "'beef short ribs, brown sugar, ketchup, water, apple cider vinegar, lemon juice, celery, yellow onion, salt, pepper');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Ribs Fantastic', 'lemon', 'https://www.allrecipes.com/recipe/30834/ribs-fantastic/', " +
                "'beef short ribs, brown sugar, ketchup, water, apple cider vinegar, lemon juice, celery, yellow onion, salt, pepper');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Ribs Fantastic', 'yellow onion', 'https://www.allrecipes.com/recipe/30834/ribs-fantastic/', " +
                "'beef short ribs, brown sugar, ketchup, water, apple cider vinegar, lemon juice, celery, yellow onion, salt, pepper');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Ribs Fantastic', 'celery', 'https://www.allrecipes.com/recipe/30834/ribs-fantastic/', " +
                "'beef short ribs, brown sugar, ketchup, water, apple cider vinegar, lemon juice, celery, yellow onion, salt, pepper');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Grilled Korean Style Beef Short Ribs', 'beef ribs', 'https://www.allrecipes.com/recipe/221226/grilled-korean-style-beef-short-ribs/', " +
                "'Asian pear, sherry wine, soy sauce, rice vinegar, brown sugar, garlic, ginger, hoisin sauce, hot chile paste, sesame oil, beef short ribs, green onion');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Grilled Korean Style Beef Short Ribs', 'asian pear', 'https://www.allrecipes.com/recipe/221226/grilled-korean-style-beef-short-ribs/', " +
                "'Asian pear, sherry wine, soy sauce, rice vinegar, brown sugar, garlic, ginger, hoisin sauce, hot chile paste, sesame oil, beef short ribs, green onion');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Argentinean Style Ribs', 'beef ribs', 'https://www.allrecipes.com/recipe/52272/argentinean-style-ribs/', " +
                "'coarse salt, beef short ribs, lime');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Argentinean Style Ribs', 'lime', 'https://www.allrecipes.com/recipe/52272/argentinean-style-ribs/', " +
                "'coarse salt, beef short ribs, lime');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Kalbi Korean BBQ Short Ribs', 'beef ribs', 'https://www.allrecipes.com/recipe/96698/kalbi-korean-bbq-short-ribs/', " +
                "'soy sauce, brown sugar, water, garlic, green onion, sesame oil, Korean style short ribs');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Grilled BBQ Short Ribs With Dry Rub', 'beef ribs', 'https://www.allrecipes.com/recipe/238139/grilled-bbq-short-ribs-with-dry-rub/', " +
                "'brown sugar, paprika, chili powder, salt, garlic powder, beef short ribs, ice cubes, barbeque sauce, aluminum foil');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Big John''s BBQ Ribs And Dry Spice Rub', 'rib roast', 'https://www.allrecipes.com/recipe/20971/big-johns-bbq-ribs-and-dry-spice-rub/', " +
                "'chili powder, minced garlic, onion powder, cumin, salt, seasoning salt, rib roast, tomato sauce, brown sugar, tomato, worcestershire sauce, onion flakes, soy sauce, water');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Big John''s BBQ Ribs And Dry Spice Rub', 'tomato', 'https://www.allrecipes.com/recipe/20971/big-johns-bbq-ribs-and-dry-spice-rub/', " +
                "'chili powder, minced garlic, onion powder, cumin, salt, seasoning salt, rib roast, tomato sauce, brown sugar, tomato, worcestershire sauce, onion flakes, soy sauce, water');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Grilled Kiwi And Chili Rubbed Short Ribs', 'kiwi', 'https://www.allrecipes.com/recipe/266646/grilled-kiwi-and-chili-rubbed-short-ribs/', " +
                "'kiwi, Korean style short ribs, ancho chili powder, white sugar, salt, pepper, garlic powder, cayenne pepper');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Grilled Kiwi And Chili Rubbed Short Ribs', 'beef ribs', 'https://www.allrecipes.com/recipe/266646/grilled-kiwi-and-chili-rubbed-short-ribs/', " +
                "'kiwi, Korean style short ribs, ancho chili powder, white sugar, salt, pepper, garlic powder, cayenne pepper');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Jane''s Barbequed Short Ribs', 'beef ribs', 'https://www.allrecipes.com/recipe/230388/janes-barbecued-short-ribs/', " +
                "'bone in beef short ribs, yellow onion, tomato sauce, water, cider vinegar, white sugar, parsley, horseradish, mustard, salt, pepper');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Jane''s Barbequed Short Ribs', 'yellow onion', 'https://www.allrecipes.com/recipe/230388/janes-barbecued-short-ribs/', " +
                "'bone in beef short ribs, yellow onion, tomato sauce, water, cider vinegar, white sugar, parsley, horseradish, mustard, salt, pepper');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Las Vegas Galbi Korean Style Beef Ribs', 'beef ribs', 'https://www.allrecipes.com/recipe/262012/las-vegas-galbi-korean-style-beef-ribs/', " +
                "'beef short ribs, Asian pear, white onion, kiwi, garlic, soy sauce, brown sugar, honey, sesame oil, pepper');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Las Vegas Galbi Korean Style Beef Ribs', 'asian  pear', 'https://www.allrecipes.com/recipe/262012/las-vegas-galbi-korean-style-beef-ribs/', " +
                "'beef short ribs, Asian pear, white onion, kiwi, garlic, soy sauce, brown sugar, honey, sesame oil, pepper');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Las Vegas Galbi Korean Style Beef Ribs', 'white onion', 'https://www.allrecipes.com/recipe/262012/las-vegas-galbi-korean-style-beef-ribs/', " +
                "'beef short ribs, Asian pear, white onion, kiwi, garlic, soy sauce, brown sugar, honey, sesame oil, pepper');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Las Vegas Galbi Korean Style Beef Ribs', 'kiwi', 'https://www.allrecipes.com/recipe/262012/las-vegas-galbi-korean-style-beef-ribs/', " +
                "'beef short ribs, Asian pear, white onion, kiwi, garlic, soy sauce, brown sugar, honey, sesame oil, pepper');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Las Vegas Galbi Korean Style Beef Ribs', 'honey', 'https://www.allrecipes.com/recipe/262012/las-vegas-galbi-korean-style-beef-ribs/', " +
                "'beef short ribs, Asian pear, white onion, kiwi, garlic, soy sauce, brown sugar, honey, sesame oil, pepper');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Kafta BBQ', 'ground beef', 'https://www.allrecipes.com/recipe/64519/kafta-bbq/', " +
                "'ground beef, yellow onion, fresh parsley, cayenne pepper, allspice, salt, pepper, skewers');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Kafta BBQ', 'yellow onion', 'https://www.allrecipes.com/recipe/64519/kafta-bbq/', " +
                "'ground beef, yellow onion, fresh parsley, cayenne pepper, allspice, salt, pepper, skewers');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Cuban Beef and Zucchini Kebabs With Mojo Sauce', 'steak', 'https://www.allrecipes.com/recipe/247241/cuban-beef-and-zucchini-kebabs-with-mojo-sauce/', " +
                "'garlic, salt, orange juice, lime juice, olive oil, cumin, oregano, sirloin steak, wooden skewers, salt, pepper, zucchini');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Cuban Beef and Zucchini Kebabs With Mojo Sauce', 'orange', 'https://www.allrecipes.com/recipe/247241/cuban-beef-and-zucchini-kebabs-with-mojo-sauce/', " +
                "'garlic, salt, orange juice, lime juice, olive oil, cumin, oregano, sirloin steak, wooden skewers, salt, pepper, zucchini');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Cuban Beef and Zucchini Kebabs With Mojo Sauce', 'lime', 'https://www.allrecipes.com/recipe/247241/cuban-beef-and-zucchini-kebabs-with-mojo-sauce/', " +
                "'garlic, salt, orange juice, lime juice, olive oil, cumin, oregano, sirloin steak, wooden skewers, salt, pepper, zucchini');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Cuban Beef and Zucchini Kebabs With Mojo Sauce', 'zucchini', 'https://www.allrecipes.com/recipe/247241/cuban-beef-and-zucchini-kebabs-with-mojo-sauce/', " +
                "'garlic, salt, orange juice, lime juice, olive oil, cumin, oregano, sirloin steak, wooden skewers, salt, pepper, zucchini');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Triestine Cevapcici', 'ground beef', 'https://www.allrecipes.com/recipe/138155/triestine-cevapcici/', " +
                "'ground beef, ground pork, parsley, garlic, salt, pepper, hot paprika, nutmeg, egg white, red wine, olive oil');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Triestine Cevapcici', 'ground pork', 'https://www.allrecipes.com/recipe/138155/triestine-cevapcici/', " +
                "'ground beef, ground pork, parsley, garlic, salt, pepper, hot paprika, nutmeg, egg white, red wine, olive oil');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Triestine Cevapcici', 'eggs', 'https://www.allrecipes.com/recipe/138155/triestine-cevapcici/', " +
                "'ground beef, ground pork, parsley, garlic, salt, pepper, hot paprika, nutmeg, egg white, red wine, olive oil');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Ugandan Kabobs', 'yogurt', 'https://www.allrecipes.com/recipe/14627/ugandan-kabobs/', " +
                "'plain yogurt, salt, jalapeno, fresh parsley, whole wheat bread, eggs, worcestershire sauce, dried bread crumbs, french fried onions, garlic, ginger, cumin, coriander, ground beef, vegetable oil');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Ugandan Kabobs', 'jalapeno', 'https://www.allrecipes.com/recipe/14627/ugandan-kabobs/', " +
                "'plain yogurt, salt, jalapeno, fresh parsley, whole wheat bread, eggs, worcestershire sauce, dried bread crumbs, french fried onions, garlic, ginger, cumin, coriander, ground beef, vegetable oil');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Ugandan Kabobs', 'eggs', 'https://www.allrecipes.com/recipe/14627/ugandan-kabobs/', " +
                "'plain yogurt, salt, jalapeno, fresh parsley, whole wheat bread, eggs, worcestershire sauce, dried bread crumbs, french fried onions, garlic, ginger, cumin, coriander, ground beef, vegetable oil');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Ugandan Kabobs', 'ground beef', 'https://www.allrecipes.com/recipe/14627/ugandan-kabobs/', " +
                "'plain yogurt, salt, jalapeno, fresh parsley, whole wheat bread, eggs, worcestershire sauce, dried bread crumbs, french fried onions, garlic, ginger, cumin, coriander, ground beef, vegetable oil');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Ugandan Kabobs', 'bread', 'https://www.allrecipes.com/recipe/14627/ugandan-kabobs/', " +
                "'plain yogurt, salt, jalapeno, fresh parsley, whole wheat bread, eggs, worcestershire sauce, dried bread crumbs, french fried onions, garlic, ginger, cumin, coriander, ground beef, vegetable oil');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Espetadas Portuguese Beef Shish Kabobs', 'steak', 'https://www.allrecipes.com/recipe/218484/espetadas-portuguese-beef-shish-kabobs/', " +
                "'red wine, garlic, bay leaves, coarse salt, pepper, sirloin steak, bamboo skewers');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Mango Spiced Steak Skewers', 'steak', 'https://www.allrecipes.com/recipe/160106/mango-spiced-steak-skewers/', " +
                "'dijon mustard, honey, mango preserves, apple juice, teriyaki sauce, garlic, cayenne pepper, salt, pepper, olive oil, flank steak, bamboo skewers');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Mango Spiced Steak Skewers', 'honey', 'https://www.allrecipes.com/recipe/160106/mango-spiced-steak-skewers/', " +
                "'dijon mustard, honey, mango preserves, apple juice, teriyaki sauce, garlic, cayenne pepper, salt, pepper, olive oil, flank steak, bamboo skewers');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Indonesia Sate Meat Kabobs', 'steak', 'https://www.allrecipes.com/recipe/165961/indonesia-sate-meat-kabobs/', " +
                "'onion, garlic, kecap manis, coriander, cumin, sambal oelek, red wine, water, lemon grass, sirloin steak');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Indonesia Sate Meat Kabobs', 'yellow onion', 'https://www.allrecipes.com/recipe/165961/indonesia-sate-meat-kabobs/', " +
                "'onion, garlic, kecap manis, coriander, cumin, sambal oelek, red wine, water, lemon grass, sirloin steak');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Grilled Teriyaki Sirloin Steak Kabobs', 'steak', 'https://www.allrecipes.com/recipe/242501/grilled-teriyaki-sirloin-steak-kabobs/', " +
                "'soy sauce, white wine vinegar, white sugar, vegetable oil, garlic, ginger, sirloin steak, yellow onion, cherry tomatoes, white mushrooms, green bell pepper, skewers');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Grilled Teriyaki Sirloin Steak Kabobs', 'yellow onion', 'https://www.allrecipes.com/recipe/242501/grilled-teriyaki-sirloin-steak-kabobs/', " +
                "'soy sauce, white wine vinegar, white sugar, vegetable oil, garlic, ginger, sirloin steak, yellow onion, cherry tomatoes, white mushrooms, green bell pepper, skewers');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Grilled Teriyaki Sirloin Steak Kabobs', 'cherry tomato', 'https://www.allrecipes.com/recipe/242501/grilled-teriyaki-sirloin-steak-kabobs/', " +
                "'soy sauce, white wine vinegar, white sugar, vegetable oil, garlic, ginger, sirloin steak, yellow onion, cherry tomatoes, white mushrooms, green bell pepper, skewers');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Grilled Teriyaki Sirloin Steak Kabobs', 'mushrooms', 'https://www.allrecipes.com/recipe/242501/grilled-teriyaki-sirloin-steak-kabobs/', " +
                "'soy sauce, white wine vinegar, white sugar, vegetable oil, garlic, ginger, sirloin steak, yellow onion, cherry tomatoes, white mushrooms, green bell pepper, skewers');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Grilled Teriyaki Sirloin Steak Kabobs', 'bell pepper', 'https://www.allrecipes.com/recipe/242501/grilled-teriyaki-sirloin-steak-kabobs/', " +
                "'soy sauce, white wine vinegar, white sugar, vegetable oil, garlic, ginger, sirloin steak, yellow onion, cherry tomatoes, white mushrooms, green bell pepper, skewers');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Awesome Spicy Beef Kabobs', 'steak', 'https://www.allrecipes.com/recipe/107915/awesome-spicy-beef-kabobs-or-haitian-voodoo-sticks/', " +
                "'beef bouillon, water, garlic, cayenne pepper, salt, pepper, beef sirloin, skewers, vegetable oil');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Asian Beef Skewers', 'steak', 'https://www.allrecipes.com/recipe/14521/asian-beef-skewers/', " +
                "'hoisin sauce, sherry, soy sauce, barbecue sauce, green onions, garlic, ginger, flank steak, skewers');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Shish Kabob A La Holly', 'bell pepper', 'https://www.allrecipes.com/recipe/218613/shish-kabob-a-la-holly/', " +
                "'skewers, green bell pepper, shrimp, zucchini, chicken breast, cherry tomatoes, beef steak, pearl onions, mushrooms, olive oil, garlic powder, salt, pepper, fettuccine, butter');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Shish Kabob A La Holly', 'shrimp', 'https://www.allrecipes.com/recipe/218613/shish-kabob-a-la-holly/', " +
                "'skewers, green bell pepper, shrimp, zucchini, chicken breast, cherry tomatoes, beef steak, pearl onions, mushrooms, olive oil, garlic powder, salt, pepper, fettuccine, butter');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Shish Kabob A La Holly', 'zucchini', 'https://www.allrecipes.com/recipe/218613/shish-kabob-a-la-holly/', " +
                "'skewers, green bell pepper, shrimp, zucchini, chicken breast, cherry tomatoes, beef steak, pearl onions, mushrooms, olive oil, garlic powder, salt, pepper, fettuccine, butter');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Shish Kabob A La Holly', 'chicken', 'https://www.allrecipes.com/recipe/218613/shish-kabob-a-la-holly/', " +
                "'skewers, green bell pepper, shrimp, zucchini, chicken breast, cherry tomatoes, beef steak, pearl onions, mushrooms, olive oil, garlic powder, salt, pepper, fettuccine, butter');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Shish Kabob A La Holly', 'cherry tomato', 'https://www.allrecipes.com/recipe/218613/shish-kabob-a-la-holly/', " +
                "'skewers, green bell pepper, shrimp, zucchini, chicken breast, cherry tomatoes, beef steak, pearl onions, mushrooms, olive oil, garlic powder, salt, pepper, fettuccine, butter');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Shish Kabob A La Holly', 'steak', 'https://www.allrecipes.com/recipe/218613/shish-kabob-a-la-holly/', " +
                "'skewers, green bell pepper, shrimp, zucchini, chicken breast, cherry tomatoes, beef steak, pearl onions, mushrooms, olive oil, garlic powder, salt, pepper, fettuccine, butter');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Shish Kabob A La Holly', 'pearl onion', 'https://www.allrecipes.com/recipe/218613/shish-kabob-a-la-holly/', " +
                "'skewers, green bell pepper, shrimp, zucchini, chicken breast, cherry tomatoes, beef steak, pearl onions, mushrooms, olive oil, garlic powder, salt, pepper, fettuccine, butter');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Shish Kabob A La Holly', 'mushrooms', 'https://www.allrecipes.com/recipe/218613/shish-kabob-a-la-holly/', " +
                "'skewers, green bell pepper, shrimp, zucchini, chicken breast, cherry tomatoes, beef steak, pearl onions, mushrooms, olive oil, garlic powder, salt, pepper, fettuccine, butter');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Shish Kabob A La Holly', 'butter', 'https://www.allrecipes.com/recipe/218613/shish-kabob-a-la-holly/', " +
                "'skewers, green bell pepper, shrimp, zucchini, chicken breast, cherry tomatoes, beef steak, pearl onions, mushrooms, olive oil, garlic powder, salt, pepper, fettuccine, butter');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Hoisin Ginger Beef Skewers', 'steak', 'https://www.allrecipes.com/recipe/186934/hoisin-ginger-beef-skewers/', " +
                "'flank steak, hoisin sauce, lime juice, honey, garlic, salt, ginger, sesame oil, chile-garlic sauce, red pepper flakes, pepper, skewers, sesame seeds, green onion');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Hoisin Ginger Beef Skewers', 'lime', 'https://www.allrecipes.com/recipe/186934/hoisin-ginger-beef-skewers/', " +
                "'flank steak, hoisin sauce, lime juice, honey, garlic, salt, ginger, sesame oil, chile-garlic sauce, red pepper flakes, pepper, skewers, sesame seeds, green onion');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Hoisin Ginger Beef Skewers', 'honey', 'https://www.allrecipes.com/recipe/186934/hoisin-ginger-beef-skewers/', " +
                "'flank steak, hoisin sauce, lime juice, honey, garlic, salt, ginger, sesame oil, chile-garlic sauce, red pepper flakes, pepper, skewers, sesame seeds, green onion');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Red Wine And Orange Marinated Beef Skewers', 'steak', 'https://www.allrecipes.com/recipe/89034/red-wine-and-orange-marinated-beef-skewers/', " +
                "'red wine, steak sauce, oranges, green onion, small red chile peppers, garlic, ground ginger, salt, pepper, beef sirloin steak');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Red Wine And Orange Marinated Beef Skewers', 'orange', 'https://www.allrecipes.com/recipe/89034/red-wine-and-orange-marinated-beef-skewers/', " +
                "'red wine, steak sauce, oranges, green onion, small red chile peppers, garlic, ground ginger, salt, pepper, beef sirloin steak');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Orange Beef Kabobs With Grilled Fruit', 'orange', 'https://www.allrecipes.com/recipe/214077/orange-beef-kabobs-with-grilled-fruit/', " +
                "'plain yogurt, cayenne pepper, ginger, orange zest, top sirloin, red onion, red bell pepper, olive oil, pineapple cubes, orange, butter, brown sugar, rum');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Orange Beef Kabobs With Grilled Fruit', 'yogurt', 'https://www.allrecipes.com/recipe/214077/orange-beef-kabobs-with-grilled-fruit/', " +
                "'plain yogurt, cayenne pepper, ginger, orange zest, top sirloin, red onion, red bell pepper, olive oil, pineapple cubes, orange, butter, brown sugar, rum');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Orange Beef Kabobs With Grilled Fruit', 'red onion', 'https://www.allrecipes.com/recipe/214077/orange-beef-kabobs-with-grilled-fruit/', " +
                "'plain yogurt, cayenne pepper, ginger, orange zest, top sirloin, red onion, red bell pepper, olive oil, pineapple cubes, orange, butter, brown sugar, rum');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Orange Beef Kabobs With Grilled Fruit', 'steak', 'https://www.allrecipes.com/recipe/214077/orange-beef-kabobs-with-grilled-fruit/', " +
                "'plain yogurt, cayenne pepper, ginger, orange zest, top sirloin, red onion, red bell pepper, olive oil, pineapple cubes, orange, butter, brown sugar, rum');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Orange Beef Kabobs With Grilled Fruit', 'bell pepper', 'https://www.allrecipes.com/recipe/214077/orange-beef-kabobs-with-grilled-fruit/', " +
                "'plain yogurt, cayenne pepper, ginger, orange zest, top sirloin, red onion, red bell pepper, olive oil, pineapple cubes, orange, butter, brown sugar, rum');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Orange Beef Kabobs With Grilled Fruit', 'pineapple', 'https://www.allrecipes.com/recipe/214077/orange-beef-kabobs-with-grilled-fruit/', " +
                "'plain yogurt, cayenne pepper, ginger, orange zest, top sirloin, red onion, red bell pepper, olive oil, pineapple cubes, orange, butter, brown sugar, rum');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Orange Beef Kabobs With Grilled Fruit', 'butter', 'https://www.allrecipes.com/recipe/214077/orange-beef-kabobs-with-grilled-fruit/', " +
                "'plain yogurt, cayenne pepper, ginger, orange zest, top sirloin, red onion, red bell pepper, olive oil, pineapple cubes, orange, butter, brown sugar, rum');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Fajitas On A Stick', 'lime', 'https://www.allrecipes.com/recipe/241100/fajitas-on-a-stick/', " +
                "'olive oil, lime, garlic, cumin, red pepper flakes, chipotle powder, beef sirloin, red bell pepper, green bell pepper, yellow onion, skewers');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Fajitas On A Stick', 'steak', 'https://www.allrecipes.com/recipe/241100/fajitas-on-a-stick/', " +
                "'olive oil, lime, garlic, cumin, red pepper flakes, chipotle powder, beef sirloin, red bell pepper, green bell pepper, yellow onion, skewers');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Fajitas On A Stick', 'bell pepper', 'https://www.allrecipes.com/recipe/241100/fajitas-on-a-stick/', " +
                "'olive oil, lime, garlic, cumin, red pepper flakes, chipotle powder, beef sirloin, red bell pepper, green bell pepper, yellow onion, skewers');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Fajitas On A Stick', 'yellow onion', 'https://www.allrecipes.com/recipe/241100/fajitas-on-a-stick/', " +
                "'olive oil, lime, garlic, cumin, red pepper flakes, chipotle powder, beef sirloin, red bell pepper, green bell pepper, yellow onion, skewers');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Mojo Beef Kabobs', 'steak', 'https://www.allrecipes.com/recipe/242300/mojo-beef-kabobs/', " +
                "'top sirloin steak, pepper, lime, red onion, cherry tomatoes, orange juice, lime juice, oregano, olive oil, parsley, cumin, garlic, salt');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Mojo Beef Kabobs', 'lime', 'https://www.allrecipes.com/recipe/242300/mojo-beef-kabobs/', " +
                "'top sirloin steak, pepper, lime, red onion, cherry tomatoes, orange juice, lime juice, oregano, olive oil, parsley, cumin, garlic, salt');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Mojo Beef Kabobs', 'red onion', 'https://www.allrecipes.com/recipe/242300/mojo-beef-kabobs/', " +
                "'top sirloin steak, pepper, lime, red onion, cherry tomatoes, orange juice, lime juice, oregano, olive oil, parsley, cumin, garlic, salt');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Mojo Beef Kabobs', 'cherry tomato', 'https://www.allrecipes.com/recipe/242300/mojo-beef-kabobs/', " +
                "'top sirloin steak, pepper, lime, red onion, cherry tomatoes, orange juice, lime juice, oregano, olive oil, parsley, cumin, garlic, salt');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Mojo Beef Kabobs', 'orange', 'https://www.allrecipes.com/recipe/242300/mojo-beef-kabobs/', " +
                "'top sirloin steak, pepper, lime, red onion, cherry tomatoes, orange juice, lime juice, oregano, olive oil, parsley, cumin, garlic, salt');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Bohemian Kebab Wraps', 'steak', 'https://www.allrecipes.com/recipe/218611/bohemian-kebab-wraps/', " +
                "'beef skirt steak, yellow onion, green bell pepper, red bell pepper, yellow bell pepper, skewers, salt, pepper, paprika, flour tortillas, butter, garlic');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Bohemian Kebab Wraps', 'yellow onion', 'https://www.allrecipes.com/recipe/218611/bohemian-kebab-wraps/', " +
                "'beef skirt steak, yellow onion, green bell pepper, red bell pepper, yellow bell pepper, skewers, salt, pepper, paprika, flour tortillas, butter, garlic');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Bohemian Kebab Wraps', 'bell pepper', 'https://www.allrecipes.com/recipe/218611/bohemian-kebab-wraps/', " +
                "'beef skirt steak, yellow onion, green bell pepper, red bell pepper, yellow bell pepper, skewers, salt, pepper, paprika, flour tortillas, butter, garlic');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Bohemian Kebab Wraps', 'butter', 'https://www.allrecipes.com/recipe/218611/bohemian-kebab-wraps/', " +
                "'beef skirt steak, yellow onion, green bell pepper, red bell pepper, yellow bell pepper, skewers, salt, pepper, paprika, flour tortillas, butter, garlic');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Greek Souzoukaklia', 'ground beef', 'https://www.allrecipes.com/recipe/36161/greek-souzoukaklia/', " +
                "'ground beef, yellow onion, raisins, parsley, cayenne pepper, cinnamon, coriander, nutmeg, white sugar, salt, pepper, skewers, vegetable oil');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Greek Souzoukaklia', 'yellow onion', 'https://www.allrecipes.com/recipe/36161/greek-souzoukaklia/', " +
                "'ground beef, yellow onion, raisins, parsley, cayenne pepper, cinnamon, coriander, nutmeg, white sugar, salt, pepper, skewers, vegetable oil');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Grilled Root Beer Beef', 'steak', 'https://www.allrecipes.com/recipe/265394/grilled-root-beer-beef/', " +
                "'vegetable oil, pepper, cayenne pepper, chipotle pepper, ketchup, honey, soy sauce, white vinegar, can of root beer, flank steak, skewers, sesame seeds');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Grilled Root Beer Beef', 'honey', 'https://www.allrecipes.com/recipe/265394/grilled-root-beer-beef/', " +
                "'vegetable oil, pepper, cayenne pepper, chipotle pepper, ketchup, honey, soy sauce, white vinegar, can of root beer, flank steak, skewers, sesame seeds');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Grilled Panzanella Beef Kabobs', 'steak', 'https://www.allrecipes.com/recipe/274037/grilled-panzanella-beef-kabobs/', " +
                "'beef tri tip steak, can artichoke hearts, red bell pepper, zucchini, red onion, mozzarella cheese, rosemary peasant style bread, bamboo skewers, balsamic vinegar, olive oil, pepper, basil');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Grilled Panzanella Beef Kabobs', 'bell pepper', 'https://www.allrecipes.com/recipe/274037/grilled-panzanella-beef-kabobs/', " +
                "'beef tri tip steak, can artichoke hearts, red bell pepper, zucchini, red onion, mozzarella cheese, rosemary peasant style bread, bamboo skewers, balsamic vinegar, olive oil, pepper, basil');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Grilled Panzanella Beef Kabobs', 'zucchini', 'https://www.allrecipes.com/recipe/274037/grilled-panzanella-beef-kabobs/', " +
                "'beef tri tip steak, can artichoke hearts, red bell pepper, zucchini, red onion, mozzarella cheese, rosemary peasant style bread, bamboo skewers, balsamic vinegar, olive oil, pepper, basil');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Grilled Panzanella Beef Kabobs', 'red onion', 'https://www.allrecipes.com/recipe/274037/grilled-panzanella-beef-kabobs/', " +
                "'beef tri tip steak, can artichoke hearts, red bell pepper, zucchini, red onion, mozzarella cheese, rosemary peasant style bread, bamboo skewers, balsamic vinegar, olive oil, pepper, basil');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('Grilled Panzanella Beef Kabobs', 'mozzarella cheese', 'https://www.allrecipes.com/recipe/274037/grilled-panzanella-beef-kabobs/', " +
                "'beef tri tip steak, can artichoke hearts, red bell pepper, zucchini, red onion, mozzarella cheese, rosemary peasant style bread, bamboo skewers, balsamic vinegar, olive oil, pepper, basil');"
        );//

        /*
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('', 'broccoli', '', " +
                "'');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('', 'broccoli', '', " +
                "'');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('', 'broccoli', '', " +
                "'');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('', 'broccoli', '', " +
                "'');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('', 'broccoli', '', " +
                "'');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('', 'broccoli', '', " +
                "'');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('', 'broccoli', '', " +
                "'');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('', 'broccoli', '', " +
                "'');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('', 'broccoli', '', " +
                "'');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('', 'broccoli', '', " +
                "'');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('', 'broccoli', '', " +
                "'');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('', 'broccoli', '', " +
                "'');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('', 'broccoli', '', " +
                "'');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('', 'broccoli', '', " +
                "'');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('', 'broccoli', '', " +
                "'');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('', 'broccoli', '', " +
                "'');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('', 'broccoli', '', " +
                "'');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('', 'broccoli', '', " +
                "'');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('', 'broccoli', '', " +
                "'');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('', 'broccoli', '', " +
                "'');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('', 'broccoli', '', " +
                "'');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('', 'broccoli', '', " +
                "'');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('', 'broccoli', '', " +
                "'');"
        );//
        recipesListDb.execSQL("INSERT INTO " + tableName +
                " Values ('', 'broccoli', '', " +
                "'');"
        );//
        */


    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        createDatabase();
    }
}
