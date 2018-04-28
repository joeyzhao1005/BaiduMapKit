package com.kit.baidumap;

import android.content.Context;
import android.util.Log;
import android.widget.ZoomButton;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;

public class BDDefaultLocationListener implements BDLocationListener {
    public BDLocation baiduLocation;
    public Context context;

    public BDDefaultLocationListener(Context context) {
        this.context = context;
    }

    @Override
    public void onReceiveLocation(BDLocation location) {
        LocationClient lc = new LocationClient(
                context.getApplicationContext());
        lc.stop();
        baiduLocation = location;

    }

    @Override
    public void onConnectHotSpotMessage(String s, int i) {
        Log.d("BDDefaultLL", "BDDefaultLocationListener s:" + s + " i:" + i);
    }
}
