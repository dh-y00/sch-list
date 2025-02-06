package com.sch.list.config;

import com.sch.list.constant.UiBusinessExceptionCodeEnum;
import com.sch.list.definition.IListDefinitionBuilder;
import com.sch.list.definition.RemoteListDefinitionBuilder;
import com.sch.list.exception.UiBusinessException;
import com.sch.list.properties.SchListProperties;
import org.springframework.beans.BeansException;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import java.util.Objects;

@Configuration
@EnableConfigurationProperties({SchListProperties.class})
@Import({BeanConfig.class, MybatisSqlSourceBeanConfig.class, ElAnalysisBeanConfig.class})
public class UiToolMybatisClientAutoConfiguration implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }


    @Bean
    public IListDefinitionBuilder listDefinitionBuilder(SchListProperties schListProperties) {
        Class<? extends IListDefinitionBuilder> definitionBuilderClazz = schListProperties.getDefinitionBuilderClazz();
        if(Objects.isNull(definitionBuilderClazz)) {
            // 如果这边取不到指定的，则默认使用远程的方式去构建
            return new RemoteListDefinitionBuilder();
        }
        IListDefinitionBuilder bean = applicationContext.getBean(definitionBuilderClazz);
        if(Objects.isNull(bean)) {
            throw new UiBusinessException(UiBusinessExceptionCodeEnum.ERR_UI10001, "未找到 bean : {}", definitionBuilderClazz.getName());
        }
        return bean;
    }


}
