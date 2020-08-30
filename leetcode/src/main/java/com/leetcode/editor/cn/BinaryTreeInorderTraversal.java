package com.leetcode.editor.cn;
//给定一个二叉树，返回它的中序 遍历。 
//
// 示例: 
//
// 输入: [1,null,2,3]
//   1
//    \
//     2
//    /
//   3
//
//输出: [1,3,2] 
//
// 进阶: 递归算法很简单，你可以通过迭代算法完成吗？ 
// Related Topics 栈 树 哈希表 
// 👍 637 👎 0

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class BinaryTreeInorderTraversal{

    public static void main(String[] args) {
        Solution solution = new BinaryTreeInorderTraversal().new Solution();
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)

    // Definition for a binary tree node.
    public class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
    }

    class Solution {
        ArrayList<Integer> list = new ArrayList<> ();
        // 方法1：递归方法
//        public List<Integer> inorderTraversal(TreeNode root) {
//            if (root == null) {
//                return list;
//            }
//
//            if (root.left != null) {
//                inorderTraversal(root.left);
//            }
//
//            list.add(root.val);
//
//            if (root.right != null) {
//                inorderTraversal(root.right);
//            }
//
//            return list;
//
//        }

        // 方法2：迭代

        public List<Integer> inorderTraversal(TreeNode root) {
            Stack<TreeNode> stack = new Stack<>();
            // 这个 || 条件怎么不遗漏？
            TreeNode curr = root;
            while (curr != null || stack.size() > 0) {
                //不断往左子树方向走，每走一次就将当前节点保存到栈中
                if (curr != null) {
                    stack.add(curr);
                    curr = curr.left;
                } else {
                    TreeNode temp = stack.pop();
                    list.add(temp.val);
                    curr = temp.right;
                }

            }

            return list;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}