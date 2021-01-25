package com.zz.adapter.server.facade;

import com.zz.adapter.server.constant.ContextKey;
import org.apache.commons.chain.Command;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.chain.impl.ContextBase;
import org.springframework.beans.BeansException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author by zz
 * @date 2021/1/26
 */

@Slf4j
@Controller
@RequestMapping({"/routerFacade", "/api/v1/routerFacade"})
public class RouterFacadeService implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @RequestMapping(path = "/execute", method = RequestMethod.POST)
    public Object execute(@RequestParam JSONObject message) {

        if (message == null) {
            log.warn("empty message received!");
            return null;
        }


        ContextBase contextBase = new ContextBase();
        contextBase.put(ContextKey.INBOUND_MESSAGE, message);

        Command command = (Command) this.applicationContext.getBean("routerCmd");
        if (command != null) {
            try {
                command.execute(contextBase);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        Object outboundMsg = contextBase.get(ContextKey.OUTBOUND_MESSAGE);

        return outboundMsg;

    }
}
