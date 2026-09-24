import java.util.*;

class Solution {
    public int maxProfit(int[] inventory, int orders) {
        int MOD = 1_000_000_007;
        int maxVal = 0;
        for (int v : inventory) maxVal = Math.max(maxVal, v);

        // Binary search for threshold: smallest k where balls > k <= orders
        int lo = 0, hi = maxVal;
        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            long count = 0;
            for (int v : inventory) {
                if (v > mid) count += v - mid;
            }
            if (count <= orders) {
                hi = mid;      // valid, try lower threshold
            } else {
                lo = mid + 1;  // too many, raise threshold
            }
        }

        int threshold = lo;
        long total = 0;
        long remaining = orders;

        // Sell all balls with value > threshold
        for (int v : inventory) {
            if (v > threshold) {
                long cnt = v - threshold;
                // Sum from (threshold+1) to v
                long sum = (long)(v + threshold + 1) * cnt / 2;
                total = (total + sum % MOD) % MOD;
                remaining -= cnt;
            }
        }

        // Sell remaining orders at value = threshold
        total = (total + remaining * threshold % MOD) % MOD;
        return (int) total;
    }
}