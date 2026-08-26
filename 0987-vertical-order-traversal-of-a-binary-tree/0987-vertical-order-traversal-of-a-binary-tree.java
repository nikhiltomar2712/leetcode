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
    public List<List<Integer>> verticalTraversal(TreeNode root) {
        // Store (col, row, val) for every node
        List<int[]> nodes = new ArrayList<>();
        dfs(root, 0, 0, nodes);

        // Sort by column, then by row, then by value
        Collections.sort(nodes, (a, b) -> {
            if (a[0] != b[0]) return Integer.compare(a[0], b[0]); // column
            if (a[1] != b[1]) return Integer.compare(a[1], b[1]); // row
            return Integer.compare(a[2], b[2]);                   // value
        });

        List<List<Integer>> result = new ArrayList<>();
        int prevCol = Integer.MIN_VALUE;

        for (int[] node : nodes) {
            int col = node[0];
            int val = node[2];

            if (col != prevCol) {
                result.add(new ArrayList<>());
                prevCol = col;
            }
            result.get(result.size() - 1).add(val);
        }

        return result;
    }

    private void dfs(TreeNode node, int row, int col, List<int[]> nodes) {
        if (node == null) return;

        nodes.add(new int[]{col, row, node.val});
        dfs(node.left, row + 1, col - 1, nodes);
        dfs(node.right, row + 1, col + 1, nodes);
    }
}