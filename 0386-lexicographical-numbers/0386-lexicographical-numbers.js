/**
 * @param {number} n
 * @return {number[]}
 */
var lexicalOrder = function(n) {
    const result = [];
    // Start from the first number (1)
    let current = 1;
    
    // We need to collect n numbers
    for (let i = 0; i < n; i++) {
        result.push(current);
        
        // Try to go deeper: multiply by 10 (e.g., 1 -> 10 -> 100)
        if (current * 10 <= n) {
            current *= 10;
        } else {
            // Cannot go deeper, try to increment
            // If we've reached the end of a branch (e.g., 19 -> 2), we need to divide by 10 and increment
            while (current % 10 === 9 || current + 1 > n) {
                current = Math.floor(current / 10);
            }
            current++;
        }
    }
    
    return result;
};