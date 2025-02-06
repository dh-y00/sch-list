package com.sch.list.properties;

import com.sch.list.definition.IListDefinitionBuilder;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "sch.list")
@Data
public class SchListProperties {

    public Class<? extends IListDefinitionBuilder> definitionBuilderClazz;

}
