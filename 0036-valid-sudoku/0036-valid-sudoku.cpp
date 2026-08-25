#include <vector>
using namespace std;

class Solution {
public:
    bool isValidSudoku(vector<vector<char>>& board) {
        bool rows[9][9] = {false};
        bool cols[9][9] = {false};
        bool boxes[9][9] = {false};
        
        for (int i = 0; i < 9; ++i) {
            for (int j = 0; j < 9; ++j) {
                char c = board[i][j];
                if (c == '.') continue;
                int num = c - '1'; // 0-8
                int boxIdx = (i / 3) * 3 + (j / 3);
                if (rows[i][num] || cols[j][num] || boxes[boxIdx][num]) {
                    return false;
                }
                rows[i][num] = true;
                cols[j][num] = true;
                boxes[boxIdx][num] = true;
            }
        }
        return true;
    }
};