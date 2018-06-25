package com.lfwang.reactor.demo;

import com.lfwang.reactor.demo.repository.ReactiveUserInfoRepository;
import com.lfwang.reactor.demo.repository.domain.UserInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.core.publisher.Flux;

/**
 * @author keleguo
 * @date Created in 2018/5/21
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserInfoRepositoryTests {

    @Autowired
    private ReactiveUserInfoRepository repository;

    @Test
    public void findAll() {
        Flux<UserInfo> flux = Flux.defer(() -> repository.findAll());
        flux.subscribe(System.out::println);
    }

    @Test
    public void findAllWithDelay() throws InterruptedException {
        Flux<UserInfo> flux = Flux.defer(() -> repository.findAll());
        flux = repository.withDelay(flux);

        System.out.println("before sleep");
        Thread.sleep(2000);
        System.out.println();

        // 订阅前flux不会执行任何代码
        flux.subscribe(System.out::println);

        Thread.sleep(10000);
    }
}
