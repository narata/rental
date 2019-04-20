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
 * @since 2019-04-20
 */
@Data
@Accessors(chain = true)
public class Collection{

    @TableId
    private Long id;

    private Long houseId;

    private Long userId;

    @TableLogic
    private Integer idDeleted;


}
