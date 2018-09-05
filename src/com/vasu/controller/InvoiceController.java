package com.vasu.controller;

import com.vasu.dao.OrderLineItemRepository;
import com.vasu.dao.OrderRepository;
import com.vasu.dao.ProductRepository;
import com.vasu.model.OrderLineItems;
import com.vasu.model.Product;
import com.vasu.pojo.OrderInvoice;
import com.vasu.pojo.ProductInvoice;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@Slf4j
public class InvoiceController {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderLineItemRepository orderLineItemRepository;


    @RequestMapping(value = "/api/invoice/order", method = GET)
    @ResponseBody
    public OrderInvoice getOrderInvoice(@RequestParam("orderId") Long orderId) {
        try {

            OrderInvoice result = new OrderInvoice();
            List<OrderLineItems> orderLineItemsList = orderLineItemRepository.findByOrderId(orderId);

            List<ProductInvoice> productInvoiceList = new ArrayList<>();
            for (OrderLineItems item : orderLineItemsList) {
                Product baseProduct = productRepository.findOne(item.getProductId());

                ProductInvoice invoice = new ProductInvoice();
                invoice.setProductId(item.getProductId());
                invoice.setProductName(item.getProductName());
                invoice.setQuantity(item.getQuantity());
                invoice.setTotalAmount((double) (item.getQuantity() * baseProduct.getPrice()));
                productInvoiceList.add(invoice);
            }

            Double totalAmount = 0.0;
            for (ProductInvoice invoice : productInvoiceList) {
                totalAmount += invoice.getTotalAmount();
            }

            result.setProductList(productInvoiceList);
            result.setTotalAmount(totalAmount);
            return result;
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }

    @RequestMapping(value = "/api/invoice/store", method = GET)
    @ResponseBody
    public OrderInvoice getStoreInvoice() {
        try {
            OrderInvoice result = new OrderInvoice();
            List<OrderLineItems> orderLineItemsList = orderLineItemRepository.findAll();

            List<ProductInvoice> productInvoiceList = new ArrayList<>();
            for (OrderLineItems item : orderLineItemsList) {
                Product baseProduct = productRepository.findOne(item.getProductId());

                ProductInvoice invoice = new ProductInvoice();
                invoice.setProductId(item.getProductId());
                invoice.setProductName(item.getProductName());
                invoice.setQuantity(item.getQuantity());
                invoice.setTotalAmount((double) (item.getQuantity() * baseProduct.getPrice()));
                productInvoiceList.add(invoice);
            }

            Double totalAmount = 0.0;
            for (ProductInvoice invoice : productInvoiceList) {
                totalAmount += invoice.getTotalAmount();
            }

            result.setProductList(productInvoiceList);
            result.setTotalAmount(totalAmount);
            return result;
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }

}
