package com.lfwang.reactor.demo.repository.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;


/**
 * @author keleguo
 * @date Created in 2018/6/7
 */
@Document
@NoArgsConstructor
@AllArgsConstructor
@Data
public class User {

    @Id
    private String id; // 注解属性id为ID
    @Indexed(unique = true)
    private String username; // 注解属性username为索引，并且不能重复
    private String name;
    private String phone;
    private LocalDateTime birthday;
}
