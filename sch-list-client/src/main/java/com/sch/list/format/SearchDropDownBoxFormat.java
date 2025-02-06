package com.sch.list.format;

import lombok.Data;

import java.util.List;

/**
 * 搜索下拉框的格式
 * @author yaodonghu
 */
@Data
public class SearchDropDownBoxFormat {

    private String key;

    private String value;

    private List<SearchDropDownBoxFormat> children;

}
