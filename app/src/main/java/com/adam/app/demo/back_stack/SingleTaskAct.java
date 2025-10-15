/**
 * Copyright (C) 2025 Adam Chen. All rights reserved.
 *
 * Description: This class is the single task activity.
 * Author: Adam Chen
 * Date: 2025-10-13
 */
package com.adam.app.demo.back_stack;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;


/**
 * @author AdamChen
 */
public class SingleTaskAct extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.setContentView(R.layout.task_layout);

        TextView text_view = (TextView) this.findViewById(R.id.text_task_id);
        text_view.setText(getString(R.string.demo_backstack_singleTask_instruction));
        TextView text_info = (TextView) this.findViewById(R.id.text_task_info);
        text_info.setText(TaskInfoProvider.getTaskInfo(this));
    }

}
