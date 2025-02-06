package com.sch.list.sql.search;

import com.sch.list.constant.ListSearchConstant;
import com.sch.list.definition.ListDefinition;
import com.sch.list.definition.ListSearchDefinition;
import org.apache.commons.collections.CollectionUtils;
import org.apache.ibatis.builder.StaticSqlSource;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.scripting.xmltags.DynamicSqlSource;
import org.apache.ibatis.scripting.xmltags.MixedSqlNode;
import org.apache.ibatis.scripting.xmltags.SqlNode;
import org.apache.ibatis.scripting.xmltags.StaticTextSqlNode;
import org.apache.ibatis.session.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * sql 拼接
 */
public interface IMybatisSqlSource {

    Logger LOG = LoggerFactory.getLogger(IMybatisSqlSource.class);

    Map<ListSearchConstant.SearchTypeEnum, IMybatisSqlSource> SEARCH_MAP = new HashMap<>();

    SqlNode doDeal(Configuration configuration, List<String> fieldName, String paramName);

    ListSearchConstant.SearchTypeEnum type();

    static SqlSource deal(Configuration configuration, ListDefinition listDefinition) {
        List<ListSearchDefinition> searchFields = listDefinition.getSearchFields();
        if(CollectionUtils.isEmpty(searchFields)) {
            return new StaticSqlSource(configuration, listDefinition.getSql());
        }
        List<SqlNode> contents = new ArrayList<>(searchFields.size() + 1);
        SqlNode sqlNode = new StaticTextSqlNode("select * from ( \n" + listDefinition.getSql() + "\n ) trrrrr where 1=1");
        contents.add(sqlNode);
        for (ListSearchDefinition searchField : searchFields) {
            IMybatisSqlSource iMybatisSqlSource = SEARCH_MAP.get(searchField.getType());
            if(Objects.isNull(iMybatisSqlSource)) {
                LOG.warn("{}类型处理器不存在, 当前{}拼接搜索项忽略！", searchField.getType().getType(), searchField.getParamCode());
                continue;
            }
            contents.add(iMybatisSqlSource.doDeal(configuration, searchField.getFields(), searchField.getParamCode()));
        }
        if(listDefinition.getNeedLimit()) {
            contents.add(new StaticTextSqlNode(" limit #{pageNum}, #{pageSize}"));
        }

        SqlNode mixedSqlNode = new MixedSqlNode(contents);
        return new DynamicSqlSource(configuration, mixedSqlNode);
    }
}
