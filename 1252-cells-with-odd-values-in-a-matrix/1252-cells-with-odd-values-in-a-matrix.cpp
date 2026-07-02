class Solution {
public:
    int oddCells(int m, int n, vector<vector<int>>& indices) {
        vector<int> row_inc(m, 0);
        vector<int> col_inc(n, 0);
        
        for (auto& idx : indices) {
            row_inc[idx[0]]++;
            col_inc[idx[1]]++;
        }
        
        int odd_count = 0;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if ((row_inc[i] + col_inc[j]) % 2 == 1) {
                    odd_count++;
                }
            }
        }
        return odd_count;
    }
};