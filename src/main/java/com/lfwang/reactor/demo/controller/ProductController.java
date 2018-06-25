package com.lfwang.reactor.demo.controller;

import com.lfwang.reactor.demo.repository.domain.Product;
import com.lfwang.reactor.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * @author keleguo
 * @date Created in 2018/6/25
 */
@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping(value = "/{id}")
    public Mono<Product> getById(@PathVariable Long id) {
        return productService.getById(id);
    }
}
