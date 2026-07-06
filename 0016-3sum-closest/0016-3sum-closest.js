/**
 * @param {number[]} nums
 * @param {number} target
 * @return {number}
 */
var threeSumClosest = function(nums, target) {
    nums.sort((a, b) => a - b);
    let closest = Infinity;
    
    for (let i = 0; i < nums.length - 2; i++) {
        // Skip duplicates for optimization
        if (i > 0 && nums[i] === nums[i - 1]) continue;
        
        let left = i + 1;
        let right = nums.length - 1;
        
        while (left < right) {
            const sum = nums[i] + nums[left] + nums[right];
            
            // Update closest if current sum is closer
            if (Math.abs(sum - target) < Math.abs(closest - target)) {
                closest = sum;
            }
            
            if (sum < target) {
                left++;
            } else if (sum > target) {
                right--;
            } else {
                return sum; // Exact match found
            }
        }
    }
    
    return closest;
};