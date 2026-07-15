/**
 * @param {number[][]} heights
 * @return {number[][]}
 */
var pacificAtlantic = function(heights) {
    if (!heights || heights.length === 0) return [];
    
    const m = heights.length;
    const n = heights[0].length;
    
    // Create two boolean matrices to track reachable cells
    const pacific = Array.from({length: m}, () => Array(n).fill(false));
    const atlantic = Array.from({length: m}, () => Array(n).fill(false));
    
    const directions = [[0, 1], [0, -1], [1, 0], [-1, 0]];
    
    // BFS function
    function bfs(queue, visited) {
        while (queue.length > 0) {
            const [row, col] = queue.shift();
            visited[row][col] = true;
            
            for (let [dr, dc] of directions) {
                const newRow = row + dr;
                const newCol = col + dc;
                
                // Check if within bounds, not visited, and water can flow
                if (newRow >= 0 && newRow < m && newCol >= 0 && newCol < n &&
                    !visited[newRow][newCol] &&
                    heights[newRow][newCol] >= heights[row][col]) {
                    queue.push([newRow, newCol]);
                }
            }
        }
    }
    
    // Initialize BFS queues with ocean borders
    const pacificQueue = [];
    const atlanticQueue = [];
    
    // Pacific Ocean: top row and left column
    for (let i = 0; i < m; i++) {
        pacificQueue.push([i, 0]);
        pacific[i][0] = true;
    }
    for (let j = 0; j < n; j++) {
        pacificQueue.push([0, j]);
        pacific[0][j] = true;
    }
    
    // Atlantic Ocean: bottom row and right column
    for (let i = 0; i < m; i++) {
        atlanticQueue.push([i, n - 1]);
        atlantic[i][n - 1] = true;
    }
    for (let j = 0; j < n; j++) {
        atlanticQueue.push([m - 1, j]);
        atlantic[m - 1][j] = true;
    }
    
    bfs(pacificQueue, pacific);
    bfs(atlanticQueue, atlantic);
    
    // Find cells that can reach both oceans
    const result = [];
    for (let i = 0; i < m; i++) {
        for (let j = 0; j < n; j++) {
            if (pacific[i][j] && atlantic[i][j]) {
                result.push([i, j]);
            }
        }
    }
    
    return result;
};