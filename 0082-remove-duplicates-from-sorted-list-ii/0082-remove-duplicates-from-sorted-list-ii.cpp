/**
 * Definition for singly-linked list.
 * struct ListNode {
 *     int val;
 *     ListNode *next;
 *     ListNode() : val(0), next(nullptr) {}
 *     ListNode(int x) : val(x), next(nullptr) {}
 *     ListNode(int x, ListNode *next) : val(x), next(next) {}
 * };
 */
class Solution {
public:
    ListNode* deleteDuplicates(ListNode* head) {
        if (!head) return nullptr;
        ListNode dummy(0);
        dummy.next = head;
        ListNode* prev = &dummy;
        ListNode* curr = head;
        while (curr) {
            // Check if current node has duplicates
            bool duplicate = false;
            while (curr->next && curr->val == curr->next->val) {
                duplicate = true;
                curr = curr->next;
            }
            if (duplicate) {
                // Skip all duplicates
                prev->next = curr->next;
            } else {
                // Move prev to curr (keep it)
                prev = prev->next;
            }
            curr = curr->next;
        }
        return dummy.next;
    }
};