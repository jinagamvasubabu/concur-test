package com.vasu.controller;

import com.vasu.dao.ProductRepository;
import com.vasu.model.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@Slf4j
public class ProductController {
    @Autowired
    private ProductRepository productRepository;

    @RequestMapping(value = "/api/product/search", method = GET)
    @ResponseBody
    public Product searchProduct(@RequestParam("productName") String productName) {
        log.info(productName);
        try {
            Product product = productRepository.findByProductNameIgnoreCaseContaining(productName);
            if (product != null)
                return product;
            else
                return null;
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }

    @RequestMapping(value = "/api/product/add", method = POST)
    @ResponseBody
    public String add(@RequestBody Product product) {
        log.info(product.toString());
        try {
            productRepository.save(product);
            return "Product Added!!!";
        } catch (Exception e) {
            log.error(e.getMessage());
            return "Error: Cannot add product!!!!";
        }
    }

    @RequestMapping(value = "/api/product/quantityCheck", method = POST)
    @ResponseBody
    public String check(@RequestBody Product product) {
        log.info(product.toString());
        try {
            Product product1 = productRepository.findByProductNameIgnoreCaseContaining(product.getProductName());

            if (product1.getQuantity()<product.getQuantity()) {
                return "Product not available at this quantity!!!";
            } else {
                return "product available at inventory!!!";
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            return "Error: Not able to check product quantity!!!";
        }
    }
}
