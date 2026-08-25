/**
 * @param {number[]} nums
 * @return {number}
 */
var lengthOfLIS = function(nums) {
    const n = nums.length;
    if (n === 0) return 0;

    // dp[i] = length of the longest increasing subsequence ending at index i
    const dp = new Array(n).fill(1);
    let maxLength = 1;

    for (let i = 1; i < n; i++) {
        for (let j = 0; j < i; j++) {
            // If nums[j] can be placed before nums[i] in an increasing subsequence
            if (nums[j] < nums[i]) {
                // Update dp[i] with the best option
                dp[i] = Math.max(dp[i], dp[j] + 1);
            }
        }
        // Track the overall maximum
        maxLength = Math.max(maxLength, dp[i]);
    }

    return maxLength;
};