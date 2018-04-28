package com.kit.extend.gps;

import com.kit.extend.gps.GpsTask.GpsData;

public interface GpsTaskCallBack {

	public void gpsConnected(GpsData gpsdata);
	
	public void gpsConnectedTimeOut();
	
}
