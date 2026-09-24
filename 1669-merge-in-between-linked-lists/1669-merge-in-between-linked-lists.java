/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode mergeInBetween(ListNode list1, int a, int b, ListNode list2) {
        // Step 1: find node at index a-1
        ListNode prevA = list1;
        for (int i = 0; i < a - 1; i++) {
            prevA = prevA.next;
        }

        // Step 2: find node at index b+1
        ListNode afterB = list1;
        for (int i = 0; i < b + 1; i++) {
            afterB = afterB.next;
        }

        // Step 3: connect prevA -> head of list2
        prevA.next = list2;

        // Step 4: find tail of list2
        ListNode tail2 = list2;
        while (tail2.next != null) {
            tail2 = tail2.next;
        }

        // Step 5: connect tail of list2 -> afterB
        tail2.next = afterB;

        return list1;
    }
}