package com.leo.springsecurity.Services;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.leo.springsecurity.Domain.LoginUser;
import com.leo.springsecurity.Domain.User;
import com.leo.springsecurity.Mapper.MenuMapper;
import com.leo.springsecurity.Mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/*
 * 校验用户身份
 */
@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private MenuMapper menuMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //从db查询用户
        //select * from sys_user where name = "leo"
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUserName,username);
        User user = userMapper.selectOne(queryWrapper);
        //不存在抛出异常
        if(Objects.isNull(user)){
            throw new RuntimeException("用户名不存在");
        }
        //存在则存LoginUser到redis
        // TODO:权限信息
        // List<String> list = new ArrayList<>(Arrays.asList("test"));
        List<String> list = menuMapper.selectPermsByUserId(user.getId());
        return new LoginUser(user,list);
    }
}
