package com.jokeer.dhand.auth.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jokeer.dhand.auth.bean.User;
import com.jokeer.dhand.auth.domain.SecurityUser;
import com.jokeer.dhand.auth.mapper.UserMapper;
import com.jokeer.dhand.common.component.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userMapper.selectOne(new QueryWrapper<User>().eq("username", username));
        if(Objects.isNull(user)){
            throw new RuntimeException("用户名或密码错误");
        }

    //TODO 根据用户查询权限信息 添加到LoginUser中


        return new SecurityUser(user);
        //封装成UserDetails对象返回
//        Set<GrantedAuthority> authorities = new HashSet<>();
//        return new org.springframework.security.core.userdetails
//                .User(username,new BCryptPasswordEncoder().encode(user.getPassword()),authorities);

//        return new SecurityUser(user);
    }

}
