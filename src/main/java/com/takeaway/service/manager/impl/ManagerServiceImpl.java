package com.takeaway.service.manager.impl;

import com.takeaway.model.order.Order;
import com.takeaway.repository.order.OrderRepo;
import com.takeaway.service.manager.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class ManagerServiceImpl implements ManagerService{

    private final OrderRepo orderRepo;

    @Autowired
    public ManagerServiceImpl(OrderRepo orderRepo) {
        this.orderRepo = orderRepo;
    }

    @Override
    public void changeState(Long id) throws Exception {

        Order order = orderRepo.findById(id);
        order.setState(0);
        orderRepo.save(order);

    }

}
