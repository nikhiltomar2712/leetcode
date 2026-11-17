class Solution {
    public long numberOfPairs(int[] nums1, int[] nums2, int k) {
        Map<Integer, Integer> count2 = new HashMap<>();
        for (int num : nums2) {
            count2.merge(num * k, 1, Integer::sum);
        }
        long res = 0;
        for (int num : nums1) {
            for (int d = 1; (long) d * d <= num; d++) {
                if (num % d == 0) {
                    res += count2.getOrDefault(d, 0);
                    if (d != num / d) {
                        res += count2.getOrDefault(num / d, 0);
                    }
                }
            }
        }
        return res;
    }
}