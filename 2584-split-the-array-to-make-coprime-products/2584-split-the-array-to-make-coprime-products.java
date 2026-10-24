class Solution {
    public int findValidSplit(int[] nums) {
        int n = nums.length;
        Map<Integer, Integer> lastIndex = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int x = nums[i];
            for (int p = 2; p * p <= x; p++) {
                if (x % p == 0) {
                    lastIndex.put(p, i);
                    while (x % p == 0) x /= p;
                }
            }
            if (x > 1) lastIndex.put(x, i);
        }
        int maxReach = 0;
        for (int i = 0; i < n - 1; i++) {
            int x = nums[i];
            for (int p = 2; p * p <= x; p++) {
                if (x % p == 0) {
                    maxReach = Math.max(maxReach, lastIndex.get(p));
                    while (x % p == 0) x /= p;
                }
            }
            if (x > 1) maxReach = Math.max(maxReach, lastIndex.get(x));
            if (maxReach == i) return i;
        }
        return -1;
    }
}