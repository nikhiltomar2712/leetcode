class Solution {
    public int sumImbalanceNumbers(int[] nums) {
        int n = nums.length;
        int res = 0;
        for (int i = 0; i < n; i++) {
            Set<Integer> set = new HashSet<>();
            set.add(nums[i]);
            int imbalance = 0;
            for (int j = i + 1; j < n; j++) {
                if (!set.contains(nums[j])) {
                    if (set.contains(nums[j] - 1) && set.contains(nums[j] + 1)) {
                        imbalance--;
                    } else if (!set.contains(nums[j] - 1) && !set.contains(nums[j] + 1)) {
                        imbalance++;
                    }
                    set.add(nums[j]);
                }
                res += imbalance;
            }
        }
        return res;
    }
}