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
    public TreeNode insertIntoBST(TreeNode root, int val) {
        // If root is null, we've found the insertion point
        if (root == null) {
            return new TreeNode(val);
        }
        
        // Insert into the left subtree if val is smaller
        if (val < root.val) {
            root.left = insertIntoBST(root.left, val);
        } 
        // Insert into the right subtree if val is larger
        else if (val > root.val) {
            root.right = insertIntoBST(root.right, val);
        }
        // Note: val == root.val won't happen per problem constraints
        
        return root; // return the (unchanged) root node
    }
}