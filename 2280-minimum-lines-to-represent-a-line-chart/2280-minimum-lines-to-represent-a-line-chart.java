class Solution {
    public int minimumLines(int[][] stockPrices) {
        int n = stockPrices.length;
        if (n == 1) return 0;
        Arrays.sort(stockPrices, (a, b) -> a[0] - b[0]);
        int lines = 1;
        for (int i = 1; i < n - 1; i++) {
            long dx1 = stockPrices[i][0] - stockPrices[i - 1][0];
            long dy1 = stockPrices[i][1] - stockPrices[i - 1][1];
            long dx2 = stockPrices[i + 1][0] - stockPrices[i][0];
            long dy2 = stockPrices[i + 1][1] - stockPrices[i][1];
            if (dy1 * dx2 != dy2 * dx1) lines++;
        }
        return lines;
    }
}