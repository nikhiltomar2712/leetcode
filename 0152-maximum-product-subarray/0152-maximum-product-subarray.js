/**
 * @param {number[]} nums
 * @return {number}
 */
var maxProduct = function(nums) {
    if (nums.length === 0) return 0;
    
    // Track maximum and minimum product ending at current position
    let maxEndingHere = nums[0];
    let minEndingHere = nums[0];
    let globalMax = nums[0];
    
    for (let i = 1; i < nums.length; i++) {
        const current = nums[i];
        
        // Store previous values before overwriting
        const prevMax = maxEndingHere;
        const prevMin = minEndingHere;
        
        // Calculate new max and min
        // A negative number can flip min to max and max to min
        maxEndingHere = Math.max(
            current,
            prevMax * current,
            prevMin * current
        );
        
        minEndingHere = Math.min(
            current,
            prevMax * current,
            prevMin * current
        );
        
        // Update global maximum
        globalMax = Math.max(globalMax, maxEndingHere);
    }
    
    return globalMax;
};