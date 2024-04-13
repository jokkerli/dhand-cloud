package com.jokeer.dhand.bean;


public enum OrderStatus {
    PENDING,    // 等待处理
    CONFIRMED,  // 已确认
    SHIPPED,    // 已发货
    COMPLETED,  // 已完成
    CANCELLED   // 已取消
}