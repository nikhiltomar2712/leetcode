class Solution {
    public int findMaximumLength(int[] nums) {
        int n = nums.length;
        // Prefix sum array
        long[] prefixSum = new long[n + 1];
        for (int i = 1; i <= n; i++) {
            prefixSum[i] = prefixSum[i - 1] + nums[i - 1];
        }

        // dp[i]: Max length for prefix ending at i
        int[] dp = new int[n + 1];
        // last[i]: The minimum possible value of the last element for the optimal split at i
        long[] last = new long[n + 1];
        
        // Monotonic deque to store indices
        int[] deque = new int[n + 1];
        int head = 0, tail = 0;
        deque[tail++] = 0;

        for (int i = 1; i <= n; i++) {
            // Find the furthest valid split point j from the front
            while (head + 1 < tail) {
                int nextJ = deque[head + 1];
                if (prefixSum[nextJ] + last[nextJ] <= prefixSum[i]) {
                    head++;
                } else {
                    break;
                }
            }
            
            int j = deque[head];
            dp[i] = dp[j] + 1;
            last[i] = prefixSum[i] - prefixSum[j];

            // Maintain monotonicity: remove back elements that are less optimal
            long currentVal = prefixSum[i] + last[i];
            while (head < tail) {
                int backJ = deque[tail - 1];
                if (prefixSum[backJ] + last[backJ] >= currentVal) {
                    tail--;
                } else {
                    break;
                }
            }
            deque[tail++] = i;
        }
        
        return dp[n];
    }
}