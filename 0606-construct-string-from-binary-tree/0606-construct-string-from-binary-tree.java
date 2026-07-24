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
    public String tree2str(TreeNode root) {
        if (root == null) return "";
        
        StringBuilder sb = new StringBuilder();
        buildString(root, sb);
        return sb.toString();
    }
    
    private void buildString(TreeNode node, StringBuilder sb) {
        sb.append(node.val);
        
        if (node.left == null && node.right == null) {
            return;
        }
        
        sb.append("(");
        if (node.left != null) {
            buildString(node.left, sb);
        }
        sb.append(")");
        
        if (node.right != null) {
            sb.append("(");
            buildString(node.right, sb);
            sb.append(")");
        }
        // If no right but had left, we still added empty () for left, which is correct
    }
}