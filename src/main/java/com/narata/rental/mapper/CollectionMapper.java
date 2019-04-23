package com.narata.rental.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.narata.rental.entity.Collection;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author narata
 * @since 2019-04-20
 */
public interface CollectionMapper extends BaseMapper<Collection> {

    /**
     * 通过房屋id和用户id查找收藏
     * @param houseId 房屋id
     * @param userId 用户id
     * @return {@link Collection}
     */
    default Collection selectByHouseAndUserId(Long houseId, Long userId) {
        return selectOne(new QueryWrapper<Collection>().lambda()
                .eq(Collection::getHouseId, houseId)
                .eq(Collection::getUserId, userId)
        );
    }
}
