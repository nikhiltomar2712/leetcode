/**
 * Return an array of arrays of size *returnSize.
 * The sizes of the arrays are returned as *returnColumnSizes array.
 * Note: Both returned array and *columnSizes array must be malloced, assume caller calls free().
 */
int** largeGroupPositions(char* s, int* returnSize, int** returnColumnSizes) {
    int n = strlen(s);
    // Max possible large groups is n/3
    int** result = (int**)malloc(sizeof(int*) * (n / 3 + 1));
    *returnColumnSizes = (int*)malloc(sizeof(int) * (n / 3 + 1));
    *returnSize = 0;
    
    int i = 0;
    while (i < n) {
        int j = i;
        while (j < n && s[j] == s[i]) {
            j++;
        }
        // Group from i to j-1
        if (j - i >= 3) {
            result[*returnSize] = (int*)malloc(sizeof(int) * 2);
            result[*returnSize][0] = i;
            result[*returnSize][1] = j - 1;
            (*returnColumnSizes)[*returnSize] = 2;
            (*returnSize)++;
        }
        i = j;
    }
    
    return result;
}