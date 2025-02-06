package com.sch.list.pojo;

import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * list 页面的数据
 * @author yaodonghu
 */
@Data
public class ListDataResp {

    /**
     * 总数
     */
    private Integer total;

    /**
     * 行数据
     */
    private List<Map<String, Object>> rows;

}
