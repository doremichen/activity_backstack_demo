/**
 * Copyright (C) 2025 Adam Chen. All rights reserved.
 *
 * Description: This class is the intent flag setting activity
 * Author: Adam Chen
 * Date: 2025-10-13
 */
package com.adam.app.demo.back_stack;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

/**
 * @author AdamChen
 */
public class IntentFlagSetAct extends Activity {

    // Check box item clicked map
    @SuppressLint("UseSparseArrays")
    private static final Map<Integer, ItemClickType> FLAG_PROCESSORS = new HashMap<Integer, ItemClickType>() {
        {
            put(R.id.checkBox_clear, ItemClickType.CHECKBOX_CLEAR);
            put(R.id.checkBox_new_task, ItemClickType.CHECKBOX_NEW_TASK);
            put(R.id.checkBox_clear_top, ItemClickType.CHECKBOX_CLEAR_TOP);
            put(R.id.checkBox_clear_task, ItemClickType.CHECKBOX_CLEAR_TASK);
            put(R.id.checkBox_single_top, ItemClickType.CHECKBOX_SINGLE_TOP);
            put(R.id.checkBox_multiple_task, ItemClickType.CHECKBOX_MULTIPLE_TASK);
            put(R.id.checkBox_brought_to_fornt, ItemClickType.CHECKBOX_BROUGHT_TO_FRONT);
            put(R.id.checkBox_clear_when_task_reset, ItemClickType.CHECKBOX_CLEAR_WHEN_TASK_RESET);
            put(R.id.checkBox_no_animation, ItemClickType.CHECKBOX_NO_ANIMATION);
        }
    };

    private TextView mTextFlagInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.checkbox_layout);
        mTextFlagInfo = this.findViewById(R.id.text_flag_info);

        // initial flag content
        FlagContent.INSTANCE.initFlag();
    }

    /**
     * Handles checkbox click events.
     *
     * @param view The clicked checkbox view.
     */
    public void onCheckBoxClicked(View view) {

        if (FLAG_PROCESSORS.containsKey(view.getId())) {
            boolean checked = ((CheckBox) view).isChecked();
            FLAG_PROCESSORS.get(view.getId()).process(checked);
        }

        // Show flag information
        String value = Integer.toHexString(FlagContent.INSTANCE.getFlag());
        mTextFlagInfo.setText("Flag value: 0x" + value);
    }

    /**
     * Handles the "Set Flag OK" button click.
     *
     * @param view The clicked button view.
     */
    public void onSetFlagOk(View view) {

        Utils.showToast(this, "Config flag done ");
        this.finish();

    }

    /**
     * Manager check item clicked process
     *
     * @author AdamChen
     */
    enum ItemClickType {

        CHECKBOX_CLEAR {
            @Override
            void process(boolean isChecked) {
                Utils.Info(this, "[process] enter isChecked = " + isChecked);
                FlagContent.INSTANCE.initFlag();
            }
        },

        CHECKBOX_NEW_TASK {
            @Override
            void process(boolean isChecked) {
                Utils.Info(this, "[process] enter isChecked = " + isChecked);

                processFlag(isChecked, Intent.FLAG_ACTIVITY_NEW_TASK);

            }

        }, CHECKBOX_CLEAR_TOP {
            @Override
            void process(boolean isChecked) {
                Utils.Info(this, "[process] enter isChecked = " + isChecked);

                processFlag(isChecked, Intent.FLAG_ACTIVITY_CLEAR_TOP);

            }

        }, CHECKBOX_CLEAR_TASK {
            @Override
            void process(boolean isChecked) {
                Utils.Info(this, "[process] enter isChecked = " + isChecked);

                processFlag(isChecked, Intent.FLAG_ACTIVITY_CLEAR_TASK);

            }

        }, CHECKBOX_SINGLE_TOP {
            @Override
            void process(boolean isChecked) {
                Utils.Info(this, "[process] enter isChecked = " + isChecked);

                processFlag(isChecked, Intent.FLAG_ACTIVITY_SINGLE_TOP);

            }

        }, CHECKBOX_MULTIPLE_TASK {
            @Override
            void process(boolean isChecked) {
                Utils.Info(this, "[process] enter isChecked = " + isChecked);

                processFlag(isChecked, Intent.FLAG_ACTIVITY_MULTIPLE_TASK);

            }

        }, CHECKBOX_BROUGHT_TO_FRONT {
            @Override
            void process(boolean isChecked) {
                Utils.Info(this, "[process] enter isChecked = " + isChecked);

                processFlag(isChecked, Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);

            }

        }, CHECKBOX_CLEAR_WHEN_TASK_RESET {
            @Override
            void process(boolean isChecked) {
                Utils.Info(this, "[process] enter isChecked = " + isChecked);

                processFlag(isChecked, Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);

            }

        }, CHECKBOX_NO_ANIMATION {
            @Override
            void process(boolean isChecked) {
                Utils.Info(this, "[process] enter isChecked = " + isChecked);

                processFlag(isChecked, Intent.FLAG_ACTIVITY_NO_ANIMATION);

            }

        };

        // Helper method to reduce code duplication
        void processFlag(boolean isChecked, int flag) {
            Utils.Info(this, "[process] enter isChecked = " + isChecked);
            if (isChecked) {
                FlagContent.INSTANCE.addFlag(flag);
            } else {
                FlagContent.INSTANCE.removeFlag(flag);
            }
        }

        abstract void process(boolean isChecked);

    }

}
