/**
 * Note: The returned array must be malloced, assume caller calls free().
 */
char** summaryRanges(int* nums, int numsSize, int* returnSize) {
    char** result = (char**)malloc(numsSize * sizeof(char*));
    *returnSize = 0;
    
    if (numsSize == 0) {
        return result;
    }
    
    int i = 0;
    while (i < numsSize) {
        int start = nums[i];
        
        // Find the end of the consecutive sequence
        while (i + 1 < numsSize && nums[i + 1] == nums[i] + 1) {
            i++;
        }
        
        int end = nums[i];
        
        // Create the range string
        if (start == end) {
            // Single number
            result[*returnSize] = (char*)malloc(12 * sizeof(char)); // Enough for int
            sprintf(result[*returnSize], "%d", start);
        } else {
            // Range with arrow
            result[*returnSize] = (char*)malloc(25 * sizeof(char)); // Enough for "start->end"
            sprintf(result[*returnSize], "%d->%d", start, end);
        }
        
        (*returnSize)++;
        i++;
    }
    
    return result;
}