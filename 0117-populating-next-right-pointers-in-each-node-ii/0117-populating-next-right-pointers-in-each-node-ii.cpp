class Solution {
public:
    Node* connect(Node* root) {
        if (!root) return root;

        Node* leftmost = root;  // leftmost node of current level

        while (leftmost) {
            Node* dummy = new Node(0);   // dummy head for the next level's linked list
            Node* tail = dummy;          // tail of the next level's list
            Node* curr = leftmost;       // traverse current level

            while (curr) {
                if (curr->left) {
                    tail->next = curr->left;
                    tail = tail->next;
                }
                if (curr->right) {
                    tail->next = curr->right;
                    tail = tail->next;
                }
                curr = curr->next;        // move to next node in current level
            }

            leftmost = dummy->next;       // move to first node of next level
            delete dummy;                 // clean up dummy node
        }

        return root;
    }
};