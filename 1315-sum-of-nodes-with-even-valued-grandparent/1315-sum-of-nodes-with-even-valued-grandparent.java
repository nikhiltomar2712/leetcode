class Solution {
    public int sumEvenGrandparent(TreeNode root) {
        return dfs(root, null, null);
    }
    
    private int dfs(TreeNode node, TreeNode parent, TreeNode grandparent) {
        if (node == null) return 0;
        
        int sum = 0;
        // Check if grandparent exists and is even-valued
        if (grandparent != null && grandparent.val % 2 == 0) {
            sum += node.val;
        }
        
        // Recurse: current becomes parent, parent becomes grandparent
        sum += dfs(node.left, node, parent);
        sum += dfs(node.right, node, parent);
        
        return sum;
    }
}