package com.lfwang.reactor.demo.routing;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.springframework.web.reactive.function.server.ServerResponse.ok;

/**
 * @author keleguo
 * @date Created in 2018/6/7
 */
@Component
public class TimeHandler {

    public Mono<ServerResponse> getTime(ServerRequest serverRequest) {
        return ok().contentType(MediaType.TEXT_PLAIN)
                .body(Mono.just("Now is " + LocalDateTime.now()
                        .format(DateTimeFormatter.ISO_LOCAL_TIME)), String.class);
    }

    public Mono<ServerResponse> getTimePerSecond(ServerRequest serverRequest) {
        return ok().contentType(MediaType.TEXT_EVENT_STREAM)
                .body(Flux.interval(Duration.ofSeconds(1)).map(l -> LocalDateTime.now()
                        .format(DateTimeFormatter.ISO_LOCAL_TIME)), String.class);
    }

    public Mono<ServerResponse> getDate(ServerRequest serverRequest) {
        return ok().contentType(MediaType.TEXT_PLAIN)
                .body(Mono.just("Today is " + LocalDateTime.now()
                        .format(DateTimeFormatter.ISO_LOCAL_DATE)), String.class);
    }
}
