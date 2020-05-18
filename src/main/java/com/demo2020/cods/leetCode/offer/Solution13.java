package com.demo2020.cods.leetCode.offer;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 机器人的运动范围
 * @author xw
 * @date 2020/5/14 15:33
 */
public class Solution13 {
    /**
     * 深度优先
     */
        int m, n, k;
        boolean[][] visited;
        public int movingCount(int m, int n, int k) {
            this.m = m; this.n = n; this.k = k;
            this.visited = new boolean[m][n];
            return dfs(0, 0, 0, 0);
        }
        public int dfs(int i, int j, int si, int sj) {
            if(i >= m || j >= n || k < si + sj || visited[i][j]) return 0;
            visited[i][j] = true;
            return 1 + dfs(i + 1, j, (i + 1) % 10 != 0 ? si + 1 : si - 8, sj) + dfs(i, j + 1, si, (j + 1) % 10 != 0 ? sj + 1 : sj - 8);
        }

    /**
     * 广度优先
     * @param m
     * @param n
     * @param k
     * @return
     */
    public int movingCoun1(int m, int n, int k) {
        boolean[][] visited = new boolean[m][n];
        int res = 0;
        Queue<int[]> queue= new LinkedList<int[]>();
        queue.add(new int[] { 0, 0, 0, 0 });
        while(queue.size() > 0) {
            int[] x = queue.poll();
            int i = x[0], j = x[1], si = x[2], sj = x[3];
            if(i >= m || j >= n || k < si + sj || visited[i][j]) continue;
            visited[i][j] = true;
            res ++;
            queue.add(new int[] { i + 1, j, (i + 1) % 10 != 0 ? si + 1 : si - 8, sj });
            queue.add(new int[] { i, j + 1, si, (j + 1) % 10 != 0 ? sj + 1 : sj - 8 });
        }
        return res;
    }

    //作者：jyd
    //链接：https://leetcode-cn.com/problems/ji-qi-ren-de-yun-dong-fan-wei-lcof/solution/mian-shi-ti-13-ji-qi-ren-de-yun-dong-fan-wei-dfs-b/
    //来源：力扣（LeetCode）
    //著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。


    /**
     * 单个数的数位求和 先进版
     * @param index
     * @return
     */
    int indexSum(int index){
        int sum = index%10;
        int tmp = index/10;
        while(tmp>0){
            sum+=tmp%10;
            tmp/=10;
        }
        return sum;
    }

    //作者：__sun
    //链接：https://leetcode-cn.com/problems/ji-qi-ren-de-yun-dong-fan-wei-lcof/solution/java-1ms-yin-ju-ming-yan-talk-is-cheap-show-me-the/
    //来源：力扣（LeetCode）
    //著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。

    /**
     * 求数位和
     * @return
     */
    public int shuweihe(int i, int j){
        int x1 = i % 10;
        int x2 = i / 10;
        int y1 = j % 10;
        int y2 = j / 10;
        return x1 + x2 + y1 + y2;
    }

    public static void main(String[] args) {
        Solution13 ss = new Solution13();
        int a = ss.shuweihe(99, 99);
        int indexSum = ss.indexSum(255);
        System.out.println(indexSum);
    }
}
