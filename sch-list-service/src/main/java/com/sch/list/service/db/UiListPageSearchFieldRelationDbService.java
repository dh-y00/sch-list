package com.sch.list.service.db;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.sch.list.entity.UiListPageSearchFieldRelation;
import com.sch.list.mapper.UiListPageSearchFieldRelationMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 列表页面搜索与字段关系; 服务实现类
 * </p>
 *
 * @author author
 * @since 2025-02-06
 */
@Service
public class UiListPageSearchFieldRelationDbService extends ServiceImpl<UiListPageSearchFieldRelationMapper, UiListPageSearchFieldRelation>{
    public List<UiListPageSearchFieldRelation> queryFieldCodesByPageId(String pageId) {
        LambdaQueryWrapper<UiListPageSearchFieldRelation> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UiListPageSearchFieldRelation::getPageId, pageId);
        return list(queryWrapper);
    }
}
