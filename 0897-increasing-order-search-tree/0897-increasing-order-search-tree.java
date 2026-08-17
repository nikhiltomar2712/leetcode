class Solution {
    // Dummy node to act as the head of the new tree
    private TreeNode dummy = new TreeNode(0);
    // Pointer to track the current node in the new tree
    private TreeNode current = dummy;
    
    public TreeNode increasingBST(TreeNode root) {
        // Perform in-order traversal
        inorderTraversal(root);
        // Return the right child of dummy, which is the new root
        return dummy.right;
    }
    
    private void inorderTraversal(TreeNode node) {
        if (node == null) {
            return;
        }
        
        // Traverse left subtree
        inorderTraversal(node.left);
        
        // Process current node: set as right child of current
        node.left = null; // Remove left child
        current.right = node;
        current = node; // Move pointer forward
        
        // Traverse right subtree
        inorderTraversal(node.right);
    }
}