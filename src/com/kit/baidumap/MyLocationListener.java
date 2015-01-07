package com.kit.baidumap;

import android.content.Context;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;
import com.kit.extend.baidu.map.R;

import java.util.ArrayList;

/**
 * 定位SDK监听函数
 */
public class MyLocationListener implements BDLocationListener, IDoOverLay {

	private Context mContext;
	private MapView mMapView;
	private BaiduMap mBaiduMap;
	private LocationClient mLocationClient;
	private ArrayList<PointKit> mPointList;

	public MyLocationListener(Context context, LocationClient mLocationClient,
			MapView mapView, BaiduMap baiduMap) {

		this.mContext = context;
		this.mMapView = mapView;
		this.mBaiduMap = baiduMap;
		this.mLocationClient = mLocationClient;

	}

	public MyLocationListener(Context context, LocationClient mLocationClient,
			MapView mapView, BaiduMap baiduMap, ArrayList<PointKit> pointList) {

		this.mContext = context;
		this.mMapView = mapView;
		this.mBaiduMap = baiduMap;
		this.mLocationClient = mLocationClient;
		this.mPointList = pointList;

	}

	public void onReceivePoi(BDLocation poiLocation) {
		if (poiLocation == null) {
			return;
		}
	}

	@Override
	public void onReceiveLocation(BDLocation location) {
		// TODO Auto-generated method stub
		if (location == null) {

			return;
		}

//		LogUtils.printLog(mContext.getClass(), location.getLatitude() + " "
//				+ location.getLongitude());

		if (location == null || mMapView == null)
			return;
		MyLocationData locData = new MyLocationData.Builder()
				.accuracy(location.getRadius())
				// 此处设置开发者获取到的方向信息，顺时针0-360
				.direction(100).latitude(location.getLatitude())
				.longitude(location.getLongitude()).build();

//		LogUtils.printLog(mContext.getClass(), "mBaiduMap:" + mBaiduMap
//				+ " locData:" + locData);

		mBaiduMap.setMyLocationData(locData);

		LatLng latLng = new LatLng(location.getLatitude(),
				location.getLongitude());

		// 设置zoom level和location
		MapStatus ms = new MapStatus.Builder().target(latLng).zoom(15).build();
		MapStatusUpdate mapStatusUpdate = MapStatusUpdateFactory
				.newMapStatus(ms);
		mBaiduMap.animateMapStatus(mapStatusUpdate);

		initOverlay(location);
	}

	@Override
	public void initOverlay(BDLocation location) {

		if (mLocationClient != null)
			mLocationClient.stop();
		// 定义Maker坐标点

		// 构建Marker图标
		BitmapDescriptor bitmap = BitmapDescriptorFactory
				.fromResource(R.drawable.ic_launcher);
		// 构建MarkerOption，用于在地图上添加Marker

		// 在地图上添加Marker，并显示

		// LatLng pt1 = new LatLng(location.getLatitude() - 0.0001,
		// location.getLongitude() - 0.0001);
		// LatLng pt2 = new LatLng(location.getLatitude() - 0.0002,
		// location.getLongitude() - 0.0002);
		// LatLng pt3 = new LatLng(location.getLatitude() - 0.0003,
		// location.getLongitude() - 0.0003);
		// LatLng pt4 = new LatLng(location.getLatitude() - 0.0004,
		// location.getLongitude() - 0.0004);

		mPointList = new ArrayList<PointKit>();
		mPointList.add(new PointKit(location.getLatitude() - 0.0001, location
				.getLongitude() - 0.0001));
		mPointList.add(new PointKit(location.getLatitude() + 0.0001, location
				.getLongitude() + 0.0001));
		mPointList.add(new PointKit(location.getLatitude() + 0.0008, location
				.getLongitude() + 0.0008));

		for (PointKit pk : mPointList) {
			LatLng pt1 = new LatLng(pk.getX(), pk.getY());
			OverlayOptions option = new MarkerOptions().position(pt1).icon(
					bitmap);
			mBaiduMap.addOverlay(option);

		}

	}

}