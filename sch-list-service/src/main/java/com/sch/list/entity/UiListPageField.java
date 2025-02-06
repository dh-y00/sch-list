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
 * 列表页面字段信息;
 * </p>
 *
 * @author author
 * @since 2025-02-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("ui_list_page_field")
public class UiListPageField extends BaseColumn {

    private static final long serialVersionUID = 1L;

    /**
     * 页面编号
     */
    @TableId(value = "page_id", type = IdType.AUTO)
    private String pageId;

    /**
     * 字段code
     */
    private String code;

    /**
     * 字段名称
     */
    private String name;

    /**
     * 显示格式
     */
    private String showStyle;

    /**
     * 是否需要排序 1-需要排序 0-不需要排序
     */
    private String isSort;

    /**
     * 是否展示表达式
     */
    private String showEl;

    /**
     * 列宽度
     */
    private String colWidth;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 计算值表达式
     */
    private String valEl;
}
