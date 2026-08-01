int dominantIndex(int* nums, int numsSize) {
    int maxIdx = 0;
    
    // Find the index of the largest element
    for (int i = 1; i < numsSize; i++) {
        if (nums[i] > nums[maxIdx]) {
            maxIdx = i;
        }
    }
    
    // Check if it is at least twice every other number
    for (int i = 0; i < numsSize; i++) {
        if (i != maxIdx && nums[maxIdx] < 2 * nums[i]) {
            return -1;
        }
    }
    
    return maxIdx;
}