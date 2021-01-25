package com.zz.adapter.server.facade;

import com.zz.adapter.server.constant.Constant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author by zz
 * @date 2021/1/26
 */
@Controller
@RequestMapping({"restListener", "/api/v1/restListener"})
@Slf4j
public class RestListener {

    private RouterFacadeService routerFacadeService;

    @RequestMapping(path = "/restService", method = RequestMethod.POST)
    public Object restService(@RequestBody JSONObject params) throws JSONException {
        String msgType = params.getString(Constant.MSG_TYPE);
        String company = params.getString(Constant.MSG_COMPANY);

        log.info("params: {}", params);

        Object result = routerFacadeService.execute(params);
        sleep(msgType, company);


        return null;
    }

    private void sleep(String msgTypes, String companys) {

        if ("".equalsIgnoreCase(msgTypes) || "".equalsIgnoreCase(companys)) {
            return;
        }

        List<String> companysList = new ArrayList<>();
        companysList = Arrays.asList(companys.trim().split(","));

        List<String> msgTypeList = new ArrayList<>();
        msgTypeList = Arrays.asList(msgTypes.trim().split(","));

        if (companysList.size() == 0 || msgTypeList.size() == 0) {
            return;
        }

        try {
            Thread.sleep(6 * 10000);
        } catch (InterruptedException e) {
            log.info(e.getMessage());
        }


    }
}
