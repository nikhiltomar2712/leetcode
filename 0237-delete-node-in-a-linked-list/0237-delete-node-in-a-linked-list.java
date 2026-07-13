/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public void deleteNode(ListNode node) {
        // Copy the next node's value to current node
        node.val = node.next.val;
        
        // Skip the next node (garbage collector will clean it up)
        node.next = node.next.next;
    }
}