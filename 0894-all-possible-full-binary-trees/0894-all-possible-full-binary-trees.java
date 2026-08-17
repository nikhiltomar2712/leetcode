class Solution {
    // Cache to store results for each n
    private Map<Integer, List<TreeNode>> memo = new HashMap<>();
    
    public List<TreeNode> allPossibleFBT(int n) {
        // Base case: even number of nodes cannot form a full binary tree
        if (n % 2 == 0) {
            return new ArrayList<>();
        }
        
        // If already computed, return cached result
        if (memo.containsKey(n)) {
            return memo.get(n);
        }
        
        List<TreeNode> result = new ArrayList<>();
        
        // Base case: n == 1, single node tree
        if (n == 1) {
            result.add(new TreeNode(0));
            memo.put(n, result);
            return result;
        }
        
        // For odd n, split into left and right subtrees
        // Left subtree must have odd number of nodes: 1, 3, 5, ..., n-2
        for (int leftNodes = 1; leftNodes < n; leftNodes += 2) {
            int rightNodes = n - 1 - leftNodes; // subtract root node
            
            // Get all possible left and right subtrees
            List<TreeNode> leftSubtrees = allPossibleFBT(leftNodes);
            List<TreeNode> rightSubtrees = allPossibleFBT(rightNodes);
            
            // Combine each left and right subtree with a root
            for (TreeNode left : leftSubtrees) {
                for (TreeNode right : rightSubtrees) {
                    TreeNode root = new TreeNode(0);
                    root.left = left;
                    root.right = right;
                    result.add(root);
                }
            }
        }
        
        memo.put(n, result);
        return result;
    }
}