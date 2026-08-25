class Solution {
    private int moves = 0;

    public int distributeCoins(TreeNode root) {
        dfs(root);
        return moves;
    }

    // Returns the excess coins in the subtree (positive = extra, negative = deficit)
    private int dfs(TreeNode node) {
        if (node == null) return 0;

        int left = dfs(node.left);
        int right = dfs(node.right);

        // Coins that must move across the edges to left and right children
        moves += Math.abs(left) + Math.abs(right);

        // Excess at this node = coins it currently has + excess from children - 1 (it needs one)
        return node.val + left + right - 1;
    }
}