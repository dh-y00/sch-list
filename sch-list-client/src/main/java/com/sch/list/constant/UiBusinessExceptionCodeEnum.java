package com.sch.list.constant;

import com.rdrk.rsf.framework.constant.SystemConstant;
import lombok.Getter;

@Getter
public enum UiBusinessExceptionCodeEnum {

    ERR_UI10001("%s", "参数校验失败");

    private String code;

    private String message;

    private String desc;

    UiBusinessExceptionCodeEnum(String message, String desc) {
        String name = this.name();
        this.code = name.split(SystemConstant.UNDERLINE)[1];
        this.message = message;
        this.desc = desc;
    }

}
