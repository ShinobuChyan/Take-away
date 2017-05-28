package com.takeaway.model.order;

import java.util.List;
import java.util.Map;

public class NewOrder {

    private Integer addressId;

    private List<Map<String, Integer>> newOrder;

    public Integer getAddressId() {
        return addressId;
    }

    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
    }

    public List<Map<String, Integer>> getNewOrder() {
        return newOrder;
    }

    public void setNewOrder(List<Map<String, Integer>> newOrder) {
        this.newOrder = newOrder;
    }
}
