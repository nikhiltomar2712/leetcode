class Solution {
    public long maximumImportance(int n, int[][] roads) {
        int[] degree = new int[n];
        for (int[] r : roads) {
            degree[r[0]]++;
            degree[r[1]]++;
        }
        Arrays.sort(degree);
        long res = 0;
        for (int i = 0; i < n; i++) {
            res += (long) degree[i] * (i + 1);
        }
        return res;
    }
}