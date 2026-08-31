class Solution {
    public int maxSumTwoNoOverlap(int[] nums, int firstLen, int secondLen) {
        return Math.max(maxSum(nums, firstLen, secondLen), 
                        maxSum(nums, secondLen, firstLen));
    }
    
    private int maxSum(int[] nums, int L, int M) {
        int n = nums.length;
        int[] prefix = new int[n + 1];
        
        // Build prefix sum array
        for (int i = 0; i < n; i++) {
            prefix[i + 1] = prefix[i] + nums[i];
        }
        
        int maxL = 0;  // Maximum sum of any L-length subarray seen so far
        int res = 0;
        
        // Enumerate the ending position of the M-length subarray
        for (int i = L + M; i <= n; i++) {
            // Update max sum of L-length subarray that ends before the current M-window
            maxL = Math.max(maxL, prefix[i - M] - prefix[i - M - L]);
            // Current M-window sum + best previous L-window
            res = Math.max(res, maxL + prefix[i] - prefix[i - M]);
        }
        
        return res;
    }
}