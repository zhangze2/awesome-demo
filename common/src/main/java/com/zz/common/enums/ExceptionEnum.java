package com.zz.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author by zz
 * @date 2020/8/23
 */

@AllArgsConstructor
@Getter
public enum  ExceptionEnum {

    // 异常枚举值
    ILLEGAL_OPERATION("ILLEGAL_OPERATION","非法操作");

    private final String code;
    private final String message;
}
