package com.xupt.order.service;

import com.xupt.order.dto.OrderDTO;

/**
 * @author maxu
 * @date 2019/2/17
 */
public interface OrderService {
    /**
     * 创建订单
     * @param orderDTO
     * @return
     */
    OrderDTO create(OrderDTO orderDTO);

    /**
     * 完结订单(只能卖家操作)
     * @param orderId
     * @return
     */
    OrderDTO finish(String orderId);

}
