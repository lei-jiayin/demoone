package com.springcloudone.utils;

/**
 * @author xw
 * @date 2019/8/22 10:23
 */
public class StringUtil {

    /**
     * 将字节数组转换为16进制字符串
     * @param byteArrays
     * @return
     */
    public static String byteToStr(byte[] byteArrays){
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < byteArrays.length; i++) {
            str.append(byteToHexStr(byteArrays[i]));
        }
        return str.toString();
    }

    /**
     * 将字节转换为十六进制字符串
     * @param mbyte
     * @return
     */
    public static String byteToHexStr(byte mbyte){
        char[] DIGIT = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A','B', 'C', 'D', 'E', 'F' };
        char[] temp = new char[2];
        temp[0] = DIGIT[(mbyte >>> 4) & 0X0F];
        temp[1] = DIGIT[mbyte & 0X0F];
        String s =  new String(temp);
        return s;
    }





    public static void main(String[] args) {
/*        String str = "7月27日，都府堤社区的王德炎老人向绿色林林7又推荐了2户花园家庭，同时他再三强调：评的上评不上不紧要，重要的是希望你们来看看……@相关报道居民一天浇四次水 跟老天爷抢进度";
        System.out.println(str.length());*/
    }
}
