package com.sch.list.config;

import com.sch.list.sql.search.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * mybatis 相关sql 初始化bean
 */
@Configuration
public class MybatisSqlSourceBeanConfig {

    @Bean
    public IMybatisSqlSource eqMybatisSource() {
        return new EqMybatisSource();
    }

    @Bean
    public IMybatisSqlSource inMybatisSource() {
        return new InMybatisSource();
    }

    @Bean
    public IMybatisSqlSource likeMybatisSource() {
        return new LikeMybatisSource();
    }

    @Bean
    public IMybatisSqlSource neMybatisSource() {
        return new NEMybatisSource();
    }
}
