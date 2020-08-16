package com.leetcode.editor.cn;
//给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。 
//
// 示例: 
//
// 输入: [0,1,0,3,12]
//输出: [1,3,12,0,0] 
//
// 说明: 
//
// 
// 必须在原数组上操作，不能拷贝额外的数组。 
// 尽量减少操作次数。 
// 
// Related Topics 数组 双指针 
// 👍 686 👎 0

import java.util.Arrays;

public class MoveZeroes{

    public static void main(String[] args) {
        Solution solution = new MoveZeroes().new Solution();
        solution.moveZeroes(new int[] {0, 1, 0, 2});
    }

    // 思考：
    // 数组可以有哪些操作？ 按[下标]遍历访问、赋值
    // 元操作：交换


    //leetcode submit region begin(Prohibit modification and deletion)
    // 方法1
    // 双指针： 第一次遍历的时候，只要是非0的统统都赋给nums[j];
    // 同时：j 指针也代表了非0的个数 ！
    class Solution {
        public void moveZeroes(int[] nums) {
            int j = 0;
            // 处理非0
            for (int i = 0; i < nums.length; i++) {

                if (nums[i] != 0) {
//                    nums[j] = nums[i];
//                    j++;
                     nums[j++] = nums[i];

                }
            }
            // 补0
            while (j < nums.length) {
                nums[j++] = 0;
            }
//            System.out.println(Arrays.toString(nums));
        }

        // 方法2：遇到0，交换
//        public void moveZeroes2(int[] nums) {
//            int j = 0;
//            for (int i = 0; i < nums.length; i++) {
//                if (nums[i] != 0) {
//                    int temp = nums[i];
//                    nums[i] = nums[j];
//                    nums[j] = temp;
//
//                    // 这样写有问题！ Java 函数参数是按值传递
////                    swap(nums[i], nums[j]);
//                    j++;
//
//                }
//            }
//            System.out.println(Arrays.toString(nums));
//        }
//
//        private void swap(int num, int num1) {
//            int tmp = num;
//            num = num1;
//            num1 = tmp;
//        }

        // 方法3：遇到0，删除 并 添加到数组后；  Java 不适合添加数组

//        public void moveZeroes3(int[] nums) {
//
//            int j = 0;
//            for (int i = 0; i < nums.length; i++) {
//                if (nums[i] == 0) {
//                    // 删除
//                    nums = deleteArrar(nums,0);
//                    // 添加
////                    nums = addArrar(nums,0);
//
//                }
//            }
//            System.out.println(Arrays.toString(nums));
//        }
//
//        public int[] addArrar(int[] nums, int num){
//            // 添加
//            int[] tempArray=new int[nums.length+1];
//            for (int i = 0; i < nums.length; i++) {
//                tempArray[i] = nums[i];
//            }
//            tempArray[nums.length] = num;
//            nums = tempArray;
//            return nums;
//
//        }
//
//        public int[] deleteArrar(int[] nums, int num){
//
//            int[] newArray = new int[nums.length-1];// 新数组，长度为原始数组减去 1
//
//            for(int i = 0;i < newArray.length; i++) {
//                // 判断元素是否越界
//                if (num < 0 || num >= nums.length) {
//                    throw new RuntimeException("元素越界... ");
//                }
//                //
//                if (i < num) {
//                    newArray[i] = nums[i];
//                } else {
//                    newArray[i] = nums[i+1];
//                }
//            }
//            nums = newArray;
//            return nums;
//
//        }

    }



//leetcode submit region end(Prohibit modification and deletion)

}