/**
 * @param {string} columnTitle
 * @return {number}
 */
var titleToNumber = function(columnTitle) {
    let result = 0;
    
    // Process each character from left to right
    for (let i = 0; i < columnTitle.length; i++) {
        // Convert character to number: 'A' -> 1, 'B' -> 2, ..., 'Z' -> 26
        const value = columnTitle.charCodeAt(i) - 'A'.charCodeAt(0) + 1;
        
        // Update result: shift left by 26 and add current value
        result = result * 26 + value;
    }
    
    return result;
};