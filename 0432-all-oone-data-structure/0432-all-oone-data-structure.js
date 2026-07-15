class AllOne {
    constructor() {
        // Map to store key -> count
        this.keyCount = new Map();
        
        // Doubly Linked List nodes: each node represents a count
        // and stores a Set of keys with that count
        this.countMap = new Map(); // count -> Node
        
        // Dummy head and tail for the doubly linked list
        this.head = new Node(0);
        this.tail = new Node(Infinity);
        this.head.next = this.tail;
        this.tail.prev = this.head;
    }
    
    /**
     * Increments the count of the string key by 1
     * @param {string} key
     * @return {void}
     */
    inc(key) {
        const oldCount = this.keyCount.get(key) || 0;
        const newCount = oldCount + 1;
        
        // Update keyCount map
        this.keyCount.set(key, newCount);
        
        // Remove key from old count's set
        if (oldCount > 0) {
            this._removeKeyFromCount(key, oldCount);
        }
        
        // Add key to new count's set
        this._addKeyToCount(key, newCount);
    }
    
    /**
     * Decrements the count of the string key by 1
     * @param {string} key
     * @return {void}
     */
    dec(key) {
        const oldCount = this.keyCount.get(key);
        const newCount = oldCount - 1;
        
        if (newCount === 0) {
            // Remove key entirely
            this.keyCount.delete(key);
            this._removeKeyFromCount(key, oldCount);
        } else {
            // Update keyCount map
            this.keyCount.set(key, newCount);
            
            // Remove key from old count's set
            this._removeKeyFromCount(key, oldCount);
            
            // Add key to new count's set
            this._addKeyToCount(key, newCount);
        }
    }
    
    /**
     * Returns one of the keys with the maximal count
     * @return {string}
     */
    getMaxKey() {
        if (this.head.next === this.tail) return "";
        // The node before tail has the maximum count
        const maxNode = this.tail.prev;
        return maxNode.keys.values().next().value || "";
    }
    
    /**
     * Returns one of the keys with the minimum count
     * @return {string}
     */
    getMinKey() {
        if (this.head.next === this.tail) return "";
        // The node after head has the minimum count
        const minNode = this.head.next;
        return minNode.keys.values().next().value || "";
    }
    
    /**
     * Helper: Add key to a count node (create node if doesn't exist)
     * @param {string} key
     * @param {number} count
     * @private
     */
    _addKeyToCount(key, count) {
        let node = this.countMap.get(count);
        if (!node) {
            // Create new node and insert in sorted order
            node = new Node(count);
            this.countMap.set(count, node);
            
            // Find position to insert (before first node with count > current)
            let current = this.head.next;
            while (current !== this.tail && current.count < count) {
                current = current.next;
            }
            this._insertNodeBefore(node, current);
        }
        node.keys.add(key);
    }
    
    /**
     * Helper: Remove key from a count node (delete node if empty)
     * @param {string} key
     * @param {number} count
     * @private
     */
    _removeKeyFromCount(key, count) {
        const node = this.countMap.get(count);
        if (!node) return;
        
        node.keys.delete(key);
        
        if (node.keys.size === 0) {
            // Remove empty node from linked list
            this._removeNode(node);
            this.countMap.delete(count);
        }
    }
    
    /**
     * Helper: Insert a node before another node
     * @param {Node} newNode
     * @param {Node} refNode
     * @private
     */
    _insertNodeBefore(newNode, refNode) {
        newNode.prev = refNode.prev;
        newNode.next = refNode;
        refNode.prev.next = newNode;
        refNode.prev = newNode;
    }
    
    /**
     * Helper: Remove a node from linked list
     * @param {Node} node
     * @private
     */
    _removeNode(node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
        node.prev = null;
        node.next = null;
    }
}

/**
 * Doubly Linked List Node
 */
class Node {
    constructor(count) {
        this.count = count;
        this.keys = new Set(); // Set of keys with this count
        this.prev = null;
        this.next = null;
    }
}