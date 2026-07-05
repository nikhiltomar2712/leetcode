class Solution {
public:
    int totalHammingDistance(vector<int>& nums) {
        int n = nums.size();
        int total = 0;
        
        for (int bit = 0; bit < 32; bit++) {
            int ones = accumulate(nums.begin(), nums.end(), 0,
                [bit](int sum, int num) {
                    return sum + ((num >> bit) & 1);
                });
            total += ones * (n - ones);
        }
        
        return total;
    }
};