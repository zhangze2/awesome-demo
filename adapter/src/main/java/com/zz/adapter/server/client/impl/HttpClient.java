package com.zz.adapter.server.client.impl;

import com.zz.adapter.server.client.IClient;
import com.zz.adapter.server.client.IFeignClient;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

/**
 * @author by zz
 * @date 2021/1/26
 */
public class HttpClient implements IClient {


    private IFeignClient feignClient;

    @Override
    public Object send(String address, JSONObject inputParameters) {

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.parseMediaType("application/json; charset=UTF-8"));
        httpHeaders.add("Accept", MediaType.APPLICATION_JSON.toString());
        httpHeaders.add("Referer", "");




        return null;
    }
}
