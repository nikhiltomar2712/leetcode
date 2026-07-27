class Solution {
    public TreeNode trimBST(TreeNode root, int low, int high) {
        if (root == null) return null;
        
        // Current node is too small → discard left subtree, keep only right
        if (root.val < low) {
            return trimBST(root.right, low, high);
        }
        
        // Current node is too large → discard right subtree, keep only left
        if (root.val > high) {
            return trimBST(root.left, low, high);
        }
        
        // Current node is in range → trim both subtrees
        root.left = trimBST(root.left, low, high);
        root.right = trimBST(root.right, low, high);
        
        return root;
    }
}