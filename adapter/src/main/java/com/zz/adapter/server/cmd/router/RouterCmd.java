package com.zz.adapter.server.cmd.router;


import com.zz.adapter.server.cmd.base.BaseCmd;
import com.zz.adapter.server.constant.Constant;
import com.zz.adapter.server.domain.RounterDomain;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.chain.Context;
import org.apache.logging.log4j.ThreadContext;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author by zz
 * @date 2021/1/26
 */
@Component
@Slf4j
public class RouterCmd extends BaseCmd {
    @Override
    public boolean execute(Context context) throws  Exception {
        JSONObject message = (JSONObject) context.get(Constant.MSG_TYPE);
        log.info("Processing the message request:\n {}", message);

        String company = message.getString(Constant.MSG_COMPANY);
        String msgType = message.getString(Constant.MSG_TYPE);
        Object data = message.get("data");

        ThreadContext.put(Constant.MSG_TYPE, msgType);
        ThreadContext.put(Constant.MSG_COMPANY, company);



        // 查询数据库 Router 和 step 配置：  RouterDomains
        context.put("originRounterData", data);
        context.put("lastRounterResult", data);


        return false;

    }

    private boolean executeRouters(Context context, List<RounterDomain> rounterDomains) {

        RounterDomain parentRounterDomain = (RounterDomain) context.get("parentRounterDomain");
        return false;
    }




}
