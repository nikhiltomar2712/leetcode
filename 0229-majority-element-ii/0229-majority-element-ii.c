/**
 * Note: The returned array must be malloced, assume caller calls free().
 */
int* majorityElement(int* nums, int numsSize, int* returnSize) {
    int* result = (int*)malloc(2 * sizeof(int));
    *returnSize = 0;
    
    if (numsSize == 0) {
        return result;
    }
    
    // First pass: Find candidates
    int candidate1 = 0, candidate2 = 0;
    int count1 = 0, count2 = 0;
    
    for (int i = 0; i < numsSize; i++) {
        if (count1 > 0 && nums[i] == candidate1) {
            count1++;
        } else if (count2 > 0 && nums[i] == candidate2) {
            count2++;
        } else if (count1 == 0) {
            candidate1 = nums[i];
            count1 = 1;
        } else if (count2 == 0) {
            candidate2 = nums[i];
            count2 = 1;
        } else {
            // Current number is different from both candidates
            count1--;
            count2--;
        }
    }
    
    // Second pass: Verify candidates
    count1 = 0;
    count2 = 0;
    
    for (int i = 0; i < numsSize; i++) {
        if (nums[i] == candidate1) {
            count1++;
        } else if (nums[i] == candidate2) {
            count2++;
        }
    }
    
    // Add valid candidates to result
    if (count1 > numsSize / 3) {
        result[*returnSize] = candidate1;
        (*returnSize)++;
    }
    if (count2 > numsSize / 3 && candidate1 != candidate2) {
        result[*returnSize] = candidate2;
        (*returnSize)++;
    }
    
    return result;
}