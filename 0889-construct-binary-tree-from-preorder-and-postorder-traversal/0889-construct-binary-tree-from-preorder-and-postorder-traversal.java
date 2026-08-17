class Solution {
    public TreeNode constructFromPrePost(int[] preorder, int[] postorder) {
        // Map to store index of each value in postorder array for quick lookup
        Map<Integer, Integer> postIndexMap = new HashMap<>();
        for (int i = 0; i < postorder.length; i++) {
            postIndexMap.put(postorder[i], i);
        }
        
        return buildTree(preorder, 0, preorder.length - 1, 
                         postorder, 0, postorder.length - 1, 
                         postIndexMap);
    }
    
    private TreeNode buildTree(int[] preorder, int preStart, int preEnd,
                               int[] postorder, int postStart, int postEnd,
                               Map<Integer, Integer> postIndexMap) {
        // Base case: no elements
        if (preStart > preEnd || postStart > postEnd) {
            return null;
        }
        
        // Root is always the first element in preorder and last in postorder
        TreeNode root = new TreeNode(preorder[preStart]);
        
        // If only one node, return it
        if (preStart == preEnd) {
            return root;
        }
        
        // The left child is the second element in preorder
        int leftChildVal = preorder[preStart + 1];
        // Find the index of left child in postorder
        int leftChildPostIndex = postIndexMap.get(leftChildVal);
        
        // Calculate the size of left subtree
        int leftSubtreeSize = leftChildPostIndex - postStart + 1;
        
        // Recursively build left subtree
        root.left = buildTree(preorder, preStart + 1, preStart + leftSubtreeSize,
                              postorder, postStart, leftChildPostIndex,
                              postIndexMap);
        
        // Recursively build right subtree
        root.right = buildTree(preorder, preStart + leftSubtreeSize + 1, preEnd,
                               postorder, leftChildPostIndex + 1, postEnd - 1,
                               postIndexMap);
        
        return root;
    }
}