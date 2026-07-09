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
    TreeNode* buildTree(vector<int>& inorder, vector<int>& postorder) {
            unordered_map<int, int> inorderIndex;
        for (int i = 0; i < inorder.size(); ++i) {
            inorderIndex[inorder[i]] = i;
        }
        int postIndex = postorder.size() - 1;
        return construct(postorder, inorderIndex, postIndex, 0, inorder.size() - 1);
    }
    
private:
    TreeNode* construct(vector<int>& postorder,
                        unordered_map<int, int>& inorderIndex,
                        int& postIndex,
                        int left,
                        int right) {
        if (left > right) return nullptr;
        
        int rootVal = postorder[postIndex--];
        TreeNode* root = new TreeNode(rootVal);
        int inIndex = inorderIndex[rootVal];
        
        // Build right subtree first, then left (because postorder is left-right-root;
        // traversing postorder from back gives root, then right subtree, then left).
        root->right = construct(postorder, inorderIndex, postIndex, inIndex + 1, right);
        root->left = construct(postorder, inorderIndex, postIndex, left, inIndex - 1);
        
        return root;
    }
};