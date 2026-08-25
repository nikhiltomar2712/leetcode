/**
 * @param {number[]} nums
 * @return {number[]}
 */
var largestDivisibleSubset = function(nums) {
    if (nums.length === 0) return [];
    
    // Sort the array to ensure divisibility relationships are transitive
    nums.sort((a, b) => a - b);
    
    const n = nums.length;
    // dp[i] = size of largest divisible subset ending with nums[i]
    const dp = new Array(n).fill(1);
    // parent[i] = index of previous element in the subset
    const parent = new Array(n).fill(-1);
    
    let maxSize = 1;
    let maxIndex = 0;
    
    // Build the dp array
    for (let i = 1; i < n; i++) {
        for (let j = 0; j < i; j++) {
            // If nums[i] is divisible by nums[j], we can extend the subset
            if (nums[i] % nums[j] === 0 && dp[j] + 1 > dp[i]) {
                dp[i] = dp[j] + 1;
                parent[i] = j;
            }
        }
        // Track the maximum subset size
        if (dp[i] > maxSize) {
            maxSize = dp[i];
            maxIndex = i;
        }
    }
    
    // Reconstruct the subset using parent pointers
    const result = [];
    let currentIndex = maxIndex;
    while (currentIndex !== -1) {
        result.push(nums[currentIndex]);
        currentIndex = parent[currentIndex];
    }
    
    return result.reverse();
};