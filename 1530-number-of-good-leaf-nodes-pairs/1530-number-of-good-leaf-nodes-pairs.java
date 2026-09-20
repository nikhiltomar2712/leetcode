class Solution {
    private int count = 0;
    private int distance;

    public int countPairs(TreeNode root, int distance) {
        this.distance = distance;
        this.count = 0;
        dfs(root);
        return count;
    }

    // Returns list of distances from this node to all leaf nodes in its subtree
    private List<Integer> dfs(TreeNode node) {
        List<Integer> result = new ArrayList<>();
        if (node == null) return result;

        // Leaf node: distance to itself is 0
        if (node.left == null && node.right == null) {
            result.add(0);
            return result;
        }

        List<Integer> left = dfs(node.left);
        List<Integer> right = dfs(node.right);

        // Count good pairs across left and right subtrees
        for (int l : left) {
            for (int r : right) {
                if (l + r + 2 <= distance) {
                    count++;
                }
            }
        }

        // Return distances incremented by 1 (edge to parent)
        for (int l : left) {
            if (l + 1 < distance) result.add(l + 1);
        }
        for (int r : right) {
            if (r + 1 < distance) result.add(r + 1);
        }
        return result;
    }
}