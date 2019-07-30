package com.springcloudone.swingdemo;

import javax.swing.*;

/**
 * 简单窗口程序
 * @author xw
 * @date 2019/7/25 17:00
 */
public class Window {

    private static Integer width;
    private static Integer height;

    static {
        System.out.println("初始化长宽。。。。");
        width = 500;
        height = 500;
    }

    public static void main(String[] args) {
        // 1 创建顶层容器
        JFrame jf = new JFrame("测试窗口");
        // 创建窗口
        jf.setSize(width, height); // 设置窗口大小
        // 把窗口位置设置到屏幕中心
        jf.setLocationRelativeTo(null);
        // 当点击退出按钮时程序会退出
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // 2 创建中间容器
        JPanel panel = new JPanel(); // 面板容器 默认布局管理器
        // 3 创建一个基本组件（按钮） 并添加到 面板容器 中
        JButton btn = new JButton("测试按钮");
        panel.add(btn);

        // 4 把面板容器 作为窗口的内容面板 设置到 窗口
        jf.setContentPane(panel);

        // 5 显示窗口，前面创建的信息都在内存中。jf.setVisible(true) 显示窗口在屏幕上
        jf.setVisible(true);
    }

}
