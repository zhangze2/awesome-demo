package com.leetcode.editor.cn;
//给你 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 (i, 
//ai) 和 (i, 0)。找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。 
//
// 说明：你不能倾斜容器，且 n 的值至少为 2。 
//
// 
//
// 
//
// 图中垂直线代表输入数组 [1,8,6,2,5,4,8,3,7]。在此情况下，容器能够容纳水（表示为蓝色部分）的最大值为 49。 
//
// 
//
// 示例： 
//
// 输入：[1,8,6,2,5,4,8,3,7]
//输出：49 
// Related Topics 数组 双指针 
// 👍 1718 👎 0

public class ContainerWithMostWater{

    public static void main(String[] args) {
        Solution solution = new ContainerWithMostWater().new Solution();
        int[] height = {1,8,6,2,5,4,8,3,7};
        System.out.println(solution.maxArea(height));
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int maxArea(int[] height) {
            // 全局变量
            int maxNum = 0;
            // 最开始公式都搞错了
            // 思考 ：两个指针什么时候停？i++ 和++i 区别
            // 如果 left < right  && num[left] < num[right], 容量为 （right-left）* num[right]
            int right = height.length - 1;
            // 1. for 循环的写法
//            for (int left = 0; left < right; left++) {
//                // 最大面积的公式
//                if (height[left] < height[right]) {
//                    maxNum = max(maxNum , (right - left) * height[left]);
//                } else {
//                    maxNum = max(maxNum, (right - left) * height[right]);
//                    right--;
//                }
//
//            }

            // 2. while 循环写法
            int left = 0;
            while (left < right) {
                if (height[left] < height[right]) {
                    maxNum = max(maxNum, (right - left) * height[left]);
                    left++;
                } else {
                    maxNum = max(maxNum, (right - left) * height[right]);
                    right--;
                }
            }

            return maxNum;
        }

        public int max(int a, int b){
            if (a < b) {
                return b;
            }
            return a;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}