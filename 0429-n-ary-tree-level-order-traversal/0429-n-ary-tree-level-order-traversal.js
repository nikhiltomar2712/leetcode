/**
 * // Definition for a Node.
 * function Node(val, children) {
 *    this.val = val;
 *    this.children = children;
 * };
 */

/**
 * @param {Node|null} root
 * @return {number[][]}
 */
var levelOrder = function(root) {
    // Result array to store all levels
    const result = [];
    
    // Edge case: empty tree
    if (root === null) {
        return result;
    }
    
    // Initialize queue with the root node
    const queue = [root];
    
    // Process the tree level by level
    while (queue.length > 0) {
        // Number of nodes at the current level
        const levelSize = queue.length;
        // Array to store values of nodes at the current level
        const currentLevel = [];
        
        // Iterate through all nodes at the current level
        for (let i = 0; i < levelSize; i++) {
            // Remove the first node from the queue
            const currentNode = queue.shift();
            
            // Add its value to the current level array
            currentLevel.push(currentNode.val);
            
            // Add all its children to the queue for the next level
            // The children property is an array of Node objects
            if (currentNode.children) {
                for (const child of currentNode.children) {
                    queue.push(child);
                }
            }
        }
        
        // Add the current level's values to the result
        result.push(currentLevel);
    }
    
    return result;
};