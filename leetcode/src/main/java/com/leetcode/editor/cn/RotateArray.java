package com.leetcode.editor.cn;
//给定一个数组，将数组中的元素向右移动 k 个位置，其中 k 是非负数。 
//
// 示例 1: 
//
// 输入: [1,2,3,4,5,6,7] 和 k = 3
//输出: [5,6,7,1,2,3,4]
//解释:
//向右旋转 1 步: [7,1,2,3,4,5,6]
//向右旋转 2 步: [6,7,1,2,3,4,5]
//向右旋转 3 步: [5,6,7,1,2,3,4]
// 
//
// 示例 2: 
//
// 输入: [-1,-100,3,99] 和 k = 2
//输出: [3,99,-1,-100]
//解释: 
//向右旋转 1 步: [99,-1,-100,3]
//向右旋转 2 步: [3,99,-1,-100] 
//
// 说明: 
//
// 
// 尽可能想出更多的解决方案，至少有三种不同的方法可以解决这个问题。 
// 要求使用空间复杂度为 O(1) 的 原地 算法。 
// 
// Related Topics 数组 
// 👍 657 👎 0

public class RotateArray{

    public static void main(String[] args) {
        Solution solution = new RotateArray().new Solution();
        solution.rotate(new int[]{1,2,3,4,5,6,7} , 3);

        // i=0; k=3; nums[0] -> nums[0];
        // i=1; k=3; nums[k+1] -> nums[1];
        // ……
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public void rotate(int[] nums, int k) {

            // 特殊情况

//            if (nums.length == 0) {
//
//            }


            // 解法1： 暴力法
            // 旋转 k 次，每次旋转 1 个元素 => 交换 nums[j] 和 nums[lenght - 1]
//            int temp, tail;
//            for (int i = 0; i < k; i++) {
//                tail = nums[nums.length - 1];
//                for (int j = 0; j < nums.length; j++) {
//                    temp = nums[j];
//                    nums[j] = tail;
//                    tail = temp;
//                }
//            }




            // 解法2. 引入新数组;  能在原数据上这样操作吗？
            // 原本数组里下标为 i 的我们把它放到 (i+k) % 数组长度 的位置
            int[] newNums = new int[nums.length];

            for (int i = 0; i < nums.length; i++) {
                newNums[(i+k)%nums.length] = nums[i];

            }
            for (int j = 0; j < nums.length; j++) {
                nums[j] = newNums[j];
            }



        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}