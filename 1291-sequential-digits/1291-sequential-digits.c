/**
 * Note: The returned array must be malloced, assume caller calls free().
 */
int* sequentialDigits(int low, int high, int* returnSize) {
    int* result = (int*)malloc(100 * sizeof(int));
    *returnSize = 0;
    
    // Generate all sequential digit numbers
    for (int start = 1; start <= 9; start++) {
        int num = start;
        
        for (int next = start + 1; next <= 9; next++) {
            num = num * 10 + next;
            
            if (num >= low && num <= high) {
                result[*returnSize] = num;
                (*returnSize)++;
            }
        }
    }
    
    // Need to sort because generation order isn't fully sorted
    // e.g., 12345 is generated before 2345
    for (int i = 0; i < *returnSize - 1; i++) {
        for (int j = 0; j < *returnSize - i - 1; j++) {
            if (result[j] > result[j + 1]) {
                int temp = result[j];
                result[j] = result[j + 1];
                result[j + 1] = temp;
            }
        }
    }
    
    return result;
}