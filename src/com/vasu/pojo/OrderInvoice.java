package com.vasu.pojo;

import lombok.Data;

import java.util.List;

@Data
public class OrderInvoice {
    private List<ProductInvoice> productList;
    private Double totalAmount;
}
