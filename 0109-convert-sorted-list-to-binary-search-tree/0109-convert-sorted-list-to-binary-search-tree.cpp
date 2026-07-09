class Solution {
public:
    TreeNode* sortedListToBST(ListNode* head) {
        // Count the number of nodes
        int size = 0;
        ListNode* curr = head;
        while (curr) {
            ++size;
            curr = curr->next;
        }
        // Build tree using in-order simulation
        return build(head, 0, size - 1);
    }

private:
    TreeNode* build(ListNode*& head, int left, int right) {
        if (left > right) return nullptr;

        int mid = left + (right - left) / 2;

        // 1. Build left subtree (in-order: left first)
        TreeNode* leftSub = build(head, left, mid - 1);

        // 2. Create root from current list node
        TreeNode* root = new TreeNode(head->val);
        head = head->next;  // advance the list pointer

        // 3. Build right subtree
        root->left = leftSub;
        root->right = build(head, mid + 1, right);

        return root;
    }
};