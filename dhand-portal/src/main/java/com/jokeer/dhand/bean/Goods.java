package com.jokeer.dhand.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
@TableName("goods") // 注意这里使用了MyBatis Plus的@TableName注解
public class Goods {

    @TableId(value = "goods_id", type = IdType.AUTO) // 使用MyBatis Plus的@TableId注解
    private Long goodsId;

    private String name;

    private String description;

    private BigDecimal price;

    private Integer stock;

    private Integer categoryId;

    private Long sellerId;

    private Date createdAt;

    private Date updatedAt;


    private String status; // 确保这个自定义枚举与数据库中的ENUM类型对应

    // ... 省略getter和setter方法或使用Lombok
}