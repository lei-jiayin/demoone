package com.springcloudone.H5;

import com.springcloudone.utils.WeiXinUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 微信自动登录
 * @author xw
 * @date 2019/8/22 15:52
 */
@Controller
@RequestMapping("/h5/login")
public class WeiXinLoginController {

    public static void main(String[] args) {
        System.out.println(WeiXinUtil.getAccessToken(WeiXinUtil.APP_ID,WeiXinUtil.appsecret));
    }
}
