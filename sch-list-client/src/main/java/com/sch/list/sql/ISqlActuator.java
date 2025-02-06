package com.sch.list.sql;


import com.sch.list.definition.ListDefinition;
import com.sch.list.pojo.ListExecuteDto;

import java.util.List;
import java.util.Map;

/**
 * 这边定义 sql 处理工厂接口
 * 这个项目本质上就是对sql 的处理
 * 未来不可预测，目前的场景只是 mysql + mybatis
 * 但是 数据库可能是 oracle
 * dao框架也可能是 hibernate
 * 等等
 * 无法预测，所以这边定义好接口，仅仅只是为了后面拓展罢了
 * @author yaodonghu
 */
public interface ISqlActuator {

    /**
     * sql 加载
     * 仅仅只是建议第一次调用的时候 加载一次
     * @param listDefinitions sql 的list实体类
     */
    void load(List<ListDefinition> listDefinitions);

    /**
     *  根据 pageId 查询对应数据
     * @param listExecuteDto
     * @return
     */
    List<Map<String, Object>> query(ListExecuteDto listExecuteDto);

}
