class Solution {
    public int areaOfMaxDiagonal(int[][] dimensions) {
        int maxArea = 0;
        long maxDiag = 0;

        for (int[] d : dimensions) {
            int l = d[0];
            int w = d[1];
            long diag = (long) l * l + (long) w * w;

            if (diag > maxDiag) {
                maxDiag = diag;
                maxArea = l * w;
            } else if (diag == maxDiag) {
                maxArea = Math.max(maxArea, l * w);
            }
        }

        return maxArea;
    }
}