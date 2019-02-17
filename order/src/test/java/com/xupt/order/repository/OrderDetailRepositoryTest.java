package com.xupt.order.repository;

import com.xupt.order.OrderApplicationTests;
import com.xupt.order.dataobject.OrderDetail;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * @author maxu
 * @date 2019/2/17
 */
@Component
public class OrderDetailRepositoryTest extends OrderApplicationTests {

    @Autowired
    private OrderDetailRepository orderDetailRepository;
    @Test
    public void findByOrderId() {

    }

    @Test
    public void testSave() {
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setDetailId("12345");
        orderDetail.setOrderId("123345");
        orderDetail.setProductId("157875196366160022");
        orderDetail.setProductName("皮蛋粥");
        orderDetail.setProductPrice(new BigDecimal(0.01));
        orderDetail.setProductQuantity(2);
        orderDetail.setProductIcon("http://xxx");
        OrderDetail detail = orderDetailRepository.save(orderDetail);
        Assert.assertTrue(detail != null);
    }
}