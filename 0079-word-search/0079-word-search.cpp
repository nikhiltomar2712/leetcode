#include <vector>
#include <string>
using namespace std;

class Solution {
public:
    bool exist(vector<vector<char>>& board, string word) {
        int m = board.size(), n = board[0].size();
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (backtrack(board, word, i, j, 0)) return true;
            }
        }
        return false;
    }
    
private:
    bool backtrack(vector<vector<char>>& board, const string& word, int i, int j, int idx) {
        if (idx == word.size()) return true;
        if (i < 0 || i >= board.size() || j < 0 || j >= board[0].size()) return false;
        if (board[i][j] != word[idx]) return false;
        
        char temp = board[i][j];
        board[i][j] = '#'; // mark as visited
        
        bool found = backtrack(board, word, i+1, j, idx+1) ||
                     backtrack(board, word, i-1, j, idx+1) ||
                     backtrack(board, word, i, j+1, idx+1) ||
                     backtrack(board, word, i, j-1, idx+1);
        
        board[i][j] = temp;
        return found;
    }
};