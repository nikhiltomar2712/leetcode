int maximalSquare(char** matrix, int matrixSize, int* matrixColSize) {
    if (matrixSize == 0 || matrixColSize[0] == 0) return 0;
    
    int rows = matrixSize;
    int cols = matrixColSize[0];
    int maxSize = 0;
    
    // Create DP table
    int** dp = (int**)malloc(rows * sizeof(int*));
    for (int i = 0; i < rows; i++) {
        dp[i] = (int*)calloc(cols, sizeof(int));
    }
    
    // Fill DP table
    for (int i = 0; i < rows; i++) {
        for (int j = 0; j < cols; j++) {
            if (matrix[i][j] == '1') {
                if (i == 0 || j == 0) {
                    // First row or column: can only form 1x1 square
                    dp[i][j] = 1;
                } else {
                    // Look at three neighbors: up, left, diagonal
                    int min = dp[i-1][j];
                    if (dp[i][j-1] < min) min = dp[i][j-1];
                    if (dp[i-1][j-1] < min) min = dp[i-1][j-1];
                    dp[i][j] = min + 1;
                }
                
                if (dp[i][j] > maxSize) {
                    maxSize = dp[i][j];
                }
            }
        }
    }
    
    // Free memory
    for (int i = 0; i < rows; i++) {
        free(dp[i]);
    }
    free(dp);
    
    return maxSize * maxSize;
}