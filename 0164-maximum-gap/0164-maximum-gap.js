/**
 * @param {number[]} nums
 * @return {number}
 */
var maximumGap = function(nums) {
    const n = nums.length;
    if (n < 2) return 0;
    
    // Find min and max values
    let minVal = Math.min(...nums);
    let maxVal = Math.max(...nums);
    
    // If all elements are the same
    if (minVal === maxVal) return 0;
    
    // Calculate bucket size and number of buckets
    // We need at least n-1 buckets to ensure max gap is between buckets
    const bucketSize = Math.max(1, Math.floor((maxVal - minVal) / (n - 1)));
    const bucketCount = Math.floor((maxVal - minVal) / bucketSize) + 1;
    
    // Initialize buckets: each bucket tracks min and max in that range
    const buckets = new Array(bucketCount);
    for (let i = 0; i < bucketCount; i++) {
        buckets[i] = { min: Infinity, max: -Infinity, hasValue: false };
    }
    
    // Place each number into its appropriate bucket
    for (const num of nums) {
        const index = Math.floor((num - minVal) / bucketSize);
        buckets[index].min = Math.min(buckets[index].min, num);
        buckets[index].max = Math.max(buckets[index].max, num);
        buckets[index].hasValue = true;
    }
    
    // Find maximum gap between consecutive non-empty buckets
    let maxGap = 0;
    let prevMax = minVal;
    
    for (let i = 0; i < bucketCount; i++) {
        if (!buckets[i].hasValue) continue;
        
        // Gap between previous bucket's max and current bucket's min
        maxGap = Math.max(maxGap, buckets[i].min - prevMax);
        prevMax = buckets[i].max;
    }
    
    return maxGap;
};