package com.springcloudone.demoone;

import com.springcloudone.demoone.pojo.User;
import xw.JSONU.JsonUtils_xw;


/**
 * @author xw
 * @date 2019/7/22 10:30
 */
public class TestJSONUtils {

    public static void main(String[] args) {
        String jsonStr = "{\"id\": 111,\"userName\": \"xxww\",\"password\": \"ssswww\"}";
        User user = JsonUtils_xw.jstrToBean(jsonStr, User.class);
        System.out.println(user.toString());
    }
}
