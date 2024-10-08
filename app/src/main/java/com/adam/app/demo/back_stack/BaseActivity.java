/**
 *
 */
package com.adam.app.demo.back_stack;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author AdamChen
 */
public abstract class BaseActivity extends Activity {

    // Create activity Map
    private static final Map<String, Class<?>> ACTIVITY_MAP = new HashMap<String, Class<?>>() {
        {
            put("A", A.class);
            put("B", B.class);
            put("C", C.class);
            put("D", D.class);
        }
    };
    private EditText mEditNextClass;
    private TextView mTextTaskId;
    private TextView mTextActIns;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Utils.Info(this, ">>>[onCreate] enter");
        this.setContentView(R.layout.activity_demo);
        // Check permission
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (!Settings.canDrawOverlays(this)) {
                getOverlayPermission();
            }
        }

        mTextTaskId = this.findViewById(R.id.text_task_id);
        mTextActIns = this.findViewById(R.id.text_act_ins);

        mEditNextClass = this.findViewById(R.id.edit_next_class);

        updateInfo();

        // reset intent flag
        FlagContent.INSTANCE.initFlag();


    }

    private void updateInfo() {
        int flag = FlagContent.INSTANCE.getFlag();
        String strFlag = Integer.toHexString(flag);

        mTextTaskId.setText("Task Id: " + onTaskId() + " intent flg = 0x" + strFlag);
        mTextActIns.setText("Activity Instance: " + onActivityIns());
    }


    @Override
    protected void onResume() {
        super.onResume();
        Utils.Info(this, "[onResume] enter");

        // Show info dialog
        TaskInfoProvider.showDialog(this.getApplicationContext());

        updateInfo();
    }


    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Utils.showToast(this, "[onNewIntent] enter");
    }


    /**
     * Configures the intent flag.
     *
     * @param v The view that triggered this method.
     */
    public void onConfigFlag(View v) {
        Intent intent = new Intent(this, IntentFlagSetAct.class);
        this.startActivity(intent);
    }

    /**
     * Starts the next activity based on user input.
     *
     * @param view The view that triggered this method.
     */
    public void onNavigateToNextActivity(View view) {
        Utils.Info(this, "[onNext] +++");
        String targetActivityInput = mEditNextClass.getText().toString();
        Class<?> nextActivityClass = ACTIVITY_MAP.get(targetActivityInput);

        if (nextActivityClass == null) {
            Utils.showToast(this, "No activity found for input: " + targetActivityInput);
            return;
        }

        Intent intent = new Intent(this, nextActivityClass);
        // add flags
        int flags = FlagContent.INSTANCE.getFlag();
        if (flags != 0) {
            intent.addFlags(flags);
        }

        this.startActivity(intent);
        Utils.Info(this, "[onNext] ---");
    }


    /**
     * Hides the soft keyboard when the user touches outside of an input field.
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {

        // Hide the softkey board
        View view = this.getCurrentFocus();
        if (view == null) {
            Utils.showToast(this, "Invalid view!!!");
            return false;
        }

        InputMethodManager imm = (InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);

        return true;
    }

    @TargetApi(Build.VERSION_CODES.M)
    private void getOverlayPermission() {
        Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION);
        intent.setData(Uri.parse("package:" + getPackageName()));
        startActivityForResult(intent, 0);
    }


    abstract int onTaskId();

    abstract String onActivityIns();

    private static class TaskInfoProvider {

        private static final String INFO_TOP = "----- Task Info Start -----";
        private static final String INFO_BOTTOM = "----- Task Info End -----";
        private static final String TASK_SIZE = "Task Size: ";
        private static final String TASK_ID = "Task ID: ";
        private static final String ACTIVITY_NUMBER = "Number of Activities: ";
        private static final String TOP_ACTIVITY = "Top Activity: ";
        private static final String BASE_ACTIVITY = "Base Activity: ";
        private static final String ORIGINAL_ACTIVITY = "Original Activity: ";

        public static void showDialog(Context context) {
            AlertDialog dialog = new AlertDialog.Builder(context).create();
            dialog.getWindow().setType(WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY);
            dialog.getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL, WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL);
            dialog.setTitle("Info:");
            dialog.setMessage(getTaskInfo(context));
            dialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                    (dialog1, which) -> dialog1.dismiss());
            dialog.show();
        }


        private static String getTaskInfo(Context context) {
            Utils.Info(context, "[getTaskInfo] +++");
            StringBuilder taskInfoBuilder = new StringBuilder();
            ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
            List<ActivityManager.AppTask> tasks = activityManager.getAppTasks();

            Utils.Info(context, "Task size = " + tasks.size());

            taskInfoBuilder.append(INFO_TOP).append("\n");
            taskInfoBuilder.append(TASK_SIZE).append(tasks.size()).append("\n");

            if (!tasks.isEmpty()) {
                // Accessing the first task for demonstration. Consider handling multiple tasks if needed.
                ActivityManager.AppTask appTask = tasks.get(0);
                ActivityManager.RecentTaskInfo taskInfo = appTask.getTaskInfo();

                taskInfoBuilder.append(TASK_ID).append(taskInfo.id).append("\n");
                taskInfoBuilder.append(ACTIVITY_NUMBER).append(taskInfo.numActivities).append("\n");
                taskInfoBuilder.append(TOP_ACTIVITY).append(taskInfo.topActivity.getShortClassName()).append("\n");
                taskInfoBuilder.append(BASE_ACTIVITY).append(taskInfo.baseActivity.getShortClassName()).append("\n");
                taskInfoBuilder.append(ORIGINAL_ACTIVITY).append(taskInfo.origActivity).append("\n");
            }

            taskInfoBuilder.append(INFO_BOTTOM).append("\n");

            String info = taskInfoBuilder.toString();

            Utils.Info(context, "[getTaskInfo] ---");
            return info;
        }
    }


}
