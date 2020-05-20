package com.demo2020.cods.leetCode.offer;

/**
 * @author xw
 * @date 2020/5/20 14:52
 */
public class Solution18 {
    public ListNode deleteNode(ListNode head, int val) {
        ListNode node = head;
        if (node.val == val) {
            return head.next;
        }
        while (node.next.val != val) {
            node = node.next;
        }
        node.next = node.next.next;
        return head;
    }

/*    作者：toto-12
    链接：https://leetcode-cn.com/problems/shan-chu-lian-biao-de-jie-dian-lcof/solution/shuang-100java-by-toto-12/
    来源：力扣（LeetCode）
    著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。*/
}

class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }
}