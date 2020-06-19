package com.demo2020.cods.leetCode.offer;

import java.util.Stack;

/**
 * 压栈序列 和 出栈序列
 * 假设 入栈数字都不相等
 * pushed 是 popped 的排列。
 * @author xw
 * @date 2020/6/19 11:51
 */
public class Solution31 {
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        Stack<Integer> stack = new Stack();
        int j = 0;
        for(int el:pushed){
            stack.push(el);
            while(j < popped.length && !stack.isEmpty() && stack.peek() == popped[j]){
                stack.pop();
                j++;
            }
        }
        return j == popped.length;
    }
}
