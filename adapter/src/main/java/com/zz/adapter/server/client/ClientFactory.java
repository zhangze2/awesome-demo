package com.zz.adapter.server.client;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

/**
 * Client 抽象工厂
 * @author by zz
 *
 * @date 2021/1/26
 */

@Component
@Getter
@Setter
public class ClientFactory {
    private IClient httpClient;
    private IClient dobboClient;

    public IClient getClientByProtocol(String interfaceType) {
        if ("HTTP".equalsIgnoreCase(interfaceType) || "HTTPS".equalsIgnoreCase(interfaceType)){
            return httpClient;
        }else if ("DOBBO".equalsIgnoreCase(interfaceType)) {
            return dobboClient;
        }

        return null;
    }
}
