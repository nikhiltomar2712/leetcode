/**
 * @param {string[][]} tickets
 * @return {string[]}
 */
var findItinerary = function(tickets) {
    // Build adjacency list with sorted destinations
    const graph = {};
    
    for (const [from, to] of tickets) {
        if (!graph[from]) {
            graph[from] = [];
        }
        graph[from].push(to);
    }
    
    // Sort destinations in reverse order (for efficient pop)
    for (const from in graph) {
        graph[from].sort().reverse();
    }
    
    const itinerary = [];
    
    function dfs(airport) {
        const destinations = graph[airport] || [];
        
        while (destinations.length > 0) {
            // Visit the smallest lexical destination (last in reversed array)
            const next = destinations.pop();
            dfs(next);
        }
        
        // Add airport after visiting all its outgoing edges
        itinerary.push(airport);
    }
    
    dfs('JFK');
    
    // Reverse the result (because we added airports in post-order)
    return itinerary.reverse();
};