package com.jokeer.dhand.auth.controller;

import com.jokeer.dhand.auth.bean.User;
import com.jokeer.dhand.auth.service.LoginService;
import com.jokeer.dhand.common.api.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
    @Autowired
    private LoginService loginService;


    @GetMapping("/hello")
    public String hello(){
        return "hello!";
    }


    @PostMapping("/user/login")
    public CommonResult login(@RequestBody User user){
        return loginService.login(user);
    }
    @PostMapping("/user/login1")
    public CommonResult login1(@RequestBody User user){
        return loginService.login(user);
    }


}
