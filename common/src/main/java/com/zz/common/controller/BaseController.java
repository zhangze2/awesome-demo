package com.zz.common.controller;

import com.sun.istack.NotNull;
import com.zz.common.domain.BaseDTO;
import com.zz.common.domain.vo.BaseVO;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * @author by zz
 * @date 2020/8/23
 */
public abstract class BaseController<Query, VO extends BaseVO, DTO extends BaseDTO> implements ApplicationContextAware {

    public ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(@NotNull ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }


}
