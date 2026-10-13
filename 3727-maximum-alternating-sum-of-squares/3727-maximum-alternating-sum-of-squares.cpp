#include <vector>
#include <algorithm>
#include <numeric>

class Solution {
public:
    long long maxAlternatingSum(std::vector<int>& nums) {
        int n = nums.size();
        
        // Square each element in-place
        for (int &x : nums) {
            x = x * x;
        }
        
        // Sort the squared values in ascending order
        std::sort(nums.begin(), nums.end());
        
        // The first half (smaller squares) will be subtracted
        // The second half (larger squares) will be added
        long long sumSmall = 0, sumLarge = 0;
        int mid = n / 2;
        
        for (int i = 0; i < mid; ++i) {
            sumSmall += nums[i];
        }
        for (int i = mid; i < n; ++i) {
            sumLarge += nums[i];
        }
        
        return sumLarge - sumSmall;
    }
};