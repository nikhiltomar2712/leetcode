/**
 * @param {ListNode} head
 * @return {void}
 */
var reorderList = function(head) {
    // Handle edge cases
    if (!head || !head.next) return;
    
    // Step 1: Find the middle node using slow/fast pointers
    let slow = head;
    let fast = head;
    
    while (fast && fast.next) {
        slow = slow.next;
        fast = fast.next.next;
    }
    
    // Step 2: Reverse the second half of the list
    let prev = null;
    let current = slow;
    
    while (current) {
        let nextNode = current.next;
        current.next = prev;
        prev = current;
        current = nextNode;
    }
    
    // Step 3: Merge the first and second halves
    let firstHalf = head;
    let secondHalf = prev;
    
    while (secondHalf.next) {
        let temp1 = firstHalf.next;
        let temp2 = secondHalf.next;
        
        firstHalf.next = secondHalf;
        secondHalf.next = temp1;
        
        firstHalf = temp1;
        secondHalf = temp2;
    }
};