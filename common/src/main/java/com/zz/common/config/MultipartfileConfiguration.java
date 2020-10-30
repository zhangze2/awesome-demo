package com.zz.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

/**
 * @author by zz
 * @date 2020/8/30
 */
@Configuration
public class MultipartfileConfiguration {

    public MultipartResolver multipartResolver() {
        CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver();
        commonsMultipartResolver.setDefaultEncoding("UTF-8");
        commonsMultipartResolver.setResolveLazily(true);
        commonsMultipartResolver.setMaxInMemorySize(0);
        commonsMultipartResolver.setMaxUploadSize(8);

        return commonsMultipartResolver;
    }
}
