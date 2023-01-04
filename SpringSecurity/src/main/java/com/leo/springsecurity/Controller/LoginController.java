package com.leo.springsecurity.Controller;

import com.leo.springsecurity.Domain.User;
import com.leo.springsecurity.Response.ResponseResult;
import com.leo.springsecurity.Services.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class LoginController {
    @Autowired
    private LoginService loginService;

    @PostMapping("/login")
    public ResponseResult Login(@RequestBody User user){
        return loginService.login(user);
    }

    @RequestMapping("/logout")
    public ResponseResult Logout(){
        return loginService.logout();
    }

}
