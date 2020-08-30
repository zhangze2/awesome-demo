package com.leetcode.editor.cn.DataStructure;

import jdk.nashorn.internal.ir.WhileNode;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author by zz
 * @date 2020/8/16
 */
public class DequeDemo {




    public static void main(String[] args) {
        Deque<String> deque = new LinkedList<String>();
//        deque.push("a");
//        deque.push("b");
//        deque.push("c");

//        deque.addFirst("a");
//        deque.addFirst("b");
//        deque.addFirst("c");

        deque.addLast("c");
        deque.addLast("b");
        deque.addLast("a");

        System.out.println("deque: " + deque);

        String str = deque.peek();

        System.out.println("deque.peek():" + str);
        System.out.println("deque:" + deque);

        while (!deque.isEmpty()) {
            System.out.println("deque.pop(): " + deque.pop());
        }

        System.out.println("deque: " + deque);




    }

}
