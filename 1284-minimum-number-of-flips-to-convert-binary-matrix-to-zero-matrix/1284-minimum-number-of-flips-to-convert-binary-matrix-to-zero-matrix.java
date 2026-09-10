class Solution {
    public int minFlips(int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;
        int start = 0;
        
        // Encode the matrix into a bitmask
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (mat[i][j] == 1) {
                    start |= (1 << (i * n + j));
                }
            }
        }
        
        if (start == 0) return 0;
        
        Queue<Integer> queue = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();
        queue.offer(start);
        visited.add(start);
        
        int steps = 0;
        int[][] dirs = {{0, 0}, {0, 1}, {0, -1}, {1, 0}, {-1, 0}}; // self + 4 neighbors
        
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int s = 0; s < size; s++) {
                int state = queue.poll();
                
                // Try flipping every cell
                for (int i = 0; i < m; i++) {
                    for (int j = 0; j < n; j++) {
                        int next = state;
                        
                        // Flip the cell and its neighbors
                        for (int[] d : dirs) {
                            int ni = i + d[0];
                            int nj = j + d[1];
                            if (ni >= 0 && ni < m && nj >= 0 && nj < n) {
                                next ^= (1 << (ni * n + nj));
                            }
                        }
                        
                        if (next == 0) return steps + 1;
                        
                        if (!visited.contains(next)) {
                            visited.add(next);
                            queue.offer(next);
                        }
                    }
                }
            }
            steps++;
        }
        
        return -1;
    }
}