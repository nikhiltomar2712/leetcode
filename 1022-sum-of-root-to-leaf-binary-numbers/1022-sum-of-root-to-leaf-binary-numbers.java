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
    public int sumRootToLeaf(TreeNode root) {
        return dfs(root, 0);
    }

    private int dfs(TreeNode node, int curr) {
        if (node == null) {
            return 0;
        }

        // Append current bit
        curr = (curr << 1) | node.val;   // same as curr * 2 + node.val

        // Leaf node → contribute the number
        if (node.left == null && node.right == null) {
            return curr;
        }

        // Sum from both subtrees
        return dfs(node.left, curr) + dfs(node.right, curr);
    }
}