package com.leo.springsecurity.handler;

import com.alibaba.fastjson.JSON;
import com.leo.springsecurity.Response.ResponseResult;
import com.leo.springsecurity.Utils.WebUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * ClassName:AccessDeniedHandleImpl
 * PackageName:com.leo.springsecurity.handler
 * Description:
 *
 * @Date:2023/1/1 10:07
 * @Auther: leo
 **/
@Component
public class AccessDeniedHandleImpl implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        //如果被拒绝返回ResponseResult格式的信息
        ResponseResult responseResult = new ResponseResult<>(HttpStatus.FORBIDDEN.value(),"权限不足");
        String json = JSON.toJSONString(responseResult);
        WebUtils.renderString(response,json);
    }
}
