package com.zz.adapter.server.client;

import org.springframework.boot.configurationprocessor.json.JSONObject;

/**
 * @author by zz
 * @date 2021/1/26
 */
//@FeignClient
public interface IFeignClient {

    Object sendMsg(JSONObject inputParameters);
}
