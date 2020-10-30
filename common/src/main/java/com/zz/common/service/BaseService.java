package com.zz.common.service;

import java.util.Optional;

/**
 * @author by zz
 * @date 2020/8/23
 */
public interface BaseService<DTO> {

    Optional<DTO> findItemById(String id);

}
