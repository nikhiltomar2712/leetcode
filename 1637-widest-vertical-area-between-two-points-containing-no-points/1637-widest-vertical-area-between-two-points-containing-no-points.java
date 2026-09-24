import java.util.Arrays;

class Solution {
    public int maxWidthOfVerticalArea(int[][] points) {
        int n = points.length;
        int[] xs = new int[n];

        for (int i = 0; i < n; i++) {
            xs[i] = points[i][0];
        }

        Arrays.sort(xs);

        int maxGap = 0;
        for (int i = 1; i < n; i++) {
            maxGap = Math.max(maxGap, xs[i] - xs[i - 1]);
        }

        return maxGap;
    }
}