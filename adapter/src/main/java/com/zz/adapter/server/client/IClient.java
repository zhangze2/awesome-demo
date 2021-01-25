package com.zz.adapter.server.client;

import org.springframework.boot.configurationprocessor.json.JSONObject;

/**
 * @author by zz
 * @date 2021/1/26
 */
public interface IClient {
    Object send(String address, JSONObject inputParameters);
}
