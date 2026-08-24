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
    private int index = 0;
    private boolean possible = true;
    private int[] voyage;
    private List<Integer> flips = new ArrayList<>();

    public List<Integer> flipMatchVoyage(TreeNode root, int[] voyage) {
        this.voyage = voyage;
        dfs(root);
        return possible ? flips : List.of(-1);
    }

    private void dfs(TreeNode node) {
        if (node == null || !possible) return;

        // Current node must match the expected value in voyage
        if (node.val != voyage[index]) {
            possible = false;
            return;
        }
        index++;

        // Decide whether we need to flip
        if (node.left != null && node.left.val != voyage[index]) {
            // Left child does not match → we must flip
            flips.add(node.val);
            dfs(node.right);   // visit right first
            dfs(node.left);
        } else {
            // No flip needed
            dfs(node.left);
            dfs(node.right);
        }
    }
}