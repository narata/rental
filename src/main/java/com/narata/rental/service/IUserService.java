package com.narata.rental.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
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

    /**
     * 获取所有用户信息
     * @param userEntity 登录用户对象
     * @param current 当前也
     * @param size 每页数量
     * @return  {@link IPage<UserEntity>}
     */
    IPage<UserEntity> getAll(UserEntity userEntity, Integer current, Integer size);

    /**
     * 删除用户
     * @param userEntity 登录用户对象
     * @param userId 删除用户id
     * @return true or false
     */
    boolean delete(UserEntity userEntity, Long userId);

    /**
     * 用户权限修改
     * @param userEntity 登录用户对象
     * @param userId 修改权限用户id
     * @param role 修改后的权限
     * @return true or false
     */
    boolean editRole(UserEntity userEntity, Long userId, Integer role);
}
