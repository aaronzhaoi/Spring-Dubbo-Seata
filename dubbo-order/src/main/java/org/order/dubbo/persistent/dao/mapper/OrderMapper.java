package org.order.dubbo.persistent.dao.mapper;


import org.apache.ibatis.annotations.Param;
import org.order.dubbo.api.domain.UserDTO;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderMapper {

   void insertOrder(@Param("userDTO") UserDTO userDTO);

   void deleteOrder(@Param("userDTO") UserDTO userDTO);
}
