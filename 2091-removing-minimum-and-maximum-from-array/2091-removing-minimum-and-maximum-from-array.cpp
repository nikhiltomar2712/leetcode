class Solution {
public:
    int minimumDeletions(vector<int>& nums) {
        int n = nums.size();
        
        // Find positions of minimum and maximum elements
        int minPos = 0, maxPos = 0;
        int minVal = nums[0], maxVal = nums[0];
        
        for (int i = 1; i < n; i++) {
            if (nums[i] < minVal) {
                minVal = nums[i];
                minPos = i;
            }
            if (nums[i] > maxVal) {
                maxVal = nums[i];
                maxPos = i;
            }
        }
        
        // Ensure minPos is to the left of maxPos for easier calculation
        int left = min(minPos, maxPos);
        int right = max(minPos, maxPos);
        
        // Three strategies:
        // 1. Delete both from left side
        int option1 = right + 1;
        
        // 2. Delete both from right side
        int option2 = n - left;
        
        // 3. Delete one from left, one from right
        int option3 = (left + 1) + (n - right);
        
        return min({option1, option2, option3});
    }
};