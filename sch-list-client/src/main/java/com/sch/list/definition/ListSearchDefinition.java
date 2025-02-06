package com.sch.list.definition;

import com.sch.list.constant.ListSearchConstant;
import lombok.Data;

import java.util.List;

/**
 * list 列表搜索的 definition
 */
@Data
public class ListSearchDefinition {

    /**
     * 字段名称
     */
    private List<String> fields;

    /**
     *  对应参数名
     */
    private String paramCode;

    /**
     * 对应搜索类型
     */
    private ListSearchConstant.SearchTypeEnum type;
}
