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
    public TreeNode addOneRow(TreeNode root, int val, int depth) {
        if (depth == 1) {
            TreeNode newRoot = new TreeNode(val);
            newRoot.left = root;
            return newRoot;
        }
        insertAtDepth(root, val, depth, 1);
        return root;
    }
    
    private void insertAtDepth(TreeNode node, int val, int depth, int currDepth) {
        if (node == null) {
            return;
        }
        
        if (currDepth == depth - 1) {
            // Insert new row at this level
            TreeNode leftNew = new TreeNode(val);
            leftNew.left = node.left;
            node.left = leftNew;
            
            TreeNode rightNew = new TreeNode(val);
            rightNew.right = node.right;
            node.right = rightNew;
            return;
        }
        
        insertAtDepth(node.left, val, depth, currDepth + 1);
        insertAtDepth(node.right, val, depth, currDepth + 1);
    }
}