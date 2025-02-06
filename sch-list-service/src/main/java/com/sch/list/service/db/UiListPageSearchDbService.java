package com.sch.list.service.db;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.sch.list.entity.UiListPageSearch;
import com.sch.list.mapper.UiListPageSearchMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 列表页面搜索; 服务实现类
 * </p>
 *
 * @author author
 * @since 2025-02-06
 */
@Service
public class UiListPageSearchDbService extends ServiceImpl<UiListPageSearchMapper, UiListPageSearch> {
    public List<UiListPageSearch> queryByPageId(String pageId) {
        LambdaQueryWrapper<UiListPageSearch> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UiListPageSearch::getPageId, pageId);
        return list(queryWrapper);
    }
}
