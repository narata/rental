package com.narata.rental.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author narata
 * @since 2019/04/03
 */

@AllArgsConstructor
public class DailyException extends RuntimeException {
    @Getter
    private int errorCode;

    public DailyException(String message) {
        super(message);
    }

    public DailyException(int errorCode, String message, Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
    }
}