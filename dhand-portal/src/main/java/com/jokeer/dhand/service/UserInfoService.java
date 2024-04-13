package com.jokeer.dhand.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jokeer.dhand.VO.UserInfoVO;
import com.jokeer.dhand.bean.Result;
import com.jokeer.dhand.bean.UserInfo;

public interface UserInfoService extends IService<UserInfo> {
    Result getInfoByUserId(Integer userId);

    Result updateUserInfo(Integer userId, UserInfoVO userInfoVO);
}
