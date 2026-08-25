/**
 * @param {number} n
 * @return {number[]}
 */
var countBits = function(n) {
    const result = new Array(n + 1).fill(0);
    
    for (let i = 1; i <= n; i++) {
        // i & (i - 1) removes the least significant 1-bit
        result[i] = result[i & (i - 1)] + 1;
    }
    
    return result;
};