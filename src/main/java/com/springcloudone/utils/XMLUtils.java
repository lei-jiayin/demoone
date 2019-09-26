package com.springcloudone.utils;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import java.io.*;
import java.util.Iterator;
import java.util.List;

/**
 * XML操作工具示例
 * SAXReader DOM4J解析器对象
 * @author xw
 * @date 2019/9/23 9:26
 */
public class XMLUtils {

    /**
     * 获取xml文件的根节点
     * @param xmlPath
     * @return
     * @throws DocumentException
     */
    public static Element getRootElement(String xmlPath) throws DocumentException {
        SAXReader sax=new SAXReader();//创建一个SAXReader对象
        File xmlFile=new File(xmlPath);//根据指定的路径创建file对象
        Document document=sax.read(xmlFile);//获取document对象,如果文档无节点，则会抛出Exception提前结束
        Element root=document.getRootElement();//获取根节点
        return root;
    }

    public void getNodes(Element node){
        System.out.println("--------------------");
        //当前节点的名称、文本内容和属性
        System.out.println("当前节点名称："+node.getName());//当前节点名称
        System.out.println("当前节点的内容："+node.getTextTrim());//当前节点内容
        List<Attribute> listAttr=node.attributes();//当前节点的所有属性的list
        for(Attribute attr:listAttr){//遍历当前节点的所有属性
            String name=attr.getName();//属性名称
            String value=attr.getValue();//属性的值
            System.out.println("属性名称："+name+"属性值："+value);
        }
        //递归遍历当前节点所有的子节点
        List<Element> listElement=node.elements();//所有一级子节点的list
        for(Element e:listElement){//遍历所有一级子节点
            this.getNodes(e);//递归
        }
    }


    /**
     * 删除指定的属性
     * @param root xml文件的根节点
     * @param nodeName 指定删除属性的节点
     * @param attributeName 删除属性的名字
     * @return
     */
    public static void delAttribute(Element root,String nodeName, String attributeName) throws NullPointerException{
        //获取指定名字的节点，无此节点的会报NullPointerException,时间问题不做此情况的判断与处理了
        Element node=root.element(nodeName);
        Attribute attr=node.attribute(attributeName);//获取此节点指定的属性,无此节点的会报NullPointerException
        node.remove(attr);
    }

    /**
     * 修改指定节点指定属性的值
     * @param root 根节点
     * @param nodeName 指定节点名称
     * @param attributeName 指定节点的指定属性
     * @param attributeValue 修改的值
     */
    public static void editAttributeValue(Element root, String nodeName, String attributeName, String attributeValue){
        Element node=root.element(nodeName);
        Attribute attrDate=node.attribute(attributeName);//获取此节点的指定属性
        attrDate.setValue(attributeValue);//更改此属性值
    }

    /**
     * 为指定节点添加新的属性及值
     * @param root 根节点
     * @param nodeName 指定的节点名称
     * @param attributeName 新的属性名
     * @param attributeValue 新的属性值
     */
    public static void addAttribute(Element root,String nodeName, String attributeName, String attributeValue){
        Element node = root.element(nodeName);
        // 添加新属性并赋值
        node.addAttribute(attributeName, attributeValue);
    }

    /**
     * 为指定的节点添加子节点
     * @param node 指定节点
     * @param nodeName 子节点名称
     * @param content 内容
     */
    public void addNode(Element node,String nodeName,String content){
        Element newNode=node.addElement(nodeName);//对指定的节点node新增子节点,名为nodeName
        newNode.setText(content);//对新增的节点添加文本内容content
    }

    /**
     * 保存document到指定文件
     * @param document
     * @param xmlFile
     * @throws IOException
     */
    public void saveDocument(Document document,File xmlFile) throws IOException {
        Writer osWrite=new OutputStreamWriter(new FileOutputStream(xmlFile));//创建输出流
        OutputFormat format = OutputFormat.createPrettyPrint();  //获取输出的指定格式
        format.setEncoding("UTF-8");//设置编码 ，确保解析的xml为UTF-8格式
        XMLWriter writer = new XMLWriter(osWrite,format);//XMLWriter 指定输出文件以及格式
        writer.write(document);//把document写入xmlFile指定的文件(可以为被解析的文件或者新创建的文件)
        writer.flush();
        writer.close();
    }



    public static void queryXML(String xmlPath){
        //创建DOM4J解析器对象
        SAXReader saxReader = new SAXReader();
        try {
            //读取xml文件，并生成document对象 现可通过document来操作文档
            Document document = saxReader.read(xmlPath);
            //获取到文档的根节点
            Element rootElement = document.getRootElement();
            System.out.println("根节点的名字是:" + rootElement.getName());
            //获取子节点列表
            Iterator it = rootElement.elementIterator();
            while (it.hasNext()) {
                Element fistChild = (Element) it.next();
                //System.out.println(fistChild.getName());
                //获取节点的属性值
                System.out.println(fistChild.attribute("name").getValue());
                //获取子节点的下一级节点
                Iterator iterator = fistChild.elementIterator();
                while (iterator.hasNext()) {
                    Element element = (Element) iterator.next();
                    System.out.println("\t" + element.attributeValue("name"));
                }
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }

    public static void addXML(String xmlPath){
        SAXReader saxReader = new SAXReader();
        try {
            Document document = saxReader.read(xmlPath);
            //获取到根节点
            Element rootElement = document.getRootElement();
            //添加一个子节点
            Element brand = rootElement.addElement("brand");
            //给当前节点添加属性
            brand.addAttribute("name", "魅族");
            Element type = brand.addElement("type");
            type.addAttribute("name", "s16");
            OutputStream os = new FileOutputStream(new File(xmlPath));
            XMLWriter xmlWriter = new XMLWriter(os);
            xmlWriter.write(rootElement);
            xmlWriter.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void editXML(String xmlPath){
        //创建DOM4J解析器对象
        SAXReader saxReader = new SAXReader();
        try {
            Document document = saxReader.read(xmlPath);
            //获取根节点
            Element rootElement = document.getRootElement();
            Iterator it = rootElement.elementIterator();
            while (it.hasNext()) {
                Element element = (Element) it.next();
                System.out.println(element.attributeValue("name"));
                if (element.attributeValue("name").equals("魅族")) {
                    Iterator iterator = element.elementIterator();
                    while (iterator.hasNext()) {
                        Element type = (Element) iterator.next();
                        if (type.attributeValue("name").equals("s16")) {
                            type.addAttribute("name", "16 pro");
                        }
                    }
                }
            }
            XMLWriter xmlWriter = new XMLWriter(new FileOutputStream(new File(xmlPath)));
            xmlWriter.write(document);
            xmlWriter.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void delXMl(String xmlPath){
        SAXReader saxReader = new SAXReader();
        try {
            Document document = saxReader.read(xmlPath);
            Element rootElement = document.getRootElement();
            Iterator iterator = rootElement.elementIterator();
            while (iterator.hasNext()) {
                Element brand = (Element) iterator.next();
                if ("魅族".equals(brand.attributeValue("name"))) {
                    //通过父节点来删除子节点
                    brand.getParent().remove(brand);
                }
            }
            XMLWriter xmlwriter = new XMLWriter(new FileOutputStream(new File(xmlPath)));
            xmlwriter.write(document);
            xmlwriter.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
