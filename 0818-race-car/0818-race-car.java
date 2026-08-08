class Solution {
    public int racecar(int target) {
        int[] dp = new int[target + 1];
        
        for (int i = 1; i <= target; i++) {
            // smallest k where (1 << k) - 1 >= i
            int k = 32 - Integer.numberOfLeadingZeros(i);
            
            // Case 1: i is exactly 2^k - 1 → pure AAAA...
            if (i == (1 << k) - 1) {
                dp[i] = k;
                continue;
            }
            
            // Case 2: go past i to 2^k - 1, reverse, then solve remaining
            dp[i] = dp[(1 << k) - 1 - i] + k + 1;
            
            // Case 3: go to 2^{k-1}-1, reverse, accelerate j times, reverse again
            for (int j = 0; j < k; j++) {
                int remain = i - ((1 << (k - 1)) - (1 << j));
                dp[i] = Math.min(dp[i], dp[remain] + (k - 1) + j + 2);
            }
        }
        
        return dp[target];
    }
}