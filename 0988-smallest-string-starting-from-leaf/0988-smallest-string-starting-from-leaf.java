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
    private String ans = null;

    public String smallestFromLeaf(TreeNode root) {
        dfs(root, new StringBuilder());
        return ans;
    }

    private void dfs(TreeNode node, StringBuilder path) {
        if (node == null) return;

        // Append current character (build path from root to leaf)
        path.append((char) ('a' + node.val));

        // If it's a leaf, reverse the path (leaf → root) and compare
        if (node.left == null && node.right == null) {
            String candidate = path.reverse().toString();
            path.reverse(); // restore original path

            if (ans == null || candidate.compareTo(ans) < 0) {
                ans = candidate;
            }
        }

        // Continue DFS
        dfs(node.left, path);
        dfs(node.right, path);

        // Backtrack
        path.deleteCharAt(path.length() - 1);
    }
}