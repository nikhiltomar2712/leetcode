import java.util.Arrays;

class Solution {
    public long maximumBeauty(int[] flowers, long newFlowers, int target, int full, int partial) {
        int n = flowers.length;
        long[] f = new long[n];
        for (int i = 0; i < n; i++) f[i] = Math.min(flowers[i], target);
        Arrays.sort(f);

        long[] prefix = new long[n + 1];
        for (int i = 0; i < n; i++) prefix[i + 1] = prefix[i] + f[i];

        // Gardens already at target are automatically complete — we may never "un-complete" them.
        int pre = 0;
        for (int i = 0; i < n; i++) if (f[i] == target) pre++;

        long best = 0;
        long completeCost = 0;

        // k = size of the complete pool (last k gardens). Must be >= pre.
        for (int k = pre; k <= n; k++) {
            if (k > pre) completeCost += target - f[n - k];   // cost to complete garden index n-k
            long rem = newFlowers - completeCost;
            if (rem < 0) break;

            int m = n - k;                    // incomplete pool = first m gardens (all < target)
            long bestMin = 0;
            if (m > 0) {
                long lo = 0, hi = target - 1; // min must stay < target, else it's complete
                while (lo < hi) {
                    long mid = (lo + hi + 1) >>> 1;
                    int p = lowerBound(f, 0, m, mid); // first index in [0,m) with f[i] >= mid
                    long cost = mid * p - prefix[p];  // cost to lift first p gardens to mid
                    if (cost <= rem) lo = mid;
                    else hi = mid - 1;
                }
                bestMin = lo;
            }
            best = Math.max(best, (long) k * full + bestMin * partial);
        }
        return best;
    }

    // first index in [from, to) with f[i] >= val
    private int lowerBound(long[] f, int from, int to, long val) {
        int lo = from, hi = to;
        while (lo < hi) {
            int mid = (lo + hi) >>> 1;
            if (f[mid] < val) lo = mid + 1;
            else hi = mid;
        }
        return lo;
    }
}