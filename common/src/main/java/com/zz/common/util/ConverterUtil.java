package com.zz.common.util;

import lombok.Getter;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.spi.MatchingStrategy;

/**
 * @author by zz
 * @date 2020/8/23
 */
@Getter
public class ConverterUtil {

    private static ModelMapper modelMapper = new ModelMapper();

    public static <T> T comvert(Object source, Class<T> targetType) {
        return modelMapper.map(source, targetType);
    }

    static {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    }
}
