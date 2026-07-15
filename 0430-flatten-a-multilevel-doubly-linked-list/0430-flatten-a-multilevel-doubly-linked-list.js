/**
 * // Definition for a Node.
 * function Node(val, prev, next, child) {
 *    this.val = val;
 *    this.prev = prev;
 *    this.next = next;
 *    this.child = child;
 * };
 */

/**
 * @param {Node} head
 * @return {Node}
 */
var flatten = function(head) {
    if (head === null) return null;
    
    // Use a stack to keep track of nodes that need to be processed
    const stack = [];
    let current = head;
    
    while (current !== null) {
        // If the current node has a child, we need to flatten it
        if (current.child !== null) {
            // If there's a next node, push it onto the stack for later processing
            if (current.next !== null) {
                stack.push(current.next);
            }
            
            // Connect current node to its child
            current.next = current.child;
            current.child.prev = current;
            // Clear the child pointer (not strictly necessary but good practice)
            current.child = null;
        }
        
        // If we've reached the end of this level and there are nodes in the stack
        if (current.next === null && stack.length > 0) {
            // Pop the next node from the stack
            const nextNode = stack.pop();
            // Connect the current node to the next node
            current.next = nextNode;
            nextNode.prev = current;
        }
        
        // Move to the next node
        current = current.next;
    }
    
    return head;
};