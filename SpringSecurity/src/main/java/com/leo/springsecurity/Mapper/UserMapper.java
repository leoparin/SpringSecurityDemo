package com.leo.springsecurity.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.leo.springsecurity.Domain.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper extends BaseMapper<User> {
}
