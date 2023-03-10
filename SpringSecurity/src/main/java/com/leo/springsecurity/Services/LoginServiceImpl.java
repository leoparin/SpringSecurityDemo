package com.leo.springsecurity.Services;

import com.leo.springsecurity.Domain.LoginUser;
import com.leo.springsecurity.Domain.User;
import com.leo.springsecurity.Response.ResponseResult;
import com.leo.springsecurity.Utils.JwtUtil;
import com.leo.springsecurity.Utils.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Objects;

@Service
public class LoginServiceImpl implements LoginService {
    //从loginController里拿到用户输入的账号密码
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private RedisCache redisCache;
    public ResponseResult login(User user) {
        //对用户登录时传入的用户名根密码进行校验，其实是调用了SpringSecurity自带的密码校验Filter
        //This class is an implementation of the Authentication interface and represents a
        // standard authentication request with username and password.
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(user.getUserName(),user.getPassword());
        Authentication authentication = authenticationManager.authenticate(authenticationToken);
        //自动调用UserDetailService验证用户是否存在于数据库，并返回用户验证以及权限信息
        if(Objects.isNull(authentication)){
            throw new RuntimeException("账号密码输入错误");
        }
        //生成token
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        String userId = loginUser.getUser().getId().toString();
        String jwt = JwtUtil.createJWT(userId);
        //loginUser存入redis
        redisCache.setCacheObject("login"+userId,loginUser);
        //token递交给前端
        HashMap<String,String> map = new HashMap<>();
        map.put("token",jwt);
        return new ResponseResult(200,"login success",map);
    }

    @Override
    public ResponseResult logout() {
        //我们只需要定义一个登陆接口，然后获取
        //SecurityContextHolder 中的认证信息，删除 redis 中对应的数据即可。
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        Long userid = loginUser.getUser().getId();

        redisCache.deleteObject("login"+userid);
        return new ResponseResult(200,"logout success");
    }
}
