/**
 * @param {number[]} nums
 * @return {number}
 */
var findPeakElement = function(nums) {
    let left = 0;
    let right = nums.length - 1;
    
    while (left < right) {
        const mid = Math.floor((left + right) / 2);
        
        // If mid element is less than its right neighbor,
        // peak must be in the right half
        if (nums[mid] < nums[mid + 1]) {
            left = mid + 1;
        } 
        // Otherwise, peak is in the left half (including mid)
        else {
            right = mid;
        }
    }
    
    return left;
};