package com.sch.list.service;

import com.sch.list.constant.ListSearchConstant;
import com.sch.list.pojo.ListDataResp;
import com.sch.list.pojo.ListExecuteDto;
import com.sch.list.sql.ISqlActuator;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 页面数据
 * @author yaodonghu
 */
@Service
public class PageDataService {

    private ISqlActuator sqlActuator;

    public PageDataService(ISqlActuator sqlActuator) {
        this.sqlActuator = sqlActuator;
    }

    /**
     * 获取到list列表data
     * @return list列表data
     */
    public ListDataResp getListData(Map<String, Object> params) {
        ListExecuteDto listExecuteDto = new ListExecuteDto();
        listExecuteDto.setPageId((String) params.get("pageId"));
        listExecuteDto.setParams(params);
        calPageNum(params);
        List<Map<String, Object>> query =
                sqlActuator.query(listExecuteDto);
        ListDataResp listDataResp = new ListDataResp();
        listDataResp.setRows(query);
        return listDataResp;
    }

    /**
     * 计算一下页码
     * @param params 传入的参数
     */
    private void calPageNum (Map<String, Object> params) {
        if (!params.containsKey(ListSearchConstant.PAGE_NUM) || !params.containsKey(ListSearchConstant.PAGE_SIZE)) {
            return ;
        }

        int pageNum = (Integer.parseInt(String.valueOf(params.get(ListSearchConstant.PAGE_NUM))) - 1) * Integer.parseInt(String.valueOf(params.get(ListSearchConstant.PAGE_SIZE)));
        params.put(ListSearchConstant.PAGE_NUM, pageNum);
        params.put(ListSearchConstant.PAGE_SIZE, Integer.parseInt(String.valueOf(params.get(ListSearchConstant.PAGE_SIZE))));
    }

}
