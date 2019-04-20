package com.narata.rental.service.impl;

import com.narata.rental.entity.Comment;
import com.narata.rental.mapper.CommentMapper;
import com.narata.rental.service.ICommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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

}
