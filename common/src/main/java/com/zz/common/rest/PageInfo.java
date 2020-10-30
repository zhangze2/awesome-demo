package com.zz.common.rest;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author by zz
 * @date 2020/8/31
 */

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PageInfo {
    private Integer pageSize;
    private Integer currentPage;
    private Long totalRecords;
    private Integer totalpages;

//    public static PageInfo.

    public static class PageInfoBuilder {

    }
}
