package com.narata.rental.mapper;

import com.narata.rental.entity.House;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author narata
 * @since 2019-04-16
 */
@Mapper
public interface HouseMapper extends BaseMapper<House> {
}
