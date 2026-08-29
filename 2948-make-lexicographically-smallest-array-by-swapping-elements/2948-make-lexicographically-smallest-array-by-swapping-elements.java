class Solution {
    public int[] lexicographicallySmallestArray(int[] nums, int limit) {
        int n = nums.length;
        Integer[] idx = new Integer[n];
        for (int i = 0; i < n; i++) {
            idx[i] = i;
        }
        // Sort indices by the corresponding nums values
        Arrays.sort(idx, (i, j) -> Integer.compare(nums[i], nums[j]));

        int[] ans = new int[n];
        for (int i = 0; i < n; ) {
            int j = i + 1;
            // Find the end of the current group (consecutive elements with diff <= limit)
            while (j < n && nums[idx[j]] - nums[idx[j - 1]] <= limit) {
                j++;
            }
            // Extract the indices of this group and sort them
            Integer[] groupIdx = Arrays.copyOfRange(idx, i, j);
            Arrays.sort(groupIdx);
            // Assign the sorted values (already in sorted order from idx) to the sorted positions
            for (int k = i; k < j; k++) {
                ans[groupIdx[k - i]] = nums[idx[k]];
            }
            i = j;
        }
        return ans;
    }
}