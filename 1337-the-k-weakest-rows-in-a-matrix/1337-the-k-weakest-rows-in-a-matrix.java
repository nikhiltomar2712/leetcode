class Solution {
    public int[] kWeakestRows(int[][] mat, int k) {
        int m = mat.length;
        int n = mat[0].length;
        
        // Store [soldierCount, rowIndex] for each row
        int[][] rows = new int[m][2];
        for (int i = 0; i < m; i++) {
            int count = 0;
            for (int j = 0; j < n; j++) {
                if (mat[i][j] == 1) count++;
                else break; // since 1s are always to the left
            }
            rows[i][0] = count;
            rows[i][1] = i;
        }
        
        // Sort by soldier count, then by index
        Arrays.sort(rows, (a, b) -> {
            if (a[0] != b[0]) return a[0] - b[0];
            return a[1] - b[1];
        });
        
        int[] result = new int[k];
        for (int i = 0; i < k; i++) {
            result[i] = rows[i][1];
        }
        return result;
    }
}