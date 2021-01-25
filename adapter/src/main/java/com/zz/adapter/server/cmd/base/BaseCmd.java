package com.zz.adapter.server.cmd.base;

import org.apache.commons.chain.Command;
import org.apache.commons.chain.Context;

/**
 * @author by zz
 * @date 2021/1/26
 */
public class BaseCmd implements Command {

    @Override
    public boolean execute(Context context) throws  Exception{
        return false;
    }
}
