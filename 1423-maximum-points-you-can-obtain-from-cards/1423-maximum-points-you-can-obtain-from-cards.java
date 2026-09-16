class Solution {
    public int maxScore(int[] cardPoints, int k) {
        int n = cardPoints.length;
        int total = 0;
        for (int p : cardPoints) total += p;

        int windowSize = n - k;

        // If we take all cards, no window is left
        if (windowSize == 0) return total;

        // Find min sum of a window of size (n - k)
        int windowSum = 0;
        for (int i = 0; i < windowSize; i++) {
            windowSum += cardPoints[i];
        }
        int minWindow = windowSum;

        for (int i = windowSize; i < n; i++) {
            windowSum += cardPoints[i] - cardPoints[i - windowSize];
            minWindow = Math.min(minWindow, windowSum);
        }

        return total - minWindow;
    }
}