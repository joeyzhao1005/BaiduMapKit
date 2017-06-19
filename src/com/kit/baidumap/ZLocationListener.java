package com.kit.baidumap;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;

public class ZLocationListener implements BDLocationListener {


    @Override
    public void onReceiveLocation(BDLocation bdLocation) {
        LocationUtils.getInstance().getLocationClient().stop();
    }

    @Override
    public void onConnectHotSpotMessage(String s, int i) {

    }
}