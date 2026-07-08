#include <vector>
#include <stack>
#include <algorithm>
using namespace std;

class Solution {
public:
    int maximalRectangle(vector<vector<char>>& matrix) {
        if (matrix.empty() || matrix[0].empty()) return 0;
        int rows = matrix.size(), cols = matrix[0].size();
        vector<int> heights(cols, 0);
        int maxArea = 0;
        for (int i = 0; i < rows; ++i) {
            // Update heights
            for (int j = 0; j < cols; ++j) {
                if (matrix[i][j] == '1') heights[j] += 1;
                else heights[j] = 0;
            }
            // Compute largest rectangle in histogram for current row
            maxArea = max(maxArea, largestRectangleArea(heights));
        }
        return maxArea;
    }
    
private:
    int largestRectangleArea(vector<int>& heights) {
        int n = heights.size();
        stack<int> st;
        int maxArea = 0;
        for (int i = 0; i <= n; ++i) {
            int curHeight = (i == n) ? 0 : heights[i];
            while (!st.empty() && curHeight < heights[st.top()]) {
                int h = heights[st.top()];
                st.pop();
                int width = st.empty() ? i : i - st.top() - 1;
                maxArea = max(maxArea, h * width);
            }
            st.push(i);
        }
        return maxArea;
    }
};