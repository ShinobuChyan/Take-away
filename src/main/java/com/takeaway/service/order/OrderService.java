package com.takeaway.service.order;

import com.takeaway.model.order.NewOrder;

public interface OrderService {

    void submitOrder(NewOrder newOrder) throws Exception;

}
