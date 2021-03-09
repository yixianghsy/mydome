package com.mengxuegu.security.authentication;

import com.mengxuegu.base.result.MengxueguResult;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 处理失败认证的
 * @Auther: 梦学谷 www.mengxuegu.com
 */
@Component("customAuthenticationFailureHandler")
public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {
    /**
     *
     * @param exception 认证失败时抛出异常
     */
    @Override
    public void onAuthenticationFailure(HttpServletRequest request,
            HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        // 认证失败响应JSON字符串，
        MengxueguResult result = MengxueguResult.build(HttpStatus.UNAUTHORIZED.value(), exception.getMessage());
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(result.toJsonString());
    }
}
