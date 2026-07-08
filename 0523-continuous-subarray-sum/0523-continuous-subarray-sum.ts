function checkSubarraySum(nums: number[], k: number): boolean {
    if (nums.length < 2) return false;
    
    const prefixMod = new Map<number, number>();
    prefixMod.set(0, -1); // Important for subarray starting from index 0
    
    let sum = 0;
    
    for (let i = 0; i < nums.length; i++) {
        sum += nums[i];
        const mod = k === 0 ? sum : sum % k; // Handle k=0 separately if needed, but constraints k >=1 usually
        
        if (prefixMod.has(mod)) {
            if (i - prefixMod.get(mod)! >= 2) {
                return true;
            }
        } else {
            prefixMod.set(mod, i);
        }
    }
    
    return false;
}