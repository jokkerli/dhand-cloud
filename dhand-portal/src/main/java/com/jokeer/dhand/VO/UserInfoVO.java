package com.jokeer.dhand.VO;

import lombok.Data;

// VO类通常不需要与数据库表直接关联，所以不需要使用 @Table 相关注解
@Data
public class UserInfoVO {

    private Integer userId;  // 用户的唯一标识
    private String userName; // 用户名
    private String school;   // 学校
    private String grade;    // 在读年级
    private String college;  // 学院
    private String major;    // 专业
    private String icon;     // 用户图像，可能是图像的URL路径

}