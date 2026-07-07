class Solution {
public:
    bool checkSubarraySum(vector<int>& nums, int k) {
        if (nums.empty()) return false;
        
        unordered_map<int, int> prefixMod;  // remainder -> earliest index
        prefixMod[0] = -1;
        
        long long prefixSum = 0;  // Use long long to prevent overflow
        
        for (int i = 0; i < nums.size(); ++i) {
            prefixSum += nums[i];
            
            // Compute modulo safely
            int mod = (prefixSum % k + k) % k;
            
            if (prefixMod.count(mod)) {
                if (i - prefixMod[mod] >= 2) {
                    return true;
                }
            } else {
                prefixMod[mod] = i;
            }
        }
        
        return false;
    }
};