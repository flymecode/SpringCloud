package com.xupt.order.service.impl;

import com.xupt.order.dataobject.OrderMaster;
import com.xupt.order.dto.OrderDTO;
import com.xupt.order.enums.OrderStatusEnum;
import com.xupt.order.enums.PayStatusEnum;
import com.xupt.order.repository.OrderDetailRepository;
import com.xupt.order.repository.OrderMasterRepository;
import com.xupt.order.service.OrderService;
import com.xupt.order.untils.KeyUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * @author maxu
 * @date 2019/2/17
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMasterRepository orderMasterRepository;
    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Override
    public OrderDTO create(OrderDTO orderDTO) {
        orderDTO.setOrderId(KeyUtil.genUniqueKey());
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDTO, orderMaster);
        orderMaster.setOrderAmount(new BigDecimal(6));
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
        orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());

        orderMasterRepository.save(orderMaster);
        return orderDTO;
    }
}
