#include <bits/stdc++.h>
using namespace std;

class Solution {
public:
    int numberOfBoomerangs(vector<vector<int>>& points) {
        int result = 0;
        
        for (const auto& p : points) {
            unordered_map<int, int> distCount;
            
            for (const auto& q : points) {
                if (&p == &q) continue; // Skip same point
                
                int dx = p[0] - q[0];
                int dy = p[1] - q[1];
                int distSq = dx * dx + dy * dy;
                distCount[distSq]++;
            }
            
            for (const auto& [dist, cnt] : distCount) {
                if (cnt >= 2) {
                    result += cnt * (cnt - 1); // Number of ways to choose 2 points
                }
            }
        }
        
        return result;
    }
};