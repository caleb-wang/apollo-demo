package com.it.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wanglb@belink.com
 * @version V1.0
 * @title
 * @description
 * @date 2024-06-20 15:03
 */
@RestController
public class TestController {
    @Value(value = "${test.name}")
    private String name;

    @RequestMapping("test")
    public String test(){
        return "hello world "+name;
    }
}
