package com.springcloudone.utils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * 图片处理
 * @author xw
 * @date 2019/7/25 14:23
 */
public class ImageUtils {

    /**
     * 图片 水平镜像
     * 耗时严重不推荐
     * @param imageFile 原图片
     * @param tarFile 目标图片
     */
    public static void mir(String imageFile,String tarFile){
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
        }catch (Exception e) {
            throw new RuntimeException("image error from imageUtils", e);
        }
    }

    public static void main(String[] args) throws IOException {
/*        String imgFile = "E:/1aydhyj/hf1.jpg";
        String tarFile = "E:/1aydhyj/hf11.jpg";
        mir(imgFile, tarFile);*/

        String imgFile1 = "E:/1aydhyj/hf2.jpg";
        String tarFile1 = "E:/1aydhyj/hf22.jpg";
        mir(imgFile1, tarFile1);
    }

}
