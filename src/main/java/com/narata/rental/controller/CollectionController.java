package com.narata.rental.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.narata.rental.entity.Collection;
import com.narata.rental.entity.UserEntity;
import com.narata.rental.service.ICollectionService;
import com.narata.rental.service.IUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author narata
 * @since 2019-04-20
 */
@RestController
@RequestMapping("/collection")
public class CollectionController {
    private final IUserService userService;
    private final ICollectionService collectionService;

    public CollectionController(IUserService userService, ICollectionService collectionService) {
        this.userService = userService;
        this.collectionService = collectionService;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity add(
            @RequestParam("house_id") Long houseId,
            Principal principal
            ) {
        UserEntity user = userService.getUserByUsername(principal.getName());
        if (collectionService.add(houseId, user.getId())) {
            return new ResponseEntity(HttpStatus.CREATED);
        } else {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public ResponseEntity delete(
            @RequestParam("collection_id") Long collectionId,
            Principal principal
    ) {
        UserEntity user = userService.getUserByUsername(principal.getName());
        if (collectionService.delete(collectionId, user)) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity list(
            @RequestParam("current") Integer current,
            @RequestParam("size") Integer size,
            Principal principal
    ) {
        UserEntity user = userService.getUserByUsername(principal.getName());
        IPage<Collection> page = collectionService.list(user, current, size);
        return new ResponseEntity<>(page, HttpStatus.OK);
    }

    @RequestMapping(value = "/list/houseId", method = RequestMethod.GET)
    public ResponseEntity listHouseId(
            Principal principal
    ) {
        UserEntity user = userService.getUserByUsername(principal.getName());
        List<Long> result = collectionService.listHouseIdByUser(user);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/byHouseId", method = RequestMethod.DELETE)
    public ResponseEntity deleteByHouseId(
            @RequestParam("house_id") Long houseId,
            Principal principal
    ) {
        UserEntity user = userService.getUserByUsername(principal.getName());
        Collection collection = collectionService.selectByHouseAndUserId(houseId, user.getId());
        if (collectionService.delete(collection.getId(), user)) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }
}

