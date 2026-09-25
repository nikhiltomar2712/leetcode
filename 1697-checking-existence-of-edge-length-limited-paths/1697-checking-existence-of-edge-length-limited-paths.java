import java.util.Arrays;

class Solution {
    private static class DSU {
        private int[] parent;
        
        public DSU(int n) {
            parent = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }
        
        public int find(int x) {
            if (parent[x] != x) {
                parent[x] = find(parent[x]); // Path compression
            }
            return parent[x];
        }
        
        public void union(int a, int b) {
            int rootA = find(a);
            int rootB = find(b);
            if (rootA != rootB) {
                parent[rootA] = rootB;
            }
        }
        
        public boolean connected(int a, int b) {
            return find(a) == find(b);
        }
    }
    
    public boolean[] distanceLimitedPathsExist(int n, int[][] edgeList, int[][] queries) {
        // Sort edges by distance ascending
        Arrays.sort(edgeList, (a, b) -> Integer.compare(a[2], b[2]));
        
        // Create queries with original indices, sorted by limit
        int[][] sortedQueries = new int[queries.length][4];
        for (int i = 0; i < queries.length; i++) {
            sortedQueries[i] = new int[]{queries[i][0], queries[i][1], queries[i][2], i};
        }
        Arrays.sort(sortedQueries, (a, b) -> Integer.compare(a[2], b[2]));
        
        DSU dsu = new DSU(n);
        boolean[] answer = new boolean[queries.length];
        int edgeIdx = 0;
        
        // Process queries in increasing order of limit
        for (int[] query : sortedQueries) {
            int p = query[0], q = query[1], limit = query[2], originalIdx = query[3];
            
            // Add all edges with distance < limit
            while (edgeIdx < edgeList.length && edgeList[edgeIdx][2] < limit) {
                dsu.union(edgeList[edgeIdx][0], edgeList[edgeIdx][1]);
                edgeIdx++;
            }
            
            // Check connectivity
            answer[originalIdx] = dsu.connected(p, q);
        }
        
        return answer;
    }
}