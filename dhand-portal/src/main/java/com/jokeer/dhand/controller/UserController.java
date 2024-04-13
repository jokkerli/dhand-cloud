package com.jokeer.dhand.controller;

import com.jokeer.dhand.DTO.LoginDTO;
import com.jokeer.dhand.VO.UserInfoVO;
import com.jokeer.dhand.annoations.UserLoginToken;
import com.jokeer.dhand.bean.Result;
import com.jokeer.dhand.service.UserInfoService;
import com.jokeer.dhand.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserInfoService userInfoService;

    @PostMapping("/sign")
    public Result sign(@RequestBody LoginDTO loginDTO){
         return userService.sign(loginDTO);
    }
    @PostMapping("login")
    public Result login(@RequestBody LoginDTO loginDTO){
        return userService.login(loginDTO);
    }

    @UserLoginToken
    @GetMapping("/testToken")
    public Result testToken(){
        return Result.ok("通过了验证");
    }


    @GetMapping("info/{UserId}")
    public Result getInfoByUserId(@PathVariable("UserId") Integer userId){
        return userInfoService.getInfoByUserId(userId);
    }

    @PutMapping("/info/{userId}")
    public Result updateUserInfo(@PathVariable("userId") Integer userId, @RequestBody UserInfoVO userInfoVO){
        return userInfoService.updateUserInfo(userId,userInfoVO);
    }


}
