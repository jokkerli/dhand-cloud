package com.jokeer.dhand.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateTime;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jokeer.dhand.DTO.GoodsDTO;
import com.jokeer.dhand.bean.Goods;
import com.jokeer.dhand.bean.GoodsImages;
import com.jokeer.dhand.mapper.GoodsMapper;
import com.jokeer.dhand.service.FileService;
import com.jokeer.dhand.service.GoodsImageService;
import com.jokeer.dhand.service.GoodsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods> implements GoodsService {

    @Autowired
    private GoodsImageService goodsImageService;
    @Autowired
    private GoodsMapper goodsMapper;
    @Autowired
    private FileService fileService;
    @Value("${minio.bucket-name}")
    private String bucketName;
    @SuppressWarnings("AlibabaTransactionMustHaveRollback")
    @Override
    @Transactional
    public void publishGoods(List<MultipartFile> files, Long sellerId, String goodsName, String description, BigDecimal price, int stock) {

        //1.将商品相信插入数据库
        Goods goods = new Goods();
        goods.setName(goodsName);
        goods.setSellerId(sellerId);
        goods.setDescription(description);
        goods.setPrice(price);
        goods.setStock(stock);
        goods.setCreatedAt(DateTime.now());
        goods.setUpdatedAt(DateTime.now());
        save(goods);

        //2.获取对应的商品id
        Long goodsId = goods.getGoodsId();
        //TODO 使用消息队列分离将物品插入数据库和上传文件服务
        //3.将商品对应的图片url插入数据库
        files.forEach(file -> {
            String imageName = fileService.uploadFile(file,bucketName);
            goodsImageService.save(new GoodsImages(goodsId,imageName));
        });

    }

    @Override
    public GoodsDTO getGoodsInfoById(Integer id) {

        Goods goods = getById(id);
        GoodsDTO goodsDTO = new GoodsDTO();
        BeanUtil.copyProperties(goods,goodsDTO);

        List<GoodsImages> images = goodsImageService.query().eq("goods_id", id).list();

        List<String> urlList = images.stream().map(image -> image.getUrl()).collect(Collectors.toList());

        goodsDTO.setUrList(urlList);
        return goodsDTO;
    }

//    @Override
//    public List<GoodsDTO> getList() {
//        ArrayList<GoodsDTO> result = new ArrayList<>();
//
//        list().forEach(i -> {
//            GoodsDTO idto = new GoodsDTO();
//            BeanUtil.copyProperties(i,idto);
//            List<GoodsImages> images = goodsImageService.query().eq("goods_id", i.getGoodsId()).list();
//            List<String> urlList = images.stream().map(image -> image.getUrl()).collect(Collectors.toList());
//            idto.setUrList(urlList);
//            result.add(idto);
//        });
//
//        Page<Goods> page = new Page<>(0, 2);
//        QueryWrapper<Goods> wrapper = new QueryWrapper<>();
//        Page<Goods> goodsPage = goodsMapper.selectPage(page, wrapper);
//
//
//        return result;
//    }
    @Override
    public Page<Goods> getList() {
        Page<Goods> page = new Page<>(1, 2);
        QueryWrapper<Goods> wrapper = new QueryWrapper<>();
        Page<Goods> goodsPage = goodsMapper.selectPage(page, wrapper);
        return goodsPage;
    }

}
