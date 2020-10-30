package com.zz.common.service;

import com.zz.common.domain.BaseDTO;
import com.zz.common.domain.dao.BaseDAO;
import com.zz.common.repo.CommonRepository;
import com.zz.common.util.ConverterUtil;

import java.lang.reflect.ParameterizedType;
import java.util.Optional;

/**
 * @author by zz
 * @date 2020/8/30
 */
public class BaseServiceImpl<DTO extends BaseDTO, DAO extends BaseDAO> implements BaseService{

    private ServiceHelper<DAO> serviceHelper;

    @Override
    public Optional findItemById(String id) {
        CommonRepository<DAO> jpaRepository = serviceHelper.getJpaRepository(getDaoClass().getName());
        Optional<DAO> findResult = jpaRepository.findById(id);
        return findResult.map(this::covertToDto);
    }

    Class<DTO> getDtoClass() {
        return (Class<DTO>) ((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    Class<DAO> getDaoClass() {
        return (Class<DAO>) ((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    protected DTO covertToDto(DAO dao) {
        return ConverterUtil.comvert(dao, getDtoClass());
    }
}
