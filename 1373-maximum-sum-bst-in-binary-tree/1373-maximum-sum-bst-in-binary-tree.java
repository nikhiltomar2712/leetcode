/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    private int ans = 0;
    private static final int INF = 1 << 30;   // a large enough value

    public int maxSumBST(TreeNode root) {
        dfs(root);
        return ans;
    }

    // returns [isBST, min, max, sum]
    private int[] dfs(TreeNode node) {
        if (node == null) {
            return new int[]{1, INF, -INF, 0};
        }

        int[] left  = dfs(node.left);
        int[] right = dfs(node.right);

        int val = node.val;

        // Check whether current subtree is a valid BST
        if (left[0] == 1 && right[0] == 1 && left[2] < val && val < right[1]) {
            int sum = val + left[3] + right[3];
            ans = Math.max(ans, sum);
            return new int[]{
                1,
                Math.min(left[1], val),
                Math.max(right[2], val),
                sum
            };
        }

        // Not a BST
        return new int[]{0, 0, 0, 0};
    }
}