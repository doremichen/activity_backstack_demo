/**
 * Copyright (C) 2025 Adam Chen. All rights reserved.
 * <p>
 * Description: This class is the single instance activity.
 * Author: Adam Chen
 * Date: 2025-10-13
 */
package com.adam.app.demo.back_stack;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class SingleInstanceAct extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.setContentView(R.layout.task_layout);

        TextView text_view = (TextView) this.findViewById(R.id.text_task_id);
        text_view.setText(getString(R.string.demo_backstack_singleInstance_instruction));
    }

}
