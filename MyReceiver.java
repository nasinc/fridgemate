package mycompanyname.foodtracker;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by Nicholas on 8/11/2018.
 */

public class MyReceiver extends BroadcastReceiver {

    public MyReceiver(){

    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Intent intent1 = new Intent(context, MyIntentService.class);
        context.startService(intent1);
        /**
         * need to research how to run the notification when the app is closed so it runs at a given time every day
         * and the app doesn't need to be open.
         * */
    }
}
