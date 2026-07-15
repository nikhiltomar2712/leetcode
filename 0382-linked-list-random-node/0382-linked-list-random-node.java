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
    private ListNode head;
    private Random rand;

    public Solution(ListNode head) {
        this.head = head;
        this.rand = new Random();
    }
    
    public int getRandom() {
        ListNode curr = head;
        int result = -1;
        int count = 0;
        
        while (curr != null) {
            count++;
            // Probability of selecting current node: 1/count
            if (rand.nextInt(count) == 0) {
                result = curr.val;
            }
            curr = curr.next;
        }
        
        return result;
    }
}