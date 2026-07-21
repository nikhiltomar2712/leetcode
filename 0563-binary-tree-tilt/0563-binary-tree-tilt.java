/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    private int totalTilt = 0;

    public int findTilt(TreeNode root) {
        postOrder(root);
        return totalTilt;
    }

    private int postOrder(TreeNode node) {
        if (node == null) {
            return 0;
        }
        int leftSum = postOrder(node.left);
        int rightSum = postOrder(node.right);
        totalTilt += Math.abs(leftSum - rightSum);
        return node.val + leftSum + rightSum;
    }
}