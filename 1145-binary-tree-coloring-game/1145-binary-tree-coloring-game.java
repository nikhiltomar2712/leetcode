class Solution {
    private int leftCount = 0;   // Nodes in the left subtree of the chosen node
    private int rightCount = 0;  // Nodes in the right subtree of the chosen node
    private int totalNodes = 0;

    public boolean btreeGameWinningMove(TreeNode root, int n, int x) {
        totalNodes = countNodes(root); // Get total nodes in the tree

        // Find the node with value x and count its left and right subtrees
        findNode(root, x);

        // Player 2 has three possible regions to block:
        // 1. Left subtree of x
        // 2. Right subtree of x
        // 3. The rest of the tree (parent side)
        int maxRegion = Math.max(Math.max(leftCount, rightCount),
                                 totalNodes - leftCount - rightCount - 1);

        // Player 2 wins if they can claim more than half the nodes
        return maxRegion > totalNodes / 2;
    }

    // Count total nodes in the tree
    private int countNodes(TreeNode node) {
        if (node == null) return 0;
        return 1 + countNodes(node.left) + countNodes(node.right);
    }

    // Traverse to find node x and populate leftCount and rightCount
    private int findNode(TreeNode node, int x) {
        if (node == null) return 0;

        // If this is the node we're looking for
        if (node.val == x) {
            leftCount = countNodes(node.left);
            rightCount = countNodes(node.right);
            return 1; // Signal that we found it
        }

        // Recursively search children
        int left = findNode(node.left, x);
        int right = findNode(node.right, x);

        // If found, return 1 to propagate the signal
        return left + right > 0 ? 1 : 0;
    }
}