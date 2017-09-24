package com.xxicon.resolvers;

import com.xxicon.annotation.CurrentUser;
import com.xxicon.base.Constant;
import com.xxicon.message.bean.TokenModel;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.multipart.support.MissingServletRequestPartException;

@Component
public class TokenModelMethodArgumentResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        //如果参数类型是 TokenModel 并且有 CurrentUser 注解则支持
        if (parameter.getParameterType().isAssignableFrom(TokenModel.class) && parameter.hasParameterAnnotation(CurrentUser.class)) {
            return true;
        }
        return false;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        Integer currentUserId = (Integer) webRequest.getAttribute(Constant.CURRENT_USER_ID, RequestAttributes.SCOPE_REQUEST);
        String currentToken = (String) webRequest.getAttribute(Constant.CURRENT_USER_TOKEN, RequestAttributes.SCOPE_REQUEST);
        if (currentUserId != null && !StringUtils.isEmpty(currentToken)) {
            return new TokenModel(currentUserId, currentToken);
        }
        throw new MissingServletRequestPartException(Constant.CURRENT_USER_ID);
    }
}
