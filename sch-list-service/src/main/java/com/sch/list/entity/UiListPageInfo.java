package com.sch.list.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 列表页面信息;
 * </p>
 *
 * @author author
 * @since 2025-02-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("ui_list_page_info")
public class UiListPageInfo extends BaseColumn {

    private static final long serialVersionUID = 1L;

    /**
     * 页面编号
     */
    @TableId(value = "page_id", type = IdType.AUTO)
    private String pageId;

    /**
     * 页面名称
     */
    private String name;

    /**
     * 页面是否支持多选 0-不支持 1-支持
     */
    private String isChoose;

    /**
     * 列配置是否支持 0-不支持 1-支持
     */
    private String isColConfig;

    /**
     * 数据刷新是否支持 0-不支持 1-支持
     */
    private String isRefresh;

    /**
     * 当前页面的版本号
     */
    private String pageVersion;
}
