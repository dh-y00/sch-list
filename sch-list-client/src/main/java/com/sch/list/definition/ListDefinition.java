package com.sch.list.definition;

import lombok.Data;

import java.util.List;

/**
 * 核心组建，构建整个
 */
@Data
public class ListDefinition {

    private String id;

    private String sql;

    /**
     * 是否需要分页
     */
    private Boolean needLimit = true;

    private List<ListSearchDefinition> searchFields;
}
