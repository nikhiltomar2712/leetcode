/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    private Map<TreeNode, TreeNode> parent = new HashMap<>();
    private List<Integer> result = new ArrayList<>();

    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        // Step 1: Build parent pointers
        buildParentMap(root, null);
        
        // Step 2: DFS from target
        dfs(target, null, k);
        return result;
    }

    private void buildParentMap(TreeNode node, TreeNode p) {
        if (node == null) return;
        parent.put(node, p);
        buildParentMap(node.left, node);
        buildParentMap(node.right, node);
    }

    private void dfs(TreeNode node, TreeNode from, int dist) {
        if (node == null) return;
        
        if (dist == 0) {
            result.add(node.val);
            return;
        }
        
        // Explore left, right, and parent (avoid going back to 'from')
        if (node.left != from) {
            dfs(node.left, node, dist - 1);
        }
        if (node.right != from) {
            dfs(node.right, node, dist - 1);
        }
        TreeNode p = parent.get(node);
        if (p != from) {
            dfs(p, node, dist - 1);
        }
    }
}