package com.kit.baidumap;

import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;

import com.baidu.location.LocationClient;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BaiduMap.OnMapClickListener;
import com.baidu.mapapi.map.BaiduMap.OnMarkerClickListener;
import com.baidu.mapapi.map.InfoWindow;
import com.baidu.mapapi.map.InfoWindow.OnInfoWindowClickListener;
import com.baidu.mapapi.map.MapPoi;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.model.LatLng;
import com.kit.baidumap.model.enums.CoorType;
import com.kit.extend.baidu.map.R;

import java.util.ArrayList;

/**
 * 
 * @ClassName MainActivity
 * @Description 应用的首页，显示一个地图以及其他相关信息,下面两个按钮做找车、找货的分发
 * @author Zhao laozhao1005@gmail.com
 * @date 2014-5-27 下午15:39:50
 * 
 */
public class BaiduMapActivity extends Activity implements OnClickListener,
		OnMapClickListener {
	private LayoutInflater inflater;

	private MapView mMapView = null;

	private LocationClient mLocationClient = null;
	// private BDLocationListener myListener;

	private InfoWindow mInfoWindow;
	private ArrayList<Marker> mMarkerList;

	private BaiduMap mBaiduMap;

	private Context mContext;

	// private Marker mMarkerB;
	// private Marker mMarkerC;
	// private Marker mMarkerD;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	public boolean initWidget() {

		setContentView(R.layout.baidumap_activity);
		mContext = this;
		inflater = getLayoutInflater();

		initMap();

		// myListener = new MyLocationListener(mContext, mLocationClient,
		// mMapView, mBaiduMap);

		mLocationClient = new LocationClient(mContext.getApplicationContext());
		LocationUtils.startLocation(mContext, mLocationClient, CoorType.BD09LL,
				new MyLocationListener(mContext, mLocationClient, mMapView,
						mBaiduMap));

		return true;
	}

	private void initMap() {

		mMapView = (MapView) findViewById(R.id.bmapView);

		mBaiduMap = mMapView.getMap();
		mBaiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);

		mBaiduMap.setMyLocationEnabled(true);

		mBaiduMap.setOnMapClickListener(this);

		mBaiduMap.setOnMarkerClickListener(new OnMarkerClickListener() {

			public boolean onMarkerClick(final Marker marker) {

				LinearLayout parent = new LinearLayout(mContext);

				View view = inflater.inflate(R.layout.baidumap_activity, null);

				parent.addView(view);

				final LatLng ll = marker.getPosition();
				Point p = mBaiduMap.getProjection().toScreenLocation(ll);
				p.y -= 47;
				LatLng llInfo = mBaiduMap.getProjection().fromScreenLocation(p);
				OnInfoWindowClickListener listener = new OnInfoWindowClickListener() {

					public void onInfoWindowClick() {

						// IntentUtils.gotoNextActivity(mContext,
						// WidgetShowActivity.class);

						mBaiduMap.hideInfoWindow();
					}

				};

				mInfoWindow = new InfoWindow(parent, llInfo, listener);
				mBaiduMap.showInfoWindow(mInfoWindow);
				return true;
			}
		});

	}

	@Override
	public boolean dispatchKeyEvent(KeyEvent event) {

		int keyCode = event.getKeyCode();

		if (keyCode == KeyEvent.KEYCODE_BACK
				&& event.getAction() == KeyEvent.ACTION_DOWN) {

			mBaiduMap.hideInfoWindow();

			// String msg = getString(R.string.tips_exit);
			// ExitApp.exit(mContext, msg, ExitApp.EXIT_TYPE_DOUBLE_CLICK);
			return true;
		}
		return super.dispatchKeyEvent(event);
	}

	@Override
	public void onClick(View view) {
		switch (view.getId()) {

		}

	}

	@Override
	protected void onStart() {

		super.onStart();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();

		// myListener = null;
		mLocationClient.stop();
		// 在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
		mMapView.onDestroy();
	}

	@Override
	protected void onResume() {
		super.onResume();

		initMap();
		// 在activity执行onResume时执行mMapView. onResume ()，实现地图生命周期管理
		mMapView.onResume();
	}

	@Override
	protected void onPause() {

		super.onPause();

		// for (Marker mker : mMarkerList) {
		// mker.remove();
		// }

		// 在activity执行onPause时执行mMapView. onPause ()，实现地图生命周期管理
		mMapView.onPause();

		mBaiduMap = null;
		mMapView = null;

	}

	@Override
	public void onMapClick(LatLng arg0) {
		// TODO Auto-generated method stub
		mBaiduMap.hideInfoWindow();

	}

	@Override
	public boolean onMapPoiClick(MapPoi arg0) {
		// TODO Auto-generated method stub

		return false;
	}
}
