/**
 * @param {number} n
 * @return {number}
 */
var findNthDigit = function(n) {
    // Step 1: Determine the number of digits in the number containing the nth digit
    let digitLength = 1;    // Number of digits (1 for 1-9, 2 for 10-99, etc.)
    let count = 9;          // How many numbers have this digit length
    let start = 1;          // The first number with this digit length
    
    // Find the range where the nth digit lies
    while (n > digitLength * count) {
        n -= digitLength * count;
        digitLength++;
        count *= 10;
        start *= 10;
    }
    
    // Step 2: Find the exact number that contains the nth digit
    // (n-1) because we want 0-based index within the numbers
    const number = start + Math.floor((n - 1) / digitLength);
    
    // Step 3: Find the exact digit within that number
    // (n-1) % digitLength gives the 0-based position from the left
    const digitIndex = (n - 1) % digitLength;
    
    // Convert number to string and return the digit at the found position
    return parseInt(number.toString()[digitIndex]);
};