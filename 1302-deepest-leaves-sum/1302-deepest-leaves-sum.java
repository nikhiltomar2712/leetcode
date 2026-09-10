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
    public int deepestLeavesSum(TreeNode root) {
        if (root == null) return 0;
        
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int sum = 0;
        
        while (!queue.isEmpty()) {
            int size = queue.size();
            sum = 0; // reset for each level
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                sum += node.val;
                if (node.left != null)  queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }
        }
        
        return sum; // sum of the last (deepest) level
    }
}