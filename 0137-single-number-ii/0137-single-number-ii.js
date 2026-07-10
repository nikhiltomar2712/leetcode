/**
 * @param {number[]} nums
 * @return {number}
 */
var singleNumber = function(nums) {
    // Array to count bits at each position
    const bitCount = new Array(32).fill(0);
    
    // Count occurrences of each bit
    for (const num of nums) {
        for (let i = 0; i < 32; i++) {
            if ((num >> i) & 1) {
                bitCount[i]++;
            }
        }
    }
    
    // Reconstruct the single number
    let result = 0;
    for (let i = 0; i < 32; i++) {
        if (bitCount[i] % 3 === 1) {
            result |= (1 << i);
        }
    }
    
    return result;
};