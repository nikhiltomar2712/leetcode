class Solution {
    public long maxScore(int[] points, int m) {
        long lo = 0;
        long hi = (long) m * Arrays.stream(points).max().getAsInt(); // safe upper bound

        while (lo < hi) {
            long mid = lo + (hi - lo + 1) / 2;
            if (isPossible(points, mid, m)) {
                lo = mid;
            } else {
                hi = mid - 1;
            }
        }
        return lo;
    }

    // Returns true if every gameScore[i] can be ≥ minVal with ≤ m moves
    private boolean isPossible(int[] points, long minVal, long m) {
        long moves = 0;
        long prevExtra = 0;          // visits already “paid” that can be used by the next index

        for (int i = 0; i < points.length; i++) {
            // how many visits does this index still need?
            long need = (minVal + points[i] - 1) / points[i]; // ceil(minVal / points[i])
            long cur = Math.max(0, need - prevExtra);

            if (cur > 0) {
                // go to i, do (cur-1) round-trips, then leave to the right (or stay if last)
                moves += 2 * cur - 1;
                prevExtra = cur - 1;   // one visit is “left behind” for the next index
            } else {
                // we already have enough visits from previous detours
                if (i + 1 < points.length) {
                    moves += 1;       // just walk to the next index
                }
                prevExtra = 0;
            }

            if (moves > m) return false;
        }
        return true;
    }
}