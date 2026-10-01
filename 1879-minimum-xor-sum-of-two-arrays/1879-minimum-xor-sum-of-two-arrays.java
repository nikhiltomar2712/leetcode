class Solution {
    public int minimumXORSum(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int[] dp = new int[1 << n];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for (int mask = 0; mask < (1 << n); mask++) {
            int i = Integer.bitCount(mask);
            if (i >= n) continue;
            for (int j = 0; j < n; j++) {
                if ((mask & (1 << j)) != 0) continue;
                int newMask = mask | (1 << j);
                dp[newMask] = Math.min(dp[newMask], dp[mask] + (nums1[i] ^ nums2[j]));
            }
        }
        return dp[(1 << n) - 1];
    }
}