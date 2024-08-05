package com.cx330.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class ProductPriceMapping implements Serializable {
    private Integer mappingId;
    private Integer productId;
    private Integer priceId;
}
