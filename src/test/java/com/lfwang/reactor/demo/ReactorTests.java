package com.lfwang.reactor.demo;

import com.lfwang.reactor.demo.support.MyReactiveLibrary;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.test.StepVerifier;

import org.assertj.core.api.Assertions;

import java.time.Duration;

/**
 * @author keleguo
 * @date Created in 2018/5/21
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ReactorTests {

    @Autowired
    private MyReactiveLibrary library;

    @Test
    public void testAlphabet5LimitsToZ1() {
        StepVerifier.create(library.alphabet5('x'))
                .expectNext("x", "y", "z")
                .expectComplete()
                .verify();
    }

    @Test
    public void testAlphabet5LimitsToZ() {
        StepVerifier.create(library.alphabet5('x'))
                .consumeNextWith(c -> Assertions.assertThat(c)
                        .as("first is alphabetic").matches("[a-z]"))
                .consumeNextWith(c -> Assertions.assertThat(c)
                        .as("second is alphabetic").matches("[a-z]"))
                .consumeNextWith(c -> Assertions.assertThat(c)
                        .as("third is alphabetic").matches("[a-z]"))
                .expectComplete()
                .verify();
    }

    @Test
    public void testWithDelay() {
        Duration testDuration = StepVerifier.withVirtualTime(() -> library.withDelay("foo", 30))
                .expectSubscription()
                .thenAwait(Duration.ofSeconds(10))
                .expectNoEvent(Duration.ofSeconds(10))
                .thenAwait(Duration.ofSeconds(10))
                .expectNext("foo")
                .expectComplete()
                .verify();

        System.out.println(testDuration.toMillis() + " ms");
    }
}
