class Solution {
    public int[] minDifference(int[] nums, int[][] queries) {
        int n = nums.length;
        int[][] prefix = new int[n + 1][101];
        for (int i = 0; i < n; i++) {
            for (int j = 1; j <= 100; j++) {
                prefix[i + 1][j] = prefix[i][j];
            }
            prefix[i + 1][nums[i]]++;
        }
        int[] res = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int l = queries[i][0], r = queries[i][1];
            int prev = -1, minDiff = Integer.MAX_VALUE;
            for (int j = 1; j <= 100; j++) {
                if (prefix[r + 1][j] - prefix[l][j] > 0) {
                    if (prev != -1) {
                        minDiff = Math.min(minDiff, j - prev);
                    }
                    prev = j;
                }
            }
            res[i] = minDiff == Integer.MAX_VALUE ? -1 : minDiff;
        }
        return res;
    }
}