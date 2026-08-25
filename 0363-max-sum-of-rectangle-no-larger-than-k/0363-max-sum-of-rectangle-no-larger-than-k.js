/**
 * @param {number[][]} matrix
 * @param {number} k
 * @return {number}
 */
var maxSumSubmatrix = function(matrix, k) {
    const rows = matrix.length;
    const cols = matrix[0].length;
    let maxSum = -Infinity;
    
    // Iterate over all pairs of columns (left and right boundaries)
    for (let left = 0; left < cols; left++) {
        // Array to store row sums between left and right columns
        const rowSums = new Array(rows).fill(0);
        
        for (let right = left; right < cols; right++) {
            // Update row sums by adding the current column
            for (let row = 0; row < rows; row++) {
                rowSums[row] += matrix[row][right];
            }
            
            // Now find the max subarray sum <= k in rowSums
            maxSum = Math.max(maxSum, maxSubarraySumNoLargerThanK(rowSums, k));
            
            // Early exit if we found exactly k
            if (maxSum === k) return k;
        }
    }
    
    return maxSum;
};

/**
 * Find max subarray sum <= k using prefix sums and binary search
 * @param {number[]} arr
 * @param {number} k
 * @return {number}
 */
function maxSubarraySumNoLargerThanK(arr, k) {
    const prefixSums = [0]; // Prefix sum array
    let currentSum = 0;
    let maxSum = -Infinity;
    
    for (const num of arr) {
        currentSum += num;
        
        // Find the smallest prefix sum >= currentSum - k
        const target = currentSum - k;
        const index = binarySearchCeiling(prefixSums, target);
        
        if (index !== -1) {
            maxSum = Math.max(maxSum, currentSum - prefixSums[index]);
        }
        
        // Insert currentSum while maintaining sorted order
        insertSorted(prefixSums, currentSum);
    }
    
    return maxSum;
}

/**
 * Find the smallest element in sorted array >= target
 * @param {number[]} sortedArr
 * @param {number} target
 * @return {number} - index of found element, or -1 if none
 */
function binarySearchCeiling(sortedArr, target) {
    let left = 0, right = sortedArr.length - 1;
    let result = -1;
    
    while (left <= right) {
        const mid = Math.floor((left + right) / 2);
        if (sortedArr[mid] >= target) {
            result = mid;
            right = mid - 1;
        } else {
            left = mid + 1;
        }
    }
    
    return result;
}

/**
 * Insert value into sorted array while maintaining order
 * @param {number[]} sortedArr
 * @param {number} value
 */
function insertSorted(sortedArr, value) {
    let left = 0, right = sortedArr.length;
    
    while (left < right) {
        const mid = Math.floor((left + right) / 2);
        if (sortedArr[mid] < value) {
            left = mid + 1;
        } else {
            right = mid;
        }
    }
    
    sortedArr.splice(left, 0, value);
}