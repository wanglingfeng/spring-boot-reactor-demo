package com.lfwang.reactor.demo.controller;

import com.lfwang.reactor.demo.repository.domain.User;
import com.lfwang.reactor.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

/**
 * @author keleguo
 * @date Created in 2018/6/7
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public Flux<User> findAll() {
        return userService.findAll();
    }

    @GetMapping(value = "/delay", produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
    public Flux<User> findAllDelay() {
        return userService.findAll().delayElements(Duration.ofSeconds(2));
    }

    @GetMapping("/{username}")
    public Mono<User> findByUsername(@PathVariable String username) {
        return userService.findByUsername(username);
    }

    @PostMapping
    public Mono<User> save(User user) {
        return userService.save(user);
    }

    @DeleteMapping("/{username}")
    public Mono<Long> deleteByUsername(@PathVariable String username) {
        return userService.deleteByUsername(username);
    }
}
