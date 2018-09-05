package com.vasu.pojo;

import lombok.Data;

@Data
public class ProductInvoice {
    private Long productId;
    private Integer quantity;
    private String productName;
    private Double totalAmount;
}
