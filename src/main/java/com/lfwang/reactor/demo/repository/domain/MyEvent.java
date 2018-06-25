package com.lfwang.reactor.demo.repository.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author keleguo
 * @date Created in 2018/6/7
 */
@Document(collection = "event")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class MyEvent {

    private Long id;
    private String message;
}
