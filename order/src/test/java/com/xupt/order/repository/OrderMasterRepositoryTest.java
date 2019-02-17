package com.xupt.order.repository;

import com.xupt.order.OrderApplicationTests;
import com.xupt.order.dataobject.OrderMaster;
import com.xupt.order.enums.OrderStatusEnum;
import com.xupt.order.enums.PayStatusEnum;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

import static org.junit.Assert.*;

/**
 * @author maxu
 * @date 2019/2/17
 */
@Component
public class OrderMasterRepositoryTest extends OrderApplicationTests {
    @Autowired
    private OrderMasterRepository orderMasterRepository;

    @Test
    public void testSave() {
        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setOrderId("123345");
        orderMaster.setBuyerOpenid("1233");
        orderMaster.setBuyerName("maxu");
        orderMaster.setBuyerPhone("123");
        orderMaster.setBuyerAddress("beijing");
        orderMaster.setOrderAmount(new BigDecimal(2));
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
        orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());

        OrderMaster result = orderMasterRepository.save(orderMaster);
        Assert.assertTrue(result != null);
    }
}