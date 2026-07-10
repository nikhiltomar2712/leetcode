var copyRandomList = function(head) {
    if (!head) return null;
    
    // Step 1: Interweave copies
    let curr = head;
    while (curr) {
        let copy = new Node(curr.val, curr.next, null);
        curr.next = copy;
        curr = copy.next;
    }
    
    // Step 2: Set random pointers
    curr = head;
    while (curr) {
        if (curr.random) {
            curr.next.random = curr.random.next;
        }
        curr = curr.next.next;
    }
    
    // Step 3: Separate lists
    curr = head;
    let dummy = new Node(0);
    let copyCurr = dummy;
    
    while (curr) {
        copyCurr.next = curr.next;
        copyCurr = copyCurr.next;
        curr.next = curr.next.next;
        curr = curr.next;
    }
    
    return dummy.next;
};