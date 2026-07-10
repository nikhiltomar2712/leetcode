/**
 * @param {number[]} nums
 * @return {number}
 */
var findMin = function(nums) {
    let left = 0;
    let right = nums.length - 1;
    
    // Binary search to find the rotation point (minimum element)
    while (left < right) {
        const mid = Math.floor((left + right) / 2);
        
        // If middle element is greater than the rightmost element,
        // the minimum must be in the right half (to the right of mid)
        if (nums[mid] > nums[right]) {
            left = mid + 1;
        } 
        // Otherwise, the minimum is in the left half (including mid)
        else {
            right = mid;
        }
    }
    
    // left and right converge to the minimum element
    return nums[left];
};