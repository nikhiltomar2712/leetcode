/**
 * @param {number[]} nums
 * @return {number}
 */
var findMin = function(nums) {
    let left = 0;
    let right = nums.length - 1;
    
    while (left < right) {
        const mid = Math.floor((left + right) / 2);
        
        // Case 1: mid > right → rotation point is to the right of mid
        if (nums[mid] > nums[right]) {
            left = mid + 1;
        } 
        // Case 2: mid < right → rotation point is to the left of or at mid
        else if (nums[mid] < nums[right]) {
            right = mid;
        }
        // Case 3: mid == right → cannot determine, reduce search space by 1
        else {
            right--;
        }
    }
    
    return nums[left];
};