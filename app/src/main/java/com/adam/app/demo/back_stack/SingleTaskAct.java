/**
 *
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
        text_view.setText("This activity is launched by  Single Task ");
    }

}
