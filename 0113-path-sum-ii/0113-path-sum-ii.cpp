class Solution {
public:
    vector<vector<int>> pathSum(TreeNode* root, int targetSum) {
        vector<vector<int>> result;
        vector<int> currentPath;
        dfs(root, targetSum, currentPath, result);
        return result;
    }

private:
    void dfs(TreeNode* node, int remaining, vector<int>& path, vector<vector<int>>& result) {
        if (!node) return;
        path.push_back(node->val);
        // Check if it's a leaf and the sum matches
        if (!node->left && !node->right && remaining == node->val) {
            result.push_back(path);
        }
        dfs(node->left, remaining - node->val, path, result);
        dfs(node->right, remaining - node->val, path, result);
        path.pop_back(); // backtrack
    }
};