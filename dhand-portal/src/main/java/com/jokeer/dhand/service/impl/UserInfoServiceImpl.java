package com.jokeer.dhand.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jokeer.dhand.VO.UserInfoVO;
import com.jokeer.dhand.bean.Result;
import com.jokeer.dhand.bean.User;
import com.jokeer.dhand.bean.UserInfo;
import com.jokeer.dhand.mapper.UserInfoMapper;
import com.jokeer.dhand.service.UserInfoService;
import com.jokeer.dhand.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements UserInfoService {
    @Autowired
    private UserService userService;

    /**
     * 获取用户详细信息
     * @param userId
     * @return
     */
    @Override
    public Result getInfoByUserId(Integer userId) {

        UserInfo userInfo = getById(userId);
        UserInfoVO userInfoVO = new UserInfoVO();
        BeanUtil.copyProperties(userInfo,userInfoVO);
        User user = userService.getById(userId);
        userInfoVO.setUserName(user.getUsername());
        return Result.ok(userInfoVO);
    }

    /**
     * 修改用户信息
     * @param userId
     * @param userInfoVO
     * @return
     */
    @Override
    public Result updateUserInfo(Integer userId, UserInfoVO userInfoVO) {

        UserInfo userInfo = new UserInfo();
        BeanUtil.copyProperties(userInfoVO,userInfo);
        userInfo.setUpdatedAt(LocalDateTime.now());
        updateById(userInfo);
        return Result.ok();
    }
}
