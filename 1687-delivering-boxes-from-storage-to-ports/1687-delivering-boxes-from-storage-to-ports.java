class Solution {
    public int boxDelivering(int[][] boxes, int portsCount, int maxBoxes, int maxWeight) {
        int n = boxes.length;
        long[] prefixWeight = new long[n + 1];
        int[] prefixDiff = new int[n];   // prefixDiff[i] = number of port changes in boxes[0..i-1]

        for (int i = 0; i < n; i++) {
            prefixWeight[i + 1] = prefixWeight[i] + boxes[i][1];
            if (i < n - 1) {
                prefixDiff[i + 1] = prefixDiff[i] + (boxes[i][0] != boxes[i + 1][0] ? 1 : 0);
            }
        }

        int[] dp = new int[n + 1];       // dp[i] = min trips to deliver first i boxes
        Deque<Integer> q = new ArrayDeque<>();
        q.offer(0);                      // indices of candidate previous positions

        for (int i = 1; i <= n; i++) {
            // remove windows that violate maxBoxes or maxWeight
            while (!q.isEmpty() && 
                   (i - q.peekFirst() > maxBoxes || 
                    prefixWeight[i] - prefixWeight[q.peekFirst()] > maxWeight)) {
                q.pollFirst();
            }

            // best previous position is at the front of the deque
            if (!q.isEmpty()) {
                int j = q.peekFirst();
                // trips for the last shipment = (port changes in [j..i-1]) + 2 (go + return)
                dp[i] = dp[j] + (prefixDiff[i - 1] - prefixDiff[j]) + 2;
            }

            // maintain the monotonic queue for future states
            // we store positions ordered by increasing (dp[pos] - prefixDiff[pos])
            if (i < n) {
                while (!q.isEmpty() && 
                       dp[q.peekLast()] - prefixDiff[q.peekLast()] >= dp[i] - prefixDiff[i]) {
                    q.pollLast();
                }
                q.offer(i);
            }
        }
        return dp[n];
    }
}