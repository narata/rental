package com.narata.rental.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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

    @Override
    public IPage<UserEntity> getAll(UserEntity userEntity, Integer current, Integer size) {
        if (userEntity.getRole().equals(1)) {
            IPage<UserEntity> page = new Page<>(current, size);
            LambdaQueryWrapper<UserEntity> wrapper = new LambdaQueryWrapper<UserEntity>().orderByDesc(UserEntity::getId);
            return baseMapper.selectPage(page, wrapper);
        }
        return null;
    }

    @Override
    public boolean delete(UserEntity userEntity, Long userId) {
        if (userEntity.getRole().equals(1)) {
            return baseMapper.deleteById(userId) > 0;
        }
        return false;
    }

    @Override
    public boolean editRole(UserEntity userEntity, Long userId, Integer role) {
        if (userEntity.getRole().equals(1)) {
            UserEntity userEntity1 = getById(userId);
            userEntity1.setRole(role);
            return updateById(userEntity1);
        }
        return false;
    }
}
