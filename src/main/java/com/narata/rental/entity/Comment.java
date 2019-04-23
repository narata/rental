package com.narata.rental.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

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
public class Comment{

    @TableId
    private Long id;

    private Long houseId;

    private Long userId;

    @TableField(exist = false)
    private String username;

    private String comment;

    private LocalDateTime time;

    @TableLogic
    private Integer isDeleted;


}
