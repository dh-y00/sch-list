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
 * 列表页面操作按钮;
 * </p>
 *
 * @author author
 * @since 2025-02-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("ui_list_page_btn")
public class UiListPageBtn extends BaseColumn {

    private static final long serialVersionUID = 1L;

    /**
     * 页面编号
     */
    @TableId(value = "page_id", type = IdType.AUTO)
    private String pageId;

    /**
     * 按钮code
     */
    private String code;

    /**
     * 按钮类型，1-页面功能按钮 2-页面行数据操作按钮
     */
    private String btnType;

    /**
     * 按钮名称
     */
    private String name;

    /**
     * 按钮排序
     */
    private Integer sort;

    /**
     * 路由
     */
    private String route;

    /**
     * 展示方式 1-弹框
     */
    private String showMethod;

    /**
     * 展示 表达式
     */
    private String showEl;
}
