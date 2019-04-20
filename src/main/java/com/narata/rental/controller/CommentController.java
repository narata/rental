package com.narata.rental.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.narata.rental.entity.Comment;
import com.narata.rental.entity.UserEntity;
import com.narata.rental.service.ICommentService;
import com.narata.rental.service.IUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author narata
 * @since 2019-04-20
 */
@RestController
@RequestMapping("/comment")
public class CommentController{
    private final IUserService userService;
    private final ICommentService commentService;

    public CommentController(IUserService userService, ICommentService commentService) {
        this.userService = userService;
        this.commentService = commentService;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity add(
            @RequestParam("house_id") Long houseId,
            @RequestParam("comment") String comment,
            Principal principal
    ) {
        UserEntity user = userService.getUserByUsername(principal.getName());
        if (commentService.add(comment, houseId, user.getId())) {
            return new ResponseEntity(HttpStatus.CREATED);
        } else {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public ResponseEntity delete(
            @RequestParam("comment_id") Long commentId,
            Principal principal
    ) {
        UserEntity user = userService.getUserByUsername(principal.getName());
        if (commentService.delete(commentId, user)) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity list(
            @RequestParam("house_id") Long houseId,
            @RequestParam("current") Integer current,
            @RequestParam("size") Integer size
    ) {
        IPage<Comment> page = commentService.list(houseId, current, size);
        return new ResponseEntity<>(page, HttpStatus.OK);
    }

}

