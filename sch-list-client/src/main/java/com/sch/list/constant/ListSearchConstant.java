package com.sch.list.constant;

import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

public interface ListSearchConstant {

    String PAGE_NUM = "pageNum";

    String PAGE_SIZE = "pageSize";

    @Getter
    enum SearchTypeEnum {
        EQ("eq"),
        NE("ne"),
        GT("gt"),
        GE("ge"),
        LT("lt"),
        LE("le"),
        LIKE("like"),
        LIKE_LEFT("likeLeft"),
        LIKE_RIGHT("likeRight"),
        IN("in"),
        NOT_IN("notIn"),
        BETWEEN("between"),
        NOT_BETWEEN("notBetween"),
        IS_NULL("isNull"),
        IS_NOT_NULL("isNotNull");

        private String type;

        SearchTypeEnum(String desc) {
            this.type = this.name();
        }

        public static SearchTypeEnum getByType(String type) {
            for (SearchTypeEnum searchTypeEnum : SearchTypeEnum.values()) {
                if (StringUtils.equals(searchTypeEnum.type, type)) {
                    return searchTypeEnum;
                }
            }
            return null;
        }
    }

}
