/**
 * 
 */
package com.adam.app.demo.back_stack;

import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.content.Context;
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
		
		int flag = FlagContent.INSTANCE.getFlag();
		String strFlag = Integer.toHexString(flag);
		
		mText_taskId.setText("Task Id: " + onTaskId() + " intent flg = " + strFlag);
		mText_actIns.setText("Activity Instance: " + onActivityIns());
		
		// reset intent flag
		FlagContent.INSTANCE.initFlag();
		
	}
	
	
	
	@Override
	protected void onResume() {
		super.onResume();
		Utils.Info(this, "[onResume] enter");

	}



	@Override
	protected void onNewIntent(Intent intent) {
		super.onNewIntent(intent);
		Utils.Info(this, "[onNewIntent] enter");
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
		String input = mEdit.getText().toString();

		if (sMap.containsKey(input)) {
			Class<?> nextAct = sMap.get(input);

			Intent intent = new Intent(this, nextAct);
			intent.addFlags(FlagContent.INSTANCE.getFlag());
			this.startActivity(intent);
		} else {
			Utils.showToast(this, "No activity to go");
		}
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




	abstract int onTaskId();
	
	abstract String onActivityIns();
	

}
