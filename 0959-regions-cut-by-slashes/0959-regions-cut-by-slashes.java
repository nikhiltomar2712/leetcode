class Solution {
    private int[] parent;
    private int regions;
    
    public int regionsBySlashes(String[] grid) {
        int n = grid.length;
        regions = n * n * 4;
        parent = new int[regions];
        for (int i = 0; i < regions; i++) parent[i] = i;
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int base = 4 * (i * n + j);
                char c = grid[i].charAt(j);
                
                // Connect triangles inside the cell
                if (c == '/') {
                    union(base + 0, base + 3);
                    union(base + 1, base + 2);
                } else if (c == '\\') {
                    union(base + 0, base + 1);
                    union(base + 2, base + 3);
                } else { // space
                    union(base + 0, base + 1);
                    union(base + 1, base + 2);
                    union(base + 2, base + 3);
                }
                
                // Connect with right neighbor
                if (j + 1 < n) {
                    union(base + 1, 4 * (i * n + j + 1) + 3);
                }
                // Connect with bottom neighbor
                if (i + 1 < n) {
                    union(base + 2, 4 * ((i + 1) * n + j) + 0);
                }
            }
        }
        return regions;
    }
    
    private int find(int x) {
        if (parent[x] != x) parent[x] = find(parent[x]);
        return parent[x];
    }
    
    private void union(int a, int b) {
        int pa = find(a), pb = find(b);
        if (pa != pb) {
            parent[pa] = pb;
            regions--;
        }
    }
}