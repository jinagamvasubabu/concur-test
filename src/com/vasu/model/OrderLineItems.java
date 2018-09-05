package com.vasu.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "order_line_items")
public class OrderLineItems {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "order_line_id")
    private Long orderLineId;

    @Column(name = "order_id", nullable = false)
    private Long orderId;

    @Column(name = "product_id", nullable = false)
    private Long productId;

    @Column(name = "product_name", nullable = false)
    private String productName;


    @Column(name = "quantity", nullable = false)
    private Integer quantity;

}
