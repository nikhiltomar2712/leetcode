int missingInteger(int* nums, int numsSize) {
    int s = nums[0];
    for (int i = 1; i < numsSize && nums[i] == nums[i - 1] + 1; i++) {
        s += nums[i];
    }
    
    // nums[i] <= 50, so a boolean array of size 51 is enough
    bool present[51] = {false};
    for (int i = 0; i < numsSize; i++) {
        present[nums[i]] = true;
    }
    
    while (s < 51 && present[s]) {
        s++;
    }
    return s;
}