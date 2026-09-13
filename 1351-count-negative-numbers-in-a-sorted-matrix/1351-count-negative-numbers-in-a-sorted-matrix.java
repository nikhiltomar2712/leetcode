class Solution {
    public int countNegatives(int[][] grid) {
        int count = 0;
        for (int[] row : grid) {
            // Find first index where value is negative
            int lo = 0, hi = row.length;
            while (lo < hi) {
                int mid = lo + (hi - lo) / 2;
                if (row[mid] < 0) {
                    hi = mid;        // negative, search left
                } else {
                    lo = mid + 1;    // non-negative, search right
                }
            }
            // lo is the first negative index; all after it are negative
            count += row.length - lo;
        }
        return count;
    }
}