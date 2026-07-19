class Solution {
    private Integer[][][] memo;
    
    public int removeBoxes(int[] boxes) {
        int n = boxes.length;
        memo = new Integer[n][n][n + 1];
        return dp(boxes, 0, n - 1, 0);
    }
    
    private int dp(int[] boxes, int i, int j, int k) {
        if (i > j) {
            return 0;
        }
        
        if (memo[i][j][k] != null) {
            return memo[i][j][k];
        }
        
        // Skip boxes with same color as boxes[i]
        int originalI = i;
        int originalK = k;
        while (i + 1 <= j && boxes[i] == boxes[i + 1]) {
            i++;
            k++;
        }
        
        int result = (k + 1) * (k + 1) + dp(boxes, i + 1, j, 0);
        
        // Try to connect with later same color boxes
        for (int m = i + 1; m <= j; m++) {
            if (boxes[m] == boxes[i]) {
                result = Math.max(result, dp(boxes, i + 1, m - 1, 0) + dp(boxes, m, j, k + 1));
            }
        }
        
        memo[originalI][j][originalK] = result;
        return result;
    }
}