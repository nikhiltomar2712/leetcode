class Solution {
public:
    bool hasPathSum(TreeNode* root, int targetSum) {
        if (!root) return false;
        // Leaf check
        if (!root->left && !root->right) return root->val == targetSum;
        // Recursively check left and right subtrees with updated sum
        return hasPathSum(root->left, targetSum - root->val) ||
               hasPathSum(root->right, targetSum - root->val);
    }
};