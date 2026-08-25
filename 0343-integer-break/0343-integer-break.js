/**
 * @param {number} n
 * @return {number}
 */
var integerBreak = function(n) {
    if (n <= 3) return n - 1; // n = 2 → 1, n = 3 → 2
    
    let product = 1;
    
    // Use as many 3's as possible
    while (n > 4) {
        product *= 3;
        n -= 3;
    }
    
    // Multiply remaining (2, 3, or 4)
    product *= n;
    
    return product;
};