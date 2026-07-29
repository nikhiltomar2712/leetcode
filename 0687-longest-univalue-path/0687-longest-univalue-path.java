class Solution {
    private int maxLen = 0;

    public int longestUnivaluePath(TreeNode root) {
        dfs(root);
        return maxLen;
    }

    // Returns the longest univalue path starting from this node going downward
    private int dfs(TreeNode node) {
        if (node == null) return 0;

        int left = dfs(node.left);
        int right = dfs(node.right);

        int leftArrow = 0, rightArrow = 0;

        if (node.left != null && node.left.val == node.val) {
            leftArrow = left + 1;
        }
        if (node.right != null && node.right.val == node.val) {
            rightArrow = right + 1;
        }

        // Path that goes through the current node
        maxLen = Math.max(maxLen, leftArrow + rightArrow);

        // Return the longer single direction for the parent
        return Math.max(leftArrow, rightArrow);
    }
}