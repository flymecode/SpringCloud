
package com.xupt.product.client;

import com.xupt.product.common.DecreaseStockInput;
import com.xupt.product.common.ProductInfoOutput;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @author maxu
 */
@Component
@FeignClient(name = "product")
public interface ProductClient {

	@PostMapping("/product/listForOrder")
	List<ProductInfoOutput> listForOrder(@RequestBody List<String> productIdList);

	@PostMapping("/decreaseStock")
	void decreaseStock(@RequestBody List<DecreaseStockInput> decreaseStockInputList);
}
