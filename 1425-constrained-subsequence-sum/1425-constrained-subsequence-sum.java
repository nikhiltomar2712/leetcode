import java.util.*;

class Solution {
    public int constrainedSubsetSum(int[] nums, int k) {
        int n = nums.length;
        int[] dp = new int[n];
        Deque<Integer> deque = new ArrayDeque<>(); // stores indices, dp decreasing
        int answer = Integer.MIN_VALUE;

        for (int i = 0; i < n; i++) {
            // Remove indices out of the window [i-k, i-1]
            while (!deque.isEmpty() && deque.peekFirst() < i - k) {
                deque.pollFirst();
            }

            // Best previous dp in window (or 0 to start fresh)
            int bestPrev = deque.isEmpty() ? 0 : Math.max(0, dp[deque.peekFirst()]);
            dp[i] = nums[i] + bestPrev;

            // Maintain decreasing order of dp in deque
            while (!deque.isEmpty() && dp[deque.peekLast()] <= dp[i]) {
                deque.pollLast();
            }
            deque.offerLast(i);

            answer = Math.max(answer, dp[i]);
        }

        return answer;
    }
}