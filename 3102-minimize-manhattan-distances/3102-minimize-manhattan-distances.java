class Solution {
    public int minimumDistance(int[][] points) {
        // Find the two indices that produce the maximum Manhattan distance
        int[] maxPair = maxManhattanDistance(points, -1);

        // Try removing each of the two candidates and compute the new max
        int[] afterFirst = maxManhattanDistance(points, maxPair[0]);
        int[] afterSecond = maxManhattanDistance(points, maxPair[1]);

        int dist1 = manhattan(points, afterFirst[0], afterFirst[1]);
        int dist2 = manhattan(points, afterSecond[0], afterSecond[1]);

        return Math.min(dist1, dist2);
    }

    // Returns indices {i, j} of the pair with maximum Manhattan distance,
    // excluding the point at excludedIndex (pass -1 to exclude none).
    private int[] maxManhattanDistance(int[][] points, int excludedIndex) {
        int minSum = Integer.MAX_VALUE, maxSum = Integer.MIN_VALUE;
        int minDiff = Integer.MAX_VALUE, maxDiff = Integer.MIN_VALUE;
        int minSumIdx = -1, maxSumIdx = -1;
        int minDiffIdx = -1, maxDiffIdx = -1;

        for (int i = 0; i < points.length; i++) {
            if (i == excludedIndex) continue;

            int sum = points[i][0] + points[i][1];
            int diff = points[i][0] - points[i][1];

            if (sum < minSum) { minSum = sum; minSumIdx = i; }
            if (sum > maxSum) { maxSum = sum; maxSumIdx = i; }
            if (diff < minDiff) { minDiff = diff; minDiffIdx = i; }
            if (diff > maxDiff) { maxDiff = diff; maxDiffIdx = i; }
        }

        // The maximum distance is either (maxSum - minSum) or (maxDiff - minDiff)
        if (maxSum - minSum >= maxDiff - minDiff) {
            return new int[]{minSumIdx, maxSumIdx};
        } else {
            return new int[]{minDiffIdx, maxDiffIdx};
        }
    }

    private int manhattan(int[][] points, int i, int j) {
        return Math.abs(points[i][0] - points[j][0]) + Math.abs(points[i][1] - points[j][1]);
    }
}