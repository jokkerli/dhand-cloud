package com.jokeer.dhand.auth.service;

import com.jokeer.dhand.auth.bean.User;
import com.jokeer.dhand.common.api.CommonResult;

public interface LoginService {
    CommonResult login(User user);
    public CommonResult logout();
}
