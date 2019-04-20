package com.narata.rental.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.narata.rental.entity.House;
import com.baomidou.mybatisplus.extension.service.IService;
import com.narata.rental.entity.UserEntity;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author narata
 * @since 2019-04-16
 */
public interface IHouseService extends IService<House> {
    /**
     * 添加房源
     * @param house 房源对象
     * @param user 用户对象
     * @return true or false
     */
    boolean add(House house, UserEntity user);

    /**
     * 修改房源信息
     * @param house 房源对象
     * @param user 用户
     * @return true or false
     */
    boolean edit(House house, UserEntity user);

    /**
     * 删除房源
     * @param houseId 房子id
     * @param user 用户
     * @return true or false
     */
    boolean delete(Long houseId, UserEntity user);

    /**
     * 分页查找房源信息
     * @param userEntity 用户
     * @param current 目前页数
     * @param size 每页大小
     * @param houseSearch 查询字段
     * @return {@link IPage<House>}
     */
    IPage<House> list(UserEntity userEntity, Integer current, Integer size, House houseSearch);

}
