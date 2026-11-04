class Solution {
    public int findChampion(int[][] grid) {
        int n = grid.length;
        for (int i = 0; i < n; i++) {
            boolean champion = true;
            for (int j = 0; j < n; j++) {
                if (i != j && grid[i][j] != 1) {
                    champion = false;
                    break;
                }
            }
            if (champion) return i;
        }
        return -1;
    }
}