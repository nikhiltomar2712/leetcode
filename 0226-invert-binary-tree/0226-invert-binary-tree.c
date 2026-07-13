/**
 * Definition for a binary tree node.
 * struct TreeNode {
 *     int val;
 *     struct TreeNode *left;
 *     struct TreeNode *right;
 * };
 */
struct TreeNode* invertTree(struct TreeNode* root) {
    if (root == NULL) {
        return NULL;
    }
    
    // Recursively invert left and right subtrees
    struct TreeNode* left = invertTree(root->left);
    struct TreeNode* right = invertTree(root->right);
    
    // Swap the children
    root->left = right;
    root->right = left;
    
    return root;
}