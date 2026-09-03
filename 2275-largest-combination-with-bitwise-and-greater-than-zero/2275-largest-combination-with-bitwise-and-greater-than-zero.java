class Solution {
    public int largestCombination(int[] candidates) {
        int maxCount = 0;
        
        // Check each of the 24 possible bits (since candidates[i] ≤ 10^7)
        for (int bit = 0; bit < 24; bit++) {
            int count = 0;
            int mask = 1 << bit;
            
            for (int num : candidates) {
                if ((num & mask) != 0) {
                    count++;
                }
            }
            
            maxCount = Math.max(maxCount, count);
        }
        
        return maxCount;
    }
}