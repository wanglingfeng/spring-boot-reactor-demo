package com.lfwang.reactor.demo.repository;

import com.lfwang.reactor.demo.repository.domain.User;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

/**
 * @author keleguo
 * @date Created in 2018/6/7
 */
public interface UserRepository extends ReactiveCrudRepository<User, String> {

    Mono<User> findByUsername(String username);
    Mono<Long> deleteByUsername(String username);
}
