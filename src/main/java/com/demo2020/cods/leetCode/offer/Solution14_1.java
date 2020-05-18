package com.demo2020.cods.leetCode.offer;

/**
 * 剪绳子
 * @author xw
 * @date 2020/5/15 9:46
 */
public class Solution14_1 {

        public int cuttingRope(int n) {
            if(n <= 3) return n - 1;
            int a = n / 3, b = n % 3;
            if(b == 0) return (int)Math.pow(3, a);
            if(b == 1) return (int)Math.pow(3, a - 1) * 4;
            return (int)Math.pow(3, a) * 2;
        }

  /*  作者：jyd
    链接：https://leetcode-cn.com/problems/jian-sheng-zi-lcof/solution/mian-shi-ti-14-i-jian-sheng-zi-tan-xin-si-xiang-by/
    来源：力扣（LeetCode）
    著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。*/

}
