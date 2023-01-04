package com.leo.springsecurity.Services;

import com.leo.springsecurity.Domain.User;
import com.leo.springsecurity.Response.ResponseResult;

public interface LoginService {

    ResponseResult login(User user);

    ResponseResult logout();
}
