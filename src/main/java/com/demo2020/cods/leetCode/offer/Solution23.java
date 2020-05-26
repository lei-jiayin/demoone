package com.demo2020.cods.leetCode.offer;

/**
 * 单链表翻转
 * @author xw
 * @date 2020/5/26 15:23
 */
public class Solution23 {
        public ListNode reverseList(ListNode head) {
            //申请节点，pre和 cur，pre指向null
            ListNode pre = null;
            ListNode cur = head;
            ListNode tmp = null;
            while(cur!=null) {
                //记录当前节点的下一个节点
                tmp = cur.next;
                //然后将当前节点指向pre
                cur.next = pre;
                //pre和cur节点都前进一位
                pre = cur;
                cur = tmp;
            }
            return pre;
        }
/*    }

    作者：wang_ni_ma
    链接：https://leetcode-cn.com/problems/fan-zhuan-lian-biao-lcof/solution/dong-hua-yan-shi-duo-chong-jie-fa-206-fan-zhuan-li/
    来源：力扣（LeetCode）
    著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。*/
}
