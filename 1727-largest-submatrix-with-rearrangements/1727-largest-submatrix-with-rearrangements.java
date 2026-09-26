class Solution {
    public int largestSubmatrix(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int[] heights = new int[n];
        int maxArea = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                heights[j] = matrix[i][j] == 1 ? heights[j] + 1 : 0;
            }

            int[] sorted = heights.clone();
            Arrays.sort(sorted);

            for (int j = 0; j < n; j++) {
                maxArea = Math.max(maxArea, sorted[j] * (n - j));
            }
        }

        return maxArea;
    }
}