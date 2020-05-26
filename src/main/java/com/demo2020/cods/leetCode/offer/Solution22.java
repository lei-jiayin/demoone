package com.demo2020.cods.leetCode.offer;

/**
 * 链表中倒数第k个节点
 * @author xw
 * @date 2020/5/25 9:40
 */
public class Solution22 {
    public ListNode getKthFromEnd(ListNode head, int k) {
        ListNode tmpNode = head;
        int length = 0;
        while(tmpNode!=null){
            length++;
            tmpNode = tmpNode.next;
        }
        int j = length - k;
        int i = 0;
        ListNode h = head;
        if(j == 0){
            return h;
        }
        while(h != null){
            i++;
            h = h.next;
            if(i == j){
                return h;
            }
        }
        return h;
    }
}