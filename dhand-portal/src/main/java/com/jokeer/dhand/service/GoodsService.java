package com.jokeer.dhand.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jokeer.dhand.DTO.GoodsDTO;
import com.jokeer.dhand.bean.Goods;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.List;

public interface GoodsService extends IService<Goods> {



    public void publishGoods(List<MultipartFile> files, Long sellerId, String goodsName, String description, BigDecimal price, int stock);

    GoodsDTO getGoodsInfoById(Integer id);

    Page<Goods> getList();
}
