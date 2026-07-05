class Solution {
public:
    vector<double> medianSlidingWindow(vector<int>& nums, int k) {
        vector<double> result;
        multiset<int> window(nums.begin(), nums.begin() + k);
        auto mid = next(window.begin(), k / 2);
        
        for (int i = k; i <= nums.size(); i++) {
            // Calculate median
            double median = ((double)*mid + *prev(mid, 1 - k % 2)) / 2.0;
            result.push_back(median);
            
            if (i == nums.size()) break;
            
            // Insert new element
            window.insert(nums[i]);
            if (nums[i] < *mid) mid--;
            
            // Remove old element
            if (nums[i - k] <= *mid) mid++;
            window.erase(window.lower_bound(nums[i - k]));
        }
        
        return result;
    }
};