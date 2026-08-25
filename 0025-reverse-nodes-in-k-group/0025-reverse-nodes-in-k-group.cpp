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
    ListNode* reverseKGroup(ListNode* head, int k) {
        if (!head || k == 1) return head;
        ListNode dummy(0);
        dummy.next = head;
        ListNode* prev = &dummy;
        ListNode* cur = head;
        
        // Count total nodes
        int count = 0;
        while (cur) {
            count++;
            cur = cur->next;
        }
        
        cur = head;
        while (count >= k) {
            // Reverse k nodes
            ListNode* start = cur;
            ListNode* nextStart = cur;
            for (int i = 0; i < k; i++) {
                nextStart = nextStart->next;
            }
            // Reverse from cur to nextStart (exclusive)
            ListNode* prevNode = nullptr;
            ListNode* tail = cur;
            for (int i = 0; i < k; i++) {
                ListNode* temp = cur->next;
                cur->next = prevNode;
                prevNode = cur;
                cur = temp;
            }
            // Connect
            prev->next = prevNode;  // prevNode is new head of reversed group
            tail->next = cur;       // tail is original head, now tail of reversed group
            // Move prev to tail
            prev = tail;
            count -= k;
        }
        return dummy.next;
    }
};