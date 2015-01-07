package com.kit.baidumap.convert;

/**
 * 国测局坐标和百度坐标的相互转换
 */

public class GCJToBD {

    public static void main(String[] args) {

        double l = 113.69147;

        double a = 34.78425;

        double lng = bd_encrypt(l, a)[0];

        double lat = bd_encrypt(l, a)[1];

        System.out.println(lng);

        System.out.println(lat);

    }


    static double x_pi = 3.14159265358979324 * 3000.0 / 180.0;

    /**
     * 火星坐标系 (国测局标准)(GCJ-02) 转换为百度坐标系 (BD-09) 的转换算法
     *
     * @param gg_lon
     * @param gg_lat
     * @return
     */

    public static double[] bd_encrypt(double gg_lon, double gg_lat) {

        double[] d = new double[2];

        double x = gg_lon, y = gg_lat;

        double z = Math.sqrt(x * x + y * y) + 0.00002 * Math.sin(y * x_pi);

        double theta = Math.atan2(y, x) + 0.000003 * Math.cos(x * x_pi);

        double bd_lon = z * Math.cos(theta) + 0.0065;

        double bd_lat = z * Math.sin(theta) + 0.006;

        d[0] = bd_lon;

        d[1] = bd_lat;


        return d;

    }


    /**
     * BD-09 坐标转换成GCJ-02 坐标
     *
     * @param bd_lon 经度
     * @param bd_lat 纬度
     * @return
     */

    public static double[] bd_decrypt(double bd_lon, double bd_lat)


    {

        double[] d = new double[2];

        double x = bd_lon - 0.0065, y = bd_lat - 0.006;


        double z = Math.sqrt(x * x + y * y) - 0.00002 * Math.sin(y * x_pi);


        double theta = Math.atan2(y, x) - 0.000003 * Math.cos(x * x_pi);


        double gg_lon = z * Math.cos(theta);


        double gg_lat = z * Math.sin(theta);

        d[0] = gg_lon;

        d[1] = gg_lat;

        return d;

    }

}