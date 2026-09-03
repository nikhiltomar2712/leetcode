class Solution {
    public double[] sampleStats(int[] count) {
        int min = -1, max = -1;
        long totalSum = 0;
        long totalCount = 0;
        int mode = 0;
        long maxFreq = 0;

        // First pass: min, max, mean components, mode
        for (int i = 0; i < 256; i++) {
            if (count[i] > 0) {
                if (min == -1) min = i;          // first non-zero → minimum
                max = i;                         // last non-zero → maximum

                totalSum += (long) i * count[i];
                totalCount += count[i];

                if (count[i] > maxFreq) {
                    maxFreq = count[i];
                    mode = i;
                }
            }
        }

        double mean = (double) totalSum / totalCount;

        // Median
        double median = 0.0;
        long mid1 = (totalCount + 1) / 2;   // 1-based position of the first middle
        long mid2 = (totalCount % 2 == 0) ? mid1 + 1 : mid1;

        long cumulative = 0;
        int left = -1, right = -1;

        for (int i = 0; i < 256; i++) {
            if (count[i] == 0) continue;
            cumulative += count[i];

            if (left == -1 && cumulative >= mid1) {
                left = i;
            }
            if (right == -1 && cumulative >= mid2) {
                right = i;
                break;
            }
        }

        median = (left + right) / 2.0;

        return new double[]{min, max, mean, median, mode};
    }
}