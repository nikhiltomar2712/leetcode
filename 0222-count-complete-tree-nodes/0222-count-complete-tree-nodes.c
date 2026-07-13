/**
 * Definition for a binary tree node.
 * struct TreeNode {
 *     int val;
 *     struct TreeNode *left;
 *     struct TreeNode *right;
 * };
 */

// Compute height by going leftmost path
int getLeftHeight(struct TreeNode* root) {
    int height = 0;
    while (root) {
        height++;
        root = root->left;
    }
    return height;
}

// Check if node at given index exists in the last level
// index is 0-based, tree has total of 'totalNodes' nodes
bool nodeExists(int index, int height, struct TreeNode* root) {
    int left = 0, right = (1 << (height - 1)) - 1;
    struct TreeNode* node = root;
    
    for (int i = 0; i < height - 1; i++) {
        int mid = left + (right - left) / 2;
        if (index <= mid) {
            node = node->left;
            right = mid;
        } else {
            node = node->right;
            left = mid + 1;
        }
    }
    
    return node != NULL;
}

int countNodes(struct TreeNode* root) {
    if (!root) return 0;
    
    int height = getLeftHeight(root);
    if (height == 1) return 1;
    
    // Number of nodes in all levels except the last
    int upperCount = (1 << (height - 1)) - 1;  // 2^(h-1) - 1
    
    // Binary search to find how many nodes in the last level
    int left = 0;
    int right = (1 << (height - 1)) - 1;  // max nodes in last level - 1
    
    while (left < right) {
        int mid = left + (right - left + 1) / 2;
        if (nodeExists(mid, height, root)) {
            left = mid;
        } else {
            right = mid - 1;
        }
    }
    
    // left now holds the index of the last existing node (0-based)
    return upperCount + left + 1;
}