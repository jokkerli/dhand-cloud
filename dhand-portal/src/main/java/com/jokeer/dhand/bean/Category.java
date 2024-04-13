package com.jokeer.dhand.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import javax.persistence.*;

@Data
@TableName("categories")
public class Category {
    @TableId(value = "category_id", type = IdType.AUTO)
    private Integer categoryId;

    private String name;

    private String description;
}

