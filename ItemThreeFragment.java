package mycompanyname.foodtracker;

//import android.app.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Nicholas on 1/22/2018.
 *
 *  ToS TAB
 */

/**
 * List of things to do for this display:
 * 1. decide what is going to be in settings
 * 2. create rough design for settings layout
 * 3. code each setting and make sure it sticks
 * 4. add disclaimer button in settings that states that all information gathered and used was found on FDA website
 *      and that I cannot be held responsible for food that does not stay as fresh as stated in the app.  Need to find a general
 *      ToS that they have to agree to stating that even with a sophisticated system, errors can still be made and
 *      dates can still be wrong on certain occassions.
 * Decide what minimum and maximum interval we want to have for reminders
 *
 * */

public class ItemThreeFragment extends Fragment {
    public static ItemThreeFragment newInstance() {
        ItemThreeFragment fragment = new ItemThreeFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        //AlarmReceiver.getInstance().scheduleAlarm(this.getContext(), 0);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){



        return inflater.inflate(R.layout.fragment_item_three, container, false);
    }

    public void onViewCreated(View view, Bundle savedInstanceState){
        /*TimePicker alarm = getView().findViewById(R.id.timePicker);
        int hour = alarm.getCurrentHour();
        Toast.makeText(getContext(), "setting notification: " + hour, Toast.LENGTH_LONG).show();

        Button applybtn = getView().findViewById(R.id.NotifSetBtn);
        //applybtn
        Button tosbtn = getView().findViewById(R.id.tosbtn);
        tosbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent showtos = new Intent(getContext(), termsofservice.class);
                startActivity(showtos);
            }
        });*/
    }
}
