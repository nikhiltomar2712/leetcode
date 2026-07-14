class Solution {
public:
    int findPairs(vector<int>& nums, int k) {
        if (k < 0) return 0;  // k must be non-negative
        
        unordered_map<int, int> freq;
        for (int num : nums) {
            freq[num]++;
        }
        
        int count = 0;
        for (auto& [num, frequency] : freq) {
            if (k == 0) {
                // For k=0, we need duplicates
                if (frequency >= 2) {
                    count++;
                }
            } else {
                // For k>0, check if num + k exists
                if (freq.count(num + k)) {
                    count++;
                }
            }
        }
        
        return count;
    }
};