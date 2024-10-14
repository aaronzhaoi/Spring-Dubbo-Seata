package org.order.service;

import org.apache.ibatis.annotations.Param;

public interface OrderService {

    String getOrderName( int OrderId);

    void doTcc();
}
