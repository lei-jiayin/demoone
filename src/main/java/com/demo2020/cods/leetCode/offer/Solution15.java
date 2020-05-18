package com.demo2020.cods.leetCode.offer;

/**
 * 二进制中1的个数
 * @author xw
 * @date 2020/5/18 9:33
 */
public class Solution15 {
    // you need to treat n as an unsigned value

    /**
     * 用时 3ms
     * @param n
     * @return
     */
    public int hammingWeight(int n) {
        String n2 = Integer.toBinaryString(n);
        char[] nc2 = n2.toCharArray();
        int size = nc2.length;
        int count = 0;
        for (int i = 0; i < size; i++){
            if ("1".equals(String.valueOf(nc2[i]))){
                count++;
            }
        }
        return count;
    }

    /**
     * 最优解 用时1ms
     * @param n
     * @return
     */
    public int hammingWeight1(int n) {
        int sum = 0;
        while(n != 0) {
            sum += n & 1;
            n = n>>>1;
        }
        return sum;
    }

    public static void main(String[] args) {
        Solution15 solution15 = new Solution15();
        int i = solution15.hammingWeight(11);
        System.out.println(i);
    }
}
