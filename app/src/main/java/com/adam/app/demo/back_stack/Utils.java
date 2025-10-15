/**
 * Copyright (C) 2025 Adam Chen. All rights reserved.
 *
 * Description: This class is used to define the tools
 * Author: Adam Chen
 * Date: 2025-10-13
 */
package com.adam.app.demo.back_stack;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

/**
 * This Utils class defines the tools
 */
public abstract class Utils {

    private static final String TAG = "Demo";

    /**
     * Show info log
     * @param obj Instance
     * @param str Log content
     */
    public static void Info(Object obj, String str) {

        Log.i(TAG, obj.getClass().getSimpleName() + ": " + str);
    }

    /**
     * Get task id
     * @param activity: Activity
     * @return task id
     */
    public static int getTaskId(Activity activity) {

        return activity.getTaskId();
    }

    /**
     * Get activity info
     * @param activity: Activity
     * @return activity info
     */
    public static String getActivityInfo(Activity activity) {

        return activity.getLocalClassName();
    }

    /**
     * Show toast
     * @param context: Context
     * @param msg: toast msg
     */
    public static void showToast(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

    public static boolean hideSoftKeyBoard(Activity activity) {
        View view = activity.getCurrentFocus();
        if (view == null) {
            showToast(activity, "Invalid view!!!");
            return false;
        }

        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        return true;
    }
}
