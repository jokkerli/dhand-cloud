package com.jokeer.dhand.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jokeer.dhand.DTO.OrderDTO;
import com.jokeer.dhand.bean.Order;
import com.jokeer.dhand.bean.Result;
import com.jokeer.dhand.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
@Slf4j
@RestController
@RequestMapping("/order")
@Tag(name = "订单管理控制层")
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
    @Operation(summary = "根据ID获取订单详情")
    @GetMapping("/detail/{orderId}")
    public Result getOrderDetail(@PathVariable("orderId") Long orderId){
        Order order = orderService.getOrderDetail(orderId);
        return Result.ok(order);
    }

    @Operation(summary = "点击购买按钮，但未支付")
    @PostMapping()
    public Result createOrder(@RequestBody OrderDTO orderDTO){
        orderService.createOrder(orderDTO);
        return Result.ok();
    }

    @Operation(summary = "取消订单，可以买家也可以是卖家")
    @DeleteMapping("/{orderId}")
    public Result cancelOrder(@PathVariable("orderId") Long orderId){

        orderService.cancelOrderByOrderId(orderId);

        return Result.ok();
    }

    @Operation(summary = "修改订单价格")
    @PutMapping("/{orderId}")
    public Result updateOrderPrice(@PathVariable("orderId") Long orderId, @RequestParam("newPrice")BigDecimal newPrice){
        boolean updateResult = orderService.updateOrderPrice(orderId, newPrice);

        return Result.ok();
    }
    @Operation(summary = "根据状态分页获取用户订单列表")
    @GetMapping("/list")
    public Result list(@RequestParam Integer status,
                       @RequestParam(required = false,defaultValue = "1") Integer pageNum,
                       @RequestParam(required = false,defaultValue = "5") Integer pageSize){
        Page<Order> page =  orderService.list(status,pageNum,pageSize);
        long size = page.getSize();
        log.info("size == "+size);
        return Result.ok(page);
    }


}
