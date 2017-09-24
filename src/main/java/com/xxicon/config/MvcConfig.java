package com.xxicon.config;

import com.xxicon.interceptor.AuthorizationInterceptor;
import com.xxicon.resolvers.CurrentUserMethodArgumentResolver;
import com.xxicon.resolvers.TokenModelMethodArgumentResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;

@Configuration
public class MvcConfig extends WebMvcConfigurerAdapter {

    @Autowired private AuthorizationInterceptor authorizationInterceptor;
    @Autowired private CurrentUserMethodArgumentResolver currentUserMethodArgumentResolver;
    @Autowired private TokenModelMethodArgumentResolver tokenModelMethodArgumentResolver;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(this.authorizationInterceptor);
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(this.currentUserMethodArgumentResolver);
        argumentResolvers.add(this.tokenModelMethodArgumentResolver);
    }
}
