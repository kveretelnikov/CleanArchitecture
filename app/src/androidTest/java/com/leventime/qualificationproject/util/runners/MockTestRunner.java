package com.leventime.qualificationproject.util.runners;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.test.runner.AndroidJUnitRunner;

import com.leventime.qualificationproject.TestApp;

/**
 * Runner for tests with provide mocks
 *
 * @author kv
 */
public class MockTestRunner extends AndroidJUnitRunner{

    @Override
    public void onCreate(Bundle arguments){
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().permitAll().build());
        super.onCreate(arguments);
    }

    @Override
    public Application newApplication(ClassLoader aCl, String aClassName, Context aContext)
            throws InstantiationException, IllegalAccessException, ClassNotFoundException{

        return super.newApplication(aCl, TestApp.class.getName(), aContext);
    }
}
