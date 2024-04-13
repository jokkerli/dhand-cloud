package com.jokeer.dhand.bean;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.time.LocalDateTime;


@Data
public class UserInfo {

    @TableId(value = "user_id")
    private Integer userId; // User表的id

    @TableField(value = "school")
    private String school;  // 学校

    @TableField(value = "grade")
    private String grade;  // 在读年级

    @TableField(value = "college")
    private String college;  // 学院

    @TableField(value = "major")
    private String major;  // 专业

    @TableField(value = "icon")
    private String icon;  // 用户图像

    @TableField(value = "created_at")
    private LocalDateTime createdAt;

    @TableField(value = "updated_at")
    private LocalDateTime updatedAt;


}

