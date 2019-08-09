package com.springcloudone.utils;

import com.baidu.aip.speech.AipSpeech;
import com.baidu.aip.speech.TtsResponse;
import com.baidu.aip.util.Util;
import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;
import it.sauronsoftware.jave.Encoder;
import it.sauronsoftware.jave.MultimediaInfo;
import javazoom.jl.player.Player;
import org.json.JSONObject;
import org.springframework.util.StringUtils;

import java.io.*;
import java.util.HashMap;

/**
 * 尝试音乐播放 包jLayer
 * @author xw
 * @date 2019/7/29 17:41
 */
public class MusicUtils {


    private static String MUSIC_PATH = "e:/CloudMusic/111.mp3";
    private static String FILE_PATH = "D:\\迅雷下载\\xw\\xx.txt";

    // 百度语音 API
    private static final String APP_ID = "16913705";
    private static final String API_KEY = "aBG3sfHSXQkmCeAreN7zZUqI";
    private static final String SECRET_KEY= "QKvUReiwqqraX0rC9PXySs6yEGP5TSTY";

    /**
     * Jlayer
     * @param musicPath
     */
    public static void play(String musicPath){
        // 播放一个 mp3 音频文件, 代码很简单
        File file = new File(musicPath);
        Player player = null;
        try {
            player = new Player(new FileInputStream(file));
            int position = player.getPosition();
            System.out.println(position);
            player.play();
            int position1 = player.getPosition();
            System.out.println(position1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * jacob
     * @param filePath
     */
    public static void voice(String filePath){
        // 获取音响
        ActiveXComponent sap = new ActiveXComponent("sapi.SpVoice");

        try {
            // 将源文件写入输出流
            File file = new File(filePath);
            FileReader fr = new FileReader(file);
            // 从缓存中取出数据
            BufferedReader bf = new BufferedReader(fr);
            String content = bf.readLine();
            // 测试一下 有没有拿到数据
            System.out.println(content);

            // 调节语速 音量大小
            sap.setProperty("Volume", new Variant(100));
            sap.setProperty("Rate", new Variant(0));

            Dispatch xj = sap.getObject();
            // 执行朗读 没有读完就继续读
            while (content != null){
                Dispatch.call(xj, "Speak", new Variant(content));
                content = bf.readLine();
            }
            xj.safeRelease();
            bf.close();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    /**
     * baidu
     * @param path
     */
    public static String synthesis(String path) {
        String content = "";
        try{
            // 将源文件写入输出流
            File file = new File(path);
            FileReader fr = new FileReader(file);
            // 从缓存中取出数据
            BufferedReader bf = new BufferedReader(fr);
            byte[] buff = new byte[2048];
            String tem;
            while ((tem=bf.readLine()) != null){
                content+=tem;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        AipSpeech client = new AipSpeech(APP_ID, API_KEY, SECRET_KEY);
        // 可选：设置网络连接参数
        client.setConnectionTimeoutInMillis(2000);
        client.setSocketTimeoutInMillis(60000);

        // 可选：设置代理服务器地址, http和socket二选一，或者均不设置
//        client.setHttpProxy("proxy_host", proxy_port);  // 设置http代理
//        client.setSocketProxy("proxy_host", proxy_port);  // 设置socket代理

        // 可选：设置log4j日志输出格式，若不设置，则使用默认配置
        // 也可以直接通过jvm启动参数设置此环境变量
//        System.setProperty("aip.log4j.conf", "path/to/your/log4j.properties");
        // 设置可选参数
        HashMap<String, Object> options = new HashMap<String, Object>();
        options.put("spd", "5");//语速，取值0-9，默认为5中语速      非必选
        options.put("pit", "5");//音调，取值0-9，默认为5中语调      非必选
        options.put("per", "4");//发音人选择, 0为女声，1为男声，3为情感合成-度逍遥，4为情感合成-度丫丫，默认为普通女 非必选
        TtsResponse res = client.synthesis(content, "zh", 1, options);
        JSONObject result = res.getResult();  //服务器返回的内容，合成成功时为null,失败时包含error_no等信息
        if (!StringUtils.isEmpty(result)) {
            System.out.printf("error：" + result.toString());
        }
        byte[] data = res.getData();            //生成的音频数据
        JSONObject res1 = res.getResult();
        String name = System.currentTimeMillis() + "output.mp3";
        if (data != null) {
            try {
                Util.writeBytesToFileSystem(data, name);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (res1 != null) {
            System.out.println(res1.toString(2));
        }
        return name;
    }


    public static Long getSpeechLength(String path){
        File source = new File(path);
        long ls = 0;
        Encoder encoder = new Encoder();
        try {
            MultimediaInfo multimediaInfo = encoder.getInfo(source);

            ls = multimediaInfo.getDuration()/1000;
//            System.out.println("此音频频时长为:"+ls/1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ls;
    }

    /*public static void main(String[]args){
        String path= ClassLoader.getSystemResource("test.mp4").getPath();
        File source = new File(path);
        Encoder encoder = new Encoder(new OSXffmpeglocator());
        try {
            MultimediaInfo multimediaInfo = encoder.getInfo(source);

            long ls = multimediaInfo.getDuration();
            System.out.println("此视频时长为:"+ls);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }*/



    public static void main(String[] args) {
//        play(MUSIC_PATH);
//        voice(FILE_PATH);
//        String name = synthesis(FILE_PATH);
//        play(name);

        // 测试获取长度
        System.out.println(getSpeechLength(MUSIC_PATH));
    }
}


