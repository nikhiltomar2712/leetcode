#include <string>
#include <vector>
using namespace std;

class Solution {
public:
    string getPermutation(int n, int k) {
        vector<int> nums;
        int fact = 1;
        for (int i = 1; i <= n; ++i) {
            nums.push_back(i);
            fact *= i;
        }
        // Adjust k to 0-indexed
        k--;
        string result;
        for (int i = 0; i < n; ++i) {
            fact /= (n - i);
            int idx = k / fact;
            result += to_string(nums[idx]);
            nums.erase(nums.begin() + idx);
            k %= fact;
        }
        return result;
    }
};