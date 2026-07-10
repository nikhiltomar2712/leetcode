/**
 * @param {number[]} nums
 * @return {number}
 */
var singleNumber = function(nums) {
    let result = 0;
    
    // XOR all numbers together
    // Properties of XOR:
    // 1. a ^ a = 0 (same numbers cancel out)
    // 2. a ^ 0 = a (single number remains)
    // 3. XOR is commutative and associative (order doesn't matter)
    for (const num of nums) {
        result ^= num;
    }
    
    return result;
};