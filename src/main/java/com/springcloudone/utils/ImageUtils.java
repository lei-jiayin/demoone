package com.springcloudone.utils;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Objects;

/**
 * 图片处理
 *
 * @author xw
 * @date 2019/7/25 14:23
 */
public class ImageUtils {

    /**
     * 图片 水平镜像
     * 耗时严重不推荐
     *
     * @param imageFile 原图片
     * @param tarFile   目标图片
     */
    public static void mir(String imageFile, String tarFile) {
        try {
            long start = System.currentTimeMillis();
            // 读取图片
            BufferedImage bufferedImage = ImageIO.read(new File(imageFile));

            // 获取图片的宽高
            int width = bufferedImage.getWidth();
            int height = bufferedImage.getHeight();

            // 读取图片的所有像素
            int[] rgbs = bufferedImage.getRGB(0, 0, width, height, null, 0, width);

            // 创建新的图片
            // BufferedImage newImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

            // 对图片的像素矩阵进行水平镜像
            for (int row = 0; row < height; row++) {
                for (int col = 0; col < width / 2; col++) {
                    int temp = rgbs[row * width + col];
                    rgbs[row * width + col] = rgbs[row * width + (width - 1 - col)];
                    rgbs[row * width + (width - 1 - col)] = temp;
                }

                // 把水平镜像后的像素矩阵设置回 buffimage
                bufferedImage.setRGB(0, 0, width, height, rgbs, 0, width);

                //把修改过的 buffImage 保存到本地
                ImageIO.write(bufferedImage, "JPEG", new File(tarFile));
            }
            long end = System.currentTimeMillis();
            System.out.println("图片生成完成，耗时：" + (end - start) + "ms");
        } catch (Exception e) {
            throw new RuntimeException("image error from imageUtils", e);
        }
    }

    private static String strNetImageToBase64;

    /**
     * 网络图片转换成base64方法
     *
     * @param netImagePath
     */
    private static String NetImageToBase64(String netImagePath) {
        final ByteArrayOutputStream data = new ByteArrayOutputStream();
        try {
            // 创建URL
            URL url = new URL(netImagePath);
            final byte[] by = new byte[1024];
            // 创建链接
            final HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(5000);

            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        InputStream is = conn.getInputStream();
                        // 将内容读取内存中
                        int len = -1;
                        while ((len = is.read(by)) != -1) {
                            data.write(by, 0, len);
                        }
                        // 对字节数组Base64编码
                        BASE64Encoder encoder = new BASE64Encoder();
                        strNetImageToBase64 = encoder.encode(data.toByteArray());
                        System.out.println("网络图片转换Base64:" + strNetImageToBase64);
                        // 关闭流
                        is.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
            return strNetImageToBase64;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return strNetImageToBase64;
    }
/* ————————————————
    版权声明：本文为CSDN博主「zhang-quan」的原创文章，遵循CC 4.0 by-sa版权协议，转载请附上原文出处链接及本声明。
    原文链接：https://blog.csdn.net/qq_38508087/article/details/84671522*/


    /**
     * 将本地图片转换成 base64编码
     *
     * @param imgPath
     * @throws IOException
     */
    private static String ImageToBase64(String imgPath) {
        byte[] data = null;
        // 读取图片字节数组
        try {
            InputStream in = new FileInputStream(imgPath);
            data = new byte[in.available()];
            in.read(data);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 对字节数组Base64编码
        BASE64Encoder encoder = new BASE64Encoder();
        // 返回Base64编码过的字节数组字符串
        System.out.println("本地图片转换Base64:" + encoder.encode(Objects.requireNonNull(data)));
        return encoder.encode(Objects.requireNonNull(data));
    }
/* ————————————————
    版权声明：本文为CSDN博主「zhang-quan」的原创文章，遵循CC 4.0 by-sa版权协议，转载请附上原文出处链接及本声明。
    原文链接：https://blog.csdn.net/qq_38508087/article/details/84671522*/


    public static boolean GenerateImage(String imgStr, String imgFilePath) {
        if (imgStr == null) // 图像数据为空
            return false;
        BASE64Decoder decoder = new BASE64Decoder();
        try {
            // Base64解码
            byte[] bytes = decoder.decodeBuffer(imgStr);
            for (int i = 0; i < bytes.length; ++i) {
                if (bytes[i] < 0) {// 调整异常数据
                    bytes[i] += 256;
                }
            }
            // 生成jpeg图片
            OutputStream out = new FileOutputStream(imgFilePath);
            out.write(bytes);
            out.flush();
            out.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
/* ————————————————
    版权声明：本文为CSDN博主「CASS_Y」的原创文章，遵循CC 4.0 by-sa版权协议，转载请附上原文出处链接及本声明。
    原文链接：https://blog.csdn.net/qq_36826248/article/details/81129946*/


    public static void main(String[] args) throws IOException {
        // 镜像测试
        /*String imgFile1 = "E:/1aydhyj/hf2.jpg";
        String tarFile1 = "E:/1aydhyj/hf22.jpg";
        mir(imgFile1, tarFile1);*/

        // 本地图片/网络图片 转base64编码
        //第一个:把网络图片装换成Base64
        String netImagePath = "http://thirdwx.qlogo.cn/mmopen/vi_32/DDyXhPqm6Z5RxzT50u6tWyKFXV7pHlyYfqcZjibRkZlJrTtsOibgsUuXtbL3icUaPBS9Ay8PaRNt0QLq0Ppc6oicCg/132";
        //下面是网络图片转换Base64的方法
         NetImageToBase64(netImagePath);
        //设置文件名
        String newName = System.currentTimeMillis()+ ".jpg";

        //生成图片的路径
        String pImagesPath = "E:\\1aydhyj\\" + newName;


        //下面是本地图片转换Base64的方法
        String imagePath = "E:\\1aydhyj\\publicImg.jpg";
        String netImgStr = ImageToBase64(imagePath);
        GenerateImage(netImgStr,pImagesPath);
/* ————————————————
        版权声明：本文为CSDN博主「zhang-quan」的原创文章，遵循CC 4.0 by-sa版权协议，转载请附上原文出处链接及本声明。
        原文链接：https://blog.csdn.net/qq_38508087/article/details/84671522*/
    }

}
