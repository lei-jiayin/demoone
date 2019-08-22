package com.springcloudone.utils;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author xw
 * @date 2019/8/22 10:10
 */
@Controller
@RequestMapping("/token")
public class CheckTokenController {
    @RequestMapping("")
    public void token(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String signature = request.getParameter("signature");
        String timestamp = request.getParameter("timestamp");
        String nonce = request.getParameter("nonce");
        String echostr = request.getParameter("echostr");
        PrintWriter out = response.getWriter();

        // 通过检验signature对请求进行校验，若校验成功则原样返回echostr，表示接入成功，否则接入失败
        if (WeiXinUtil.checkSignature(signature, timestamp, nonce)) {
            System.out.print("echostr=" + echostr);
            out.print(echostr);
        }

        out.close();
        out = null;
    }



}
