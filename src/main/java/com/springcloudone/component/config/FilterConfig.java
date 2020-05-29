package com.springcloudone.component.config;

import com.springcloudone.component.filter.TimerFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 过滤器配置文件
 *（测试）
 * @author xw
 * @date 2020/5/29 15:11
 */
@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean regist(){
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        //设置过滤器的实例化
        registrationBean.setFilter(new TimerFilter());
        //设置过滤器路径
        registrationBean.addUrlPatterns("/*");
        //设置过滤器名字
        registrationBean.setName("timerFilter");
        //设置过滤器的执行顺序
        registrationBean.setOrder(1);
        return registrationBean;
    }
}
