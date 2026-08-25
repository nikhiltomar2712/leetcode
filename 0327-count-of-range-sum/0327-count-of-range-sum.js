/**
 * @param {number[]} nums
 * @param {number} lower
 * @param {number} upper
 * @return {number}
 */
var countRangeSum = function(nums, lower, upper) {
    const n = nums.length;
    
    // Calculate prefix sums
    const prefixSums = new Array(n + 1).fill(0);
    for (let i = 0; i < n; i++) {
        prefixSums[i + 1] = prefixSums[i] + nums[i];
    }
    
    // Use merge sort to count range sums
    function mergeSort(start, end) {
        if (start >= end) return 0;
        
        const mid = Math.floor((start + end) / 2);
        let count = mergeSort(start, mid) + mergeSort(mid + 1, end);
        
        // Count valid ranges
        let left = mid + 1;
        let right = mid + 1;
        
        for (let i = start; i <= mid; i++) {
            while (left <= end && prefixSums[left] - prefixSums[i] < lower) {
                left++;
            }
            while (right <= end && prefixSums[right] - prefixSums[i] <= upper) {
                right++;
            }
            count += right - left;
        }
        
        // Merge the two sorted halves
        const temp = [];
        let i = start, j = mid + 1;
        
        while (i <= mid && j <= end) {
            if (prefixSums[i] <= prefixSums[j]) {
                temp.push(prefixSums[i++]);
            } else {
                temp.push(prefixSums[j++]);
            }
        }
        
        while (i <= mid) temp.push(prefixSums[i++]);
        while (j <= end) temp.push(prefixSums[j++]);
        
        for (let k = 0; k < temp.length; k++) {
            prefixSums[start + k] = temp[k];
        }
        
        return count;
    }
    
    return mergeSort(0, n);
};