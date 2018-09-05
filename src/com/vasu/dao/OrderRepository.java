package com.vasu.dao;

import com.vasu.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;


@Transactional
public interface OrderRepository extends JpaRepository<Order, Long> {

}
