package com.springcloudone.component.config;

import com.springcloudone.component.interceptor.TimerInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * 拦截器的配置
 * @author xw
 * @date 2020/5/29 15:45
 */
@Configuration
public class InterceptorConfig extends WebMvcConfigurerAdapter {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new TimerInterceptor()).addPathPatterns("/**");
        super.addInterceptors(registry);
    }
}
