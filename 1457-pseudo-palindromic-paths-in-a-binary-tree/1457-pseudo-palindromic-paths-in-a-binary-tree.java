class Solution {
    public int pseudoPalindromicPaths(TreeNode root) {
        return dfs(root, 0);
    }

    private int dfs(TreeNode node, int path) {
        if (node == null) return 0;

        path ^= (1 << node.val);

        if (node.left == null && node.right == null) {
            return (path & (path - 1)) == 0 ? 1 : 0;
        }

        return dfs(node.left, path) + dfs(node.right, path);
    }
}