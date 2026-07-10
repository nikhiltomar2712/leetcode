/**
 * Definition for singly-linked list.
 * function ListNode(val, next) {
 *     this.val = (val===undefined ? 0 : val)
 *     this.next = (next===undefined ? null : next)
 * }
 */

/**
 * @param {ListNode} head
 * @return {ListNode}
 */
var insertionSortList = function(head) {
    if (!head || !head.next) return head;
    
    // Create a dummy node to serve as the start of sorted list
    let dummy = new ListNode(0);
    let current = head;
    
    while (current) {
        // Store next node before breaking the link
        let nextNode = current.next;
        
        // Find the correct position to insert current node in sorted list
        let prev = dummy;
        while (prev.next && prev.next.val < current.val) {
            prev = prev.next;
        }
        
        // Insert current node into sorted list
        current.next = prev.next;
        prev.next = current;
        
        // Move to next node
        current = nextNode;
    }
    
    return dummy.next;
};