package com.lfwang.reactor.demo.repository;

import com.lfwang.reactor.demo.repository.domain.MyEvent;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.mongodb.repository.Tailable;
import reactor.core.publisher.Flux;

/**
 * @author keleguo
 * @date Created in 2018/6/7
 */
public interface MyEventRepository extends ReactiveMongoRepository<MyEvent, Long> {

    @Tailable
    Flux<MyEvent> findBy();
}
