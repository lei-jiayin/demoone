package com.springcloudone.utils;

/**
 * 关于地图的事
 *
 * @author xw
 * @date 2019/11/4 10:42
 */
public class MapUtils {

    /**
     * 地球半径
     */
    private static final double EARTH_RADIUS = 6378.137;

    /**
     * 经纬度转换成弧度
     *
     * @param d
     * @return
     */
    private static double rad(double d) {
        return d * Math.PI / 180.0;
    }

    /**
     * 计算两个坐标点之间的距离
     * Add by 成长的小猪（Jason.Song） on 2017/11/01
     * http://blog.csdn.net/jasonsong2008
     *
     * @param firstLatitude   第一个坐标的纬度
     * @param firstLongitude  第一个坐标的经度
     * @param secondLatitude  第二个坐标的纬度
     * @param secondLongitude 第二个坐标的经度
     * @return 返回两点之间的距离，单位：公里/千米
     */
    public static double getDistance(double firstLatitude, double firstLongitude,
                                     double secondLatitude, double secondLongitude) {
        double firstRadLat = rad(firstLatitude);
        double firstRadLng = rad(firstLongitude);
        double secondRadLat = rad(secondLatitude);
        double secondRadLng = rad(secondLongitude);

        double a = firstRadLat - secondRadLat;
        double b = firstRadLng - secondRadLng;
        double cal = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2) + Math.cos(firstRadLat)
                * Math.cos(secondRadLat) * Math.pow(Math.sin(b / 2), 2))) * EARTH_RADIUS;
        double result = Math.round(cal * 10000d) / 10000d;
        return result;
    }

    /**
     * 计算两个坐标点之间的距离
     * Add by 成长的小猪（Jason.Song） on 2017/11/01
     * http://blog.csdn.net/jasonsong2008
     *
     * @param firstPoint  第一个坐标点的（纬度,经度） 例如："31.2553210000,121.4620020000"
     * @param secondPoint 第二个坐标点的（纬度,经度） 例如："31.2005470000,121.3269970000"
     * @return 返回两点之间的距离，单位：公里/千米
     */
    public static double GetPointDistance(String firstPoint, String secondPoint) {
        String[] firstArray = firstPoint.split(",");
        String[] secondArray = secondPoint.split(",");
        double firstLatitude = Double.valueOf(firstArray[0].trim());
        double firstLongitude = Double.valueOf(firstArray[1].trim());
        double secondLatitude = Double.valueOf(secondArray[0].trim());
        double secondLongitude = Double.valueOf(secondArray[1].trim());
        return getDistance(firstLatitude, firstLongitude, secondLatitude, secondLongitude);
    }

/*————————————————
    版权声明：本文为CSDN博主「jasonsong2008」的原创文章，遵循 CC 4.0 BY-SA 版权协议，转载请附上原文出处链接及本声明。
    原文链接：https://blog.csdn.net/jasonsong2008/article/details/78434237*/

    public static void main(String[] args) {
        String firstPoint = "30.618919,114.254585";
        String secondPoint = "30.528671,114.317792";
        double distance = GetPointDistance(firstPoint, secondPoint);
        System.out.println(distance);
    }

}
