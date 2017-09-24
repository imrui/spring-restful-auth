package com.xxicon.interceptor;

import com.xxicon.annotation.Authorization;
import com.xxicon.base.Constant;
import com.xxicon.message.bean.TokenModel;
import com.xxicon.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

@Component
public class AuthorizationInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //如果不是映射到方法直接通过
//        if (!handler.getClass().isAssignableFrom(HandlerMethod.class))
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        Class<?> clazz = method.getDeclaringClass();
        // 拦截注解了Authorization的class和method
        if (clazz.isAnnotationPresent(Authorization.class) || method.isAnnotationPresent(Authorization.class)) {
            //从header中得到token
            String token = request.getHeader(Constant.AUTHORIZATION);
            //验证token
            TokenModel model = this.userService.checkToken(token);
            if (model != null) {
                //如果token验证成功，将token对应的用户id存在request中，便于之后注入
                request.setAttribute(Constant.CURRENT_USER_ID, model.getUid());
                request.setAttribute(Constant.CURRENT_USER_TOKEN, token);
                return true;
            }
            //如果验证token失败，返回401错误
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }
        return true;
    }
}
