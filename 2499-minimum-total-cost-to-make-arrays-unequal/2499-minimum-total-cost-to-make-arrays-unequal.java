class Solution {
    public long minimumTotalCost(int[] nums1, int[] nums2) {
        int n = nums1.length;
        Map<Integer, Integer> count = new HashMap<>();
        int same = 0;
        long cost = 0;
        for (int i = 0; i < n; i++) {
            if (nums1[i] == nums2[i]) {
                same++;
                count.merge(nums1[i], 1, Integer::sum);
                cost += i;
            }
        }
        int maxFreq = 0, maxVal = -1;
        for (Map.Entry<Integer, Integer> e : count.entrySet()) {
            if (e.getValue() > maxFreq) {
                maxFreq = e.getValue();
                maxVal = e.getKey();
            }
        }
        int needed = Math.max(0, 2 * maxFreq - same);
        for (int i = 0; i < n && needed > 0; i++) {
            if (nums1[i] != nums2[i] && nums1[i] != maxVal && nums2[i] != maxVal) {
                cost += i;
                needed--;
            }
        }
        if (needed > 0) return -1;
        return cost;
    }
}