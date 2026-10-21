class Solution {
    public int minCost(int[] nums, int k) {
        int n = nums.length;
        // dp[i] = min cost to split prefix nums[0...i-1]
        int[] dp = new int[n + 1];
        
        for (int i = 1; i <= n; i++) {
            dp[i] = Integer.MAX_VALUE;
            // Frequency array (nums[i] < n per constraints)
            int[] freq = new int[n];
            int trimmedLen = 0;
            
            // Extend the last subarray backward from i-1 down to j
            for (int j = i - 1; j >= 0; j--) {
                int val = nums[j];
                freq[val]++;
                
                // Update trimmed length based on new frequency
                if (freq[val] == 2) {
                    trimmedLen += 2;
                } else if (freq[val] > 2) {
                    trimmedLen += 1;
                }
                
                // Cost = previous split cost + k + trimmed length of current subarray
                int currentCost = dp[j] + k + trimmedLen;
                dp[i] = Math.min(dp[i], currentCost);
            }
        }
        
        return dp[n];
    }
}