package com.springcloudone.component.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 拦截器实现打印运行时间
 * （存在线程安全问题）
 * @author xw
 * @date 2020/5/29 15:40
 */
public class TimerInterceptor implements HandlerInterceptor {

    long start = System.currentTimeMillis();

    /**
     * 请求执行前
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        start = System.currentTimeMillis();
        return true;
    }

    /**
     * 请求执行后
     * 当preHandle返回true时执行
     * @param request
     * @param response
     * @param handler
     * @param modelAndView
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("interceptor time:" + (System.currentTimeMillis() - start));
    }
}
