#include <vector>
#include <cstdint>
using namespace std;

class Solution {
public:
    vector<int> findErrorNums(vector<int>& nums) {
        long long n = nums.size();
        // Expected sum of 1..n
        long long expected_sum = n * (n + 1) / 2;
        // Expected sum of squares
        long long expected_sum_sq = n * (n + 1) * (2 * n + 1) / 6;
        
        long long actual_sum = 0;
        long long actual_sum_sq = 0;
        for (int x : nums) {
            actual_sum += x;
            actual_sum_sq += (long long)x * x;
        }
        
        long long diff = actual_sum - expected_sum;               // dup - missing
        long long diff_sq = actual_sum_sq - expected_sum_sq;      // dup^2 - missing^2
        
        // diff_sq = (dup - missing) * (dup + missing)
        long long sum_dup_mis = diff_sq / diff;                   // dup + missing
        
        int duplicate = (diff + sum_dup_mis) / 2;
        int missing = sum_dup_mis - duplicate;
        
        return {duplicate, missing};
    }
};