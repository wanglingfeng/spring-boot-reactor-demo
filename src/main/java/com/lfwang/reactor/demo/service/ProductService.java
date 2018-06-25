package com.lfwang.reactor.demo.service;

import com.lfwang.reactor.demo.repository.ProductRepository;
import com.lfwang.reactor.demo.repository.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

/**
 * @author keleguo
 * @date Created in 2018/6/25
 */
@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Mono<Product> getById(Long id) {
        return productRepository.getById(id);
    }
}
