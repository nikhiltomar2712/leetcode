function findMaxLength(nums: number[]): number {
    const prefixMap = new Map<number, number>();
    prefixMap.set(0, -1);
    
    let maxLen = 0;
    let sum = 0;
    
    for (let i = 0; i < nums.length; i++) {
        sum += nums[i] === 1 ? 1 : -1;
        
        if (prefixMap.has(sum)) {
            maxLen = Math.max(maxLen, i - prefixMap.get(sum)!);
        } else {
            prefixMap.set(sum, i);
        }
    }
    
    return maxLen;
}