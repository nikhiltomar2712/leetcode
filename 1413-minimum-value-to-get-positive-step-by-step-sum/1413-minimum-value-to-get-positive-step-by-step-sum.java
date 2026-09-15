class Solution {
    public int minStartValue(int[] nums) {
        int lo = 1, hi = 10001;
        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            if (canReach(nums, mid)) hi = mid;
            else lo = mid + 1;
        }
        return lo;
    }

    private boolean canReach(int[] nums, int start) {
        int sum = start;
        for (int num : nums) {
            sum += num;
            if (sum < 1) return false;
        }
        return true;
    }
}