class Solution {
    public int largestComponentSize(int[] nums) {
        int max = 0;
        for (int num : nums) {
            max = Math.max(max, num);
        }
        
        UnionFind uf = new UnionFind(max + 1);
        
        // For each number, union it with all its factors > 1
        for (int num : nums) {
            for (int i = 2; i * i <= num; i++) {
                if (num % i == 0) {
                    uf.union(num, i);
                    uf.union(num, num / i);
                }
            }
        }
        
        // Count the size of each component (only for the numbers in nums)
        Map<Integer, Integer> count = new HashMap<>();
        int maxSize = 0;
        
        for (int num : nums) {
            int root = uf.find(num);
            count.put(root, count.getOrDefault(root, 0) + 1);
            maxSize = Math.max(maxSize, count.get(root));
        }
        
        return maxSize;
    }
}

class UnionFind {
    int[] parent;
    int[] rank;
    
    public UnionFind(int n) {
        parent = new int[n];
        rank = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
    }
    
    public int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]); // path compression
        }
        return parent[x];
    }
    
    public void union(int x, int y) {
        int px = find(x);
        int py = find(y);
        if (px == py) return;
        
        if (rank[px] < rank[py]) {
            parent[px] = py;
        } else if (rank[px] > rank[py]) {
            parent[py] = px;
        } else {
            parent[py] = px;
            rank[px]++;
        }
    }
}