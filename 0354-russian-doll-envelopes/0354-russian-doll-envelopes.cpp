#include <bits/stdc++.h>
using namespace std;

class Solution {
public:
    int maxEnvelopes(vector<vector<int>>& envelopes) {
        if (envelopes.empty()) return 0;
        
        // Sort by width ascending, height descending for same width
        sort(envelopes.begin(), envelopes.end(), [](const vector<int>& a, const vector<int>& b) {
            if (a[0] == b[0]) {
                return a[1] > b[1]; // descending height
            }
            return a[0] < b[0];
        });
        
        // Now find LIS on heights using patience sorting / binary search
        vector<int> tails; // tails[i] = smallest tail of all increasing subsequences with length i+1
        
        for (const auto& env : envelopes) {
            int height = env[1];
            auto it = lower_bound(tails.begin(), tails.end(), height);
            
            if (it == tails.end()) {
                tails.push_back(height);
            } else {
                *it = height;
            }
        }
        
        return tails.size();
    }
};