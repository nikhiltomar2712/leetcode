class Solution {
    public long minSum(int[] nums1, int[] nums2) {
        long sum1 = 0, sum2 = 0;
        int zero1 = 0, zero2 = 0;
        for (int num : nums1) {
            if (num == 0) zero1++;
            else sum1 += num;
        }
        for (int num : nums2) {
            if (num == 0) zero2++;
            else sum2 += num;
        }
        long min1 = sum1 + zero1;
        long min2 = sum2 + zero2;
        if (zero1 == 0 && zero2 == 0) {
            return sum1 == sum2 ? sum1 : -1;
        }
        if (zero1 == 0) {
            return sum1 >= min2 ? sum1 : -1;
        }
        if (zero2 == 0) {
            return sum2 >= min1 ? sum2 : -1;
        }
        return Math.max(min1, min2);
    }
}