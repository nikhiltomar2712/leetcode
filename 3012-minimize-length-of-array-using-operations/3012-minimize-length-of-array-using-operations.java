class Solution {
    public int minimumArrayLength(int[] nums) {
        int min = nums[0];
        for (int num : nums) {
            if (num < min) {
                min = num;
            }
        }

        for (int num : nums) {
            if (num % min != 0) {
                return 1;
            }
        }

        int count = 0;
        for (int num : nums) {
            if (num == min) {
                count++;
            }
        }

        return (count + 1) / 2;
    }
}