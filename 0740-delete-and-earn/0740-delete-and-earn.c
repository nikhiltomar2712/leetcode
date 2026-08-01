int deleteAndEarn(int* nums, int numsSize) {
    // nums[i] is in [1, 10000]
    int points[10001] = {0};
    int maxNum = 0;
    
    for (int i = 0; i < numsSize; i++) {
        points[nums[i]] += nums[i];
        if (nums[i] > maxNum) maxNum = nums[i];
    }
    
    // House Robber style DP
    // dp[i] = max points using numbers up to i
    int prev2 = 0;          // dp[i-2]
    int prev1 = 0;          // dp[i-1]
    
    for (int i = 1; i <= maxNum; i++) {
        int curr = prev1 > (prev2 + points[i]) ? prev1 : (prev2 + points[i]);
        prev2 = prev1;
        prev1 = curr;
    }
    
    return prev1;
}