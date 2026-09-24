import java.util.*;

class Solution {
    public int[][] matrixRankTransform(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        
        // Group cells by value (sorted order)
        TreeMap<Integer, List<int[]>> valueToCells = new TreeMap<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                valueToCells.computeIfAbsent(matrix[i][j], k -> new ArrayList<>())
                            .add(new int[]{i, j});
            }
        }
        
        int[][] result = new int[m][n];
        int[] rowMaxRank = new int[m];  // max rank assigned in each row
        int[] colMaxRank = new int[n];  // max rank assigned in each column
        
        // Union-Find: nodes 0..m-1 are rows, nodes m..m+n-1 are columns
        int[] parent = new int[m + n];
        
        for (int value : valueToCells.keySet()) {
            List<int[]> cells = valueToCells.get(value);
            
            // Reset union-find for this value group
            for (int i = 0; i < m + n; i++) parent[i] = i;
            
            // Union rows and columns for cells with the same value
            for (int[] cell : cells) {
                union(parent, cell[0], m + cell[1]);
            }
            
            // Track maximum rank per component
            Map<Integer, Integer> componentMaxRank = new HashMap<>();
            
            // First pass: find the max rank needed for each component
            for (int[] cell : cells) {
                int root = find(parent, cell[0]);
                int rank = Math.max(rowMaxRank[cell[0]], colMaxRank[cell[1]]) + 1;
                componentMaxRank.merge(root, rank, Math::max);
            }
            
            // Second pass: assign ranks and update row/col max ranks
            for (int[] cell : cells) {
                int root = find(parent, cell[0]);
                int rank = componentMaxRank.get(root);
                result[cell[0]][cell[1]] = rank;
                rowMaxRank[cell[0]] = rank;
                colMaxRank[cell[1]] = rank;
            }
        }
        
        return result;
    }
    
    private int find(int[] parent, int x) {
        if (parent[x] != x) {
            parent[x] = find(parent, parent[x]);  // path compression
        }
        return parent[x];
    }
    
    private void union(int[] parent, int a, int b) {
        int rootA = find(parent, a);
        int rootB = find(parent, b);
        if (rootA != rootB) {
            parent[rootA] = rootB;
        }
    }
}