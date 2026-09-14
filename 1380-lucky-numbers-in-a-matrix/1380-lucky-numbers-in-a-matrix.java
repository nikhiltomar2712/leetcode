class Solution {
    public List<Integer> luckyNumbers(int[][] matrix) {
        List<Integer> result = new ArrayList<>();
        int m = matrix.length;
        int n = matrix[0].length;
        
        for (int i = 0; i < m; i++) {
            // Find the minimum in the current row and its column index
            int minVal = matrix[i][0];
            int minCol = 0;
            for (int j = 1; j < n; j++) {
                if (matrix[i][j] < minVal) {
                    minVal = matrix[i][j];
                    minCol = j;
                }
            }
            
            // Check if this minVal is the maximum in its column
            boolean isMaxInCol = true;
            for (int k = 0; k < m; k++) {
                if (matrix[k][minCol] > minVal) {
                    isMaxInCol = false;
                    break;
                }
            }
            
            if (isMaxInCol) {
                result.add(minVal);
            }
        }
        
        return result;
    }
}