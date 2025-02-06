package com.sch.list.remote;

import com.sch.list.definition.ListDefinition;

import java.util.Collections;
import java.util.List;

public class HttpRemote implements IRemote{



    @Override
    public List<ListDefinition> getListDefinition() {
        return Collections.emptyList();
    }
}
