package org.order.dubbo.api.facade;

import org.order.dubbo.api.domain.UserDTO;

public interface UserProvideFacade {
    UserDTO queryUser(String name);
}
