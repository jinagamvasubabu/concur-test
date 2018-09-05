package com.vasu.dao;

import com.vasu.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;


@Transactional
public interface ProductRepository extends JpaRepository<Product, Long> {
    Product findByProductNameIgnoreCaseContaining(String productName);
}
