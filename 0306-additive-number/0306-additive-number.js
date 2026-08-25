/**
 * @param {string} num
 * @return {boolean}
 */
var isAdditiveNumber = function(num) {
    const n = num.length;
    
    // Try all possible lengths for the first two numbers
    for (let i = 1; i <= Math.floor(n / 2); i++) {
        // First number cannot have leading zero unless it's "0"
        if (num[0] === '0' && i > 1) break;
        
        const first = num.substring(0, i);
        
        for (let j = i + 1; j < n; j++) {
            // Second number cannot have leading zero unless it's "0"
            if (num[i] === '0' && j > i + 1) break;
            
            const second = num.substring(i, j);
            const remaining = num.substring(j);
            
            if (isValidSequence(first, second, remaining)) {
                return true;
            }
        }
    }
    
    return false;
};

/**
 * Helper function to check if the remaining string can form an additive sequence
 * @param {string} first
 * @param {string} second
 * @param {string} remaining
 * @return {boolean}
 */
function isValidSequence(first, second, remaining) {
    // Base case: if no remaining characters, we have a valid sequence
    if (remaining.length === 0) return true;
    
    // Calculate the sum of first and second as strings (handles large numbers)
    const sum = addStrings(first, second);
    
    // Check if the remaining string starts with the sum
    if (!remaining.startsWith(sum)) {
        return false;
    }
    
    // Continue with the next pair: (second, sum) and the rest of the string
    const nextRemaining = remaining.substring(sum.length);
    return isValidSequence(second, sum, nextRemaining);
}

/**
 * Add two numbers represented as strings (handles very large integers)
 * @param {string} a
 * @param {string} b
 * @return {string}
 */
function addStrings(a, b) {
    let i = a.length - 1;
    let j = b.length - 1;
    let carry = 0;
    const result = [];
    
    while (i >= 0 || j >= 0 || carry > 0) {
        const digitA = i >= 0 ? parseInt(a[i]) : 0;
        const digitB = j >= 0 ? parseInt(b[j]) : 0;
        const sum = digitA + digitB + carry;
        
        result.push(sum % 10);
        carry = Math.floor(sum / 10);
        
        i--;
        j--;
    }
    
    return result.reverse().join('');
}