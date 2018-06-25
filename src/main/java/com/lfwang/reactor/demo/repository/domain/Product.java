package com.lfwang.reactor.demo.repository.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by lfwang on 2017/8/21.
 */
@Data
public class Product implements Serializable {
    
    private long id;
    private String name;
    private long price;
}
