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
    private int maxDiff = 0;

    public int maxAncestorDiff(TreeNode root) {
        dfs(root, root.val, root.val);
        return maxDiff;
    }

    private void dfs(TreeNode node, int min, int max) {
        if (node == null) return;

        // Update the maximum difference with current node
        maxDiff = Math.max(maxDiff, Math.max(
            Math.abs(node.val - min),
            Math.abs(node.val - max)
        ));

        // Update min and max for the path
        int newMin = Math.min(min, node.val);
        int newMax = Math.max(max, node.val);

        dfs(node.left, newMin, newMax);
        dfs(node.right, newMin, newMax);
    }
}