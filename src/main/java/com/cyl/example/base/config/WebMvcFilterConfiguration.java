package com.cyl.example.base.config;

import com.cyl.example.base.auth.AuthenticationFilter;
import com.cyl.example.dao.UserInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebMvcFilterConfiguration {

    private UserInfoMapper userInfoMapper;

    @Autowired
    WebMvcFilterConfiguration(UserInfoMapper userInfoMapper) {
        this.userInfoMapper = userInfoMapper;
    }

    @Bean
    AuthenticationFilter authenticationFilter() {
        return new AuthenticationFilter(userInfoMapper);
    }

    @Bean("checkUserFilter")
    public FilterRegistrationBean<AuthenticationFilter> checkUserFilter(AuthenticationFilter authenticationFilter) {
        FilterRegistrationBean<AuthenticationFilter> registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(authenticationFilter);
        registrationBean.addUrlPatterns("/*"); //url拦截
        registrationBean.setOrder(1);
        return registrationBean;
    }


}
