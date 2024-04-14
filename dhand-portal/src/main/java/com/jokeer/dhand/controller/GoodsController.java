package com.jokeer.dhand.controller;

import com.jokeer.dhand.bean.Result;
import com.jokeer.dhand.service.GoodsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/goods")
public class GoodsController {

    @Autowired
    private GoodsService goodsService;

    /**
     * 查看商品详情，包括基本信息和商品图片
     * @param id
     * @return
     */
    @GetMapping("/{goods_id}")
    public Result getGoodsInfoById(@PathVariable("goods_id") Integer id){
        return Result.ok(goodsService.getGoodsInfoById(id));
    }

    /**
     * 获取商品列表
     * @return
     */
    @GetMapping("/list")
    public Result listGoods(){

        return Result.ok(goodsService.getList());
    }


    @PostMapping("/publish")
    public Result publishGoods(@RequestParam("images") List<String> urlList,
                             @RequestParam("sellerId") Long sellId,
                             @RequestParam("goodsName") String goodsName,
                             @RequestParam("description") String description,
                             @RequestParam("price") BigDecimal price,
                             @RequestParam("stock") int stock
                               ){

        goodsService.publishGoods(urlList,sellId,goodsName,description,price,stock);

        return Result.ok();


    }







}
