package com.demo2020.cods.leetCode.offer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * @author xw
 * @date 2020/5/22 10:30
 */
public class Solution21 {
    public int[] exchange(int[] nums) {
        List<Integer> arrayList=new ArrayList<>();
        for (int a : nums) {
            arrayList.add(a);
        }
        List<Integer> list1 = new ArrayList<>();
        Iterator<Integer> iter = arrayList.iterator();
        while (iter.hasNext()) {
            Integer item = iter.next();
            if (item % 2 == 0) {
                iter.remove();
                list1.add(item);
            }
        }
        arrayList.addAll(list1);
        Integer[] str2=arrayList.toArray(new Integer[arrayList.size()]);
        int[] arr2 = Arrays.stream(str2).mapToInt(Integer::valueOf).toArray();
        return arr2;
    }

    /**
     * 优秀解
     * @param nums
     * @return
     */
    public int[] exchange1(int[] nums) {
        int i = 0, j = nums.length - 1, tmp;
        while(i < j) {
            while(i < j && (nums[i] & 1) == 1) i++;
            while(i < j && (nums[j] & 1) == 0) j--;
            tmp = nums[i];
            nums[i] = nums[j];
            nums[j] = tmp;
        }
        return nums;
    }

/*    作者：jyd
    链接：https://leetcode-cn.com/problems/diao-zheng-shu-zu-shun-xu-shi-qi-shu-wei-yu-ou-shu-qian-mian-lcof/solution/mian-shi-ti-21-diao-zheng-shu-zu-shun-xu-shi-qi-4/
    来源：力扣（LeetCode）
    著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。*/

    public static void main(String[] args){
        int[] nums = new int[]{1,2,3,4};
        Solution21 ss = new Solution21();
        int[] exchange = ss.exchange(nums);
        for (int a:exchange) {
            System.out.println(a);
        }
    }
}
