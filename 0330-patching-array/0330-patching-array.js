/**
 * @param {number[]} nums
 * @param {number} n
 * @return {number}
 */
var minPatches = function(nums, n) {
    let patches = 0;
    let maxReach = 0; // We can form all sums from 1 to maxReach
    let i = 0;
    
    while (maxReach < n) {
        if (i < nums.length && nums[i] <= maxReach + 1) {
            // We can extend our reach using nums[i]
            maxReach += nums[i];
            i++;
        } else {
            // We need to patch with maxReach + 1
            patches++;
            maxReach += maxReach + 1;
        }
    }
    
    return patches;
};