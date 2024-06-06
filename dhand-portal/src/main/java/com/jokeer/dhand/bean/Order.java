package com.jokeer.dhand.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
@TableName("`order`")
public class Order {

    @TableId(value = "order_id",type = IdType.AUTO)
    private Long orderId;

//    @ApiModelProperty(value = "订单编号")
    private String orderSn;

    private Long buyerId;

    private Long sellerId;

    private Long goodsId;

//    @ApiModelProperty
//    @ApiModelProperty(value = "订单总金额")
    private BigDecimal totalAmount;

//    @ApiModelProperty(value = "支付方式：0->未支付；1->支付宝；2->微信")
    private Integer payType;

//    @ApiModelProperty(value = "订单状态：0->待付款；1->待发货；2->已发货；3->已完成；4->已关闭；5->无效订单")
    private Integer status;

//    @ApiModelProperty(value = "配送方式 0->卖家自送 买家自提")
    private String deliveryMethod;

//    @ApiModelProperty(value = "订单备注")
    private String note;

//    @ApiModelProperty(value = "提交时间")
    private Date createTime;

//    @ApiModelProperty(value = "修改时间")
    private Date modifyTime;
}