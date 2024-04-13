package com.jokeer.dhand.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.json.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jokeer.dhand.DTO.LoginDTO;
import com.jokeer.dhand.bean.Result;
import com.jokeer.dhand.bean.User;
import com.jokeer.dhand.bean.UserInfo;
import com.jokeer.dhand.mapper.UserMapper;
import com.jokeer.dhand.service.UserInfoService;
import com.jokeer.dhand.service.UserService;
import com.jokeer.dhand.utils.JWTUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Slf4j
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserInfoService userInfoService;


    /**
     * 用户注册功能
     * @param loginDTO
     * @return
     */
    @Override
    @Transactional
    public Result sign(LoginDTO loginDTO) {
        //1.获得对应的phone和passwod
        String phone = loginDTO.getPhone();
        //2.判断phone是否存在
        User user = query().eq("phone", phone).one();
        //2.1 存在，即返回提示信息，用户名已存在
        if(user != null){
            return Result.fail("用户名已存在");
        }
        //2.2 不存在，插入数据库User，显示成功
        user = createUser(loginDTO);
        save(user);
        createUserInfo(user);
        return Result.ok();
    }



    @Override
    public User findUserById(String userId) {
        int id = Integer.parseInt(userId);
        return getById(id);
    }

    /**
     * 登录功能
     * @param loginDTO
     * @return
     */
    @Override
    public Result login(LoginDTO loginDTO) {
        JSONObject jsonObject=new JSONObject();
        String phone = loginDTO.getPhone();
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("phone",phone);
        User user = getOne(userQueryWrapper);
        if(user == null){
            return Result.fail("登录失败，用户不存在");
        }
        if(!user.getPassword().equals(loginDTO.getPassword())){
            return Result.fail("登录失败，密码错误");
        }
        String token = JWTUtils.createJWT(user);
        jsonObject.put("token", token);
        jsonObject.put("user", user);
        return Result.ok(jsonObject);
    }



    /**
     * 添加用户到数据库
     * @param loginDTO
     * @return
     */
    private User createUser(LoginDTO loginDTO) {
        User user = BeanUtil.copyProperties(loginDTO, User.class);
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());
        log.info(String.valueOf(user));
        return  user;
    }

    /**
     * 添加用户到数据库表的UserInfo
     * @param user
     */
    private void createUserInfo(User user) {
        UserInfo userInfo = new UserInfo();
        userInfo.setUserId(user.getId());
        userInfo.setCreatedAt(user.getCreatedAt());
        userInfo.setUpdatedAt(user.getUpdatedAt());
        userInfoService.save(userInfo);
    }



}
