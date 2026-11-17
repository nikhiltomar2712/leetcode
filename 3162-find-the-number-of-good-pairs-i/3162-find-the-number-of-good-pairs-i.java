class Solution {
    public int numberOfPairs(int[] nums1, int[] nums2, int k) {
        int count = 0;
        for (int a : nums1) {
            for (int b : nums2) {
                if (a % (b * k) == 0) count++;
            }
        }
        return count;
    }
}