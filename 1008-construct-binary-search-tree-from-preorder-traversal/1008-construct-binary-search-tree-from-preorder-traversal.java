class Solution {
    public TreeNode bstFromPreorder(int[] preorder) {
        if (preorder == null || preorder.length == 0) return null;
        
        TreeNode root = new TreeNode(preorder[0]);
        Deque<TreeNode> stack = new ArrayDeque<>();
        stack.push(root);
        
        for (int i = 1; i < preorder.length; i++) {
            TreeNode node = new TreeNode(preorder[i]);
            TreeNode parent = stack.peek();
            
            // Pop until we find the correct parent
            while (!stack.isEmpty() && stack.peek().val < node.val) {
                parent = stack.pop();
            }
            
            if (parent.val > node.val) {
                parent.left = node;   // goes to left
            } else {
                parent.right = node;  // goes to right
            }
            
            stack.push(node);
        }
        
        return root;
    }
}