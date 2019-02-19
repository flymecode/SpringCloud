package com.xupt.product.service.impl;

import com.xupt.product.common.ProductInfoOutput;
import com.xupt.product.dataobject.ProductInfo;
import com.xupt.product.dto.CartDTO;
import com.xupt.product.enums.ProductStatusEnum;
import com.xupt.product.enums.ResultEnum;
import com.xupt.product.exception.ProductException;
import com.xupt.product.repository.ProductInfoRepository;
import com.xupt.product.service.ProductService;
import com.xupt.product.utils.JsonUtil;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

	@Override
	public List<ProductInfo> findList(List<String> productIdList) {
		return productInfoRepository.findByProductIdIn(productIdList);
	}

	@Autowired
	private AmqpTemplate amqpTemplate;

	@Override
	public void decreaseStock(List<CartDTO> cartDTOList) {
		List<ProductInfo> productInfos = decreaseStockProcess(cartDTOList);
		// 发送消息
		List<ProductInfoOutput> productInfoOutputList = productInfos.stream().map(e -> {
			ProductInfoOutput productInfoOutput = new ProductInfoOutput();
			BeanUtils.copyProperties(e, productInfoOutput);
			return productInfoOutput;
		}).collect(Collectors.toList());

		amqpTemplate.convertAndSend("productInfo", JsonUtil.toJson(productInfoOutputList));

	}

	@Transactional
	public List<ProductInfo> decreaseStockProcess(List<CartDTO> cartDTOList) {
		List<ProductInfo> productInfoList = new ArrayList<>();
		for (CartDTO cartDTO : cartDTOList) {
			Optional<ProductInfo> productInfoOptional = productInfoRepository.findById(cartDTO.getProductId());
			// 判断商品是否存在
			if (!productInfoOptional.isPresent()) {
				throw new ProductException(ResultEnum.PRODUCT_NOT_EXIST);
			}
			ProductInfo productInfo = productInfoOptional.get();
			// 库存是否足够
			Integer result = productInfo.getProductStock() - cartDTO.getProductQuantity();
			if (result < 0) {
				throw new ProductException(ResultEnum.PRODUCT_STOCK_ERROR);
			}
			productInfo.setProductStock(result);
			productInfoRepository.save(productInfo);
			productInfoList.add(productInfo);
		}
		return productInfoList;
	}
}
