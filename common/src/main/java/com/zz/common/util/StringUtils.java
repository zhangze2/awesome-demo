package com.zz.common.util;

/**
 * @author by zz
 * @date 2020/8/30
 */
public class StringUtils extends org.apache.commons.lang3.StringUtils {

    public static String unCapitalizeFirst(String str) {
        return str.replaceFirst(str.substring(0, 1), str.substring(0, 1).toLowerCase());
    }

    public static String getRepoNameByDaoName(String fullDaoName) {
        String repoBeanName = fullDaoName.substring(fullDaoName.lastIndexOf(".") + 1, fullDaoName.lastIndexOf("DAO"));

        return StringUtils.unCapitalizeFirst(repoBeanName) + "Repository";
    }
}
