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
    public TreeNode searchBST(TreeNode root, int val) {
        // Base case: root is null or we found the value
        if (root == null || root.val == val) {
            return root;
        }
        
        // If val is smaller, search in left subtree
        if (val < root.val) {
            return searchBST(root.left, val);
        } 
        // If val is larger, search in right subtree
        else {
            return searchBST(root.right, val);
        }
    }
}