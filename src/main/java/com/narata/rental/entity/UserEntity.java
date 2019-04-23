package com.narata.rental.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author narata
 * @since 2019/03/26
 */

@Data
@Accessors(chain = true)
@TableName(value = "user")
public class UserEntity{

    @TableId
    private Long id;

    private String username;
    private String password;
    private String name;
    private String email;
    private String imgUrl;
    private String phone;
    private Integer role;

    @TableLogic
    private Integer isDeleted;
}
