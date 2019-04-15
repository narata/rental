package com.narata.rental.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.narata.rental.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author narata
 * @since 2019/03/26
 */

@Mapper
public interface UserMapper extends BaseMapper<UserEntity> {
}
