package com.narata.rental.controller;

import com.narata.rental.entity.UserEntity;
import com.narata.rental.service.IUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author narata
 * @since 2019/03/26
 */
@RestController
public class UserController {
    private final IUserService userService;

    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "login";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(
            @RequestBody UserEntity userEntity
            ) {
        userService.register(userEntity);
        return "success";
    }

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String hello() {
        return "hello";
    }

    @RequestMapping(value = "/not_login", method = RequestMethod.GET)
    public ResponseEntity noLogin() {
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }
}
