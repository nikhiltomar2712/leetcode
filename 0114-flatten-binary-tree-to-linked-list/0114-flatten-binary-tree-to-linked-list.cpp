class Solution {
public:
    void flatten(TreeNode* root) {
        TreeNode* curr = root;
        while (curr) {
            if (curr->left) {
                // Find the rightmost node in the left subtree
                TreeNode* rightmost = curr->left;
                while (rightmost->right) {
                    rightmost = rightmost->right;
                }
                // Connect the rightmost's right to the current node's right subtree
                rightmost->right = curr->right;
                // Move left subtree to right
                curr->right = curr->left;
                curr->left = nullptr;
            }
            // Move to the next node (which is now the right child)
            curr = curr->right;
        }
    }
};