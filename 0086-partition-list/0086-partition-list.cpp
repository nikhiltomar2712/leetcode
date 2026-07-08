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
    ListNode* partition(ListNode* head, int x) {
        ListNode smallDummy(0);
        ListNode largeDummy(0);
        ListNode* smallTail = &smallDummy;
        ListNode* largeTail = &largeDummy;
        
        ListNode* curr = head;
        while (curr) {
            if (curr->val < x) {
                smallTail->next = curr;
                smallTail = smallTail->next;
            } else {
                largeTail->next = curr;
                largeTail = largeTail->next;
            }
            curr = curr->next;
        }
        // Terminate the large list
        largeTail->next = nullptr;
        // Connect small list to large list
        smallTail->next = largeDummy.next;
        return smallDummy.next;
    }
};