package com.leo.springsecuritybasic.Controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ClassName:helloController
 * PackageName:com.leo.springsecuritybasic.Controller
 * Description:
 *
 * @Date:2022/12/30 15:18
 * @Auther: leo
 **/

@RestController
public class helloController {

    @GetMapping("/hello")
   public  String hello(){
        return "hello";
    }
}
