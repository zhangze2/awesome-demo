package com.zz.common.repo;

import com.zz.common.domain.dao.BaseDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author by zz
 * @date 2020/8/30
 */
public interface CommonRepository<T extends BaseDAO> extends JpaRepository<T, String>, JpaSpecificationExecutor {

}
