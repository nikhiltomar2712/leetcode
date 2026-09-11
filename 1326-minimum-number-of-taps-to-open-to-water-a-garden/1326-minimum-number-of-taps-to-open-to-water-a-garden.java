class Solution {
    public int minTaps(int n, int[] ranges) {
        // maxReach[i] = farthest right point reachable by a tap
        // whose interval starts at or before i
        int[] maxReach = new int[n + 1];
        
        for (int i = 0; i <= n; i++) {
            int left = Math.max(0, i - ranges[i]);
            int right = Math.min(n, i + ranges[i]);
            maxReach[left] = Math.max(maxReach[left], right);
        }
        
        int taps = 0;
        int currentEnd = 0;   // current coverage frontier [0, currentEnd]
        int farthest = 0;     // farthest we can extend in this "level"
        int i = 0;
        
        while (currentEnd < n) {
            // Among all taps starting at or before currentEnd,
            // find the one extending coverage the farthest
            while (i <= currentEnd && i <= n) {
                farthest = Math.max(farthest, maxReach[i]);
                i++;
            }
            
            // If we can't extend coverage, garden can't be fully watered
            if (farthest <= currentEnd) {
                return -1;
            }
            
            currentEnd = farthest;
            taps++;
        }
        
        return taps;
    }
}