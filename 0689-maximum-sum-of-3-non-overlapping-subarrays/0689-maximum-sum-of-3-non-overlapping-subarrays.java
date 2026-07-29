class Solution {
    public int[] maxSumOfThreeSubarrays(int[] nums, int k) {
        int n = nums.length - k + 1;          // number of possible windows
        int[] sums = new int[n];

        // 1. Compute all window sums of length k
        int windowSum = 0;
        for (int i = 0; i < nums.length; i++) {
            windowSum += nums[i];
            if (i >= k) windowSum -= nums[i - k];
            if (i >= k - 1) sums[i - k + 1] = windowSum;
        }

        // 2. left[i] = best window index in [0..i]
        int[] left = new int[n];
        int best = 0;
        for (int i = 0; i < n; i++) {
            if (sums[i] > sums[best]) best = i;
            left[i] = best;
        }

        // 3. right[i] = best window index in [i..n-1]
        int[] right = new int[n];
        best = n - 1;
        for (int i = n - 1; i >= 0; i--) {
            if (sums[i] >= sums[best]) best = i;  // >= for lexicographical order
            right[i] = best;
        }

        // 4. Enumerate middle window
        int[] ans = {-1, -1, -1};
        for (int mid = k; mid + k < n; mid++) {
            int l = left[mid - k];
            int r = right[mid + k];
            if (ans[0] == -1 ||
                sums[l] + sums[mid] + sums[r] > sums[ans[0]] + sums[ans[1]] + sums[ans[2]]) {
                ans[0] = l;
                ans[1] = mid;
                ans[2] = r;
            }
        }
        return ans;
    }
}