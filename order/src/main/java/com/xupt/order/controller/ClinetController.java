package com.xupt.order.controller;

import com.xupt.product.client.ProductClient;
import com.xupt.product.common.ProductInfoOutput;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

/**
 *
 * @author maxu
 */
@RestController
@Slf4j
public class ClinetController {

	//	@Autowired
//	private RestTemplate restTemplate;
//
//	@Autowired
//	private LoadBalancerClient loadBalancerClient;
//
//	@GetMapping("/getProductMsg")
//	public String getProductMsg() {
//		String forObject = restTemplate.getForObject("http://localhost:8001/msg", String.class);
//		log.info(forObject);
//		return forObject;
//	}
//
//	@GetMapping("/getProductMsg1")
//	public String getProductMsg1() {
//		ServiceInstance choose = loadBalancerClient.choose("PRODUCT-SERVER");
//		String url = String.format("http://%s:%s", choose.getHost(), choose.getPort()+"/msg");
//		log.info("response={}", url);
//		String forObject = restTemplate.getForObject(url, String.class);
//		return forObject;
//
//	}

	@Autowired
	private ProductClient productClient;

	@GetMapping("/productlist")
	public List<ProductInfoOutput> getProductMsg() {
		List<ProductInfoOutput> productInfos = productClient.listForOrder(Arrays.asList("157875196366160022"));
		return productInfos;
	}
}
