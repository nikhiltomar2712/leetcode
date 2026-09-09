class Solution {
    public int oddCells(int m, int n, int[][] indices) {
        int[] row = new int[m];
        int[] col = new int[n];

        // Count increments for each row and column
        for (int[] index : indices) {
            row[index[0]]++;
            col[index[1]]++;
        }

        // Count how many rows and columns have odd increments
        int oddRows = 0;
        for (int r : row) {
            if (r % 2 == 1) oddRows++;
        }

        int oddCols = 0;
        for (int c : col) {
            if (c % 2 == 1) oddCols++;
        }

        // Cells that are odd = (oddRows * evenCols) + (evenRows * oddCols)
        return oddRows * (n - oddCols) + (m - oddRows) * oddCols;
    }
}