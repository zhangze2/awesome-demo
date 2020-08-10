package com.leetcode.editor.cn;
//给定一个 N 叉树，返回其节点值的前序遍历。 
//
// 例如，给定一个 3叉树 : 
//
// 
//
// 
//
// 
//
// 返回其前序遍历: [1,3,5,6,2,4]。 
//
// 
//
// 说明: 递归法很简单，你可以使用迭代法完成此题吗? Related Topics 树 
// 👍 91 👎 0

import java.util.ArrayList;
import java.util.List;

public class NAryTreePreorderTraversal{

    public static void main(String[] args) {
        Solution solution = new NAryTreePreorderTraversal().new Solution();
        Node root = new Node();
        root.val = 1;

        List<Node> chiledrenList = new ArrayList<>();

        Node left = new Node();
        Node right = new Node();
        left.val = 2;
        right.val = 3;
        chiledrenList.add(left);
        chiledrenList.add(right);
        root.children = chiledrenList;

        List<Integer> list = solution.preOrder(root);
        for (Integer i : list) {
            System.out.println(i);
        }


    }

    static class Node {
        public int val;
        public List<Node> children;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    };

    //leetcode submit region begin(Prohibit modification and deletion)


class Solution {

    public List<Integer> list = new ArrayList<>();
    public List<Integer> preOrder(Node root) {

        // 1. 递归,  空 List 和 null 的区别
        if (root == null) { return list; }

        list.add(root.val);
        // 忘记判空
        if (root.children != null) {
            for (Node node : root.children){
                preOrder(node);
            }
        }

        return list;
    }


}
//leetcode submit region end(Prohibit modification and deletion)

}