class Solution {
    public int maxScoreSightseeingPair(int[] values) {
        int maxScore = 0;
        int maxLeft = values[0]; // values[0] + 0

        for (int j = 1; j < values.length; j++) {
            // Current best pair ending at j
            maxScore = Math.max(maxScore, maxLeft + values[j] - j);
            // Update the best left contribution for future j's
            maxLeft = Math.max(maxLeft, values[j] + j);
        }
        return maxScore;
    }
}
