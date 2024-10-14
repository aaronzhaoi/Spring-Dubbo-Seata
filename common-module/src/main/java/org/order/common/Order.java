package org.order.common;

import java.io.Serializable;

public class Order implements Serializable {

    private String id;

    private String productionName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProductionName() {
        return productionName;
    }

    public void setProductionName(String productionName) {
        this.productionName = productionName;
    }
}
