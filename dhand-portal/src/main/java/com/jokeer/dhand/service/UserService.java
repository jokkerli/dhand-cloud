package com.jokeer.dhand.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jokeer.dhand.DTO.LoginDTO;
import com.jokeer.dhand.bean.Result;
import com.jokeer.dhand.bean.User;

public interface UserService extends IService<User> {
    Result sign(LoginDTO loginDTO);

    User findUserById(String userId);

    Result login(LoginDTO loginDTO);

}
