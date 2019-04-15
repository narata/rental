package com.narata.rental.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import static com.narata.rental.common.ReturnCode.*;


/**
 * @author narata
 * @since 2019/03/30
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BaseJsonResponse {
    private Integer code;
    private String msg;
    private Object data;

    public static BaseJsonResponse success(Object data) {
        return new BaseJsonResponse().setCode(OK.getCode()).setData(data);
    }

    public static BaseJsonResponse success() {
        return new BaseJsonResponse().setCode(OK.getCode());
    }

    /**
     *
     *  请求调用方异常
     */
    public static BaseJsonResponse requestIllegalResponse() {
        return new BaseJsonResponse(BAD_REQUEST.getCode(), "please check your request!", null);
    }

    public static BaseJsonResponse requestIllegalResponse(String msg) {
        return new BaseJsonResponse(BAD_REQUEST.getCode(), msg, null);
    }

    public static BaseJsonResponse internalErrorResponse(String message) {
        return new BaseJsonResponse().setCode(ERROR_INTERNAL.getCode()).setMsg(message);
    }

    public static BaseJsonResponse internalErrorResponse() {
        return internalErrorResponse("internal error!");
    }
}
