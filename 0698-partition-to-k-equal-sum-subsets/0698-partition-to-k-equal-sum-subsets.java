class Solution {
    public boolean canPartitionKSubsets(int[] nums, int k) {
        int sum = 0;
        for (int num : nums) sum += num;
        if (sum % k != 0) return false;

        int target = sum / k;
        Arrays.sort(nums);                 // ascending
        // reverse to descending
        for (int i = 0, j = nums.length - 1; i < j; i++, j--) {
            int tmp = nums[i];
            nums[i] = nums[j];
            nums[j] = tmp;
        }

        if (nums[0] > target) return false;

        int[] buckets = new int[k];
        return dfs(nums, 0, buckets, target);
    }

    private boolean dfs(int[] nums, int idx, int[] buckets, int target) {
        if (idx == nums.length) return true;

        int num = nums[idx];
        for (int i = 0; i < buckets.length; i++) {
            // symmetry pruning
            if (i > 0 && buckets[i] == buckets[i - 1]) continue;

            if (buckets[i] + num <= target) {
                buckets[i] += num;
                if (dfs(nums, idx + 1, buckets, target)) return true;
                buckets[i] -= num;
            }

            // if this bucket is empty and we failed, no need to try later empty buckets
            if (buckets[i] == 0) break;
        }
        return false;
    }
}