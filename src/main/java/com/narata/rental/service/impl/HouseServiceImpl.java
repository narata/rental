package com.narata.rental.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.narata.rental.entity.House;
import com.narata.rental.entity.UserEntity;
import com.narata.rental.mapper.HouseMapper;
import com.narata.rental.service.IHouseService;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author narata
 * @since 2019-04-16
 */
@Service
public class HouseServiceImpl extends ServiceImpl<HouseMapper, House> implements IHouseService {

    @Override
    public boolean add(House house, UserEntity user) {
        if (Objects.isNull(house.getId())) {
            return baseMapper.insert(house.setUserId(user.getId())) > 0;
        } else {
            return baseMapper.updateById(house) > 0;
        }

    }

    @Override
    public boolean edit(House house, UserEntity user) {
        if (house.getUserId().equals(user.getId()) || user.getRole().equals(1)) {
            return baseMapper.updateById(house) > 0;

        } else {
            return false;
        }
    }

    @Override
    public boolean delete(Long houseId, UserEntity user) {
        House house = baseMapper.selectById(houseId);
        if (house.getUserId().equals(user.getId()) || user.getRole().equals(1)) {
            return baseMapper.deleteById(houseId) > 0;
        } else {
            return false;
        }
    }

    @Override
    public boolean deleteByUserId(UserEntity userEntity, Long userId) {
        if (userEntity.getRole().equals(1)) {
            return baseMapper.delete(new LambdaQueryWrapper<House>().eq(House::getUserId, userId)) > 0;
        }
        return false;
    }

    @Override
    public IPage<House> list(Integer current, Integer size, House houseSearch) {
        IPage<House> page = new Page<>(current, size);
        LambdaQueryWrapper<House> wrapper = new QueryWrapper<House>().lambda();
        if (Objects.nonNull(houseSearch.getFloor())) {
            wrapper.like(House::getFloor, houseSearch.getFloor());
        }
        if (Objects.nonNull(houseSearch.getLocation())) {
            wrapper.like(House::getLocation, houseSearch.getLocation());
        }
        if (Objects.nonNull(houseSearch.getType())) {
            wrapper.like(House::getType, houseSearch.getType());
        }
        if (Objects.nonNull(houseSearch.getSize())) {
            wrapper.between(House::getSize, houseSearch.getSize() - 10, houseSearch.getSize() + 10);
        }
        if (Objects.nonNull(houseSearch.getRent())) {
            wrapper.le(House::getRent, houseSearch.getRent());
        }
        if (Objects.nonNull(houseSearch.getRentHouse())) {
            wrapper.eq(House::getRentHouse, houseSearch.getRentHouse());
        }
        if (Objects.nonNull(houseSearch.getOrientation())) {
            wrapper.like(House::getOrientation, houseSearch.getOrientation());
        }
        if (Objects.nonNull(houseSearch.getDescription())) {
            wrapper.like(House::getDescription, houseSearch.getDescription());
        }
        wrapper.orderByDesc(House::getId);
        return baseMapper.selectPage(page, wrapper);
    }

    @Override
    public IPage<House> listByUser(UserEntity userEntity, Integer current, Integer size) {
        IPage<House> page = new Page<>(current, size);
        LambdaQueryWrapper<House> wrapper = new QueryWrapper<House>().lambda();
        if (userEntity.getRole().equals(0)) {
            wrapper.eq(House::getUserId, userEntity.getId());
        }
        wrapper.orderByDesc(House::getId);
        return baseMapper.selectPage(page, wrapper);
    }

    @Override
    public IPage<House> listByCollection(UserEntity userEntity, Integer current, Integer size) {
        Page<House> page = new Page<>(current, size);
        return baseMapper.selectPageCollection(page, userEntity.getId());
    }

}
