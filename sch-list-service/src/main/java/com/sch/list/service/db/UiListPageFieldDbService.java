package com.sch.list.service.db;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.sch.list.entity.UiListPageField;
import com.sch.list.mapper.UiListPageFieldMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 列表页面字段信息; 服务实现类
 * </p>
 *
 * @author author
 * @since 2025-02-06
 */
@Service
public class UiListPageFieldDbService extends ServiceImpl<UiListPageFieldMapper, UiListPageField> {

    public List<UiListPageField> queryByPageId(String pageId) {
        LambdaQueryWrapper<UiListPageField> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UiListPageField::getPageId, pageId);
        return list(queryWrapper);
    }

}
