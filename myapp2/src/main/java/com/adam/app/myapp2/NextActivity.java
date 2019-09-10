package com.adam.app.myapp2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class NextActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        // set content view
        this.setContentView(R.layout.activity_next);
    }

    private static int mCounter = 0;
    private static final int MAX_VALUE = 3;
    public void onGo(View view) {
        Utils.info(this, "onGo enter");
        Intent intent;
        if (mCounter < MAX_VALUE) {
            intent = new Intent(this, NextActivity.class);
            mCounter++;
        } else {
            intent = new Intent(this, MainActivity.class);
            mCounter = 0;
        }

        this.startActivity(intent);
    }
}
