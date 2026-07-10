/**
 * @param {Node} node
 * @return {Node}
 */
var cloneGraph = function(node) {
    if (!node) return null;
    
    // Map to store cloned nodes
    const visited = new Map();
    
    // Create clone of starting node
    const cloneNode = new Node(node.val);
    visited.set(node, cloneNode);
    
    // BFS queue
    const queue = [node];
    
    while (queue.length > 0) {
        const current = queue.shift();
        
        // Process all neighbors
        for (const neighbor of current.neighbors) {
            // If neighbor not cloned yet
            if (!visited.has(neighbor)) {
                // Create clone
                const cloneNeighbor = new Node(neighbor.val);
                visited.set(neighbor, cloneNeighbor);
                queue.push(neighbor);
            }
            
            // Add the cloned neighbor to current node's clone neighbors
            visited.get(current).neighbors.push(visited.get(neighbor));
        }
    }
    
    return cloneNode;
};