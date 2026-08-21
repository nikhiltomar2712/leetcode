class Solution {
    public int rangeSumBST(TreeNode root, int low, int high) {
        if (root == null) return 0;
        
        // If current value is less than low, only right subtree can contribute
        if (root.val < low) {
            return rangeSumBST(root.right, low, high);
        }
        
        // If current value is greater than high, only left subtree can contribute
        if (root.val > high) {
            return rangeSumBST(root.left, low, high);
        }
        
        // Current node is in range → include it and explore both sides
        return root.val 
             + rangeSumBST(root.left, low, high) 
             + rangeSumBST(root.right, low, high);
    }
}