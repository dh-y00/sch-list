package com.sch.list.pojo;

import lombok.Data;
import com.sch.list.constant.PageConfigConstant;

/**
 * 页面 按钮配置
 * @author yaodonghu
 */
@Data
public class PageBtnConfig {

    /**
     * 按钮名称
     */
    private String name;

    /**
     * 按钮code
     */
    private String code;

    /**
     * 按钮的类型
     * {@link PageConfigConstant.BtnTypeEnum}
     */
    private String type;

    /**
     * 按钮点击后所对应的路由
     */
    private String route;

    /**
     * 按钮触发路由之后弹出的方式
     * {@link PageConfigConstant.RouteShowMethodEnum}
     */
    private String showMethod;

    /**
     * 按钮排序
     */
    private Integer sort;

    /**
     *
     */
    private String el;

    /**
     * 按钮参数
     */
    @Data
    public static class PageBtnParams {

        /**
         * 参数名
         */
        private String name;

        /**
         * 对应的值
         */
        private String value;

    }

}
