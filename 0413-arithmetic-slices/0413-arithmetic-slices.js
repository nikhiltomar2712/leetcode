/**
 * @param {number[]} nums
 * @return {number}
 */
var numberOfArithmeticSlices = function(nums) {
    if (nums.length < 3) return 0;
    
    let total = 0;
    let dp = 0; // Number of arithmetic slices ending at current index
    
    for (let i = 2; i < nums.length; i++) {
        if (nums[i] - nums[i-1] === nums[i-1] - nums[i-2]) {
            dp = dp + 1;
            total += dp;
        } else {
            dp = 0;
        }
    }
    
    return total;
};