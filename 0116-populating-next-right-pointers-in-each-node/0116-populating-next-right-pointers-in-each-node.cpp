class Solution {
public:
    Node* connect(Node* root) {
        if (!root) return root;

        // 'leftmost' starts at root and moves to the leftmost node of each level
        Node* leftmost = root;
        while (leftmost->left) {                     // while not on leaf level
            Node* curr = leftmost;
            while (curr) {
                curr->left->next = curr->right;      // connect same parent's children
                if (curr->next) {                    // connect across different parents
                    curr->right->next = curr->next->left;
                }
                curr = curr->next;                   // move to the right sibling
            }
            leftmost = leftmost->left;               // go to next level
        }
        return root;
    }
};