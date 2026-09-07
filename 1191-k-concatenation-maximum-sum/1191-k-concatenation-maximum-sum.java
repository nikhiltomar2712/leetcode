class Solution {
    public int kConcatenationMaxSum(int[] arr, int k) {
        final int MOD = 1_000_000_007;
        
        long totalSum = 0;
        long maxPrefix = 0;
        long minPrefix = 0;
        long maxSubarray = 0;   // classic Kadane result
        
        long currentSum = 0;
        for (int x : arr) {
            currentSum += x;
            totalSum += x;
            
            maxPrefix = Math.max(maxPrefix, currentSum);
            minPrefix = Math.min(minPrefix, currentSum);
            maxSubarray = Math.max(maxSubarray, currentSum - minPrefix);
        }
        
        // Case 1: k == 1
        if (k == 1) {
            return (int) (maxSubarray % MOD);
        }
        
        // Maximum suffix sum = totalSum - minimum prefix sum
        long maxSuffix = totalSum - minPrefix;
        
        // Case 2: maximum subarray spans at most two copies
        long ans = Math.max(maxSubarray, maxPrefix + maxSuffix);
        
        // Case 3: totalSum > 0 → we can take (k-2) full copies in the middle
        if (totalSum > 0) {
            ans = Math.max(ans, maxPrefix + maxSuffix + (k - 2) * totalSum);
        }
        
        return (int) (ans % MOD);
    }
}