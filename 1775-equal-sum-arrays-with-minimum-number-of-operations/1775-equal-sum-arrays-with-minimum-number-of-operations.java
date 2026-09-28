class Solution {
    public int minOperations(int[] nums1, int[] nums2) {
        int sum1 = 0, sum2 = 0;
        for (int x : nums1) sum1 += x;
        for (int x : nums2) sum2 += x;
        if (sum1 == sum2) return 0;
        if (sum1 > sum2) return minOperations(nums2, nums1);
        int diff = sum2 - sum1;
        int[] freq = new int[6];
        for (int x : nums1) freq[6 - x]++;
        for (int x : nums2) freq[x - 1]++;
        int ops = 0;
        for (int i = 5; i >= 1; i--) {
            while (freq[i] > 0) {
                diff -= i;
                ops++;
                freq[i]--;
                if (diff <= 0) return ops;
            }
        }
        return -1;
    }
}