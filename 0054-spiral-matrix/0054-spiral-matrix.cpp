#include <vector>
using namespace std;

class Solution {
public:
    vector<int> spiralOrder(vector<vector<int>>& matrix) {
        if (matrix.empty() || matrix[0].empty()) return {};
        int m = matrix.size();
        int n = matrix[0].size();
        int top = 0, bottom = m - 1;
        int left = 0, right = n - 1;
        vector<int> result;
        
        while (top <= bottom && left <= right) {
            // top row, left to right
            for (int j = left; j <= right; ++j) {
                result.push_back(matrix[top][j]);
            }
            ++top;
            
            // right column, top to bottom
            for (int i = top; i <= bottom; ++i) {
                result.push_back(matrix[i][right]);
            }
            --right;
            
            if (top <= bottom) {
                // bottom row, right to left
                for (int j = right; j >= left; --j) {
                    result.push_back(matrix[bottom][j]);
                }
                --bottom;
            }
            
            if (left <= right) {
                // left column, bottom to top
                for (int i = bottom; i >= top; --i) {
                    result.push_back(matrix[i][left]);
                }
                ++left;
            }
        }
        return result;
    }
};