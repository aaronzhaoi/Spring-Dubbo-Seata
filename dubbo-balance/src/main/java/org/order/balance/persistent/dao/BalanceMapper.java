package org.order.balance.persistent.dao;


import org.apache.ibatis.annotations.Param;
import org.order.dubbo.api.domain.CustomBalance;
import org.springframework.stereotype.Repository;

@Repository
public interface BalanceMapper {

   void insertBalanceHis(@Param("customBalance") CustomBalance customBalance);

   void deleteBalanceHis(@Param("customBalance") CustomBalance customBalance);
}
