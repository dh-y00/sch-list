package com.sch.list.service.db;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.sch.list.entity.UiListPageBtn;
import com.sch.list.mapper.UiListPageBtnMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 列表页面操作按钮; 服务实现类
 * </p>
 *
 * @author author
 * @since 2025-02-06
 */
@Service
public class UiListPageBtnDbService extends ServiceImpl<UiListPageBtnMapper, UiListPageBtn> {
    public List<UiListPageBtn> queryByPageId(String pageId) {
        LambdaQueryWrapper<UiListPageBtn> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UiListPageBtn::getPageId, pageId);
        return list(queryWrapper);
    }
}
