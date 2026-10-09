class Solution {
    public int largestCombination(int[] candidates) {
        int res = 0;
        for (int bit = 0; bit < 32; bit++) {
            int count = 0;
            for (int num : candidates) {
                if ((num >> bit & 1) == 1) count++;
            }
            res = Math.max(res, count);
        }
        return res;
    }
}