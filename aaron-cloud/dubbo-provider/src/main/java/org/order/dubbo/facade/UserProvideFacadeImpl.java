package org.order.dubbo.facade;


import org.apache.dubbo.config.annotation.DubboService;
import org.order.dubbo.api.domain.UserDTO;
import org.order.dubbo.api.facade.UserProvideFacade;

@DubboService
public class UserProvideFacadeImpl implements UserProvideFacade {
    @Override
    public UserDTO queryUser(String name) {
        UserDTO userDTO = new UserDTO();
        userDTO.setUserId(123);
        userDTO.setUserName(name);
        return userDTO;
    }
}
