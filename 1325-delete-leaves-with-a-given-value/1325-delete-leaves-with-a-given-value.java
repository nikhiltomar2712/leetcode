class Solution {
    public TreeNode removeLeafNodes(TreeNode root, int target) {
        if (root == null) return null;
        
        // Post-order: process children first
        root.left = removeLeafNodes(root.left, target);
        root.right = removeLeafNodes(root.right, target);
        
        // If this node became a leaf and matches target, delete it
        if (root.left == null && root.right == null && root.val == target) {
            return null;
        }
        
        return root;
    }
}