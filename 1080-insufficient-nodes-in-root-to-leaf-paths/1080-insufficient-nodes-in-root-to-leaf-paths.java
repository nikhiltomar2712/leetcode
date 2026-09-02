class Solution {
    public TreeNode sufficientSubset(TreeNode root, int limit) {
        return dfs(root, 0, limit);
    }

    private TreeNode dfs(TreeNode node, int sum, int limit) {
        if (node == null) {
            return null;
        }

        sum += node.val;

        // Leaf node
        if (node.left == null && node.right == null) {
            return sum < limit ? null : node;
        }

        // Process children first (post-order)
        node.left = dfs(node.left, sum, limit);
        node.right = dfs(node.right, sum, limit);

        // If both children are pruned, this node is also insufficient
        if (node.left == null && node.right == null) {
            return null;
        }

        return node;
    }
}