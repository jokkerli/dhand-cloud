package com.jokeer.dhand.auth.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName(value = "users")  //处理数据库表名和实体类类名的不一致问题
public class User {

    @TableId(value = "user_id", type = IdType.AUTO)
    private Long id;

    private String username;

    private String password;

    private String phone;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;



}
