/**
 * 
 */
package com.adam.app.demo.back_stack;

import android.app.Activity;
import android.util.Log;
import android.widget.Toast;

/**
 * @author AdamChen
 * 
 */
public abstract class Utils {

	private static final String TAG = "Demo";

	/**
	 * 
	 * @param obj
	 * @param str
	 */
	public static final void Info(Object obj, String str) {

		Log.i(TAG, obj.getClass().getSimpleName() + ": " + str);
	}

	/**
	 * 
	 * @param activity
	 * @param str
	 */
	public static void showToast(Activity activity, String str) {
		Toast.makeText(activity, str, Toast.LENGTH_SHORT).show();
	}

	/**
	 * 
	 * @param activity
	 * @return
	 */
	public static int getTaskId(Activity activity) {

		return activity.getTaskId();
	}

	/**
	 * 
	 * @param activity
	 * @return
	 */
	public static String getActivityInfo(Activity activity) {

		return activity.getLocalClassName();
	}

}
