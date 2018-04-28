//package com.kit.baidumap;
//
//import android.content.Context;
//import android.location.LocationListener;
//
//import com.baidu.location.BDLocation;
//import com.baidu.location.BDLocationListener;
//import com.kit.app.core.task.DoSomeThing;
//import com.kit.baidumap.BDDefaultLocationListener;
//import com.kit.baidumap.LocationUtils;
//import com.kit.baidumap.ZLocationListener;
//import com.kit.baidumap.model.enums.CoorType;
//import com.kit.utils.ResWrapper;
//import com.kit.utils.log.Zog;
//import com.zhao.withu.core.tools.EventBusTools;
//
//import org.greenrobot.eventbus.Subscribe;
//import org.greenrobot.eventbus.ThreadMode;
//
///**
// * Created by Zhao on 16/7/25.
// */
//public class LocationDispatcher {
//
//    /**
//     * 天气,可能为后台查询
//     */
//    public static final String TYPE_WEATHER = "weather";
//
//    /**
//     * Ass结尾的为助理聊天界面查询的
//     */
//    public static final String TYPE_WEATHER_ASSISTANT = "weather_assistant";
//    public static final String TYPE_BUS_ASSISTANT = "bus_assistant";
//
//    String type;
//    ILocator locator;
//
//    public LocationDispatcher() {
//        EventBusTools.register(this);
//    }
//
//
//    public void locate(final DoSomeThing doSomeThing) {
//        Zog.i("location start");
//        Context context = ResWrapper.getInstance().getApplicationContext();
//
//
//        ZLocationListener bdLocationListener = new ZLocationListener() {
//            @Override
//            public void onReceiveLocation(BDLocation location) {
//                super.onReceiveLocation(location);
//                Zog.i("locate success");
//                doSomeThing.execute(location);
//                Zog.i("locate success " + location.getCity());
//            }
//
//            @Override
//            public void onConnectHotSpotMessage(String s, int i) {
//                super.onConnectHotSpotMessage(s, i);
//
//            }
//        };
//
//
//        LocationUtils.getInstance()
//                .init(context)
//                .startLocation(CoorType.BD09LL, bdLocationListener);
//
//    }
//
//    public void locate(final String type, ILocator locator) {
//        this.type = type;
//        this.locator = locator;
//
////        UnderstandResponse understandResponse = new UnderstandResponse();
////        understandResponse.setService(Service.MAP);
////        understandResponse.setOperation(MapManager.Operation.POSITION);
////
//////        String answerStr = ResWrapper.getInstance().getString(R.string.locating);
////////        understandResponse.setText( answerStr;
//////        understandResponse.setAnswer(AnswerManager.getInstance().creatAnswer(answerStr));
////
////        switch (type) {
////            case TYPE_WEATHER:
////                setRCCode(understandResponse);
////                break;
////        }
////
////
//////        AssistantViewHelper.getInstance().setReply(understandResponse, false);
////
////        return understandResponse;
//
//    }
//
//
//
////    public void locate(String type, ILocator locator) {
////
////
////            this.type = type;
////            this.locator = locator;
////
////            Zog.i("location start");
////            Context context = ResWrapper.getInstance().getApplicationContext();
////
////            final LocationClient lc = new LocationClient(
////                    context.getApplicationContext());
////            BDLocationListener bdLocationListener = new BDLocationListener() {
////                @Override
////                public void onReceiveLocation(BDLocation bdLocation) {
////                    lc.stop();
////
////                    onLocationRecevied(bdLocation);
////                    Zog.i("locate success " + bdLocation.getCity());
////                }
////            };
////
////
////            LocationUtils.startLocation(lc, CoorType.BD09LL, bdLocationListener);
////    }
//
//
//    @Subscribe(threadMode = ThreadMode.MAIN)
//    public void onLocationRecevied(BDLocation bdLocation) {
//        Zog.i("onLocationRecevied locator" + locator);
//        if (locator != null) {
//            locator.onLocationRecevied(type, bdLocation);
//        }
//        EventBusTools.unregister(this);
//
//    }
//
//}
