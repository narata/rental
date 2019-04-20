package com.narata.rental.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.narata.rental.entity.Comment;
import com.narata.rental.entity.UserEntity;
import com.narata.rental.mapper.CommentMapper;
import com.narata.rental.service.ICommentService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author narata
 * @since 2019-04-20
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements ICommentService {

    @Override
    public boolean add(String comment, Long houseId, Long userId) {
        return baseMapper.insert(new Comment()
                .setComment(comment)
                .setHouseId(houseId)
                .setUserId(userId)
                .setTime(LocalDateTime.now())
        ) > 0;
    }

    @Override
    public boolean delete(Long commentId, UserEntity userEntity) {
        Comment comment = getById(commentId);
        if (Objects.nonNull(comment)) {
            if (comment.getUserId().equals(userEntity.getId()) || userEntity.getRole().equals(1)) {
                return baseMapper.deleteById(commentId) > 0;
            }
        }
        return false;
    }

    @Override
    public IPage<Comment> list(Long houseId, Integer current, Integer size) {
        IPage<Comment> page = new Page<>(current, size);
        LambdaQueryWrapper<Comment> wrapper = new QueryWrapper<Comment>().lambda().eq(Comment::getHouseId, houseId);
        return baseMapper.selectPage(page, wrapper);
    }
}
