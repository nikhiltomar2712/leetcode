class Solution {
public:
    int maximumSafenessFactor(vector<vector<int>>& grid) {
        int n = grid.size();
        
        // Step 1: Multi-source BFS to calculate safeness factor
        vector<vector<int>> safeness(n, vector<int>(n, -1));
        queue<pair<int, int>> q;
        
        // Initialize with all thief positions
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    safeness[i][j] = 0;
                    q.push({i, j});
                }
            }
        }
        
        // BFS directions
        vector<int> dirs = {-1, 0, 1, 0, -1};
        
        // Multi-source BFS
        while (!q.empty()) {
            auto [x, y] = q.front();
            q.pop();
            
            for (int k = 0; k < 4; k++) {
                int nx = x + dirs[k];
                int ny = y + dirs[k + 1];
                
                if (nx >= 0 && nx < n && ny >= 0 && ny < n && safeness[nx][ny] == -1) {
                    safeness[nx][ny] = safeness[x][y] + 1;
                    q.push({nx, ny});
                }
            }
        }
        
        // Step 2: Binary search on answer
        int left = 0, right = n * n;  // Maximum possible safeness
        int result = 0;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            
            if (canReach(safeness, mid, n)) {
                result = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        
        return result;
    }
    
private:
    bool canReach(vector<vector<int>>& safeness, int minSafeness, int n) {
        // Check if start and end meet the minimum safeness requirement
        if (safeness[0][0] < minSafeness || safeness[n-1][n-1] < minSafeness) {
            return false;
        }
        
        vector<vector<bool>> visited(n, vector<bool>(n, false));
        queue<pair<int, int>> q;
        q.push({0, 0});
        visited[0][0] = true;
        
        vector<int> dirs = {-1, 0, 1, 0, -1};
        
        while (!q.empty()) {
            auto [x, y] = q.front();
            q.pop();
            
            if (x == n - 1 && y == n - 1) {
                return true;
            }
            
            for (int k = 0; k < 4; k++) {
                int nx = x + dirs[k];
                int ny = y + dirs[k + 1];
                
                if (nx >= 0 && nx < n && ny >= 0 && ny < n && 
                    !visited[nx][ny] && safeness[nx][ny] >= minSafeness) {
                    visited[nx][ny] = true;
                    q.push({nx, ny});
                }
            }
        }
        
        return false;
    }
};