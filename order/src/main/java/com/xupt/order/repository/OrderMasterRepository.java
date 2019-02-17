package com.xupt.order.repository;

import com.xupt.order.dataobject.OrderMaster;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author maxu
 */
public interface OrderMasterRepository extends JpaRepository<OrderMaster, String> {

}
