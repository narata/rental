package com.narata.rental.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.narata.rental.vo.UserInfoVo;
import com.narata.rental.entity.UserEntity;
import com.narata.rental.service.IHouseService;
import com.narata.rental.service.IUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author narata
 * @since 2019/03/26
 */
@RestController
public class UserController {
    private final IUserService userService;
    private final IHouseService houseService;

    public UserController(IUserService userService, IHouseService houseService) {
        this.userService = userService;
        this.houseService = houseService;
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "login";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity register(
            @RequestBody UserEntity userEntity
            ) {
        if (userService.register(userEntity)) {
            return new ResponseEntity(HttpStatus.CREATED);
        } else {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

    }

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String hello() {
        return "hello";
    }

    @RequestMapping(value = "/not_login", method = RequestMethod.GET)
    public ResponseEntity noLogin() {
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    @RequestMapping(value = "/user/info", method = RequestMethod.GET)
    public ResponseEntity getInfo(
            @RequestParam("user_id") Long userId
    ) {
        UserEntity userEntity = userService.getById(userId);
        Map<String, String> info = new HashMap<>(4);
        info.put("name", userEntity.getName());
        info.put("phone", userEntity.getPhone());
        info.put("email", userEntity.getEmail());
        return new ResponseEntity<>(info, HttpStatus.OK);
    }

    @RequestMapping(value = "/user/info/self", method = RequestMethod.GET)
    public ResponseEntity getInfoSelf(
            Principal principal
    ) {
        UserEntity userEntity = userService.getUserByUsername(principal.getName());
        Map<String, String> info = new HashMap<>(4);
        info.put("name", userEntity.getName());
        info.put("phone", userEntity.getPhone());
        info.put("email", userEntity.getEmail());
        return new ResponseEntity<>(info, HttpStatus.OK);
    }

    @RequestMapping(value = "/user/info/all", method = RequestMethod.GET)
    public ResponseEntity getInfoAll(
            @RequestParam("current") Integer current,
            @RequestParam("size") Integer size,
            Principal principal
    ) {
        UserEntity userEntity = userService.getUserByUsername(principal.getName());
        IPage<UserEntity> page = userService.getAll(userEntity,current,size);
        return new ResponseEntity<>(page, HttpStatus.OK);
    }

    @RequestMapping(value = "/user/update", method = RequestMethod.PATCH)
    public ResponseEntity updateInfo(
            @RequestBody UserInfoVo userInfo,
            Principal principal
    ) {
        UserEntity userEntity = userService.getUserByUsername(principal.getName());
        if (Objects.nonNull(userInfo.getRole()) && userEntity.getRole().equals(1) && Objects.nonNull(userInfo.getId())) {
            UserEntity userEntity1 = userService.getById(userInfo.getId());
            userEntity1.setRole(userInfo.getRole()).setPhone(userInfo.getPhone()).setEmail(userInfo.getEmail()).setName(userInfo.getName());
            userService.updateById(userEntity1);
        } else {
            userEntity.setName(userInfo.getName()).setEmail(userInfo.getEmail()).setPhone(userInfo.getPhone());
            userService.updateById(userEntity);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/user/editPassword", method = RequestMethod.POST)
    public ResponseEntity editPassword(
            @RequestParam("password") String password,
            Principal principal
    ) {
        UserEntity userEntity = userService.getUserByUsername(principal.getName());
        userEntity.setPassword(new BCryptPasswordEncoder().encode(password));
        if (userService.updateById(userEntity)) {
            return new ResponseEntity(HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

    }

    @RequestMapping(value = "/user/isAdmin", method = RequestMethod.GET)
    public ResponseEntity isAdmin(
            Principal principal
    ) {
        UserEntity userEntity = userService.getUserByUsername(principal.getName());
        if (userEntity.getRole().equals(1)) {
            return new ResponseEntity(HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.ACCEPTED);
        }
    }

    @RequestMapping(value = "/user", method = RequestMethod.DELETE)
    public ResponseEntity delete(
            Principal principal,
            @RequestParam("user_id") Long userId
    ) {
        UserEntity userEntity = userService.getUserByUsername(principal.getName());
        if (userEntity.getRole().equals(1)) {
            userService.delete(userEntity, userId);
            houseService.deleteByUserId(userEntity, userId);
            return new ResponseEntity(HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.FORBIDDEN);
        }
    }
}
