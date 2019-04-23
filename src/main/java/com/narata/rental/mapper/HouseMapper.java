package com.narata.rental.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.narata.rental.entity.House;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author narata
 * @since 2019-04-16
 */
public interface HouseMapper extends BaseMapper<House> {
    /**
     * 获取被收藏的房源
     * @param page page
     * @param userId 用户id
     * @return List
     */
    IPage<House> selectPageCollection(Page page, @Param("user_id") Long userId);
}
