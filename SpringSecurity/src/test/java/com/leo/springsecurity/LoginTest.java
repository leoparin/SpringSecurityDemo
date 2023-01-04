package com.leo.springsecurity;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.leo.springsecurity.Domain.User;
import com.leo.springsecurity.Mapper.UserMapper;
import com.leo.springsecurity.Utils.JwtUtil;
import com.leo.springsecurity.Utils.RedisCache;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Objects;

/**
 * ClassName:LoginTest
 * PackageName:com.leo.springsecurity
 * Description:
 *
 * @Date:2022/12/29 11:02
 * @Auther: leo
 **/
@SpringBootTest
public class LoginTest {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RedisCache redisCache;
    @Test
    void myBatis(){
        String username = "leo";
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUserName,username);
        User user = userMapper.selectOne(queryWrapper);
        if(Objects.isNull(user)){
            throw new RuntimeException("用户名不存在");
        }
        System.out.println(user);
    }

    @Test
    void RedisCache(){
        //store a jwtToken to redis
        String key = "test:token:";
        String username = "stillopen";
        String jwtToken = JwtUtil.createJWT(username);

        redisCache.setCacheObject(key,jwtToken);

        String result =  redisCache.getCacheObject(key);
        System.out.println(result.equals(jwtToken));
    }

//    @Test
//    void Login(){
//        String userName =
//    }
}
