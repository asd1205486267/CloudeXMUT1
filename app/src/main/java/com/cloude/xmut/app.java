package com.cloude.xmut;

import android.util.Log;
import android.app.Application;
import com.squareup.leakcanary.LeakCanary;

public class app extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        if (LeakCanary.isInAnalyzerProcess(this)) {
            Log.d("App", "In LeakCanary Analyzer Process");
            return;
        }

    }
}
