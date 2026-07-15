/**
 * @param {number} num
 * @return {string}
 */
var toHex = function(num) {
    if (num === 0) return "0";
    
    // For negative numbers, convert to 32-bit unsigned
    if (num < 0) {
        num = num >>> 0; // Zero-fill right shift converts to unsigned 32-bit
    }
    
    const hexChars = "0123456789abcdef";
    let result = "";
    
    while (num > 0) {
        result = hexChars[num & 15] + result; // Get last 4 bits
        num = num >>> 4; // Shift right by 4 bits (unsigned)
    }
    
    return result;
};