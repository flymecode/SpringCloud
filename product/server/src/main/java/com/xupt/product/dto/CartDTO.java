
package com.xupt.product.dto;

import lombok.Data;

/**
 * @author maxu
 */
@Data
public class CartDTO {

	/**
	 * 商品数量
	 */
	private String productId;

	/**
	 * 商品数量
	 */
	private Integer productQuantity;

	public CartDTO() {

	}
	public CartDTO(String productId, Integer productQuantity) {
		this.productId = productId;
		this.productQuantity = productQuantity;
	}
}
