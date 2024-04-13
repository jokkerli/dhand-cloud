package com.jokeer.dhand.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Data
@TableName("orders")
public class Order {

    @TableId(value = "order_id",type = IdType.AUTO)
    private Integer orderId;

    private Integer buyerId;

    private Integer sellerId;

    private BigDecimal totalPrice;

    private OrderStatus status;

    private Date createdAt;

}