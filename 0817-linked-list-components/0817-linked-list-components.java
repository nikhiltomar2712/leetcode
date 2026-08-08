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
    public int numComponents(ListNode head, int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }

        int components = 0;
        while (head != null) {
            // Current node is in the set AND (end of list OR next node is not in the set)
            if (set.contains(head.val) && 
                (head.next == null || !set.contains(head.next.val))) {
                components++;
            }
            head = head.next;
        }
        return components;
    }
}