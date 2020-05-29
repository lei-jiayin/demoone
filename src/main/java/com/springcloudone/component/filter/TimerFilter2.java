package com.springcloudone.component.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * 以注解的形式完成过滤器的配置
 * （小试一下）
 * @author xw
 * @date 2020/5/29 15:30
 */
@WebFilter(urlPatterns = "/*", filterName = "timerFilter2")
public class TimerFilter2 implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        long start = System.currentTimeMillis();
        chain.doFilter(request, response);
        System.out.println("LogFilter2 Execute cost="+(System.currentTimeMillis() - start));
    }

    @Override
    public void destroy() {

    }
}
