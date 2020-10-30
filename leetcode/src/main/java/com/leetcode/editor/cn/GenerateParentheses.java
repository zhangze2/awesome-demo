package com.leetcode.editor.cn;
//22
//数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。 
//
// 
//
// 示例： 
//
// 输入：n = 3
//输出：[
//       "((()))",
//       "(()())",
//       "(())()",
//       "()(())",
//       "()()()"
//     ]
// 
// Related Topics 字符串 回溯算法 
// 👍 1265 👎 0

import java.util.ArrayList;
import java.util.List;

public class GenerateParentheses{

    public static void main(String[] args) {
        Solution solution = new GenerateParentheses().new Solution();
        solution.generateParenthesis(1);
        solution.generateParenthesis(2);
        solution.generateParenthesis(3);
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public List<String> generateParenthesis(int n) {

            List<String> result = new ArrayList<>();

            // 空格填坑，初始有 2n 个（n个左括号、n个右括号）
            dfs(result, n, n, "");

            return result;

        }
        // 辅助递归函数
        public void dfs(List<String> result, int left, int right, String subList) {
            if (left == 0 && right == 0) {
                result.add(subList);
                return;
            }
            
            if (left > 0) {
                dfs(result, left - 1, right, subList + "(" );
            }
            // 左括号没进来，右括号等着
            if (right > left) {
                dfs(result, left, right -1, subList + ")");
            }



        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}