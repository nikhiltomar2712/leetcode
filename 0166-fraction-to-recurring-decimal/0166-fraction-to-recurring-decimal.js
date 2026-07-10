/**
 * @param {number} numerator
 * @param {number} denominator
 * @return {string}
 */
var fractionToDecimal = function(numerator, denominator) {
    // Handle zero numerator
    if (numerator === 0) return "0";
    
    let result = "";
    
    // Handle sign
    if ((numerator < 0) ^ (denominator < 0)) {
        result += "-";
    }
    
    // Use absolute values for calculation
    let num = Math.abs(numerator);
    let den = Math.abs(denominator);
    
    // Integer part
    const integerPart = Math.floor(num / den);
    result += integerPart;
    
    // Remainder
    let remainder = num % den;
    
    // If no remainder, return result
    if (remainder === 0) return result;
    
    // Add decimal point
    result += ".";
    
    // Map to store remainders and their positions
    const remainderMap = new Map();
    
    // Process decimal part
    while (remainder !== 0) {
        // If we've seen this remainder before, cycle detected
        if (remainderMap.has(remainder)) {
            const startIndex = remainderMap.get(remainder);
            result = result.slice(0, startIndex) + "(" + result.slice(startIndex) + ")";
            break;
        }
        
        // Store the current remainder and its position
        remainderMap.set(remainder, result.length);
        
        // Perform long division
        remainder *= 10;
        const digit = Math.floor(remainder / den);
        result += digit;
        remainder = remainder % den;
    }
    
    return result;
};