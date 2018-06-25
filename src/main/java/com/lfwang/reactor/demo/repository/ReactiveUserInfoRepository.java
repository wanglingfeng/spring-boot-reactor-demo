package com.lfwang.reactor.demo.repository;

import com.lfwang.reactor.demo.repository.domain.UserInfo;
import org.reactivestreams.Publisher;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author keleguo
 * @date Created in 2018/5/18
 */
@Repository
public class ReactiveUserInfoRepository {

    private final static long DEFAULT_DELAY_IN_MS = 100;

    private final long delayInMs;

    private final List<UserInfo> userInfos;


    public ReactiveUserInfoRepository() {
        this(DEFAULT_DELAY_IN_MS);
    }

    public ReactiveUserInfoRepository(long delayInMs) {
        this.delayInMs = delayInMs;
        userInfos = new ArrayList<>(Arrays.asList(UserInfo.SKYLER, UserInfo.JESSE, UserInfo.WALTER, UserInfo.SAUL));
    }

    public ReactiveUserInfoRepository(UserInfo... userInfos) {
        this(DEFAULT_DELAY_IN_MS, userInfos);
    }

    public ReactiveUserInfoRepository(long delayInMs, UserInfo... userInfos) {
        this.delayInMs = delayInMs;
        this.userInfos = new ArrayList<>(Arrays.asList(userInfos));
    }

    public Mono<Void> save(Publisher<UserInfo> userPublisher) {
        return withDelay(Flux.from(userPublisher)).doOnNext(userInfos::add).then();
    }

    public Mono<UserInfo> findFirst() {
        return Mono.just(userInfos.get(0));
    }

    public Flux<UserInfo> findAll() {
        System.out.println("execute findAll");

        return Flux.fromIterable(userInfos);
    }

    public Mono<UserInfo> findById(String username) {
        UserInfo userInfo = userInfos.stream().filter((p) -> p.getUsername().equals(username))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("No userInfo with username " + username + " found!"));
        return Mono.just(userInfo);
    }


    public Mono<UserInfo> withDelay(Mono<UserInfo> userMono) {
        return Mono
                .delay(Duration.ofMillis(delayInMs))
                .flatMap(c -> userMono).single();
    }

    public Flux<UserInfo> withDelay(Flux<UserInfo> userFlux) {
        return Flux
                .interval(Duration.ofMillis(delayInMs))
                .zipWith(userFlux, (i, userInfo) -> userInfo);
    }
}
