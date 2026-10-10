class Solution {
    public long minSumSquareDiff(int[] nums1, int[] nums2, int k1, int k2) {
        int n = nums1.length;
        int[] diff = new int[n];
        int max = 0;
        for (int i = 0; i < n; i++) {
            diff[i] = Math.abs(nums1[i] - nums2[i]);
            max = Math.max(max, diff[i]);
        }
        long k = k1 + k2;
        long[] count = new long[max + 1];
        for (int d : diff) count[d]++;
        for (int i = max; i > 0 && k > 0; i--) {
            if (count[i] == 0) continue;
            long take = Math.min(k, count[i]);
            count[i] -= take;
            count[i - 1] += take;
            k -= take;
        }
        long res = 0;
        for (int i = 0; i <= max; i++) {
            if (count[i] > 0) {
                res += count[i] * (long) i * i;
            }
        }
        return res;
    }
}