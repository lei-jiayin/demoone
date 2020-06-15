package com.demo2020.cods.leetCode.offer;

import java.util.Stack;

/**
 * 镜像二叉树
 * 输入：root = [4,2,7,1,3,6,9]
 * 输出：[4,7,2,9,6,3,1]
 * @author xw
 * @date 2020/6/8 10:15
 */
public class Solution27 {
    /**
     * 递归法
     * @param root
     * @return
     */
    public TreeNode mirrorTree(TreeNode root) {
        if(root == null) return null;
        TreeNode tmp = root.left;
        root.left = mirrorTree(root.right);
        root.right = mirrorTree(tmp);
        return root;
    }

    /**
     * 辅助栈
     * @param root
     * @return
     */
    public TreeNode mirrorTree1(TreeNode root) {
        if(root == null) return null;
        Stack<TreeNode> stack = new Stack<TreeNode>() {{ add(root); }};
        while(!stack.isEmpty()) {
            TreeNode node = stack.pop();
            if(node.left != null) stack.add(node.left);
            if(node.right != null) stack.add(node.right);
            TreeNode tmp = node.left;
            node.left = node.right;
            node.right = tmp;
        }
        return root;
    }

    /*作者：jyd
    链接：https://leetcode-cn.com/problems/er-cha-shu-de-jing-xiang-lcof/solution/mian-shi-ti-27-er-cha-shu-de-jing-xiang-di-gui-fu-/
    来源：力扣（LeetCode）
    著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。*/
}
