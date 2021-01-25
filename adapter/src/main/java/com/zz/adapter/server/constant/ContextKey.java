package com.zz.adapter.server.constant;

/**
 * @author by zz
 * @date 2021/1/26
 */
public enum  ContextKey {

    // 业务相关枚举值
    INBOUND_MESSAGE("inboundMessage", "业务消息"),
    OUTBOUND_MESSAGE("outboundMessage", "业务流程返回");

    private String name;
    private String description;

    ContextKey(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
