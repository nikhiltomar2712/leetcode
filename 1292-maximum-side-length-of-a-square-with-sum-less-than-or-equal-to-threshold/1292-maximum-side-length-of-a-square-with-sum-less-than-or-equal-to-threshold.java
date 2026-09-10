class Solution {
    public int maxSideLength(int[][] mat, int threshold) {
        int m = mat.length;
        int n = mat[0].length;
        
        // prefix[i][j] = sum of the rectangle from (0,0) to (i-1,j-1)
        int[][] prefix = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                prefix[i][j] = mat[i - 1][j - 1] 
                             + prefix[i - 1][j] 
                             + prefix[i][j - 1] 
                             - prefix[i - 1][j - 1];
            }
        }
        
        // Binary search on the side length
        int left = 0, right = Math.min(m, n);
        while (left < right) {
            int mid = (left + right + 1) / 2;  // try larger first
            if (existsSquare(prefix, m, n, mid, threshold)) {
                left = mid;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }
    
    private boolean existsSquare(int[][] prefix, int m, int n, int side, int threshold) {
        for (int i = side; i <= m; i++) {
            for (int j = side; j <= n; j++) {
                int sum = prefix[i][j] 
                        - prefix[i - side][j] 
                        - prefix[i][j - side] 
                        + prefix[i - side][j - side];
                if (sum <= threshold) {
                    return true;
                }
            }
        }
        return false;
    }
}