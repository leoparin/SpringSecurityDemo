package com.leo.springsecurity.handler;

import com.alibaba.fastjson.JSON;
import com.leo.springsecurity.Response.ResponseResult;
import com.leo.springsecurity.Utils.WebUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * ClassName:AuthenticationEntryPointImpl
 * PackageName:com.leo.springsecurity.handler
 * Description:
 *
 * @Date:2023/1/1 10:12
 * @Auther: leo
 **/
@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        ResponseResult result = new ResponseResult(HttpStatus.UNAUTHORIZED.value(), "认证失败请重新登录");

        String json = JSON.toJSONString(result);

        WebUtils.renderString(response,json);
    }
}
