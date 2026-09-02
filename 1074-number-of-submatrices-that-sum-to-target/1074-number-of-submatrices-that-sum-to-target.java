class Solution {
    public int numSubmatrixSumTarget(int[][] matrix, int target) {
        int m = matrix.length;
        int n = matrix[0].length;
        int ans = 0;

        // Fix the top and bottom rows
        for (int top = 0; top < m; top++) {
            int[] colSum = new int[n];          // compressed 1-D array of column sums
            for (int bottom = top; bottom < m; bottom++) {
                // Add the current bottom row into colSum
                for (int c = 0; c < n; c++) {
                    colSum[c] += matrix[bottom][c];
                }
                // Now count how many subarrays in colSum sum to target
                ans += subarraySum(colSum, target);
            }
        }
        return ans;
    }

    // Classic “Subarray Sum Equals K” using prefix sum + HashMap
    private int subarraySum(int[] nums, int target) {
        Map<Integer, Integer> prefixCount = new HashMap<>();
        prefixCount.put(0, 1);                  // empty prefix
        int sum = 0;
        int count = 0;

        for (int x : nums) {
            sum += x;
            count += prefixCount.getOrDefault(sum - target, 0);
            prefixCount.merge(sum, 1, Integer::sum);
        }
        return count;
    }
}