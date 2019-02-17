package com.xupt.product.service.impl;

import com.xupt.product.dataobject.ProductInfo;
import com.xupt.product.enums.ProductStatusEnum;
import com.xupt.product.repository.ProductInfoRepository;
import com.xupt.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author maxu
 * @date 2019/2/17
 */
@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductInfoRepository productInfoRepository;

    @Override
    public List<ProductInfo> findUpAll() {
        return productInfoRepository.findByProductStatus(ProductStatusEnum.UP.getCode());
    }

}
