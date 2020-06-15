package com.demo2020.cods.leetCode.offer;

/**
 * 对称的二叉树
 * @author xw
 * @date 2020/6/9 9:22
 */
public class Solution28 {
    public boolean isSymmetric(TreeNode root) {
        if(root ==  null){
            return true;
        }
        return isEqu(root.left, root.right);
    }

    public boolean isEqu(TreeNode a, TreeNode b){
        if (a == null && b == null ){
            return true;
        }
        if (a == null || b == null || a.val != b.val){
            return false;
        }
        return isEqu(a.left,b.right) && isEqu(a.right, b.left);
    }
}
