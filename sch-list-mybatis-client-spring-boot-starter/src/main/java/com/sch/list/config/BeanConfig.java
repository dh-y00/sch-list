package com.sch.list.config;

import com.sch.list.definition.IListDefinitionBuilder;
import com.sch.list.definition.RemoteListDefinitionBuilder;
import com.sch.list.sql.ISqlActuator;
import com.sch.list.sql.MybatisSqlActuator;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 相关关键类的配置
 * 比如通过配置文件(未实现，留给后面拓展)
 * ListDefinitionBuilder 的指定
 * SqlFactory 的指定
 * @author yaodonghu
 */
@Configuration
public class BeanConfig {

    /**
     * ListDefinition 构建器
     * 主要目的 是指定 DbListDefinitionBuilder 作为默认构建器
     * @return 返回项目中实际的构建器
     */
    @Bean
    @ConditionalOnProperty(prefix = "ui.tool.definition", value = "builder-class-name", havingValue = "com.rdrk.uitool.definition.RemoteListDefinitionBuilder")
    public IListDefinitionBuilder listDefinitionBuilder() {
        return new RemoteListDefinitionBuilder();
    }

    /**
     * sql 处理工厂
     * 主要目的 是指定 MybatisSqlFactory 作为默认sql 处理工厂
     * @return 返回对应的 sql 处理工厂
     */
    @Bean
    public ISqlActuator sqlActuator(SqlSessionFactory sqlSessionFactory) {
        return new MybatisSqlActuator(sqlSessionFactory);
    }

    @Bean
    public ListInitFactory listInitFactory(IListDefinitionBuilder listDefinitionBuilder, ISqlActuator sqlActuator) {
        return new ListInitFactory(listDefinitionBuilder, sqlActuator);
    }

    @Bean
    public InitApplication initApplication(ListInitFactory listInitFactory) {
        return new InitApplication(listInitFactory);
    }
}
