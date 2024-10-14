package org.order.dubbo.api.domain;


import lombok.Data;

import java.io.Serializable;


public class OrderInv implements Serializable {
    private String itemId;


    public OrderInv() {
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    private String itemName;
}
