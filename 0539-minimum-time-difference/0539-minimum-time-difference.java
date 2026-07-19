class Solution {
    public int findMinDifference(List<String> timePoints) {
        int n = timePoints.size();
        boolean[] minutes = new boolean[24 * 60];
        int first = Integer.MAX_VALUE;
        
        for (String time : timePoints) {
            int mins = parseTime(time);
            if (minutes[mins]) {
                return 0; // Duplicate time
            }
            minutes[mins] = true;
            first = Math.min(first, mins);
        }
        
        int minDiff = Integer.MAX_VALUE;
        int prev = first;
        
        for (int i = first + 1; i < 24 * 60; i++) {
            if (minutes[i]) {
                minDiff = Math.min(minDiff, i - prev);
                prev = i;
            }
        }
        
        // Check circular difference
        minDiff = Math.min(minDiff, 24 * 60 - prev + first);
        
        return minDiff;
    }
    
    private int parseTime(String time) {
        String[] parts = time.split(":");
        int hours = Integer.parseInt(parts[0]);
        int mins = Integer.parseInt(parts[1]);
        return hours * 60 + mins;
    }
}