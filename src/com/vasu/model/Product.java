package com.vasu.model;

import lombok.Data;

import javax.persistence.*;


@Data
@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="product_id")
    private Long productId;

    @Column(name="product_name", unique = true, nullable = false)
    private String productName;

    @Column(name="quantity", nullable = false)
    private Integer quantity;

    @Column(name="price", nullable = false)
    private Integer price;

}
