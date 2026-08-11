/**
 * Return an array of arrays of size *returnSize.
 * The sizes of the arrays are returned as *returnColumnSizes array.
 * Note: Both returned array and *columnSizes array must be malloced, assume caller calls free().
 */
int** flipAndInvertImage(int** image, int imageSize, int* imageColSize, int* returnSize, int** returnColumnSizes) {
    int n = imageSize;
    *returnSize = n;
    *returnColumnSizes = (int*)malloc(sizeof(int) * n);
    
    int** result = (int**)malloc(sizeof(int*) * n);
    
    for (int i = 0; i < n; i++) {
        (*returnColumnSizes)[i] = n;
        result[i] = (int*)malloc(sizeof(int) * n);
        
        // Reverse and invert in one pass
        for (int j = 0; j < n; j++) {
            result[i][j] = 1 - image[i][n - 1 - j];
        }
    }
    
    return result;
}