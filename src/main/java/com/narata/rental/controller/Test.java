package com.narata.rental.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author narata
 * @since 2019/03/25
 */

@RestController
public class Test {
    @RequestMapping("/hello")
    public String hello() {
        return "Hello World!";
    }

    @RequestMapping("/hello_login")
    public String helloLogin() {
        return "Hello Login";
    }
}
