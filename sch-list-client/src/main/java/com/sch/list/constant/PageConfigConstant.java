package com.sch.list.constant;

import lombok.Getter;

/**
 * 页面配置常量
 * @author yaodonghu
 */
public interface PageConfigConstant {

    @Getter
    enum BtnTypeEnum{
        /**
         * 按钮类型
         */
        TABLE_TOP_BTN("表格上的按钮"),
        TABLE_OPR_BTN("表格操作项按钮");

        private String code;
        private String desc;

        BtnTypeEnum(String desc) {
            this.code = this.name();
            this.desc = desc;
        }
    }

    @Getter
    enum RouteShowMethodEnum{

        ALERT("弹框");

        private String code;
        private String desc;

        RouteShowMethodEnum(String desc) {
            this.code = this.name();
            this.desc = desc;
        }



    }
}
