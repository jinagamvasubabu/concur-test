package com.vasu.controller;

import com.vasu.dao.OrderLineItemRepository;
import com.vasu.dao.OrderRepository;
import com.vasu.dao.ProductRepository;
import com.vasu.model.Order;
import com.vasu.model.OrderLineItems;
import com.vasu.model.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@Slf4j
public class OrderController {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderLineItemRepository orderLineItemRepository;

    @RequestMapping(value = "/api/order/create", method = POST)
    @ResponseBody
    public String createOrder(@RequestBody List<Product> products) {
        Order createdOrder = new Order();
        try {

            if (products.size() < 1) {
                throw new Exception();
            }


            createdOrder.setCreatedAt(new Date());
            createdOrder = orderRepository.save(createdOrder);

            for (Product product : products) {

                Product selectedProduct = productRepository.findByProductNameIgnoreCaseContaining(product.getProductName());


                if (product.getQuantity() > selectedProduct.getQuantity()) {
                    throw new Exception();

                }
                OrderLineItems orderLineItem = new OrderLineItems();
                orderLineItem.setOrderId(createdOrder.getOrderId());
                orderLineItem.setProductId(selectedProduct.getProductId());
                orderLineItem.setProductName(product.getProductName());
                orderLineItem.setQuantity(product.getQuantity());
                orderLineItemRepository.save(orderLineItem);
                selectedProduct.setQuantity(selectedProduct.getQuantity() - product.getQuantity());
                productRepository.save(selectedProduct);
            }

            return "" + createdOrder.getOrderId();
        } catch (Exception e) {
            log.error(e.getMessage());
            orderRepository.delete(createdOrder.getOrderId());
            return "We don't have enough stock to fulfil this order!!!";
        }
    }


}



