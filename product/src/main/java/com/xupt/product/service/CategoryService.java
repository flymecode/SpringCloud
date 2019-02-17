package com.xupt.product.service;

import com.xupt.product.dataobject.ProductCategory;

import java.util.List;

/**
 * 类目
 *
 * @author maxu
 */
public interface CategoryService {

    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);
}
