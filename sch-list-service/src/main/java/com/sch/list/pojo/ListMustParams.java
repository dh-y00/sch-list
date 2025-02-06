package com.sch.list.pojo;

import lombok.Data;

@Data
public class ListMustParams {

    private String pageId;

    private Integer pageNum = 1;

    private Integer pageSize = 10;

}
