/**
 * 
 */
package com.adam.app.demo.back_stack;

import java.util.HashMap;
import java.util.Map;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

/**
 * @author AdamChen
 *
 */
public class IntentFlagSetAct extends Activity {

	// Check box item clicked map
	@SuppressLint("UseSparseArrays")
	private static Map<Integer,ItemClickType> sMap = new HashMap<Integer, ItemClickType>();
	
	private TextView mText_flag_info;
	
	// Create mapping table
	static {
		sMap.put(R.id.checkBox_new_task, ItemClickType.CHECKBOX_NEW_TASK);
		sMap.put(R.id.checkBox_clear_top, ItemClickType.CHECKBOX_CLEAR_TOP);
		sMap.put(R.id.checkBox_clear_task, ItemClickType.CHECKBOX_CLEAR_TASK);
		sMap.put(R.id.checkBox_single_top, ItemClickType.CHECKBOX_SINGLE_TOP);
		sMap.put(R.id.checkBox_multiple_task, ItemClickType.CHECKBOX_MULTIPLE_TASK);
		sMap.put(R.id.checkBox_brought_to_fornt, ItemClickType.CHECKBOX_BROUGHT_TO_FRONT);
		sMap.put(R.id.checkBox_clear_when_task_reset, ItemClickType.CHECKBOX_CLEAR_WHEN_TASK_RESET);
		sMap.put(R.id.checkBox_no_animation, ItemClickType.CHECKBOX_NO_ANIMATION);
	}
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.checkbox_layout);
		mText_flag_info = (TextView)this.findViewById(R.id.text_flag_info);
		
		// initial flag content
		FlagContent.INSTANCE.initFlag();
	}
	
	/**
	 * 
	 * @param view
	 */
    public void onCheckBoxClicked(View view) {
    	
    	if (sMap.containsKey(view.getId())) {
    		boolean checked = ((CheckBox) view).isChecked();
    		sMap.get(view.getId()).process(checked);
    	}
    	
    	// Show flag information
    	String value = Integer.toHexString(FlagContent.INSTANCE.getFlag());
    	mText_flag_info.setText("Flag value: " + value);
    }
	
    /**
	 * 
	 * @param view
	 */
    public void onSetFlagOk(View view) {
    	
    	Utils.showToast(this, "Config flag done ");
    	this.finish();
    	
    }
    
    /**
     * 
     * Manager check item clicked process
     * 
     * @author AdamChen
     *
     */
    enum ItemClickType {
    	
    	CHECKBOX_NEW_TASK {

			@Override
			void process(boolean isChecked) {
				Utils.Info(this, "[process] enter isChecked = " + isChecked);
				
				int flag = Intent.FLAG_ACTIVITY_NEW_TASK;
				
				if (isChecked) {					
					// Set flag value
					FlagContent.INSTANCE.addFlag(flag);
				} else {
					// Remove flag value
					FlagContent.INSTANCE.removeFlag(flag);
				}

			}
    		
    	} ,
    	CHECKBOX_CLEAR_TOP {

			@Override
			void process(boolean isChecked) {
				Utils.Info(this, "[process] enter isChecked = " + isChecked);
				
				int flag = Intent.FLAG_ACTIVITY_CLEAR_TOP;
				
				if (isChecked) {					
					// Set flag value
					FlagContent.INSTANCE.addFlag(flag);
				} else {
					// Remove flag value
					FlagContent.INSTANCE.removeFlag(flag);
				}

			}
    		
    	} ,
    	CHECKBOX_CLEAR_TASK {

			@Override
			void process(boolean isChecked) {
				Utils.Info(this, "[process] enter isChecked = " + isChecked);
				
				int flag = Intent.FLAG_ACTIVITY_CLEAR_TASK;
				
				if (isChecked) {					
					// Set flag value
					FlagContent.INSTANCE.addFlag(flag);
				} else {
					// Remove flag value
					FlagContent.INSTANCE.removeFlag(flag);
				}

			}
    		
    	} ,
    	CHECKBOX_SINGLE_TOP {

			@Override
			void process(boolean isChecked) {
				Utils.Info(this, "[process] enter isChecked = " + isChecked);
				
				int flag = Intent.FLAG_ACTIVITY_SINGLE_TOP;
				
				if (isChecked) {					
					// Set flag value
					FlagContent.INSTANCE.addFlag(flag);
				} else {
					// Remove flag value
					FlagContent.INSTANCE.removeFlag(flag);
				}

			}
    		
    	} ,
    	CHECKBOX_MULTIPLE_TASK {

			@Override
			void process(boolean isChecked) {
				Utils.Info(this, "[process] enter isChecked = " + isChecked);
				
				int flag = Intent.FLAG_ACTIVITY_MULTIPLE_TASK;
				
				if (isChecked) {					
					// Set flag value
					FlagContent.INSTANCE.addFlag(flag);
				} else {
					// Remove flag value
					FlagContent.INSTANCE.removeFlag(flag);
				}

			}
    		
    	} ,
    	CHECKBOX_BROUGHT_TO_FRONT {

			@Override
			void process(boolean isChecked) {
				Utils.Info(this, "[process] enter isChecked = " + isChecked);
				
				int flag = Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT;
				
				if (isChecked) {					
					// Set flag value
					FlagContent.INSTANCE.addFlag(flag);
				} else {
					// Remove flag value
					FlagContent.INSTANCE.removeFlag(flag);
				}

			}
    		
    	}  ,
    	CHECKBOX_CLEAR_WHEN_TASK_RESET {

			@Override
			void process(boolean isChecked) {
				Utils.Info(this, "[process] enter isChecked = " + isChecked);
				
				int flag = Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET;
				
				if (isChecked) {					
					// Set flag value
					FlagContent.INSTANCE.addFlag(flag);
				} else {
					// Remove flag value
					FlagContent.INSTANCE.removeFlag(flag);
				}

			}
    		
    	}  ,
    	CHECKBOX_NO_ANIMATION {

			@Override
			void process(boolean isChecked) {
				Utils.Info(this, "[process] enter isChecked = " + isChecked);
				
				int flag = Intent.FLAG_ACTIVITY_NO_ANIMATION;
				
				if (isChecked) {					
					// Set flag value
					FlagContent.INSTANCE.addFlag(flag);
				} else {
					// Remove flag value
					FlagContent.INSTANCE.removeFlag(flag);
				}

			}
    		
    	};
    	
    	abstract void process(boolean isChecked);
    	
    }
	
}
