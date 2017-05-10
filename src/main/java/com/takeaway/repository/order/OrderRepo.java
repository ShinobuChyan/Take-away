package com.takeaway.repository.order;

import com.takeaway.model.order.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepo extends JpaRepository<Order, Long> {

    Page<Order> findByUserId(Pageable pageable, Long userId);

}
