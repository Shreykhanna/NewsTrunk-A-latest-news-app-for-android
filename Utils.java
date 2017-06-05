package com.khannashrey07gmail.newstrunk;

import android.app.Activity;
import android.content.Intent;

/**
 * Created by shrey on 4/5/17.
 */

public class Utils {
    public static int stheme;
    public final static int Theme_Default=0;
    public final static int Theme_Black=1;

    public static void changetotheme(Activity activity, int theme){
        stheme=theme;
        activity.finish();
        activity.startActivity(new Intent(activity,activity.getClass()));


    }
        public static void onActivityCreateSetTheme(Activity activity){
            switch (stheme){
                case Theme_Default:
                    activity.setTheme(R.style.AppTheme);
                    break;
                case Theme_Black:
                    activity.setTheme(R.style.NightmodeTheme);
                    break;
            }
        }
}
