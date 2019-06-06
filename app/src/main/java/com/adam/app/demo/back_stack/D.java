/**
 * 
 */
package com.adam.app.demo.back_stack;


/**
 * @author AdamChen
 *
 */
public class D extends BaseActivity {

	@Override
	int onTaskId() {
		return Utils.getTaskId(this);
	}

	@Override
	String onActivityIns() {
		return Utils.getActivityInfo(this);
	}
	
}
