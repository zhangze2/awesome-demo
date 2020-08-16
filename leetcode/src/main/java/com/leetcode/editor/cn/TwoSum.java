package com.leetcode.editor.cn;
//给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。 
//
// 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素不能使用两遍。 
//
// 
//
// 示例: 
//
// 给定 nums = [2, 7, 11, 15], target = 9
//
//因为 nums[0] + nums[1] = 2 + 7 = 9
//所以返回 [0, 1]
// 
// Related Topics 数组 哈希表 
// 👍 8839 👎 0

import java.util.HashMap;
import java.util.Map;

public class TwoSum{

    public static void main(String[] args) {
        Solution solution = new TwoSum().new Solution();
        int []a = solution.twoSum(new int[]{1,2,3}, 5);
        System.out.println(a[0]);
        System.out.println(a[1]);
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
//    class Solution {
//        public int[] twoSum(int[] nums, int target) {
//
//            for (int i=0; i<nums.length; i++) {
//                for (int j=i+1; j<nums.length; j++) {
//                    if (target - nums[j] == nums[i]) {
//                        return new int[] {i,j};
//                    }
//                }
//            }
//
//            return new int[0];
//
//        }
//    }

    /*
    * 到底用 Integer 还是 int ?
    * */

    class Solution {
        public int[] twoSum(int[] nums, int target) {

            // 引入-哈希表，存数组的（值-下标），利用在 Map 中map.containsKey(other) 查找，少一层循环（查找的时间复杂度为常数级）
            Map<Integer,Integer> map = new HashMap<>();


            for (int i = 0; i < nums.length; i++) {
                map.put(nums[i],i);
            }
            // 检查每个元素所对应的目标元素（target - nums[i]）是否存在于表中。
            // 注意，该目标元素不能是 nums[i] 本身！要把哈希表中的自己排出在外（容易遗漏）
            for (int i = 0; i < nums.length; i++) {
                int other = target - nums[i];
                if (map.containsKey(other) && map.get(other)!=i){
                    return new int[] {i, map.get(other)};
                }
            }

            return new int[0];

        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}