package com.springcloudone.component.filter;

import javax.servlet.*;
import java.io.IOException;

/**
 * 请求时间 过滤器
 * （过滤器的简单使用）
 * @author xw
 * @date 2020/5/29 15:06
 */
public class TimerFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        long start = System.currentTimeMillis();
        filterChain.doFilter(servletRequest, servletResponse);
        System.out.println("Execute cost="+(System.currentTimeMillis() - start));
    }

    @Override
    public void destroy() {

    }
}
