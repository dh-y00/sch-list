package com.sch.list.definition;

import cn.hutool.core.io.resource.ResourceUtil;
import cn.hutool.core.util.ArrayUtil;
import com.sch.list.constant.ListSearchConstant;
import com.sch.list.constant.ListTagConstant;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.*;

/**
 * 通过resource 下的XML 去初始化生成 list definition
 * 未实现，勿用
 * @author yaodonghu
 */
@Component
public class ResourceListDefinitionBuilder implements IListDefinitionBuilder{

    private Logger logger = LoggerFactory.getLogger("ui-list");

    @Override
    public List<ListDefinition> build() {
        URL resource = ResourceUtil.getResource("list");
        if (Objects.isNull(resource)) {
            return Collections.emptyList();
        }
        File file = new File(resource.getFile());
        File[] files = file.listFiles();
        if(ArrayUtil.isEmpty(files)) {
            return Collections.emptyList();
        }
        List<ListDefinition> listDefinitions = new ArrayList<>();
        for (File xmlFile : files) {
            if(xmlFile.isDirectory()) {
                continue;
            }
            try {
                DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
                DocumentBuilder db = dbf.newDocumentBuilder();
                Document parse = db.parse(xmlFile);
                List<ListDefinition> definitions = buildListDefinition(parse);
                if(CollectionUtils.isEmpty(definitions)) {
                    continue;
                }
                listDefinitions.addAll(definitions);
            } catch (ParserConfigurationException | SAXException | IOException ignored) {
                logger.error("xml file parse error", ignored);
            }
        }
        return listDefinitions;
    }

    private List<ListDefinition> buildListDefinition(Document parse) {
        NodeList list = parse.getDocumentElement().getElementsByTagName(ListTagConstant.LIST);
        int listLength = list.getLength();
        if(listLength == 0) {
            return Collections.emptyList();
        }
        List<ListDefinition> listDefinitions = new ArrayList<>(listLength);
        for (int i = 0; i < listLength; i++) {
            Node item = list.item(i);
            ListDefinition listDefinition = getListDefinition(item);
            listDefinitions.add(listDefinition);
        }
        return listDefinitions;
    }

    private ListDefinition getListDefinition(Node item) {
        ListDefinition listDefinition = new ListDefinition();
        NamedNodeMap attributes = item.getAttributes();
        int length = attributes.getLength();
        for (int j = 0; j < length; j++) {
            Node attNode = attributes.item(j);
            switch (attNode.getNodeName()) {
                case ListTagConstant.FUNC_ID:
                    listDefinition.setId(attNode.getNodeValue());
                    break;
                default:
                    break;
            }
        }
        listDefinition.setSql(item.getTextContent());
        Map<String, ListSearchConstant.SearchTypeEnum> searchFields = new HashMap<>();
//        searchFields.put("flow_id", ListSearchConstant.SearchTypeEnum.EQ);
//        listDefinition.setSearchFields(searchFields);
        return listDefinition;
    }

    private List<ListSearchDefinition> buildSearch(String pageId) {
        return null;
    }
}
