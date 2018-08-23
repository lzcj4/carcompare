package com.carcompare.config;

import com.carcompare.filter.ImageDecoderFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.DelegatingFilterProxy;

import javax.servlet.Filter;

@Configuration
public class AppConfig {
    /**
     * 发布到tomcat时需要通过此方式注入Filter，否则Filter中无法通过@Autowired进入注入
     * @return
     */
    @Bean
    public Filter imageDecoderFilter() {
        return new ImageDecoderFilter();
    }

    @Bean
    public FilterRegistrationBean imageFilterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new DelegatingFilterProxy("imageDecoderFilter"));
        registration.addUrlPatterns("/upload/*");
        registration.setName("imageDecoderFilter");
        registration.isAsyncSupported();
        return registration;
    }
}
