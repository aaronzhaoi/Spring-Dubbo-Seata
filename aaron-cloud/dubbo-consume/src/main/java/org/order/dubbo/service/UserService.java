package org.order.dubbo.service;


import org.apache.dubbo.config.annotation.DubboReference;

import org.order.dubbo.api.facade.UserProvideFacade;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @DubboReference
    private UserProvideFacade userProvideFacade;

    public String getMsg(String name) {
        return userProvideFacade.queryUser(name).toString();
    }

}