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
    private int maxLen = 0;
    
    public int longestZigZag(TreeNode root) {
        dfs(root, true, 0);   // start going left
        dfs(root, false, 0);  // start going right
        return maxLen;
    }
    
    /**
     * @param node     current node
     * @param isLeft   true if we are coming from the left (so next move should be right)
     * @param length   current zigzag length
     */
    private void dfs(TreeNode node, boolean isLeft, int length) {
        if (node == null) return;
        
        maxLen = Math.max(maxLen, length);
        
        if (isLeft) {
            // Came from left → next should go right
            dfs(node.right, false, length + 1);
            // Restart from left child
            dfs(node.left, true, 1);
        } else {
            // Came from right → next should go left
            dfs(node.left, true, length + 1);
            // Restart from right child
            dfs(node.right, false, 1);
        }
    }
}