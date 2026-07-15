/**
 * @param {number[]} nums
 * @return {boolean}
 */
var canPartition = function(nums) {
    // Calculate total sum
    const totalSum = nums.reduce((a, b) => a + b, 0);
    
    // If total sum is odd, cannot partition equally
    if (totalSum % 2 !== 0) return false;
    
    const target = totalSum / 2;
    const dp = new Array(target + 1).fill(false);
    dp[0] = true;
    
    for (let num of nums) {
        // Iterate backwards to avoid using the same element multiple times
        for (let i = target; i >= num; i--) {
            dp[i] = dp[i] || dp[i - num];
        }
    }
    
    return dp[target];
};