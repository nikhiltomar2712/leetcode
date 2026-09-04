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
    public TreeNode lcaDeepestLeaves(TreeNode root) {
        return postOrder(root).node;
    }
    
    private Result postOrder(TreeNode node) {
        if (node == null) {
            return new Result(null, 0);
        }
        
        Result left = postOrder(node.left);
        Result right = postOrder(node.right);
        
        if (left.depth == right.depth) {
            // Both subtrees have deepest leaves at same depth
            // Current node is the LCA of deepest leaves from both sides
            return new Result(node, left.depth + 1);
        } else if (left.depth > right.depth) {
            // Deepest leaves are only in left subtree
            return new Result(left.node, left.depth + 1);
        } else {
            // Deepest leaves are only in right subtree
            return new Result(right.node, right.depth + 1);
        }
    }
    
    private static class Result {
        TreeNode node;  // LCA of deepest leaves in this subtree
        int depth;      // Depth of deepest leaves in this subtree
        
        Result(TreeNode node, int depth) {
            this.node = node;
            this.depth = depth;
        }
    }
}