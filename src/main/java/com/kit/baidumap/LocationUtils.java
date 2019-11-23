package com.kit.baidumap;

import android.content.Context;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.location.LocationClientOption.LocationMode;

public class LocationUtils {

    public void stopLocation() {
        if (locationClient == null) {
            return;
        }
        locationClient.stop();
    }


    public void startLocation(String coorType, ZLocationListener locationListener) {

        if (locationClient == null) {
            return;
        }

        // 注册监听函数
        locationClient.registerLocationListener(locationListener);

        // 定位初始化
        LocationClientOption option = new LocationClientOption();
        // 设置定位模式
        option.setLocationMode(LocationMode.Hight_Accuracy);
        // 返回的定位结果是百度经纬度,默认值gcj02
        option.setCoorType(coorType);
        // option.setScanSpan(HiBitConfig.baiduMapScanSpan);//
        // 设置发起定位请求的间隔时间为5000ms
        // 返回的定位结果包含地址信息
        option.setIsNeedAddress(true);
        // 返回的定位结果包含手机机头的方向
        option.setNeedDeviceDirect(true);
        option.setOpenGps(true);
        locationClient.setLocOption(option);
        locationClient.start();

        if (locationClient.isStarted()) {
            locationClient.requestLocation();
        } else {
            locationClient.start();
            locationClient.requestLocation();
        }

    }

    public static LocationUtils getInstance() {
        if (locationUtils == null) {
            locationUtils = new LocationUtils();
        }


        return locationUtils;
    }

    public LocationUtils init(Context context) {
        if (locationUtils.locationClient == null) {
            locationUtils.locationClient = new LocationClient(context.getApplicationContext());
        }
        return locationUtils;
    }


    public LocationClient getLocationClient() {
        return locationClient;
    }

    private static LocationUtils locationUtils;
    LocationClient locationClient;


    // How to use?

	/*
     * LocationUtils.startLocation(mContext, new LocationClient(
	 * getApplicationContext()), new BDLocationListener() {
	 * 
	 * @Override public void onReceiveLocation(BDLocation location) {
	 * LogUtils.printLog(getClass(), location.getAddrStr()); } });
	 */

}
