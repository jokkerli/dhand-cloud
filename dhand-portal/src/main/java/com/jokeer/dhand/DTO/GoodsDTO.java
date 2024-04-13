package com.jokeer.dhand.DTO;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class GoodsDTO {

    private String name;

    private BigDecimal price;

    private Integer stock;

    private Integer sellerId;

    private String description;

    private List<String> urList;






}
