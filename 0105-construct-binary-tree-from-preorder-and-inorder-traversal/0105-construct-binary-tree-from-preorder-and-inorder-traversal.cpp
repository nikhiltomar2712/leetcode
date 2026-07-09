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
public:
    TreeNode* buildTree(vector<int>& preorder, vector<int>& inorder) {
        // Map value to its index in inorder for O(1) lookup
        unordered_map<int, int> inorderIndex;
        for (int i = 0; i < inorder.size(); ++i) {
            inorderIndex[inorder[i]] = i;
        }
        int preIndex = 0;
        return construct(preorder, inorderIndex, preIndex, 0, inorder.size() - 1);
    }
    
private:
    TreeNode* construct(vector<int>& preorder, 
                        unordered_map<int, int>& inorderIndex, 
                        int& preIndex, 
                        int left, 
                        int right) {
        if (left > right) return nullptr;
        
        int rootVal = preorder[preIndex++];
        TreeNode* root = new TreeNode(rootVal);
        
        int inIndex = inorderIndex[rootVal];
        
        // Build left subtree first, then right subtree
        root->left = construct(preorder, inorderIndex, preIndex, left, inIndex - 1);
        root->right = construct(preorder, inorderIndex, preIndex, inIndex + 1, right);
        
        return root;
    }
};