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
    public ListNode removeZeroSumSublists(ListNode head) {
        ListNode dummy = new ListNode(0, head);
        Map<Integer, ListNode> last = new HashMap<>();
        
        // First pass: record the last occurrence of each prefix sum
        int prefix = 0;
        ListNode curr = dummy;
        while (curr != null) {
            prefix += curr.val;
            last.put(prefix, curr);
            curr = curr.next;
        }
        
        // Second pass: remove zero-sum sequences
        prefix = 0;
        curr = dummy;
        while (curr != null) {
            prefix += curr.val;
            // Jump over the zero-sum sequence
            curr.next = last.get(prefix).next;
            curr = curr.next;
        }
        
        return dummy.next;
    }
}