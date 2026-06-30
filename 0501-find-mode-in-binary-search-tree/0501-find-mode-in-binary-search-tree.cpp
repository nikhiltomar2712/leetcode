/**
 * Definition for a binary tree node.
 * struct TreeNode {
 *     int val;
 *     TreeNode *left;
 *     TreeNode *right;
 *     TreeNode() : val(0), left(nullptr), right(nullptr) {}
 *     TreeNode(int x) : val(x), left(nullptr), right(nullptr) {}
 *     TreeNode(int x, TreeNode *left, TreeNode *right) : val(x), left(left), right(right) {}
 * };
 */
class Solution {
private:
    int currentVal = 0;
    int currentCount = 0;
    int maxCount = 0;
    vector<int> modes;
    
    void inorder(TreeNode* root) {
        if (!root) return;
        
        // Traverse left subtree
        inorder(root->left);
        
        // Process current node
        if (root->val == currentVal) {
            currentCount++;
        } else {
            currentVal = root->val;
            currentCount = 1;
        }
        
        // Update modes based on current count
        if (currentCount > maxCount) {
            maxCount = currentCount;
            modes = {currentVal};  // Clear and start new modes list
        } else if (currentCount == maxCount) {
            modes.push_back(currentVal);
        }
        
        // Traverse right subtree
        inorder(root->right);
    }
    
public:
    vector<int> findMode(TreeNode* root) {
        currentVal = 0;
        currentCount = 0;
        maxCount = 0;
        modes.clear();
        
        inorder(root);
        
        return modes;
    }
};