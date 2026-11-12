class Solution {
    public long minimumMoves(int[] nums, int k, int maxChanges) {
        List<Integer> ones = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 1) {
                ones.add(i);
            }
        }

        int m = ones.size();
        if (m == 0) {
            return 2L * k;
        }

        long[] pref = new long[m + 1];
        for (int i = 0; i < m; i++) {
            pref[i + 1] = pref[i] + ones.get(i);
        }

        long ans = Long.MAX_VALUE;
        int minSize = Math.max(0, k - maxChanges);
        int maxSize = Math.min(k, Math.min(minSize + 3, m));

        for (int size = minSize; size <= maxSize; size++) {
            if (size == 0) {
                ans = Math.min(ans, 2L * k);
                continue;
            }

            for (int l = 0; l + size <= m; l++) {
                int r = l + size - 1;
                int mid = (l + r) / 2;

                long leftCost = (long) ones.get(mid) * (mid - l) - (pref[mid] - pref[l]);
                long rightCost = (pref[r + 1] - pref[mid + 1]) - (long) ones.get(mid) * (r - mid);

                long totalCost = leftCost + rightCost + 2L * (k - size);
                ans = Math.min(ans, totalCost);
            }
        }

        return ans;
    }
}