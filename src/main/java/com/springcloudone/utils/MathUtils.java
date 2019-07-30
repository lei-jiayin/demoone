package com.springcloudone.utils;

import com.springcloudone.component.MathComponent;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 数字工具类
 * @author xw
 * @date 2019/7/29 10:57
 */
public class MathUtils {

    @Autowired
    private static MathComponent mathComponent;

    public static Integer add(int a, int b){
        return a + b;
    }

    public static Integer sub(int a, int b){
        return a-b;
    }

    public static void main(String[] args) {
        System.out.println(mathComponent.getMathAdd());
        System.out.println(add(1, 3));
    }
}
