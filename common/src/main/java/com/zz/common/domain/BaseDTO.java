package com.zz.common.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author by zz
 * @date 2020/8/11
 */

@Data
@NoArgsConstructor
public abstract class BaseDTO {

    protected String id;
    protected Date createDate;
    protected String createdBy;
    protected Date updateDate;

    protected Map<String, List<?>> divisibleAttr = new TreeMap<>();


    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    public static class FullTextcondition {
        String key;
        Object value;
        List<String> supportedFields;
    }


}
