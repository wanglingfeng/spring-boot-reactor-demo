package com.lfwang.reactor.demo;

import com.lfwang.reactor.demo.repository.domain.User;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

/**
 * @author keleguo
 * @date Created in 2018/6/7
 */
public class WebClientTests {

    @Test
    public void testHello() throws InterruptedException {
        // 创建WebClient对象并指定baseUrl
        WebClient webClient = WebClient.create("http://localhost:8080");

        Mono<String> resp = webClient
                .get().uri("/hello")
                .retrieve() // 异步地获取response信息
                .bodyToMono(String.class); // 将response body解析为字符串
        resp.subscribe(System.out::println); // 打印出来
        Thread.sleep(1000); // 由于是异步的，我们将测试线程sleep 1秒确保拿到response，也可以像前边的例子一样用CountDownLatch
    }

    @Test
    public void testUser() {
        // 这次我们使用WebClientBuilder来构建WebClient对象
        WebClient webClient = WebClient.builder().baseUrl("http://localhost:8080").build();

        webClient.get().uri("/user")
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .retrieve()
                .bodyToFlux(User.class)
                .log()
                .blockLast();
    }

    @Test
    public void testTimes() {
        WebClient webClient = WebClient.create("http://localhost:8080");

        webClient.get().uri("/times")
                .accept(MediaType.TEXT_EVENT_STREAM)
                .retrieve()
                .bodyToFlux(String.class)
                .log()
                .take(10)
                .blockLast();
    }
}
