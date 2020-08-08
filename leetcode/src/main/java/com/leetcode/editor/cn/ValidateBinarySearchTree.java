package com.leetcode.editor.cn;
//给定一个二叉树，判断其是否是一个有效的二叉搜索树。 
//
// 假设一个二叉搜索树具有如下特征： 
//
// 
// 节点的左子树只包含小于当前节点的数。 
// 节点的右子树只包含大于当前节点的数。 
// 所有左子树和右子树自身必须也是二叉搜索树。 
// 
//
// 示例 1: 
//
// 输入:
//    2
//   / \
//  1   3
//输出: true
// 
//
// 示例 2: 
//
// 输入:
//    5
//   / \
//  1   4
//     / \
//    3   6
//输出: false
//解释: 输入为: [5,1,4,null,null,3,6]。
//     根节点的值为 5 ，但是其右子节点值为 4 。
// 
// Related Topics 树 深度优先搜索 
// 👍 698 👎 0

public class ValidateBinarySearchTree{

    public static void main(String[] args) {

        Solution solution = new ValidateBinarySearchTree().new Solution();
        TreeNode root = new TreeNode(6);
        TreeNode node1 = new TreeNode(9);
        TreeNode node2 = new TreeNode(8);
        root.left=node1;
        root.right=node2;

        System.out.println(solution.isValidBST(root));
    }
    
      public static class TreeNode {
          int val;
          TreeNode left;
          TreeNode right;
          TreeNode(int x) { val = x; }
      }
    
    //leetcode submit region begin(Prohibit modification and deletion)

    
    class Solution {
        public boolean isValidBST(TreeNode root) {
            // 为什么会想到增加上界 和 下限这两个参数 ？
            // 函数递归调用的入口为 valid(root, -inf, +inf)， inf 表示一个无穷大的值

            return valid(root, null, null);
        }

        public boolean valid(TreeNode root, Integer lower, Integer upper) {
            // 终结条件
            if (root == null) {
                return true;
            }
            // 处理当前层逻辑，主角参数
            // 如果 root 节点的值 val 不在 (l,r)(l,r) 的范围内说明不满足条件直接返回
            int val = root.val;
            if (lower != null && val <= lower) {
                return false;
            }
            if (upper != null && val >= upper) {
                return false;
            }

            // 下一层处理，这里具体是子左、右结点;  顺序不影响

            if (!valid(root.left, lower, val)) { return false; }
            if (!valid(root.right, val, upper)) { return false; }

            // 到底是 return true 还是 flase ?  看上面判断什么条件会简单点？
            return true;

        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}