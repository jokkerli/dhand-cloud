package com.jokeer.dhand.controller;

import com.jokeer.dhand.DTO.OrderDTO;
import com.jokeer.dhand.bean.Order;
import com.jokeer.dhand.bean.Result;
import com.jokeer.dhand.service.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/order")
//@Api("订单管理")
public class OrderController {

    @Autowired
    private OrderService orderService;

/**
 * 购买功能
 *
 * 支付功能
 *
 * 取消订单功能（卖家/买家）
 *
 * 修改订单功能
 *
 * 查看订单功能
 */
//    @ApiOperation("根据ID获取订单详情")
    @GetMapping("/detail/{orderId}")
    public Result getOrderDetail(@PathVariable("orderId") Long orderId){
        Order order = orderService.getOrderDetail(orderId);
        return Result.ok(order);
    }

//    @ApiOperation("点击购买按钮，但未支付")
    @PostMapping()
    public Result createOrder(@RequestBody OrderDTO orderDTO){
        orderService.createOrder(orderDTO);
        return Result.ok();
    }

//    @ApiOperation("取消订单，可以买家也可以是卖家")
    @DeleteMapping("/{orderId}")
    public Result cancelOrder(@PathVariable("orderId") Long orderId){

        orderService.cancelOrderByOrderId(orderId);

        return Result.ok();
    }

//    @ApiOperation("修改订单价格")
    @PutMapping("/{orderId}")
    public Result updateOrderPrice(@PathVariable("orderId") Long orderId, @RequestParam("newPrice")BigDecimal newPrice){
        boolean updateResult = orderService.updateOrderPrice(orderId, newPrice);

        return Result.ok();
    }



}
