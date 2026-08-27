class Solution {
    public TreeNode insertIntoMaxTree(TreeNode root, int val) {
        if (root.val < val) {
            return new TreeNode(val, root, null);
        }

        TreeNode curr = root;
        // Walk down the right spine while the next node is still larger than val
        while (curr.right != null && curr.right.val > val) {
            curr = curr.right;
        }

        // Insert the new node
        TreeNode node = new TreeNode(val, curr.right, null);
        curr.right = node;
        return root;
    }
}