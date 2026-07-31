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
    public ListNode[] splitListToParts(ListNode head, int k) {
        // Step 1: Find the length of the linked list
        int n = 0;
        ListNode current = head;
        while (current != null) {
            n++;
            current = current.next;
        }
        
        // Step 2: Calculate the size of each part
        int baseSize = n / k;          // Minimum size for each part
        int extraNodes = n % k;        // First 'extraNodes' parts get one extra node
        
        // Step 3: Split the list
        ListNode[] result = new ListNode[k];
        current = head;
        
        for (int i = 0; i < k; i++) {
            // Determine the size of this part
            int partSize = baseSize + (i < extraNodes ? 1 : 0);
            
            if (partSize == 0) {
                result[i] = null;
                continue;
            }
            
            // Set the head of this part
            result[i] = current;
            
            // Move to the end of this part
            for (int j = 0; j < partSize - 1; j++) {
                current = current.next;
            }
            
            // Save the next node and break the link
            ListNode nextPart = current.next;
            current.next = null;
            current = nextPart;
        }
        
        return result;
    }
}