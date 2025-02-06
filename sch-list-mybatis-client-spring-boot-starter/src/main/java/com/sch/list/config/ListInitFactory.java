package com.sch.list.config;

import com.sch.list.definition.IListDefinitionBuilder;
import com.sch.list.definition.ListDefinition;
import com.sch.list.sql.ISqlActuator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * list 页面初始化工厂
 * 1、首先生成 ListDefinition
 * 2、将 对应sql 动态设置到 mybatis 中去
 */
public class ListInitFactory {

    private IListDefinitionBuilder listDefinitionBuilder;

    private ISqlActuator sqlActuator;

    private volatile Boolean isInit = false;

    private Logger logger = LoggerFactory.getLogger("ui-list");

    /**
     * 这边采用 构造方法的方式 注入bean
     * 好处
     * 1、显示的可以控制注入bean 名称
     * 2、防止 空指针异常
     * 3、为了拓展，后期可以按照一定规则注入对应的 bean
     * @param listDefinitionBuilder
     */
    public ListInitFactory(IListDefinitionBuilder listDefinitionBuilder, ISqlActuator sqlActuator) {
        this.listDefinitionBuilder = listDefinitionBuilder;
        this.sqlActuator = sqlActuator;
    }

    public void init() {
        if(isInit) {
            logger.warn("已经初始化过了，请勿重复调用");
            return ;
        }
        // 生产对应的 ListDefinition
        List<ListDefinition> listDefinitions = this.listDefinitionBuilder.build();
        // 加载 sql
        this.sqlActuator.load(listDefinitions);
    }
}
