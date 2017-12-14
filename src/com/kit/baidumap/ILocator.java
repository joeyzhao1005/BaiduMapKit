package com.kit.baidumap;

import com.baidu.location.BDLocation;

public interface ILocator {

    /**
     * 得到城市的名字
     */
    String getCity();


    void onLocationRecevied(String type,BDLocation bdLocation);

    /**
     * 去定位的时候
     */
    void locate(String type);

}