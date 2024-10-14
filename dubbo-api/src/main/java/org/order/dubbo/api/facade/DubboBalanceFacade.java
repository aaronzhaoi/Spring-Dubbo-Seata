package org.order.dubbo.api.facade;

import org.order.dubbo.api.domain.UserDTO;

public interface DubboBalanceFacade {

    UserDTO deductBalance(UserDTO userDTO);
}
