package com.sch.list.definition;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 通过远程的方式去 list definition
 * @author yaodonghu
 */
public class RemoteListDefinitionBuilder implements IListDefinitionBuilder{

    private Logger logger = LoggerFactory.getLogger("ui-list");

    @Override
    public List<ListDefinition> build() {

        return null;
    }

}
