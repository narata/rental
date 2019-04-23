package com.narata.rental.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.narata.rental.entity.Collection;
import com.narata.rental.entity.UserEntity;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author narata
 * @since 2019-04-20
 */
public interface ICollectionService extends IService<Collection> {
    /**
     * 增加收藏
     * @param houseId 房屋id
     * @param userId 用户id
     * @return true or false
     */
    boolean add(Long houseId, Long userId);

    /**
     * 取消收藏
     * @param collectionId 收藏id
     * @param userEntity 用户
     * @return true or false
     */
    boolean delete(Long collectionId, UserEntity userEntity);

    /**
     * 查看用户收藏
     * @param userEntity 用户
     * @param current 当前页
     * @param size 每页大小
     * @return {@link IPage<Collection>}
     */
    IPage<Collection> list(UserEntity userEntity, Integer current, Integer size);

    /**
     * 列出用户的所有收藏房间id
     * @param userEntity 用户
     * @return {@link List<Long>}
     */
    List<Long> listHouseIdByUser(UserEntity userEntity);

    /**
     * 通过房源id与用户id获得Collection
     * @param houseId 房源id
     * @param userId 用户id
     * @return {@link Collection}
     */
    Collection selectByHouseAndUserId(Long houseId, Long userId);
}
