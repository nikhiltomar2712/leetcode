/** 
 * Forward declaration of guess API.
 * @param {number} num   your guess
 * @return 	            -1 if num is higher than the picked number
 *			             1 if num is lower than the picked number
 *                       otherwise return 0
 * var guess = function(num) {}
 */

/**
 * @param {number} n
 * @return {number}
 */
var guessNumber = function(n) {
    let left = 1;
    let right = n;
    
    while (left <= right) {
        const mid = left + Math.floor((right - left) / 2);
        const result = guess(mid);
        
        if (result === 0) {
            return mid;
        } else if (result === -1) {
            // The picked number is lower than mid, search left side
            right = mid - 1;
        } else {
            // The picked number is higher than mid, search right side
            left = mid + 1;
        }
    }
    
    // Should never reach here as there's always a valid pick
    return -1;
};