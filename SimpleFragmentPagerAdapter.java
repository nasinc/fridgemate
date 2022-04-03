package mycompanyname.foodtracker;

import android.content.Context;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by Nicholas on 9/24/2018.
 */

public class SimpleFragmentPagerAdapter extends FragmentStatePagerAdapter{

    private Context mContext;

    public SimpleFragmentPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public Parcelable saveState()
    {
        return null;
    }


    // This determines the fragment for each tab
    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new ItemOneFragment();//search
        } else if (position == 1){
            return new ItemFourFragment();//categories
        }  else if (position == 2){
            return new VoiceInputDemo();//my list
        }else if (position == 3){
            return new ItemTwoFragment();//my kitchen
        } else if (position == 4){
            return new ItemFiveFragment();//recipes
        }
        else
        {
            return new ItemThreeFragment();//settings
        }

    }

    // This determines the number of tabs
    @Override
    public int getCount() {
        return 6;
    }

    // This determines the title for each tab
    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        switch (position) {
            case 0:
                return mContext.getString(R.string.title_home);
            case 1:
                return mContext.getString(R.string.title_categories);
            case 2:
                return "My Checklists";
            case 3:
                return mContext.getString(R.string.title_dashboard);
            case 4:
                return mContext.getString(R.string.title_recipes);
            case 5:
                return mContext.getString(R.string.title_notifications);
            default:
                return null;
        }
    }

}
