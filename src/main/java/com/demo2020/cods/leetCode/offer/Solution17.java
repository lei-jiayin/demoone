package com.demo2020.cods.leetCode.offer;

/**
 * 打印从1到最大的n位十进制数
 * 输入 n = 1;
 * [1,2,3,4,5,6,7,8,9]
 * @author xw
 * @date 2020/5/20 14:13
 */
public class Solution17 {
    public int[] printNumbers(int n) {
        int max = (int) Math.pow(10, n);
        System.out.println(max);
        int[] a= new int[max - 1];
        for(int i = 1; i < max; i++){
            a[i - 1] = i;
            System.out.println(i);
        }
        return a;
    }

    public static void main(String[] args) {
        Solution17 solution17 = new Solution17();
        solution17.printNumbers(3);
    }
}
