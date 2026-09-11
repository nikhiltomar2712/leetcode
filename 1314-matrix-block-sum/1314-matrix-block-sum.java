class Solution {
    public int[][] matrixBlockSum(int[][] mat, int k) {
        int m = mat.length;
        int n = mat[0].length;
        
        // Build 2D prefix sum with a border of zeros
        // prefix[i][j] = sum of mat[0..i-1][0..j-1]
        int[][] prefix = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                prefix[i][j] = prefix[i - 1][j] + prefix[i][j - 1]
                             - prefix[i - 1][j - 1] + mat[i - 1][j - 1];
            }
        }
        
        int[][] answer = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // Clamp block boundaries to valid matrix indices
                int r1 = Math.max(0, i - k);
                int r2 = Math.min(m - 1, i + k);
                int c1 = Math.max(0, j - k);
                int c2 = Math.min(n - 1, j + k);
                
                // O(1) submatrix sum using prefix
                answer[i][j] = prefix[r2 + 1][c2 + 1]
                             - prefix[r1][c2 + 1]
                             - prefix[r2 + 1][c1]
                             + prefix[r1][c1];
            }
        }
        
        return answer;
    }
}