package com.kit.baidumap;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;

public class ZLocationListener extends BDAbstractLocationListener {


    @Override
    public void onReceiveLocation(BDLocation bdLocation) {
        LocationUtils.getInstance().getLocationClient().stop();
    }
}