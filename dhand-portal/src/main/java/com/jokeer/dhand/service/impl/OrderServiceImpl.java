package com.jokeer.dhand.service.impl;

import cn.hutool.core.date.DateTime;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jokeer.dhand.DTO.OrderDTO;
import com.jokeer.dhand.bean.Goods;
import com.jokeer.dhand.bean.Order;
import com.jokeer.dhand.common.service.RedisService;
import com.jokeer.dhand.common.service.impl.RedisServiceImpl;
import com.jokeer.dhand.mapper.OrderMapper;
import com.jokeer.dhand.service.GoodsService;
import com.jokeer.dhand.service.OrderService;
import com.sun.xml.internal.bind.v2.TODO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.security.PrivateKey;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Random;
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {
    @Autowired
    private GoodsService goodsService;
    @Autowired
    private RedisService redisService;
    @Autowired
    private  OrderMapper orderMapper;
    @Override
    public Order getOrderDetail(Long orderId) {
        Order order = getById(orderId);
        return order;
    }

    @Override
    public void createOrder(OrderDTO orderDTO) {
        //1.生成一个订单编号，
        String orderSn = System.currentTimeMillis() + "" + (new Random().nextInt(9000) + 1000);
        //2.存入redis，15分钟
        String key  = "order" + orderSn;
        int expireTime = 15 * 60 ;
        redisService.set(key,0,expireTime);
        //3.创建数据表order对应的数据
        Order order = new Order();
        order.setBuyerId(orderDTO.getBuyerId());
        order.setGoodsId(orderDTO.getGoodsId());
        order.setOrderSn(orderSn);

        Goods goods = goodsService.getById(orderDTO.getGoodsId());

        order.setSellerId(goods.getSellerId());
        order.setTotalAmount(goods.getPrice());
        order.setPayType(0);
        order.setStatus(0);
        order.setNote("");
        order.setCreateTime(DateTime.now());
        order.setModifyTime(DateTime.now());
        //4.插入数据库
        save(order);

    }

    @Override
    public boolean cancelOrderByOrderId(Long orderId) {
        //1.查询redis的状态，0时代表未支付，可取消订单
        String orderSn = getById(orderId).getOrderSn();
        int orderStatus = (int)redisService.get("order" + orderSn);
        if(orderStatus!=0){
            return false;
        }
        //2.修改redis的状态，用于取消
        redisService.del("order"+orderSn);
        //3.修改数据库
        return update().set("status", 4).eq("order_id", orderId).update();
    }
    @Override
    public boolean updateOrderPrice(Long orderId, BigDecimal newPrice) {
        //1.查询redis的状态，0时代表未支付，可修改订单价格
        String orderSn = getById(orderId).getOrderSn();
        Object o = redisService.get("order" + orderSn);
        if(o!=null){
            return false;
        }
        //2.修改数据库
        return update().set("total_amount", newPrice).eq("order_id", orderId).update();

    }

    @Override
    public Page<Order> list(Integer status, Integer pageNum, Integer pageSize) {

        Page<Order> page = new Page<>(pageNum, pageSize);

        QueryWrapper<Order> queryWrapper = new QueryWrapper<Order>().eq("status", status).orderBy(true,true,"create_time");

        Page<Order> orders = orderMapper.selectPage(page, queryWrapper);

        return orders;
    }
}
