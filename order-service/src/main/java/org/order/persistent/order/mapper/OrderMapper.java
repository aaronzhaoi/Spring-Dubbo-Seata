package org.order.persistent.order.mapper;


import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderMapper {

    String getOrderName(@Param("orderId") int orderId);
}
