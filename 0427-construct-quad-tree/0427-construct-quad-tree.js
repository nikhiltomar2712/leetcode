/**
 * // Definition for a QuadTree node.
 * function Node(val, isLeaf, topLeft, topRight, bottomLeft, bottomRight) {
 *    this.val = val;
 *    this.isLeaf = isLeaf;
 *    this.topLeft = topLeft;
 *    this.topRight = topRight;
 *    this.bottomLeft = bottomLeft;
 *    this.bottomRight = bottomRight;
 * };
 */

/**
 * @param {number[][]} grid
 * @return {Node}
 */
function construct(grid) {
    const n = grid.length;
    
    // Helper function to check if a sub-grid is uniform (all values are the same)
    function isUniform(x, y, size) {
        const firstVal = grid[x][y];
        for (let i = x; i < x + size; i++) {
            for (let j = y; j < y + size; j++) {
                if (grid[i][j] !== firstVal) {
                    return false;
                }
            }
        }
        return true;
    }
    
    // Recursive function to build the Quad-Tree
    function build(x, y, size) {
        // If the current sub-grid is uniform, create a leaf node
        if (isUniform(x, y, size)) {
            return new Node(grid[x][y] === 1, true, null, null, null, null);
        }
        
        // Otherwise, divide into 4 sub-grids and recurse
        const half = size / 2;
        const topLeft = build(x, y, half);
        const topRight = build(x, y + half, half);
        const bottomLeft = build(x + half, y, half);
        const bottomRight = build(x + half, y + half, half);
        
        // Create an internal node. The val can be anything (true/false), as per problem statement.
        return new Node(true, false, topLeft, topRight, bottomLeft, bottomRight);
    }
    
    return build(0, 0, n);
}