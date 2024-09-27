package org.order.dubbo.api.domain;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserDTO implements Serializable {
    private int userId;

    private String userName;
}
