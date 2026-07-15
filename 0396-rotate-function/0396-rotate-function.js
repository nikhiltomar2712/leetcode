/**
 * @param {number[]} nums
 * @return {number}
 */
var maxRotateFunction = function(nums) {
    const n = nums.length;
    
    // Handle edge case: single element
    if (n === 1) return 0;
    
    // Calculate the sum of all elements
    const sum = nums.reduce((acc, val) => acc + val, 0);
    
    // Calculate F(0) = 0*nums[0] + 1*nums[1] + ... + (n-1)*nums[n-1]
    let f0 = 0;
    for (let i = 0; i < n; i++) {
        f0 += i * nums[i];
    }
    
    // Initialize max with F(0)
    let maxF = f0;
    let currentF = f0;
    
    // Calculate F(k) for k = 1 to n-1 using the recurrence relation
    for (let k = 1; k < n; k++) {
        // F(k) = F(k-1) + sum - n * nums[n - k]
        // Explanation: When rotating right by 1, the element at index n-1 moves to index 0,
        // losing (n-1)*value and gaining 0*value, net change: -(n-1)*value + 0*value = -n*value + value
        // All other elements gain +value, so total change: -n*value + sum
        currentF = currentF + sum - n * nums[n - k];
        maxF = Math.max(maxF, currentF);
    }
    
    return maxF;
};