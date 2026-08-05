class Solution {
    public boolean splitArraySameAverage(int[] nums) {
        int n = nums.length;
        if (n == 1) return false;

        int s = 0;
        for (int v : nums) s += v;

        // Transform: nums[i] = nums[i]*n - s
        for (int i = 0; i < n; i++) {
            nums[i] = nums[i] * n - s;
        }

        int m = n / 2;               // left half size
        Set<Integer> leftSums = new HashSet<>();

        // Enumerate all non-empty subsets of the left half
        for (int mask = 1; mask < (1 << m); mask++) {
            int sum = 0;
            for (int j = 0; j < m; j++) {
                if ((mask & (1 << j)) != 0) {
                    sum += nums[j];
                }
            }
            if (sum == 0) return true;   // whole subset already has sum 0
            leftSums.add(sum);
        }

        // Enumerate all non-empty subsets of the right half
        int rightSize = n - m;
        for (int mask = 1; mask < (1 << rightSize); mask++) {
            int sum = 0;
            for (int j = 0; j < rightSize; j++) {
                if ((mask & (1 << j)) != 0) {
                    sum += nums[m + j];
                }
            }
            if (sum == 0) return true;                     // pure right subset
            // Avoid taking the whole array (would leave B empty)
            if (mask != (1 << rightSize) - 1 && leftSums.contains(-sum)) {
                return true;
            }
        }
        return false;
    }
}