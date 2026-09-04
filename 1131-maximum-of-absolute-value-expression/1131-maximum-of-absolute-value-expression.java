class Solution {
    public int maxAbsValExpr(int[] arr1, int[] arr2) {
        int n = arr1.length;
        int max = Integer.MIN_VALUE;
        
        // There are 4 possible sign combinations for (arr1[i], arr2[i], i)
        // For each combination, we track max and min of the expression
        int[][] signs = {
            {1, 1, 1},   // arr1[i] + arr2[i] + i
            {1, -1, 1},  // arr1[i] - arr2[i] + i
            {-1, 1, 1},  // -arr1[i] + arr2[i] + i
            {-1, -1, 1}  // -arr1[i] - arr2[i] + i
        };
        
        for (int[] sign : signs) {
            int min = Integer.MAX_VALUE;
            int maxVal = Integer.MIN_VALUE;
            
            for (int i = 0; i < n; i++) {
                int value = sign[0] * arr1[i] + sign[1] * arr2[i] + sign[2] * i;
                min = Math.min(min, value);
                maxVal = Math.max(maxVal, value);
            }
            
            max = Math.max(max, maxVal - min);
        }
        
        return max;
    }
}