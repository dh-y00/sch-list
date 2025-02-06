package com.sch.list.sql;

import com.rdrk.rsf.framework.utils.ApplicationContextUtils;
import com.sch.list.definition.ListDefinition;
import com.sch.list.pojo.ListExecuteDto;
import com.sch.list.sql.search.IMybatisSqlSource;
import org.apache.commons.collections.CollectionUtils;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ResultMap;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * MYBATIS 实现
 */
public class MybatisSqlActuator implements ISqlActuator {

    private SqlSessionFactory sqlSessionFactory;

    private Logger logger = LoggerFactory.getLogger("ui-list");

    public MybatisSqlActuator(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    /**
     * 初始化一下 search的选项
     */
    private void initSearch() {
        Map<String, IMybatisSqlSource> mybatisSqlSearch = ApplicationContextUtils.getBeansOfType(IMybatisSqlSource.class);
        for (IMybatisSqlSource value:
                mybatisSqlSearch.values()) {
            IMybatisSqlSource.SEARCH_MAP.put(value.type(), value);
        }
    }

    @Override
    public void load(List<ListDefinition> listDefinitions) {
        if(CollectionUtils.isEmpty(listDefinitions)) {
            return ;
        }
        logger.debug("load listDefinition: {}", listDefinitions.stream().map(ListDefinition::getId).collect(Collectors.toList()));
        initSearch();
        for (ListDefinition listDefinition : listDefinitions) {
            Configuration configuration = sqlSessionFactory.getConfiguration();
            // 添加MappedStatement
            MappedStatement.Builder statementBuilder = new MappedStatement.Builder(configuration, listDefinition.getId(), IMybatisSqlSource.deal(configuration, listDefinition), SqlCommandType.SELECT);
            ResultMap.Builder resultMapBuilder = new ResultMap.Builder(configuration, listDefinition.getId(), Map.class, new ArrayList<>());
            statementBuilder.resultMaps(Collections.singletonList(resultMapBuilder.build()));
            MappedStatement mappedStatement = statementBuilder.build();
            // 将新添加的MappedStatement注册到configuration中
            configuration.addMappedStatement(mappedStatement);
        }

    }

    @Override
    public List<Map<String, Object>> query(ListExecuteDto listExecuteDto) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        return sqlSession.selectList(listExecuteDto.getPageId(), listExecuteDto.getParams());
    }
}
