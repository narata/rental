package com.narata.rental.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.narata.rental.entity.UserEntity;
import com.narata.rental.mapper.UserMapper;
import com.narata.rental.service.IUserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author narata
 * @since 2019/03/26
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserEntity> implements IUserService {
    @Override
    public UserEntity getUserByUsername(String username) {
        return baseMapper.selectOne(new QueryWrapper<UserEntity>().lambda().eq(UserEntity::getUsername, username));
    }

    @Override
    public boolean register(UserEntity userEntity) {
        userEntity.setPassword(new BCryptPasswordEncoder().encode(userEntity.getPassword()));
        return baseMapper.insert(userEntity) > 0;
    }
}
