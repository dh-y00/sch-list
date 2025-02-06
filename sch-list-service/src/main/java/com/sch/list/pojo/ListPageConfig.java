package com.sch.list.pojo;

import com.sch.list.format.SearchDropDownBoxFormat;
import com.sch.list.constant.ListSearchConstant;
import lombok.Data;

import java.util.List;

/**
 * list 页面元素的配置情况 包含 搜索，功能区域情况，页面相关基础配置情况
 */
@Data
public class ListPageConfig {

    /**
     * 页面编码
     */
    private String pageId;

    /**
     * 页面名称
     */
    private String pageName;

    /**
     * 是否多选
     */
    private Boolean isChoose;

    /**
     * 是否支持列配置
     */
    private Boolean colConfig;

    /**
     * 是否支持数据刷新
     */
    private Boolean isRefresh;

    /**
     * 当前页面版本号
     */
    private String pageVersion;

    /**
     * 页面搜索配置
     */
    private List<PageSearchConfig> searchFields;

    /**
     * 按钮配置
     */
    private List<PageBtnConfig> btnConfig;

    /**
     * 列配置
     */
    private List<PageColConfig> column;

    /**
     * 页面列配置
     * @author yaodonghu
     */
    @Data
    public static class PageColConfig {

        private String code;

        private String name;

        /**
         * 是否排序
         */
        private Boolean isSort;

        /**
         * 列宽度
         */
        private String colWidth;

        private Integer sort;

    }

    /**
     * 页面搜索项配置
     * @author yaodonghu
     */
    @Data
    public static class PageSearchConfig {

        /**
         * 搜索的code
         */
        private String code;

        /**
         * 搜索的名称
         */
        private String name;

        /**
         * 搜索的类型 详细见
         * {@link ListSearchConstant.SearchTypeEnum}
         */
        private String type;

        /**
         * 如果是下拉框等有预选值，则会走这边返回至前端
         */
        private List<SearchDropDownBoxFormat> value;
    }
}
