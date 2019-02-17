package com.xupt.product.service;

import com.xupt.product.dataobject.ProductInfo;

import java.util.List;

/**
 * @author maxu
 * @date 2019/2/17
 */
public interface ProductService {
    /**
     * 查询所有上架的商品
     *
     * @return
     */
    List<ProductInfo> findUpAll();
}
