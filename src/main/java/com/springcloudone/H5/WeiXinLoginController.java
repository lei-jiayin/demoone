package com.springcloudone.H5;

import com.alibaba.fastjson.JSONObject;
import com.springcloudone.entity.WxUser;
import com.springcloudone.service.WxLoginService;
import com.springcloudone.utils.MyHttpClientUtils;
import com.springcloudone.utils.StringUtil;
import com.springcloudone.utils.WeiXinUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;

/**
 * 微信自动登录
 * @author xw
 * @date 2019/8/22 15:52
 */
@Controller
@RequestMapping("/h5")
public class WeiXinLoginController {

    private static final Logger logger = LoggerFactory.getLogger(WeiXinLoginController.class);

    @Value("${wx.login.url}")
    private String backURl;

    @Autowired
    private WxLoginService wxLoginService;


    @RequestMapping("login")
    private String login(HttpServletRequest request, HttpServletResponse response){
        System.out.println(backURl);
        // 域名需要在微信公众号中注册。以下是回调地址
//        String backUrl = "http://26ef7704.cpolar.io/h5/callBack";
        // 1  用户同意授权获取code
        String url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + WeiXinUtil.APP_ID
                + "&redirect_uri=" + URLEncoder.encode(backURl)
                + "&response_type=code"
                + "&scope=snsapi_userinfo"
                + "&state=STATE#wechat_redirect";
        logger.info("重定向地址{"+ url +"}");
        return "redirect:" + url;
    }

    @RequestMapping("callBack")
    public String callBack(ModelMap modelMap, HttpServletRequest req, HttpServletResponse resp) throws IOException {
/*
         * start 获取微信用户基本信息
         */
        String code =req.getParameter("code");
      //第二步：通过code换取网页授权access_token
         String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid="+WeiXinUtil.APP_ID
                + "&secret="+WeiXinUtil.APPSECRET
                + "&code="+code
                + "&grant_type=authorization_code";

        System.out.println("url:"+url);
        JSONObject jsonObject = MyHttpClientUtils.url2JSON(url);
        /*
         { "access_token":"ACCESS_TOKEN",
            "expires_in":7200,
            "refresh_token":"REFRESH_TOKEN",
            "openid":"OPENID",
            "scope":"SCOPE" 
           }
         */
        String openid = jsonObject.getString("openid");
        String access_token = jsonObject.getString("access_token");
        String refresh_token = jsonObject.getString("refresh_token");
        //第五步验证access_token是否失效；展示都不需要
        String chickUrl="https://api.weixin.qq.com/sns/auth?access_token="+access_token+"&openid="+openid;

        JSONObject chickuserInfo = MyHttpClientUtils.url2JSON(chickUrl);
        System.out.println(chickuserInfo.toString());
        if(!"0".equals(chickuserInfo.getString("errcode"))){
            // 第三步：刷新access_token（如果需要）-----暂时没有使用,参考文档https://mp.weixin.qq.com/wiki，
            String refreshTokenUrl="https://api.weixin.qq.com/sns/oauth2/refresh_token?appid="+openid+"&grant_type=refresh_token&refresh_token="+refresh_token;

            JSONObject refreshInfo = MyHttpClientUtils.url2JSON(chickUrl);
            /*
             * { "access_token":"ACCESS_TOKEN",
                "expires_in":7200,
                "refresh_token":"REFRESH_TOKEN",
                "openid":"OPENID",
                "scope":"SCOPE" }
             */
            System.out.println(refreshInfo.toString());
            access_token=refreshInfo.getString("access_token");
        }
       
       // 第四步：拉取用户信息(需scope为 snsapi_userinfo)
       String infoUrl = "https://api.weixin.qq.com/sns/userinfo?access_token="+access_token
                + "&openid="+openid
                + "&lang=zh_CN";
        System.out.println("infoUrl:"+infoUrl);
        JSONObject userInfo = MyHttpClientUtils.url2JSON(infoUrl);
        /*
         {    "openid":" OPENID",
            " nickname": NICKNAME,
            "sex":"1",
            "province":"PROVINCE"
            "city":"CITY",
            "country":"COUNTRY",
            "headimgurl":    "http://wx.qlogo.cn/mmopen/g3MonUZtNHkdmzicIlibx6iaFqAc56vxLSUfpb6n5WKSYVY0ChQKkiaJSgQ1dZuTOgvLLrhJbERQQ4eMsv84eavHiaiceqxibJxCfHe/46",
            "privilege":[ "PRIVILEGE1" "PRIVILEGE2"     ],
            "unionid": "o6_bmasdasdsad6_2sgVt7hMZOPfL"
            }
         */
        System.out.println("JSON-----"+userInfo.toString());
        System.out.println("名字-----"+userInfo.getString("nickname"));
        System.out.println("头像-----"+userInfo.getString("headimgurl"));
        req.getSession().setAttribute("nickName",userInfo.getString("nickname"));
        String nickName = userInfo.getString("nickname");
        logger.info("原始的string微信昵称：" + nickName);
        String nn = StringUtil.toAlias(nickName);
        req.getSession().setAttribute("nickName1",nn);
        logger.info("to alias的表情字符：" + nn);
        String nnu = StringUtil.toUnicode(nickName);
        req.getSession().setAttribute("nickName2",nnu);
        logger.info("to unicode的表情字符：" + nnu);
        String html = StringUtil.toHtml(nickName);
        req.getSession().setAttribute("nickName3",html);
        logger.info("to html的表情字符：" + html);
        WxUser wxUser = new WxUser();
        wxUser.setNickName(nickName);
//        wxLoginService.insert(wxUser);
        /*
         * end 获取微信用户基本信息
         */
        //获取到用户信息后就可以进行重定向，走自己的业务逻辑了。。。。。。
        //接来的逻辑就是你系统逻辑了，请自由发挥
       
        return "redirect:/h5/index";
    }

    @RequestMapping("index")
    public String index(Model model, HttpServletRequest request){
        WxUser wxUser = wxLoginService.get(1);
        model.addAttribute("nn",wxUser.getNickName());
        return "/wxIndex";
    }


    public static void main(String[] args) {
//        System.out.println(WeiXinUtil.getAccessToken(WeiXinUtil.APP_ID,WeiXinUtil.APPSECRET));
//        System.out.println(backURl);
    }
}
