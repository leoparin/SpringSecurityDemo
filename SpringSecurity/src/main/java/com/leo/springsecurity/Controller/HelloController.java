package com.leo.springsecurity.Controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ClassName:HelloController
 * PackageName:com.leo.springsecurity.Controller
 * Description:
 *
 * @Date:2022/12/30 15:48
 * @Auther: leo
 **/
@RestController
public class HelloController {

    @RequestMapping("/hello")
    @PreAuthorize("hasAuthority('sys:record:check')")
    public String hello(){
        return "hello";
    }
}
