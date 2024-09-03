/**
 *
 */
package com.adam.app.demo.back_stack;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

/**
 * @author AdamChen
 */
public abstract class Utils {

    private static final String TAG = "Demo";

    /**
     * @param obj
     * @param str
     */
    public static final void Info(Object obj, String str) {

        Log.i(TAG, obj.getClass().getSimpleName() + ": " + str);
    }

    /**
     * @param activity
     * @return
     */
    public static int getTaskId(Activity activity) {

        return activity.getTaskId();
    }

    /**
     * @param activity
     * @return
     */
    public static String getActivityInfo(Activity activity) {

        return activity.getLocalClassName();
    }

    public static void showToast(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

}
