/**
 * @param {number} num
 * @return {boolean}
 */
var isPerfectSquare = function(num) {
    // Handle edge cases
    if (num < 2) return true; // 0 and 1 are perfect squares
    
    let left = 2;
    let right = Math.floor(num / 2);
    
    while (left <= right) {
        const mid = Math.floor((left + right) / 2);
        const square = mid * mid;
        
        if (square === num) {
            return true;
        } else if (square < num) {
            left = mid + 1;
        } else {
            right = mid - 1;
        }
    }
    
    return false;
};