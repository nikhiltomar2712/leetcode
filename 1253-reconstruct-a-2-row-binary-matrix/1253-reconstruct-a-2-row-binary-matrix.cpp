class Solution {
public:
    vector<vector<int>> reconstructMatrix(int upper, int lower, vector<int>& colsum) {
        int n = colsum.size();
        vector<vector<int>> res(2, vector<int>(n, 0));
        
        // First, handle columns that must be 2
        for (int j = 0; j < n; ++j) {
            if (colsum[j] == 2) {
                res[0][j] = 1;
                res[1][j] = 1;
                upper--;
                lower--;
            }
        }
        
        if (upper < 0 || lower < 0) return {};
        
        // Then, handle columns that are 1
        for (int j = 0; j < n; ++j) {
            if (colsum[j] == 1) {
                if (upper > 0) {
                    res[0][j] = 1;
                    upper--;
                } else if (lower > 0) {
                    res[1][j] = 1;
                    lower--;
                } else {
                    return {};
                }
            }
        }
        
        // Check if we used exactly the required sums
        if (upper == 0 && lower == 0) {
            return res;
        }
        return {};
    }
};