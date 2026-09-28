class Solution {
    public int minimumSize(int[] nums, int maxOperations) {
        int left = 1, right = 0;
        for (int num : nums) right = Math.max(right, num);
        while (left < right) {
            int mid = left + (right - left) / 2;
            int ops = 0;
            for (int num : nums) {
                ops += (num - 1) / mid;
            }
            if (ops <= maxOperations) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
}