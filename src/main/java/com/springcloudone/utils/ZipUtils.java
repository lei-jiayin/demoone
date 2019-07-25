package com.springcloudone.utils;

import java.io.*;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

/**
 * zip工具
 * @author xw
 * @date 2019/7/25 9:44
 */
public class ZipUtils {

    private static final Integer BUFFER_SIZE = 2048;

    public static void toZip(String srcDir, OutputStream out, Boolean keepDirStructure) throws RuntimeException{
        // 开始时间
        long startTime = System.currentTimeMillis();
        //压缩文件输出流
        ZipOutputStream zos = null;
        try{
            // 实例化
            zos = new ZipOutputStream(out);
            // 获取目标文件
            File sourceFile = new File(srcDir);
            //进行文件压缩
            compress(sourceFile, zos, sourceFile.getName(), keepDirStructure);
            // 结束时间
            long endTime = System.currentTimeMillis();
            //压缩所花费的时间
            System.out.println("压缩完成，耗时：" + (endTime - startTime) + "ms");

        }catch (Exception e){
            throw new RuntimeException("zip error from zipUtils", e);
        }finally {
            if (zos != null){
                try{
                    // 关闭压缩输出流
                    zos.close();
                } catch (IOException e){
                    e.printStackTrace();
                }
            }
        }

    }

    /**
     * 进行文件压缩
     * @param sourceFile 源文件
     * @param zos 压缩输出流
     * @param name 文件名
     * @param keepDirStructure 是否保存原来的目录结构，true：保留目录结构，false：所有文件跑到压缩包根目录下（可能会出现同名文件，压缩失败）
     */
    private static void compress(File sourceFile, ZipOutputStream zos, String name, Boolean keepDirStructure) throws Exception{
        byte[] buf = new byte[BUFFER_SIZE];
        // is file ?
        if (sourceFile.isFile()){
            // 向zip输出流中添加一个zip实体，参数为zip实体文件的名字
            zos.putNextEntry(new ZipEntry(name));
            // 拷贝文件到zip输出流中
            int len;
            FileInputStream in = new FileInputStream(sourceFile);
            while ((len = in.read(buf)) != -1){
                zos.write(buf, 0, len);
            }
            // 关闭实体与输入流
            zos.closeEntry();
            in.close();
        } else {
            File[] files = sourceFile.listFiles();
            if (files == null || files.length == 0){
                // 需要保留原来文件结构时，需要对空文件夹进行处理
                if(keepDirStructure){
                    // 空文件处理
                    zos.putNextEntry(new ZipEntry(name + "/"));
                    //  空文件 无需拷贝
                    zos.closeEntry();
                }
            }
            else {
                for (File file : files){
                    // 判断是否需要保留原来的文件结构
                    if (keepDirStructure){
                        // 注：file.getName() 前面需要带上父文件的名字加上斜杠
                        // 不然最后压缩包中就不能保留原来的文件结构：所有文件出现在压缩包根目录中
                        compress(file, zos, name + "/" + file.getName(), keepDirStructure);
                    }
                    else {
                        compress(file, zos, file.getName(), keepDirStructure);
                    }
                }
            }
        }
    }


    /**
     * 压缩方法二
     * @param srcFiles 需要压缩的文件列表
     * @param out 压缩文件输出流
     * @throws RuntimeException 抛出异常
     */
    public static void toZip(List<File> srcFiles, OutputStream out) throws RuntimeException{
        long startTime = System.currentTimeMillis();
        ZipOutputStream zos = null;
        try{
            zos =  new ZipOutputStream(out);
            for (File srcFile : srcFiles){
                byte[] buf = new byte[BUFFER_SIZE];
                zos.putNextEntry(new ZipEntry(srcFile.getName()));
                int len;
                FileInputStream in = new FileInputStream(srcFile);
                while ((len = in.read(buf)) != -1){
                    zos.write(buf, 0, len);
                }
                zos.closeEntry();
                in.close();
            }
            long endTime = System.currentTimeMillis();
            System.out.println("压缩完成，耗时：" + (endTime - startTime) + "ms");
        } catch (Exception e){
            throw new RuntimeException("zip error from zipUtils", e);
        } finally {
            if (zos != null){
                try {
                    zos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }



    public static void unZip(File srcFile, String destDirPath) throws RuntimeException {
        long start = System.currentTimeMillis();
        // 判断源文件是否存在
        if (!srcFile.exists()) {
            throw new RuntimeException(srcFile.getPath() + "所指文件不存在");
        }
        // 开始解压
        ZipFile zipFile = null;
        try {
            zipFile = new ZipFile(srcFile);
            Enumeration<?> entries = zipFile.entries();
            while (entries.hasMoreElements()) {
                ZipEntry entry = (ZipEntry) entries.nextElement();
                System.out.println("解压" + entry.getName());
                // 如果是文件夹，就创建个文件夹
                if (entry.isDirectory()) {
                    String dirPath = destDirPath + "/" + entry.getName();
                    File dir = new File(dirPath);
                    dir.mkdirs();
                } else {
                    // 如果是文件，就先创建一个文件，然后用io流把内容copy过去
                    File targetFile = new File(destDirPath + "/" + entry.getName());
                    // 保证这个文件的父文件夹必须要存在
                    if(!targetFile.getParentFile().exists()){
                        targetFile.getParentFile().mkdirs();
                    }
                    targetFile.createNewFile();
                    // 将压缩文件内容写入到这个文件中
                    InputStream is = zipFile.getInputStream(entry);
                    FileOutputStream fos = new FileOutputStream(targetFile);
                    int len;
                    byte[] buf = new byte[BUFFER_SIZE];
                    while ((len = is.read(buf)) != -1) {
                        fos.write(buf, 0, len);
                    }
                    // 关流顺序，先打开的后关闭
                    fos.close();
                    is.close();
                }
            }
            long end = System.currentTimeMillis();
            System.out.println("解压完成，耗时：" + (end - start) +" ms");
        } catch (Exception e) {
            throw new RuntimeException("unzip error from ZipUtils", e);
        } finally {
            if(zipFile != null){
                try {
                    zipFile.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }




    public static void main(String[] args) throws FileNotFoundException {
        /** 测试压缩方法1  */
/*        FileOutputStream fos1 = new FileOutputStream(new File("E:/mytest01.zip"));
        ZipUtils.toZip("E:/1aydhyj/表情包", fos1,true);*/

        /** 测试压缩方法2  */
/*        List<File> fileList = new ArrayList<>();
        fileList.add(new File("D:/zxing.png"));
        fileList.add(new File("D:/_error.log"));
        FileOutputStream fos2 = new FileOutputStream(new File("E:/mytest02.zip"));
        ZipUtils.toZip(fileList, fos2);*/

        // 测试解压缩方法
        File file = new File("E:/mytest02.zip");
        unZip(file,"E:/1aydhyj");
    }
}
