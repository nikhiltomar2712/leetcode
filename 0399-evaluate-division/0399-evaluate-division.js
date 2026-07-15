/**
 * @param {string[][]} equations
 * @param {number[]} values
 * @param {string[][]} queries
 * @return {number[]}
 */
var calcEquation = function(equations, values, queries) {
    // Build the graph
    const graph = new Map();
    
    for (let i = 0; i < equations.length; i++) {
        const [a, b] = equations[i];
        const value = values[i];
        
        // Add edge a -> b with weight value (a/b = value)
        if (!graph.has(a)) graph.set(a, []);
        graph.get(a).push([b, value]);
        
        // Add edge b -> a with weight 1/value (b/a = 1/value)
        if (!graph.has(b)) graph.set(b, []);
        graph.get(b).push([a, 1 / value]);
    }
    
    // Helper function to perform DFS/BFS and find the product from start to end
    function findProduct(start, end) {
        // If either node doesn't exist in the graph, return -1
        if (!graph.has(start) || !graph.has(end)) {
            return -1.0;
        }
        
        // If start and end are the same, return 1.0 (a/a = 1)
        if (start === end) {
            return 1.0;
        }
        
        // Use BFS to find a path from start to end
        const queue = [[start, 1.0]]; // [node, currentProduct]
        const visited = new Set();
        visited.add(start);
        
        while (queue.length > 0) {
            const [currentNode, currentProduct] = queue.shift();
            
            // Explore neighbors
            for (const [neighbor, weight] of graph.get(currentNode) || []) {
                if (visited.has(neighbor)) continue;
                
                const newProduct = currentProduct * weight;
                
                // If we reached the end, return the product
                if (neighbor === end) {
                    return newProduct;
                }
                
                visited.add(neighbor);
                queue.push([neighbor, newProduct]);
            }
        }
        
        // No path found
        return -1.0;
    }
    
    // Process each query
    const results = [];
    for (const [c, d] of queries) {
        results.push(findProduct(c, d));
    }
    
    return results;
};