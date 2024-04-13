package com.jokeer.dhand.DTO;

import lombok.Data;

/**
 * 接收用户前端传来的登录表单的注册数据
 */
@Data
public class LoginDTO {

    private String username;

    private String password;

    private String phone;



}
