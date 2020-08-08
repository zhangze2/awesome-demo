package com.leetcode.editor.cn;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author by zz
 * @date 2020/8/8
 */
class TwoSumTest {
    private static TwoSum.Solution solution = new TwoSum().new Solution();


    @Test
    void twoSum() {
        int []dataIndex = solution.twoSum(new int[]{2, 7, 11, 15},9);
        assertEquals(0, dataIndex[0]);
        assertEquals(1,dataIndex[1]);


    }

}