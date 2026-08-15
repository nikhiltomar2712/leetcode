class Solution {
    public int shortestSubarray(int[] nums, int k) {
        int n = nums.length;
        
        // prefix[i] = sum of first i elements (prefix[0] = 0)
        long[] prefix = new long[n + 1];
        for (int i = 0; i < n; i++) {
            prefix[i + 1] = prefix[i] + nums[i];
        }
        
        // Monotonic increasing deque of indices
        Deque<Integer> dq = new ArrayDeque<>();
        int ans = n + 1;
        
        for (int i = 0; i <= n; i++) {
            // While the front of the deque gives a valid subarray
            // (prefix[i] - prefix[dq.front()] >= k), update answer and remove it
            while (!dq.isEmpty() && prefix[i] - prefix[dq.peekFirst()] >= k) {
                ans = Math.min(ans, i - dq.pollFirst());
            }
            
            // Maintain the deque in increasing order of prefix sums
            // (remove indices with larger or equal prefix sums from the back)
            while (!dq.isEmpty() && prefix[dq.peekLast()] >= prefix[i]) {
                dq.pollLast();
            }
            
            dq.offerLast(i);
        }
        
        return ans > n ? -1 : ans;
    }
}