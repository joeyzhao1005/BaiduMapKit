package com.kit.baidumap;

import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.location.LocationClientOption.LocationMode;

public class LocationUtils {

    public static void startLocation(LocationClient locationClient
            , String coorType, BDLocationListener bdLocationListener) {

        if (locationClient == null) {
            return;
        }

        locationClient.registerLocationListener(bdLocationListener); // 注册监听函数
        // 定位初始化
        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(LocationMode.Hight_Accuracy);// 设置定位模式
        option.setCoorType(coorType);// 返回的定位结果是百度经纬度,默认值gcj02
        // option.setScanSpan(HiBitConfig.baiduMapScanSpan);//
        // 设置发起定位请求的间隔时间为5000ms
        option.setIsNeedAddress(true);// 返回的定位结果包含地址信息
        option.setNeedDeviceDirect(true);// 返回的定位结果包含手机机头的方向
        option.setOpenGps(true);
        locationClient.setLocOption(option);
        locationClient.start();

        if (locationClient.isStarted()) {
            locationClient.requestLocation();
        }

    }

    // How to use?

	/*
     * LocationUtils.startLocation(mContext, new LocationClient(
	 * getApplicationContext()), new BDLocationListener() {
	 * 
	 * @Override public void onReceiveLocation(BDLocation location) {
	 * LogUtils.printLog(getClass(), location.getAddrStr()); } });
	 */

}
