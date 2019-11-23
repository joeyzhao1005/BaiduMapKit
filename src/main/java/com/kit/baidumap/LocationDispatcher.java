package com.kit.baidumap;

import android.content.Context;

import com.baidu.location.BDLocation;
import com.kit.app.application.AppMaster;
import com.kit.app.core.task.DoSomeThing;
import com.kit.baidumap.model.enums.CoorType;
import com.kit.utils.ResWrapper;
import com.kit.utils.log.Zog;


/**
 * Created by Zhao on 16/7/25.
 */
public class LocationDispatcher {

    /**
     * 天气,可能为后台查询
     */
    public static final String TYPE_WEATHER = "weather";

    /**
     * Ass结尾的为助理聊天界面查询的
     */
    public static final String TYPE_WEATHER_ASSISTANT = "weather_assistant";
    public static final String TYPE_BUS_ASSISTANT = "bus_assistant";

    String type;
    ILocator locator;

    private static volatile ZLocationListener bdLocationListener = null;

    public static void locate(final DoSomeThing doSomeThing) {
        Zog.i("location start");
        Context context = AppMaster.getInstance().getAppContext();

        ZLocationListener bdLocationListener = new ZLocationListener() {
            @Override
            public void onReceiveLocation(BDLocation location) {
                super.onReceiveLocation(location);
                LocationUtils.getInstance().getLocationClient().unRegisterLocationListener(this);

                Zog.i("locate success");
                if (doSomeThing != null) {
                    doSomeThing.execute(location);
                }
                Zog.i("locate success " + location.getAddrStr());
            }

            @Override
            public void onConnectHotSpotMessage(String s, int i) {
                super.onConnectHotSpotMessage(s, i);
            }
        };


        LocationUtils.getInstance()
                .init(context)
                .startLocation(CoorType.BD09LL, bdLocationListener);

    }

    public void locate(final String type, ILocator locator) {
        this.type = type;
        this.locator = locator;

    }


}
