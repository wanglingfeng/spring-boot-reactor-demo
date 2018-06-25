package com.lfwang.reactor.demo.repository.mapper;


import com.lfwang.reactor.demo.repository.domain.Product;
import org.springframework.stereotype.Repository;

/**
 * Created by lfwang on 2017/8/21.
 */
@Repository
public interface ProductMapper {

    Product getById(long id);

    long insert(Product product);

    void update(Product product);

    void deleteById(long id);

    void delete(Product product);
}
