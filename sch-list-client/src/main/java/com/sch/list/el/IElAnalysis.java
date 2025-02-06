package com.sch.list.el;

import com.rdrk.rsf.framework.utils.ApplicationContextUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;

/**
 * EL 解析器
 */
public interface IElAnalysis {

    /**
     * 解析EL 表达式
     * @param el 表达式
     * @return 解析出来的数据
     */
    String analysis(String el);

    /**
     * EL 表达式的前缀
     * 例如
     * 如果是直接取值，那么则前缀为 val://
     * @return 前缀
     */
    String prefix();

    static String analysisEl(String el) {
        if(StringUtils.isBlank(el)) {
            return el;
        }
        Map<String, IElAnalysis> beansOfType = ApplicationContextUtils.getBeansOfType(IElAnalysis.class);

        for (IElAnalysis elAnalysis : beansOfType.values()) {
            if (el.startsWith(elAnalysis.prefix())) {
                return elAnalysis.analysis(el.replace(elAnalysis.prefix(), ""));
            }
        }
        return el;
    }
}
