/**
 * Copyright (C) 2025 Adam Chen. All rights reserved.
 *
 * Description: This class is used to provide task information.
 * Author: Adam Chen
 * Date: 2025-10-13
 */
package com.adam.app.demo.back_stack;

import android.app.ActivityManager;
import android.content.Context;

import androidx.annotation.NonNull;

import java.util.List;

public final class TaskInfoProvider {

    private static final String INFO_TOP = "----- Task Info Start -----";
    private static final String INFO_BOTTOM = "----- Task Info End -----";
    private static final String TASK_SIZE = "Task Size: ";
    private static final String TASK_ID = "Task ID: ";
    private static final String ACTIVITY_NUMBER = "Number of Activities: ";
    private static final String TOP_ACTIVITY = "Top Activity: ";
    private static final String BASE_ACTIVITY = "Base Activity: ";
    private static final String ORIGINAL_ACTIVITY = "Original Activity: ";


    /**
     * private constructor to hide the implicit public one
     */
    private TaskInfoProvider() {
    }

    /**
     * get task info
     *
     * @param context Context
     * @return String
     */
    public static String getTaskInfo(Context context) {
        StringBuilder sb = new StringBuilder();
        sb.append(INFO_TOP).append("\n");
        // activity manager
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        // List of app task
        List<ActivityManager.AppTask> tasks = am.getAppTasks();
        sb.append(TASK_SIZE).append(tasks.size()).append("\n");
        // get task recent activity info
        for (ActivityManager.AppTask task : tasks) {
            sb.append("=======================").append("\n");
            ActivityManager.RecentTaskInfo info = task.getTaskInfo();
            sb.append(TASK_ID).append(info.id).append("\n");
            sb.append(ACTIVITY_NUMBER).append(info.numActivities).append("\n");
            sb.append(TOP_ACTIVITY).append(info.topActivity != null ? info.topActivity.getShortClassName() : null).append("\n");
            sb.append(BASE_ACTIVITY).append(info.baseActivity != null ? info.baseActivity.getShortClassName() : null).append("\n");
            sb.append(ORIGINAL_ACTIVITY).append(info.origActivity).append("\n");
            sb.append("=======================").append("\n");
        }
        sb.append(INFO_BOTTOM).append("\n");
        return sb.toString();
    }

}
