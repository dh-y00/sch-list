package com.sch.list.controller;

import com.sch.list.constant.ListSearchConstant;
import com.sch.list.constant.UiBusinessExceptionCodeEnum;
import com.sch.list.exception.UiBusinessException;
import com.sch.list.pojo.ListDataResp;
import com.sch.list.pojo.ListPageConfig;
import com.sch.list.service.PageConfigService;
import com.sch.list.service.PageDataService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/ui/list")
public class UiListController {

    @Autowired
    private PageConfigService pageConfigService;

    @Autowired
    private PageDataService pageDataService;

    @GetMapping("/config")
    public ListPageConfig getListPageConfig(@RequestParam String pageId) {
        return pageConfigService.getListPageConfig(pageId);
    }

    /**
     * 查询列表数据
     * pageNum
     * pageSize
     * pageId
     * 以上三个参数是必备参数
     * pageNum
     * pageSize
     * 如果为空的话 默认给 1, 20
     * @param params 所对应的参数
     * @return
     */
    @GetMapping
    public ListDataResp list(@RequestParam Map<String, Object> params, HttpServletRequest request) {
        if(StringUtils.isBlank(request.getParameter("pageId"))) {
            throw new UiBusinessException(UiBusinessExceptionCodeEnum.ERR_UI10001, "页面编码不可以为空");
        }
        Object pageNum = params.get(ListSearchConstant.PAGE_NUM);
        if(Objects.isNull(pageNum)) {
            params.put(ListSearchConstant.PAGE_NUM, 1);
        }
        Object pageSize = params.get(ListSearchConstant.PAGE_SIZE);
        if(Objects.isNull(pageSize)) {
            params.put(ListSearchConstant.PAGE_SIZE, 20);
        }
        return pageDataService.getListData(params);

    }

}
