class Solution {
    public int maximumPopulation(int[][] logs) {
        int[] diff = new int[2051];
        for (int[] log : logs) {
            diff[log[0]]++;
            diff[log[1]]--;
        }
        int max = 0, year = 1950, cur = 0;
        for (int i = 1950; i <= 2050; i++) {
            cur += diff[i];
            if (cur > max) {
                max = cur;
                year = i;
            }
        }
        return year;
    }
}