package org.csu.tank.config;

import org.csu.tank.config.interceptor.TokenValidationInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import javax.annotation.Resource;

/**
 * @author louyy
 * @date Created on 2019/8/29
 */
@Configuration
public class WebMVCConfiguration extends WebMvcConfigurationSupport {
    @Resource
    private TokenValidationInterceptor tokenValidationInterceptor;

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(tokenValidationInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("**/login");
        super.addInterceptors(registry);
    }
}
