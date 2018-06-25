package com.lfwang.reactor.demo.controller;

import com.lfwang.reactor.demo.repository.domain.UserInfo;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author keleguo
 * @date Created in 2018/5/21
 */
@RestController
public class ExampleController {

    @GetMapping("hello")
    public Mono<String> hello() {
        return Mono.just("Welcome to reactive world ~");
    }

    @GetMapping("hello/{who}")
    public Mono<String> hello(@PathVariable String who) {
        return Mono.just(who).map(w -> "Hello " + w + " !");
    }

    @PostMapping("heyMister")
    public Flux<String> hey(@RequestBody Mono<UserInfo> body) {
        return Mono.just("Hey mister ")
                .concatWith(body
                        .flatMapMany(userInfo -> Flux.fromArray(userInfo.getLastname().split("")))
                        .map(String::toUpperCase)
                        .take(1)
                ).concatWith(Mono.just(". how are you?"));
    }
}
