package com.adam.app.myapp2;

import android.util.Log;

public class Utils {

    private static final String TAG = "MyApp2";

    public static void info(Object obj, String str) {
        Log.i(TAG, obj.getClass().getSimpleName() + ": " + str);
    }

    public static void info(Class<?> clazz, String str) {
        Log.i(TAG, clazz.getSimpleName() + ": " + str);
    }
}
