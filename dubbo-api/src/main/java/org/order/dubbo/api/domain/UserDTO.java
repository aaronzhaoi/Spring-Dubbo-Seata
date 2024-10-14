package org.order.dubbo.api.domain;

import lombok.Data;

import java.io.Serializable;


public class UserDTO implements Serializable {
    private String userId;

    private String userName;

    private OrderInv orderInv;


    private CustomBalance customBalance;

    public UserDTO() {
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public OrderInv getOrderInv() {
        return orderInv;
    }

    public void setOrderInv(OrderInv orderInv) {
        this.orderInv = orderInv;
    }

    public CustomBalance getCustomBalance() {
        return customBalance;
    }

    public void setCustomBalance(CustomBalance customBalance) {
        this.customBalance = customBalance;
    }
}
