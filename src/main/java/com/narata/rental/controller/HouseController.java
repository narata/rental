package com.narata.rental.controller;


import com.fasterxml.jackson.databind.JsonNode;
import com.narata.rental.entity.House;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
    @RequestMapping(value = "/test", method = RequestMethod.POST)
    public House test(
            @RequestBody House house
            ) {
        return house;
    }
}

