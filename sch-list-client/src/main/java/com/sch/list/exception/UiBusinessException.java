package com.sch.list.exception;

import com.rdrk.rsf.framework.exception.RsfBusinessException;
import com.sch.list.constant.UiBusinessExceptionCodeEnum;

import java.util.Arrays;

public class UiBusinessException extends RsfBusinessException {

    private String message;

    private String code;

    public UiBusinessException(UiBusinessExceptionCodeEnum businessExceptionCodeEnum, String... ex) {
        this(String.format(businessExceptionCodeEnum.getMessage(), Arrays.stream(ex).toArray()) , businessExceptionCodeEnum.getCode());
    }

    public UiBusinessException(UiBusinessExceptionCodeEnum businessExceptionCodeEnum, Throwable e, String... ex) {
        this(String.format(businessExceptionCodeEnum.getMessage(), Arrays.stream(ex).toArray()) , e, businessExceptionCodeEnum.getCode());
    }

    public UiBusinessException(String message, String code) {
        super(message, code);
        this.message = message;
        this.code = code;
    }

    public UiBusinessException(String message, Throwable e, String code) {
        super(message, code, e);
        this.message = message;
        this.code = code;
    }
}
