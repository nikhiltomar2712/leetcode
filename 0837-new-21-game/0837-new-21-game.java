class Solution {
    public double new21Game(int n, int k, int maxPts) {
        // If k == 0, Alice already has 0 >= 0, so she stops with 0 points, which is <= n.
        if (k == 0 || n >= k + maxPts - 1) return 1.0;
        
        double[] dp = new double[n + 1];
        dp[0] = 1.0; // probability of starting with 0 points
        double windowSum = 1.0; // sum of dp[j] for j in current window [i - maxPts, i - 1]
        double answer = 0.0;
        
        for (int i = 1; i <= n; i++) {
            // dp[i] is the probability of reaching exactly i points
            dp[i] = windowSum / maxPts;
            
            // If i >= k, Alice stops here, so this probability contributes to the answer.
            if (i >= k) {
                answer += dp[i];
            }
            
            // Update windowSum for the next i (i+1)
            // Add dp[i] only if i < k, because scores >= k do not continue drawing.
            if (i < k) {
                windowSum += dp[i];
            }
            
            // Remove the score that is now out of the window (i - maxPts + 1)
            // Only remove if that score was < k and we had added it previously.
            if (i - maxPts >= 0 && i - maxPts < k) {
                windowSum -= dp[i - maxPts];
            }
        }
        
        return answer;
    }
}