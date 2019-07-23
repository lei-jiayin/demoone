package com.springcloudone.demoone;

import org.apache.commons.lang.StringUtils;
import xw.StringU.StringUtils_xw;

/**
 * @author xw
 * @date 2019/7/22 9:55
 */
public class TestStringUtils {

    public static void main(String[] args) {
        String str = "sssssssd";
        String s = new StringUtils_xw(str).subString(2, 4);
        System.out.println(s);

        StringUtils.substring(str,3);
    }



}
