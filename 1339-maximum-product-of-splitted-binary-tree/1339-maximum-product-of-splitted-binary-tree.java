class Solution {
    private long maxProduct = 0;
    private long totalSum = 0;
    
    public int maxProduct(TreeNode root) {
        final long MOD = 1_000_000_007L;
        
        // First pass: compute total sum
        totalSum = subtreeSum(root);
        
        // Second pass: compute max product
        maxProduct = 0;
        computeMaxProduct(root);
        
        return (int) (maxProduct % MOD);
    }
    
    // Post-order: returns the sum of the subtree rooted at node
    private long subtreeSum(TreeNode node) {
        if (node == null) return 0;
        long left = subtreeSum(node.left);
        long right = subtreeSum(node.right);
        return node.val + left + right;
    }
    
    // Post-order: for each node, consider the edge from its parent to it
    private long computeMaxProduct(TreeNode node) {
        if (node == null) return 0;
        
        long left = computeMaxProduct(node.left);
        long right = computeMaxProduct(node.right);
        long subtreeTotal = node.val + left + right;
        
        // The edge from parent to this node splits the tree into:
        // - subtreeTotal (this subtree)
        // - totalSum - subtreeTotal (the rest)
        // But don't apply this to the root (no parent edge)
        if (node != null) {
            // This is safe: even for the root, totalSum - totalSum = 0, product = 0
            long product = subtreeTotal * (totalSum - subtreeTotal);
            maxProduct = Math.max(maxProduct, product);
        }
        
        return subtreeTotal;
    }
}