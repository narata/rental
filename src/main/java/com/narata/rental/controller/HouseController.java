package com.narata.rental.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.narata.rental.entity.House;
import com.narata.rental.entity.UserEntity;
import com.narata.rental.service.IHouseService;
import com.narata.rental.service.IUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author narata
 * @since 2019-04-16
 */
@RestController
@RequestMapping("/house")
public class HouseController{
    private final IUserService userService;
    private final IHouseService houseService;

    public HouseController(IUserService userService, IHouseService houseService) {
        this.userService = userService;
        this.houseService = houseService;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity add(
            @RequestBody House house,
            Principal principal
    ) {
        UserEntity user = userService.getUserByUsername(principal.getName());
        if (houseService.add(house, user)) {
            return new ResponseEntity(HttpStatus.CREATED);
        } else {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public ResponseEntity delete(
            @RequestParam("house_id") Long houseId,
            Principal principal
    ) {
        UserEntity user = userService.getUserByUsername(principal.getName());
        if (houseService.delete(houseId, user)) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity list(
            @RequestBody House houseSearch,
            @RequestParam("current") Integer current,
            @RequestParam("size") Integer size,
            Principal principal
    ) {
        UserEntity user = userService.getUserByUsername(principal.getName());
        IPage<House> page = houseService.list(user, current, size, houseSearch);
        return new ResponseEntity<>(page, HttpStatus.OK);
    }
}

