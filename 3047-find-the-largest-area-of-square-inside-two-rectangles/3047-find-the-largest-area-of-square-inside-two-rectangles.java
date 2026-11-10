class Solution {
    public long largestSquareArea(int[][] bottomLeft, int[][] topRight) {
        long maxArea = 0;
        int n = bottomLeft.length;

        // Check every pair of rectangles
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                // Compute the intersection rectangle
                int left   = Math.max(bottomLeft[i][0], bottomLeft[j][0]);
                int right  = Math.min(topRight[i][0], topRight[j][0]);
                int bottom = Math.max(bottomLeft[i][1], bottomLeft[j][1]);
                int top    = Math.min(topRight[i][1], topRight[j][1]);

                // Check if the intersection is valid (positive width and height)
                if (right > left && top > bottom) {
                    long side = Math.min(right - left, top - bottom);
                    maxArea = Math.max(maxArea, side * side);
                }
            }
        }

        return maxArea;
    }
}