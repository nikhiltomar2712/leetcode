import java.util.*;

class Solution {
    public int maxResult(int[] nums, int k) {
        int n = nums.length;
        int[] dp = new int[n];
        dp[0] = nums[0];
        
        // Deque stores indices, ordered by dp value decreasing
        Deque<Integer> dq = new ArrayDeque<>();
        dq.offer(0);
        
        for (int i = 1; i < n; i++) {
            // Remove indices outside the jump window [i-k, i-1]
            while (!dq.isEmpty() && dq.peekFirst() < i - k) {
                dq.pollFirst();
            }
            
            // Best previous score is at the front
            dp[i] = nums[i] + dp[dq.peekFirst()];
            
            // Maintain decreasing order: remove smaller dp values from back
            while (!dq.isEmpty() && dp[dq.peekLast()] <= dp[i]) {
                dq.pollLast();
            }
            dq.offer(i);
        }
        
        return dp[n - 1];
    }
}