package com.lfwang.reactor.demo.repository;

import com.lfwang.reactor.demo.repository.domain.Product;
import com.lfwang.reactor.demo.repository.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

/**
 * @author keleguo
 * @date Created in 2018/6/25
 */
@Repository
public class ProductRepository {

    @Autowired
    private ProductMapper productMapper;

    public Mono<Product> getById(Long id) {
        return Mono.justOrEmpty(productMapper.getById(id));
    }
}
