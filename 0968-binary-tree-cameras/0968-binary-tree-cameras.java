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
    private int cameras = 0;

    public int minCameraCover(TreeNode root) {
        // If the root itself is not covered, we must place one extra camera
        if (dfs(root) == 0) {
            cameras++;
        }
        return cameras;
    }

    /**
     * Returns the state of the current node:
     * 0 → needs a camera (not covered)
     * 1 → has a camera
     * 2 → is covered (by a child or itself)
     */
    private int dfs(TreeNode node) {
        if (node == null) {
            return 2;               // null nodes are considered covered
        }

        int left = dfs(node.left);
        int right = dfs(node.right);

        // If any child needs a camera → place a camera here
        if (left == 0 || right == 0) {
            cameras++;
            return 1;               // this node now has a camera
        }

        // If any child has a camera → this node is covered
        if (left == 1 || right == 1) {
            return 2;
        }

        // Both children are covered, but this node is not → it needs a camera
        return 0;
    }
}