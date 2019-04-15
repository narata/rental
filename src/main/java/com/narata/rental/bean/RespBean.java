package com.narata.rental.bean;

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
public class RespBean {
    private Integer status;
    private String msg;
    private Object obj;

    private RespBean() {
    }

    private RespBean(Integer status, String msg, Object obj) {
        this.status = status;
        this.msg = msg;
        this.obj = obj;
    }

    public static RespBean build() {
        return new RespBean();
    }

    public static RespBean ok(String msg, Object obj) {
        return new RespBean(200, msg, obj);
    }

    public static RespBean ok(String msg) {
        return new RespBean(200, msg, null);
    }

    public static RespBean error(String msg, Object obj) {
        return new RespBean(500, msg, obj);
    }

    public static RespBean error(String msg) {
        return new RespBean(500, msg, null);
    }
}
