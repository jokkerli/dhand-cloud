package com.jokeer.dhand.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("goods_images")
public class GoodsImages {


    @TableId(value = "image_id",type = IdType.AUTO)
    private Integer imageId;

    private Long goodsId;

    private String url;

    public GoodsImages(Long goodsId, String url){
        this.goodsId = goodsId;
        this.url = url;

    }

}
