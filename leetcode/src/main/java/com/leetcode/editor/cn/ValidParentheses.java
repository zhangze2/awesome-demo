package com.leetcode.editor.cn;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/*
* 感想：
* 1. 字符串 String 的 for 循环，原始 和 加强版
* 2. 什么时候适合引入 哈希表？
* */

public class ValidParentheses{
    public static void main(String[] args) {
        Solution solution = new ValidParentheses().new Solution();
        String s = "{}";
        System.out.println(solution.isValid(s));
    }

//leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public boolean isValid(String s) {

            Map<Character, Character> map = new HashMap<Character, Character>();
            map.put('(', ')');
            map.put('[', ']');
            map.put('{', '}');
            // 边界问题：栈 stack 为空： 此时 stack.pop() 操作会报错
            map.put('#', '#');

            if (s.length()>0 && !map.containsKey(s.charAt(0))) {
                return  false;
            }

            Stack<Character> stack = new Stack<>();
            // 初始化栈，并压一个默认字符
            stack.push('#');

            for (char c : s.toCharArray()) {

                // 如果和 map 中的 key 匹配，压栈
                if (map.containsKey(c)) {
                    stack.push(c);

                } else if (c != map.get(stack.pop())){
                    // 如果不匹配，拿当前遍历的符号 和 栈顶元素比较，不匹配，返回 false
                    return false;
                }
            }

            return stack.size() == 1;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}