package com.leetcode.editor.cn;
//50
//实现 pow(x, n) ，即计算 x 的 n 次幂函数。 
//
// 示例 1: 
//
// 输入: 2.00000, 10
//输出: 1024.00000
// 
//
// 示例 2: 
//
// 输入: 2.10000, 3
//输出: 9.26100
// 
//
// 示例 3: 
//
// 输入: 2.00000, -2
//输出: 0.25000
//解释: 2-2 = 1/22 = 1/4 = 0.25 
//
// 说明: 
//
// 
// -100.0 < x < 100.0 
// n 是 32 位有符号整数，其数值范围是 [−231, 231 − 1] 。 
// 
// Related Topics 数学 二分查找 
// 👍 478 👎 0

public class PowxN{

    public static void main(String[] args) {
        Solution solution = new PowxN().new Solution();
        // 一般特殊情况，单元测试案例可以考虑到
        solution.myPow(2, 0);

        solution.myPow(0, 1);
        solution.myPow(-1, 2);
        solution.myPow(1, 2);
        solution.myPow(2, -2);
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public double myPow(double x, int n) {

            long N = n;

            return N >= 0 ? help(x, N) : 1.0 / help(x, -N);
        }

        private double help(double x, long N) {

            if (N == 0) {
                return 1.0;
            }

            double result = help(x, N / 2);


            return N%2 == 0 ? result * result : result * result * x;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}