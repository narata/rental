package com.narata.rental.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.narata.rental.entity.House;
import com.narata.rental.entity.UserEntity;
import com.narata.rental.service.IHouseService;
import com.narata.rental.service.IUserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * @author narata
 * @since 2019/04/16
 */

@SpringBootTest
@RunWith(SpringRunner.class)
public class HouseServiceImplTest {
    @Autowired
    IHouseService houseService;
    @Autowired
    IUserService userService;

    @Test
    public void add() {
        UserEntity user = userService.getById(2);
        House house = new House().setRent(1);
        assertTrue(houseService.add(house, user));
    }

    @Test
    public void edit() {
        UserEntity user = userService.getById(1);
        House house = houseService.getById(1);
        house.setFloor(10);
        assertTrue(houseService.edit(house, user));
    }

    @Test
    public void delete() {
        UserEntity user = userService.getById(2);
        assertTrue(houseService.delete(1L, user));
    }

    @Test
    public void list() throws JsonProcessingException {
        UserEntity userEntity = userService.getById(1);
        House house = new House();
        System.out.println(new ObjectMapper().writeValueAsString(houseService.list(userEntity, 1, 2, house)));
    }
}