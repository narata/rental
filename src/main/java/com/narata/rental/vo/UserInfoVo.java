package com.narata.rental.vo;

import lombok.Data;

/**
 * @author narata
 * @since 2019/04/21
 */

@Data
public class UserInfoVo {
    private Long id;
    private String name;
    private String email;
    private String phone;
    private Integer role;
}
