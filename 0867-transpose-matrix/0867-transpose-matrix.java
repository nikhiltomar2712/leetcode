class Solution {
    public int[][] transpose(int[][] matrix) {
        int m = matrix.length;        // number of rows
        int n = matrix[0].length;     // number of columns
        
        int[][] result = new int[n][m];
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                result[j][i] = matrix[i][j];
            }
        }
        
        return result;
    }
}