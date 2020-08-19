/**
 * 
 */
package com.adam.app.demo.back_stack;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

/**
 * @author AdamChen
 *
 */
public abstract class BaseActivity extends Activity {

	protected EditText mEdit;
	
	// Create activity Map
	protected static Map<String, Class<?>> sMap = new HashMap<String, Class<?>>();

	private TextView mText_taskId;

	private TextView mText_actIns;
	
	static {
		sMap.put("A", A.class);
		sMap.put("B", B.class);
		sMap.put("C", C.class);
		sMap.put("D", D.class);
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Utils.Info(this, ">>>[onCreate] enter");
		this.setContentView(R.layout.activity_demo);
		
		mText_taskId = (TextView)this.findViewById(R.id.text_task_id);
		mText_actIns = (TextView)this.findViewById(R.id.text_act_ins);
		
		mEdit = (EditText)this.findViewById(R.id.edit_next_class);

		updateInfo();

		// reset intent flag
		FlagContent.INSTANCE.initFlag();


	}

	private void updateInfo() {
		int flag = FlagContent.INSTANCE.getFlag();
		String strFlag = Integer.toHexString(flag);

		mText_taskId.setText("Task Id: " + onTaskId() + " intent flg = 0x" + strFlag);
		mText_actIns.setText("Activity Instance: " + onActivityIns());
	}


	@Override
	protected void onResume() {
		super.onResume();
		Utils.Info(this, "[onResume] enter");

		// Show info dialog
		AlertDialog dialog = new AlertDialog.Builder(this).create();
		dialog.setTitle("Info:");
		dialog.setMessage(getTaskInfo());
		dialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
					}
				});
		dialog.show();

		updateInfo();
	}



	@Override
	protected void onNewIntent(Intent intent) {
		super.onNewIntent(intent);
		Utils.showToast(this, "[onNewIntent] enter");
	}


	/**
	 * 
	 * @param v
	 */
	public void onConfigFlag(View v) {
		Intent intent = new Intent(this, IntentFlagSetAct.class);
		this.startActivity(intent);
	}
	
	/**
	 * 
	 * @param v
	 */
	public void onNext(View v) {
		Utils.Info(this, "[onNext] +++");
		String input = mEdit.getText().toString();

		if (sMap.containsKey(input)) {
			Class<?> nextAct = sMap.get(input);

			Intent intent = new Intent(this, nextAct);
			if (FlagContent.INSTANCE.getFlag() != 0) {
				intent.addFlags(FlagContent.INSTANCE.getFlag());
			}
			this.startActivity(intent);
		} else {
			Utils.showToast(this, "No activity to go");
		}
		Utils.Info(this, "[onNext] ---");
	}
	

	/**
	 * 
	 */
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		
		// Hide the softkey board
		View view = this.getCurrentFocus();
		
		if (view != null) {
			InputMethodManager imm = (InputMethodManager)this.getSystemService(Context.INPUT_METHOD_SERVICE);
			imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
		}
		
		return true;
	}


	private String getTaskInfo() {
		Utils.Info(this, "[getTaskInfo] +++");
		StringBuilder stb = new StringBuilder("");
		ActivityManager am = (ActivityManager)this.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.AppTask> tasks = am.getAppTasks();

        Utils.Info(this, "task size = " + tasks.size());

		final ActivityManager.AppTask appTask = tasks.get(0);
		final ActivityManager.RecentTaskInfo taskInfo = appTask.getTaskInfo();

		stb.append("Task num: ").append(tasks.size()-1).append("\n");
		stb.append("Task Id: ").append(taskInfo.id).append("\n");
		stb.append("Activity num: ").append(taskInfo.numActivities).append("\n");
		stb.append("Top Activity: ").append(taskInfo.topActivity.getShortClassName()).append("\n");
		stb.append("Base Activity: ").append(taskInfo.baseActivity.getShortClassName()).append("\n");
		stb.append("Orig Activity: ").append(taskInfo.origActivity).append("\n");

		String info = stb.toString();

		Utils.Info(this, "[getTaskInfo] ---");
		return info;
	}




	abstract int onTaskId();
	
	abstract String onActivityIns();
	

}
