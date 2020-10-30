package com.zz.common.util;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.*;

/**
 * @author by zz
 * @date 2020/8/23
 */

@Slf4j
public class ReflectionUtils {
    public static Map<String, Field> getAllFieldsMap(Class<?> clazz) {
        return getAllFields(clazz).stream().collect(Collectors.toMap(Field::getName, Function.identity()));
    }


    public static List<Field> getAllFields(Class<?> type){
        return getAllFields(new ArrayList<>(), type);
    }

    public static List<Field> getAllFields(List<Field> fields, Class<?> type) {
        fields.addAll(Arrays.asList(type.getDeclaredFields()));
        if (type.getSuperclass() != null) {
            getAllFields(fields, type.getSuperclass());
        }

        return fields;
    }
}
