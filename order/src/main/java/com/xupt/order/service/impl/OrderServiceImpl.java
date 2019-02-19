package com.xupt.order.service.impl;

import com.xupt.order.dataobject.OrderDetail;
import com.xupt.order.dataobject.OrderMaster;
import com.xupt.order.dto.OrderDTO;
import com.xupt.order.enums.OrderStatusEnum;
import com.xupt.order.enums.PayStatusEnum;
import com.xupt.order.repository.OrderDetailRepository;
import com.xupt.order.repository.OrderMasterRepository;
import com.xupt.order.service.OrderService;
import com.xupt.order.untils.KeyUtil;
import com.xupt.product.client.ProductClient;
import com.xupt.product.common.DecreaseStockInput;
import com.xupt.product.common.ProductInfoOutput;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

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
    @Autowired
    private ProductClient productClient;

    @Override
    @Transactional
    public OrderDTO create(OrderDTO orderDTO) {
        String orderId = KeyUtil.genUniqueKey();

        // 查询商品信息
        // TODO
        List<String> productIdList = orderDTO.getOrderDetailList().stream()
        .map(OrderDetail::getProductId)
        .collect(Collectors.toList());
        List<ProductInfoOutput> productInfoList = productClient.listForOrder(productIdList);

        // 读redis
        // 减库存并将新的数值设置到redis中
        // 订单入库异常，应该手动回滚

        // 计算总价
        BigDecimal orderAmount = new BigDecimal(BigInteger.ZERO);

        for (OrderDetail orderDetail : orderDTO.getOrderDetailList()) {
            for (ProductInfoOutput productInfo : productInfoList) {
                if (productInfo.getProductId().equals(orderDetail.getProductId())) {

                    orderAmount = productInfo.getProductPrice()
                            .multiply(new BigDecimal(orderDetail.getProductQuantity()))
                            .add(orderAmount);
                    BeanUtils.copyProperties(productInfo, orderDetail);
                    orderDetail.setOrderId(orderId);
                    orderDetail.setDetailId(KeyUtil.genUniqueKey());
                    // 订单详情入库
                    orderDetailRepository.save(orderDetail);
                }
            }
        }
        // 扣库存
        List<DecreaseStockInput> decreaseStockInput = orderDTO.getOrderDetailList().stream().map(e -> new DecreaseStockInput(e.getProductId(),e.getProductQuantity())).collect(Collectors.toList());

        productClient.decreaseStock(decreaseStockInput);
        OrderMaster orderMaster = new OrderMaster();
        orderDTO.setOrderId(orderId);
        BeanUtils.copyProperties(orderDTO, orderMaster);
        orderMaster.setOrderAmount(orderAmount);
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
        orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());
        //订单入库
        orderMasterRepository.save(orderMaster);
        return orderDTO;
    }
}
