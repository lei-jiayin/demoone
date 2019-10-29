package com.springcloudone.utils;


import net.sf.json.JSONObject;

import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.List;

/**
 * 微信验证工具
 * @author xw
 * @date 2019/8/22 9:46
 */
public class WeiXinUtil {
    private static String token = "token";
    public final static String APP_ID = "wxe1fe1715b789aab0";
    public final static String APPSECRET = "26cac5a1998713555fa399f23129530c";



    /**
     * 输入自己的id跟密码，获取微信的安全密令字符串
     * @param APP_ID
     * @param APPSECRET
     * @return
     */
    public static String getAccessToken( String APP_ID,String APPSECRET) {
        //设置变量 url与返回值其中url使用拼接带入参数APP_ID， APPSECRET
        String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="
                + APP_ID+ "&secret=" + APPSECRET;
        String accessToken = null;
        try {
            //设置链接
            URL urlGet = new URL(url);
            //设置外网代理链接
            InetSocketAddress addr = new InetSocketAddress("192.168.1.23",80);
            Proxy proxy = new Proxy(Proxy.Type.HTTP, addr);
            //启动链接
            HttpURLConnection http = (HttpURLConnection) urlGet .openConnection(proxy);
            //设置链接参数与要求
            http.setRequestMethod("GET"); // 必须是get方式请求
            http.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            http.setDoOutput(true);
            http.setDoInput(true);
            System.setProperty("sun.net.client.defaultConnectTimeout", "30000");// 连接超时30�?
            System.setProperty("sun.net.client.defaultReadTimeout", "30000"); // 读取超时30�?
//            链接
            http.connect();
            //获取返回值json字节流
            InputStream is = http.getInputStream();
            int size = is.available();
            byte[] jsonBytes = new byte[size];
            is.read(jsonBytes);
            //转化成字符串
            String message = new String(jsonBytes, "UTF-8");
//            转化成json对象然后返回accessToken属性的值
            JSONObject demoJson = JSONObject.fromObject(message);
            accessToken = demoJson.getString("access_token");
            System.out.println(accessToken);
            is.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return accessToken;
    }








    /**
     * 判断是否是微信浏览器
     * @param request
     * @return true为微信 false为其他
     */
    public static boolean isWechat(HttpServletRequest request){
        String ua = request.getHeader("User-Agent").toLowerCase();
        if (ua.indexOf("micromessenger") > -1) {
            return true;
        }
        return false;
    }

    /**
     * 判断是手机端还是PC端
     * @param request
     * @return
     */
    public static boolean isMobile(HttpServletRequest request) {
        List<String> mobileAgents = Arrays.asList("ipad", "iphone os", "rv:1.2.3.4", "ucweb", "android", "windows ce", "windows mobile");
        String ua = request.getHeader("User-Agent").toLowerCase();
        for (String sua : mobileAgents) {
            if (ua.indexOf(sua) > -1) {
                return true;//手机端
            }
        }
        return false;//PC端
    }

    /**
     * 检验签名
     * @param signature
     * @param timestamp
     * @param nonce
     * @return
     */
    public static boolean checkSignature(String signature, String timestamp, String nonce){
        // 1.将token、timestamp、nonce三个参数进行字典序排序
        String[] arr = new String[]{token,timestamp,nonce};
        Arrays.sort(arr);

        // 2.将三个参数字符串拼接成一个字符串进行sha1加密
        StringBuilder content = new StringBuilder();
        for (int i=0; i < arr.length; i++){
            content.append(arr[i]);
        }
        MessageDigest md = null;
        String tmpStr = null;
        try{
            md = MessageDigest.getInstance("SHA-1");
            // 将三个参数字符串拼接成一个字符串进行sha1加密
            byte[] digest = md.digest(content.toString().getBytes());
            tmpStr = StringUtil.byteToStr(digest);
        } catch (Exception e){
            e.printStackTrace();
        }
        content = null;
        // 3.将sha1加密后的字符串可与signature对比 标识请求来源于微信
        return tmpStr != null ? tmpStr.equals(signature.toUpperCase()) : false;
    }
}
