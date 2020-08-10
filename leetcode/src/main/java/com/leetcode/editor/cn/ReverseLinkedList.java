package com.leetcode.editor.cn;
//反转一个单链表。 
//
// 示例: 
//
// 输入: 1->2->3->4->5->NULL
//输出: 5->4->3->2->1->NULL 
//
// 进阶: 
//你可以迭代或递归地反转链表。你能否用两种方法解决这道题？ 
// Related Topics 链表 
// 👍 1152 👎 0

import java.util.LinkedList;

public class ReverseLinkedList{

    public static void main(String[] args) {
        Solution solution = new ReverseLinkedList().new Solution();

//        LinkedList linkedList = new LinkedList();
//
//        solution.reverseList();
    }


    // 1. 双指针迭代法， 将节点的 next 指针指向前驱(读了很多遍题目才理解这句话的意思)
    // 1.1 不知道引入两个前继指针 prev 和 当前指针 current 的作用； 看了视频之后，了解。
    //
    // 视频帮助很大：https://leetcode-cn.com/problems/reverse-linked-list/solution/tu-jie-fan-zhuan-lian-biao-c-by-vin-18/
    // 最后返回什么呢？
    // 不理解的地方: 做 test case 打印, LinkedList底层是链表Node(E).



/*    public void recur(int level, int param) {
        // terminator 递归终结条件
        if (level > MAX_LEVEL) {
            // process result
            return;
        }
        // process current logic  处理当前层逻辑
        process(level, param);
        // drill down  下控到下一层
        recur( level: level + 1, newParam);
        // restore current status 清理当前层

    }*/

    public class ListNode {
      int val;
      ListNode next;
      ListNode(int x) { val = x; }
    }
    
//leetcode submit region begin(Prohibit modification and deletion)
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
    class Solution {
        public ListNode reverseList(ListNode head) {

            if (head == null) {
                return null;
            }
            // 最初是指向 null
            ListNode pre = null;

            // cur 指向 head，然后不断遍历 cur
            ListNode current = head;

            while (current != null) {
                //记录当前节点的下一个节点
                ListNode tmp = current.next;
                // 然后将当前节点指向 pre
                current.next = pre;
                // 然后 pre 和 cur 前进一位
                pre = current;
                current = tmp;

            }

            // 都迭代完了(cur 变成 null 了),pre 就是最后一个节点
            return pre;

        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}