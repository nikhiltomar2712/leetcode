class Solution {
    public long minimumOperations(int[] nums, int[] target) {
        int n = nums.length;
        long res = 0;
        long prev = 0;
        for (int i = 0; i < n; i++) {
            long diff = target[i] - nums[i];
            if (diff >= 0) {
                if (prev < 0) {
                    res += diff;
                } else if (diff > prev) {
                    res += diff - prev;
                }
            } else {
                if (prev > 0) {
                    res += -diff;
                } else if (diff < prev) {
                    res += prev - diff;
                }
            }
            prev = diff;
        }
        return res;
    }
}