class Solution {
    public int removeCoveredIntervals(int[][] intervals) {
        // Sort by start ascending; if starts are equal, by end descending
        Arrays.sort(intervals, (a, b) -> {
            if (a[0] != b[0]) return a[0] - b[0];
            return b[1] - a[1];
        });
        
        int remaining = 0;
        int maxEnd = 0;
        
        for (int[] interval : intervals) {
            if (interval[1] > maxEnd) {
                // Not covered by any previous interval
                remaining++;
                maxEnd = interval[1];
            }
            // else it is covered → discard
        }
        
        return remaining;
    }
}