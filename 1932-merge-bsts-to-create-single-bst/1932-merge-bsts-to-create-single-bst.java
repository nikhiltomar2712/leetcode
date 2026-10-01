class Solution {
    public TreeNode canMerge(List<TreeNode> trees) {
        Map<Integer, TreeNode> roots = new HashMap<>();
        Set<Integer> leaves = new HashSet<>();
        for (TreeNode t : trees) {
            roots.put(t.val, t);
            if (t.left != null) leaves.add(t.left.val);
            if (t.right != null) leaves.add(t.right.val);
        }
        TreeNode root = null;
        for (TreeNode t : trees) {
            if (!leaves.contains(t.val)) {
                root = t;
                break;
            }
        }
        if (root == null) return null;
        roots.remove(root.val);
        if (!merge(root, roots)) return null;
        if (!roots.isEmpty()) return null;
        return isValidBST(root, Long.MIN_VALUE, Long.MAX_VALUE) ? root : null;
    }

    private boolean merge(TreeNode node, Map<Integer, TreeNode> roots) {
        if (node == null) return true;
        if (node.left != null && roots.containsKey(node.left.val)) {
            TreeNode sub = roots.remove(node.left.val);
            node.left = sub;
        }
        if (node.right != null && roots.containsKey(node.right.val)) {
            TreeNode sub = roots.remove(node.right.val);
            node.right = sub;
        }
        return merge(node.left, roots) && merge(node.right, roots);
    }

    private boolean isValidBST(TreeNode node, long min, long max) {
        if (node == null) return true;
        if (node.val <= min || node.val >= max) return false;
        return isValidBST(node.left, min, node.val) && isValidBST(node.right, node.val, max);
    }
}