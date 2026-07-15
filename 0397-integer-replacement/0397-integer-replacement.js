/**
 * @param {number} n
 * @return {number}
 */
var integerReplacement = function(n) {
    let count = 0;
    // Use long to avoid overflow issues when n = 2^31 - 1
    let num = n;
    
    while (num > 1) {
        if (num % 2 === 0) {
            // Even: always divide by 2
            num /= 2;
        } else {
            // Odd: decide whether to add 1 or subtract 1
            // If num is 3, subtracting 1 is better (3 -> 2 -> 1) than adding 1 (3 -> 4 -> 2 -> 1)
            if (num === 3) {
                num -= 1;
            } 
            // Check the last two bits: if they are '11' (num % 4 === 3), adding 1 is better
            // because it creates more trailing zeros (e.g., 7 -> 8 -> 4 -> 2 -> 1)
            else if ((num & 3) === 3) { // num % 4 === 3
                num += 1;
            } 
            // Otherwise (last two bits are '01'), subtract 1 is better
            else {
                num -= 1;
            }
        }
        count++;
    }
    
    return count;
};