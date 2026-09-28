class Solution {
    public int minAbsoluteSumDiff(int[] nums1, int[] nums2) {
        int mod = 1000000007;
        int n = nums1.length;
        int[] sorted = nums1.clone();
        Arrays.sort(sorted);
        long total = 0;
        int maxDiff = 0;
        for (int i = 0; i < n; i++) {
            int diff = Math.abs(nums1[i] - nums2[i]);
            total += diff;
            int pos = Arrays.binarySearch(sorted, nums2[i]);
            if (pos < 0) pos = -pos - 1;
            if (pos < n) maxDiff = Math.max(maxDiff, diff - Math.abs(sorted[pos] - nums2[i]));
            if (pos > 0) maxDiff = Math.max(maxDiff, diff - Math.abs(sorted[pos - 1] - nums2[i]));
        }
        return (int) ((total - maxDiff) % mod);
    }
}