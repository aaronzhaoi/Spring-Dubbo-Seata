package org.order.dubbo.api.domain;


import lombok.Data;

import java.io.Serializable;


public class CustomBalance implements Serializable {

    String userId;

    String balanceId;

    int balance;

    public CustomBalance() {
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getBalanceId() {
        return balanceId;
    }

    public void setBalanceId(String balanceId) {
        this.balanceId = balanceId;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }
}
