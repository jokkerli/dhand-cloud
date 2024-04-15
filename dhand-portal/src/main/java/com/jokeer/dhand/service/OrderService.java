package com.jokeer.dhand.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jokeer.dhand.DTO.OrderDTO;
import com.jokeer.dhand.bean.Order;

import java.math.BigDecimal;

public interface OrderService extends IService<Order> {
    Order getOrderDetail(Long orderId);

    void createOrder(OrderDTO orderDTO);

    boolean cancelOrderByOrderId(Long orderId);

    boolean updateOrderPrice(Long orderId, BigDecimal newPrice);

    Page<Order> list(Integer status, Integer pageNum, Integer pageSize);
}
