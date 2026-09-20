class Solution {
    public int minSwaps(int[][] grid) {
        int n = grid.length;
        int[] trailingZeros = new int[n];

        // Compute how many trailing zeros each row has
        for (int i = 0; i < n; i++) {
            int zeros = 0;
            for (int j = n - 1; j >= 0; j--) {
                if (grid[i][j] == 0) {
                    zeros++;
                } else {
                    break;
                }
            }
            trailingZeros[i] = zeros;
        }

        int swaps = 0;

        // For each row position from top to bottom
        for (int i = 0; i < n; i++) {
            int needed = n - 1 - i;   // how many trailing zeros we need

            // Find the first remaining row that satisfies the requirement
            int j = i;
            while (j < n && trailingZeros[j] < needed) {
                j++;
            }

            // Impossible
            if (j == n) {
                return -1;
            }

            // Bubble the chosen row up to position i
            // (each step is one adjacent swap)
            while (j > i) {
                // swap trailingZeros[j] and trailingZeros[j-1]
                int temp = trailingZeros[j];
                trailingZeros[j] = trailingZeros[j - 1];
                trailingZeros[j - 1] = temp;
                j--;
                swaps++;
            }
        }

        return swaps;
    }
}