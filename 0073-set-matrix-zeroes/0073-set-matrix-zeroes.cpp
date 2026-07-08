#include <vector>
using namespace std;

class Solution {
public:
    void setZeroes(vector<vector<int>>& matrix) {
        int m = matrix.size();
        int n = matrix[0].size();
        bool firstRowZero = false, firstColZero = false;
        // Check if first row has zero
        for (int j = 0; j < n; ++j) {
            if (matrix[0][j] == 0) {
                firstRowZero = true;
                break;
            }
        }
        // Check if first column has zero
        for (int i = 0; i < m; ++i) {
            if (matrix[i][0] == 0) {
                firstColZero = true;
                break;
            }
        }
        // Mark zeros on first row and first column
        for (int i = 1; i < m; ++i) {
            for (int j = 1; j < n; ++j) {
                if (matrix[i][j] == 0) {
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
            }
        }
        // Set zeros based on markers
        for (int i = 1; i < m; ++i) {
            if (matrix[i][0] == 0) {
                for (int j = 1; j < n; ++j) matrix[i][j] = 0;
            }
        }
        for (int j = 1; j < n; ++j) {
            if (matrix[0][j] == 0) {
                for (int i = 1; i < m; ++i) matrix[i][j] = 0;
            }
        }
        // Handle first row and column
        if (firstRowZero) {
            for (int j = 0; j < n; ++j) matrix[0][j] = 0;
        }
        if (firstColZero) {
            for (int i = 0; i < m; ++i) matrix[i][0] = 0;
        }
    }
};