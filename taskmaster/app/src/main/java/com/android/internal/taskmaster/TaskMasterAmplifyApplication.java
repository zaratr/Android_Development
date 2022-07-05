package com.android.internal.taskmaster;


import android.app.Application;
import android.util.Log;

import com.amplifyframework.AmplifyException;
import com.amplifyframework.api.aws.AWSApiPlugin;
import com.amplifyframework.core.Amplify;


public class TaskMasterAmplifyApplication extends Application{
    public static final String TAG = "taskmasteractivity";

    @Override
    public void onCreate(){
        super.onCreate();
        try {
            Log.i(TAG, "Initialized Amplifiy successfully");
            Amplify.addPlugin(new AWSApiPlugin());
            Amplify.configure(getApplicationContext());

        }
        catch(AmplifyException ae){
            Log.e(TAG, "Error initializing Amplify: " +ae.getMessage(), ae);
        }
    }
}
