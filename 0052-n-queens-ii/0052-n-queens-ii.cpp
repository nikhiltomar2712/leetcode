class Solution {
public:
    int totalNQueens(int n) {
        int count = 0;
        vector<bool> col(n, false);
        vector<bool> diag1(2 * n - 1, false);
        vector<bool> diag2(2 * n - 1, false);
        
        function<void(int)> backtrack = [&](int row) {
            if (row == n) {
                count++;
                return;
            }
            for (int c = 0; c < n; c++) {
                int d1 = row - c + n - 1;
                int d2 = row + c;
                if (!col[c] && !diag1[d1] && !diag2[d2]) {
                    col[c] = diag1[d1] = diag2[d2] = true;
                    backtrack(row + 1);
                    col[c] = diag1[d1] = diag2[d2] = false;
                }
            }
        };
        
        backtrack(0);
        return count;
    }
};