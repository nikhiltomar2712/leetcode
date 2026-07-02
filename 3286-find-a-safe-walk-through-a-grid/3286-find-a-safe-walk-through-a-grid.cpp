#include <bits/stdc++.h>
using namespace std;

class Solution {
public:
    bool findSafeWalk(vector<vector<int>>& grid, int health) {
        int m = grid.size();
        int n = grid[0].size();
        int init_h = health - grid[0][0];
        if (init_h < 1) return false;
        
        vector<vector<vector<bool>>> visited(m, vector<vector<bool>>(n, vector<bool>(health + 1, false)));
        queue<tuple<int, int, int>> q;  // x, y, h
        q.push({0, 0, init_h});
        visited[0][0][init_h] = true;
        
        int dirs[4][2] = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        
        while (!q.empty()) {
            auto [x, y, h] = q.front();
            q.pop();
            
            if (x == m - 1 && y == n - 1) {
                return true;
            }
            
            for (auto& d : dirs) {
                int nx = x + d[0];
                int ny = y + d[1];
                if (nx >= 0 && nx < m && ny >= 0 && ny < n) {
                    int nh = h - grid[nx][ny];
                    if (nh >= 1 && !visited[nx][ny][nh]) {
                        visited[nx][ny][nh] = true;
                        q.push({nx, ny, nh});
                    }
                }
            }
        }
        return false;
    }
};