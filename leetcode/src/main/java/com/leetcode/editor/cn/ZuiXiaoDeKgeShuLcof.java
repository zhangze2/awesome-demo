package com.leetcode.editor.cn;
//输入整数数组 arr ，找出其中最小的 k 个数。例如，输入4、5、1、6、2、7、3、8这8个数字，则最小的4个数字是1、2、3、4。 
//
// 
//
// 示例 1： 
//
// 输入：arr = [3,2,1], k = 2
//输出：[1,2] 或者 [2,1]
// 
//
// 示例 2： 
//
// 输入：arr = [0,1,2,1], k = 1
//输出：[0] 
//
// 
//
// 限制： 
//
// 
// 0 <= k <= arr.length <= 10000 
// 0 <= arr[i] <= 10000 
// 
// Related Topics 堆 分治算法 
// 👍 124 👎 0

import java.util.PriorityQueue;
import java.util.Queue;

public class ZuiXiaoDeKgeShuLcof{

    public static void main(String[] args) {
        Solution solution = new ZuiXiaoDeKgeShuLcof().new Solution();
        solution.getLeastNumbers(new int[]{}, 0);
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int[] getLeastNumbers(int[] arr, int k) {
            // 需要用到什么数据结构？

            if (arr.length == 0 || k ==0) {
                return new int[0];
            }
            Queue<Integer> heap = new PriorityQueue<>(k, (v1, v2) -> Integer.compare(v2, v1));


            for (int num : arr) {
                if (heap.size() < k) {
                    heap.add(num);
                } else if (heap.peek() > num){
                    heap.poll();
                    heap.offer(num);
                }
            }

            int[] newNum = new int[heap.size()];
            int i = 0;
            for (int num : heap) {
                newNum[i++] = num;
            }

            return newNum;

        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}