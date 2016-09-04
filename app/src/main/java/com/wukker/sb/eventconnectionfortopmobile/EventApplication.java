package com.wukker.sb.eventconnectionfortopmobile;

import android.app.Application;
import com.vk.sdk.VKSdk;

public class EventApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        VKSdk.initialize(this);
    }

}
