package org.order.dubbo.facade;




import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.DubboService;


import org.order.dubbo.api.domain.OrderInv;
import org.order.dubbo.api.domain.UserDTO;
import org.order.dubbo.api.facade.DubboBalanceFacade;
import org.order.dubbo.api.facade.DubboInvFacade;
import org.order.dubbo.persistent.dao.InvMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Random;

@DubboService
public class DubboInvFacadeImpl implements DubboInvFacade {


    @DubboReference
    private DubboBalanceFacade userBalanceFacade;

    @Autowired
    private InvMapper invMapper;

    @Override
    public UserDTO placeOrder(UserDTO userDTO) {
        // 生成订单

        OrderInv orderInv = new OrderInv();
        orderInv.setItemId(String.valueOf(Math.abs(new Random().nextInt())));
        orderInv.setItemName("商品01");
        userDTO.setOrderInv(orderInv);
      //  int exp=1/0;   // 设置异常点测试是否提交
        invMapper.insertInv(orderInv);
        System.out.print("商品信息生成完毕：" + userDTO.toString());
        // 扣减余额

        return userBalanceFacade.deductBalance(userDTO);
    }
}
