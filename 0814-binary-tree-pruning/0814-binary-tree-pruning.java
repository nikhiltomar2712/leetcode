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
    public TreeNode pruneTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        
        // Prune left and right subtrees first
        root.left = pruneTree(root.left);
        root.right = pruneTree(root.right);
        
        // If current node is 0 and has no children left, prune it
        if (root.val == 0 && root.left == null && root.right == null) {
            return null;
        }
        
        return root;
    }
}