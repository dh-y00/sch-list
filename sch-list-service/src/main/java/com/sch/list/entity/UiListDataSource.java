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
 * 列表数据来源;
 * </p>
 *
 * @author author
 * @since 2025-02-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("ui_list_data_source")
public class UiListDataSource extends BaseColumn {

    private static final long serialVersionUID = 1L;

    /**
     * 页面编号
     */
    private String pageId;

    /**
     * 数据查询sql
     */
    private String selectSql;

    /**
     * 查询总数sql
     */
    private String countSql;

    /**
     * 通过 java_bean的方式构建数据，优先级较低
     */
    private String javaBean;
}
