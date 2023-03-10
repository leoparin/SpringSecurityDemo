package com.leo.springsecurity.Filter;

import com.leo.springsecurity.Domain.LoginUser;
import com.leo.springsecurity.Utils.JwtUtil;
import com.leo.springsecurity.Utils.RedisCache;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

/**
 * ClassName:JwtAuthenticationTokenFilter
 * PackageName:com.leo.springsecurity.Filter
 * Description:验证用户登录状态
 *
 * @Date:2022/12/30 17:00
 * @Auther: leo
 **/
@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {
    //OncePerRequestFilter可以保证每个请求只经过过滤器一次
    //从前段拿到token
    //用jwt工具解析其中的user
    //与redis中的userid生成的token比较

    @Autowired
    private RedisCache redisCache;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String token = request.getHeader("token");
        if(!StringUtils.hasText(token)){
            filterChain.doFilter(request,response);
            return;
        }

        String userId;

        try {
            Claims claims = JwtUtil.parseJWT(token);
           userId = claims.getSubject();
        } catch (Exception e) {
            throw new RuntimeException("Token is illegal");
        }

       LoginUser loginUser = redisCache.getCacheObject("login"+userId);

        if(Objects.isNull(loginUser)){
            throw new RuntimeException("not a login user");
        }

        List<GrantedAuthority> authorities =
                (List<GrantedAuthority>) loginUser.getAuthorities();

        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginUser,null,authorities);
        //如果用户是已登陆用户则把认证信息存入SecurityContextHolder
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        filterChain.doFilter(request,response);

    }
}
