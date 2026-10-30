class Solution {
    public int matrixSum(int[][] nums) {
        for (int[] row : nums) {
            Arrays.sort(row);
        }
        int score = 0;
        int m = nums.length, n = nums[0].length;
        for (int j = 0; j < n; j++) {
            int max = 0;
            for (int i = 0; i < m; i++) {
                max = Math.max(max, nums[i][j]);
            }
            score += max;
        }
        return score;
    }
}