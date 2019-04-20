package com.narata.rental.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author narata
 * @since 2019-04-16
 */
@Data
@Accessors(chain = true)
public class House {

    /**
     * id
     */
    @TableId
    private Long id;

    /**
     * 租金
     */
    private Integer rent;

    /**
     * 位置
     */
    private String location;

    /**
     * 大小
     */
    private Integer size;

    /**
     * 户型
     */
    private String type;

    /**
     * 楼层
     */
    private Integer floor;

    /**
     * 朝向
     */
    private String orientation;

    /**
     * 状态，0未租，1已租
     */
    private Integer status;

    /**
     * 图片位置
     */
    private String image;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 0 房源信息，1 求租信息
     */
    private Integer rentHouse;

    /**
     * 删除标志位
     */
    @TableLogic
    private Boolean isDeleted;


}
