package com.narata.rental.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.narata.rental.entity.Collection;
import com.narata.rental.entity.UserEntity;
import com.narata.rental.mapper.CollectionMapper;
import com.narata.rental.service.ICollectionService;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author narata
 * @since 2019-04-20
 */
@Service
public class CollectionServiceImpl extends ServiceImpl<CollectionMapper, Collection> implements ICollectionService {

    @Override
    public boolean add(Long houseId, Long userId) {
        if (Objects.isNull(baseMapper.selectByHouseAndUserId(houseId, userId))) {
            Collection collection = new Collection().setHouseId(houseId).setUserId(userId);
            return baseMapper.insert(collection) > 0;
        }
        return false;
    }

    @Override
    public boolean delete(Long collectionId, UserEntity user) {
        Collection collection = baseMapper.selectById(collectionId);
        if (Objects.nonNull(collection)) {
            if (collection.getUserId().equals(user.getId()) || user.getRole().equals(1)) {
                return baseMapper.deleteById(collection.getId()) > 0;
            }
        }
        return false;
    }

    @Override
    public IPage<Collection> list(UserEntity userEntity, Integer current, Integer size) {
        IPage<Collection> page = new Page<>(current, size);
        LambdaQueryWrapper<Collection> wrapper = new QueryWrapper<Collection>().lambda().eq(Collection::getUserId, userEntity.getId());
        return baseMapper.selectPage(page, wrapper);
    }
}
