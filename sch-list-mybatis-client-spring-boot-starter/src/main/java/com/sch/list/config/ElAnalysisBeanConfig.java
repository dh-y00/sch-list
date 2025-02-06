package com.sch.list.config;

import com.sch.list.el.IElAnalysis;
import com.sch.list.el.ValElAnalysis;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ElAnalysisBeanConfig {

    @Bean
    public IElAnalysis valElAnalysis()
    {
        return new ValElAnalysis();
    }

}
