/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

class Solution {
    public final TreeNode getTargetCopy(final TreeNode original, final TreeNode cloned, final TreeNode target) {
        if (original == null) return null;
        if (original == target) return cloned;   // found the corresponding node
        
        // Search left subtree
        TreeNode left = getTargetCopy(original.left, cloned.left, target);
        if (left != null) return left;
        
        // Search right subtree
        return getTargetCopy(original.right, cloned.right, target);
    }
}