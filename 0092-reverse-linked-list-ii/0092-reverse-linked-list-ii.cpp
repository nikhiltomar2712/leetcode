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
    ListNode* reverseBetween(ListNode* head, int left, int right) {
        if (left == right) return head;
        ListNode dummy(0);
        dummy.next = head;
        ListNode* prev = &dummy;
        for (int i = 1; i < left; ++i) {
            prev = prev->next;
        }
        ListNode* curr = prev->next;
        ListNode* next = nullptr;
        ListNode* tail = curr; // will become tail of reversed part
        ListNode* prevRev = nullptr;
        for (int i = left; i <= right; ++i) {
            next = curr->next;
            curr->next = prevRev;
            prevRev = curr;
            curr = next;
        }
        // Connect
        prev->next = prevRev;
        tail->next = curr;
        return dummy.next;
    }
};