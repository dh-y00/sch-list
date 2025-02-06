package com.sch.list.sql.search;

import com.sch.list.constant.ListSearchConstant;
import org.apache.ibatis.scripting.xmltags.IfSqlNode;
import org.apache.ibatis.scripting.xmltags.SqlNode;
import org.apache.ibatis.scripting.xmltags.StaticTextSqlNode;
import org.apache.ibatis.session.Configuration;

import java.text.MessageFormat;
import java.util.List;

/**
 * 相等搜索
 */
public class LikeMybatisSource implements IMybatisSqlSource {

    @Override
    public SqlNode doDeal(Configuration configuration, List<String> fieldName, String paramName) {
        StringBuffer sql = new StringBuffer();
        sql.append(" AND (");
        int len = fieldName.size();
        for (int i = 0; i < len; i++) {
            String field = fieldName.get(i);
            sql.append(MessageFormat.format(" {0}  like concat('%', #{1}, '%') \n", field, paramName));
            if(i != len - 1) {
                sql.append(" or ");
            }
        }
        sql.append(" ) ");
        SqlNode staticTextSqlNode = new StaticTextSqlNode(sql.toString());
        return new IfSqlNode(staticTextSqlNode, String.format("%s != null and %s != ''", paramName, paramName));
    }

    @Override
    public ListSearchConstant.SearchTypeEnum type() {
        return ListSearchConstant.SearchTypeEnum.LIKE;
    }
}
