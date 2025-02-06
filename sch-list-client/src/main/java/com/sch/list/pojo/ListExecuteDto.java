package com.sch.list.pojo;

import lombok.Data;

import java.util.Map;

@Data
public class ListExecuteDto {

    /**
     * 需要执行SQL的函数ID
     */
    private String pageId;

    /** 请求参数 */
    private Map<String, Object> params;

}
