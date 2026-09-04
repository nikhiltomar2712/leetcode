class Solution {
    public List<TreeNode> delNodes(TreeNode root, int[] to_delete) {
        Set<Integer> toDelete = new HashSet<>();
        for (int val : to_delete) {
            toDelete.add(val);
        }
        
        List<TreeNode> forest = new ArrayList<>();
        root = helper(root, toDelete, forest);
        
        // If the original root is not deleted, it is also a root of a tree in the forest
        if (root != null) {
            forest.add(root);
        }
        
        return forest;
    }
    
    private TreeNode helper(TreeNode node, Set<Integer> toDelete, List<TreeNode> forest) {
        if (node == null) return null;
        
        // Post-order: process children first
        node.left  = helper(node.left,  toDelete, forest);
        node.right = helper(node.right, toDelete, forest);
        
        // If current node needs to be deleted
        if (toDelete.contains(node.val)) {
            // Its non-null children become new roots of the forest
            if (node.left  != null) forest.add(node.left);
            if (node.right != null) forest.add(node.right);
            return null;   // delete this node
        }
        
        // Node is kept
        return node;
    }
}