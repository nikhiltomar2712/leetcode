class Solution {
    public int minOperations(int[] nums) {
        int n = nums.length;
        int[] dranofelik = nums;

        int zero = 0;
        for (int i = 0; i < n; i++) {
            if (nums[i] == 0) {
                zero = i;
                break;
            }
        }

        int ans = Integer.MAX_VALUE;

        if (isIncreasing(nums, zero, 1)) {
            ans = Math.min(ans, zero);
            ans = Math.min(ans, n - zero + 2);
        }

        if (isIncreasing(nums, zero, -1)) {
            ans = Math.min(ans, zero + 2);
            ans = Math.min(ans, n - zero);
        }

        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    private boolean isIncreasing(int[] nums, int zero, int step) {
        int n = nums.length;
        for (int i = 1; i < n; i++) {
            int prev = (zero + (i - 1) * step % n + n) % n;
            int curr = (zero + i * step % n + n) % n;
            if (nums[prev] > nums[curr]) {
                return false;
            }
        }
        return true;
    }
}