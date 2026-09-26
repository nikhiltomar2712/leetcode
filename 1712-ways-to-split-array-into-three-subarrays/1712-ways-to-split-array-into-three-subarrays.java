class Solution {
    public int waysToSplit(int[] nums) {
        int MOD = 1_000_000_007;
        int n = nums.length;
        long[] prefix = new long[n + 1];
        for (int i = 0; i < n; i++) {
            prefix[i + 1] = prefix[i] + nums[i];
        }

        long total = prefix[n];
        long count = 0;

        for (int i = 0; i < n - 2; i++) {
            long left = prefix[i + 1];

            // Find smallest j such that mid >= left, where j is the end of mid (exclusive)
            int lo = i + 1, hi = n - 2;
            int jMin = -1;
            while (lo <= hi) {
                int mid = lo + (hi - lo) / 2;
                long midSum = prefix[mid + 1] - prefix[i + 1];
                if (midSum >= left) {
                    jMin = mid;
                    hi = mid - 1;
                } else {
                    lo = mid + 1;
                }
            }
            if (jMin == -1) continue;

            // Find largest j such that mid <= right
            lo = jMin;
            hi = n - 2;
            int jMax = -1;
            while (lo <= hi) {
                int mid = lo + (hi - lo) / 2;
                long midSum = prefix[mid + 1] - prefix[i + 1];
                long rightSum = total - prefix[mid + 1];
                if (midSum <= rightSum) {
                    jMax = mid;
                    lo = mid + 1;
                } else {
                    hi = mid - 1;
                }
            }

            if (jMax != -1) {
                count = (count + jMax - jMin + 1) % MOD;
            }
        }

        return (int) count;
    }
}