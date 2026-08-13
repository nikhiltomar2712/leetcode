class Solution {
    public int numSimilarGroups(String[] strs) {
        int n = strs.length;
        DSU dsu = new DSU(n);
        
        // Compare every pair of strings
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (areSimilar(strs[i], strs[j])) {
                    dsu.union(i, j);
                }
            }
        }
        
        // Count distinct groups (roots)
        int groups = 0;
        for (int i = 0; i < n; i++) {
            if (dsu.find(i) == i) {
                groups++;
            }
        }
        return groups;
    }
    
    // Helper to check if two strings are similar
    private boolean areSimilar(String a, String b) {
        int diffCount = 0;
        // Since all strings are anagrams, they have same length
        for (int i = 0; i < a.length(); i++) {
            if (a.charAt(i) != b.charAt(i)) {
                diffCount++;
                if (diffCount > 2) return false; // Early exit
            }
        }
        // If identical (diffCount == 0) or exactly 2 differences (one swap)
        return diffCount == 0 || diffCount == 2;
    }
    
    // Union-Find (Disjoint Set Union) class
    class DSU {
        int[] parent;
        int[] rank;
        
        DSU(int size) {
            parent = new int[size];
            rank = new int[size];
            for (int i = 0; i < size; i++) {
                parent[i] = i;
                rank[i] = 0;
            }
        }
        
        int find(int x) {
            if (parent[x] != x) {
                parent[x] = find(parent[x]); // Path compression
            }
            return parent[x];
        }
        
        void union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);
            if (rootX == rootY) return;
            
            // Union by rank to keep tree flat
            if (rank[rootX] < rank[rootY]) {
                parent[rootX] = rootY;
            } else if (rank[rootX] > rank[rootY]) {
                parent[rootY] = rootX;
            } else {
                parent[rootY] = rootX;
                rank[rootX]++;
            }
        }
    }
}