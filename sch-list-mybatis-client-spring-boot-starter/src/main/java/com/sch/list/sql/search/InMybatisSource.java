package com.sch.list.sql.search;

import com.sch.list.constant.ListSearchConstant;
import org.apache.ibatis.scripting.xmltags.*;
import org.apache.ibatis.session.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * in 相关搜索
 */
public class InMybatisSource implements IMybatisSqlSource {
    @Override
    public SqlNode doDeal(Configuration configuration, List<String> fieldName, String paramName) {
        List<SqlNode> contents = new ArrayList<>(2);
        contents.add(new StaticTextSqlNode(String.format(" and %s in ", fieldName.get(0))));
        contents.add(new ForEachSqlNode(configuration,
                new StaticTextSqlNode("#{item}"), paramName, null, null, "item", "(", ")", ","));
        SqlNode mixedSqlNode = new MixedSqlNode(contents);
        return new IfSqlNode(mixedSqlNode, String.format("%s != null and %s.size != 0", fieldName, fieldName));
    }

    @Override
    public ListSearchConstant.SearchTypeEnum type() {
        return ListSearchConstant.SearchTypeEnum.IN;
    }
}
