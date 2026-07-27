class Solution {
    public int findSecondMinimumValue(TreeNode root) {
        if (root == null) return -1;
        return dfs(root, root.val);
    }
    
    private int dfs(TreeNode node, int minVal) {
        if (node == null) return -1;
        
        // Found a value strictly larger than the minimum
        if (node.val > minVal) {
            return node.val;
        }
        
        // Both children must be ≥ minVal; explore both sides
        int left = dfs(node.left, minVal);
        int right = dfs(node.right, minVal);
        
        // Return the smaller of the two valid candidates
        if (left == -1) return right;
        if (right == -1) return left;
        return Math.min(left, right);
    }
}