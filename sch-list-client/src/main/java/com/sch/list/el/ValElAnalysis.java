package com.sch.list.el;


/**
 * EL 解析器
 */
public class ValElAnalysis implements IElAnalysis{

    @Override
    public String analysis(String el) {
        return el;
    }

    @Override
    public String prefix() {
        return "val://";
    }
}
