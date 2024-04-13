package com.jokeer.dhand.bean;

import com.baomidou.mybatisplus.annotation.IEnum;

public enum GoodsStatus implements IEnum<String> {
    ON_SALE("on_sale"),
    SOLD("sold"),
    OFF_SHELF("off_shelf");

    private final String value;

    GoodsStatus(String value) {
        this.value = value;
    }

    @Override
    public String getValue() {
        return this.value;
    }
}