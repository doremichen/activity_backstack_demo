package com.adam.app.demo.back_stack;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_exit) {
            // exit
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void onLaunchStandardActivity(View v) {
        Intent it = new Intent(this, StandardAct.class);
        this.startActivity(it);
    }

    public void onLaunchSingleTopActivity(View v) {
        Intent it = new Intent(this, SingleTopAct.class);
        this.startActivity(it);
    }

    public void onLaunchSingleTaskActivity(View v) {
        Intent it = new Intent(this, SingleTaskAct.class);
        this.startActivity(it);
    }

    public void onLaunchSingleInstanceActivity(View v) {
        Intent it = new Intent(this, SingleInstanceAct.class);
        this.startActivity(it);
    }

    public void onLaunchDemoActivity(View v) {
        Utils.showToast(this, "[Start Demo...]");
        Intent intent = new Intent(this, A.class);
        this.startActivity(intent);

    }
}
