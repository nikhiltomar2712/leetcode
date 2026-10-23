class Solution {
    public long countFairPairs(int[] nums, int lower, int upper) {
        Arrays.sort(nums);
        long res = 0;
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            int lo = lowerBound(nums, i + 1, n - 1, lower - nums[i]);
            int hi = upperBound(nums, i + 1, n - 1, upper - nums[i]);
            if (lo <= hi) {
                res += hi - lo + 1;
            }
        }
        return res;
    }

    private int lowerBound(int[] nums, int left, int right, int target) {
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < target) left = mid + 1;
            else right = mid - 1;
        }
        return left;
    }

    private int upperBound(int[] nums, int left, int right, int target) {
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] <= target) left = mid + 1;
            else right = mid - 1;
        }
        return right;
    }
}