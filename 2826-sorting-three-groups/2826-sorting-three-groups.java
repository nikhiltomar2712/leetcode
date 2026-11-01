class Solution {
    public int minimumOperations(List<Integer> nums) {
        // dp[i] = longest non-decreasing subsequence ending with numbers ≤ i
        int[] dp = new int[4];
        
        for (int num : nums) {
            dp[num]++;                          // append current number
            dp[2] = Math.max(dp[2], dp[1]);     // best sequence ending with ≤ 2
            dp[3] = Math.max(dp[3], dp[2]);     // best sequence ending with ≤ 3
        }
        
        return nums.size() - dp[3];             // min removals = n - longest valid subsequence
    }
}