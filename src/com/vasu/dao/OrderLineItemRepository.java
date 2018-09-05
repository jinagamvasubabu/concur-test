package com.vasu.dao;

import com.vasu.model.OrderLineItems;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;


@Transactional
public interface OrderLineItemRepository extends JpaRepository<OrderLineItems, Long> {
    List<OrderLineItems> findByOrderId(Long orderId);
}
