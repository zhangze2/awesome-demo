package com.zz.common.service;

import com.zz.common.domain.dao.BaseDAO;
import com.zz.common.repo.CommonRepository;
import com.zz.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

/**
 * @author by zz
 * @date 2020/8/30
 */
public class ServiceHelper<T extends BaseDAO> {

    @Autowired
    private ApplicationContext applicationContext;

    public CommonRepository<T> getJpaRepository(String daoClassName) {
        return (CommonRepository<T>) applicationContext.getBean(StringUtils.getRepoNameByDaoName(daoClassName));
    }

}
