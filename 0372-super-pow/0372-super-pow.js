/**
 * @param {number} a
 * @param {number[]} b
 * @return {number}
 */
var superPow = function(a, b) {
    const MOD = 1337;
    
    // Helper function to calculate (a^b) % mod
    function powMod(a, b, mod) {
        let result = 1;
        a = a % mod;
        
        while (b > 0) {
            if (b & 1) { // if b is odd
                result = (result * a) % mod;
            }
            a = (a * a) % mod;
            b >>= 1; // divide b by 2
        }
        
        return result;
    }
    
    // Process the exponent digit by digit
    let result = 1;
    let base = a % MOD;
    
    for (let i = 0; i < b.length; i++) {
        // For each digit: result = (result^10 * a^digit) % MOD
        result = (powMod(result, 10, MOD) * powMod(base, b[i], MOD)) % MOD;
    }
    
    return result;
};