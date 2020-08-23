package com.leetcode.editor.cn;
//给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有满足条件且不重复
//的三元组。 
//
// 注意：答案中不可以包含重复的三元组。 
//
// 
//
// 示例： 
//
// 给定数组 nums = [-1, 0, 1, 2, -1, -4]，
//
//满足要求的三元组集合为：
//[
//  [-1, 0, 1],
//  [-1, -1, 2]
//]
// 
// Related Topics 数组 双指针 
// 👍 2480 👎 0

import java.util.*;

public class ThreeSum{

    public static void main(String[] args) {
        Solution solution = new ThreeSum().new Solution();
        solution.threeSum(new int[]{-1, 0, 1, 2, -1, -4});
        solution.threeSum(new int[]{0, 0, 0});
        solution.threeSum(new int[]{1, 3, 0});


    }
    // 官方返回类型为什么是 List<List<Integer>> ，不是 数组？ 题目没理解，是返回所有符合要求的结果…集！
    // 数组 和 List 之间的转换用 Arrays.asList();
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public List<List<Integer>> threeSum(int[] nums) {
            // 特殊情况
            if (nums == null || nums.length <= 2) {
                return Collections.emptyList();
            }

            // 1. 暴力三重循环

            // 排序容易遗漏 []
            Arrays.sort(nums);

            // 准备Set<> 装每次遍历找出的结果,去重
            Set<List<Integer>> result = new LinkedHashSet<>();

//            for (int i = 0; i < nums.length; i++) {
//                for (int j = i + 1; j < nums.length; j++) {
//                    for (int k = j + 1; k < nums.length; k++) {
//                        if (nums[i] + nums[j] == -nums[k]) {
//                            List<Integer> value = Arrays.asList(nums[i], nums[j], nums[k]);
//
//                            result.add(value);
//
//                        }
//                    }
//                }
//            }

            for (int i = 0; i < nums.length - 2; i++) {
                Map<Integer, Integer> map = new HashMap<>(nums.length - 1);
                for (int j = i + 1; j < nums.length; j++) {

                    int target = - (nums[i] + nums[j]);
                    if (map.containsKey(target) && map.get(target) != j) {
                        List<Integer> value = Arrays.asList(nums[i], nums[j], target);
                        value.sort(Comparator.naturalOrder());
                        result.add(value);
                    } else {
                        // map 的存储为什么放这一层？  放错过几次
                        map.put(nums[j], j);
                    }
                }
            }


            return new ArrayList<>(result);

        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}