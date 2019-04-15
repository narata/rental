package com.narata.rental.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.narata.rental.entity.UserEntity;

/**
 * @author narata
 * @since 2019/03/26
 */
public interface IUserService extends IService<UserEntity> {
    /**
     * 通过用户名获得用户
     * @param username 用户名
     * @return {@link UserEntity}
     */
    UserEntity getUserByUsername(String username);

    /**
     * 用户注册
     * @param userEntity 用户实体 {@link UserEntity}
     * @return true or false
     */
    boolean register(UserEntity userEntity);
}
