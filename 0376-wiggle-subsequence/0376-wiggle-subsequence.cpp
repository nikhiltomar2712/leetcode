#include <bits/stdc++.h>
using namespace std;

class Solution {
public:
    int wiggleMaxLength(vector<int>& nums) {
        if (nums.size() <= 1) return nums.size();
        
        int up = 1;    // Length of longest wiggle ending with an up
        int down = 1;  // Length of longest wiggle ending with a down
        
        for (int i = 1; i < nums.size(); ++i) {
            if (nums[i] > nums[i - 1]) {
                up = down + 1;
            } else if (nums[i] < nums[i - 1]) {
                down = up + 1;
            }
            // If equal, skip (no wiggle)
        }
        
        return max(up, down);
    }
};