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
 * 列表页面搜索与字段关系;
 * </p>
 *
 * @author author
 * @since 2025-02-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("ui_list_page_search_field_relation")
public class UiListPageSearchFieldRelation extends BaseColumn {

    private static final long serialVersionUID = 1L;

    /**
     * 页面编号
     */
    @TableId(value = "page_id", type = IdType.AUTO)
    private String pageId;

    /**
     * 搜索code
     */
    private String searchCode;

    /**
     * 字段code
     */
    private String fieldCode;
}
