package com.jokeer.dhand.auth.service.impl;

import com.google.j2objc.annotations.AutoreleasePool;
import com.jokeer.dhand.auth.bean.User;
import com.jokeer.dhand.auth.domain.SecurityUser;
import com.jokeer.dhand.auth.service.LoginService;
import com.jokeer.dhand.common.api.CommonResult;
import com.jokeer.dhand.common.service.RedisService;
import com.jokeer.dhand.common.util.JwtUtil;
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

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private RedisService redisService;


    @Override
    public CommonResult login(User user) {

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword());

        Authentication authenticate = authenticationManager.authenticate(authenticationToken);

        if(Objects.isNull(authenticate)){
            throw new RuntimeException("用户名或密码错误");
        }
        SecurityUser securityUser =(SecurityUser) authenticate.getPrincipal();

        String id = securityUser.getId().toString();

        String jwt = JwtUtil.createJWT(id);

        redisService.set("login:"+id,securityUser);

        //把token响应给前端
        HashMap<String,String> map = new HashMap<>();
        map.put("token",jwt);

        return CommonResult.success(map,"登录成功！");
    }
    @Override
    public CommonResult logout() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        SecurityUser securityUser = (SecurityUser) authentication.getPrincipal();
        Long userid = securityUser.getId();
        redisService.del("login:"+userid);
        return CommonResult.success(200,"退出成功");
    }
}
