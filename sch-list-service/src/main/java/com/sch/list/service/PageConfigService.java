package com.sch.list.service;

import com.alibaba.fastjson2.JSONArray;
import com.rdrk.rsf.framework.constant.SystemConstant;
import com.sch.list.constant.UiBusinessExceptionCodeEnum;
import com.sch.list.el.IElAnalysis;
import com.sch.list.entity.UiListPageBtn;
import com.sch.list.entity.UiListPageField;
import com.sch.list.entity.UiListPageInfo;
import com.sch.list.entity.UiListPageSearch;
import com.sch.list.exception.UiBusinessException;
import com.sch.list.format.SearchDropDownBoxFormat;
import com.sch.list.pojo.ListPageConfig;
import com.sch.list.pojo.PageBtnConfig;
import com.sch.list.service.db.UiListPageBtnDbService;
import com.sch.list.service.db.UiListPageFieldDbService;
import com.sch.list.service.db.UiListPageInfoDbService;
import com.sch.list.service.db.UiListPageSearchDbService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 页面配置服务
 * @author yaodonghu
 */
@Service
public class PageConfigService {

    @Autowired
    private UiListPageInfoDbService uiListPageInfoDbService;

    @Autowired
    private UiListPageSearchDbService uiListPageSearchDbService;

    @Autowired
    private UiListPageBtnDbService uiListPageBtnDbService;

    @Autowired
    private UiListPageFieldDbService uiListPageFieldDbService;

    private Logger logger = LoggerFactory.getLogger("ui-list");

    /**
     * 获取页面配置
     * @param pageId 页面ID
     * @return list 页面配置
     */
    public ListPageConfig getListPageConfig(String pageId) {
        UiListPageInfo uiListPageInfo = uiListPageInfoDbService.queryByPageId(pageId);
        if(Objects.isNull(uiListPageInfo)) {
            throw new UiBusinessException(UiBusinessExceptionCodeEnum.ERR_UI10001, "页面不存在");
        }
        ListPageConfig listPageConfig = new ListPageConfig();
        listPageConfig.setPageId(pageId);
        listPageConfig.setPageName(uiListPageInfo.getName());
        listPageConfig.setIsChoose(SystemConstant.YesAndNoEnum.isYes(uiListPageInfo.getIsChoose()));
        listPageConfig.setColConfig(SystemConstant.YesAndNoEnum.isYes(uiListPageInfo.getIsColConfig()));
        listPageConfig.setIsRefresh(SystemConstant.YesAndNoEnum.isYes(uiListPageInfo.getIsRefresh()));
        listPageConfig.setPageVersion(uiListPageInfo.getPageVersion());
        listPageConfig.setSearchFields(buildSearchFields(pageId));
        listPageConfig.setBtnConfig(buildPageBtn(pageId));
        listPageConfig.setColumn(buildColumn(pageId));
        return listPageConfig;
    }

    /**
     * 构建表格列
     * @return 列信息
     */
    private List<ListPageConfig.PageColConfig> buildColumn(String pageId) {
        List<UiListPageField> uiListPageFields = uiListPageFieldDbService.queryByPageId(pageId);
        List<ListPageConfig.PageColConfig> pageColConfigs = new ArrayList<>(uiListPageFields.size());
        for (UiListPageField uiListPageField : uiListPageFields) {
            ListPageConfig.PageColConfig pageColConfig = new ListPageConfig.PageColConfig();
            pageColConfig.setCode(uiListPageField.getCode());
            pageColConfig.setName(uiListPageField.getName());
            pageColConfig.setIsSort(SystemConstant.YesAndNoEnum.isYes(uiListPageField.getIsSort()));
            pageColConfig.setSort(uiListPageField.getSort());
            pageColConfig.setColWidth(uiListPageField.getColWidth());
            pageColConfigs.add(pageColConfig);
        }
        return pageColConfigs;
    }

    /**
     * 构建搜索框数据
     * @param pageId 页面ID
     * @return 搜索框
     */
    private List<ListPageConfig.PageSearchConfig> buildSearchFields(String pageId) {
        List<UiListPageSearch> uiListPageSearches = uiListPageSearchDbService.queryByPageId(pageId);
        List<ListPageConfig.PageSearchConfig> pageSearchConfigs = new ArrayList<>(uiListPageSearches.size());
        for (UiListPageSearch uiListPageSearch : uiListPageSearches) {
            ListPageConfig.PageSearchConfig pageSearchConfig = new ListPageConfig.PageSearchConfig();
            pageSearchConfig.setCode(uiListPageSearch.getCode());
            pageSearchConfig.setName(uiListPageSearch.getName());
            pageSearchConfig.setType(uiListPageSearch.getSearchType());
            String s = IElAnalysis.analysisEl(uiListPageSearch.getValEl());
            if(StringUtils.isNotBlank(s)) {
                try {
                    pageSearchConfig.setValue(JSONArray.parseArray(s, SearchDropDownBoxFormat.class));
                }catch (Exception e) {
                    logger.error("解析下拉框数据异常", e);
                }
            }
            pageSearchConfigs.add(pageSearchConfig);
        }
        return pageSearchConfigs;
    }

    /**
     * 构建按钮
     * @return 返回按钮信息
     */
    private List<PageBtnConfig> buildPageBtn(String pageId) {
        List<UiListPageBtn> uiListPageBtns = uiListPageBtnDbService.queryByPageId(pageId);
        List<PageBtnConfig> pageBtnConfigs = new ArrayList<>(uiListPageBtns.size());
        for (UiListPageBtn uiListPageBtn : uiListPageBtns) {
            PageBtnConfig pageBtnConfig = new PageBtnConfig();
            pageBtnConfig.setName(uiListPageBtn.getName());
            pageBtnConfig.setCode(uiListPageBtn.getCode());
            pageBtnConfig.setType(uiListPageBtn.getBtnType());
            pageBtnConfig.setRoute(uiListPageBtn.getRoute());
            pageBtnConfig.setShowMethod(uiListPageBtn.getShowMethod());
            pageBtnConfig.setSort(uiListPageBtn.getSort());
            pageBtnConfigs.add(pageBtnConfig);
        }
        return pageBtnConfigs;
    }
}
