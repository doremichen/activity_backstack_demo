/**
 * 
 */
package com.adam.app.demo.back_stack;

import android.content.Intent;
import android.view.View;

/**
 * @author AdamChen
 *
 */
public class C extends BaseActivity {

	@Override
	int onTaskId() {
		return Utils.getTaskId(this);
	}

	@Override
	String onActivityIns() {
		return Utils.getActivityInfo(this);
	}
	
	/**
	 * 
	 * @param v
	 */
	public void onConfigFlag(View v) {
		Utils.setIntentFlag(this);
	}
	
	
	/**
	 * 
	 * @param v
	 */
	public void onNext(View v) {
		Utils.goToNextAct(this, mEdit, sMap);
	}
}
