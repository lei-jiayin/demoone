package com.springcloudone.baseSt.myEnum;

/**
 * @author xw
 * @date 2019/11/13 10:03
 */
public class MyMain {
    public static void main(String[] args) {
        System.out.println(ExceptionEnum.values());
        for (ExceptionEnum ee: ExceptionEnum.values()) {
            System.out.println(ee.toString());
        }
    }
}
