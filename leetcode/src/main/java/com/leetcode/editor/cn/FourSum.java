package com.leetcode.editor.cn;
//18
//给定一个包含 n 个整数的数组 nums 和一个目标值 target，判断 nums 中是否存在四个元素 a，b，c 和 d ，使得 a + b + c +
// d 的值与 target 相等？找出所有满足条件且不重复的四元组。 
//
// 注意： 
//
// 答案中不可以包含重复的四元组。 
//
// 示例： 
//
// 给定数组 nums = [1, 0, -1, 0, -2, 2]，和 target = 0。
//
//满足要求的四元组集合为：
//[
//  [-1,  0, 0, 1],
//  [-2, -1, 1, 2],
//  [-2,  0, 0, 2]
//]
// 
// Related Topics 数组 哈希表 双指针 
// 👍 569 👎 0

import java.util.*;
import java.util.stream.Collectors;

public class FourSum{

    public static void main(String[] args) {
        Solution solution = new FourSum().new Solution();
        solution.fourSum(new int[]{1,2,3,4,5}, 10);

    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public List<List<Integer>> fourSum(int[] nums, int target) {

            if (nums.length < 4) {
                return Collections.emptyList();
            }
            Arrays.sort(nums);

            Set<List<Integer>> result = new LinkedHashSet<>();

            for (int i = 0; i < nums.length - 1; i++) {
                for (int j = nums.length - 1; j > i; j--) {
                    Map<Integer, Integer> map = new HashMap<>(nums.length - 1);

                    for (int k = i+1; k < j; k++) {
                        int temp = target - nums[i] - nums[j] - nums[k];

                        if (map.containsKey(temp) && map.get(temp) != k) {

                            List<Integer> list = Arrays.asList(nums[i], nums[j], nums[k], temp);
                            result.add(list);
                        } else {
                            map.put(nums[k], k);
                        }

                    }

                }

            }

            return new ArrayList<>(result);
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}