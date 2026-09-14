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
    public TreeNode balanceBST(TreeNode root) {
        List<Integer> sorted = new ArrayList<>();
        inorder(root, sorted);
        return build(sorted, 0, sorted.size() - 1);
    }
    
    private void inorder(TreeNode node, List<Integer> sorted) {
        if (node == null) return;
        inorder(node.left, sorted);
        sorted.add(node.val);
        inorder(node.right, sorted);
    }
    
    private TreeNode build(List<Integer> sorted, int left, int right) {
        if (left > right) return null;
        
        int mid = left + (right - left) / 2;
        TreeNode root = new TreeNode(sorted.get(mid));
        root.left = build(sorted, left, mid - 1);
        root.right = build(sorted, mid + 1, right);
        return root;
    }
}