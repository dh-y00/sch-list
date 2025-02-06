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
 * 列表页面搜索;
 * </p>
 *
 * @author author
 * @since 2025-02-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("ui_list_page_search")
public class UiListPageSearch extends BaseColumn {

    private static final long serialVersionUID = 1L;

    /**
     * 页面编号
     */
    @TableId(value = "page_id", type = IdType.AUTO)
    private String pageId;

    /**
     * 搜索code
     */
    private String code;

    /**
     * 搜索名称
     */
    private String name;

    /**
     * 搜索类型
     */
    private String searchType;

    /**
     * web页面的搜索类型
     */
    private String webSearchType;

    /**
     * 是否多 0-否 1-是
     */
    private String isMany;

    /**
     * 如果下拉框之类的，下拉框取值
     */
    private String valEl;

}
