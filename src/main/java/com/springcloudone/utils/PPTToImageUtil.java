package com.springcloudone.utils;

import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xslf.usermodel.*;
import org.openxmlformats.schemas.drawingml.x2006.main.*;
import org.openxmlformats.schemas.presentationml.x2006.main.CTGroupShape;
import org.openxmlformats.schemas.presentationml.x2006.main.CTShape;
import org.openxmlformats.schemas.presentationml.x2006.main.CTSlide;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PPTToImageUtil {
    public static final int ppSaveAsPNG = 17;

    public static void main(String[] args) {
        doPPTtoImage2007(new File("E:\\1aydhyj\\test1.pptx"), "E:\\1aydhyj\\jiaoshui", "a", "png");
    }

    /**
     * 设置PPTX字体
     *
     * @param slide
     */
    private static void setFont(XSLFSlide slide) {
        for (XSLFShape shape : slide.getShapes()) {
            if (shape instanceof XSLFTextShape) {
                XSLFTextShape txtshape = (XSLFTextShape) shape;
                for (XSLFTextParagraph paragraph : txtshape.getTextParagraphs()) {
                    List<XSLFTextRun> truns = paragraph.getTextRuns();
                    for (XSLFTextRun trun : truns) {
                        trun.setFontFamily("宋体");
                    }
                }
            }

        }
    }

    /**
     * PPT转图片 （jpeg）(2007)
     *
     * @param file
     * @param path    存放路径
     * @param picName 图片前缀名称 如 a 生成后为a_1,a_2 ...
     * @param picType 转成图片的类型，无点 如 jpg bmp png ...
     * @return true/false
     */
    public static boolean doPPTtoImage2007(File file, String path, String picName, String picType) {
        try {
            // 检查文件是否是PPT
            boolean isppt = checkFile(file);
            if (!isppt) {
                return false;
            }
            // 将PPT文件写入输入流
            FileInputStream is = new FileInputStream(file);
            XMLSlideShow xmlSlideShow = new XMLSlideShow(is);
            List<XSLFSlide> xslfSlides = xmlSlideShow.getSlides();
            Dimension pageSize = xmlSlideShow.getPageSize();

            is.close();
            for (int i = 0; i < xslfSlides.size(); i++) {
                System.out.print("第" + i + "页。");
                setFont(xslfSlides.get(i));
                BufferedImage img = new BufferedImage(pageSize.width,
                        pageSize.height, BufferedImage.TYPE_INT_RGB);

                Graphics2D graphics = img.createGraphics();
                graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                        RenderingHints.VALUE_ANTIALIAS_ON);
                graphics.setRenderingHint(RenderingHints.KEY_RENDERING,
                        RenderingHints.VALUE_RENDER_QUALITY);
                graphics.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                        RenderingHints.VALUE_INTERPOLATION_BICUBIC);
                graphics.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS,
                        RenderingHints.VALUE_FRACTIONALMETRICS_ON);

                graphics.setPaint(Color.white);
                graphics.fill(new Rectangle2D.Float(0, 0, pageSize.width,
                        pageSize.height));
                xslfSlides.get(i).draw(graphics);
                FileOutputStream out = new FileOutputStream(path + "/" +
                        +(i + 1) + "." + picType);
                javax.imageio.ImageIO.write(img, "jpeg", out);
                out.close();
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * PPT转图片 （jpeg）(2003)
     *
     * @param file
     * @param path    存放路径
     * @param picName 图片前缀名称 如 a 生成后为a_1,a_2 ...
     * @param picType 转成图片的类型，无点 如 jpg bmp png ...
     * @return true/false
     */
    public static boolean doPPTtoImage(File file, String path, String picName, String picType) {
        boolean isppt = checkFile(file);
        if (!isppt) {
            return false;
        }
        try {
            FileInputStream is = new FileInputStream(file);

//			SlideShow ppt = new SlideShow(is);
            XMLSlideShow ppt = new XMLSlideShow(is);
            is.close();
            // Dimension pgsize = ppt.getPageSize();
//			Slide[] slide = ppt.getSlides();
            List<XSLFSlide> slide = ppt.getSlides();
            Dimension pgSize = ppt.getPageSize();
            for (int i = 0; i < slide.size(); i++) {
                System.out.print("第" + i + "页。");
                BufferedImage img = new BufferedImage(pgSize.width,
                        pgSize.height, BufferedImage.TYPE_INT_RGB);

                Graphics2D graphics = img.createGraphics();
                graphics.setPaint(Color.white);
                graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                        RenderingHints.VALUE_ANTIALIAS_ON);
                graphics.setRenderingHint(RenderingHints.KEY_RENDERING,
                        RenderingHints.VALUE_RENDER_QUALITY);
                graphics.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                        RenderingHints.VALUE_INTERPOLATION_BICUBIC);
                graphics.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS,
                        RenderingHints.VALUE_FRACTIONALMETRICS_ON);
                graphics.fill(new Rectangle2D.Float(0, 0, pgSize.width,
                        pgSize.height));
                slide.get(i).draw(graphics);

                FileOutputStream out = new FileOutputStream(path + "/"
                        + (i + 1) + "." + picType);
                javax.imageio.ImageIO.write(img, "jpeg", out);
                out.close();
            }
            return true;
        } catch (FileNotFoundException e) {
            System.out.println(e);
        } catch (IOException e) {
        }
        return false;
    }

    /**
     * PPT转图片 （jpeg）(2003)
     *
     * @param file
     * @param path    存放路径
     * @param picName 图片前缀名称 如 a 生成后为a_1,a_2 ...
     * @param picType 转成图片的类型，无点 如 jpg bmp png ...
     * @return true/false
     */
    public static boolean doPPTtoImage(File file, String path, String picName, String picType, int w,
                                       int h) {
        boolean isppt = checkFile(file);
        if (!isppt) {
            return false;
        }
        try {
            FileInputStream is = new FileInputStream(file);
            XMLSlideShow ppt = new XMLSlideShow(is);
            is.close();
            // Dimension pgsize = ppt.getPageSize();
            List<XSLFSlide> slide = ppt.getSlides();
            for (int i = 0; i < slide.size(); i++) {
                System.out.print("第" + i + "页。");
                BufferedImage img = new BufferedImage(w,
                        h, BufferedImage.TYPE_INT_RGB);

                Graphics2D graphics = img.createGraphics();
                graphics.setPaint(Color.white);
                graphics.fill(new Rectangle2D.Float(0, 0, w,
                        h));
                slide.get(i).draw(graphics);

                FileOutputStream out = new FileOutputStream(path + "/" + picName + "_"
                        + (i + 1) + "." + picType);
                javax.imageio.ImageIO.write(img, "jpeg", out);
                out.close();
            }
            return true;
        } catch (FileNotFoundException e) {
            System.out.println(e);
        } catch (IOException e) {
        }
        return false;
    }

    /**
     * 检查文件是否是PPT
     * true 是
     * false 不是
     *
     * @param file
     * @return
     */
    private static boolean checkFile(File file) {
        int pos = file.getName().lastIndexOf(".");
        String extName = "";
        if (pos >= 0) {
            extName = file.getName().substring(pos);
        }
        if (".ppt".equalsIgnoreCase(extName) || ".pptx".equalsIgnoreCase(extName)) {
            return true;
        }
        return false;
    }

    /**
     * 根据ppt文件生成图片
     *
     * @param sourceFile
     * @param pdfFile
     */
    @SuppressWarnings("deprecation")
    public static synchronized void ppt2007Img(String sourceFile, String pdfFile) {
        try {
            FileInputStream is = new FileInputStream(sourceFile);
            XMLSlideShow ppt = new XMLSlideShow(is);
            is.close();
            Dimension pgsize = ppt.getPageSize();
            List<XSLFSlide> slides = ppt.getSlides();
            for (int i = 0; i < slides.size(); i++) {
                System.out.print("第" + i + "页。");
                //设置字体为宋体，解决中文乱码问题
                CTSlide xmlObject = slides.get(i).getXmlObject();
                CTGroupShape spTree = xmlObject.getCSld().getSpTree();
                CTShape[] spArray = spTree.getSpArray();
                for (CTShape shape : spArray) {
                    CTTextBody txBody = shape.getTxBody();
                    if (txBody == null) {
                        continue;
                    }
                    CTTextParagraph[] pArray = txBody.getPArray();
                    CTTextFont font = CTTextFont.Factory.parse(
                            "<xml-fragment xmlns:a=\"http://schemas.openxmlformats.org/drawingml/2006/main\" xmlns:r=\"http://schemas.openxmlformats.org/officeDocument/2006/relationships\" xmlns:p=\"http://schemas.openxmlformats.org/presentationml/2006/main\">" +
                                    "<a:rPr lang=\"zh-CN\" altLang=\"en-US\" dirty=\"0\" smtClean=\"0\"> " +
                                    "<a:latin typeface=\"+mj-ea\"/> " +
                                    "</a:rPr>" +
                                    "</xml-fragment>");
                    for (CTTextParagraph textParagraph : pArray) {
                        CTRegularTextRun[] textRuns = textParagraph.getRArray();
                        for (CTRegularTextRun textRun : textRuns) {
                            CTTextCharacterProperties properties = textRun.getRPr();
                            properties.setLatin(font);
                        }
                    }
                }
                BufferedImage img = new BufferedImage(pgsize.width, pgsize.height, BufferedImage.TYPE_INT_RGB);
                Graphics2D graphics = img.createGraphics();
                graphics.setPaint(Color.white);
                graphics.fill(new Rectangle2D.Float(0, 0, pgsize.width, pgsize.height));
                slides.get(i).draw(graphics);
                FileOutputStream out = new FileOutputStream(new File(pdfFile + File.separator + i + ".png"));
                ImageIO.write(img, "png", out);
                out.close();
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void renameFile(String path, String newName, String olName) {
        if (!olName.equals(newName)) {//新的文件名和以前文件名不同时,才有必要进行重命名
            File oldfile = new File(path + "/" + olName);
            File newfile = new File(path + "/" + newName);
            if (!oldfile.exists()) {
                return;//重命名文件不存在
            }
            if (newfile.exists()) {
                //若在该目录下已经有一个文件和新文件名相同，则不允许重命名
                System.out.println(newName + "已经存在！");
            } else {
                oldfile.renameTo(newfile);
            }
        } else {
            System.out.println("新文件名和旧文件名相同...");
        }
    }

    public static String formatNum(String name) {
        int index = 0;
        Pattern pattern = Pattern.compile("[0-9]");
        Matcher matcher = pattern.matcher(name);
        if (matcher.find()) {
            index = name.indexOf(matcher.group());
        }
        name = name.substring(index);
        name = name.substring(0, name.lastIndexOf("."));
        System.out.println(name);
        return name;
    }

    public static void ppt2003Img(String sourceFile, String tmpPicsDirName) throws OpenXML4JException {
        try {
            XMLSlideShow ppt = new XMLSlideShow(OPCPackage.open(sourceFile));
            Dimension pgsize = ppt.getPageSize();
            List<XSLFSlide> slides = ppt.getSlides();
            for (int i = 0; i < slides.size(); i++) {
                System.out.print("第" + i + "页。");
                BufferedImage img = new BufferedImage(pgsize.width, pgsize.height, BufferedImage.TYPE_INT_RGB);
                Graphics2D graphics = img.createGraphics();
                graphics.setPaint(Color.white);
                graphics.fill(new Rectangle2D.Float(0, 0, pgsize.width, pgsize.height));
                slides.get(i).draw(graphics);
                FileOutputStream out = new FileOutputStream(new File(tmpPicsDirName + File.separator + i + ".png"));
                ImageIO.write(img, "png", out);
                out.close();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}