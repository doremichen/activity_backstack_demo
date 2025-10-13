/**
 * Copyright (C) 2025 Adam Chen. All rights reserved.
 *
 * Description: This class is the C activity
 * Author: Adam Chen
 * Date: 2025-10-13
 */
package com.adam.app.demo.back_stack;


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

}
