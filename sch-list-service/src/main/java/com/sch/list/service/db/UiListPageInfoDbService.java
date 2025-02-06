package com.sch.list.service.db;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.sch.list.entity.UiListPageInfo;
import com.sch.list.mapper.UiListPageInfoMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 列表页面信息; 服务实现类
 * </p>
 *
 * @author author
 * @since 2025-02-06
 */
@Service
public class UiListPageInfoDbService extends ServiceImpl<UiListPageInfoMapper, UiListPageInfo> {
    /**
     * 根据页面ID查询页面信息
     * @param pageId 页面ID
     * @return 页面信息
     */
    public UiListPageInfo queryByPageId(String pageId) {
        LambdaQueryWrapper<UiListPageInfo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UiListPageInfo::getPageId, pageId);
        return getOne(queryWrapper);
    }
}
