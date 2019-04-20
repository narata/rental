package com.narata.rental.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @author narata
 * @since 2019/03/26
 */

@Data
@EqualsAndHashCode(callSuper = false)
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
    private Integer role;
}
