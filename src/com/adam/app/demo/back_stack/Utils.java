/**
 * 
 */
package com.adam.app.demo.back_stack;

import java.util.Map;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
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
		return activity.toString();
	}

	/**
	 * 
	 */
	public static void setIntentFlag(Activity activity) {
		Intent intent = new Intent(activity, IntentFlagSetAct.class);
		activity.startActivity(intent);
	}

	public static void goToNextAct(Activity activity, EditText edit,
			Map<String, Class<?>> map) {

		String input = edit.getText().toString();

		if (map.containsKey(input)) {
			Class<?> nextAct = map.get(input);

			Intent intent = new Intent(activity, nextAct);
			intent.addFlags(FlagContent.INSTANCE.getFlag());
			activity.startActivity(intent);
		} else {
			showToast(activity, "No activity to go");
		}
	}
}
