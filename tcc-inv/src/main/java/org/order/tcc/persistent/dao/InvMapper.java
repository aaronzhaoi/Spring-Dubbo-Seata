package org.order.tcc.persistent.dao;


import org.apache.ibatis.annotations.Param;
import org.order.dubbo.api.domain.OrderInv;
import org.springframework.stereotype.Repository;

@Repository
public interface InvMapper {

   void insertInv(@Param("orderInv") OrderInv orderInv);

   void deleteInv(@Param("orderInv") OrderInv orderInv);
}
