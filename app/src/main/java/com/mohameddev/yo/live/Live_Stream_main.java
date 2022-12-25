package com.mohameddev.yo.live;

import android.os.Bundle;

import androidx.annotation.Nullable;
import android.app.Application;
import live.videosdk.rtc.android.VideoSDK;

import com.mohameddev.yo.R;


public class Live_Stream_main  extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        VideoSDK.initialize(getApplicationContext());
    }
}


