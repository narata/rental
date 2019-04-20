package com.narata.rental.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.narata.rental.entity.Comment;
import com.narata.rental.entity.UserEntity;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author narata
 * @since 2019-04-20
 */
public interface ICommentService extends IService<Comment> {

    /**
     * 添加评论
     * @param comment 评论内容
     * @param houseId 房屋id
     * @param userId 用户id
     * @return true or false
     */
    boolean add(String comment, Long houseId, Long userId);

    /**
     * 删除评论
     * @param commentId 评论id
     * @param userEntity 用户
     * @return true or false
     */
    boolean delete(Long commentId, UserEntity userEntity);

    /**
     * 查看房屋评论
     * @param houseId 房屋id
     * @param current 当前页
     * @param size 每页大小
     * @return {@link IPage<Comment>}
     */
    IPage<Comment> list(Long houseId, Integer current, Integer size);
}
