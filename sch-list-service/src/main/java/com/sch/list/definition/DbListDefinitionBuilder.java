package com.sch.list.definition;

import cn.hutool.core.collection.CollectionUtil;
import com.sch.list.constant.ListSearchConstant;
import com.sch.list.entity.UiListDataSource;
import com.sch.list.entity.UiListPageSearch;
import com.sch.list.entity.UiListPageSearchFieldRelation;
import com.sch.list.service.db.UiListDataSourceDbService;
import com.sch.list.service.db.UiListPageSearchDbService;
import com.sch.list.service.db.UiListPageSearchFieldRelationDbService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 通过数据库去初始化生成 list definition
 * @author yaodonghu
 */
@Component
public class DbListDefinitionBuilder implements IListDefinitionBuilder{

    @Autowired
    private UiListDataSourceDbService uiListDataSourceDbService;

    @Autowired
    private UiListPageSearchDbService uiListPageSearchDbService;

    @Autowired
    private UiListPageSearchFieldRelationDbService uiListPageSearchFieldRelationDbService;

    public DbListDefinitionBuilder(UiListDataSourceDbService uiListDataSourceDbService, UiListPageSearchDbService uiListPageSearchDbService, UiListPageSearchFieldRelationDbService uiListPageSearchFieldRelationDbService) {
        this.uiListDataSourceDbService = uiListDataSourceDbService;
        this.uiListPageSearchDbService = uiListPageSearchDbService;
        this.uiListPageSearchFieldRelationDbService = uiListPageSearchFieldRelationDbService;
    }

    private Logger logger = LoggerFactory.getLogger("ui-list");

    @Override
    public List<ListDefinition> build() {
        List<UiListDataSource> list = uiListDataSourceDbService.list();
        if(CollectionUtil.isEmpty(list)) {
            return Collections.emptyList();
        }
        List<ListDefinition> listDefinitions = new ArrayList<>(list.size());
        for (UiListDataSource uiListDataSource : list) {
            ListDefinition listDefinition = new ListDefinition();
            listDefinition.setId(uiListDataSource.getPageId());
            listDefinition.setSql(uiListDataSource.getSelectSql());
            listDefinition.setSearchFields(buildSearch(uiListDataSource.getPageId()));
            listDefinitions.add(listDefinition);
        }
        return listDefinitions;
    }

    private List<ListSearchDefinition> buildSearch(String pageId) {
        List<UiListPageSearch> uiListPageSearches = uiListPageSearchDbService.queryByPageId(pageId);
        if(CollectionUtil.isEmpty(uiListPageSearches)) {
            logger.info("未找到对应的搜索配置为空，pageId:{}", pageId);
        }
        List<UiListPageSearchFieldRelation> uiListPageSearchFieldRelations = uiListPageSearchFieldRelationDbService.queryFieldCodesByPageId(pageId);
        if(CollectionUtil.isEmpty(uiListPageSearchFieldRelations)) {
            logger.warn("未找到对应的搜索字段对应关系为空，pageId:{}", pageId);
            return Collections.emptyList();
        }
        Map<String, List<UiListPageSearchFieldRelation>> relationsMap = uiListPageSearchFieldRelations.stream().collect(Collectors.groupingBy(UiListPageSearchFieldRelation::getSearchCode));
        List<ListSearchDefinition> searchFields = new ArrayList<>(uiListPageSearches.size());

        for (UiListPageSearch uiListPageSearch : uiListPageSearches) {
            ListSearchConstant.SearchTypeEnum byType = ListSearchConstant.SearchTypeEnum.getByType(uiListPageSearch.getSearchType());
            if(Objects.isNull(byType)) {
                logger.warn("未找到对应的搜索类型，pageId:{}, code:{}, searchType:{}", uiListPageSearch.getPageId(), uiListPageSearch.getCode(), uiListPageSearch.getSearchType());
                continue;
            }
            ListSearchDefinition listSearchDefinition = new ListSearchDefinition();
            List<UiListPageSearchFieldRelation> searchFieldRelations = relationsMap.get(uiListPageSearch.getCode());
            List<String> fieldCodes = searchFieldRelations.stream().map(UiListPageSearchFieldRelation::getFieldCode).collect(Collectors.toList());
            listSearchDefinition.setFields(fieldCodes);
            listSearchDefinition.setParamCode(uiListPageSearch.getCode());
            listSearchDefinition.setType(byType);
            searchFields.add(listSearchDefinition);
        }
        return searchFields;
    }
}
