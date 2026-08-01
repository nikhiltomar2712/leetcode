class Solution {
    public int intersectionSizeTwo(int[][] intervals) {
        // Sort by end ascending, if ends equal then by start descending
        Arrays.sort(intervals, (a, b) -> a[1] == b[1] ? b[0] - a[0] : a[1] - b[1]);
        
        int ans = 0;
        int p1 = -1, p2 = -1;   // the two largest points chosen so far
        
        for (int[] interval : intervals) {
            int start = interval[0];
            int end   = interval[1];
            
            if (start > p2) {
                // need two new points
                ans += 2;
                p1 = end - 1;
                p2 = end;
            } else if (start > p1) {
                // need one new point
                ans += 1;
                p1 = p2;
                p2 = end;
            }
            // else already covered by ≥ 2 points
        }
        
        return ans;
    }
}