/**
 * @param {number[][]} matrix
 */
var NumMatrix = function(matrix) {
    const m = matrix.length;
    const n = matrix[0].length;
    
    // Create a 2D prefix sum array with an extra row and column of zeros
    // prefix[i][j] = sum of all elements in matrix[0...i-1][0...j-1]
    this.prefix = Array.from({ length: m + 1 }, () => new Array(n + 1).fill(0));
    
    for (let i = 0; i < m; i++) {
        for (let j = 0; j < n; j++) {
            // Formula for 2D prefix sum
            this.prefix[i + 1][j + 1] = 
                matrix[i][j] 
                + this.prefix[i][j + 1]   // sum above
                + this.prefix[i + 1][j]   // sum left
                - this.prefix[i][j];      // subtract double-counted area
        }
    }
};

/**
 * @param {number} row1
 * @param {number} col1
 * @param {number} row2
 * @param {number} col2
 * @return {number}
 */
NumMatrix.prototype.sumRegion = function(row1, col1, row2, col2) {
    // Use the inclusion-exclusion principle with the prefix sum array
    return this.prefix[row2 + 1][col2 + 1]
         - this.prefix[row1][col2 + 1]
         - this.prefix[row2 + 1][col1]
         + this.prefix[row1][col1];
};

/**
 * Your NumMatrix object will be instantiated and called as such:
 * var obj = new NumMatrix(matrix)
 * var param_1 = obj.sumRegion(row1,col1,row2,col2)
 */