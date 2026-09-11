class Solution {
    public int[][] diagonalSort(int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;
        
        // Map from diagonal key (i - j) to a min-heap of values
        Map<Integer, PriorityQueue<Integer>> diagonals = new HashMap<>();
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int key = i - j;
                diagonals.computeIfAbsent(key, k -> new PriorityQueue<>())
                         .offer(mat[i][j]);
            }
        }
        
        // Write back sorted values
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int key = i - j;
                mat[i][j] = diagonals.get(key).poll();
            }
        }
        
        return mat;
    }
}