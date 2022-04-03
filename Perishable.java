 package mycompanyname.foodtracker;

/**
 * Created by Nicholas on 2/21/2018.
 */

public class Perishable {
    private int index;
    private String pName;
    private String goesBadOnMin;
    private String goesBadOnMax;
    private String minPantry, maxPantry;
    private String minFridge, maxFridge;
    private String minFreeze, maxFreeze;
    private int position;

    Perishable()
    {
        //empty
    }

    Perishable(String name, String minpan, String maxpan, String minfrid, String maxfrid, String minfrz, String maxfrz)
    {
        this.pName = name;
        this.minPantry = minpan;
        this.maxPantry = maxpan;
        this.minFridge = minfrid;
        this.maxFridge = maxfrid;
        this.minFreeze = minfrz;
        this.maxFreeze = maxfrz;
    }

    Perishable(int index, String name, String bomin, String bomax, String minpan, String maxpan, String minfrid, String maxfrid, String minfrz, String maxfrz, int pos)
    {
        this.index = index;
        this.pName = name;
        this.minPantry = minpan;
        this.maxPantry = maxpan;
        this.minFridge = minfrid;
        this.maxFridge = maxfrid;
        this.minFreeze = minfrz;
        this.maxFreeze = maxfrz;
        this.goesBadOnMin = bomin;
        this.goesBadOnMax = bomax;
        this.position = pos;
    }

    public void setIndex(int temp)
    {
        index = temp;
    }

    public int getIndex()
    {
        return index;
    }

    public void setBadOnMin(String temp)
    {
        goesBadOnMin = temp;
    }

    public String getBadOnMin()
    {
        return goesBadOnMin;
    }

    public void setBadOnMax(String temp)
    {
        goesBadOnMax = temp;
    }

    public String getBadOnMax()
    {
        return goesBadOnMax;
    }

    public void setPosition(int temp)
    {
        position = temp;
    }

    public int getPosition()
    {
        return position;
    }

    public void setpName(String temp)
    {
        pName = temp;
    }

    public String getpName()
    {
        return pName;
    }

    public void setMinPantry(String temp)
    {
        minPantry = temp;
    }

    public String getMinPantry()
    {
        return minPantry;
    }

    public void setMaxPantry(String temp)
    {
        maxPantry = temp;
    }

    public String getMaxPantry()
    {
        return maxPantry;
    }

    public void setMinFridge(String temp)
    {
        minFridge = temp;
    }

    public String getMinFridge()
    {
        return minFridge;
    }

    public void setMaxFridge(String temp)
    {
        maxFridge = temp;
    }

    public String getMaxFridge()
    {
        return maxFridge;
    }

    public void setMinFreeze(String temp)
    {
        minFreeze = temp;
    }

    public String getMinFreeze()
    {
        return minFreeze;
    }

    public void setMaxFreeze(String temp)
    {
        maxFreeze = temp;
    }

    public String getMaxFreeze()
    {
        return maxFreeze;
    }



}

